package io.nebulas.explorer.model.vo;

import io.nebulas.explorer.domain.NebAddress;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Address View
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-29
 */
@Data
@ToString
public class AddressVo implements Serializable {
    private Integer rank;
    private String hash;
    private String alias;
    private String balance;
    private String percentage;
    private Long txCnt;

    public AddressVo() {
    }
    public AddressVo(String hash) {
        this.hash = hash;
    }

    public AddressVo build(NebAddress address) {
        this.hash = address.getHash();
        this.alias = address.getAlias();
        this.balance = address.getCurrentBalance().toPlainString();
        return this;
    }
}
