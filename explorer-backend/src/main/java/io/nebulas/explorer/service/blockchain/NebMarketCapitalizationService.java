package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebMarketCapitalization;
import io.nebulas.explorer.mapper.NebMarketCapitalizationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
     * save NebMarketCapitalization information
     *
     * @param record NebMarketCapitalization bean
     * @return saved result
     */
    public boolean addMarket(NebMarketCapitalization record) {
        if (null == record) {
            return false;
        }
        return marketCapitalizationMapper.add(record) > 0;
    }

    /**
     * query the latest market capitalization
     *
     * @return nebulas market capitalization information
     */
    public NebMarketCapitalization getLatest() {

        NebMarketCapitalization marketCapitalization = marketCapitalizationMapper.getLatest();
        marketCapitalization.setPrice(marketCapitalization.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
        return marketCapitalization;
    }

    /**
     * query the latest atp market capitalization
     *
     * @return atp market capitalization information
     */
    public NebMarketCapitalization  getAtpLatest(){
        return marketCapitalizationMapper.getAtpLatest();
    }
}
