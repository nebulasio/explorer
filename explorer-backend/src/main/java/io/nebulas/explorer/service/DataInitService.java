package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.BlockSyncRecord;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.model.Zone;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-26
 */
@Slf4j
@AllArgsConstructor
@Service
public class DataInitService {
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebAddressService nebAddressService;
    private final NebDynastyService nebDynastyService;
    private final BlockSyncRecordService blockSyncRecordService;
    private final NebulasApiService nebulasApiService;


    public void init(boolean isSync) {
        if (!isSync) {
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

        final Long goalHeight = block.getHeight();
        final Long lastConfirmHeight = blockSyncRecordService.getMaxConfirmedBlockHeight();
        List<Zone> zoneList = divideZones(lastConfirmHeight, goalHeight);
        populateZones(zoneList);
    }

    private void populateZones(List<Zone> zones) {
        if (zones.size() > 0) {
            log.info("zones {}", zones);
            ExecutorService executor = Executors.newFixedThreadPool(1);
            for (Zone zone : zones) {
                executor.execute(() -> {
                    populate(zone.getFrom(), zone.getTo());
                });
            }
        }
    }

    private void populate(long from, long to) {
        long threadId = Thread.currentThread().getId();
        log.info("Thread {} start populating", threadId);

        long start = System.currentTimeMillis();
        Block latestIrreversibleBlk = nebulasApiService.getLatestIrreversibleBlock().toBlocking().first();
        log.info("get latestIrreversibleBlk height={}", latestIrreversibleBlk.getHeight());

        for (long h = from; h <= to; ) {
            BlockSyncRecord record = new BlockSyncRecord();
            record.setBlockHeight(h);
            record.setTxCnt(0L);
            record.setConfirm(0L);
            blockSyncRecordService.add(record);

            NebBlock nebBlock = nebBlockService.getNebBlockByHeight(h);
            if (nebBlock != null) {
                h++;
                log.info("block exist, height={}", nebBlock.getHeight());
                continue;
            }

            try {
                Block blk = nebulasApiService.getBlockByHeight(new GetBlockByHeightRequest(h, true)).toBlocking().first();
                if (blk == null) {
                    log.error("block with height {} not found", h);
                    h++;
                    continue;
                }

                NebBlock nebBlk = NebBlock.builder()
                        .id(IdGenerator.getId())
                        .height(blk.getHeight())
                        .hash(blk.getHash())
                        .parentHash(blk.getParentHash())
                        .timestamp(new Date(blk.getTimestamp() * 1000))
                        .miner(blk.getMiner())
                        .coinbase(blk.getCoinbase())
                        .nonce(blk.getNonce())
                        .finality(blk.getHeight() <= latestIrreversibleBlk.getHeight())
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

                //todo add address

                List<Transaction> txs = blk.getTransactions();
                log.info("get txs {}", txs.size());
                for (int tpos = 0; tpos < txs.size(); ) {
                    Transaction tx = txs.get(tpos);
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

                    NebTransaction nebTxs = NebTransaction.builder()
                            .id(IdGenerator.getId())
                            .hash(tx.getHash())
                            .blockHeight(blk.getHeight())
                            .blockHash(blk.getHash())
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
                    log.info("save tx={} tpos={}", tx.getHash(), tpos);
                    tpos++;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            h++;
        }
        log.info("Thread {}: {} millis elapsed for populating", threadId, System.currentTimeMillis() - start);
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

    private List<Zone> divideZones(long from, long to) {
        if (from >= to) {
            return new ArrayList<>(0);
        }
        final long total = to - from + 1;

        final Long zoneThreshold = 20000L;

        if (total < zoneThreshold) {
            return Arrays.asList(new Zone(from, to));
        }

        long zoneSize = total / zoneThreshold + 1;
        List<Zone> zoneList = new ArrayList<>((int) zoneSize);
        for (int i = 1; i <= zoneSize; i++) {
            from = (i - 1) * zoneThreshold + 1;
            long end = from + zoneThreshold;
            if (end > to) {
                end = to;
            }
            zoneList.add(new Zone(from, end));
        }
        return zoneList;
    }
}
