package io.nebulas.explorer.service.redis;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.nebulas.explorer.domain.NebTxCountByDay;
import io.nebulas.explorer.mapper.NebTxCountByDayMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RedisService {

    private final static String REDIS_KEY_PREFIX = "tx_today_";

    @Qualifier("customStringTemplate")
    private final StringRedisTemplate redisTemplate;

    private final NebTxCountByDayMapper nebTxCountByDayMapper;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void plusCount(Block block) {
        log.info("Tracing: RedisService: Start to plusCount of block : " + block.getHeight());
        DateTime today = DateTime.now(DateTimeZone.UTC).withTimeAtStartOfDay();
        DateTime blockTime = new DateTime(block.getTimestamp()*1000, DateTimeZone.UTC).withTimeAtStartOfDay();
        String format = today.toString("yyyy-MM-dd");
        String blockTimeFormat = blockTime.toString("yyyy-MM-dd");
        log.info("Tracing: RedisService: Start to compare date between : Today - " + format + "; BlockDate - " + blockTimeFormat);
        if (!format.equals(blockTimeFormat)) {
            return;
        }

        String redisKey = REDIS_KEY_PREFIX + format;
        String cache = redisTemplate.opsForValue().get(redisKey);

        int txCountInBlock = block.getTransactions().size();
        log.info("Tracing: RedisService: cache in redis : " + cache);
        log.info("Tracing: RedisService: txCountInBlock : " + txCountInBlock);
        NebTxCountByDay nebTxCountByDay = null;
        if (cache == null) {
            nebTxCountByDay = new NebTxCountByDay();
            nebTxCountByDay.setDay(today.toDate());
            nebTxCountByDay.setCount(txCountInBlock);
            redisTemplate.opsForValue().set(redisKey, txCountInBlock + "");
            DateTime yesterday = today.minusDays(1);
            String yesterdayFormat = yesterday.toString("yyyy-MM-dd");
            String keyYesterday = REDIS_KEY_PREFIX + yesterdayFormat;
            redisTemplate.delete(keyYesterday);
        } else {
            if (txCountInBlock > 0) {
                int cachedCount = Integer.parseInt(cache);
                int countNow = cachedCount + txCountInBlock;
                redisTemplate.opsForValue().set(redisKey, Integer.toString(countNow));
                nebTxCountByDay = new NebTxCountByDay();
                nebTxCountByDay.setDay(today.toDate());
                nebTxCountByDay.setCount(countNow);
            }
        }
        log.info("Tracing: RedisService: before db update : " + (nebTxCountByDay == null ? "null" : nebTxCountByDay.toString()));
        if (nebTxCountByDay != null) {
            final NebTxCountByDay finalObj = nebTxCountByDay;
            executorService.submit(() -> {
                log.info("Tracing: RedisService: db update : " + finalObj.toString() + "; Thread: " + Thread.currentThread().getName());
                nebTxCountByDayMapper.update(finalObj);
            });

        }
    }
}
