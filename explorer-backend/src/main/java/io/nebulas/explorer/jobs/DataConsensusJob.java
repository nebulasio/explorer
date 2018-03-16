package io.nebulas.explorer.jobs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.nebulas.explorer.domain.BlockSyncRecord;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.enums.NebAddressTypeEnum;
import io.nebulas.explorer.enums.NebTransactionTypeEnum;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.service.blockchain.*;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import io.nebulas.explorer.util.BlockHelper;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Data check Job
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Slf4j(topic = "data_init_log")
@AllArgsConstructor
@Component
public class DataConsensusJob {
    private final NebBlockService nebBlockService;
    private final NebAddressService nebAddressService;
    private final BlockSyncRecordService blockSyncRecordService;
    private final NebDynastyService nebDynastyService;
    private final NebTransactionService nebTransactionService;
    private final NebulasApiService nebulasApiService;
    private final StringRedisTemplate redisTemplate;

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    @Scheduled(cron = "0 0/2 * * * ?")
    public void check() {
        String key = getHashKey();
        String running = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(running)) {
            log.warn("DataConsensusJob is running");
            return;
        }

        NebState nebState = nebulasApiService.getNebState().toBlocking().first();
        if (nebState == null) {
            log.error("neb state not found");
            return;
        }
        log.info("neb state: {}", toJSONString(nebState));

        Block block = nebulasApiService.getBlockByHash(new GetBlockByHashRequest(nebState.getTail(), false)).toBlocking().first();
        if (block == null) {
            log.error("block by hash {} not found", nebState.getTail());
            return;
        }
        log.info("top block: {}", toJSONString(block));

        Long lastConfirmHeight = blockSyncRecordService.getMaxConfirmedBlockHeight();
        if (lastConfirmHeight < block.getHeight()) {
            try {
                redisTemplate.opsForValue().set(key, "running");
                redisTemplate.opsForValue().getOperations().expire(key, 5, TimeUnit.MINUTES);

                Block latestIrreversibleBlk = nebulasApiService.getLatestIrreversibleBlock().toBlocking().first();

                long end = block.getHeight() - lastConfirmHeight > 2000 ? lastConfirmHeight + 2000 : block.getHeight();

                for (long i = lastConfirmHeight + 1; i <= end; i++) {
                    BlockSyncRecord record = new BlockSyncRecord();
                    record.setBlockHeight(i);
                    record.setTxCnt(0L);
                    record.setConfirm(0L);
                    blockSyncRecordService.add(record);

                    Block blk = nebulasApiService.getBlockByHeight(new GetBlockByHeightRequest(i, true)).toBlocking().first();
                    if (blk != null) {
                        boolean isOk = true;
                        NebBlock nebBlock = nebBlockService.getNebBlockByHeight(blk.getHeight());
                        if (null == nebBlock) {
                            saveBlock(blk, latestIrreversibleBlk.getHeight());
                            isOk = false;
                        }

                        long txCnt = nebTransactionService.countTxnCntByBlockHeight(blk.getHeight());
                        if (txCnt < blk.getTransactions().size()) {
                            for (Transaction tx : blk.getTransactions()) {
                                addAddr(tx.getFrom(), NebAddressTypeEnum.NORMAL);

                                if (NebTransactionTypeEnum.BINARY.getDesc().equals(tx.getType())) {
                                    addAddr(tx.getTo(), NebAddressTypeEnum.NORMAL);
                                } else if (NebTransactionTypeEnum.CALL.getDesc().equals(tx.getType())) {
                                    addAddr(tx.getTo(), NebAddressTypeEnum.CONTRACT);
                                    String realReceiver = extractReceiverAddress(tx.getData());
                                    addAddr(realReceiver, NebAddressTypeEnum.NORMAL);
                                } else if (NebTransactionTypeEnum.DEPLOY.getDesc().equals(tx.getType())) {
                                    addAddr(tx.getContractAddress(), NebAddressTypeEnum.NORMAL);
                                }

                                NebTransaction nebTx = BlockHelper.buildNebTransaction(tx, blk);
                                if (StringUtils.isEmpty(nebTx.getGasUsed())) {
                                    String gasUsed = null;
                                    try {
                                        GetGasUsedResponse gasUsedResponse = nebulasApiService.getGasUsed(new GetGasUsedRequest(tx.getHash())).toBlocking().first();
                                        gasUsed = gasUsedResponse.getGas();
                                    } catch (Exception e) {
                                        log.error("get gas used by tx hash error", e);
                                        continue;
                                    }
                                    if (gasUsed != null) {
                                        nebTx.setGasUsed(gasUsed);
                                        log.info("tx hash {} gas used: {} ", tx.getHash(), gasUsed);
                                    } else {
                                        nebTx.setGasUsed("");
                                        log.warn("gas used not found for tx hash {}", tx.getHash());
                                    }
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
                redisTemplate.opsForValue().getOperations().delete(key);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }
    }

    private void saveBlock(Block blk, Long libBlkHeight) {
        NebBlock nebBlk = NebBlock.builder()
                .id(IdGenerator.getId())
                .height(blk.getHeight())
                .hash(blk.getHash())
                .parentHash(blk.getParentHash())
                .timestamp(new Date(blk.getTimestamp() * 1000))
                .miner(blk.getMiner())
                .coinbase(blk.getCoinbase())
                .nonce(blk.getNonce())
                .finality(blk.getHeight() <= libBlkHeight)
                .build();

        addAddr(blk.getMiner(), NebAddressTypeEnum.NORMAL);
        addAddr(blk.getCoinbase(), NebAddressTypeEnum.NORMAL);

        NebBlock nblk = nebBlockService.getNebBlockByHash(nebBlk.getHash());
        if (nblk == null) {
            nebBlockService.addNebBlock(nebBlk);
            log.info("save block, height={}", nebBlk.getHeight());
        } else {
            log.warn("duplicate block hash {}", nebBlk.getHash());
        }

        GetDynastyResponse dynastyResponse = nebulasApiService.getDynasty(new GetDynastyRequest(blk.getHeight())).toBlocking().first();
        nebDynastyService.batchAddNebDynasty(blk.getHeight(), dynastyResponse.getDelegatees());
    }

    private void addAddr(String hash, NebAddressTypeEnum type) {
        NebAddress addr = nebAddressService.getNebAddressByHash(hash);
        if (addr == null) {
            try {
                nebAddressService.addNebAddress(hash, type.getValue());
            } catch (Throwable e) {
                log.error("add address error", e);
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

    private String getHashKey() {
        return "explorer:data_consensus_job:running";
    }

}
