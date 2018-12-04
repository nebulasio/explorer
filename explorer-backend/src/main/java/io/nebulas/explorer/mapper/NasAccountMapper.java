package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NasAccount;
import io.nebulas.explorer.domain.NasAccountCondition;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface NasAccountMapper {
    int countByCondition(NasAccountCondition example);

    int deleteByCondition(NasAccountCondition example);

    int deleteById(Long id);

    int insert(NasAccount record);

    int insertSelective(NasAccount record);

    List<NasAccount> selectByCondition(NasAccountCondition example);

    NasAccount selectById(Long id);

    int updateByConditionSelective(@Param("record") NasAccount record, @Param("example") NasAccountCondition example);

    int updateByCondition(@Param("record") NasAccount record, @Param("example") NasAccountCondition example);

    int updateByIdSelective(NasAccount record);

    int updateById(NasAccount record);

    NasAccount selectByLatest();

}