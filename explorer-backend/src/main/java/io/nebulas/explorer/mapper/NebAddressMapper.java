package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * the mapper of table neb_address
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Mapper
public interface NebAddressMapper {

    Integer add(@Param("id") String id, @Param("hash") String hash, @Param("type") Integer type);

    Integer update(@Param("hash") String hash, @Param("balance") BigDecimal balance);

    long countTotalAddressCnt();

    NebAddress getByHash(String hash);

    List<NebAddress> findAddressOrderByBalance(@Param("offset") int offset, @Param("limit") int limit);

    List<NebAddress> findAddressMapByAddressHash(List<String> addressHashes);
}
