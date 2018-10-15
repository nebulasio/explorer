package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebContractTokenBalance;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface NebContractTokenBalanceMapper {

    NebContractTokenBalance getByAddressAndContract(@Param("address") String address, @Param("contract") String contract);

    List<NebContractTokenBalance> getValidAddressesByContractOrderByBalance(@Param("contract") String contract, @Param("offset") int offset, @Param("limit") int limit);

    Integer insert(@Param("address") String address,
                   @Param("contract") String contract,
                   @Param("balance") BigDecimal balance,
                   @Param("createdTime") Date createdTime,
                   @Param("updatedTime") Date updatedTime);

    Integer update(@Param("address") String address,
                   @Param("contract") String contract,
                   @Param("balance") BigDecimal balance,
                   @Param("updatedTime") Date updatedTime);

    long countValidHolders(String contract);
}
