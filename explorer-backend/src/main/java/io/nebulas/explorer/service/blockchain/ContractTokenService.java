package io.nebulas.explorer.service.blockchain;


import io.nebulas.explorer.domain.NebContractToken;
import io.nebulas.explorer.mapper.NebContractTokenMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.CallContractResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ContractTokenService {

    private final NebApiServiceWrapper nebApiServiceWrapper;

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

    public boolean updateTotalSupply(String contract) {
        if (contract==null){
            return false;
        }

        CallContractResponse response = nebApiServiceWrapper.callContractTotalSupply(contract, contract);
        if (response == null || response.getResult()==null) {
            return false;
        }
        String totalSupply = response.getResult().trim().replace("\"", "");
        NebContractToken token = this.getByContract(contract);
        if (token != null) {
            token.setTotal(new BigDecimal(totalSupply));
            return nebContractTokenMapper.updateTotal(
                    token.getContract(),
                    token.getTotal()
            ) > 0;
        } else {
            return false;
        }
    }


}
