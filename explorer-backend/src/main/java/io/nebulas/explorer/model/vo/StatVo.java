package io.nebulas.explorer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YanxiSir
 * @since 2018/6/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatVo {

    /**
     * 昨天日交易数
     */
    private Long dailyTxCount;
    /**
     * 历史合约总数
     */
    private Long contractCount;
    /**
     * 历史钱包账户总数
     */
    private Long addrCount;
}
