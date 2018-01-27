package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.mapper.NebAddressMapper;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public NebAddress getNebAddressByHash(String hash) {
        return nebAddressMapper.getByHash(hash);
    }

    public long countTotalAddressCnt() {
        return nebAddressMapper.countTotalAddressCnt();
    }

    public List<NebAddress> findAddressOrderByBalance(int offset, int limit) {
        return nebAddressMapper.findAddressOrderByBalance(offset, limit);
    }

    public int addNebAddress(String hash, int type) {
        return nebAddressMapper.add(IdGenerator.getId(), hash, type);
    }
}
