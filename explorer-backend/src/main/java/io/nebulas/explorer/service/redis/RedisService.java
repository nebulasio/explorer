package io.nebulas.explorer.service.redis;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import io.nebulas.explorer.domain.NebTxCountByDay;
import io.nebulas.explorer.mapper.NebTxCountByDayMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RedisService {

    private final static String REDIS_KEY_PREFIX = "tx_today_";

    @Qualifier("customStringTemplate")
    private final StringRedisTemplate redisTemplate;

    private final NebTxCountByDayMapper nebTxCountByDayMapper;

    public void plusCount(Block block) {
        DateTime today = DateTime.now(DateTimeZone.UTC).withTimeAtStartOfDay().toDateTime(DateTimeZone.getDefault());
        DateTime blockTime = new DateTime(block.getTimestamp(), DateTimeZone.UTC).withTimeAtStartOfDay().toDateTime(DateTimeZone.getDefault());
        String format = today.toString("yyyy-MM-dd");
        String blockTimeFormat = blockTime.toString("yyyy-MM-dd");
        if (!format.equals(blockTimeFormat)) {
            return;
        }

        String redisKey = REDIS_KEY_PREFIX + format;
        String cache = redisTemplate.opsForValue().get(redisKey);

        int txCountInBlock = block.getTransactions().size();
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
        if (nebTxCountByDay != null) {
            nebTxCountByDayMapper.update(nebTxCountByDay);
        }
    }
}
