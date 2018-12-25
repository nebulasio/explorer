package io.nebulas.explorer.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.nebulas.explorer.domain.*;
import io.nebulas.explorer.domain.extention.ContractTransaction;
import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.enums.NebTransactionStatusEnum;
import io.nebulas.explorer.enums.NebTransactionTypeEnum;
import io.nebulas.explorer.mapper.NebContractTokenMapper;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.model.PageIterator;
import io.nebulas.explorer.model.vo.AddressVo;
import io.nebulas.explorer.model.vo.BlockVo;
import io.nebulas.explorer.model.vo.ContractListItemVo;
import io.nebulas.explorer.model.vo.Nrc20TransactionVo;
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
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
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
    //暂时屏蔽该地址的请求
    private static String BAN_ADDRESS = "n1ggAx4ZJ9Bn63Fuor8ZbqXAJ6x49wLi11D";

    private final NebAddressService nebAddressService;
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebMarketCapitalizationService nebMarketCapitalizationService;
    private final NebDynastyService nebDynastyService;
    private final NebApiServiceWrapper nebApiServiceWrapper;
    private final NebStatService nebStatService;
    private final NebEventService nebEventService;
    private final NasAccountService nasAccountService;
    private final NebTxCountByDayService nebTxCountByDayService;

    @Qualifier("customStringTemplate")
    private final StringRedisTemplate redisTemplate;
    @Qualifier("customRedisTemplate")
    private final RedisTemplate<String, String> mapRedisTemplate;

    private final ContractTokenService contractTokenService;
    private final ContractTokenBalanceService contractTokenBalanceService;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);
    private static final Base64.Decoder DECODER = Base64.getDecoder();
    private static final ExecutorService DB_UPDATE_EXECUTOR = Executors.newSingleThreadExecutor();

    private static long lastUpdateTimeForContractTokens = 0;
    private static final long HOUR_1 = 60 * 60 * 1000;
    private static Set<String> allContractTokens = new HashSet<>(8);

    private final byte[] lock1 = new byte[0];
    private final byte[] lock2 = new byte[0];

    @RequestMapping(value = "/market_cap", method = RequestMethod.GET)
    public JsonResult marketCap() {
        return JsonResult.success(nebMarketCapitalizationService.getLatest());
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public JsonResult getContractList(@RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        long now = System.currentTimeMillis();
        if (now - lastUpdateTimeForContractTokens > HOUR_1) {
            List<NebContractToken> tokens = contractTokenService.getAllContractTokens();
            lastUpdateTimeForContractTokens = now;
            for (NebContractToken token : tokens) {
                allContractTokens.add(token.getContract());
            }
        }
        List<NebAddress> contractList = nebAddressService.getContractList(page, PAGE_SIZE);

        List<ContractListItemVo> listItemVos = new ArrayList<>(contractList.size());
        for (NebAddress address : contractList) {
            ContractListItemVo vo = new ContractListItemVo();
            vo.setHash(address.getHash());
            vo.setAlias(address.getAlias());
            vo.setBalance(address.getBalance());
            vo.setCreatedAt(address.getCreatedAt());
            vo.setContractType(
                    allContractTokens.contains(address.getHash()) ?
                            ContractListItemVo.ContractType.NRC20_TOKEN :
                            ContractListItemVo.ContractType.NORMAL
            );
            listItemVos.add(vo);
        }

        long totalContractCount = nebAddressService.getTotalContractCount();
        long totalPage;
        if (totalContractCount % PAGE_SIZE == 0) {
            totalPage = totalContractCount / 25;
        } else {
            totalPage = totalContractCount / 25 + 1;
        }
        JsonResult result = JsonResult.success();
        result.put("contracts", listItemVos);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("total", totalContractCount);
        return result;
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public JsonResult latestBlock(@RequestParam(value = "type") String type) {
        List<NebBlock> blkList = new ArrayList<>();
        if (type.equals("latest")) {
            blkList = nebBlockService.findNebBlockOrderByHeight(1, 40);
        } else if (type.equals("newblock")) {
            blkList = nebBlockService.findLatestBlock();
        } else {
            blkList = nebBlockService.findNebBlockOrderByHeight(1, 10);
        }
        return JsonResult.success(convertBlock2BlockVo(blkList, false));
    }

    @RequestMapping(value = "/blocks")
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
        log.info("Tracing: RpcController: Start to get transaction list : " + System.currentTimeMillis());
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
            log.info("Tracing: RpcController: Start to count total of transaction : " + System.currentTimeMillis());
            txnCnt = nebTransactionService.countTxnCnt(block, address);
            log.info("Tracing: RpcController: End count total of transaction : " + System.currentTimeMillis());

            log.info("Tracing: RpcController: Start to get list of transaction : page " + page + " : " + System.currentTimeMillis());
            List<NebTransaction> txnList = nebTransactionService.findTxnByCondition(block, address, page, PAGE_SIZE);
            log.info("Tracing: RpcController: End list of transaction : " + System.currentTimeMillis());

            log.info("Tracing: RpcController: Start to convert to VO : " + System.currentTimeMillis());
            result.put("txnList", convertTxn2TxnVoWithAddress(txnList));
            log.info("Tracing: RpcController: End convert to VO : " + System.currentTimeMillis());

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
        log.info("Tracing: RpcController: End get transaction list : " + System.currentTimeMillis());
        return result;
    }

    @RequestMapping("/tx/{txHash}")
    public JsonResult tx(@PathVariable("txHash") String txHash) {

        JsonResult result = JsonResult.success();

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

        if (toAddress != null) {
            vo.setTo(new AddressVo().build(toAddress));
            NebContractToken contractToken = contractTokenService.getByContract(toAddress.getHash());
            if (contractToken == null) {
                vo.setTokenName("");
                vo.setDecimal(18L);
                //result.put("decimal", "18");
            } else {
                vo.setTokenName(contractToken.getTokenName());
                vo.setDecimal(contractToken.getTokenDecimals());
                //result.put("decimal", contractToken.getTokenDecimals());
            }
        } else {
            vo.setTo(new AddressVo(txn.getTo()));
        }

        vo.setEvents(nebEventService.findEventListByHash(txHash));

        result.add(vo);
        result.put("isPending", isPending);

        return result;
    }

    @RequestMapping("/tx/cnt_static")
    public JsonResult txStatic() {
        log.info("Tracing: Start api cnt_static : " + System.currentTimeMillis());
        DateTime to = DateTime.now(DateTimeZone.UTC).withTimeAtStartOfDay();
        DateTime from = to.minusDays(15);
        Map<String, Integer> result = new HashMap<>(15);
        while (from.isBefore(to)) {
            String format = from.toString("yyyy-MM-dd");
            String key = "tx_count_" + format;
            Integer count;
            String cache = redisTemplate.opsForValue().get(key);
            if (cache == null) {
                count = getTxCountByDay(from);
                redisTemplate.opsForValue().set(key, count.toString());
                redisTemplate.opsForValue().getOperations().expire(key, 24, TimeUnit.HOURS);
            } else {
                count = Integer.parseInt(cache);
            }
            result.put(format, count);
            from = from.plusDays(1);
        }

        log.info("Tracing: End api cnt_static : " + System.currentTimeMillis());
        return JsonResult.success(result);
    }

    private Integer getTxCountByDay(DateTime day) {
        log.info("Tracing: Start getTxCountByDay : " + System.currentTimeMillis());
        NebTxCountByDay countByDay;
        synchronized (lock1) {
            log.info("Tracing: Start to find in neb_tx_count_by_day : " + System.currentTimeMillis());
            countByDay = nebTxCountByDayService.getByDay(day.toDate());
            log.info("Tracing: End find in neb_tx_count_by_day : " + System.currentTimeMillis());
            if (countByDay == null) {
                log.info("Tracing: Record in neb_tx_count_by_day not exist! Start to count with neb_transaction : " + System.currentTimeMillis());
                countByDay = new NebTxCountByDay();
                countByDay.setDay(day.toDate());
                int count = nebTransactionService.countTxCountByDate(day.toDate());
                countByDay.setCount(count);
                final NebTxCountByDay newRecord = countByDay;
                log.info("Tracing: Record in neb_tx_count_by_day not exist! End count with neb_transaction : " + System.currentTimeMillis());
                DB_UPDATE_EXECUTOR.submit(() -> {
                    log.info("Tracing: Record in neb_tx_count_by_day not exist! Start to insert new record : " + System.currentTimeMillis() + " : " + Thread.currentThread().getName());
                    nebTxCountByDayService.insert(newRecord);
                    log.info("Tracing: Record in neb_tx_count_by_day not exist! End insert new record : " + System.currentTimeMillis() + " : " + Thread.currentThread().getName());
                });
            }
        }
        log.info("Tracing: End getTxCountByDay : " + System.currentTimeMillis());
        return countByDay.getCount();
    }

    @RequestMapping("/tx/cnt_today")
    public JsonResult txToday() {
        log.info("Tracing: Start api cnt_today : " + System.currentTimeMillis());
        DateTime today = DateTime.now(DateTimeZone.UTC).withTimeAtStartOfDay();
        String format = today.toString("yyyy-MM-dd");
        String redisKey = "tx_today_" + format;
        String cache = redisTemplate.opsForValue().get(redisKey);
        int count = 0;
        if (cache != null) {
            log.info("Tracing: count transactions of today : Get it from redis!");
            count = Integer.parseInt(cache);
        } else {
            log.info("Tracing: Start to count transactions of today : " + System.currentTimeMillis());
            count = nebTransactionService.countTxCountByDate(today.toDate());
            redisTemplate.opsForValue().set(redisKey, Integer.toString(count));
            log.info("Tracing: End api cnt_today : " + System.currentTimeMillis());
        }

        log.info("Tracing: End api cnt_today : " + System.currentTimeMillis());
        return JsonResult.success(count);
    }

    @GetMapping("/stat/data")
    public JsonResult statData() {
        return JsonResult.success(nebStatService.stat());
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
        result.put("decimal", 18);

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

    @RequestMapping("/contract/holders")
    public JsonResult contractHolders(
            @RequestParam(value = "p", required = false, defaultValue = "1") int page,
            @RequestParam(value = "contract") String contract) {
        page = Math.min(Math.max(1, page), MAX_PAGE);
        NebContractToken token = contractTokenService.getByContract(contract);
        if (token == null) {
            return JsonResult.failed();
        }

        long totalCount = contractTokenBalanceService.countValidHolders(contract);
        JsonResult result = JsonResult.success();
        if (totalCount == 0) {
            result.put("totalHolderCount", totalCount);
            result.put("totalPageCount", 0);
            result.put("page", page);
            result.put("holders", Collections.emptyList());
            return result;
        }

        long pageCount = (totalCount - 1) / PAGE_SIZE + 1;
        result.put("totalHolderCount", totalCount);
        result.put("totalPageCount", pageCount);
        result.put("page", page);
        result.put("decimal", token.getTokenDecimals());

        BigDecimal total = token.getTotal();
        List<NebContractTokenBalance> addressBalanceList = contractTokenBalanceService.getValidAddressesByContractOrderByBalance(contract, (page - 1) * PAGE_SIZE, PAGE_SIZE);
        List<ContractHolder> holders = new LinkedList<>();
        int i = (page - 1) * PAGE_SIZE;
        for (NebContractTokenBalance balance : addressBalanceList) {
            i++;
            ContractHolder holder = new ContractHolder();
            holder.setAddress(balance.getAddress());
            holder.setBalance(balance.getBalance());
            holder.setContract(balance.getContract());
            holder.setRank(i);
            holder.setPercentage(balance.getBalance()
                    .divide(total, 18, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .stripTrailingZeros()
                    .toPlainString());
            holders.add(holder);
        }
        result.put("holders", holders);
        return result;
    }

    @RequestMapping("/contract/tx")
    public JsonResult contractTransaction(
            @RequestParam(value = "p", required = false, defaultValue = "1") int page,
            @RequestParam(value = "contract") String contract,
            @RequestParam(value = "isPending", required = false, defaultValue = "false") Boolean isPending) {
        NebContractToken token = contractTokenService.getByContract(contract);
        if (token == null) {
            return JsonResult.failed("contract does not exist");
        }
        JsonResult result = JsonResult.success();
        long txnCnt;
        if (!isPending) {
            page = Math.min(page, 20);
            txnCnt = nebTransactionService.countContractTransfer(contract);
            List<ContractTransaction> txnList = nebTransactionService.findContractTransactions(contract, page, PAGE_SIZE)
                    .stream()
                    .map(ContractTransaction::fromNebTransaction)
                    .peek(t -> {
                        if (t != null) {
                            t.setTokenName(token.getTokenName());
                        }
                    })
                    .collect(Collectors.toList());
            result.put("txnList", txnList);
            Set<String> associatedAddresses = new HashSet<>(PAGE_SIZE * 2);
            txnList.forEach(t -> {
                if (t.getContractFunction().equals("transfer")) {
                    associatedAddresses.add(t.getContractTo());
                    associatedAddresses.add(t.getFrom());
                }
            });
            EXECUTOR.execute(() -> associatedAddresses.forEach(address -> {
                NebContractTokenBalance tokenBalance = contractTokenBalanceService.getFromRPC(address, contract);
                if (tokenBalance != null) {
                    contractTokenBalanceService.updateAddressBalance(tokenBalance);
                }
            }));
        } else {
            txnCnt = nebTransactionService.countPendingContractTransaction(contract);
            List<NebPendingTransaction> pendingTxnList = nebTransactionService.findPendingContractTransactions(contract, page, PAGE_SIZE);
            List<ContractTransaction> transactions = new LinkedList<>();
            pendingTxnList.forEach(pTxn -> {
                try {
                    ContractTransaction tx = new ContractTransaction();
                    PropertyUtils.copyProperties(tx, pTxn);
                    tx.setBlockHeight(0L);
                    tx.parseContractArgs();
                    tx.setTokenName(token.getTokenName());
                    transactions.add(tx);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
            result.put("txnList", transactions);
        }
        result.put("tokenName", token.getTokenName());
        result.put("txnCnt", txnCnt);
        result.put("currentPage", page);
        result.put("decimal", token.getTokenDecimals());

        long totalPage = txnCnt / PAGE_SIZE + 1;
        result.put("totalPage", !isPending && totalPage > 20 ? 20 : totalPage);
        result.put("maxDisplayCnt", Math.min(txnCnt, 500));
        return result;
    }


    @RequestMapping("/contract/{contract}")
    public JsonResult contractToken(@PathVariable("contract") String contract) {
        NebContractToken token = contractTokenService.getByContract(contract);
        if (token == null) {
            return JsonResult.failed();
        }
        ContractTokenInfo info = new ContractTokenInfo();
        info.setContract(token.getContract());
        info.setTokenName(token.getTokenName());
        info.setTotal(token.getTotal());

        long transactionCount = nebTransactionService.countContractTransfer(contract);
        info.setTransactionCount(transactionCount);

        long holderCount = contractTokenBalanceService.countValidHolders(contract);
        info.setHolderCount(holderCount);

        List<ContractTransaction> transactions = new LinkedList<>();
        long pendingTransactionCount = nebTransactionService.countPendingContractTransaction(contract);
        info.setPendingTransactionCount(pendingTransactionCount);
        if (pendingTransactionCount > 0) {
            List<NebPendingTransaction> pendingTxnList = nebTransactionService.findPendingContractTransactions(contract, 1, PAGE_SIZE);
            pendingTxnList.forEach(pTxn -> {
                try {
                    ContractTransaction tx = new ContractTransaction();
                    PropertyUtils.copyProperties(tx, pTxn);
                    tx.setBlockHeight(0L);
                    tx.parseContractArgs();
                    transactions.add(tx);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
            if (pendingTxnList.size() < PAGE_SIZE) {
                List<NebTransaction> nebTransactions = nebTransactionService.findContractTransactions(contract, 1, PAGE_SIZE - pendingTxnList.size());
                nebTransactions.forEach(t -> {
                    transactions.add(ContractTransaction.fromNebTransaction(t));
                });
            }
        } else {
            List<NebTransaction> nebTransactions = nebTransactionService.findContractTransactions(contract, 1, PAGE_SIZE);
            nebTransactions.forEach(t -> {
                transactions.add(ContractTransaction.fromNebTransaction(t));
            });
        }

        NebMarketCapitalization marketCapitalization = nebMarketCapitalizationService.getNrc20Latest(token.getTokenName());

        JsonResult result = JsonResult.success();
        result.put("contract", info);
        result.put("txList", transactions);
        result.put("decimal", token.getTokenDecimals());
        if (marketCapitalization != null) {
            //price 统一设置4位小数点
            result.put("price", marketCapitalization.getPrice().setScale(4, BigDecimal.ROUND_DOWN));
            result.put("change24h", marketCapitalization.getChange24h());
            result.put("trends", marketCapitalization.getTrends());
        }
        return result;
    }


    @RequestMapping("/address/{hash}")
    public JsonResult address(@PathVariable("hash") String hash) {
        NebAddress address = nebAddressService.getNebAddressByHashRpc(hash);
        if (null == address || hash.equals(BAN_ADDRESS)) {
            return JsonResult.failed();
        }

        JsonResult result = JsonResult.success();

        nebAddressService.updateAddressBalance(hash, address.getCurrentBalance().toPlainString(), address.getNonce());

        List<NebContractToken> allContractTokens = contractTokenService.getAllContractTokens();
        List<ContractTokenBalance> tokenBalanceList = new ArrayList<>(allContractTokens.size());
        for (NebContractToken token : allContractTokens) {
            NebContractTokenBalance balance = contractTokenBalanceService.getFromRPC(hash, token.getContract());
            if (balance == null) {
                continue;
            }
            if (token.getContract().equals(hash)) {
                result.put("tokenName", token.getTokenName());
            }

            EXECUTOR.execute(() -> contractTokenBalanceService.updateAddressBalance(balance));
            ContractTokenBalance tokenBalance = new ContractTokenBalance();
            tokenBalance.setAddress(hash);
            tokenBalance.setContract(token.getContract());
            tokenBalance.setBalance(balance.getBalance());
            tokenBalance.setTokenName(token.getTokenName());
            tokenBalance.setDecimal(token.getTokenDecimals());
            tokenBalanceList.add(tokenBalance);
        }

        long pendingTxCnt = nebTransactionService.countPendingTxnCnt(address.getHash());

        result.put("address", address);
        result.put("tokens", tokenBalanceList);
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
        result.put("decimal", 18);

//        if (address.getUpdatedAt().before(LocalDateTime.now().plusSeconds(-5).toDate())) {
//            GetAccountStateResponse accountState = nebApiServiceWrapper.getAccountState(address.getHash());
//            if (null != accountState && StringUtils.isNotEmpty(accountState.getBalance())) {
//                String balance = accountState.getBalance();
//                address.setCurrentBalance(new BigDecimal(balance));

//            }
//        }

        //contract address code
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
        NebContractToken contractToken = contractTokenService.getByTokenName(q.trim());
        if (contractToken != null) {
            return JsonResult.success("type", "contract").put("q", contractToken.getContract());
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

    private List<TransactionVo> convertNrc20Txn2TxnVo(List<Nrc20TransactionVo> txns) {
        if (CollectionUtils.isEmpty(txns)) {
            return Collections.emptyList();
        }

        Set<String> addressHashSet = Sets.newHashSet();
        txns.forEach(txn -> {
            addressHashSet.add(txn.getFrom());
            addressHashSet.add(txn.getTo());
        });
        Map<String, NebAddress> nebAddressMap = nebAddressService.findAddressMapByAddressHash(Lists.newArrayList(addressHashSet));

        //根据contractAddress去反查tokenName
        List<TransactionVo> txnVoList = new LinkedList<>();
        for (Nrc20TransactionVo txn : txns) {

            NebContractToken token = contractTokenService.getByContract(txn.getContractAddress());
            TransactionVo vo = new TransactionVo()
                    .build(txn)
                    .setBlockVo(txn.getBlockHash(), txn.getBlockHeight())
                    .setFromVo(txn.getFrom(), nebAddressMap.get(txn.getFrom()))
                    .setToVo(txn.getTo(), nebAddressMap.get(txn.getTo()));
            vo.setTokenName(token == null ? "" : token.getTokenName());//若对应的合约地址没有nrc20记录，则tokenName为空
            txnVoList.add(vo);
        }
        return txnVoList;
    }


    @RequestMapping("/address/nrc20/{hash}/{page}")
    public JsonResult nrc20Transactions(@PathVariable("hash") String hash, @PathVariable("page") int page) {
        NebAddress address = nebAddressService.getNebAddressByHashRpc(hash);
        if (null == address || hash.equals(BAN_ADDRESS)) {
            return JsonResult.failed();
        }

        List<Nrc20TransactionVo> txList = nebTransactionService.getNrc20Transactions(hash);
        JsonResult result = JsonResult.success();

        int totalRowNum = txList.size();
        int realPageNo = page;
        int totalPageNum = (totalRowNum - 1) / PAGE_SIZE + 1;

        if (page > totalPageNum) {
            realPageNo = totalPageNum;
        } else if (page < 1) {
            realPageNo = 1;
        }

        int fromIdx = (realPageNo - 1) * PAGE_SIZE;
        int toIdx = realPageNo * PAGE_SIZE + 1;
        if (realPageNo == totalPageNum && totalPageNum * PAGE_SIZE > totalRowNum) {
            toIdx = totalRowNum;
        }

        List<Nrc20TransactionVo> resultList = txList.subList(fromIdx, toIdx);

        result.put("txnCnt", totalRowNum);
        result.put("currentPage", page);
        result.put("txnList", convertNrc20Txn2TxnVo(resultList));
        result.put("totalPage", totalPageNum > 20 ? 20 : totalPageNum);
        result.put("maxDisplayCnt", totalRowNum > 500 ? 500 : totalRowNum);

        return result;
    }

    /**
     * 返回nas主网总成交量，总合约数量，总账户数量
     *
     * @return
     */
    @RequestMapping("/nasinfo")
    public JsonResult getNasMainnetInfo() {

        JsonResult result = JsonResult.success();
        long totalAddressCount = nebAddressService.countTotalAddressCnt();
        long totalContractCount = nebAddressService.getTotalContractCount();
        NasAccount nasAccount = nasAccountService.getLatestNasAccount();

        if (nasAccount == null) {
            return JsonResult.success();
        }

        //从redis中拿，减少查询频率
        String key = "txnCnt";
        Long totalTxnCnt = 0L;
        String txnCnt = redisTemplate.opsForValue().get(key);
        if (txnCnt == null || txnCnt.isEmpty()) {
            totalTxnCnt = nebTransactionService.countTotalTxnCnt();
            redisTemplate.opsForValue().set(key, totalTxnCnt.toString());
            redisTemplate.opsForValue().getOperations().expire(key, 15, TimeUnit.MINUTES);

        } else {
            totalTxnCnt = Long.valueOf(txnCnt);
        }

        NasAccount ninetyDayAccount = nasAccountService.getNasAccountFromNinetyDays();

        long newAddressCount = nasAccount.getAddressCount() - ninetyDayAccount.getAddressCount();
        result.put("totalAddressCount", totalAddressCount);
        result.put("totalContractCount", totalContractCount);
        result.put("txnCnt", totalTxnCnt);
        result.put("newAddressCount", newAddressCount);
        result.put("oldAddressCount", ninetyDayAccount.getAddressCount());

        List<NasAccount> nasAccountList = nasAccountService.getEightWeeks();

        result.put("addressWeekList", nasAccountList);
        return result;
    }


}
