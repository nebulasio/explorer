package io.nebulas.explorer.domain;

import lombok.Data;
import lombok.ToString;

/**
 *
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
    private Long gasUsed;
    private Long gasLimit;
    private Long avgGasPrice;
}
