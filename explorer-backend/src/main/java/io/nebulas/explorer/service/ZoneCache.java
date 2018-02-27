package io.nebulas.explorer.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.nebulas.explorer.service.PopulationMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-31
 */
@Slf4j
@Service
public class ZoneCache {
    private static final long MAX_CURSORS = 1000L;
    private static final long THRESHOLD = 100L;

    private final PopulationMonitor populationMonitor;

    public ZoneCache(PopulationMonitor populationMonitor) {
        this.populationMonitor = populationMonitor;
        init();
    }

    private void init() {
        cursors = CacheBuilder.newBuilder()
                .maximumSize(MAX_CURSORS)
                .build();
        counterLock = new ReentrantLock(true);
    }

    public void addCursor(long from, long to, long cursor) {
        final String hashKey = getHashKey(from, to);
        if (cursor >= to) {
            addRedis(hashKey, cursor);
            cursors.invalidate(hashKey);
            return;
        }
        counterLock.lock();
        try {
            Long count = cursors.get(hashKey, () -> 0L);
            count++;
            cursors.put(hashKey, count);
            if (count > THRESHOLD) {
                addRedis(hashKey, cursor);
                cursors.invalidate(hashKey);
            }
        } catch (ExecutionException e) {
            log.error("cursor get error", e);
        } finally {
            counterLock.unlock();
        }
    }

    public void deleteAll() {
        cursors.invalidateAll();
    }

    private Cache<String, Long> cursors;
    private ReentrantLock counterLock;

    private String getHashKey(long from, long to) {
        return "zone_" + from + "_" + to;
    }

    private void addRedis(String hashKey, long cursor) {
        String[] parts = hashKey.split("_");
        long from = Long.parseLong(parts[1]);
        long to = Long.parseLong(parts[2]);
        if (cursor >= to) {
            try {
                populationMonitor.delete(from, to);
            } catch (Throwable e) {
                log.warn("duplicate delete redis record", e);
            }
        } else {
            populationMonitor.add(from, to, cursor);
        }
    }

}
