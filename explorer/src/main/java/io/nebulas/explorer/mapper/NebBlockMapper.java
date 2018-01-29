package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    Integer add(NebBlock block);

    long count();

    long countByMiner(String miner);

    Long getMaxHeight();

    NebBlock getByHeight(Long height);

    NebBlock getByHash(String hash);

    List<NebBlock> findOrderByHeightDesc(@Param("offset") int offset, @Param("limit") int limit);

    List<NebBlock> findOrderByTimestamp(@Param("offset") int offset, @Param("limit") int limit);

    List<NebBlock> findByMiner(@Param("miner") String miner, @Param("offset") int offset, @Param("limit") int limit);

}
