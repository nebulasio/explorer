package io.nebulas.explorer.jobs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.nebulas.explorer.domain.NebMarketCapitalization;
import io.nebulas.explorer.service.blockchain.NebMarketCapitalizationService;
import io.nebulas.explorer.service.thirdpart.coinmarketcap.CoinMarketCapApiService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
@Slf4j
@AllArgsConstructor
@Component
public class FetchNebulasMarket {
    private NebMarketCapitalizationService nebMarketCapitalizationService;
    private CoinMarketCapApiService coinMarketCapApiService;


    @Scheduled(cron = "0 0/15 * * * ?")
    public void fetch() {
        JSONArray jsonArray = coinMarketCapApiService.getMarket().toBlocking().first();

        if (null != jsonArray && jsonArray.size() > 0) {
            /** jsonObject格式
             * {
             "id": "nebulas-token",
             "name": "Nebulas",
             "symbol": "NAS",
             "rank": "67",
             "price_usd": "8.0421",
             "price_btc": "0.00080844",
             "24h_volume_usd": "7921550.0",
             "market_cap_usd": "285494550.0",
             "available_supply": "35500000.0",
             "total_supply": "100000000.0",
             "max_supply": "100000000.0",
             "percent_change_1h": "1.14",
             "percent_change_24h": "-3.66",
             "percent_change_7d": "-11.3",
             "last_updated": "1519376054"
             }
             */
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            NebMarketCapitalization record = new NebMarketCapitalization();
            record.setMarketCap(jsonObject.getBigDecimal("market_cap_usd"));
            record.setVolume24h(jsonObject.getBigDecimal("24h_volume_usd"));

            BigDecimal change24HBD = jsonObject.getBigDecimal("percent_change_24h");
            record.setChange24h(change24HBD.abs());
            record.setTrends(change24HBD.compareTo(BigDecimal.ZERO) > 0 ? 1 : 0);

            record.setPrice(jsonObject.getBigDecimal("price_usd"));
            record.setPriceUnit("USD");

            nebMarketCapitalizationService.addMarket(record);
        }

    }
}
