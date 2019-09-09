package io.nebulas.explorer.domain;

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
public class NaxStage {
    private int id;
    private long stage;
    private long start;
    private long end;
    private BigDecimal estimateNax;
    private BigDecimal actualNax;
    private BigDecimal destroyedNax;
    private BigDecimal totalNax;
    private BigDecimal pledgeNas;
    private BigDecimal totalNas;
    private int status;
    private Date createdAt;
}
