package io.nebulas.explorer.service.thirdpart.coinmarketcap;

import com.alibaba.fastjson.JSONObject;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
public interface CoinMarketCapApiService {

    @GET("/v1/cryptocurrency/quotes/latest?id=1908&convert=USD")
    Observable<JSONObject> getMarket();

}
