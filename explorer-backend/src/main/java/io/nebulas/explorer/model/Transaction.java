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
 * @since 2018-01-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction implements Serializable {
    private String hash;
    private Integer chainId;
    private String from;
    private String to;
    private String value;
    private Long nonce;
    private Long timestamp;
    private String type;
    private String data;
    private String gasPrice;
    private String gasLimit;
    private String contractAddress;
    private Integer status;
}
