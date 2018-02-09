package io.nebulas.explorer.model.vo;

import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.text.DecimalFormat;
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
    private Long gasLimit;
    private String avgGasPrice; //sum(tx.gasPrice)/count(tx)
    private String gasReward; //sum(tx.gasUsed * tx.gasPrice)

    public BlockVo() {
    }

    public BlockVo(String hash, Long height) {
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

    public BlockVo setMiner(String minerHash, NebAddress address) {
        this.miner = null == address ? new NebAddress(minerHash) : address; //in order to ensure consistent miner structure
        return this;
    }

    public BlockVo setSummary(BlockSummary summary) {
        this.txnCnt = null != summary ? summary.getTxCnt() : 0L;
        if (null != summary) {
            this.avgGasPrice = summary.getAvgGasPrice();
            this.gasLimit = summary.getGasLimit();
            this.gasReward = summary.getGasReward();
        }
        return this;
    }

}
