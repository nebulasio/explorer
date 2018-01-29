package io.nebulas.explorer.grpc;

import com.alibaba.fastjson.JSONObject;
import io.grpc.Channel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpcpb.ApiServiceGrpc;
import rpcpb.Rpc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private GrpcChannelService grpcChannelService;

    @Autowired
    private NebBlockService nebBlockService;

    @Autowired
    private NebTransactionService nebTransactionService;

    public void subscribe(String topic) {
        Channel channel = grpcChannelService.getChannel();
        ApiServiceGrpc.ApiServiceStub asyncStub = ApiServiceGrpc.newStub(channel);
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<Rpc.SubscribeResponse> responseObserver = new StreamObserver<Rpc.SubscribeResponse>() {
            @Override
            public void onNext(Rpc.SubscribeResponse sr) {
                String dataStr = sr.getData();
                log.info("msg type: {}, data: {}", sr.getTopic(), dataStr);
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
                if (Const.TopicLinkBlock.equals(sr.getTopic())) {
                    String hash = data.getString("hash");
                    if (StringUtils.isBlank(hash)) {
                        log.error("empty hash");
                    } else {
                        NebBlock nebBlock = nebBlockService.getNebBlockByHash(hash);
                        if (nebBlock == null) {
                            try {
                                Block block;
                                try {
                                    block = getBlockByHash(hash, true);
                                } catch (StatusRuntimeException e) {
                                    log.error("get block by hash error, skipped block hash " + hash, e);
                                    return;
                                }
                                if (block == null) {
                                    log.warn("block with hash {} not ready", hash);
                                } else {
                                    log.info("get block by hash {}", block.toString());
                                    Integer trx = data.getInteger("tx");
                                    if (trx != null && trx > 0) {
                                        log.info("guess the block has transaction");
                                        if (block.getTransactions() == null || block.getTransactions().isEmpty()) {
                                            log.info("but can not get block transactions by full transaction");
                                        }
                                    }
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
                                        String gasUsed;
                                        try {
                                            gasUsed = getGasUsed(tx.getHash());
                                        } catch (StatusRuntimeException e) {
                                            log.error("get gas used error, set gas used to null for tx hash " + tx.getHash(), e);
                                            gasUsed = null;
                                        }
                                        if (gasUsed != null) {
                                            log.info("gas used: {}", gasUsed);
                                            gasUsedList.add(gasUsed);
                                        } else {
                                            gasUsedList.add("");
                                        }
                                    }

                                    collectTxs(block, txs, nebTxsList, gasUsedList);
                                    if (nebTxsList.size() > 0) {
                                        nebTransactionService.batchAddNebTransaction(nebTxsList);
                                    }
                                }
                            } catch (UnsupportedEncodingException e) {
                                log.error("no block yet", e);
                            }
                        } else {
                            log.warn("block with hash {} already existed", hash);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                log.warn("failed: {}", status);
                try {
                    grpcChannelService.renewChannel();
                    // sleep for 10 seconds to enter next retry
                    log.info("entering next retry after 10 seconds ....");
                    TimeUnit.SECONDS.sleep(10);
                    subscribe(topic);
                } catch (InterruptedException e1) {
                    log.error("thread sleep interrupted, skipped reconnect", e1);
                    finishLatch.countDown();
                }
            }

            @Override
            public void onCompleted() {
                log.info("Finished");
                finishLatch.countDown();
            }
        };
        asyncStub.subscribe(Rpc.SubscribeRequest.newBuilder().addTopics(topic).build()
                , responseObserver);
    }

    public String getGasUsed(String hash) {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.GasResponse gasUsed;
        try {
            gasUsed = stub.getGasUsed(Rpc.HashRequest.newBuilder().setHash(hash).build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return populateGas(gasUsed);
    }

    public Block getBlockByHash(String hash) throws UnsupportedEncodingException {
        return getBlockByHash(hash, false);
    }

    public Block getBlockByHash(String hash, Boolean fullTransaction) throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.BlockResponse block;
        try {
            block = stub.getBlockByHash(Rpc.GetBlockByHashRequest.newBuilder().setHash(hash).setFullTransaction(fullTransaction).build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return populateBlock(block);
    }

    public NebState getNebState() {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.GetNebStateResponse nebState;
        try {
            nebState = stub.getNebState(Rpc.NonParamsRequest.newBuilder().build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return populateNebState(nebState);
    }

    @Deprecated
    public String getDumpData(int count) {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.BlockDumpResponse response;
        try {
            response = stub.blockDump(Rpc.BlockDumpRequest.newBuilder().setCount(count).build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return response.getData();
    }

    public Transaction getTransactionByHash(String hash) throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.TransactionResponse transactionReceipt;
        try {
            transactionReceipt = stub.getTransactionReceipt(Rpc
                    .GetTransactionByHashRequest.newBuilder()
                    .setHash(hash).build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return populateTransaction(transactionReceipt);
    }

    public Block getBlockByHeight(long height) throws UnsupportedEncodingException {
        return getBlockByHeight(height, false);
    }

    private String populateGas(Rpc.GasResponse gasResponse) {
        if (gasResponse == null) {
            return null;
        }
        return gasResponse.getGas();
    }

    private NebState populateNebState(Rpc.GetNebStateResponse nebState) {
        if (nebState == null) {
            return null;
        }
        return new NebState(nebState.getChainId(), nebState.getTail(), nebState.getCoinbase()
                , nebState.getPeerCount(), nebState.getIsMining(), nebState.getProtocolVersion()
                , nebState.getSynchronized(), nebState.getVersion());
    }

    public Block getBlockByHeight(long height, boolean fullTransaction) throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.BlockResponse response;
        try {
            response = stub.getBlockByHeight(Rpc.GetBlockByHeightRequest.newBuilder()
                    .setHeight(height)
                    .setFullTransaction(fullTransaction)
                    .build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return populateBlock(response);
    }

    private Block populateBlock(Rpc.BlockResponse response) throws UnsupportedEncodingException {
        if (response == null) {
            return null;
        }
        Rpc.DposContext dposContext = response.getDposContext();
        DposContext dposCxt = populateDposContext(dposContext);
        List<Rpc.TransactionResponse> transactionsList = response.getTransactionsList();
        List<Transaction> transactions = populateTransactionList(transactionsList);

        return new Block(response.getHash(), response.getParentHash(), response.getHeight()
                , response.getNonce(), response.getCoinbase(), response.getMiner(), response.getTimestamp()
                , response.getChainId(), response.getStateRoot(), response.getTxsRoot(), response.getEventsRoot()
                , dposCxt, transactions);
    }

    private DposContext populateDposContext(Rpc.DposContext dposContext) {
        return dposContext == null ? null
                : new DposContext(dposContext.getDynastyRoot(), dposContext.getNextDynastyRoot()
                , dposContext.getDelegateRoot(), dposContext.getCandidateRoot(), dposContext.getVoteRoot()
                , dposContext.getMintCntRoot());
    }

    private List<Transaction> populateTransactionList(List<Rpc.TransactionResponse> transactionResponseList) throws UnsupportedEncodingException {
        if (transactionResponseList == null || transactionResponseList.isEmpty()) {
            return new ArrayList<>(0);
        }
        List<Transaction> transactions = new ArrayList<>(transactionResponseList.size());
        for (Rpc.TransactionResponse tr : transactionResponseList) {
            Transaction transaction = populateTransaction(tr);
            if (transaction != null) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    private Transaction populateTransaction(Rpc.TransactionResponse transactionResponse) throws UnsupportedEncodingException {
        return transactionResponse == null ? null
                : new Transaction(transactionResponse.getHash(), transactionResponse.getChainId()
                , transactionResponse.getFrom(), transactionResponse.getTo(), transactionResponse.getValue()
                , transactionResponse.getNonce(), transactionResponse.getTimestamp(), transactionResponse.getType()
                , transactionResponse.getData() == null ? null : transactionResponse.getData().toString("UTF-8")
                , transactionResponse.getGasPrice(), transactionResponse.getGasLimit(), transactionResponse.getContractAddress()
                , transactionResponse.getStatus());
    }

}
