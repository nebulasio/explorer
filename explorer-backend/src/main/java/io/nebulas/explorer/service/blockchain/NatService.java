package io.nebulas.explorer.service.blockchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import io.nebulas.explorer.domain.NatRecord;
import io.nebulas.explorer.mapper.NatRecordMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NatService {

    @Autowired
    private NatRecordMapper natRecordMapper;

    public List<NatRecord> list(int page, int pageSize, String address) {
        return natRecordMapper.getByAddress(address, (page - 1) * pageSize, pageSize);
    }

    public long total(String address){
        return natRecordMapper.countByAddress(address);
    }
}
