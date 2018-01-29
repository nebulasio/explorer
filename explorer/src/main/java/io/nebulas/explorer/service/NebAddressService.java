package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.mapper.NebAddressMapper;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

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

    public int addNebAddress(String hash, int type) {
        return nebAddressMapper.add(IdGenerator.getId(), hash, type);
    }

    public long countTotalAddressCnt() {
        return nebAddressMapper.countTotalAddressCnt();
    }

    public NebAddress getNebAddressByHash(String hash) {
        return nebAddressMapper.getByHash(hash);
    }

    public List<NebAddress> findAddressOrderByBalance(int offset, int limit) {
        return nebAddressMapper.findAddressOrderByBalance(offset, limit);
    }

    public Map<String, NebAddress> findAddressMapByAddressHash(List<String> addressHashes) {
        if (CollectionUtils.isEmpty(addressHashes)) {
            return Collections.emptyMap();
        }
        List<NebAddress> addressList = nebAddressMapper.findAddressMapByAddressHash(addressHashes);
        return addressList.stream().collect(Collectors.toMap(NebAddress::getHash, v -> v));
    }

}
