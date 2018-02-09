package io.nebulas.explorer.service;

import com.google.common.collect.Maps;
import io.nebulas.explorer.config.YAMLConfig;
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
    private final StringRedisTemplate redisTemplate;

    public PopulationMonitor(YAMLConfig myConfig, StringRedisTemplate redisTemplate) {
        this.environment = myConfig.getEnvironment();
        this.redisTemplate = redisTemplate;
        ops = redisTemplate.opsForHash();
        key = environment + KEY;
    }

    public void add(long from, long to, long cur) {
        ops.put(key, getHashKey(from, to), String.valueOf(cur));
    }

    public void delete(long from, long to) {
        ops.delete(key, getHashKey(from, to));
    }

    public void deleteAll() {
        redisTemplate.delete(key);
    }

    public Long get(long from, long to) {
        Object o = ops.get(key, getHashKey(from, to));
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

    private String getHashKey(long from, long to) {
        return "zone_" + from + "_" + to;
    }

}
