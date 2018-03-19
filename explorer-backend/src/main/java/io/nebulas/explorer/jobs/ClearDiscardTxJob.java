package io.nebulas.explorer.jobs;

import com.alibaba.fastjson.JSONObject;
import io.nebulas.explorer.domain.NebPendingTransaction;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.service.blockchain.NebTransactionService;
import io.nebulas.explorer.service.thirdpart.nebulas.NebulasApiService;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetTransactionReceiptRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.util.Date;
import java.util.List;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-28
 */
@Slf4j
@AllArgsConstructor
@Component
public class ClearDiscardTxJob {
    private NebTransactionService nebTransactionService;
    private NebulasApiService nebulasApiService;

    @Scheduled(cron = "0 0/3 * * * ?")
    public void clear() {
        Date now = new Date();
        log.info("begin check pending transaction date={}", now);
        List<NebPendingTransaction> pendingTxList = nebTransactionService.findLessThanTimestamp(now, 200);
        if (CollectionUtils.isNotEmpty(pendingTxList)) {
            for (NebPendingTransaction ptx : pendingTxList) {
                try {
                    log.info("check pending tx hash={}", ptx.getHash());
                    Response<Transaction> response = nebulasApiService.getTransactionReceipt(new GetTransactionReceiptRequest(ptx.getHash())).execute();
                    if (response.code() == 500) {
                        JSONObject jsonObject = JSONObject.parseObject(response.errorBody().string());
                        if (null != jsonObject && "transaction not found".equals(jsonObject.getString("error"))) {
                            nebTransactionService.deleteNebPendingTransaction(ptx.getId());
                            log.info("delete pending tx, hash={}", ptx.getHash());
                        }
                    } else if (response.code() == 200) {
                        NebTransaction tx = nebTransactionService.getNebTransactionByHash(ptx.getHash());
                        if (null != tx) {
                            nebTransactionService.deleteNebPendingTransaction(ptx.getId());
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
