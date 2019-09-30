package io.nebulas.explorer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.nebulas.explorer.domain.NaxStage;
import io.nebulas.explorer.mapper.NaxProfitMapper;
import io.nebulas.explorer.mapper.NaxStageMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import io.nebulas.explorer.util.NebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j(topic = "subscribe")
public class NaxSyncService {

    private Executor executor = Executors.newSingleThreadExecutor();

    @Autowired
    private NebApiServiceWrapper nebApiServiceWrapper;

    @Autowired
    private NaxStageMapper naxStageMapper;

    @Value("${nax.height.start}")
    private long naxStartHeight;

    @Value("${nax.height.stage}")
    private long naxStageStep;

    @Value("${nax.topic}")
    private String topicNax;

    @Value("${nax.contract.data}")
    private String contractNaxData;

    public void sync(long blockHeight) {
        if (blockHeight < naxStartHeight) {
            return;
        }
        processNaxInfo(blockHeight);
        processNasPledgeInfo(blockHeight);
    }

    private void processNaxInfo(long blockHeight) {
        executor.execute(() -> {
            // 计算当前理论上的NAX周期
            long theoryStage = (blockHeight - naxStartHeight) / naxStageStep;
            // 本地实际的NAX周期
            long currentStage = getCurrentStage();
            if (theoryStage > currentStage) {
                if ((blockHeight - naxStartHeight) % 4 == 0) {
                    NaxStage naxStage = naxStageMapper.getStage(currentStage);
                    if (naxStage == null) {
                        naxStage = newStage(currentStage);
                    }
                    if (naxStage.getStatus() == 0) {
                        if (syncStageInfo(naxStage)) {
                            newStage(currentStage + 1);
                        }
                    }
                }
            }
        });
    }

    private void processNasPledgeInfo(long blockHeight) {
        long a = (blockHeight - naxStartHeight) % 10;
        if (a == 0) {
            executor.execute(this::getPledgedNasInfo);
        }
    }

    private String getPledgedNasInfo() {
        NebCallResult result = call(contractNaxData, contractNaxData, "getStakingTotalNAS");
        if (!result.hasError()) {
            return (String) JSONObject.parse(result.getResult());
        } else {
            return getPledgedNasInfo();
        }
    }

    private long getCurrentStage() {
        Integer lastCompletedStage = naxStageMapper.getLastCompletedStage();
        if (lastCompletedStage != null) {
            return lastCompletedStage + 1;
        } else {
            newStage(0);
            return 0;
        }
    }

    private NaxStage newStage(long stage) {
        NaxStage exist = naxStageMapper.getStage(stage);
        if (exist!=null){
            return exist;
        }
        // while-true 调用合约获取下一周期NAX预发行量并保存（数据库初始化下一周期记录）
        NebCallResult nextStageNaxCallResult = null;
        while (nextStageNaxCallResult == null || nextStageNaxCallResult.hasError()) {
            nextStageNaxCallResult = call(contractNaxData,
                    contractNaxData,
                    "getDistributeToken",
                    String.valueOf(stage));
        }
        String nextStageEstimateNax = (String) JSONObject.parse(nextStageNaxCallResult.getResult());
        NaxStage nextStage = new NaxStage();
        nextStage.setStage(stage);
        nextStage.setEstimateNax(new BigDecimal(nextStageEstimateNax));
        naxStageMapper.newStage(nextStage);
        return nextStage;
    }

    private NebCallResult call(String address, String contract, String function, String... args) {
        NebCallResult callResult;
        while (true) {
            callResult = nebApiServiceWrapper.call(address, contract, function, args);
            if (callResult == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        return callResult;
    }

    private boolean syncStageInfo(NaxStage naxStage) {
        NebCallResult callResult = call(contractNaxData,
                contractNaxData,
                "getStakingData",
                String.valueOf(naxStage.getStage()));
        if (callResult.hasError()) {
            return false;
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(callResult.getResult());
            String totalNax = jsonObject.getString("totalSupply");
            String estimateNax = jsonObject.getString("total");
            String actualNax = jsonObject.getString("totalDistribute");
            String destroyedNax = jsonObject.getString("totalDestory");
            String pledgeNas = jsonObject.getString("totalNAS");
            Long start = jsonObject.getLong("start");
            Long end = jsonObject.getLong("end");
            BigInteger totalNas = NebUtils.calculateTotalNas(end - 1);

            naxStage.setStart(start);
            naxStage.setEnd(end);
            naxStage.setTotalNax(new BigDecimal(totalNax));
            naxStage.setActualNax(new BigDecimal(actualNax));
            naxStage.setEstimateNax(new BigDecimal(estimateNax));
            naxStage.setDestroyedNax(new BigDecimal(destroyedNax));
            naxStage.setPledgeNas(new BigDecimal(pledgeNas));
            naxStage.setTotalNas(new BigDecimal(totalNas));
            naxStage.setStatus(1);
            naxStageMapper.update(naxStage);
            return true;
        } catch (Exception e) {
            log.error("NaxService.syncStageInfo error: {}", e.getMessage());
            return false;
        }
    }


}
