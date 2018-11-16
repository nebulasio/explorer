package io.nebulas.explorer.service.blockchain;

import com.google.common.collect.Maps;
import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebPendingTransaction;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.mapper.NebPendingTransactionMapper;
import io.nebulas.explorer.mapper.NebTransactionMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Nebulas transaction related operation service
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@AllArgsConstructor
@Service
public class NebTransactionService {
    private final NebTransactionMapper nebTransactionMapper;
    private final NebPendingTransactionMapper nebPendingTransactionMapper;

    //数据库很慢，比RPC还慢，暂时弃用
    public boolean hasContractTransfer(String address, String contract){
        if (StringUtils.isEmpty(address) || StringUtils.isEmpty(contract)) {
            return false;
        }
        return nebTransactionMapper.countTxnCntByFromAndTo(address, contract)>0;
    }

    /**
     * save transaction information
     *
     * @param transaction transaction bean
     * @return saved result
     */
    public boolean addNebTransaction(NebTransaction transaction) {
        return nebTransactionMapper.addNebTransaction(transaction) > 0;
    }

    public Integer deleteNebTransactionByBlkHeight(long blkHeight) {
        return nebTransactionMapper.deleteByBlkHeight(blkHeight);
    }

    /**
     * save pending transaction information
     *
     * @param transaction pending transaction
     * @return saved result
     */
    public boolean addNebPendingTransaction(NebPendingTransaction transaction) {
        return nebPendingTransactionMapper.add(transaction) > 0;
    }

    /**
     * delete pending transaction
     *
     * @param hash pending transaction hash
     * @return deleted result
     */
    public boolean deleteNebPendingTransaction(String hash) {
        return nebPendingTransactionMapper.delete(hash) > 0;
    }

    /**
     * delete pending transaction
     *
     * @return deleted result
     */
    public boolean deleteNebPendingTransactionByTimestamp(Date ts) {
        return nebPendingTransactionMapper.deleteByTimestamp(ts) > 0;
    }

    /**
     * query transaction total count
     *
     * @param blockHeight block height
     * @param addressHash address hash
     * @return the number of transaction
     */
    public long countTxnCnt(Long blockHeight, String addressHash) {
        return nebTransactionMapper.countTxnCntByCondition(blockHeight, addressHash);
    }

    public long countContractTransfer(String contract) {
        return nebTransactionMapper.countContractTransfer(contract);
    }

    /**
     * according address hash to query transaction total count
     *
     * @param addressHash address hash
     * @return the number of transaction
     */
    public long countTxnCntByFromTo(String addressHash) {
        if (StringUtils.isEmpty(addressHash)) {
            return 0L;
        }
        return nebTransactionMapper.countTxnCntByFromTo(addressHash);
    }

    /**
     * according address hash to query pending transaction total count
     *
     * @param addressHash address hash
     * @return the number of transaction
     */
    public long countPendingTxnCnt(String addressHash) {
        return nebPendingTransactionMapper.countPendingTxnCntByCondition(addressHash);
    }

    public long countPendingContractTransaction(String contract) {
        if (StringUtils.isEmpty(contract)) {
            return 0L;
        }
        return nebPendingTransactionMapper.countPendingContractTransaction(contract);
    }

    public long countTxnCntByBlockHeight(Long blockHeight) {
        return nebTransactionMapper.countTxnCntByBlockHeight(blockHeight);
    }

    /**
     * According to transaction hash query transaction information
     *
     * @param hash transaction hash
     * @return transaction information
     */
    public NebTransaction getNebTransactionByHash(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        return nebTransactionMapper.getByHash(hash);
    }

    /**
     * According to contract address query transaction information
     * ps: return null when contract address is empty
     *
     * @param contractAddress contractAddress hash
     * @return transaction information
     */
    public NebTransaction getNebTransactionByContractAddress(String contractAddress) {
        if (StringUtils.isEmpty(contractAddress)) {
            return null;
        }
        return nebTransactionMapper.getByContractAddress(contractAddress);
    }

