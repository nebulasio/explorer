//package io.nebulas.explorer;
//
//
//import io.nebulas.explorer.service.blockchain.NebEventService;
//import io.nebulas.explorer.service.thirdpart.nebulas.bean.Event;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import rpcpb.Rpc;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class NebEventServiceTest {
//
//    @Autowired
//    private NebEventService nebEventService;
//
//    @Test
//    public void testGetEventByHash(){
//
//        String emptyHash = "";
//        List<Event> eventList = nebEventService.findEventListByHash(emptyHash);
//        Assert.assertEquals(eventList.size(),0);
//
//        String invalidHash = "xxxx";
//        eventList = nebEventService.findEventListByHash(invalidHash);
//        Assert.assertEquals(eventList.size(),0);
//
//        String validHash = "b3995683712e16c0e4008b1eb9959e86914407530bf5921fb78fa17683c8d16c";
//        eventList = nebEventService.findEventListByHash(validHash);
//        Assert.assertEquals(eventList.size(),2);
//        Assert.assertEquals(eventList.get(0).getTopic(),"chain.contract.TIE");
//        Assert.assertEquals(eventList.get(1).getTopic(),"chain.transactionResult");
//
//        String nodataHash = "276aed73bd6982de4ca29c144ae1040fabb66720f03df6fe913fb9a882d176d4";
//        eventList = nebEventService.findEventListByHash(nodataHash);
//        Assert.assertEquals(eventList.size(),0);
//
//    }
//
//
//}
