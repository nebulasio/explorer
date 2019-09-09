package io.nebulas.explorer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.nebulas.explorer.domain.NaxProfit;
import io.nebulas.explorer.domain.NebTransactionEvent;
import io.nebulas.explorer.mapper.NaxProfitMapper;
import io.nebulas.explorer.mapper.NebTransactionEventsMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Block;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Event;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetEventsByHashResponse;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionEventsService {

    @Value("${nax.topic}")
    private String topicNax;

    @Autowired
    private NebApiServiceWrapper nebApiServiceWrapper;
    @Autowired
    private NaxProfitMapper naxProfitMapper;
    @Autowired
    private NebTransactionEventsMapper nebTransactionEventsMapper;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void syncEvents(Block blk) {
        executor.execute(() -> {
            List<Transaction> txList = blk.getTransactions();
            for (Transaction tx : txList) {
                while (true) {
                    GetEventsByHashResponse response = nebApiServiceWrapper.getEventsByHash(tx.getHash());
                    if (response == null) {
                        continue;
                    }
                    List<Event> events = response.getEvents();
                    Event naxEvent = null;
                    Event stakingEvent = null;
                    for (Event event : events) {
                        NebTransactionEvent nebTransactionEvent = new NebTransactionEvent();
                        nebTransactionEvent.setTxHash(tx.getHash());
                        nebTransactionEvent.setTimestamp(new Date(blk.getTimestamp() * 1000));
                        nebTransactionEvent.setBlock(blk.getHeight());
                        nebTransactionEvent.setTopic(event.getTopic());
                        nebTransactionEvent.setData(event.getData());
                        nebTransactionEventsMapper.insert(nebTransactionEvent);
                        if (event.getTopic().equals(topicNax)) {
                            naxEvent = event;
                        }
                        if (event.getTopic().endsWith(".Staking")) {
                            stakingEvent = event;
                        }
                    }
                    if (naxEvent != null && stakingEvent != null) {
                        log.info("Found NAX trigger transaction: {}", tx.getHash());
                        // 解析Staking数据，用于获取此交易对应的NAX周期
                        long stage = -1;
                        String stakingData = stakingEvent.getData();
                        JSONObject jsonStakingData = JSONObject.parseObject(stakingData);
                        if (jsonStakingData.containsKey("section")) {
                            JSONObject section = jsonStakingData.getJSONObject("section");
                            stage = section.getLongValue("period");
                        } else {
                            log.error("NAX trigger events data error in staking info: {}", stakingData);
                        }
                        // 真正的nax数据，需要解析
                        String data = naxEvent.getData();
                        try {
                            JSONObject jsonData = JSON.parseObject(data);
                            if (jsonData.containsKey("Distribute")) {
                                JSONObject distribute = jsonData.getJSONObject("Distribute");
                                if (distribute != null && distribute.containsKey("data")) {
                                    JSONArray naxData = distribute.getJSONArray("data");
                                    int size = naxData.size();
                                    for (int i = 0; i < size; i++) {
                                        NaxProfit record = new NaxProfit();
                                        JSONObject item = naxData.getJSONObject(i);
                                        String address = item.getString("addr");
                                        if (address == null) {
                                            address = "";
                                        }
                                        String value = item.getString("value");
                                        if (value == null || value.isEmpty()) {
                                            value = "0";
                                        }
                                        record.setSource(0);
                                        record.setStage(stage);
                                        record.setAddress(address);
                                        record.setProfit(new BigDecimal(value));
                                        record.setTimestamp(new Date(blk.getTimestamp() * 1000));
                                        record.setTxHash(tx.getHash());
                                        record.setBlock(blk.getHeight());
                                        record.setCreatedAt(new Date());
                                        naxProfitMapper.insert(record);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            log.error("Nax sync error: {}", e.getMessage());
                        }
                    }
                    break;
                }
            }
        });
    }


}
