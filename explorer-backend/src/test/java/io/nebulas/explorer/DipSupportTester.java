package io.nebulas.explorer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import io.nebulas.explorer.mapper.NebDipAwardMapper;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = {ExplorerApplication.class})
public class DipSupportTester {

    @Resource
    NebDipAwardMapper nebDipAwardMapper;

    @Test
    public void testInsert(){
//        NebDipAward award = NebDipAward.builder()
//                .contract("n1oCz3qg9CqXGBH6fuvbMDpQHaC3vRANNQF")
//                .creator("n1cNMFrdM9a3vT2C3vtiC6j5eQhAWEP73Z1")
//                .award("10086")
//                .txHash("8ed7a662dceb6f36e57866d4aa4058bc9d0fb1bfa52bb709afd23b4f2d01b805")
//                .txTimestamp(new Date(1547016485000L))
//                .week(2)
//                .year(2019)
//                .build();
//        int effectRow = nebDipAwardMapper.insert(award);
//        Assert.assertEquals(effectRow, 1);
    }
}
