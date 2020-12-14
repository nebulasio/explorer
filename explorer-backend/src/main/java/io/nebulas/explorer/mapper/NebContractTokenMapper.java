package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebContractToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface NebContractTokenMapper {

    NebContractToken getByContract(String contract);

    List<NebContractToken> getAllContractTokens();

    NebContractToken getByTokenName(String tokenName);

    Integer updateTotal(@Param("contract") String contract,
                   @Param("total") BigDecimal total);
}
