package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

import io.nebulas.explorer.domain.NatRecord;

@Mapper
@Component
public interface NatRecordMapper {

    @Insert("insert into nat_record(address, txHash, block, amount, source, timestamp, createdAt) values(#{address}, #{txHash}, #{block}, #{amount}, #{source}, #{timestamp}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(NatRecord record);

    @Select("select * from nat_record where address=#{address} order by `timestamp` desc limit #{offset}, #{limit}")
    List<NatRecord> getByAddress(@Param("address") String address, @Param("offset") int offset, @Param("limit") int limit);

}
