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
import io.nebulas.explorer.service.NebAddressService;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebMarketCapitalizationService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Title.
 * <p>
 * Description.
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

    @RequestMapping(value = "/market_cap", method = RequestMethod.GET)
    public JsonResult marketCap() {
        return JsonResult.success(nebMarketCapitalizationService.getLatest());
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET, params = "type=latest")
    public JsonResult latestBlock() {
        List<NebBlock> blkList = nebBlockService.findNebBlockOrderByTimestamp(1, 10);
        return JsonResult.success(convertBlk2Vo(blkList));
    }

    @RequestMapping(value = "/block")
    public JsonResult blocks(@RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        PageIterator<NebBlock> blockPageIterator = nebBlockService.findNebBlockPageIterator(page, PAGE_SIZE);

        PageIterator<BlockVo> blockVoPageIterator = PageIterator.create(blockPageIterator.getPage(), blockPageIterator.getPageSize(), blockPageIterator.getTotalCount());
        blockVoPageIterator.setData(convertBlk2Vo(blockPageIterator.getData()));

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

        JsonResult result = JsonResult.success();
        result.add(block);
        result.put("blkMaxHeight", nebBlockService.getMaxHeight());

        List<NebTransaction> txnList = nebTransactionService.findTxnByBlockHeight(block.getHeight());
        BigDecimal blkGasUsed = BigDecimal.ZERO;
        BigDecimal blkGasLimit = BigDecimal.ZERO;
        for (NebTransaction txn : txnList) {
            blkGasUsed = blkGasUsed.add(StringUtils.isEmpty(txn.getGasUsed()) ? BigDecimal.ZERO : new BigDecimal(txn.getGasUsed()));
            blkGasLimit = blkGasLimit.add(StringUtils.isEmpty(txn.getGasLimit()) ? BigDecimal.ZERO : new BigDecimal(txn.getGasLimit()));
        }
        result.put("txCnt", txnList.size());
        result.put("blkGasUsed", blkGasUsed.toPlainString());
        result.put("blkGasLimit", blkGasLimit.toPlainString());
        result.put("blkGasUsedRate", BigDecimal.ZERO.compareTo(blkGasLimit) < 0 ? blkGasUsed.divide(blkGasLimit, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).toPlainString() : "0");

        NebAddress nebAddress = nebAddressService.getNebAddressByHash(block.getMiner());
        if (null != nebAddress) {
            result.put("miner", nebAddress);
        }
        return result;
    }

    @RequestMapping(value = "/tx", method = RequestMethod.GET, params = "type=latest")
    public JsonResult latestTransaction() {
        return JsonResult.success(nebTransactionService.findTxnOrderById(1, 10));
    }

    @RequestMapping(value = "/txs", method = RequestMethod.GET)
    public JsonResult transactions(@RequestParam(value = "block", required = false) Long block,
                                   @RequestParam(value = "a", required = false) String address,
                                   @RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        if (page > 20) {
            page = 20;
        }

        List<NebTransaction> txnList;
        long txnCnt;
        String type;

        if (null != block) {
            txnCnt = nebTransactionService.countTxnCntByBlockHeight(block);
            txnList = nebTransactionService.findTxnByBlockHeight(block, page, PAGE_SIZE);
            type = "block";
        } else if (StringUtils.isNoneEmpty(address)) {
            txnCnt = nebTransactionService.countTxnCntByFromTo(address);
            txnList = nebTransactionService.findTxnByFromTo(address, page, PAGE_SIZE);
            type = "address";
        } else {
            txnCnt = nebTransactionService.countTxnCnt();
            txnList = nebTransactionService.findTxnOrderById(page, PAGE_SIZE);
            type = "total";
        }

        Set<String> addressHashSet = Sets.newHashSet();
        txnList.forEach(txn -> {
            addressHashSet.add(txn.getFrom());
            addressHashSet.add(txn.getTo());
        });

        Map<String, NebAddress> nebAddressMap = nebAddressService.findAddressMapByAddressHash(Lists.newArrayList(addressHashSet));

        List<TransactionVo> txnVoList = new LinkedList<>();
        for (NebTransaction txn : txnList) {
            TransactionVo vo = new TransactionVo();
            vo.build(txn);
            vo.setBlock(new BlockVo(txn.getBlockHash(), txn.getBlockHeight()));

            NebAddress fromAddress = nebAddressMap.get(txn.getFrom());
            vo.setFrom(fromAddress != null ? (new AddressVo().build(fromAddress)) : new AddressVo(txn.getFrom()));

            NebAddress toAddress = nebAddressMap.get(txn.getTo());
            vo.setTo(toAddress != null ? (new AddressVo().build(toAddress)) : new AddressVo(txn.getTo()));

            txnVoList.add(vo);
        }

        JsonResult result = JsonResult.success();
        result.put("type", type);
        result.put("txnCnt", txnCnt);
        result.put("txnList", txnVoList);
        result.put("currentPage", page);
        result.put("totalPage", txnCnt / PAGE_SIZE + 1);
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
            return JsonResult.failed();
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
        result.put("txn", vo);
        result.put("isPending", isPending);
        return result;
    }

    @RequestMapping("/tx/cnt_static")
    public JsonResult txStatic() {
        return JsonResult.success(nebTransactionService.countTxCntGroupMapByTimestamp(LocalDate.now().plusDays(-15).toDate(), LocalDate.now().toDate()));
    }

    @RequestMapping("/txs_pending")
    public JsonResult txsPending(@RequestParam(value = "a", required = false) String address,
                                 @RequestParam(value = "p", required = false, defaultValue = "1") int page) {
        String type;
        long pendingTxnCnt;
        List<NebPendingTransaction> pendingTxnList;

        if (StringUtils.isEmpty(address)) {
            pendingTxnCnt = nebTransactionService.countPendingTxnCnt();
            pendingTxnList = nebTransactionService.findPendingTxnByFromTo(page, PAGE_SIZE);
            type = "total";
        } else {
            pendingTxnCnt = nebTransactionService.countPendingTxnCntByFromTo(address);
            pendingTxnList = nebTransactionService.findPendingTxnByFromTo(address, page, PAGE_SIZE);
            type = "address";
        }

        JsonResult result = JsonResult.success();
        result.put("type", type);
        result.put("address", address);
        result.put("txnTotalCnt", pendingTxnCnt);
        result.put("currentPage", page);
        result.put("txnTotalPage", pendingTxnCnt / PAGE_SIZE + 1);
        result.put("pendingTxnList", pendingTxnList);
        return result;
    }

    @RequestMapping("/accounts")
    public JsonResult accounts(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        if (page < 1) {
            page = 1;
        }
        if (page > MAX_PAGE) {
            return JsonResult.failed("");
        }

        BigDecimal totalBalance = BigDecimal.ONE;//todo
        List<NebAddress> addressList = nebAddressService.findAddressOrderByBalance((page - 1) * PAGE_SIZE, PAGE_SIZE);
        Map<String, BigDecimal> percentageMap = addressList.stream()
                .collect(Collectors.toMap(NebAddress::getHash, a -> a.getCurrentBalance().divide(totalBalance, 8, BigDecimal.ROUND_DOWN)));

        List<String> addressHashList = addressList.stream().map(NebAddress::getHash).collect(Collectors.toList());
        Map<String, Long> txCntMap = nebTransactionService.countTxnCntByFromTo(addressHashList);

        List<AddressVo> voList = Lists.newLinkedList();
        int i = 1 + (page - 1) * PAGE_SIZE;
        for (NebAddress address : addressList) {
            AddressVo vo = new AddressVo();
            vo.setRank(i);
            vo.setHash(address.getHash());
            vo.setAlias(address.getAlias());
            vo.setBalance(address.getCurrentBalance());
            vo.setPercentage(percentageMap.get(address.getHash()));
            vo.setTxCnt(txCntMap.get(address.getHash()));
            voList.add(vo);

        }

        JsonResult result = JsonResult.success();
        result.put("totalAccountsCnt", nebAddressService.countTotalAddressCnt());
        result.put("totalBalance", totalBalance);
        result.put("page", page);
        result.put("addressList", voList);
        return result;
    }

    @RequestMapping("/address/{hash}")
    public JsonResult address(@PathVariable("hash") String hash, @RequestParam(value = "part", required = false) String part) {
        NebAddress address = nebAddressService.getNebAddressByHash(hash);
        if (null == address) {
            return JsonResult.failed();
        }
        long pendingTxCnt = nebTransactionService.countPendingTxnCntByFromTo(address.getHash());

        JsonResult result = JsonResult.success();
        result.put("address", address);
        result.put("pendingTxCnt", pendingTxCnt);
        result.put("txCnt", nebTransactionService.countTxnCntByFromTo(address.getHash()));
        result.put("minedBlkCnt", nebBlockService.countBlockCntByMiner(address.getHash()));

        if ("mine".equals(part)) {
            List<NebBlock> blkList = nebBlockService.findNebBlockByMiner(address.getHash(), 1, PAGE_SIZE);
            result.put("minedBlkList", convertBlk2Vo(blkList));
        } else {
            List<NebTransaction> txList = Lists.newLinkedList();
            if (pendingTxCnt > 0) {
                List<NebPendingTransaction> pendingTxnList = nebTransactionService.findPendingTxnByFromTo(address.getHash(), 1, PAGE_SIZE);
                pendingTxnList.forEach(pTxn -> {
                    try {
                        NebTransaction tx = new NebTransaction();
                        PropertyUtils.copyProperties(tx, pTxn);
                        tx.setBlockHeight(0L);
                        tx.setBlockHash("");
                        tx.setStatus(0);
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
            result.put("txList", txList);
        }
        return result;
    }

    private List<BlockVo> convertBlk2Vo(List<NebBlock> blks) {
        if (CollectionUtils.isEmpty(blks)) {
            return Collections.emptyList();
        }
        List<Long> blkHeightList = blks.stream().map(NebBlock::getHeight).collect(Collectors.toList());
        List<String> minerHashList = blks.stream().map(NebBlock::getMiner).collect(Collectors.toList());

        Map<Long, BlockSummary> txCntMap = nebTransactionService.countTxnInBlock(blkHeightList);
        Map<String, NebAddress> addressMap = nebAddressService.findAddressMapByAddressHash(minerHashList);

        List<BlockVo> resultList = new LinkedList<>();
        for (NebBlock blk : blks) {
            BlockVo vo = new BlockVo().build(blk);
            NebAddress nebAddress = addressMap.get(blk.getMiner());
            if (null == nebAddress) {
                nebAddress = new NebAddress();
                nebAddress.setHash(blk.getMiner());
            }
            vo.setMiner(nebAddress);

            BlockSummary summary = txCntMap.get(blk.getHeight());
            if (null != summary) {
                vo.setTxnCnt(summary.getTxCnt());
                vo.setGasLimit(summary.getGasLimit());
                vo.setGasUsed(summary.getGasUsed());
                vo.setAvgGasPrice(summary.getAvgGasPrice());
            }
            resultList.add(vo);
        }
        return resultList;
    }
}
