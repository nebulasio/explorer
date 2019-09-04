package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaxPeriod {
    private int id;
    private long period;
    private String lastDistribute;
    private String totalSupply;
    private String totalNAS;
    private String totalDistribute;
}
