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
 * @since 2018-01-27
 */
@Data
@ToString
public class SyncConfig {
    private Long zone;
    private Integer cpu;
    private boolean open;
    private boolean subscribe;
}
