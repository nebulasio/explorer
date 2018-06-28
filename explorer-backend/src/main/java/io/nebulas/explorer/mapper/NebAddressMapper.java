package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.model.vo.AddrTypeVo;
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

//    Integer add(@Param("hash") String hash, @Param("type") Integer type);

    Integer addAddress(@Param("hash") String hash, @Param("nonce") String nonce, @Param("type") Integer type, @Param("balance") BigDecimal balance);

    Integer update(@Param("hash") String hash, @Param("balance") BigDecimal balance, @Param("nonce") String nonce);

    long countTotalAddressCnt();

    NebAddress getByHash(String hash);

    List<NebAddress> findAddressOrderByBalance(@Param("offset") int offset, @Param("limit") int limit);

    List<NebAddress> findAddressMapByAddressHash(List<String> addressHashes);

    List<AddrTypeVo> countAccountGroupByType();
}
