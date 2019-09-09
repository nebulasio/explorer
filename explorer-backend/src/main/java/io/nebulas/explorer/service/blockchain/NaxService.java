package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NaxProfit;
import io.nebulas.explorer.mapper.NaxProfitMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NaxService {

    @Autowired
    private NaxProfitMapper naxProfitMapper;

    public List<NaxProfit> list(int page, int pageSize, String address) {
        return naxProfitMapper.getByAddress(address, (page - 1) * pageSize, pageSize);
    }

    public long total(String address){
        return naxProfitMapper.countByAddress(address);
    }
}
