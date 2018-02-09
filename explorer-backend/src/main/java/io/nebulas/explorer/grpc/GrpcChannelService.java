package io.nebulas.explorer.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.nebulas.explorer.config.GrpcConfig;
import io.nebulas.explorer.config.YAMLConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-28
 */
@Slf4j
@Service
public class GrpcChannelService {
    @Autowired
    private YAMLConfig cfg;

    private ManagedChannel channel;

    public synchronized ManagedChannel renewChannel() {
        if (channel != null) {
            try {
                channel.shutdown().awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                log.error("shutdown channel error", e);
            }
        }
        channel = newChannel();
        return channel;
    }

    public synchronized Channel getChannel() {
        if (channel == null) {
            return newChannel();
        }
        return channel;
    }

    private ManagedChannel newChannel() {
        GrpcConfig grpcCfg = cfg.getGrpc();
        try {
            ManagedChannelBuilder<?> managedChannelBuilder = ManagedChannelBuilder
                    .forAddress(grpcCfg.getHost(), grpcCfg.getPort());
            if (grpcCfg.getEnableKeepAlive()) {
                managedChannelBuilder.keepAliveWithoutCalls(grpcCfg.getEnableKeepAlive());
                managedChannelBuilder.keepAliveTimeout(grpcCfg.getKeepAliveTimeout(), TimeUnit.SECONDS);
            }
            managedChannelBuilder.usePlaintext(true);
            return managedChannelBuilder.build();
        } catch (Exception e) {
            log.error("build channel error", e);
            throw e;
        }
    }

}
