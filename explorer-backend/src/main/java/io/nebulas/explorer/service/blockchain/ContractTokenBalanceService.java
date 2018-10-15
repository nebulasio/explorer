package io.nebulas.explorer.service.blockchain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.nebulas.explorer.domain.NebContractTokenBalance;
import io.nebulas.explorer.mapper.NebContractTokenBalanceMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.CallContractResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContractTokenBalanceService {

    private final NebApiServiceWrapper nebApiServiceWrapper;

    private final NebContractTokenBalanceMapper nebContractTokenBalanceMapper;

    public boolean addAddressBalance(NebContractTokenBalance addressBalance) {
        return addressBalance != null &&
                nebContractTokenBalanceMapper.insert(
                        addressBalance.getAddress(),
                        addressBalance.getContract(),
                        addressBalance.getBalance(),
                        addressBalance.getCreatedTime(),
                        addressBalance.getUpdatedTime()
                ) > 0;
    }

    public boolean updateAddressBalance(NebContractTokenBalance addressBalance) {
        if (addressBalance==null){
            return false;
        }
        NebContractTokenBalance fromDb = getByAddressAndContract(addressBalance.getAddress(), addressBalance.getContract());
        if (fromDb != null) {
            addressBalance.setCreatedTime(fromDb.getCreatedTime());
            return nebContractTokenBalanceMapper.update(
                    addressBalance.getAddress(),
                    addressBalance.getContract(),
                    addressBalance.getBalance(),
                    addressBalance.getUpdatedTime()
            ) > 0;
        } else {
            return addAddressBalance(addressBalance);
        }
    }

    public NebContractTokenBalance getByAddressAndContract(String address, String contract) {
        return nebContractTokenBalanceMapper.getByAddressAndContract(address, contract);
    }

    public List<NebContractTokenBalance> getValidAddressesByContractOrderByBalance(String contract, int offset, int limit) {
        return nebContractTokenBalanceMapper.getValidAddressesByContractOrderByBalance(contract, offset, limit);
    }

    public NebContractTokenBalance getFromRPC(String address, String contract) {
        CallContractResponse response = nebApiServiceWrapper.callContractBalance(address, contract);
        if (response == null) {
            return null;
        }
        String balance = response.getResult().trim().replace("\"", "");
        NebContractTokenBalance addressBalance = new NebContractTokenBalance();
        addressBalance.setAddress(address);
        Date now = new Date();
        return NebContractTokenBalance.builder()
                .address(address)
                .contract(contract)
                .balance(new BigDecimal(balance))
                .createdTime(now)
                .updatedTime(now)
                .build();
    }

    public long countValidHolders(String contract){
        return nebContractTokenBalanceMapper.countValidHolders(contract);
    }
}
