package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebBlock;
import org.apache.ibatis.annotations.Mapper;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Mapper
public interface NebBlockMapper {

    Integer save(NebBlock block);

    NebBlock selectByHeight(Long height);


}
