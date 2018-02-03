package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebPendingTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Integer batchAdd(@Param("transactions") List<NebPendingTransaction> transactions);

    Integer delete(String id);

    long countPendingTxnCntByCondition(@Param("addressHash") String addressHash);

    NebPendingTransaction getByHash(String hash);

    List<NebPendingTransaction> findPendingTxnByCondition(@Param("addressHash") String addressHash, @Param("offset") int offset, @Param("limit") int limit);
}
