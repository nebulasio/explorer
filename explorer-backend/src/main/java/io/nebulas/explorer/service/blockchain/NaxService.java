package io.nebulas.explorer.service.blockchain;

import com.alibaba.fastjson.JSONObject;

import io.nebulas.explorer.domain.NaxProfit;
import io.nebulas.explorer.domain.NaxStage;
import io.nebulas.explorer.mapper.NaxProfitMapper;
import io.nebulas.explorer.mapper.NaxStageMapper;
import io.nebulas.explorer.mapper.NebBlockMapper;
import io.nebulas.explorer.model.vo.NaxStageVo;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.NebCallResult;
import io.nebulas.explorer.util.NebUtils;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NaxService {
    private static String KEY_CURRENT_NAS_PLEDGE = "NaxService.key_current_nas_pledge";
    private static String KEY_CURRENT_NAS_TOTAL = "NaxService.key_current_nas_total";

    @Autowired
    private NaxProfitMapper naxProfitMapper;
    @Autowired
    private NaxStageMapper naxStageMapper;

    @Qualifier("customStringTemplate")
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${nax.contract.data}")
    private String contractNaxData;

    @Value("${nax.height.start}")
    private long naxStartHeight;

    @Value("${nax.height.stage}")
    private long naxStageStep;

    @Autowired
    private NebBlockMapper nebBlockMapper;

    @Autowired
    private NebApiServiceWrapper nebApiServiceWrapper;

    public List<NaxProfit> list(int page, int pageSize, String address) {
        return naxProfitMapper.getByAddress(address, (page - 1) * pageSize, pageSize);
    }

    public long total(String address) {
        return naxProfitMapper.countByAddress(address);
    }

    public List<NaxStageVo> getStageList(int page, int pageSize) {
        return naxStageMapper.getStageList((page - 1) * pageSize, pageSize)
                .stream().map(NaxStageVo::new).collect(Collectors.toList());
    }

    public long countCompletedStage() {
        return naxStageMapper.countCompletedStage();
    }

    public List<NaxStageVo> getLast15StageList(){
        return naxStageMapper.getStageList(0, 15)
                .stream().map(NaxStageVo::new).collect(Collectors.toList());
    }

    public BigDecimal getTotalDistributeNax() {
        return naxStageMapper.getTotalDistributeNax();
    }

    public BigDecimal getCurrentPledgedNas() {
        return new BigDecimal(getPledgedNasInfo());
    }

    public BigDecimal getCurrentTotalNas() {
        String inCache = redisTemplate.opsForValue().get(KEY_CURRENT_NAS_TOTAL);
        if (!StringUtils.isEmpty(inCache)) {
            return new BigDecimal(inCache);
        }
        long height = nebBlockMapper.getMaxHeight();
        BigInteger totalNas = NebUtils.calculateTotalNas(height);
        redisTemplate.opsForValue().set(KEY_CURRENT_NAS_TOTAL, totalNas.toString(), 15, TimeUnit.SECONDS);
        return new BigDecimal(totalNas);
    }

    public long getCurrentStageEndHeight() {
        long currentStage = getLastCompletedStage() + 1;
        return naxStartHeight + ((currentStage+1) * naxStageStep);
    }

    public BigDecimal getLastDistributeNax() {
        long stageIdx = getLastCompletedStage();
        if (stageIdx==-1){
            return BigDecimal.ZERO;
        }
        NaxStage stage = naxStageMapper.getStage(stageIdx);
        return stage.getActualNax();
    }

    public BigDecimal getEstimateNax() {
        long stageIdx = getLastCompletedStage() + 1;
        if (stageIdx==-1){
            return BigDecimal.ZERO;
        }
        NaxStage stage = naxStageMapper.getStage(stageIdx);
        if (stage==null){
            return BigDecimal.ZERO;
        }
        return stage.getEstimateNax();
    }

    private String getPledgedNasInfo() {
        String inCache = redisTemplate.opsForValue().get(KEY_CURRENT_NAS_PLEDGE);
        if (!StringUtils.isEmpty(inCache)) {
            return inCache;
        }
        NebCallResult result = call(contractNaxData, contractNaxData, "getStakingTotalNAS");
        if (!result.hasError()) {
            String pledgedNas = (String) JSONObject.parse(result.getResult());
            redisTemplate.opsForValue().set(KEY_CURRENT_NAS_PLEDGE, pledgedNas, 3, TimeUnit.MINUTES);
            return pledgedNas;
        } else {
            return getPledgedNasInfo();
        }
    }

    public NaxStage getStage(long stage) {
        return naxStageMapper.getStage(stage);
    }

    private long getLastCompletedStage() {
        Integer lastCompletedStage = naxStageMapper.getLastCompletedStage();
        if (lastCompletedStage == null) {
            return -1;
        }
        return lastCompletedStage;
    }

    private NebCallResult call(String address, String contract, String function, String... args) {
        NebCallResult callResult;
        while (true) {
            callResult = nebApiServiceWrapper.call(address, contract, function, args);
            if (callResult == null) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return callResult;
    }
}
