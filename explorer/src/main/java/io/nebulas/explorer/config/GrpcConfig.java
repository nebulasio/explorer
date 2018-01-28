package io.nebulas.explorer.config;

import lombok.Data;
import lombok.ToString;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-28
 */
@Data
@ToString
public class GrpcConfig {
    private String host;
    private Integer port;
    private Boolean enableKeepAlive;
    private Boolean keepAliveWithoutCalls;
    private Integer keepAliveTimeout;
}
