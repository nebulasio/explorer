package io.nebulas.explorer.mapper;

import io.nebulas.explorer.domain.NaxRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NaxMapper {
    @Insert("insert into nax_record(address, txHash, block, amount, source, timestamp, createdAt) values(#{address}, #{txHash}, #{block}, #{amount}, #{source}, #{timestamp}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(NaxRecord record);

    @Select("select * from nax_record where address=#{address} order by `timestamp` desc limit #{offset}, #{limit}")
    List<NaxRecord> getByAddress(@Param("address") String address, @Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from nat_record where address=#{address}")
    long countByAddress(@Param("address") String address);
}
