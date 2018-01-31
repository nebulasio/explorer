package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebMarketCapitalization;
import org.apache.ibatis.annotations.Mapper;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-30
 */
@Mapper
public interface NebMarketCapitalizationMapper {

    NebMarketCapitalization getLatest();

}
