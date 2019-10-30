package io.nebulas.explorer.service.thirdpart.infra;

import io.nebulas.explorer.service.thirdpart.infra.bean.InfraResponse;
import io.nebulas.explorer.service.thirdpart.infra.bean.NRC20TxListBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface InfraApiService {

    @GET("tx/nrc20")
    Observable<InfraResponse<NRC20TxListBean>> getNRC20TxList(@Query("address") String address, @Query("page") int page);

}
