package io.nebulas.explorer.model.vo;

import io.nebulas.explorer.domain.NebTransaction;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction View
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-31
 */
@Data
@ToString
public class TransactionVo implements Serializable {

    private String hash;
    private BlockVo block;
    private AddressVo from;
    private AddressVo to;
    private Integer status;
    private String value;
    private Long nonce;
    private Date timestamp;
    private String type;
    private String gasPrice;
    private String gasLimit;
    private String gasUsed;
    private Date createdAt;
    private String data;

    private static final long serialVersionUID = 1L;

    public TransactionVo build(NebTransaction txn) {
        this.hash = txn.getHash();
        this.status = txn.getStatus();
        this.value = txn.getValue();
        this.nonce = txn.getNonce();
        this.timestamp = txn.getTimestamp();
        this.type = txn.getType();
        this.gasPrice = txn.getGasPrice();
        this.gasLimit = txn.getGasLimit();
        this.gasUsed = txn.getGasUsed();
        this.data = txn.getData();
        this.createdAt = txn.getCreatedAt();
        return this;
    }

    public String getTxFee() {
        return new BigDecimal(gasUsed).multiply(new BigDecimal(gasPrice)).toPlainString();
    }


}
