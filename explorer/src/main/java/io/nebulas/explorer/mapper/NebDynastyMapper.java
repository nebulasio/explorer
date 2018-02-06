package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebDynasty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * the mapper of table neb_dynasty
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-02-03
 */
@Mapper
public interface NebDynastyMapper {

    Integer batchAdd(@Param("dynasties") List<NebDynasty> dynasties);

    List<NebDynasty> findByBlockHeight(Long blockHeight);
}
