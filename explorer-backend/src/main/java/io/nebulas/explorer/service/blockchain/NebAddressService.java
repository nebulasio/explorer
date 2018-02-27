package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.mapper.NebAddressMapper;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
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

    /**
     * save address information
     *
     * @param hash address hash
     * @param type address type
     * @return saved result
     */
    public boolean addNebAddress(String hash, int type) {
        return nebAddressMapper.add(IdGenerator.getId(), hash, type) > 0;
    }

    /**
     * update address current_balance property
     *
     * @param hash    address hash
     * @param balance current balance
     * @return saved result
     */
    public boolean updateAddressBalance(String hash, String balance) {
        if (StringUtils.isEmpty(hash) || StringUtils.isEmpty(balance)) {
            return false;
        }
        return nebAddressMapper.update(hash, new BigDecimal(balance)) > 0;
    }

    /**
     * According to miner hash query address number
     *
     * @return the number of address
     */
    public long countTotalAddressCnt() {
        return nebAddressMapper.countTotalAddressCnt();
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
