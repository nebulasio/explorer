package io.nebulas.explorer.service;

import io.grpc.StatusRuntimeException;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.grpc.GrpcChannelService;
import io.nebulas.explorer.grpc.GrpcClientService;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.model.Zone;
import io.nebulas.explorer.service.PopulationMonitor;
import io.nebulas.explorer.service.blockchain.NebAddressService;
import io.nebulas.explorer.service.blockchain.NebBlockService;
import io.nebulas.explorer.service.blockchain.NebDynastyService;
import io.nebulas.explorer.service.blockchain.NebTransactionService;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-24
 */
@Deprecated
@Slf4j
@AllArgsConstructor
@Service
public class SysService {
    private final GrpcChannelService grpcChannelService;
    private final GrpcClientService grpcClientService;
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebAddressService nebAddressService;
    private final NebDynastyService nebDynastyService;
    private final PopulationMonitor populationMonitor;
    //    private final ZoneCache zoneCache;
    private final YAMLConfig yamlConfig;

    public void init(boolean isSync, boolean isSubscribe) {
        log.info("sys init starting...");
        do {
            final long start = System.currentTimeMillis();
            try {
                if (isSync) {
//                    long blockCount = nebBlockService.countBlockCnt();
//                    if (blockCount > 0) {
//                        List<Zone> fragments = getFragments();
//                        if (fragments.isEmpty()) {
//                            log.info("no fragments");
//                        } else {
//                            log.info("fragments to tidy: {}", fragments.toString());
//                        }
//                        populateZones(fragments);
//                    } else {
//                    zoneCache.deleteAll();
                    populationMonitor.deleteAll();
//                    }

                    NebState nebState = grpcClientService.getNebState();
                    if (nebState == null) {
                        log.error("neb state not found");
                        return;
                    }
                    log.info("neb state: {}", toJSONString(nebState));
                    Block block = grpcClientService.getBlockByHash(nebState.getTail(), true);
                    if (block == null) {
                        log.error("block by hash {} not found", nebState.getTail());
                        return;
                    }
                    log.info("top block: {}", toJSONString(block));

                    final Long goalHeight = block.getHeight();
                    final Long lastHeightO = nebBlockService.getMaxHeight();
//                    long lastHeight = lastHeightO == null ? 0L : lastHeightO;
                    long lastHeight = 460730L;

                    List<Zone> zones = divideZones(lastHeight, goalHeight);
                    populateZones(zones);
                }
                if (isSubscribe) {
                    grpcClientService.subscribe();
                }
                long elapsed = System.currentTimeMillis() - start;
                log.info("{} millis elapsed", elapsed);
                log.info("sys init end");
                break;
            } catch (StatusRuntimeException e) {
                String errorMessage = e.getMessage();
                log.error(errorMessage);
                reConnect();
            } catch (UnsupportedEncodingException e) {
                log.error("encoding error", e);
                break;
            } catch (Throwable e) {
                log.error("other error", e);
                break;
            }
        } while (true);
    }

    private void populateZones(List<Zone> zones) {
        if (zones.size() > 0) {
            log.info("zones {}", zones);
            ExecutorService executor = Executors.newFixedThreadPool(1);
            for (Zone zone : zones) {
                executor.execute(() -> {
                    // spin loop until success
                    try {
                        populate(zone.getFrom(), zone.getTo());
                    } catch (UnsupportedEncodingException e) {
                        log.error("encoding error", e);
                    }
                });
            }
        }
    }

