package io.nebulas.explorer.service.thirdpart.nebulas;

import io.nebulas.explorer.service.thirdpart.nebulas.bean.*;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Desc:
 * User: nathan
 * Date: 2018-03-27
 */
@Slf4j
@Service
public class NebApiServiceWrapper {

    @Autowired
    private NebulasApiService nebApiService;

    public NebState getNebState() {
        try {
            NebResponse<NebState> response = nebApiService.getNebState().toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    public Block getLatestLibBlock() {
        try {
            NebResponse<Block> response = nebApiService.getLatestLibBlock().toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    public Block getBlockByHash(String hash, boolean withFullTx) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        try {
            NebResponse<Block> response = nebApiService.getBlockByHash(new GetBlockByHashRequest(hash, withFullTx)).toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    public Block getBlockByHeight(long height) {
        return getBlockByHeight(height, true);
    }

    public Block getBlockByHeight(long height, boolean withFullTx) {
        try {
            NebResponse<Block> response = nebApiService.getBlockByHeight(new GetBlockByHeightRequest(height, withFullTx)).toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getDynasty(long height) {
        try {
            NebResponse<GetDynastyResponse> response = nebApiService.getDynasty(new GetDynastyRequest(height)).toBlocking().first();
            if (null != response.getResult()) {
                return response.getResult().getMiners();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    public Transaction getTransactionReceipt(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        try {
            NebResponse<Transaction> response = nebApiService.getTransactionReceipt(new GetTransactionReceiptRequest(hash)).toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    public GetAccountStateResponse getAccountState(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }
        try {
            NebResponse<GetAccountStateResponse> response = nebApiService.getAccountState(new GetAccountStateRequest(address, "latest")).toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    public CallContractResponse callContractBalance(String address, String contract) {
        if (StringUtils.isEmpty(address) || StringUtils.isEmpty(contract)) {
            return null;
        }
        try {
            CallContractRequest request = CallContractRequest.buildQueryBalanceRequest(address, contract);
            NebResponse<CallContractResponse> response = nebApiService
                    .callContract(request)
                    .toBlocking().first();
            return response.getResult();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    //添加getEventsByHash rpc方法 :xihao.liao@nebulas.io   2018.11
    public GetEventsByHashResponse getEventsByHash(String hash){
        if (StringUtils.isEmpty(hash)){
            return null;
        }
        try {
            //构造request对象并获取response
            GetEventsByHashRequest request = new GetEventsByHashRequest(hash);
            NebResponse<GetEventsByHashResponse> response = nebApiService.getEventsByHash(request).toBlocking().first();
            return response.getResult();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.info(e.getMessage(), e);
            return null;
        }
    }

}