    /**
     * According to pending transaction hash query pending transaction information
     *
     * @param hash pending transaction hash
     * @return pending transaction information
     */
    public NebPendingTransaction getNebPendingTransactionByHash(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        return nebPendingTransactionMapper.getByHash(hash);
    }

    public BlockSummary getBlockSummaryByBlockHeight(long blockHeight) {
        return covertBlockSummary(blockHeight, nebTransactionMapper.findTxnByBlockHeight(blockHeight));
    }

    /**
     * According to block height query transaction information
     *
     * @param blockHeight block height
     * @return transaction list
     */
    public List<NebTransaction> findTxnByBlockHeight(Long blockHeight) {
        return nebTransactionMapper.findTxnByBlockHeight(blockHeight);
    }

    /**
     * According to condition query transaction information
     *
     * @param blockHeight block height
     * @param addressHash address hash
     * @param page        current page
     * @param pageSize    number of information per page
     * @return transaction list
     */
    public List<NebTransaction> findTxnByCondition(Long blockHeight, String addressHash, int page, int pageSize) {
        return nebTransactionMapper.findTxnByCondition(blockHeight, addressHash, (page - 1) * pageSize, pageSize);
    }

    /**
     * According to condition query pending transaction information
     *
     * @param addressHash address hash
     * @param page        current page
     * @param pageSize    number of information per page
     * @return pending transaction list
     */
    public List<NebPendingTransaction> findPendingTxnByCondition(String addressHash, int page, int pageSize) {
        return nebPendingTransactionMapper.findPendingTxnByCondition(addressHash, (page - 1) * pageSize, pageSize);
    }

    public List<NebPendingTransaction> findLessThanTimestamp(Date timestamp, int limit) {
        return nebPendingTransactionMapper.findLessThanTimestamp(timestamp, limit);
    }

    public List<NebPendingTransaction> findPendingContractTransactions(String contract, int page, int pageSize){
        if (StringUtils.isEmpty(contract)){
            return Collections.emptyList();
        }
        return nebPendingTransactionMapper.findPendingContractTransactions(contract, (page - 1) * pageSize, pageSize);
    }

    public List<NebTransaction> findContractTransactions(String contract, int page, int pageSize){
        if (StringUtils.isEmpty(contract)){
            return Collections.emptyList();
        }
        return nebTransactionMapper.findContractTransactions(contract, (page - 1) * pageSize, pageSize);
    }

    /**
     * query transaction information list
     *
     * @param addressHash address hash
     * @param page        current page
     * @param pageSize    number of information per page
     * @return transaction list
     */
    public List<NebTransaction> findTxnByFromTo(String addressHash, int page, int pageSize) {
        if (StringUtils.isEmpty(addressHash)) {
            return Collections.emptyList();
        }
        return nebTransactionMapper.findTxnByFromTo(addressHash, (page - 1) * pageSize, pageSize);
    }

    /**
     * query transaction information list
     *
     * @param page     current page
     * @param pageSize number of information per page
     * @return transaction list
     */
    public List<NebTransaction> findTxnOrderById(int page, int pageSize) {
        return nebTransactionMapper.findTxnOrderById((page - 1) * pageSize, pageSize);
    }

    /**
     * calculate transaction count between date
     *
     * @param from begin date
     * @param to   end date
     * @return transaction count map
     */
    public Map<String, Long> countTxCntGroupMapByTimestamp(Date from, Date to) {
        List<Map<String, Object>> txCntResultList = nebTransactionMapper.countTxCntGroupByTimestamp(parseDate2Str(from), parseDate2Str(to));
        Map<String, Long> txCntMap = txCntResultList.stream()
                .collect(Collectors.toMap(k -> k.get("ts").toString(), v -> Long.valueOf(v.get("cnt").toString())));

        Map<String, Long> resultMap = Maps.newLinkedHashMap();
        LocalDate fromLocalDate = LocalDate.fromDateFields(from);
        LocalDate toLocalDate = LocalDate.fromDateFields(to);
        while (fromLocalDate.isBefore(toLocalDate)) {
            String dateStr = fromLocalDate.toString("yyyy-MM-dd");
            Long cnt = txCntMap.get(dateStr);
            resultMap.put(dateStr, (null != cnt ? cnt : 0));
            fromLocalDate = fromLocalDate.plusDays(1);
        }
        return resultMap;
    }

