package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NasAccount;
import io.nebulas.explorer.domain.NasAccountCondition;
import io.nebulas.explorer.mapper.NasAccountMapper;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class NasAccountService {

    private final NasAccountMapper nasAccountMapper;

    public NasAccount getLatestNasAccount() {
        NasAccount nasAccount = nasAccountMapper.selectByLatest();
        if (nasAccount == null) {
            nasAccount = nasAccountMapper.selectByLatest();
        }
        return nasAccount;
    }


    public NasAccount getNasAccountFromNinetyDays() {
        //找90天前的account记录
        NasAccountCondition cond = new NasAccountCondition();

        DateTime today = new DateTime(DateTimeZone.UTC).withTimeAtStartOfDay().toDateTime(DateTimeZone.forID("Asia/Shanghai"));
        DateTime ninetyDay = today.minusDays(90);
        cond.createCriteria().andTimestampEqualTo(ninetyDay.withTimeAtStartOfDay().toDate());

        List<NasAccount> nasAccount = nasAccountMapper.selectByCondition(cond);
        if (nasAccount.size() == 0) {
            return null;
        }
        return nasAccount.get(0);
    }

    //获取八周内的账户变化数据
    public List<NasAccount> getEightWeeks() {

        NasAccountCondition cond = new NasAccountCondition();
        DateTime today = new DateTime().withTimeAtStartOfDay();
        DateTime eightWeek = today.minusDays(56);
        cond.createCriteria().andTimestampBetween(eightWeek.toDate(), today.toDate());
        List<NasAccount> nasAccountList = nasAccountMapper.selectByCondition(cond);
        if (nasAccountList.size() == 0) {
            return Collections.emptyList();
        }

        //每隔8天把数据取出来
        List<NasAccount> result = new ArrayList<>();
        for (int i = 0; i < nasAccountList.size(); i++) {
            if (i % 7 == 0) {
                result.add(nasAccountList.get(i));
            }
        }
        return result;
    }


}
