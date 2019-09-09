package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NaxRecord;
import io.nebulas.explorer.mapper.NaxMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NaxService {

    @Autowired
    private NaxMapper naxMapper;

    public List<NaxRecord> list(int page, int pageSize, String address) {
        return naxMapper.getByAddress(address, (page - 1) * pageSize, pageSize);
    }

    public long total(String address){
        return naxMapper.countByAddress(address);
    }
}
