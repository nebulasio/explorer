package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
@Getter
@Setter
public class BlockResponse implements Serializable {
    private String hash;
    private String parent_has;
    private String height;
    private String nonce;
    private String coinbase;
    private String miner;
    private String timestamp;
    private Integer chain_id;


}