    public void populate(long from, long to) throws UnsupportedEncodingException {
        long threadId = Thread.currentThread().getId();
        log.info("Thread {} start populating", threadId);
        long start = System.currentTimeMillis();

        Block latestIrreversibleBlk = null;
        try {
            latestIrreversibleBlk = grpcClientService.getLatestIrreversibleBlock();
            log.info("get latestIrreversibleBlk height={}", latestIrreversibleBlk.getHeight());
        } catch (StatusRuntimeException e) {
            log.error("get block by height error", e);
            String errorMessage = e.getMessage();
            log.error(errorMessage);
            reConnect();
        }

        long h;
        for (h = from; h <= to; ) {
            NebBlock nebBlock = nebBlockService.getNebBlockByHeight(h);
            if (nebBlock != null) {
//                zoneCache.addCursor(from, to, h);
                h++;
                log.info("block exist, height={}", nebBlock.getHeight());
                continue;
            }
            Block blk;
            try {
                blk = grpcClientService.getBlockByHeight(h, true);
            } catch (StatusRuntimeException e) {
                log.error("get block by height error", e);
                String errorMessage = e.getMessage();
                log.error(errorMessage);
                reConnect();
                // continue loop on this pos
                continue;
            }

            if (blk == null) {
                log.error("block with height {} not found", h);
//                zoneCache.addCursor(from, to, h);
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
                    .finality(latestIrreversibleBlk != null && blk.getHeight() <= latestIrreversibleBlk.getHeight())
                    .build();

            try {
                addAddr(blk.getMiner(), 0);
                addAddr(blk.getCoinbase(), 0);
                NebBlock nblk = nebBlockService.getNebBlockByHash(nebBlk.getHash());
                if (nblk == null) {
                    nebBlockService.addNebBlock(nebBlk);
                    log.info("save block, height={}", nebBlk.getHeight());
                } else {
                    log.warn("duplicate block hash {}", nebBlk.getHash());
                }
            } catch (Throwable e) {
                log.error("add neb block error, ignoring....", e);
//                zoneCache.addCursor(from, to, h);
                h++;
                continue;
            }

            List<String> dynastyDelegateList = grpcClientService.getDynasty(blk.getHeight());
            nebDynastyService.batchAddNebDynasty(blk.getHeight(), dynastyDelegateList);

            List<Transaction> txs = blk.getTransactions();
            log.info("get txs {}", txs.size());
            for (int tpos = 0; tpos < txs.size(); ) {
                Transaction tx = txs.get(tpos);
                final int type = StringUtils.isBlank(tx.getContractAddress()) ? 0 : 1;
                addAddr(tx.getFrom(), type);
                addAddr(tx.getTo(), type);
                String gasUsed;
                try {
                    gasUsed = grpcClientService.getGasUsed(tx.getHash());
                } catch (StatusRuntimeException e) {
                    log.error("get gas used by tx hash error", e);
                    String errorMessage = e.getMessage();
                    log.error(errorMessage);
                    reConnect();
                    // continue loop on this pos
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
                log.info("save tx={} tpos={}",tx.getHash(),tpos);
                tpos++;
            }
//            zoneCache.addCursor(from, to, h);
            h++;

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }
        if (h > to) {
            populationMonitor.delete(from, to);
        }
        long elapsed = System.currentTimeMillis() - start;
        log.info("Thread {}: {} millis elapsed for populating", threadId, elapsed);
    }

    private void reConnect() {
        try {
            grpcChannelService.renewChannel();
            // sleep for 10 seconds to enter next retry
            log.info("entering next retry after 10 seconds ....");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e1) {
            log.error("thread sleep interrupted", e1);
        }
    }

    synchronized private void addAddr(String hash, int type) {
        NebAddress addr = nebAddressService.getNebAddressByHash(hash);
        if (addr == null) {
            try {
                nebAddressService.addNebAddress(hash, type);
            } catch (Throwable e) {
                log.error("add address error", e);
            }
        }
    }

    private List<Zone> getFragments() {
        Map<String, Long> zk = populationMonitor.getAll();
        if (zk.isEmpty()) {
            return new ArrayList<>(0);
        }
        List<Zone> fragments = new ArrayList<>(zk.size());
        for (String hk : zk.keySet()) {
            String[] parts = hk.split("_");
            long to = Long.parseLong(parts[2]);
            long cur = zk.get(hk);
            if (cur >= to) {
                continue;
            }
            fragments.add(new Zone(cur, to));
        }

        return fragments;
    }

    /**
     * Divide into separated zones
     * <p>
     * 1. Zone list size should never exceed cpu threshold
     * 2. Each zone size should never exceed zone threshold
     * 3. If the above two principle can NOT meet together, consider cpu threshold as priority
     *
     * @param from
     * @param to
     * @return zones
     */
    private List<Zone> divideZones(long from, long to) {
        if (from >= to) {
            return new ArrayList<>(0);
        }
        final long total = to - from;
        final Integer cpuThreshold = yamlConfig.getSync().getCpu();
        final Long zoneThreshold = yamlConfig.getSync().getZone();

        final long zoneLowThreshold = zoneThreshold / 100;
        if (total <= zoneLowThreshold) {
            return Arrays.asList(new Zone(from + 1, to));
        }

        int traverseCpu = cpuThreshold;
        for (; traverseCpu > 0; traverseCpu--) {
            long zoneSize = total / traverseCpu;
            if (zoneSize > zoneLowThreshold) {
                break;
            }
        }

        final int confirmedCpu = traverseCpu;
        final long zoneSize = total / confirmedCpu;
        final long extra = total % confirmedCpu;
        final long seedZoneSize = zoneSize + (extra / confirmedCpu);

        List<Zone> zones = new ArrayList<>(confirmedCpu);
        for (int pos = 1; pos <= confirmedCpu; pos++) {
            long f = (pos - 1) * seedZoneSize + from + 1;
            long t;
            if (pos == confirmedCpu) {
                t = to;
            } else {
                t = pos * seedZoneSize + from;
            }
            zones.add(new Zone(f, t));
        }

        return zones;
    }

}
