package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

import io.nebulas.explorer.domain.NaxStage;


@Mapper
@Component
public interface NaxStageMapper {

    @Select("select max(stage) from nax_stage where status=1")
    Integer getLastCompletedStage();

    @Select("select * from nax_stage where stage=#{stage}")
    NaxStage getStage(long stage);

    @Insert("insert into nax_stage (stage, estimateNax) values (#{stage}, #{estimateNax})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int newStage(NaxStage stage);

    @Select("select * from nax_stage where status=0")
    List<NaxStage> getUnfinishedStages();

    @Update("update nax_stage set " +
            "start=#{start}, " +
            "end=#{end}, " +
            "estimateNax=#{estimateNax}, " +
            "actualNax=#{actualNax}, " +
            "destroyedNax=#{destroyedNax}, " +
            "totalNax=#{totalNax}, " +
            "pledgeNas=#{pledgeNas}, " +
            "totalNas=#{totalNas}, " +
            "status=#{status} " +
            "where stage=#{stage}")
    void update(NaxStage stage);
}
