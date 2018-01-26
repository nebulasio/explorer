package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.mapper.NebAddressMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
