package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebMarketCapitalization;
import org.apache.ibatis.annotations.Mapper;

/**
 * the mapper of table neb_market_capitalization
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-30
 */
@Mapper
public interface NebMarketCapitalizationMapper {

    Integer add(NebMarketCapitalization nebMarketCapitalization);

    NebMarketCapitalization getLatest();

}
