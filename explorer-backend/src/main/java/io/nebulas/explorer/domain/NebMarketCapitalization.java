package io.nebulas.explorer.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * explorer.neb_market_capitalization  nebulas market capitalization
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-27
 */
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebMarketCapitalization implements Serializable {
    /**  */
    private Long id;

    /**  */
    private BigDecimal marketCap;

    /**  */
    private BigDecimal volume24h;

    /**  */
    private BigDecimal change24h;

    /**  */
    private BigDecimal price;

    /**  */
    private String priceUnit;

    private Integer trends;

    /**  */
    private Date createdAt;

    private static final long serialVersionUID = 1L;
}