package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebPendingTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * the mapper of table neb_pending_transaction
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-27
 */
@Mapper
public interface NebPendingTransactionMapper {

    Integer add(NebPendingTransaction transaction);

    Integer delete(String hash);

    Integer deleteByTimestamp(@Param("timestamp") Date timestamp);

    long countPendingTxnCntByCondition(@Param("addressHash") String addressHash);

    long countPendingContractTransaction(@Param("contract") String contract);

    NebPendingTransaction getByHash(String hash);

    List<NebPendingTransaction> findPendingTxnByCondition(@Param("addressHash") String addressHash, @Param("offset") int offset, @Param("limit") int limit);

    List<NebPendingTransaction> findPendingContractTransactions(@Param("contract") String contract, @Param("offset") int offset, @Param("limit") int limit);

    List<NebPendingTransaction> findLessThanTimestamp(@Param("timestamp") Date timestamp, @Param("limit") int limit);
}
