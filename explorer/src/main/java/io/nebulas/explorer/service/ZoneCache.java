package io.nebulas.explorer.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    private static final long MAX_CURSORS = 100000L;
    private static final long EXPIRE_DURATION = 1000L;

    private final PopulationMonitor populationMonitor;

    public ZoneCache(PopulationMonitor populationMonitor) {
        this.populationMonitor = populationMonitor;
        init();
    }

    private void init() {
        cursors = CacheBuilder.newBuilder()
                .maximumSize(MAX_CURSORS)
                .expireAfterWrite(EXPIRE_DURATION, TimeUnit.MILLISECONDS)
                .removalListener((RemovalListener<String, Long>) notification -> addRedis(notification.getKey(), notification.getValue()))
                .build();
    }

    public void addCursor(long from, long to, long cursor) {
        cursors.put(getHashKey(from, to), cursor);
    }

    public void deleteAll() {
        cursors.invalidateAll();
    }

    private Cache<String, Long> cursors;

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
