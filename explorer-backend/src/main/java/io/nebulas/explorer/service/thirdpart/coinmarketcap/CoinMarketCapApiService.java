package io.nebulas.explorer.service.thirdpart.coinmarketcap;

import com.alibaba.fastjson.JSONArray;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
public interface CoinMarketCapApiService {

    @GET("/v1/ticker/nebulas-token/")
    Observable<JSONArray> getMarket();

}
