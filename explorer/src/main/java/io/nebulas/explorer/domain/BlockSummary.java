package io.nebulas.explorer.domain;

import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Data
@ToString
public class BlockSummary {
    private Long blockHeight;
    private Long txCnt;
    private Long gasLimit;
    private String avgGasPrice;
    private String gasReward;

    public BlockSummary() {
    }

    public BlockSummary(long blockHeight, long txCnt) {
        this.blockHeight = blockHeight;
        this.txCnt = txCnt;
    }

    public BlockSummary(long blockHeight, int txCnt, String gasReward, long gasLimit, String avgGasPrice) {
        this.blockHeight = blockHeight;
        this.txCnt = (long) txCnt;
        this.gasReward = gasReward;
        this.gasLimit = gasLimit;
        this.avgGasPrice = avgGasPrice;
    }
}
