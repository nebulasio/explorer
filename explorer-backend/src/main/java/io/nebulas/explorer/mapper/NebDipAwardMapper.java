package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Mapper;

import io.nebulas.explorer.domain.NebDipAward;

@Mapper
public interface NebDipAwardMapper {

    public int insert(NebDipAward award);

}
