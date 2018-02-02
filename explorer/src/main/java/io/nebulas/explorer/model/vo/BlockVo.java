package io.nebulas.explorer.model.vo;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Block View
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-29
 */
@Data
@ToString
public class BlockVo implements Serializable {
    private String hash;
    private Long height;
    private Date timestamp;
    private String parentHash;
    private NebAddress miner;
    private Long txnCnt = 0L;
    private Long gasUsed;
    private Long gasLimit;
    private Long avgGasPrice;

    public BlockVo() {
    }

    public BlockVo(String hash,Long height) {
        this.hash = hash;
        this.height = height;
    }

    public BlockVo build(NebBlock nebBlock) {
        this.hash = nebBlock.getHash();
        this.height = nebBlock.getHeight();
        this.timestamp = nebBlock.getTimestamp();
        this.parentHash = nebBlock.getParentHash();
        return this;
    }
}
