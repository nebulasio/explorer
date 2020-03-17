//package io.nebulas.explorer.config.http;
//
//import com.alibaba.fastjson.JSONObject;
//import io.nebulas.explorer.service.thirdpart.coinmarketcap.CoinMarketCapApiService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class HttpApiConfigTest {
//
//    @Autowired
//    private CoinMarketCapApiService coinMarketCapApiService;
//
//    @Test
//    public void createCoinMarketCapApiService() {
//        JSONObject object = coinMarketCapApiService.getMarket().toBlocking().first();
//        Assert.assertNotNull(object);
//    }
//}