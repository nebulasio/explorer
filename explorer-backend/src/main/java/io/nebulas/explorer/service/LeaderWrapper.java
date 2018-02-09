package io.nebulas.explorer.service;

import io.nebulas.explorer.config.YAMLConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
public class LeaderWrapper {
    private static final String LEADER_LOCK = "LEADER_LOCK";
    private static final long EXPIRE_SECONDS = 6L;

    private final String environment;
    private final String applicationId;
    private final ValueOperations<String, String> ops;

    public LeaderWrapper(YAMLConfig myConfig, String applicationId, StringRedisTemplate redisTemplate) {
        this.environment = myConfig.getEnvironment();
        this.applicationId = applicationId;
        ops = redisTemplate.opsForValue();
    }

    synchronized public boolean tryToAcquireLock() {
        String appId = ops.get(environment + LEADER_LOCK);
        if (appId == null) {
            ops.set(environment + LEADER_LOCK, applicationId, EXPIRE_SECONDS, TimeUnit.SECONDS);
            return true;
        }
        return applicationId.equals(appId);
    }

    @Scheduled(fixedDelay = 2000)
    public void heartbeat() {
        tryToAcquireLock();
    }

}
