package io.nebulas.explorer;

import io.nebulas.explorer.domain.NaxPeriod;
import io.nebulas.explorer.mapper.NaxPeriodMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.nebulas.explorer.service.blockchain.NebDipAwardService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExplorerApplicationTests {

    @Autowired
    private NebDipAwardService dipAwardService;

    @Autowired
    private NaxPeriodMapper naxPeriodMapper;
    /**
     * 测试DIP交易的解析
     * 运行多次可以检查是否存在重复插入
     * @throws ParseException   with SimpleDateFormat
     */
    @Test
    public void testDipTransactionParse() throws ParseException {
        NaxPeriod period = new NaxPeriod();
        period.setPeriod(1L);
        period.setLastDistribute(1000L);
        period.setTotalDistribute(22L);
        period.setTotalNAS(10000000L);
        period.setTotalSupply(333L);
        naxPeriodMapper.insert(period);

        NaxPeriod naxPeriodFromDB = naxPeriodMapper.getByPeriod(1L);
        System.out.println(naxPeriodFromDB);
        //Assert.assertTrue(naxPeriodFromDB.size()>0);
    }

}
