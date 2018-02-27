package io.nebulas.explorer.jobs;

import io.grpc.StatusRuntimeException;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.grpc.GrpcChannelService;
import io.nebulas.explorer.grpc.GrpcClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-24
 */
@Slf4j
@AllArgsConstructor
@Service
public class SysSubscribeService {
    private final GrpcChannelService grpcChannelService;
    private final GrpcClientService grpcClientService;
    private final YAMLConfig yamlConfig;

    public void init(boolean isSubscribe) {
        log.info("sys init starting...");
        do {
            final long start = System.currentTimeMillis();
            try {
                if (isSubscribe) {
                    grpcClientService.subscribe();
                }
                long elapsed = System.currentTimeMillis() - start;
                log.info("{} millis elapsed", elapsed);
                log.info("sys init end");
                break;
            } catch (StatusRuntimeException e) {
                String errorMessage = e.getMessage();
                log.error(errorMessage);
                reConnect();
            } catch (Throwable e) {
                log.error("other error", e);
                break;
            }
        } while (true);
    }

    private void reConnect() {
        try {
            grpcChannelService.renewChannel();
            // sleep for 10 seconds to enter next retry
            log.info("entering next retry after 10 seconds ....");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e1) {
            log.error("thread sleep interrupted", e1);
        }
    }
}
