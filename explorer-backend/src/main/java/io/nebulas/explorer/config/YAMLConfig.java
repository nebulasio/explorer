package io.nebulas.explorer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-22
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class YAMLConfig {
    private String environment;
    private GrpcConfig grpc;
    private PageConfig page;
    private SyncConfig sync;
    private HttpApiHostConfig apiHost;
}
