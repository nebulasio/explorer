package io.nebulas.explorer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import static io.nebulas.explorer.service.Const.NEB_LOCKER_KEY;
import static io.nebulas.explorer.service.Const.NEB_LOCKER_VALUE;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-30
 */
@Service
public class NebLockService {
    private final StringRedisTemplate redisTemplate;
    private final ValueOperations<String, String> ops;

    @Autowired
    public NebLockService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        ops = redisTemplate.opsForValue();
    }

    public void addLock() {
        ops.set(NEB_LOCKER_KEY, NEB_LOCKER_VALUE);
    }


}
