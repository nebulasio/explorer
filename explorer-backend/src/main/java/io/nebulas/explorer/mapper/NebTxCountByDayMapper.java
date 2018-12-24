package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

import io.nebulas.explorer.domain.NebTxCountByDay;

@Mapper
public interface NebTxCountByDayMapper {

    public NebTxCountByDay getByDay(Date day);

    public int insert(NebTxCountByDay record);

    public int update(NebTxCountByDay record);
}
