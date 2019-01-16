package io.nebulas.explorer.service.blockchain;

import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import io.nebulas.explorer.domain.NebDipAward;
import io.nebulas.explorer.mapper.NebDipAwardMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "subscribe")
@AllArgsConstructor
@Service
public class NebDipAwardService {

    private static final String TYPE_DIP = "dip";

    private static final String KEY_CONTRACT = "Contract";
    private static final String KEY_START_HEIGHT = "StartHeight";
    private static final String KEY_END_HEIGHT = "EndHeight";

    private NebDipAwardMapper nebDipAwardMapper;

    private static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();

    public void parseDipTransaction(Transaction transaction) {
        if (!transaction.getType().equals(TYPE_DIP)) {
            return;
        }
        log.info("开始解析DIP发奖交易: {}", transaction.getHash());
        String data = "";
        String contract = "";
        int startHeight = 0;
        int endHeight = 0;
        if (transaction.getData() != null) {
            data = new String(BASE64_DECODER.decode(transaction.getData()), StandardCharsets.UTF_8);
            try {
                JSONObject dataJson = new JSONObject(data);
                contract = dataJson.getString(KEY_CONTRACT);
                startHeight = dataJson.getInt(KEY_START_HEIGHT);
                endHeight = dataJson.getInt(KEY_END_HEIGHT);
            } catch (Exception e) {
                log.error("解析DIP中奖合约失败 -- 交易Hash: {}; data: {}", data);
            }
        }
        DateTime now = DateTime.now();
        DateTime sevenDaysAgo = now.minusDays(7);
        int week = sevenDaysAgo.getWeekOfWeekyear();
        int year = sevenDaysAgo.getWeekyear();
        NebDipAward dipAward = new NebDipAward();
        dipAward.setContract(contract);
        dipAward.setCreator(transaction.getTo());
        dipAward.setAward(transaction.getValue());
        dipAward.setTxHash(transaction.getHash());
        dipAward.setTxTimestamp(new Date(transaction.getTimestamp()*1000));
        dipAward.setWeek(week);
        dipAward.setYear(year);
        dipAward.setStartHeight(startHeight);
        dipAward.setEndHeight(endHeight);
        nebDipAwardMapper.insert(dipAward);
        log.info("DIP发奖交易插入成功: {}", dipAward.toString());
    }

    public List<NebDipAward> getDipAwardByWeek(int week, int weekYear, int page, int pageSize) {
        return nebDipAwardMapper.queryByWeek(week, weekYear, (page-1)*pageSize, pageSize);
    }

    public long getTotalAwardByWeek(int week, int weekYear) {
        return nebDipAwardMapper.queryTotalAwardByWeek(week, weekYear);
    }

    public int getCountByWeek(int week, int weekYear) {
        return nebDipAwardMapper.countByWeek(week, weekYear);
    }

}
