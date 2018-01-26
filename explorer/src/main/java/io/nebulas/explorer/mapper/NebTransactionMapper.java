package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

   NebTransaction getByHash(String hash);

   Integer batchAddNebTransaction(@Param("transactions") List<NebTransaction> transactions);

   List<NebTransaction> getByBlockHeight(Long blockHeight);

   Integer addNebTransaction(NebTransaction transaction);

}
