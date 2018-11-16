package io.nebulas.explorer.jobs;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.service.blockchain.NebTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private final YAMLConfig myConfig;

    @Scheduled(cron = "0 0/3 * * * ?")
    public void clear() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = now.plusHours(0 - myConfig.getPendingTxExpiredTime()).plusMinutes(-30);
        log.info("ClearDiscardTxJob at {}", now.toString("yyyy-MM-dd HH:mm:ss"));
        nebTransactionService.deleteNebPendingTransactionByTimestamp(endTime.toDate());
    }
}
