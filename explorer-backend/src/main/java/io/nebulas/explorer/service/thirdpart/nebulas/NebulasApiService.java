package io.nebulas.explorer.service.thirdpart.nebulas;

import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
public interface NebulasApiService {

    @POST("/v1/user/nebstate")
    Observable<NebState> getNebState();

    @POST("/v1/user/getBlockByHash")
    Observable<Block> getBlockByHash(@Body BlockRequest request);

    @POST("/v1/user/getBlockByHeight")
    Observable<Block> getBlockByHeight(@Body BlockRequest request);

    @POST("/v1/usr/dynasty")
    Observable<GetDynastyResponse> getDynasty(@Body GetDynastyRequest request);

    @POST("/v1/user/getGasUsed")
    Observable<GetGasUsedResponse> getGasUsed(@Body GetGasUsedRequest request);
}
