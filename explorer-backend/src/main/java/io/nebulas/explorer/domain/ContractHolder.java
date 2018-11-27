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
public class ContractHolder {

    private int rank;
    private String address;
    private String contract;
    private BigDecimal balance;
    private String percentage;
}