    /**
     * According to block height calculate transaction information
     *
     * @param blockHeights block height list
     * @return summary map
     */
    public Map<Long, BlockSummary> calculateTxnSummaryInBlock(List<Long> blockHeights, boolean isWithGas) {
        if (CollectionUtils.isEmpty(blockHeights)) {
            return Collections.emptyMap();
        }

        List<NebTransaction> txnList = nebTransactionMapper.findByBlockHeights(blockHeights);
        Map<Long, List<NebTransaction>> txnMap = txnList.stream().collect(Collectors.groupingBy(NebTransaction::getBlockHeight));

        Map<Long, BlockSummary> summaryMap = Maps.newHashMap();
        if (isWithGas) {
            blockHeights.forEach(height -> summaryMap.put(height, covertBlockSummary(height, txnMap.get(height))));
        } else {
            txnMap.forEach((k, v) -> summaryMap.put(k, new BlockSummary(k, v.size())));
            blockHeights.forEach(height -> {
                List<NebTransaction> txList = txnMap.get(height);
                summaryMap.put(height, new BlockSummary(height, CollectionUtils.isNotEmpty(txList) ? txList.size() : 0L));
            });
        }
        return summaryMap;
    }

    private BlockSummary covertBlockSummary(long blockHeight, List<NebTransaction> txs) {
        if (CollectionUtils.isEmpty(txs)) {
            return new BlockSummary(blockHeight, 0, null, 0L, null);
        } else {
            BigDecimal blkGasRewardBd = BigDecimal.ZERO;
            BigDecimal blkTotalGasPriceBd = BigDecimal.ZERO;
            BigDecimal blkTotalGasLimitBd = BigDecimal.ZERO;

            for (NebTransaction tx : txs) {
                blkGasRewardBd = blkGasRewardBd.add(tx.getGasUsedBd().multiply(tx.getGasPriceBd()));
                blkTotalGasPriceBd = blkTotalGasPriceBd.add(tx.getGasPriceBd());
                blkTotalGasLimitBd = blkTotalGasLimitBd.add(tx.getGasLimitBd());
            }

            String gasReward = blkGasRewardBd.toPlainString();
            long gasLimit = blkTotalGasLimitBd.longValue();
            String avgGasPrice = blkTotalGasPriceBd.divide(new BigDecimal(txs.size()), 2, BigDecimal.ROUND_DOWN).toPlainString();
            return new BlockSummary(blockHeight, txs.size(), gasReward, gasLimit, avgGasPrice);
        }
    }

    /**
     * According to address hash calculate transaction count
     *
     * @param addressHashes address hash
     * @return transaction count map
     */
    public Map<String, Long> countTxnCntByFromTo(List<String> addressHashes) {
        if (CollectionUtils.isEmpty(addressHashes)) {
            return Collections.emptyMap();
        }
        List<Map<String, String>> fromList = nebTransactionMapper.countTxnCntMapByFrom(addressHashes);
        Map<String, Long> fromCntMap = fromList.stream()
                .collect(Collectors.toMap(k -> k.get("from"), v -> Long.parseLong(String.valueOf(v.get("cnt")))));

        List<Map<String, String>> toList = nebTransactionMapper.countTxnCntMapByTo(addressHashes);
        Map<String, Long> toCntMap = toList.stream()
                .collect(Collectors.toMap(k -> k.get("to"), v -> Long.parseLong(String.valueOf(v.get("cnt")))));

        return addressHashes.stream().collect(Collectors.toMap(k -> k, v -> {
            Long fromCnt = fromCntMap.get(v);
            Long toCnt = toCntMap.get(v);
            return (fromCnt == null ? 0 : fromCnt) + (toCnt == null ? 0 : toCnt);
        }));

    }

    private String parseDate2Str(Date date) {
        return LocalDate.fromDateFields(date).toString("yyyy-MM-dd");
    }
}
