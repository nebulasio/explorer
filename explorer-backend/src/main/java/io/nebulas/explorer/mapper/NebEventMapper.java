package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NebEvent;
import io.nebulas.explorer.domain.NebEventCondition;

import java.util.List;

import io.nebulas.explorer.service.thirdpart.nebulas.bean.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NebEventMapper {
    int countByCondition(NebEventCondition example);

    int deleteByCondition(NebEventCondition example);

    int deleteById(Long id);

    int insert(NebEvent record);

    int insertSelective(NebEvent record);

    List<NebEvent> selectByCondition(NebEventCondition example);

    List<Event> selectByHash(@Param("hash") String hash);

    NebEvent selectById(Long id);

    int updateByConditionSelective(@Param("record") NebEvent record, @Param("example") NebEventCondition example);

    int updateByCondition(@Param("record") NebEvent record, @Param("example") NebEventCondition example);

    int updateByIdSelective(NebEvent record);

    int updateById(NebEvent record);
}