package io.nebulas.explorer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.nebulas.explorer.domain.NaxPeriod;
import io.nebulas.explorer.domain.NaxRecord;
import io.nebulas.explorer.mapper.NaxMapper;
import io.nebulas.explorer.mapper.NaxPeriodMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@Slf4j(topic = "subscribe")
public class NaxSyncService {
    private Executor singleExecutor = Executors.newSingleThreadExecutor();

    private static final String TOPIC_INNER_CONTRACT = "chain.innerContract";
    private static final String TOPIC_PLEDGE = "chain.contract.pledge";
    private static final String TOPIC_VOTE = "chain.contract.vote";
    private static final String TOPIC_NAX = "chain.contract.NAXToken";

    private static final String NAX_Data = "n1en9zPsCxqieD2cEaU634f7CEaAqZeXiW5";
    private static final String from = "n1JvS1LDTJRxSdq4F5cDd1x78ihHTTRyWif";
    private static final long height_begin = 2408520;


    @Autowired
    private NebApiServiceWrapper nebApiServiceWrapper;

    @Autowired
    private NaxMapper naxMapper;

    @Autowired
    private NaxPeriodMapper naxPeriodMapper;

    public void sync(long blockHeight, List<Transaction> transactions) {
        syncNaxRecord(blockHeight, transactions);
    }
    private void syncNaxRecord(long blockHeight, List<Transaction> transactions) {
        singleExecutor.execute(() -> {
                // get current period
                long period = (blockHeight-height_begin)/6000 + 1;

                if (naxPeriodMapper.getByPeriod(period) == null ){
                    // 调用合约获取上一周期数据

                    CallContractResponse response = nebApiServiceWrapper.callContractGetNax(from, Long.toString(period),NAX_Data);
                    String result = response.getResult();
                    JSONObject stakingData = JSON.parseObject(result);

                    NaxPeriod periodRecord = new NaxPeriod();
                    String lastDistribute = stakingData.getString("last_distribute");
                    String totalSupply = stakingData.getString("totalSupply");
                    String totalNAS = stakingData.getString("totalNAS");
                    periodRecord.setPeriod(period);
                    periodRecord.setLastDistribute(lastDistribute );
                    periodRecord.setTotalSupply(totalSupply);
                    periodRecord.setTotalNAS(totalNAS);
                    System.out.println(periodRecord);

                }
                for (Transaction transaction : transactions) {

                    String hash = transaction.getHash();
                    while (true) {
                        int source = NaxRecord.SOURCE_UNKNOWN;
                        GetEventsByHashResponse response = nebApiServiceWrapper.getEventsByHash(hash);
                        if (response != null){
                            List<Event> events = response.getEvents();

                            Event naxEvent = null;
                            for (Event event : events) {
                                if (event.getTopic().equals(TOPIC_NAX)) {
                                    naxEvent = event;
                                }
                                if (naxEvent != null) {
                                    String data = naxEvent.getData();
                                    try{
                                        JSONObject jsonData = JSON.parseObject(data);
                                        if (jsonData.containsKey("Produce")) {
                                            JSONObject produce = jsonData.getJSONObject("Produce");
                                            if (produce != null && produce.containsKey("data")) {
                                                JSONArray naxData = produce.getJSONArray("data");
                                                int size = naxData.size();
                                                for (int i = 0; i < size; i++) {
                                                    NaxRecord record = new NaxRecord();
                                                    JSONObject item = naxData.getJSONObject(i);
                                                    String address = item.getString("addr");
                                                    if (address == null) {
                                                        address = "";
                                                    }
                                                    String value = item.getString("value");
                                                    if (value == null || value.isEmpty()) {
                                                        value = "0";
                                                    }
                                                    record.setSource(source);
                                                    record.setAddress(address);
                                                    record.setAmount(value);
                                                    record.setTimestamp(new Date(transaction.getTimestamp() * 1000));
                                                    record.setTxHash(transaction.getHash());
                                                    record.setBlock(blockHeight);
                                                    record.setCreatedAt(new Date());
                                                    naxMapper.insert(record);
                                                }
                                            }
                                        }

                                    } catch (Exception e) {
                                        log.error("Nax sync error: {}", e.getMessage());
                                    }
                                }
                            }
                            break;
                        }

                    }

                }
        });
    }
}
