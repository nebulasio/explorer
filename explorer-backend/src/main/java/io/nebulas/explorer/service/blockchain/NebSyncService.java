package io.nebulas.explorer.service.blockchain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebContractTokenBalance;
import io.nebulas.explorer.domain.NebPendingTransaction;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.enums.NebTransactionTypeEnum;
import io.nebulas.explorer.grpc.GrpcChannelService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Desc:
 * User: nathan
 * Date: 2018-03-20
 */
@Slf4j(topic = "subscribe")
@Service
public class NebSyncService {

    @Autowired
    private GrpcChannelService grpcChannelService;
    @Autowired
    private NebBlockService nebBlockService;
    @Autowired
    private NebTransactionService nebTransactionService;
    @Autowired
    private NebAddressService nebAddressService;
    @Autowired
    private NebDynastyService nebDynastyService;
    @Autowired
    private NebApiServiceWrapper nebApiServiceWrapper;
    @Autowired
    private ContractTokenBalanceService contractTokenBalanceService;

    private static final Base64.Decoder DECODER = Base64.getDecoder();


    public void testSyncContractTransactions(String hash){
        try {
            Block block = nebApiServiceWrapper.getBlockByHash(hash, true);
            if (block == null) {
                log.error("block with hash {} not found", hash);
                return;
            }
            log.info("get block by hash {}", block.getHash());
            List<Transaction> txs = block.getTransactions();
            for (Transaction tx: txs) {
                NebTransactionTypeEnum typeEnum = NebTransactionTypeEnum.parse(tx.getType());
                if (NebTransactionTypeEnum.CALL.equals(typeEnum)) {
                    log.info("开始处理合约调用交易: " + tx.getHash());
                    JSONObject data = decodeData(tx.getData());
                    processContractBalanceInfo(tx, data);
                }
            }
        } catch (Exception e) {
            log.error("no block yet", e);
        }
    }



    public void syncBlockByHash(String hash, boolean isLib) {
        try {
            Block block = nebApiServiceWrapper.getBlockByHash(hash, true);
            if (block == null) {
                log.error("block with hash {} not found", hash);
                return;
            }
            log.info("get block by hash {}", block.getHash());

            syncBlock(block, isLib);
        } catch (Exception e) {
            log.error("no block yet", e);
        }
    }

    public void syncBlockByHeight(long height, boolean isLib) {
        try {
            Block block = nebApiServiceWrapper.getBlockByHeight(height);
            if (block == null) {
                log.error("block with height {} not found", height);
                return;
            }
            syncBlock(block, isLib);
        } catch (Exception e) {
            log.error("no block yet", e);
        }
    }

