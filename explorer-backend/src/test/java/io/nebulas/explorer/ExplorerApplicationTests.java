//package io.nebulas.explorer;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import io.nebulas.explorer.service.blockchain.NebDipAwardService;
//import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
//
//@ActiveProfiles("dev")
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ExplorerApplicationTests {
//
//    @Autowired
//    private NebDipAwardService dipAwardService;
//
//    /**
//     * 测试DIP交易的解析
//     * 运行多次可以检查是否存在重复插入
//     * @throws ParseException   with SimpleDateFormat
//     */
//    @Test
//    public void testDipTransactionParse() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Transaction transaction = new Transaction();
//        transaction.setHash("91ef064ec20c93d65de3c2c7d3610b18f35b19edd50318681c4a76c2ae91f1af");
//        transaction.setType("dip");
//        transaction.setValue("300228915537073143808");
//        transaction.setTo("n1ZeoNgcV2rr92xx96Z3zn8FpA2hT4Qoxbd");
//        transaction.setData("eyJTdGFydEhlaWdodCI6MTU2MjgwMCwiRW5kSGVpZ2h0IjoxNjAzMTE5LCJWZXJzaW9uIjoyODE0NzQ5NzY3MTA2NTYsIkNvbnRyYWN0IjoibjF5WG1EZVpuZmtiM1FQTFZadVl6ZFRueHZWZHZaMno2d2QifQ==");
//        transaction.setTimestamp(sdf.parse("2019-01-29 01:24:15").getTime()/1000);
//        dipAwardService.parseDipTransaction(transaction);
//    }
//
//}
