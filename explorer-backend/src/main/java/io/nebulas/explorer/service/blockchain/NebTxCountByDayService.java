package io.nebulas.explorer.service.blockchain;

import org.springframework.stereotype.Service;

import java.util.Date;

import io.nebulas.explorer.domain.NebTxCountByDay;
import io.nebulas.explorer.mapper.NebTxCountByDayMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NebTxCountByDayService {

    private NebTxCountByDayMapper txCountByDayMapper;

    public NebTxCountByDay getByDay(Date day){
        return txCountByDayMapper.getByDay(day);
    }

    public void insert(NebTxCountByDay record){
        txCountByDayMapper.insert(record);
    }

    public void update(NebTxCountByDay record) {
        NebTxCountByDay exist = getByDay(record.getDay());
        if (exist!=null){
            if (exist.getCount()!=record.getCount()){
                txCountByDayMapper.update(record);
            }
        } else {
            txCountByDayMapper.insert(record);
        }
    }

}
