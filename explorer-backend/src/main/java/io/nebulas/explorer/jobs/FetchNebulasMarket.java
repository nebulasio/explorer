package io.nebulas.explorer.jobs;

import com.alibaba.fastjson.JSONObject;
import io.nebulas.explorer.domain.NebMarketCapitalization;
import io.nebulas.explorer.service.blockchain.NebMarketCapitalizationService;
import io.nebulas.explorer.service.thirdpart.coinmarketcap.CoinMarketCapApiService;
import io.nebulas.explorer.service.thirdpart.gateio.GateioApiService;
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
    private GateioApiService gateioApiService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void fetch() {
        JSONObject object = coinMarketCapApiService.getMarket().toBlocking().first();

        if (null != object && object.containsKey("data")) {
            /** jsonObject格式
             {
                "status": {
                    "timestamp": "2020-03-17T13:37:44.816Z",
                    "error_code": 0,
                    "error_message": null,
                    "elapsed": 15,
                    "credit_count": 1,
                    "notice": null
                },
                "data": {
                    "1908": {
                        "id": 1908,
                        "name": "Nebulas",
                        "symbol": "NAS",
                        "slug": "nebulas-token",
                        "num_market_pairs": 24,
                        "date_added": "2017-08-23T00:00:00.000Z",
                        "tags": [],
                        "max_supply": null,
                        "circulating_supply": 51952589,
                        "total_supply": 71758447.8254,
                        "platform": null,
                        "cmc_rank": 167,
                        "last_updated": "2020-03-17T13:37:06.000Z",
                        "quote": {
                            "USD": {
                                "price": 0.189892368131,
                                "volume_24h": 2044144.71774657,
                                "percent_change_1h": -2.49873,
                                "percent_change_24h": 7.42929,
                                "percent_change_7d": -50.9639,
                                "market_cap": 9865400.15574654,
                                "last_updated": "2020-03-17T13:37:06.000Z"
                            }
                        }
                    }
                }
            }
             */
            JSONObject jsonObject = object.getJSONObject("data").getJSONObject("1908").getJSONObject("quote").getJSONObject("USD");

            NebMarketCapitalization record = new NebMarketCapitalization();
            record.setCurrencyId("NAS");
            record.setMarketCap(jsonObject.getBigDecimal("market_cap"));
            record.setVolume24h(jsonObject.getBigDecimal("volume_24h"));

            BigDecimal change24HBD = jsonObject.getBigDecimal("percent_change_24h");
            record.setChange24h(change24HBD.abs());
            record.setTrends(change24HBD.compareTo(BigDecimal.ZERO) > 0 ? 1 : 0);

            record.setPrice(jsonObject.getBigDecimal("price"));
            record.setPriceUnit("USD");

            nebMarketCapitalizationService.addMarket(record);
        }

        //fetch atp price from gate.io api
        JSONObject jsonObject = gateioApiService.getAtpMarket().toBlocking().first();
        if (null != jsonObject){
            NebMarketCapitalization record = new NebMarketCapitalization();
            //读取交易市场数据
            record.setCurrencyId("ATP");

            BigDecimal lastPrice = jsonObject.getBigDecimal("last");
            BigDecimal atpTotalAmount = new BigDecimal("10000000000");

            BigDecimal marketCap = lastPrice.multiply(atpTotalAmount);
            record.setMarketCap(marketCap);
            record.setVolume24h(jsonObject.getBigDecimal("baseVolume"));

            BigDecimal change24HBD = jsonObject.getBigDecimal("percentChange");
            record.setChange24h(change24HBD.abs());

            record.setTrends(change24HBD.compareTo(BigDecimal.ZERO) > 0 ? 1 : 0);

            record.setPrice(lastPrice);
            record.setPriceUnit("USD");
            nebMarketCapitalizationService.addMarket(record);
        }

    }
}