    private void syncBlock(Block block, boolean isLib) {
        if (null == block) {
            return;
        }

        syncAddresses(Arrays.asList(block.getMiner(), block.getCoinbase()));

        NebBlock newBlock = NebBlock.builder()
                .height(block.getHeight())
                .hash(block.getHash())
                .parentHash(block.getParentHash())
                .timestamp(new Date(block.getTimestamp() * 1000))
                .miner(block.getMiner())
                .coinbase(block.getCoinbase())
                .finality(isLib)
                .createdAt(new Date(System.currentTimeMillis())).build();
        if (isLib) {
            nebBlockService.replaceNebBlock(newBlock);
            log.info("replace block, height={}, blockTimestamp={}, timestamp={}, date={}", newBlock.getHeight(), block.getTimestamp(), newBlock.getTimestamp().getTime(), newBlock.getTimestamp());
        } else {
            nebBlockService.addNebBlock(newBlock);
            log.info("add block(nebSyncService), blockTimestamp={}, height={}, timestamp={}, date={}", newBlock.getHeight(), block.getTimestamp(), newBlock.getTimestamp().getTime(), newBlock.getTimestamp());
        }

        //sync transaction
        List<Transaction> txs = block.getTransactions();
        if (isLib) {
            nebTransactionService.deleteNebTransactionByBlkHeight(block.getHeight());
        }
        int i = 0;
        for (Transaction tx : txs) {
            i++;
            try {
                syncTx(tx, block, i);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        //sync dynasty
        List<String> dynastyList = nebApiServiceWrapper.getDynasty(block.getHeight());
        nebDynastyService.batchAddNebDynasty(block.getHeight(), dynastyList);
    }

    private void syncTx(Transaction tx, Block block, int seq) {
        //sync address
        log.error("开始同步交易: " + tx.getHash());
        syncAddress(tx.getFrom(), NebAddressTypeEnum.NORMAL);

        NebTransactionTypeEnum typeEnum = NebTransactionTypeEnum.parse(tx.getType());

        if (NebTransactionTypeEnum.BINARY.equals(typeEnum)) {
            syncAddress(tx.getTo(), NebAddressTypeEnum.NORMAL);
        } else if (NebTransactionTypeEnum.CALL.equals(typeEnum)) {
            log.error("开始处理合约调用交易: " + tx.getHash());
            syncAddress(tx.getTo(), NebAddressTypeEnum.CONTRACT);
            JSONObject data = decodeData(tx.getData());
            String realReceiver = extractReceiverAddress(data);
            syncAddress(realReceiver, NebAddressTypeEnum.NORMAL);
            processContractBalanceInfo(tx, data);
        } else if (NebTransactionTypeEnum.DEPLOY.equals(typeEnum)) {
            syncAddress(tx.getContractAddress(), NebAddressTypeEnum.CONTRACT);
        }

        NebPendingTransaction nebPendingTransaction = nebTransactionService.getNebPendingTransactionByHash(tx.getHash());
        if (nebPendingTransaction != null) {
            nebTransactionService.deleteNebPendingTransaction(tx.getHash());
        }

        NebTransaction nebTxs = NebTransaction.builder()
                .hash(tx.getHash())
                .blockHeight(block.getHeight())
                .blockHash(block.getHash())
                .txSeq(seq)
                .from(tx.getFrom())
                .to(tx.getTo())
                .status(tx.getStatus())
                .value(tx.getValue())
                .nonce(tx.getNonce())
                .timestamp(new Date(block.getTimestamp() * 1000))
                .type(tx.getType())
                .contractAddress(StringUtils.isEmpty(tx.getContractAddress()) ? "" : tx.getContractAddress())
                .data(block.getHeight() == 1 ? convertData(typeEnum, tx.getData()) : tx.getData())
                .gasPrice(tx.getGasPrice())
                .gasLimit(tx.getGasLimit())
                .gasUsed(tx.getGasUsed())
                .createdAt(new Date())
                .executeError(StringUtils.isEmpty(tx.getExecuteError()) ? "" : tx.getExecuteError())
                .build();
        nebTransactionService.addNebTransaction(nebTxs);
    }

    public void syncPendingTx(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return;
        }

        NebPendingTransaction pendingNebTransaction = nebTransactionService.getNebPendingTransactionByHash(hash);
        if (pendingNebTransaction == null) {
            Transaction txSource = nebApiServiceWrapper.getTransactionReceipt(hash);

            if (txSource == null) {
                log.warn("pending tx with hash {} not ready", hash);
            } else {
                NebTransactionTypeEnum typeEnum = NebTransactionTypeEnum.parse(txSource.getType());

                syncAddress(txSource.getFrom(), NebAddressTypeEnum.NORMAL);

                if (NebTransactionTypeEnum.BINARY.equals(typeEnum)) {
                    syncAddress(txSource.getTo(), NebAddressTypeEnum.NORMAL);
                } else if (NebTransactionTypeEnum.CALL.equals(typeEnum)) {
                    syncAddress(txSource.getTo(), NebAddressTypeEnum.CONTRACT);
                    JSONObject data = decodeData(txSource.getData());
                    String realReceiver = extractReceiverAddress(data);
                    syncAddress(realReceiver, NebAddressTypeEnum.NORMAL);
                    processContractBalanceInfo(txSource, data);
                } else if (NebTransactionTypeEnum.DEPLOY.equals(typeEnum)) {
                    syncAddress(txSource.getContractAddress(), NebAddressTypeEnum.CONTRACT);
                }

                log.info("get pending tx by hash {}", hash);
                Long timestamp = String.valueOf(txSource.getTimestamp()).length() < 13 ?
                        txSource.getTimestamp() * 1000 : txSource.getTimestamp();
                NebPendingTransaction pendingTxToSave = NebPendingTransaction.builder()
                        .hash(hash)
                        .from(txSource.getFrom())
                        .to(txSource.getTo())
                        .value(txSource.getValue())
                        .nonce(txSource.getNonce())
//                        .timestamp(new Date(txSource.getTimestamp() * 1000))
                        .timestamp(new Date(timestamp))
                        .type(txSource.getType())
                        .contractAddress(StringUtils.isEmpty(txSource.getContractAddress()) ? "" : txSource.getContractAddress())
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

    public void deletePendingTx(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return;
        }
        nebTransactionService.deleteNebPendingTransaction(hash);
    }

    private void syncAddress(String hash, NebAddressTypeEnum type) {
        if (StringUtils.isEmpty(hash)) {
            return;
        }
        try {
            NebAddress addr = nebAddressService.getNebAddressByHash(hash);
            if (addr == null) {
                addr = nebAddressService.getNebAddressByHashRpc(hash);
                if (null != addr) {
                    nebAddressService.addNebAddress(addr);
                }
            }
        } catch (Throwable e) {
            log.error("add address error", e);
        }
    }

    private void syncAddresses(List<String> addresses) {
        if (CollectionUtils.isNotEmpty(addresses)) {
            for (String s : addresses) {
                syncAddress(s, NebAddressTypeEnum.NORMAL);
            }
        }
    }

    private String convertData(NebTransactionTypeEnum type, String data) {
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        if (NebTransactionTypeEnum.BINARY.equals(type)) {
            try {
                return new String(DECODER.decode(data), "UTF-8");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return data;
    }

    private String extractReceiverAddress(JSONObject jsonObject) {
        try {
            String func = jsonObject.getString("Function");

            if ("transfer".equals(func)) {
                JSONArray array = jsonObject.getJSONArray("Args");
                return array.getString(0);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    private void processContractBalanceInfo(Transaction tx, JSONObject data) {
        log.error("开始处理合约地址资产: " + data.toJSONString());
        if (!isContractTransfer(data)) {
            return;
        }
        String contract = tx.getTo();
        String sender = tx.getFrom();
        String receiver = extractReceiverAddress(data);
        syncContractBalanceAddress(sender, contract);
        syncContractBalanceAddress(receiver, contract);
    }

    private void syncContractBalanceAddress(String address, String contract) {
        try {
            NebContractTokenBalance addressBalance = contractTokenBalanceService.getByAddressAndContract(address, contract);
            if (addressBalance == null) {
                addressBalance = contractTokenBalanceService.getFromRPC(address, contract);
                if (addressBalance != null) {
                    contractTokenBalanceService.addAddressBalance(addressBalance);
                }
                log.error("从RPC获取合约地址资产: " + addressBalance.getBalance());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private boolean isContractTransfer(JSONObject data) {
        String func = data.getString("Function");
        return "transfer".equals(func);
    }

    private JSONObject decodeData(String data) {
        try {
            String dataStr = new String(DECODER.decode(data), "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(dataStr);
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new JSONObject();
    }

}
