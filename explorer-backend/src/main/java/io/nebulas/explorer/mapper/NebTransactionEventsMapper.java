package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

import io.nebulas.explorer.domain.NebTransactionEvent;


@Mapper
@Component
public interface NebTransactionEventsMapper {

    @Insert("insert into neb_transaction_events(txHash, timestamp, block, topic, data) values(#{txHash}, #{timestamp}, #{block}, #{topic}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(NebTransactionEvent event);

}
