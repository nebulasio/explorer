package io.nebulas.explorer.jobs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.domain.BlockSyncRecord;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.enums.NebTransactionTypeEnum;
import io.nebulas.explorer.service.blockchain.BlockSyncRecordService;
import io.nebulas.explorer.service.blockchain.NebAddressService;
import io.nebulas.explorer.service.blockchain.NebBlockService;
import io.nebulas.explorer.service.blockchain.NebDynastyService;
import io.nebulas.explorer.service.blockchain.NebSyncService;
import io.nebulas.explorer.service.blockchain.NebTransactionService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.NebState;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
import io.nebulas.explorer.task.DataInitTask;
import io.nebulas.explorer.util.BlockHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Data check Job
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Slf4j(topic = "subscribe")
@AllArgsConstructor
@Component
public class DataConsensusJob {
    private final NebBlockService nebBlockService;
    private final NebAddressService nebAddressService;
    private final BlockSyncRecordService blockSyncRecordService;
    private final NebDynastyService nebDynastyService;
    private final NebTransactionService nebTransactionService;
    private final NebApiServiceWrapper nebApiServiceWrapper;
    private final StringRedisTemplate redisTemplate;
    private final YAMLConfig myConfig;
    private final ThreadPoolTaskExecutor executor;
    private final NebSyncService nebSyncService;

    private static final Base64.Decoder DECODER = Base64.getDecoder();


    @Scheduled(cron = "0 0/2 * * * ?")
    public void check() {

        if (!DataInitTask.isDone()) {
            log.warn("DataInitTask is running, waiting for it to be done.");
            return;
        }

        String key = getHashKey();
        String running = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(running)) {
            log.warn("DataConsensusJob is running");
            return;
        }

        log.info("DataConsensusJob starting to run");

