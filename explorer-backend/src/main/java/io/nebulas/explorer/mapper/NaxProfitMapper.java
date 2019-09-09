package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import io.nebulas.explorer.domain.NaxProfit;


@Mapper
@Component
public interface NaxProfitMapper {

    @Insert("insert into nax_profit" +
            "(address, txHash, block, profit, source, stage, timestamp, createdAt) " +
            "values" +
            "(#{address}, #{txHash}, #{block}, #{profit}, #{source}, #{stage}, #{timestamp}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(NaxProfit record);

    @Select("select * from nax_profit where address=#{address} order by `timestamp` desc limit #{offset}, #{limit}")
    List<NaxProfit> getByAddress(@Param("address") String address, @Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from nax_profit where address=#{address}")
    long countByAddress(@Param("address") String address);

    @Select("select sum(profit) from nax_profit where address=#{address}")
    BigDecimal sumByAddress(@Param("address") String address);

    @Select("<script>"+
                "select sum(profit) as profits from nax_profit where address in " +
                "<foreach item='address' index='index' collection='addresses' open='(' separator=',' close=')'>" +
                    "#{address} " +
                "</foreach>" +
            "</script>")
    BigDecimal sumProfitsByAddresses(@Param("addresses") List<String> addresses);

    @Select("<script>"+
                "select * from nax_profit where stage=#{stage} and address in " +
                "<foreach item='address' index='index' collection='addresses' open='(' separator=',' close=')'>" +
                    "#{address} " +
                "</foreach>" +
            "</script>")
    List<NaxProfit> getStageProfitsByAddresses(@Param("stage") long stage, @Param("addresses") List<String> addresses);

}
