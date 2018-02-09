package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * the mapper of table neb_block
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Mapper
public interface NebBlockMapper {

    Integer add(NebBlock block);

    Integer updateBlockIrreversible(Long height);

    long count();

    long countByMiner(String miner);

    Long getMaxHeight();

    NebBlock getByHeight(Long height);

    NebBlock getByHash(String hash);

    List<NebBlock> findOrderByHeightDesc(@Param("offset") int offset, @Param("limit") int limit);

    List<NebBlock> findByMiner(@Param("miner") String miner, @Param("offset") int offset, @Param("limit") int limit);

    List<NebBlock> findNebBlockBetweenHeight(@Param("fromHeight") Long fromHeight, @Param("toHeight") Long toHeight);
}
