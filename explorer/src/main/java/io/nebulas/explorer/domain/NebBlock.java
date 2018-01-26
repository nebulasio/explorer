package io.nebulas.explorer.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * nebulas block model
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebBlock implements Serializable {
    private String id;
    private Long height;
    private String hash;
    private String parentHash;
    private Date timestamp;
    private String miner;
    private String coinbase;
    private Long nonce;
    private Date createdAt;
}
