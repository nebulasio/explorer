package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.model.vo.StatVo;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author YanxiSir
 * @since 2018/6/28
 */
@Service
public class NebStatService {

    @Autowired
    private NebTransactionService nebTransactionService;
    @Autowired
    private NebAddressService nebAddressService;

    public StatVo stat() {
        Date yesterdayDate = LocalDate.now().plusDays(-1).toDate();
        String yesterday = LocalDate.fromDateFields(yesterdayDate).toString("yyyy-MM-dd");
        Map<String, Long> txMap = nebTransactionService.countTxCntGroupMapByTimestamp(yesterdayDate, LocalDate.now().toDate());
        Map<NebAddressTypeEnum, Long> addrMap = nebAddressService.countAccountGroupByType();
        return StatVo.builder()
                .dailyTxCount(txMap.containsKey(yesterday) ? txMap.get(yesterday) : new Long(0))
                .contractCount(addrMap.get(NebAddressTypeEnum.CONTRACT) == null ? new Long(0) : addrMap.get(NebAddressTypeEnum.CONTRACT))
                .addrCount(addrMap.get(NebAddressTypeEnum.NORMAL) == null ? new Long(0) : addrMap.get(NebAddressTypeEnum.NORMAL))
                .build();
    }
}
