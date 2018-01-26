package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Mapper
public interface NebTransactionMapper {

    Integer addNebTransaction(NebTransaction transaction);

    Integer batchAddNebTransaction(@Param("transactions") List<NebTransaction> transactions);

    NebTransaction getByHash(String hash);

    List<NebTransaction> getByBlockHeight(Long blockHeight);

    int countNormalTxCntByBlockHeight(Long blockHeight);

    List<NebTransaction> findNormalTxInBlockByBlockHeight(Long blockHeight);

    List<BlockSummary> countNormalTxInBlock(@Param("blockHeights") List<Long> blockHeights);

    List<Map<String, String>> countNormalTxnCntMapByFrom(List<String> addressHashes);

    List<Map<String, String>> countNormalTxnCntMapByTo(List<String> addressHashes);

    int countNormalTxnCntByFromTo(String addressHash);

    List<NebTransaction> findNormalTxnByFromTo(@Param("addressHash") String addressHash, @Param("offset") int offset, @Param("limit") int limit);
}