        Long lastConfirmHeight = blockSyncRecordService.getMaxConfirmedBlockHeight();
        try {

            List<BlockSyncRecord> recordList = blockSyncRecordService.findUnConfirmed(lastConfirmHeight);
            if (recordList.size() > 0) {
                List<CompletableFuture<Void>> taskList = Lists.newArrayList();
                Block latestLibBlk = nebApiServiceWrapper.getLatestLibBlock();
                recordList.forEach(r -> {
                    taskList.add(CompletableFuture.runAsync(() -> {
                        syncBlock(r.getBlockHeight(), latestLibBlk);
                    }, executor));
                });

                //waiting for all the tasks done
                taskList.stream().map(CompletableFuture::join).collect(Collectors.toList());
            }

            NebState nebState = nebApiServiceWrapper.getNebState();
            if (nebState == null) {
                log.error("neb state not found");
                return;
            }
            log.info("neb state: {}", toJSONString(nebState));

            Long maxBlockHeight = lastConfirmHeight;
            Block block = nebApiServiceWrapper.getBlockByHash(nebState.getTail(), false);
            if (block == null) {
                log.error("block by hash {} not found", nebState.getTail());
                maxBlockHeight = blockSyncRecordService.getMaxBlockHeight();
            } else {
                log.info("top block: {}", toJSONString(block));
                maxBlockHeight = block.getHeight();
            }


            if (lastConfirmHeight < maxBlockHeight) {
                try {
                    redisTemplate.opsForValue().set(key, "running");

                    final Block latestLibBlk = nebApiServiceWrapper.getLatestLibBlock();

                    long end = maxBlockHeight - lastConfirmHeight > 2000 ? lastConfirmHeight + 2000 : maxBlockHeight;
                    List<CompletableFuture<Void>> taskList = Lists.newArrayList();
                    for (long i = lastConfirmHeight + 1; i <= end; i++) {
                        BlockSyncRecord record = new BlockSyncRecord();
                        record.setBlockHeight(i);
                        record.setTxCnt(0L);
                        record.setConfirm(0L);
                        blockSyncRecordService.add(record);
                        final long loop = i;
                        taskList.add(CompletableFuture.runAsync(() -> {
                            syncBlock(loop, latestLibBlk);
                        }, executor));
                    }

                    //waiting for all the tasks done
                    taskList.stream().map(CompletableFuture::join).collect(Collectors.toList());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        } finally {
            redisTemplate.opsForValue().getOperations().delete(key);
            log.info("DataConsensusJob is done.");
        }

    }

    /**
     * clear the running key in case the program terminates abnormally
     */
    @PreDestroy
    public void destroy() {
        String key = getHashKey();
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    private void syncBlock(long i, Block latestIrreversibleBlk) {
        Block blk = nebApiServiceWrapper.getBlockByHeight(i);
        if (blk != null) {
            boolean isOk = true;
            NebBlock nebBlock = nebBlockService.getNebBlockByHeight(blk.getHeight());
            if (null == nebBlock) {
                saveBlock(blk, latestIrreversibleBlk.getHeight());
                isOk = false;
            }

            long txCnt = nebTransactionService.countTxnCntByBlockHeight(blk.getHeight());
            if (txCnt < blk.getTransactions().size()) {
                nebTransactionService.deleteNebTransactionByBlkHeight(blk.getHeight());
                int seq = 0;
                for (Transaction tx : blk.getTransactions()) {
                    seq++;
                    addAddr(tx.getFrom());

                    NebTransactionTypeEnum typeEnum = NebTransactionTypeEnum.parse(tx.getType());

                    if (NebTransactionTypeEnum.BINARY.equals(typeEnum)) {
                        addAddr(tx.getTo());
                    } else if (NebTransactionTypeEnum.CALL.equals(typeEnum)) {
                        addAddr(tx.getTo());
                        String realReceiver = extractReceiverAddress(tx.getData());
                        addAddr(realReceiver);
                    } else if (NebTransactionTypeEnum.DEPLOY.equals(typeEnum)) {
                        addAddr(tx.getContractAddress());
                    }

                    NebTransaction nebTx = BlockHelper.buildNebTransaction(tx, blk, seq, convertData(typeEnum, tx.getData()));
                    if (StringUtils.isEmpty(nebTx.getGasUsed())) {
                        nebTx.setGasUsed("");
                        log.warn("gas used not found for tx hash {}", tx.getHash());
                    }
                    nebTransactionService.addNebTransaction(nebTx);
                    log.info("save tx={}", tx.getHash());
                }
                isOk = false;
            }

            if (isOk) {
                blockSyncRecordService.setConfirmed(blk.getHeight(), (long) blk.getTransactions().size());
            }
        }
    }

    private void saveBlock(Block blk, Long libBlkHeight) {
        NebBlock nebBlk = NebBlock.builder()
                .height(blk.getHeight())
                .hash(blk.getHash())
                .parentHash(blk.getParentHash())
                .timestamp(new Date(blk.getTimestamp() * 1000))
                .miner(blk.getMiner())
                .coinbase(blk.getCoinbase())
                .finality(blk.getHeight() <= libBlkHeight)
                .build();

        addAddr(blk.getMiner());
        addAddr(blk.getCoinbase());

        NebBlock nblk = nebBlockService.getNebBlockByHash(nebBlk.getHash());
        if (nblk == null) {
            nebBlockService.addNebBlock(nebBlk);
            log.info("save block, height={}", nebBlk.getHeight());
        } else {
            log.warn("duplicate block hash {}", nebBlk.getHash());
        }

        List<String> dynastyList = nebApiServiceWrapper.getDynasty(blk.getHeight());
        nebDynastyService.batchAddNebDynasty(blk.getHeight(), dynastyList);
    }

    private void addAddr(String hash) {
        try {
            NebAddress addr = nebAddressService.getNebAddressByHash(hash);
            if (addr == null) {
                addr = nebAddressService.getNebAddressByHashRpc(hash);
                if (null != addr) {
                    nebAddressService.addNebAddress(addr);
                }
            }
            nebSyncService.syncBalance(hash);
        } catch (Throwable e) {
            log.error("add address error", e);
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

    private String getHashKey() {
        return String.format("explorer:%s:data_consensus_job:running", myConfig.getEnvironment());
    }

}
