package io.nebulas.explorer.service.thirdpart.huobi;

import com.alibaba.fastjson.JSONObject;
import retrofit2.http.GET;
import rx.Observable;

public interface HuobiApiService {

    @GET("/detail/merged?symbol=xmxbtc")
    Observable<JSONObject> getMarket();

}
