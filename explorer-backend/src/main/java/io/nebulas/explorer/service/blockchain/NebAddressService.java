package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.mapper.NebAddressMapper;
import io.nebulas.explorer.model.vo.AddrTypeVo;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetAccountStateResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * nebulas address related operation service
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Service
@AllArgsConstructor
public class NebAddressService {
    private final NebAddressMapper nebAddressMapper;
    private final NebApiServiceWrapper nebApiServiceWrapper;

//    /**
//     * save address information
//     *
//     * @param hash address hash
//     * @param type address type
//     * @return saved result
//     */
//    public boolean addNebAddress(String hash, int type) {
//        return nebAddressMapper.add(hash, type) > 0;
//    }

    /**
     * save address information
     *
     * @param address neb address entity
     * @return saved result
     */
    public boolean addNebAddress(NebAddress address) {
        return nebAddressMapper.addAddress(address.getHash(), address.getNonce(), address.getType(), address.getCurrentBalance()) > 0;
    }

    /**
     * update address current_balance property
     *
     * @param hash    address hash
     * @param balance current balance
     * @param nonce   current tx count
     * @return saved result
     */
    public boolean updateAddressBalance(String hash, String balance, String nonce) {
        if (StringUtils.isEmpty(hash) || StringUtils.isEmpty(balance) || StringUtils.isEmpty(nonce)) {
            return false;
        }
        return nebAddressMapper.update(hash, new BigDecimal(balance), nonce) > 0;
    }

    /**
     * According to miner hash query address number
     *
     * @return the number of address
     */
    public long countTotalAddressCnt() {
        return nebAddressMapper.countTotalAddressCnt();
    }

    public Map<NebAddressTypeEnum, Long> countAccountGroupByType() {
        List<AddrTypeVo> voList = nebAddressMapper.countAccountGroupByType();
        if (CollectionUtils.isEmpty(voList)) {
            return MapUtils.EMPTY_MAP;
        }
        return voList.stream().collect(Collectors.toMap(it -> NebAddressTypeEnum.of(it.getType()), AddrTypeVo::getAmount));
    }

    /**
     * According to address hash query address information
     *
     * @param hash address hash
     * @return address information
     */
    public NebAddress getNebAddressByHash(String hash) {
        return nebAddressMapper.getByHash(hash);
    }

    /**
     * According to address hash query address information, but through go-nebulas rpc interface
     *
     * @param hash address hash
     * @return address information
     */
    public NebAddress getNebAddressByHashRpc(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        GetAccountStateResponse response = nebApiServiceWrapper.getAccountState(hash);
        if (null == response) {
            return null;
        }
        NebAddress nebAddress = new NebAddress();
        nebAddress.setHash(hash);
        nebAddress.setNonce(response.getNonce());
        nebAddress.setCurrentBalance(new BigDecimal(response.getBalance()));
        nebAddress.setType(87 == response.getType() ? NebAddressTypeEnum.NORMAL.getValue() : NebAddressTypeEnum.CONTRACT.getValue());
        return nebAddress;
    }

    /**
     * query address information
     *
     * @param page     current page
     * @param pageSize number of information per page
     * @return address list
     */
    public List<NebAddress> findAddressOrderByBalance(int page, int pageSize) {
        return nebAddressMapper.findAddressOrderByBalance((page - 1) * pageSize, pageSize);
    }

    /**
     * batch query address information
     *
     * @param addressHashes address hash list
     * @return address information
     */
    public Map<String, NebAddress> findAddressMapByAddressHash(List<String> addressHashes) {
        if (CollectionUtils.isEmpty(addressHashes)) {
            return Collections.emptyMap();
        }
        List<NebAddress> addressList = nebAddressMapper.findAddressMapByAddressHash(addressHashes);
        return addressList.stream().collect(Collectors.toMap(NebAddress::getHash, v -> v));
    }

}
