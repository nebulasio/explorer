package io.nebulas.explorer.jobs;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
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

import java.util.Date;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Data check Job
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Slf4j
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
                Block latestIrreversibleBlk = nebulasApiService.getLatestIrreversibleBlock().toBlocking().first();

                for (long i = lastConfirmHeight + 1; i <= block.getHeight(); i++) {
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
                                final int type = StringUtils.isBlank(tx.getContractAddress()) ? 0 : 1;
                                addAddr(tx.getFrom(), type);
                                addAddr(tx.getTo(), type);
                                String gasUsed;
                                try {
                                    GetGasUsedResponse gasUsedResponse = nebulasApiService.getGasUsed(new GetGasUsedRequest(tx.getHash())).toBlocking().first();
                                    gasUsed = gasUsedResponse.getGas();
                                } catch (Exception e) {
                                    log.error("get gas used by tx hash error", e);
                                    continue;
                                }
                                if (gasUsed != null) {
                                    log.info("tx hash {} gas used: {} ", tx.getHash(), gasUsed);
                                } else {
                                    log.warn("gas used not found for tx hash {}", tx.getHash());
                                }

                                nebTransactionService.addNebTransaction(BlockHelper.buildNebTransaction(tx, blk, gasUsed));
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

        addAddr(blk.getMiner(), 0);
        addAddr(blk.getCoinbase(), 0);

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

    private String getHashKey() {
        return "explorer:data_consensus_job:running";
    }

}
