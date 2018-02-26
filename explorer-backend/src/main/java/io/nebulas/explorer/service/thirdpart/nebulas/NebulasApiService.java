package io.nebulas.explorer.service.thirdpart.nebulas;

import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
public interface NebulasApiService {

    @GET("/v1/user/nebstate")
    Observable<NebState> getNebState();

    @GET("/v1/user/lib")
    Observable<Block> getLatestIrreversibleBlock();

    @POST("/v1/user/getBlockByHash")
    Observable<Block> getBlockByHash(@Body GetBlockByHashRequest request);

    @POST("/v1/user/getBlockByHeight")
    Observable<Block> getBlockByHeight(@Body GetBlockByHeightRequest request);

    @POST("/v1/user/dynasty")
    Observable<GetDynastyResponse> getDynasty(@Body GetDynastyRequest request);

    @POST("/v1/user/getGasUsed")
    Observable<GetGasUsedResponse> getGasUsed(@Body GetGasUsedRequest request);

    @POST("/v1/user/accountstate")
    Observable<GetAccountStateResponse> getAccountState(@Body GetAccountStateRequest request);

}
