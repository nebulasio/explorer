package io.nebulas.explorer;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.grpc.GrpcClientService;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.service.blockchain.NebAddressService;
import io.nebulas.explorer.service.blockchain.NebTransactionService;
import io.nebulas.explorer.util.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExplorerApplicationTests {
    @Autowired
    private NebTransactionService nebTransactionService;

    @Autowired
    private NebAddressService nebAddressService;

    @Autowired
    private GrpcClientService grpcClientService;

    @Test
    public void contextLoads() {
    }

    //    @Test
    public void testTx() {
        List<NebTransaction> txs = new ArrayList<>();
        NebTransaction nebTransaction = NebTransaction.builder()
                .id(IdGenerator.getId())
                .hash("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9be")
                .blockHeight(146240L)
                .blockHash("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9be")
                .from("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9be")
                .to("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9be")
                .status(1)
                .value("{}")
                .nonce(0L)
                .timestamp(new Date())
                .type("binary")
                .gasPrice("3.8")
                .gasLimit("10")
                .gasUsed("2.1")
                .createdAt(new Date()).build();
        txs.add(nebTransaction);
        nebTransaction = NebTransaction.builder()
                .id(IdGenerator.getId())
                .hash("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9b")
                .blockHeight(146240L)
                .blockHash("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9b")
                .from("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9b")
                .to("a268cc55f9670312e33daad0fcea92b797a854263f786b2a17ee13d06099c9b")
                .status(1)
                .value("{}")
                .nonce(0L)
                .timestamp(new Date())
                .type("binary")
                .gasPrice("3.8")
                .gasLimit("10")
                .gasUsed("2.1")
                .createdAt(new Date()).build();
        txs.add(nebTransaction);
        nebTransactionService.batchAddNebTransaction(txs);
        List<NebTransaction> result = nebTransactionService.findTxnByBlockHeight(146240L);
        System.out.println(result);
    }

    //    @Test
    public void testAddr() {
        nebAddressService.addNebAddress("262646262", 0);
        NebAddress addr = nebAddressService.getNebAddressByHash("262646262");
        System.out.println(addr);
    }

//    @Test
    public void testRpc() {
        try {
            Block block = grpcClientService
                    .getBlockByHash("ff6aeddecfe4cc8810e7318e4bc7d52626cfbb6934ec7bbc6ad571b35b344aeg", false);
            if (block == null) {
                System.out.println("empty block");
            } else {
                System.out.println(String.format("block %s", block.toString()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void testRpc2() {
        try {
            Block block = grpcClientService.getBlockByHeight(329495, true);
            if (block == null) {
                System.out.println("empty block");
            } else {
                System.out.println(String.format("block %s", block.toString()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
