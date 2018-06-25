package io.nebulas.explorer.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.nebulas.explorer.domain.*;
import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.enums.NebTransactionStatusEnum;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.model.PageIterator;
import io.nebulas.explorer.model.vo.AddressVo;
import io.nebulas.explorer.model.vo.BlockVo;
import io.nebulas.explorer.model.vo.TransactionVo;
import io.nebulas.explorer.service.blockchain.*;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetAccountStateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Explorer http rpc gateway
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-29
 */
@Slf4j
@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class RpcController {
    private static final Integer PAGE_SIZE = 25;
    private static final int MAX_PAGE = 400;

    private final NebAddressService nebAddressService;
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebMarketCapitalizationService nebMarketCapitalizationService;
    private final NebDynastyService nebDynastyService;
    private final NebApiServiceWrapper nebApiServiceWrapper;
    private final StringRedisTemplate redisTemplate;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    @RequestMapping(value = "/market_cap", method = RequestMethod.GET)
    public JsonResult marketCap() {
        return JsonResult.success(nebMarketCapitalizationService.getLatest());
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET, params = "type=latest")
    public JsonResult latestBlock() {
        List<NebBlock> blkList = nebBlockService.findNebBlockOrderByHeight(1, 10);
        return JsonResult.success(convertBlock2BlockVo(blkList, false));
    }

    @RequestMapping(value = "/block")
    public JsonResult blocks(@RequestParam(value = "m", required = false) String miner,
                             @RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        PageIterator<NebBlock> blockPageIterator;

        if (StringUtils.isEmpty(miner)) {
            blockPageIterator = nebBlockService.findNebBlockPageIterator(page, PAGE_SIZE);
        } else {
            blockPageIterator = nebBlockService.findNebBlockPageIteratorByMiner(miner, page, PAGE_SIZE);
        }

        PageIterator<BlockVo> blockVoPageIterator = PageIterator.create(blockPageIterator.getPage(), blockPageIterator.getPageSize(), blockPageIterator.getTotalCount());
        blockVoPageIterator.setData(convertBlock2BlockVo(blockPageIterator.getData(), true));

        return JsonResult.success(blockVoPageIterator);
    }

    @RequestMapping(value = "/block/{blkKey}", method = RequestMethod.GET)
    public JsonResult block(@PathVariable("blkKey") String blkKey) {
        NebBlock block;
        if (StringUtils.isNumeric(blkKey)) {
            block = nebBlockService.getNebBlockByHeight(Long.valueOf(blkKey));
        } else {
            block = nebBlockService.getNebBlockByHash(blkKey);
        }

        if (null == block) {
            return JsonResult.failed("block does not exist");
        }

        JsonResult result = JsonResult.success(block);
        result.put("currentTimestamp", new Date());
        result.put("timeDiff", System.currentTimeMillis() - block.getTimestamp().getTime());
        result.put("blkMaxHeight", nebBlockService.getMaxHeight());
        result.put("dynasty", nebDynastyService.findDynastyDelegateByBlockHeight(block.getHeight()));
        result.put("blkSummary", nebTransactionService.getBlockSummaryByBlockHeight(block.getHeight()));

        NebAddress nebAddress = nebAddressService.getNebAddressByHash(block.getMiner());
        result.put("miner", null != nebAddress ? nebAddress : new NebAddress(block.getMiner()));
        return result;
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET, params = "type=latest")
    public JsonResult latestTransaction() {
        List<NebTransaction> txnList = nebTransactionService.findTxnOrderById(1, 10);
        return JsonResult.success(convertTxn2TxnVoWithAddress(txnList));
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET)
    public JsonResult transactions(@RequestParam(value = "block", required = false) Long block,
                                   @RequestParam(value = "a", required = false) String address,
                                   @RequestParam(value = "p", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "isPending", required = false, defaultValue = "false") Boolean isPending) {
        String type;
        if (null != block) {
            type = "block";
        } else if (StringUtils.isNoneEmpty(address)) {
            type = "address";
        } else {
            type = "total";
        }

        JsonResult result = JsonResult.success();
        long txnCnt;
        if (!isPending) {
            if (page > 20) {
                page = 20;
            }
            txnCnt = nebTransactionService.countTxnCnt(block, address);
            List<NebTransaction> txnList = nebTransactionService.findTxnByCondition(block, address, page, PAGE_SIZE);
            result.put("txnList", convertTxn2TxnVoWithAddress(txnList));

        } else {
            txnCnt = nebTransactionService.countPendingTxnCnt(address);
            List<NebPendingTransaction> pendingTxnList = nebTransactionService.findPendingTxnByCondition(address, page, PAGE_SIZE);
            result.put("txnList", convertPendingTxn2TxnVo(pendingTxnList));
        }

        result.put("type", type);
        result.put("txnCnt", txnCnt);
        result.put("currentPage", page);

        long totalPage = txnCnt / PAGE_SIZE + 1;
        result.put("totalPage", !isPending && totalPage > 20 ? 20 : totalPage);
        result.put("maxDisplayCnt", txnCnt > 500 ? 500 : txnCnt);
        return result;
    }

    @RequestMapping("/tx/{txHash}")
    public JsonResult tx(@PathVariable("txHash") String txHash) {
        boolean isPending = false;
        NebTransaction txn = nebTransactionService.getNebTransactionByHash(txHash);
        if (null == txn) {
            NebPendingTransaction pendingTxn = nebTransactionService.getNebPendingTransactionByHash(txHash);
            if (null != pendingTxn) {
                txn = new NebTransaction();
                try {
                    PropertyUtils.copyProperties(txn, pendingTxn);
                    txn.setStatus(NebTransactionStatusEnum.PENDING.getValue());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                isPending = true;
            }
        }

        if (null == txn) {
            return JsonResult.failed("transaction does not exist");
        }

        List<String> addressHashList = Arrays.asList(txn.getFrom(), txn.getTo());
        Map<String, NebAddress> nebAddressMap = nebAddressService.findAddressMapByAddressHash(addressHashList);

        TransactionVo vo = new TransactionVo();
        vo.build(txn);
        if (!isPending) {
            vo.setBlock(new BlockVo(txn.getBlockHash(), txn.getBlockHeight()));
        }

        NebAddress fromAddress = nebAddressMap.get(txn.getFrom());
        vo.setFrom(fromAddress != null ? (new AddressVo().build(fromAddress)) : new AddressVo(txn.getFrom()));

        NebAddress toAddress = nebAddressMap.get(txn.getTo());
        vo.setTo(toAddress != null ? (new AddressVo().build(toAddress)) : new AddressVo(txn.getTo()));

        JsonResult result = JsonResult.success();
        result.add(vo);
        result.put("isPending", isPending);
        return result;
    }

    @RequestMapping("/tx/cnt_static")
    public JsonResult txStatic() {
        return JsonResult.success(nebTransactionService.countTxCntGroupMapByTimestamp(LocalDate.now().plusDays(-15).toDate(), LocalDate.now().toDate()));
    }

    @RequestMapping("/account")
    public JsonResult accounts(@RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        if (page < 1) {
            page = 1;
        }
        if (page > MAX_PAGE) {
            page = MAX_PAGE;
        }

        long maxBlockHeight = nebBlockService.getMaxHeight();
        BigDecimal totalBalance = new BigDecimal("100000000").subtract(new BigDecimal(1.42694 * maxBlockHeight)).setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros();

        List<NebAddress> addressList = nebAddressService.findAddressOrderByBalance(page, PAGE_SIZE);

        Map<String, BigDecimal> percentageMap = addressList.stream()
                .collect(Collectors.toMap(NebAddress::getHash, a -> {
                    BigDecimal balanceBD = a.getCurrentBalance().divide(BigDecimal.valueOf(Math.pow(10, 18)), 18, BigDecimal.ROUND_DOWN);
                    return balanceBD.divide(totalBalance, 8, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).stripTrailingZeros();
                }));

        List<String> addressHashList = addressList.stream().map(NebAddress::getHash).collect(Collectors.toList());
        Map<String, Long> txCntMap = nebTransactionService.countTxnCntByFromTo(addressHashList);

        List<AddressVo> voList = Lists.newLinkedList();
        int i = 1 + (page - 1) * PAGE_SIZE;
        for (NebAddress address : addressList) {
            AddressVo vo = new AddressVo().build(address);
            vo.setRank(i);
            vo.setTxCnt(txCntMap.get(address.getHash()));
            vo.setPercentage(percentageMap.get(address.getHash()).toPlainString());
            voList.add(vo);
            i++;
        }

        JsonResult result = JsonResult.success();
        long totalAccountsCnt = nebAddressService.countTotalAddressCnt();
        long totalPage = totalAccountsCnt / PAGE_SIZE + 1;
        result.put("totalAccountsCnt", totalAccountsCnt);
        result.put("totalBalance", totalBalance.toPlainString());
        result.put("page", page);
        result.put("addressList", voList);
        result.put("totalPage", totalPage > MAX_PAGE ? MAX_PAGE : totalPage);

        EXECUTOR.execute(() -> {
            for (NebAddress address : addressList) {
                if (address.getUpdatedAt().before(LocalDateTime.now().plusSeconds(-5).toDate())) {
                    GetAccountStateResponse accountState = nebApiServiceWrapper.getAccountState(address.getHash());
                    if (null != accountState && StringUtils.isNotEmpty(accountState.getBalance())) {
                        nebAddressService.updateAddressBalance(address.getHash(), accountState.getBalance(), accountState.getNonce());
                    }
                }
            }
        });
        return result;
    }

    @RequestMapping("/dapp/{hash}")
    public JsonResult dapp(@PathVariable("hash") String hash) {
        String existKey = "dapp-key-" + hash;
        String valueKey = "dapp-value-" + hash;
        NebAddress address = nebAddressService.getNebAddressByHash(hash);
        if (null == address) {
            return JsonResult.failed();
        }
        try {
            if (redisTemplate.opsForValue().setIfAbsent(existKey, "1")) {
                redisTemplate.expire(existKey, 5, TimeUnit.MINUTES);
                if (address.getUpdatedAt().before(LocalDateTime.now().plusSeconds(-5).toDate())) {
                    GetAccountStateResponse accountState = nebApiServiceWrapper.getAccountState(address.getHash());
                    if (null != accountState && StringUtils.isNotEmpty(accountState.getBalance())) {
                        String balance = accountState.getBalance();
                        String nonce = accountState.getNonce();
                        address.setCurrentBalance(new BigDecimal(balance));
                        nebAddressService.updateAddressBalance(hash, balance, nonce);
                    }
                }
                double base = Math.pow(10, 18);
                JsonResult result = JsonResult.success();
                String balance = "0";
                if (address.getCurrentBalance() != null && address.getCurrentBalance().compareTo(BigDecimal.ZERO) > 0) {
                    balance = address.getCurrentBalance().divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toString();
                }
                result.put("balance", balance);
                int page = 1;
                boolean loop = true;
                List<NebTransaction> txList = Lists.newLinkedList();
                List<CompletableFuture<List<NebTransaction>>> cfList = new ArrayList<>();
                long txCnt = nebTransactionService.countTxnByTo(hash);
                int pageSize = Math.max((int) (txCnt / 10), 10);
                do {
                    final int p = page++;
                    cfList.add(CompletableFuture.supplyAsync(() -> {
                        return nebTransactionService.findTxnByTo(hash, p, pageSize);
                    }));
                    loop = p*pageSize < txCnt;
                } while (loop);

                cfList.stream().map(c -> txList.addAll(c.join())).collect(Collectors.toList());

                // Daily active users
                long dau = 0;
                long au24h = 0;
                long au7d = 0;
                long au = 0;
                if (!txList.isEmpty()) {
                    dau = txList.parallelStream()
                            .filter(tx -> LocalDate.fromDateFields(tx.getTimestamp()).equals(LocalDate.now()))
                            .map(tx -> tx.getFrom()).distinct().count();

                    au24h = txList.parallelStream()
                            .filter(tx -> new DateTime(tx.getTimestamp()).isAfter(DateTime.now().minusHours(24)))
                            .map(tx -> tx.getFrom()).distinct().count();

                    au7d = txList.parallelStream()
                            .filter(tx -> new DateTime(tx.getTimestamp()).isAfter(DateTime.now().minusDays(7)))
                            .map(tx -> tx.getFrom()).distinct().count();

                    au = txList.parallelStream()
                            .map(tx -> tx.getFrom()).distinct().count();

                }

                String vol24h = "0";
                String vol7d = "0";
                String vol = "0";
                if (!txList.isEmpty()) {
                    vol24h = txList.parallelStream()
                            .filter(tx -> new DateTime(tx.getTimestamp()).isAfter(DateTime.now().minusHours(24)))
                            .filter(tx -> Integer.valueOf(NebTransactionStatusEnum.SUCCESS.getValue()).equals(tx.getStatus()))
                            .map(tx -> new BigDecimal(tx.getValue())).reduce(new BigDecimal(0), (sum, a) -> sum.add(a))
                            .divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString();
                    vol7d = txList.parallelStream()
                            .filter(tx -> new DateTime(tx.getTimestamp()).isAfter(DateTime.now().minusDays(7)))
                            .filter(tx -> Integer.valueOf(NebTransactionStatusEnum.SUCCESS.getValue()).equals(tx.getStatus()))
                            .map(tx -> new BigDecimal(tx.getValue())).reduce(new BigDecimal(0), (sum, a) -> sum.add(a))
                            .divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString();

                    vol = txList.parallelStream().filter(tx -> Integer.valueOf(NebTransactionStatusEnum.SUCCESS.getValue()).equals(tx.getStatus()))
                            .map(tx -> new BigDecimal(tx.getValue())).reduce(new BigDecimal(0), (sum, a) -> sum.add(a))
                            .divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString();
                }

                long tx24h = 0;
                long tx7d = 0;
                long tx = 0;
                if (!txList.isEmpty()) {
                    tx24h = txList.parallelStream()
                            .filter(txn -> new DateTime(txn.getTimestamp()).isAfter(DateTime.now().minusHours(24))).count();
                    tx7d = txList.parallelStream()
                            .filter(txn -> new DateTime(txn.getTimestamp()).isAfter(DateTime.now().minusDays(7))).count();
                    tx = txList.stream().count();
                }

                result.put("dau", dau);
                result.put("au24h", au24h);
                result.put("au7d", au7d);
                result.put("au", au);
                result.put("vol24h", vol24h);
                result.put("vol7d", vol7d);
                result.put("vol", vol);
                result.put("tx24h", tx24h);
                result.put("tx7d", tx7d);
                result.put("tx", tx);

                Future<List<Map<String, String>>> topAccounts = EXECUTOR.submit(() -> {
                    int p = 1;
                    int ps = 20;
                    Set<String> r = Sets.newLinkedHashSet();
                    List<NebTransaction> list = Lists.newArrayList();
                    do {
                        list = nebTransactionService.findTopAccount(hash, p++, ps);
                        r.addAll(list.stream().map(e -> e.getFrom()).distinct().collect(Collectors.toList()));
                    } while(r.size() < 10 && list.size() > 0);

                    return r.stream().map(e -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("address", e);
                        NebAddress na = nebAddressService.getNebAddressByHash(e);
                        if (na.getUpdatedAt().before(LocalDateTime.now().plusSeconds(-5).toDate())) {
                            GetAccountStateResponse accountState = nebApiServiceWrapper.getAccountState(na.getHash());
                            if (null != accountState && StringUtils.isNotEmpty(accountState.getBalance())) {
                                String b = accountState.getBalance();
                                String nonce = accountState.getNonce();
                                na.setCurrentBalance(new BigDecimal(b));
                                nebAddressService.updateAddressBalance(hash, b, nonce);
                            }
                        }
                        map.put("value", na.getCurrentBalance().divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString());
                        return map;
                    }).limit(10).collect(Collectors.toList());
                });

                Future<List<Map<String,String>>> topTxs = EXECUTOR.submit(() -> {
                    List<NebTransaction> list = nebTransactionService.findTopTxn(hash, 1, 10);
                    return list.stream().map(e -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("txHash", e.getHash());
                        map.put("value", new BigDecimal(e.getValue()).divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString());
                        return map;
                    }).collect(Collectors.toList());
                });

                Future<List<Map<String, String>>> recentAccounts = EXECUTOR.submit(() -> {
                    int p = 1;
                    int ps = 20;
                    Set<String> r = Sets.newLinkedHashSet();
                    List<NebTransaction> list = Lists.newArrayList();
                    do {
                        list = nebTransactionService.findRecentTxn(hash, p++, ps);
                        r.addAll(list.stream().map(e -> e.getFrom()).distinct().collect(Collectors.toList()));
                    } while(r.size() < 10 && list.size() > 0);

                    return r.stream().map(e -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("address", e);
                        NebAddress na = nebAddressService.getNebAddressByHash(e);
                        if (na.getUpdatedAt().before(LocalDateTime.now().plusSeconds(-5).toDate())) {
                            GetAccountStateResponse accountState = nebApiServiceWrapper.getAccountState(na.getHash());
                            if (null != accountState && StringUtils.isNotEmpty(accountState.getBalance())) {
                                String b = accountState.getBalance();
                                String nonce = accountState.getNonce();
                                na.setCurrentBalance(new BigDecimal(b));
                                nebAddressService.updateAddressBalance(hash, b, nonce);
                            }
                        }
                        map.put("value", na.getCurrentBalance().divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString());
                        return map;
                    }).limit(10).collect(Collectors.toList());
                });

                Future<List<Map<String,String>>> recentTxs = EXECUTOR.submit(() -> {
                    List<NebTransaction> list = nebTransactionService.findRecentTxn(hash, 1, 10);
                    return list.stream().map(e -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("txHash", e.getHash());
                        map.put("value", new BigDecimal(e.getValue()).divide(new BigDecimal(base), 8, RoundingMode.FLOOR).toPlainString());
                        return map;
                    }).collect(Collectors.toList());
                });

                Future<List<Map<String,Object>>> trend7days = EXECUTOR.submit(() -> {
                    List<Map<String, Object>> list = Lists.newArrayList();
                    DateTime now = DateTime.now();
                    for (int k = 0; k < 7; k++) {
                        Map<String, Object> m = new HashMap<>();
                        m.put("d", now.minusDays(k).toString("yyyy-MM-dd"));
                        m.put("c", 0);
                        list.add(m);
                    }
                    List<Map<String, Object>> dbList = nebTransactionService.recent7days(hash);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date last = new Date();
                    for (Map<String, Object> map : dbList) {
                        last = (Date) map.get("d");
                        String dv = sdf.format(last);
                        list.stream().filter(e -> e.get("d").equals(dv)).findFirst().get().put("c", map.get("c"));
                    }
                    return list;
                });

                try {
                    result.put("topAccounts", topAccounts.get());
                    result.put("topTxs", topTxs.get());
                    result.put("recentAccounts", recentAccounts.get());
                    result.put("recentTxs", recentTxs.get());
                    result.put("trend7days", trend7days.get());
                    redisTemplate.opsForValue().set(valueKey, result.toJsonString(), 5, TimeUnit.MINUTES);
                } catch (InterruptedException | ExecutionException e) {
                    log.error("error", e);
                }

                return result;
            }
        } catch (Exception e) {
            redisTemplate.delete(existKey);
        }

        return JSON.parseObject(redisTemplate.opsForValue().get(valueKey), JsonResult.class);
    }

    @RequestMapping("/address/{hash}")
    public JsonResult address(@PathVariable("hash") String hash) {
        NebAddress address = nebAddressService.getNebAddressByHashRpc(hash);
        if (null == address) {
            return JsonResult.failed();
        }

        nebAddressService.updateAddressBalance(hash, address.getCurrentBalance().toPlainString(), address.getNonce());

        long pendingTxCnt = nebTransactionService.countPendingTxnCnt(address.getHash());

        JsonResult result = JsonResult.success();
        result.put("address", address);
        result.put("pendingTxCnt", pendingTxCnt);
        result.put("txCnt", nebTransactionService.countTxnCntByFromTo(address.getHash()));
        result.put("mintedBlkCnt", nebBlockService.countBlockCntByMiner(address.getHash()));

        List<NebBlock> blkList = nebBlockService.findNebBlockByMiner(address.getHash(), 1, PAGE_SIZE);
        result.put("mintedBlkList", convertBlock2BlockVo(blkList, true));

        List<NebTransaction> txList = Lists.newLinkedList();
        if (pendingTxCnt > 0) {
            List<NebPendingTransaction> pendingTxnList = nebTransactionService.findPendingTxnByCondition(address.getHash(), 1, PAGE_SIZE);
            pendingTxnList.forEach(pTxn -> {
                try {
                    NebTransaction tx = new NebTransaction();
                    PropertyUtils.copyProperties(tx, pTxn);
                    tx.setBlockHeight(0L);
                    txList.add(tx);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
            if (pendingTxnList.size() < PAGE_SIZE) {
                txList.addAll(nebTransactionService.findTxnByFromTo(address.getHash(), 1, PAGE_SIZE - pendingTxnList.size()));
            }
        } else {
            txList.addAll(nebTransactionService.findTxnByFromTo(address.getHash(), 1, PAGE_SIZE));
        }
        result.put("txList", convertTxn2TxnVoWithAddress(txList));

//        if (address.getUpdatedAt().before(LocalDateTime.now().plusSeconds(-5).toDate())) {
//            GetAccountStateResponse accountState = nebApiServiceWrapper.getAccountState(address.getHash());
//            if (null != accountState && StringUtils.isNotEmpty(accountState.getBalance())) {
//                String balance = accountState.getBalance();
//                address.setCurrentBalance(new BigDecimal(balance));

//            }
//        }

        //contract address
        if (NebAddressTypeEnum.CONTRACT.getValue() == address.getType()) {
            NebTransaction nebTx = nebTransactionService.getNebTransactionByContractAddress(address.getHash());
            if (null != nebTx) {
                try {
                    result.put("contractCode", StringUtils.isNotEmpty(nebTx.getData()) ? new String(DECODER.decode(nebTx.getData()), "UTF-8") : "");
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    @RequestMapping("/search")
    public JsonResult search(@RequestParam(value = "q") String q) {
        if (StringUtils.isEmpty(q)) {
            return JsonResult.success("type", "unknown").put("q", "");
        }
        if (StringUtils.isNumeric(q)) {
            NebBlock block = nebBlockService.getNebBlockByHeight(Long.valueOf(q));
            if (null != block) {
                return JsonResult.success("type", "block").put("q", block.getHeight());
            }
            return JsonResult.success("type", "unknown").put("q", q);
        }
        if (q.length() < 64) {
            NebAddress address = nebAddressService.getNebAddressByHash(q);
            if (null != address) {
                return JsonResult.success("type", "address").put("q", address.getHash());
            }
            address = nebAddressService.getNebAddressByHashRpc(q);
            if (null != address && address.getType() == NebAddressTypeEnum.NORMAL.getValue()) {
                nebAddressService.addNebAddress(address);
                return JsonResult.success("type", "address").put("q", address.getHash());
            }
        } else {
            NebBlock block = nebBlockService.getNebBlockByHash(q);
            if (null != block) {
                return JsonResult.success("type", "block").put("q", block.getHeight());
            }
            NebTransaction transaction = nebTransactionService.getNebTransactionByHash(q);
            if (null != transaction) {
                return JsonResult.success("type", "tx").put("q", transaction.getHash());
            }
        }
        return JsonResult.success("type", "unknown").put("q", q);
    }

    private List<BlockVo> convertBlock2BlockVo(List<NebBlock> blks, boolean isWithGas) {
        if (CollectionUtils.isEmpty(blks)) {
            return Collections.emptyList();
        }
        List<Long> blkHeightList = blks.stream().map(NebBlock::getHeight).collect(Collectors.toList());
        List<String> minerHashList = blks.stream().map(NebBlock::getMiner).collect(Collectors.toList());

        Map<Long, BlockSummary> txCntMap = nebTransactionService.calculateTxnSummaryInBlock(blkHeightList, isWithGas);
        Map<String, NebAddress> addressMap = nebAddressService.findAddressMapByAddressHash(minerHashList);

        List<BlockVo> resultList = new LinkedList<>();
        for (NebBlock blk : blks) {
            BlockVo vo = new BlockVo()
                    .build(blk)
                    .setMiner(blk.getMiner(), addressMap.get(blk.getMiner()))
                    .setSummary(txCntMap.get(blk.getHeight()));
            resultList.add(vo);
        }
        return resultList;
    }

    private List<TransactionVo> convertTxn2TxnVoWithAddress(List<NebTransaction> txns) {
        if (CollectionUtils.isEmpty(txns)) {
            return Collections.emptyList();
        }

        Set<String> addressHashSet = Sets.newHashSet();
        txns.forEach(txn -> {
            addressHashSet.add(txn.getFrom());
            addressHashSet.add(txn.getTo());
        });
        Map<String, NebAddress> nebAddressMap = nebAddressService.findAddressMapByAddressHash(Lists.newArrayList(addressHashSet));

        List<TransactionVo> txnVoList = new LinkedList<>();
        for (NebTransaction txn : txns) {
            TransactionVo vo = new TransactionVo()
                    .build(txn)
                    .setBlockVo(txn.getBlockHash(), txn.getBlockHeight())
                    .setFromVo(txn.getFrom(), nebAddressMap.get(txn.getFrom()))
                    .setToVo(txn.getTo(), nebAddressMap.get(txn.getTo()));
            txnVoList.add(vo);
        }
        return txnVoList;
    }

    private List<TransactionVo> convertPendingTxn2TxnVo(List<NebPendingTransaction> pendingTxns) {
        if (CollectionUtils.isEmpty(pendingTxns)) {
            return Collections.emptyList();
        }

        Set<String> addressHashSet = Sets.newHashSet();
        pendingTxns.forEach(txn -> {
            addressHashSet.add(txn.getFrom());
            addressHashSet.add(txn.getTo());
        });
        Map<String, NebAddress> nebAddressMap = nebAddressService.findAddressMapByAddressHash(Lists.newArrayList(addressHashSet));

        List<TransactionVo> txnVoList = new LinkedList<>();
        for (NebPendingTransaction txn : pendingTxns) {
            TransactionVo vo = new TransactionVo()
                    .build(txn)
                    .setFromVo(txn.getFrom(), nebAddressMap.get(txn.getFrom()))
                    .setToVo(txn.getTo(), nebAddressMap.get(txn.getTo()));
            txnVoList.add(vo);
        }
        return txnVoList;
    }
}
