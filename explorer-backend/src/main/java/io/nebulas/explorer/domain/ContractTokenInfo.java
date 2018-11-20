package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContractTokenInfo {

    private BigDecimal total;
    private String tokenName;
    private String contract;
    private long transactionCount;
    private long pendingTransactionCount;
    private long holderCount;





}
