package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebTransaction;
import org.apache.ibatis.annotations.Mapper;

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

   NebTransaction selectByHash(String hash);
}
