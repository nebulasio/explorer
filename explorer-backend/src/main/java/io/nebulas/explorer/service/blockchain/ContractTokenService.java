package io.nebulas.explorer.service.blockchain;


import io.nebulas.explorer.domain.NebContractToken;
import io.nebulas.explorer.mapper.NebContractTokenMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractTokenService {

    private final NebContractTokenMapper nebContractTokenMapper;

    public List<NebContractToken> getAllContractTokens(){
        return nebContractTokenMapper.getAllContractTokens();
    }

    public NebContractToken getByContract(String contract){
        return nebContractTokenMapper.getByContract(contract);
    }

    public  NebContractToken getByTokenName(String tokenName){
        return nebContractTokenMapper.getByTokenName(tokenName);
    }


}
