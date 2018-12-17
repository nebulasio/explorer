package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebContractToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NebContractTokenMapper {

    NebContractToken getByContract(String contract);

    List<NebContractToken> getAllContractTokens();

    NebContractToken getByTokenName(String tokenName);
}
