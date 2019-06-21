package io.nebulas.explorer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.nebulas.explorer.domain.NatRecord;
import io.nebulas.explorer.mapper.NatRecordMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Event;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetEventsByHashResponse;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "subscribe")
public class NatSyncService {

    private Executor singleExecutor = Executors.newSingleThreadExecutor();

    private static final String TOPIC_INNER_CONTRACT = "chain.innerContract";
    private static final String TOPIC_NR = "chain.contract.nr";
    private static final String TOPIC_PLEDGE = "chain.contract.pledge";
    private static final String TOPIC_VOTE = "chain.contract.vote";
    private static final String TOPIC_NAT = "chain.contract.NATToken";

    private static final String CONTRACT_NAT = "n1mpgNi6KKdSzr7i5Ma7JsG5yPY9knf9He7";

    @Autowired
    private NebApiServiceWrapper nebApiServiceWrapper;

    @Autowired
    private NatRecordMapper natRecordMapper;

    public void sync(long blockHeight, List<Transaction> transactions) {
        syncNatRecord(blockHeight, transactions);
    }

    private void syncNatRecord(long blockHeight, List<Transaction> transactions) {
        singleExecutor.execute(() -> {
            for (Transaction transaction : transactions) {
                String hash = transaction.getHash();
                while (true) {
                    int source = NatRecord.SOURCE_UNKNOWN;
                    GetEventsByHashResponse response = nebApiServiceWrapper.getEventsByHash(hash);
                    if (response != null) {
                        List<Event> events = response.getEvents();
                        // 处理铸币生成的NAT
                        Event natEvent = null;
                        for (Event event : events) {
                            if (event.getTopic().equals(TOPIC_NR)) {
                                source = NatRecord.SOURCE_NR;
                            } else if (event.getTopic().equals(TOPIC_PLEDGE)) {
                                source = NatRecord.SOURCE_PLEDGE;
                            } else if (event.getTopic().equals(TOPIC_VOTE)) {
                                source = NatRecord.SOURCE_VOTE;
                            }
                            if (event.getTopic().equals(TOPIC_NAT)) {
                                natEvent = event;
                            }
                        }
                        if (natEvent != null) {
                            // 真正的nat数据，需要解析
                            String data = natEvent.getData();
                            try {
                                JSONObject jsonData = JSON.parseObject(data);
                                if (jsonData.containsKey("Produce")) {
                                    JSONObject produce = jsonData.getJSONObject("Produce");
                                    if (produce != null && produce.containsKey("data")) {
                                        JSONArray natData = produce.getJSONArray("data");
                                        int size = natData.size();
                                        for (int i = 0; i < size; i++) {
                                            NatRecord record = new NatRecord();
                                            JSONObject item = natData.getJSONObject(i);
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
                                            natRecordMapper.insert(record);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                log.error("Nat sync error: {}", e.getMessage());
                            }
                        }
                        // 处理合约间调用的转账NAT
                        boolean hasInnerContractTransferNat = false;
                        for (Event event : events) {
                            try {
                                if (event.getTopic().equals(TOPIC_INNER_CONTRACT)) {
                                    JSONObject data = JSON.parseObject(event.getData());
                                    if (data.containsKey("to")) {
                                        String to = data.getString("to");
                                        if (to.equals(CONTRACT_NAT)) {
                                            hasInnerContractTransferNat = true;
                                        }
                                    }
                                } else if (event.getTopic().equals(TOPIC_NAT)) {
                                    if (!hasInnerContractTransferNat) {
                                        continue;
                                    }
                                    JSONObject data = JSON.parseObject(event.getData());
                                    if (!data.containsKey("Transfer")) {
                                        continue;
                                    }
                                    boolean status = data.getBooleanValue("Status");
                                    if (!status) {
                                        hasInnerContractTransferNat = false;
                                        continue;
                                    }
                                    //通过合约间调用进行的NAT转账
                                    JSONObject transferData = data.getJSONObject("Transfer");
                                    String from = transferData.getString("from");
                                    String to = transferData.getString("to");
                                    String value = transferData.getString("value");
                                    BigDecimal valueDecimal = new BigDecimal(value);
                                    BigDecimal valueDecimalConverse = valueDecimal.negate();

                                    NatRecord recordForFromAddress = new NatRecord();
                                    recordForFromAddress.setSource(NatRecord.SOURCE_UNKNOWN);
                                    recordForFromAddress.setAddress(from);
                                    recordForFromAddress.setAmount(valueDecimalConverse.toPlainString());
                                    recordForFromAddress.setTimestamp(new Date(transaction.getTimestamp() * 1000));
                                    recordForFromAddress.setTxHash(transaction.getHash());
                                    recordForFromAddress.setBlock(blockHeight);
                                    recordForFromAddress.setCreatedAt(new Date());
                                    natRecordMapper.insert(recordForFromAddress);

                                    NatRecord recordForToAddress = new NatRecord();
                                    recordForToAddress.setSource(NatRecord.SOURCE_UNKNOWN);
                                    recordForToAddress.setAddress(to);
                                    recordForToAddress.setAmount(value);
                                    recordForToAddress.setTimestamp(new Date(transaction.getTimestamp() * 1000));
                                    recordForToAddress.setTxHash(transaction.getHash());
                                    recordForToAddress.setBlock(blockHeight);
                                    recordForToAddress.setCreatedAt(new Date());
                                    natRecordMapper.insert(recordForToAddress);
                                    hasInnerContractTransferNat = false;
                                }
                            } catch (Exception e) {
                                log.error("Nat sync error - InnerContract: {}", e.getMessage());
                            }
                        }
                        break;
                    }
                }
            }
        });
    }

}
