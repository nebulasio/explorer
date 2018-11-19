package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * explorer_prod.neb_event  neb event table
 *
 * @author liaoxihao
 * @date 2018-11-13
 *
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebEvent implements Serializable {
    /**  */
    private Long id;

    /** block hash */
    private String blockHash;

    /** block height */
    private Long blockHeight;

    /** transaction hash */
    private String txHash;

    /** event topic */
    private String topic;

    /** event data */
    private String data;

    /**  */
    private Date createTime;

    /**  */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

}