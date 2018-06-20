package io.nebulas.explorer.task;

import com.google.common.collect.Lists;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.domain.BlockSyncRecord;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.jobs.SysSubscribeService;
import io.nebulas.explorer.model.Zone;
import io.nebulas.explorer.service.blockchain.BlockSyncRecordService;
import io.nebulas.explorer.service.blockchain.NebBlockService;
import io.nebulas.explorer.service.blockchain.NebSyncService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.NebState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
    private final ThreadPoolTaskExecutor executor;
    private final YAMLConfig myConfig;
    private final SysSubscribeService sysSubscribeService;
    //indicate if the init task done.
    private static boolean isDone = false;

    @PostConstruct
    public void init() {
        try {
            boolean isSync = myConfig.getSync().isOpen();
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
        } finally {
            isDone = true;
            sysSubscribeService.init(myConfig.getSync().isSubscribe());
        }
    }

    private void populateZones(List<Zone> zones) {
        if (zones.size() > 0) {
            log.info("zones {}", zones);
            List<CompletableFuture<Void>> taskList = Lists.newArrayList();
            for (Zone zone : zones) {
                taskList.add(CompletableFuture.runAsync(() -> {
                    populate(zone.getFrom(), zone.getTo());
                }, executor));
            }
            //waiting for all tasks done.
            taskList.stream().map(CompletableFuture::join).collect(Collectors.toList());
            log.info("DataInitTask is done");
        }
    }

    private void populate(long from, long to) {
        long threadId = Thread.currentThread().getId();
        log.info("Thread {} start populating", threadId);

        long start = System.currentTimeMillis();
        Block latestLibBlk = nebApiServiceWrapper.getLatestLibBlock();
        log.info("get latestIrreversibleBlk height={}", latestLibBlk.getHeight());

        for (long h = from; h < to; h++) {
            blockSyncRecordService.add(new BlockSyncRecord(h));

            try {
                boolean isLib = h <= latestLibBlk.getHeight();

                NebBlock nebBlock = nebBlockService.getNebBlockByHeight(h);
                if (null != nebBlock && (nebBlock.getFinality() || !isLib)) {
                    log.warn("block with height {} already existed", h);
                    continue;
                }

                nebSyncService.syncBlockByHeight(h, isLib);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        log.info("Thread {}: {} millis elapsed for populating", threadId, System.currentTimeMillis() - start);
    }

    private List<Zone> divideZones(long from, long to) {
        if (from > to) {
            return new ArrayList<>(0);
        }
        final long total = to - from + 1;

        final Long zoneThreshold = total / (Runtime.getRuntime().availableProcessors() * 4);

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

    public static boolean isDone() {
        return isDone;
    }
}
