package io.nebulas.explorer.grpc;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import io.grpc.Channel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebPendingTransaction;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.DposContext;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.service.blockchain.NebAddressService;
import io.nebulas.explorer.service.blockchain.NebBlockService;
import io.nebulas.explorer.service.blockchain.NebDynastyService;
import io.nebulas.explorer.service.blockchain.NebTransactionService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import rpcpb.ApiServiceGrpc;
import rpcpb.Rpc;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
@AllArgsConstructor
@Service
public class GrpcClientService {
    private GrpcChannelService grpcChannelService;
    private NebBlockService nebBlockService;
    private NebTransactionService nebTransactionService;
    private NebAddressService nebAddressService;
    private NebDynastyService nebDynastyService;
    private NebulasApiService nebulasApiService;

    public void subscribe() {
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

                String topic = sr.getTopic();
                String hash = data.getString("hash");
                if (Const.TopicLinkBlock.equals(topic)) {
                    processTopicLinkBlock(hash);
                } else if (Const.TopicPendingTransaction.equals(topic)) {
                    processTopicPendingTransaction(hash);
                } else if (Const.TopicLibBlock.equals(topic)) {
                    processTopicLibBlock(hash);
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
                    subscribe();
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
        asyncStub.subscribe(Rpc.SubscribeRequest.newBuilder()
                .addTopics(Const.TopicPendingTransaction)
                .addTopics(Const.TopicLinkBlock)
                .addTopics(Const.TopicLibBlock)
                .build(), responseObserver);
    }

    private void processTopicLinkBlock(String hash) {
        if (StringUtils.isBlank(hash)) {
            log.error("empty hash");
            return;
        }
        NebBlock nebBlock = nebBlockService.getNebBlockByHash(hash);
        if (null != nebBlock) {
            log.warn("block with hash {} already existed", hash);
            return;
        }
        try {
            Block block = nebulasApiService.getBlockByHash(new GetBlockByHashRequest(hash, true)).toBlocking().first();

            if (block == null) {
                log.warn("block with hash {} not ready", hash);
                return;
            }

            log.info("get block by hash {}", block.toString());

            addAddr(block.getMiner(), 0);
            addAddr(block.getCoinbase(), 0);

            Block latestIrreversibleBlk = null;
            try {
                latestIrreversibleBlk = getLatestIrreversibleBlock();
            } catch (StatusRuntimeException e) {
                log.error("get block by height error", e);
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
                    .finality(latestIrreversibleBlk != null && block.getHeight() <= latestIrreversibleBlk.getHeight())
                    .createdAt(new Date(System.currentTimeMillis())).build();
            nebBlockService.addNebBlock(newBlock);

            List<Transaction> txs = block.getTransactions();
            for (Transaction tx : txs) {
                String gasUsed;
                try {
                    GetGasUsedResponse gasUsedResponse = nebulasApiService.getGasUsed(new GetGasUsedRequest(tx.getHash())).toBlocking().first();
                    gasUsed = gasUsedResponse.getGas();
                } catch (Exception e) {
                    log.error("get gas used error, set gas used to null for tx hash " + tx.getHash(), e);
                    gasUsed = null;
                }
                if (gasUsed != null) {
                    log.info("gas used: {}", gasUsed);
                }
                NebPendingTransaction nebPendingTransaction = nebTransactionService.getNebPendingTransactionByHash(tx.getHash());
                if (nebPendingTransaction != null) {
                    nebTransactionService.deleteNebPendingTransaction(nebPendingTransaction.getId());
                }
                NebTransaction nebTxs = NebTransaction.builder()
                        .id(IdGenerator.getId())
                        .hash(tx.getHash())
                        .blockHeight(block.getHeight())
                        .blockHash(block.getHash())
                        .from(tx.getFrom())
                        .to(tx.getTo())
                        .status(tx.getStatus())
                        .value(tx.getValue())
                        .nonce(tx.getNonce())
                        .timestamp(new Date(tx.getTimestamp() * 1000))
                        .type(tx.getType())
                        .data(tx.getData())
                        .gasPrice(tx.getGasPrice())
                        .gasLimit(tx.getGasLimit())
                        .gasUsed(gasUsed)
                        .createdAt(new Date())
                        .build();
                nebTransactionService.addNebTransaction(nebTxs);
            }

            GetDynastyResponse dynastyResponse = nebulasApiService.getDynasty(new GetDynastyRequest(block.getHeight())).toBlocking().first();
            nebDynastyService.batchAddNebDynasty(block.getHeight(), dynastyResponse.getDelegatees());

        } catch (UnsupportedEncodingException e) {
            log.error("no block yet", e);
        } catch (Throwable e) {
            log.error("sys error", e);
        }
    }

    private void processTopicPendingTransaction(String hash) {
        if (StringUtils.isBlank(hash)) {
            log.error("empty hash");
            return;
        }

        NebPendingTransaction pendingNebTransaction = nebTransactionService.getNebPendingTransactionByHash(hash);
        if (pendingNebTransaction == null) {
            Transaction txSource;
            try {
                txSource = getTransactionByHash(hash);
            } catch (UnsupportedEncodingException e) {
                log.error("get tx by hash error, skipped pending tx hash " + hash, e);
                return;
            }
            if (txSource == null) {
                log.warn("pending tx with hash {} not ready", hash);
            } else {
                log.info("get pending tx by hash {}", hash);
                NebPendingTransaction pendingTxToSave = NebPendingTransaction.builder()
                        .id(IdGenerator.getId())
                        .hash(hash)
                        .from(txSource.getFrom())
                        .to(txSource.getTo())
                        .value(txSource.getValue())
                        .nonce(txSource.getNonce())
                        .timestamp(new Date(txSource.getTimestamp() * 1000))
                        .type(txSource.getType())
                        .gasPrice(txSource.getGasPrice())
                        .gasLimit(txSource.getGasLimit())
                        .createdAt(new Date())
                        .data(txSource.getData())
                        .build();
                nebTransactionService.addNebPendingTransaction(pendingTxToSave);
            }
        } else {
            log.warn("duplicate pending neb transaction {}", pendingNebTransaction.getHash());
        }
    }

    private void processTopicLibBlock(String hash) {
        NebBlock block = nebBlockService.getNebBlockByHash(hash);
        if (null != block) {
            nebBlockService.updateBlockIrreversible(block.getHeight());
        }
        log.warn("block with hash {} has not been synced", hash);
    }

    private void addAddr(String hash, int type) {
        NebAddress addr = nebAddressService.getNebAddressByHash(hash);
        if (addr == null) {
            try {
                nebAddressService.addNebAddress(hash, type);
            } catch (Throwable e) {
                log.error("add address error", e);
            }
        }
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

    public Block getLatestIrreversibleBlock() throws UnsupportedEncodingException {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.BlockResponse block;
        try {
            block = stub.latestIrreversibleBlock(Rpc.NonParamsRequest.newBuilder().build());
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

    public Map<String, String> findAccountStateMap(List<String> hashes) {
        if (CollectionUtils.isEmpty(hashes)) {
            return Collections.emptyMap();
        }
        Map<String, String> balanceMap = Maps.newHashMap();
        hashes.forEach(a -> balanceMap.put(a, getAccountState(a)));
        return balanceMap;
    }

    public String getAccountState(String addressHash) {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.GetAccountStateResponse accountState;
        try {
            accountState = stub.getAccountState(Rpc.GetAccountStateRequest.newBuilder().setAddress(addressHash).build());
        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return accountState == null ? null : accountState.getBalance();
    }

    public List<String> getDynasty(Long height) {
        ApiServiceGrpc.ApiServiceBlockingStub stub = ApiServiceGrpc.newBlockingStub(grpcChannelService.getChannel());
        Rpc.GetDynastyResponse dynasty;
        try {
            dynasty = stub.getDynasty(Rpc.ByBlockHeightRequest.newBuilder().setHeight(height).build());

        } catch (StatusRuntimeException e) {
            String errMessage = e.getMessage();
            if (errMessage.contains("not found") || errMessage.contains("invalid byte")) {
                return null;
            }
            throw e;
        }
        return populateDynasty(dynasty);
    }

    private List<String> populateDynasty(Rpc.GetDynastyResponse dynasty) {
        if (null == dynasty) {
            return Collections.emptyList();
        }
        List<String> delegateList = new ArrayList<>();
        for (Iterator i = dynasty.getDelegateesList().iterator(); i.hasNext(); ) {
            delegateList.add((String) i.next());
        }
        return delegateList;
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
