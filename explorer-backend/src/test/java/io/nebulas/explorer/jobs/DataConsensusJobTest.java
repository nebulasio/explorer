package io.nebulas.explorer.jobs;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.SysService;
import org.junit.Test;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class DataConsensusJobTest {

    @Test(timeout = 10000)
    // TODO: fix the infinity loop in the DataConsensusJob.check method
    public void check() throws UnsupportedEncodingException {
        final StringRedisTemplate redisTemplate = createMock(StringRedisTemplate.class);
        final NebBlockService nebBlockService = createMock(NebBlockService.class);
        final SysService sysService = createMock(SysService.class);
        DataConsensusJob job = new DataConsensusJob(redisTemplate, nebBlockService, sysService);

        expect(nebBlockService.getMaxHeight()).andReturn(1000L);
        ValueOperations valueOperations = createMock(ValueOperations.class);
        expect(redisTemplate.opsForValue()).andReturn(valueOperations);
        expect(valueOperations.get(anyString())).andReturn(("100"));

        List<NebBlock> nebBlockList = new ArrayList<>();
        for (long i = 100; i < 900; i++) {
            nebBlockList.add(NebBlock.builder().height(i) .build());
        }

        expect(nebBlockService.findNebBlockBetweenHeight(eq(100L), eq(1000L)))
                .andReturn(nebBlockList).anyTimes();

        sysService.populate(anyLong(), anyLong());
        expectLastCall().anyTimes();

        replay(redisTemplate, nebBlockService, sysService, valueOperations);

        job.check();

        verify(redisTemplate, nebBlockService, sysService, valueOperations);
    }
}
