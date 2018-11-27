package io.nebulas.explorer.service.thirdpart.gateio;

import com.alibaba.fastjson.JSONObject;
import retrofit2.http.GET;
import rx.Observable;

public interface GateioApiService {

    @GET("/api2/1/ticker/atp_usdt")
    Observable<JSONObject> getAtpMarket();
}
