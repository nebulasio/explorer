package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebContractToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String tokenName;

    private String description;

    private String contract;

    private BigDecimal total;
}
