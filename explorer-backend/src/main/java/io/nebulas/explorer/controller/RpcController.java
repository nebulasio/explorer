package io.nebulas.explorer.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.nebulas.explorer.domain.*;
import io.nebulas.explorer.enums.NebTransactionStatusEnum;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.model.PageIterator;
import io.nebulas.explorer.model.vo.AddressVo;
import io.nebulas.explorer.model.vo.BlockVo;
import io.nebulas.explorer.model.vo.TransactionVo;
import io.nebulas.explorer.service.blockchain.*;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetAccountStateRequest;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetAccountStateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    private final NebulasApiService nebulasApiService;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);

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
        BigDecimal totalBalance = new BigDecimal(20000000 + 0.48 * maxBlockHeight).setScale(8, BigDecimal.ROUND_DOWN).stripTrailingZeros();

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
                if (address.getUpdatedAt().before(LocalDateTime.now().plusMinutes(-30).toDate())) {
                    GetAccountStateResponse accountState = nebulasApiService.getAccountState(new GetAccountStateRequest(address.getHash())).toBlocking().first();
                    String balance = accountState.getBalance();
                    if (StringUtils.isNotEmpty(balance)) {
                        nebAddressService.updateAddressBalance(address.getHash(), balance);
                    }
                }
            }
        });
        return result;
    }

    @RequestMapping("/address/{hash}")
    public JsonResult address(@PathVariable("hash") String hash) {
        NebAddress address = nebAddressService.getNebAddressByHash(hash);
        if (null == address) {
            return JsonResult.failed();
        }
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

        if (address.getUpdatedAt().before(LocalDateTime.now().plusMinutes(-30).toDate())) {
            EXECUTOR.execute(() -> {
                GetAccountStateResponse accountState = nebulasApiService.getAccountState(new GetAccountStateRequest(hash)).toBlocking().first();
                String balance = accountState.getBalance();
                if (StringUtils.isNotEmpty(balance)) {
                    nebAddressService.updateAddressBalance(hash, balance);
                }
            });
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
        }
        NebBlock block = nebBlockService.getNebBlockByHash(q);
        if (null != block) {
            return JsonResult.success("type", "block").put("q", block.getHeight());
        }
        NebAddress address = nebAddressService.getNebAddressByHash(q);
        if (null != address) {
            return JsonResult.success("type", "address").put("q", address.getHash());
        }

        NebTransaction transaction = nebTransactionService.getNebTransactionByHash(q);
        if (null != transaction) {
            return JsonResult.success("type", "tx").put("q", address.getHash());
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
