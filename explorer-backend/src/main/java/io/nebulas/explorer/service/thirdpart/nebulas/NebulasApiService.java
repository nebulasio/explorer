package io.nebulas.explorer.service.thirdpart.nebulas;

import io.nebulas.explorer.service.thirdpart.nebulas.bean.Transaction;
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
    Observable<NebResponse<NebState>> getNebState();

    @GET("/v1/user/lib")
    Observable<NebResponse<Block>> getLatestLibBlock();

    @POST("/v1/user/getBlockByHash")
    Observable<NebResponse<Block>> getBlockByHash(@Body GetBlockByHashRequest request);

    @POST("/v1/user/getBlockByHeight")
    Observable<NebResponse<Block>> getBlockByHeight(@Body GetBlockByHeightRequest request);

    @POST("/v1/user/dynasty")
    Observable<NebResponse<GetDynastyResponse>> getDynasty(@Body GetDynastyRequest request);

    @POST("/v1/user/getTransactionReceipt")
    Observable<NebResponse<Transaction>> getTransactionReceipt(@Body GetTransactionReceiptRequest request);

    @POST("/v1/user/accountstate")
    Observable<NebResponse<GetAccountStateResponse>> getAccountState(@Body GetAccountStateRequest request);

    @POST("/v1/user/call")
    Observable<NebResponse<CallContractResponse>> callContract(@Body CallContractRequest request);
}
