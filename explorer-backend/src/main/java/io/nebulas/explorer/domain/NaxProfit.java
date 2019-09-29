package io.nebulas.explorer.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaxProfit implements Serializable {

    public static int SOURCE_PLEDGE = 0;
    public static int SOURCE_TRANSFER = 1;

    private int id;
    private String address;
    private String txHash;
    private long block;
    private BigDecimal profit;
    private int source;
    private long stage;
    private Date timestamp;
    private Date createdAt;
}
