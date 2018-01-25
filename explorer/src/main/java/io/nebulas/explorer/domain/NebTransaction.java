package io.nebulas.explorer.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * nebulas transaction model
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Builder
@Data
@ToString
public class NebTransaction implements Serializable {
    private String id;
    private String hash;
    private Long blockHeight;
    private String blockHash;
    private String from;
    private String to;
    private Integer status;
    private String value;
    private Long nonce;
    private Date timestamp;
    private String type;
    private String data;
    private String gasPrice;
    private String gasLimit;
    private String gasUsed;
    private Date createdAt;
}
