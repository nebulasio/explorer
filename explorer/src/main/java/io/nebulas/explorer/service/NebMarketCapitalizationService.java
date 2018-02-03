package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebMarketCapitalization;
import io.nebulas.explorer.mapper.NebMarketCapitalizationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * nebulas market capitalization related operation service
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-30
 */
@Service
@AllArgsConstructor
public class NebMarketCapitalizationService {
    private final NebMarketCapitalizationMapper marketCapitalizationMapper;

    /**
     * query the latest market capitalization
     *
     * @return nebulas market capitalization information
     */
    public NebMarketCapitalization getLatest() {
        return marketCapitalizationMapper.getLatest();
    }
}
