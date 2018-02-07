package io.nebulas.explorer.jobs;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.SysService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Data check Job
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Slf4j
@AllArgsConstructor
@Component
public class DataConsensusJob {
    private static final int QUERY_SIZE = 2000;
    private final StringRedisTemplate redisTemplate;
    private final NebBlockService nebBlockService;
    private final SysService sysService;

    @Scheduled(cron = "0 0 3 ? * * ")
    public void check() {
        Long maxBlkHeight = nebBlockService.getMaxHeight();
        Long checkedBlkHeight = getCheckedBlockMaxHeight();

        if (checkedBlkHeight.equals(maxBlkHeight)) {
            return;
        }
        boolean isMissing = false;
        for (long begin = checkedBlkHeight; begin <= maxBlkHeight; ) {
            long end = begin + QUERY_SIZE;
            end = end < maxBlkHeight ? end : maxBlkHeight;

            List<NebBlock> blkList = nebBlockService.findNebBlockBetweenHeight(begin, end);
            if (blkList.size() == end - begin + 1) {
                begin = end;
                if (!isMissing) {
                    saveCheckedBlockMaxHeight(end);
                }
            } else {
                Map<Long, NebBlock> blkMap = blkList.stream().collect(Collectors.toMap(NebBlock::getHeight, e -> e));
                for (long i = begin; i <= end; i++) {
                    if (!blkMap.containsKey(i)) {
                        isMissing = true;
                        try {
                            sysService.populate(i, i + 1);
                        } catch (UnsupportedEncodingException e) {
                            log.error("encoding error", e);
                        }
                    }
                }
            }
        }
    }

    private Long getCheckedBlockMaxHeight() {
        String height = redisTemplate.opsForValue().get(getCheckedBlockRedisKey());
        return StringUtils.isEmpty(height) ? 1L : Long.valueOf(height);
    }

    private void saveCheckedBlockMaxHeight(Long height) {
        redisTemplate.opsForValue().set(getCheckedBlockRedisKey(), height.toString());
    }

    private String getCheckedBlockRedisKey() {
        return "DataConsensus.blockHeight";
    }

}
