package io.nebulas.explorer.grpc;

import com.alibaba.fastjson.JSONObject;
import io.grpc.Channel;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.DposContext;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebTransactionService;
import io.nebulas.explorer.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpcpb.ApiRpc;
import rpcpb.ApiServiceGrpc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static io.nebulas.explorer.util.BlockUtil.collectTxs;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-23
 */
@Slf4j
@Service
public class GrpcClientService {
    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    @Autowired
    private NebBlockService nebBlockService;

    @Autowired
    private NebTransactionService nebTransactionService;

    public void subscribe(String topic) {
        ApiServiceGrpc.ApiServiceStub asyncStub = ApiServiceGrpc.newStub(serverChannel);
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<ApiRpc.SubscribeResponse> responseObserver = new StreamObserver<ApiRpc.SubscribeResponse>() {
            @Override
            public void onNext(ApiRpc.SubscribeResponse sr) {
                String dataStr = sr.getData();
                log.info("msg type: {}, data: {}", sr.getMsgType(), dataStr);
                if (StringUtils.isBlank(dataStr)) {
                    log.error("empty data");
                    return;
                }
                JSONObject data;
                try {
                    data = JSONObject.parseObject(dataStr);
                } catch (Throwable e) {
                    log.error(String.format("data string %s can NOT parse into json", dataStr), e);
                    return;
                }
                if (Const.TopicLinkBlock.equals(sr.getMsgType())) {
                    Long height = data.getLong("height");
                    if (height == null) {
                        log.error("empty height");
                    } else {
                        NebBlock nebBlock = nebBlockService.getByHeight(height);
                        if (nebBlock == null) {
                            try {
                                Block block = getBlockByHeight(height, true);
                                if (block == null) {
                                    log.warn("block with height {} not ready", height);
                                } else {
                                    NebBlock newBlock = NebBlock.builder()
                                            .id(IdGenerator.getId())
                                            .height(block.getHeight())
                                            .hash(block.getHash())
                                            .parentHash(block.getParentHash())
                                            .timestamp(new Date(block.getTimestamp() * 1000))
                                            .miner(block.getMiner())
                                            .coinbase(block.getCoinbase())
                                            .nonce(block.getNonce())
                                            .createdAt(new Date(System.currentTimeMillis())).build();
                                    nebBlockService.addNebBlock(newBlock);
                                    List<Transaction> txs = block.getTransactions();

                                    List<NebTransaction> nebTxsList = new ArrayList<>(txs.size());

                                    List<String> gasUsedList = new ArrayList<>(txs.size());

                                    for (Transaction tx : txs) {
                                        String gasUsed = getGasUsed(tx.getHash());
                                        if (gasUsed != null) {
                                            log.info("gas used: {}", gasUsed);
                                            gasUsedList.add("");
                                        } else {
                                            gasUsedList.add(gasUsed);
                                        }
                                    }

                                    collectTxs(block, txs, nebTxsList, gasUsedList);
                                    if (nebTxsList.size() > 0) {
                                        nebTransactionService.batchAddNebTransaction(nebTxsList);
                                    }
                                }
                            } catch (UnsupportedEncodingException e) {
                                log.error("not block yet", e);
                            }
                        } else {
                            log.warn("block with height {} already existed", height);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                log.warn("failed: {}", status);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                log.info("Finished");
                finishLatch.countDown();
            }
        };
        asyncStub.subscribe(ApiRpc.SubscribeRequest.newBuilder().addTopic(topic).build()
                , responseObserver);
    }

    public String getGasUsed(String hash) {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(serverChannel);
        ApiRpc.GasResponse gasUsed = stub.getGasUsed(ApiRpc.HashRequest.newBuilder().setHash(hash).build());
        return populateGas(gasUsed);
    }

    public Block getBlockByHash(String hash) throws UnsupportedEncodingException {
        return getBlockByHash(hash, false);
    }

    public Block getBlockByHash(String hash, Boolean fullTransaction) throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(serverChannel);
        ApiRpc.BlockResponse block = stub.getBlockByHash(ApiRpc.GetBlockByHashRequest.newBuilder().setHash(hash).setFullTransaction(fullTransaction).build());
        return populateBlock(block);
    }

    public NebState getNebState() {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(serverChannel);
        ApiRpc.GetNebStateResponse nebState = stub.getNebState(ApiRpc.NonParamsRequest.newBuilder().build());
        return populateNebState(nebState);
    }

    @Deprecated
    public String getDumpData(int count) {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(serverChannel);
        ApiRpc.BlockDumpResponse response = stub.blockDump(ApiRpc.BlockDumpRequest.newBuilder().setCount(count).build());
        return response.getData();
    }

    public Transaction getTransactionByHash(String hash) throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(serverChannel);
        ApiRpc.TransactionResponse transactionReceipt = stub.getTransactionReceipt(ApiRpc
                .GetTransactionByHashRequest.newBuilder()
                .setHash(hash).build());
        return populateTransaction(transactionReceipt);
    }

    public Block getBlockByHeight(long height) throws UnsupportedEncodingException {
        return getBlockByHeight(height, false);
    }

    private String populateGas(ApiRpc.GasResponse gasResponse) {
        if (gasResponse == null) {
            return null;
        }
        return gasResponse.getGas();
    }

    private NebState populateNebState(ApiRpc.GetNebStateResponse nebState) {
        if (nebState == null) {
            return null;
        }
        return new NebState(nebState.getChainId(), nebState.getTail(), nebState.getCoinbase()
                , nebState.getPeerCount(), nebState.getIsMining(), nebState.getProtocolVersion()
                , nebState.getSynchronized(), nebState.getVersion());
    }

    public Block getBlockByHeight(long height, boolean fullTransaction) throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(serverChannel);
        ApiRpc.BlockResponse response = stub.getBlockByHeight(ApiRpc.GetBlockByHeightRequest.newBuilder()
                .setHeight(height)
                .setFullTransaction(fullTransaction)
                .build());
        return populateBlock(response);
    }

