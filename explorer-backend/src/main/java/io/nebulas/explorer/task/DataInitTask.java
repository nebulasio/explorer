package io.nebulas.explorer.task;

import io.nebulas.explorer.domain.BlockSyncRecord;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.model.Zone;
import io.nebulas.explorer.service.blockchain.BlockSyncRecordService;
import io.nebulas.explorer.service.blockchain.NebBlockService;
import io.nebulas.explorer.service.blockchain.NebSyncService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.NebState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-26
 */
@Slf4j(topic = "subscribe")
@AllArgsConstructor
@Service
public class DataInitTask {
    private final NebBlockService nebBlockService;
    private final BlockSyncRecordService blockSyncRecordService;
    private final NebApiServiceWrapper nebApiServiceWrapper;
    private final NebSyncService nebSyncService;

    public void init(boolean isSync) {
        if (!isSync) {
            return;
        }
        NebState nebState = nebApiServiceWrapper.getNebState();
        if (nebState == null) {
            log.error("neb state not found");
            return;
        }
        log.info("neb state: {}", toJSONString(nebState));

        Block block = nebApiServiceWrapper.getBlockByHash(nebState.getTail(), false);
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
        Block latestLibBlk = nebApiServiceWrapper.getLatestLibBlock();
        log.info("get latestIrreversibleBlk height={}", latestLibBlk.getHeight());

        for (long h = from; h <= to; ) {
            blockSyncRecordService.add(new BlockSyncRecord(h));

            try {
                boolean isLib = h <= latestLibBlk.getHeight();

                NebBlock nebBlock = nebBlockService.getNebBlockByHeight(h);
                if (null != nebBlock && (nebBlock.getFinality() || !isLib)) {
                    log.warn("block with height {} already existed", h);
                    return;
                }

                nebSyncService.syncBlockByHeight(h, isLib);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

            h++;
        }
        log.info("Thread {}: {} millis elapsed for populating", threadId, System.currentTimeMillis() - start);
    }

    private List<Zone> divideZones(long from, long to) {
        if (from > to) {
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
            long end = from + zoneThreshold;
            if (end > to) {
                end = to;
            }
            zoneList.add(new Zone(from, end));
            from = end;
        }
        return zoneList;
    }
}
