package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebAddress;
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
public interface NebAddressMapper {

    NebAddress getByHash(String hash);

    long countTotalAddressCnt();

    List<NebAddress> findAddressOrderByBalance(@Param("offset") int offset, @Param("limit") int limit);

    Integer add(@Param("id") String id, @Param("hash") String hash, @Param("type") Integer type);
}
