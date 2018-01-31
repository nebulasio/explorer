package io.nebulas.explorer.service;

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

import java.util.*;
import java.util.stream.Collectors;

/**
 * nebulas transaction related operation service
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Service
@AllArgsConstructor
public class NebTransactionService {
    private final NebTransactionMapper nebTransactionMapper;
    private final NebPendingTransactionMapper nebPendingTransactionMapper;

    /**
     * save single nebulas transaction entity
     *
     * @param transaction
     * @return
     */
    public Integer addNebTransaction(NebTransaction transaction) {
        return nebTransactionMapper.addNebTransaction(transaction);
    }

    /**
     * batch add nebulas transaction entity
     *
     * @param transactions
     * @return
     */
    public Integer batchAddNebTransaction(List<NebTransaction> transactions) {
        return nebTransactionMapper.batchAddNebTransaction(transactions);
    }

    /**
     * save single nebulas pending transaction entity
     *
     * @param transaction pending transaction
     * @return
     */
    public Integer addNebPendingTransaction(NebPendingTransaction transaction) {
        return nebPendingTransactionMapper.add(transaction);
    }

    /**
     * batch add nebluas pending transaction entity
     *
     * @param transactions pending transaction
     * @return
     */
    public Integer batchAddNebPendingTransaction(List<NebPendingTransaction> transactions) {
        return nebPendingTransactionMapper.batchAdd(transactions);
    }

    public long countTxnCnt() {
        return nebTransactionMapper.countTxnCnt();
    }

    public long countTxnCntByBlockHeight(long blockHeight) {
        return nebTransactionMapper.countTxnCntByBlockHeight(blockHeight);
    }

    public long countTxnCntByFromTo(String addressHash) {
        if (StringUtils.isEmpty(addressHash)) {
            return 0L;
        }
        return nebTransactionMapper.countTxnCntByFromTo(addressHash);
    }

    public long countPendingTxnCnt() {
        return nebPendingTransactionMapper.countPendingTxnCnt();
    }

    public long countPendingTxnCntByFromTo(String addressHash) {
        if (StringUtils.isEmpty(addressHash)) {
            return 0L;
        }
        return nebPendingTransactionMapper.countPendingTxnCntByFromTo(addressHash);
    }

    /**
     * get nebulas transaction by hash
     *
     * @param hash
     * @return
     */
    public NebTransaction getNebTransactionByHash(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        return nebTransactionMapper.getByHash(hash);
    }

    public NebPendingTransaction getNebPendingTransactionByHash(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        return nebPendingTransactionMapper.getByHash(hash);
    }

    public int deleteNebPendingTransaction(String id) {
        return nebPendingTransactionMapper.delete(id);
    }

    public List<NebTransaction> findTxnByBlockHeight(Long blockHeight) {
        return nebTransactionMapper.findTxnByBlockHeight(blockHeight);
    }

    public List<NebTransaction> findTxnByBlockHeight(Long blockHeight, int page, int pageSize) {
        return nebTransactionMapper.findTxnByBlockHeightWithLimit(blockHeight, (page - 1) * pageSize, pageSize);
    }

    public Map<Long, BlockSummary> countTxnInBlock(List<Long> blockHeights) {
        if (CollectionUtils.isEmpty(blockHeights)) {
            return Collections.emptyMap();
        }
        List<BlockSummary> blockSummaryList = nebTransactionMapper.countTxnInBlock(blockHeights);
        return blockSummaryList.stream().collect(Collectors.toMap(BlockSummary::getBlockHeight, summary -> summary));
    }

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

    public List<NebPendingTransaction> findPendingTxnByFromTo(int page, int pageSize) {
        return nebPendingTransactionMapper.findPendingTxnByFromTo(null, (page - 1) * pageSize, pageSize);
    }

    public List<NebPendingTransaction> findPendingTxnByFromTo(String addressHash, int page, int pageSize) {
        if (StringUtils.isEmpty(addressHash)) {
            return Collections.emptyList();
        }
        return nebPendingTransactionMapper.findPendingTxnByFromTo(addressHash, (page - 1) * pageSize, pageSize);
    }

    public List<NebTransaction> findTxnByFromTo(String addressHash, int page, int pageSize) {
        if (StringUtils.isEmpty(addressHash)) {
            return Collections.emptyList();
        }
        return nebTransactionMapper.findTxnByFromTo(addressHash, (page - 1) * pageSize, pageSize);
    }

    public List<NebTransaction> findTxnOrderById(int page, int pageSize) {
        return nebTransactionMapper.findTxnOrderById((page - 1) * pageSize, pageSize);
    }

    public List<Long> countTxCntGroupByTimestamp(Date from, Date to) {
        List<Map<String, Object>> txCntResultList = nebTransactionMapper.countTxCntGroupByTimestamp(parseDate2Str(from), parseDate2Str(to));
        if (CollectionUtils.isEmpty(txCntResultList)) {
            return Collections.emptyList();
        }
        Map<String, Long> txCntMap = txCntResultList.stream()
                .collect(Collectors.toMap(k -> k.get("ts").toString(), v -> Long.valueOf(v.get("cnt").toString())));

        List<Long> txnCntList = new LinkedList<>();
        LocalDate fromLocalDate = LocalDate.fromDateFields(from);
        LocalDate toLocalDate = LocalDate.fromDateFields(to);
        while (fromLocalDate.isBefore(toLocalDate)) {
            String dateStr = fromLocalDate.toString("yyyy-MM-dd");
            Long cnt = txCntMap.get(dateStr);
            txnCntList.add(null != cnt ? cnt : 0);
            fromLocalDate = fromLocalDate.plusDays(1);
        }
        return txnCntList;
    }

    public Map<String, Long> countTxCntGroupMapByTimestamp(Date from, Date to) {
        List<Map<String, Object>> txCntResultList = nebTransactionMapper.countTxCntGroupByTimestamp(parseDate2Str(from), parseDate2Str(to));
        if (CollectionUtils.isEmpty(txCntResultList)) {
            return Collections.emptyMap();
        }
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

    private String parseDate2Str(Date date) {
        return LocalDate.fromDateFields(date).toString("yyyy-MM-dd");
    }
}
