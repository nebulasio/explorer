package io.nebulas.explorer.service;

import io.nebulas.explorer.repository.NebAddressRepository;
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
   private final NebAddressRepository nebAddressRepository;


}
