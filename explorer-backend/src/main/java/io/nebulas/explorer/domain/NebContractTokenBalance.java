package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebContractTokenBalance implements Serializable {
    private static final long serialVersionUID = 1L;

    private String address;

    private String contract;

    private BigDecimal balance;

    private Date createdTime;

    private Date updatedTime;
}
