package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    long countTxnCnt();

    long countTxnCntByBlockHeight(Long blockHeight);

    NebTransaction getByHash(String hash);

    List<NebTransaction> findTxnByBlockHeight(Long blockHeight);

    List<NebTransaction> findTxnByBlockHeightWithLimit(@Param("blockHeight") Long blockHeight, @Param("offset") int offset, @Param("limit") int limit);

    List<BlockSummary> countTxnInBlock(@Param("blockHeights") List<Long> blockHeights);

    List<Map<String, String>> countTxnCntMapByFrom(List<String> addressHashes);

    List<Map<String, String>> countTxnCntMapByTo(List<String> addressHashes);

    long countTxnCntByFromTo(String addressHash);

    List<NebTransaction> findTxnByFromTo(@Param("addressHash") String addressHash, @Param("offset") int offset, @Param("limit") int limit);

    List<NebTransaction> findTxnOrderByTimestamp(@Param("offset") int offset, @Param("limit") int limit);

    List<Map<String,Object>> countTxCntGroupByTimestamp(@Param("from") String from,@Param("to") String to);
}
