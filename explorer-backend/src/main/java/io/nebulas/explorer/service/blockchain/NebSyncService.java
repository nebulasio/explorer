package io.nebulas.explorer.service.blockchain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebPendingTransaction;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.enums.NebTransactionTypeEnum;
import io.nebulas.explorer.grpc.GrpcChannelService;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import io.nebulas.explorer.util.IdGenerator;
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
    private NebulasApiService nebulasApiService;

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    public void syncBlockByHash(String hash, boolean isLib) {
        try {
            Block block = nebulasApiService.getBlockByHash(new GetBlockByHashRequest(hash, true)).toBlocking().first();
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
            Block block = nebulasApiService.getBlockByHeight(new GetBlockByHeightRequest(height, true)).toBlocking().first();
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
                .id(IdGenerator.getId())
                .height(block.getHeight())
                .hash(block.getHash())
                .parentHash(block.getParentHash())
                .timestamp(new Date(block.getTimestamp() * 1000))
                .miner(block.getMiner())
                .coinbase(block.getCoinbase())
                .nonce(block.getNonce())
                .finality(isLib)
                .createdAt(new Date(System.currentTimeMillis())).build();
        if (isLib) {
            nebBlockService.replaceNebBlock(newBlock);
        } else {
            nebBlockService.addNebBlock(newBlock);
        }

        //sync transaction
        List<Transaction> txs = block.getTransactions();
        if (isLib) {
            nebTransactionService.deleteNebTransactionByBlkHeight(block.getHeight());
        }
        for (Transaction tx : txs) {
            try {
                syncTx(tx, block.getHeight(), block.getHash());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        //sync dynasty
        GetDynastyResponse dynastyResponse = nebulasApiService.getDynasty(new GetDynastyRequest(block.getHeight())).toBlocking().first();
        nebDynastyService.batchAddNebDynasty(block.getHeight(), dynastyResponse.getDelegatees());
    }

    private void syncTx(Transaction tx, long blkHeight, String blkHash) {
        //sync address
        syncAddress(tx.getFrom(), NebAddressTypeEnum.NORMAL);

        if (NebTransactionTypeEnum.BINARY.getDesc().equals(tx.getType())) {
            syncAddress(tx.getTo(), NebAddressTypeEnum.NORMAL);
        } else if (NebTransactionTypeEnum.CALL.getDesc().equals(tx.getType())) {
            syncAddress(tx.getTo(), NebAddressTypeEnum.CONTRACT);
            String realReceiver = extractReceiverAddress(tx.getData());
            syncAddress(realReceiver, NebAddressTypeEnum.NORMAL);
        } else if (NebTransactionTypeEnum.DEPLOY.getDesc().equals(tx.getType())) {
            syncAddress(tx.getContractAddress(), NebAddressTypeEnum.NORMAL);
        }

        NebPendingTransaction nebPendingTransaction = nebTransactionService.getNebPendingTransactionByHash(tx.getHash());
        if (nebPendingTransaction != null) {
            nebTransactionService.deleteNebPendingTransaction(nebPendingTransaction.getId());
        }

        NebTransaction nebTxs = NebTransaction.builder()
                .id(IdGenerator.getId())
                .hash(tx.getHash())
                .blockHeight(blkHeight)
                .blockHash(blkHash)
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
                .gasUsed(tx.getGasUsed())
                .createdAt(new Date())
                .build();
        nebTransactionService.addNebTransaction(nebTxs);
    }

    public void syncPendingTx(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return;
        }

        NebPendingTransaction pendingNebTransaction = nebTransactionService.getNebPendingTransactionByHash(hash);
        if (pendingNebTransaction == null) {
            Transaction txSource;
            try {
                txSource = nebulasApiService.getTransactionReceipt(new GetTransactionReceiptRequest(hash)).execute().body();
            } catch (Exception e) {
                log.error("get tx by hash error, skipped pending tx hash " + hash, e);
                return;
            }

            if (txSource == null) {
                log.warn("pending tx with hash {} not ready", hash);
            } else {
                syncAddress(txSource.getFrom(), NebAddressTypeEnum.NORMAL);
                if (NebTransactionTypeEnum.BINARY.getDesc().equals(txSource.getType())) {
                    syncAddress(txSource.getTo(), NebAddressTypeEnum.NORMAL);
                } else if (NebTransactionTypeEnum.CALL.getDesc().equals(txSource.getType())) {
                    syncAddress(txSource.getTo(), NebAddressTypeEnum.CONTRACT);
                    String realReceiver = extractReceiverAddress(txSource.getData());
                    syncAddress(realReceiver, NebAddressTypeEnum.NORMAL);
                } else if (NebTransactionTypeEnum.DEPLOY.getDesc().equals(txSource.getType())) {
                    syncAddress(txSource.getContractAddress(), NebAddressTypeEnum.NORMAL);
                }

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

    private void syncAddress(String hash, NebAddressTypeEnum type) {
        NebAddress addr = nebAddressService.getNebAddressByHash(hash);
        if (addr == null) {
            try {
                nebAddressService.addNebAddress(hash, type.getValue());
            } catch (Throwable e) {
                log.error("add address error", e);
            }
        }
    }

    private void syncAddresses(List<String> addresses) {
        if (CollectionUtils.isNotEmpty(addresses)) {
            for (String s : addresses) {
                syncAddress(s, NebAddressTypeEnum.NORMAL);
            }
        }
    }

    private String extractReceiverAddress(String data) {
        try {
            String dataStr = new String(DECODER.decode(data), "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(dataStr);
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
}
