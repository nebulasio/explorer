package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebTxCountByDay;
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
    private NebAddressService nebAddressService;
    @Autowired
    private NebTxCountByDayService nebTxCountByDayService;

    public StatVo stat() {
        Date yesterdayDate = LocalDate.now().plusDays(-1).toDate();
        NebTxCountByDay txCountByDay = nebTxCountByDayService.getByDay(yesterdayDate);
        Map<NebAddressTypeEnum, Long> addrMap = nebAddressService.countAccountGroupByType();
        return StatVo.builder()
                .dailyTxCount(txCountByDay==null ? 0L : (long) txCountByDay.getCount())
                .contractCount(addrMap.get(NebAddressTypeEnum.CONTRACT) == null ? new Long(0) : addrMap.get(NebAddressTypeEnum.CONTRACT))
                .addrCount(addrMap.get(NebAddressTypeEnum.NORMAL) == null ? new Long(0) : addrMap.get(NebAddressTypeEnum.NORMAL))
                .build();
    }
}
