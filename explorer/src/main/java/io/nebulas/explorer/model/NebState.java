package io.nebulas.explorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NebState implements Serializable {
    private Integer chainId;
    private String tail;
    private String coinbase;
    private Integer peerCount;
    private Boolean isMining;
    private String protocolVersion;
    private Boolean sync;
    private String version;
}
