package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NaxPeriod;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NaxPeriodMapper {
    @Insert("insert into nax_period(period, lastDistribute, totalSupply, totalNAS, totalDistribute) values(#{period}, #{lastDistribute}, #{totalSupply}, #{totalNAS}, #{totalDistribute})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(NaxPeriod record);

    @Select("select * from nax_period where period=#{period}")
    NaxPeriod getByPeriod(@Param("period") long period);

    @Select("select count(*) from nax_period where period=#{period}")
    long countByPeriod(@Param("period") long period);
}