    private Block populateBlock(ApiRpc.BlockResponse response) throws UnsupportedEncodingException {
        if (response == null) {
            return null;
        }
        ApiRpc.DposContext dposContext = response.getDposContext();
        DposContext dposCxt = populateDposContext(dposContext);
        List<ApiRpc.TransactionResponse> transactionsList = response.getTransactionsList();
        List<Transaction> transactions = populateTransactionList(transactionsList);

        return new Block(response.getHash(), response.getParentHash(), response.getHeight()
                , response.getNonce(), response.getCoinbase(), response.getMiner(), response.getTimestamp()
                , response.getChainId(), response.getStateRoot(), response.getTxsRoot(), response.getEventsRoot()
                , dposCxt, transactions);
    }

    private DposContext populateDposContext(ApiRpc.DposContext dposContext) {
        return dposContext == null ? null
                : new DposContext(dposContext.getDynastyRoot(), dposContext.getNextDynastyRoot()
                , dposContext.getDelegateRoot(), dposContext.getCandidateRoot(), dposContext.getVoteRoot()
                , dposContext.getMintCntRoot());
    }

    private List<Transaction> populateTransactionList(List<ApiRpc.TransactionResponse> transactionResponseList) throws UnsupportedEncodingException {
        if (transactionResponseList == null || transactionResponseList.isEmpty()) {
            return new ArrayList<>(0);
        }
        List<Transaction> transactions = new ArrayList<>(transactionResponseList.size());
        for (ApiRpc.TransactionResponse tr : transactionResponseList) {
            Transaction transaction = populateTransaction(tr);
            if (transaction != null) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    private Transaction populateTransaction(ApiRpc.TransactionResponse transactionResponse) throws UnsupportedEncodingException {
        return transactionResponse == null ? null
                : new Transaction(transactionResponse.getHash(), transactionResponse.getChainId()
                , transactionResponse.getFrom(), transactionResponse.getTo(), transactionResponse.getValue()
                , transactionResponse.getNonce(), transactionResponse.getTimestamp(), transactionResponse.getType()
                , transactionResponse.getData() == null ? null : transactionResponse.getData().toString("UTF-8")
                , transactionResponse.getGasPrice(), transactionResponse.getGasLimit(), transactionResponse.getContractAddress()
                , transactionResponse.getStatus());
    }

}
