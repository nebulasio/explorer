package io.nebulas.explorer.service;

import com.google.common.collect.Maps;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.model.Zone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-30
 */
@Slf4j
@Service
public class PopulationMonitor {
    private static final String KEY = "POPULATION_MONITOR";

    private final String environment;
    private final HashOperations<String, Object, Object> ops;
    private final String key;

    public PopulationMonitor(YAMLConfig myConfig, StringRedisTemplate redisTemplate) {
        this.environment = myConfig.getEnvironment();
        ops = redisTemplate.opsForHash();
        key = environment + KEY;
    }

    public void add(Zone zone, long cur) {
        ops.put(key, getHashKey(zone), String.valueOf(cur));
    }

    public void delete(Zone zone) {
        ops.delete(key, getHashKey(zone));
    }

    public void deleteAll() {
        ops.delete(key, "zone_*");
    }

    public Long get(Zone zone) {
        Object o = ops.get(key, getHashKey(zone));
        if (o == null) {
            return null;
        }
        return Long.parseLong((String) o);
    }

    public Map<String, Long> getAll() {
        Map<Object, Object> entries = ops.entries(key);
        if (entries == null || entries.isEmpty()) {
            return Maps.newHashMap();
        }
        Map<String, Long> ret = new HashMap<>(entries.size());
        for (Object hk : entries.keySet()) {
            ret.put((String) hk, Long.parseLong((String) entries.get(hk)));
        }
        return ret;
    }

    private String getHashKey(Zone zone) {
        return "zone_" + zone.getFrom() + "_" + zone.getTo();
    }

}
