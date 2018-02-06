package io.nebulas.explorer.model.vo;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebPendingTransaction;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.util.NasUnitUtil;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

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
    private Date currentTimestamp;
    private Long timeDiff;

    private static final long serialVersionUID = 1L;

    public TransactionVo build(NebTransaction txn) {
        this.hash = txn.getHash();
        this.status = txn.getStatus();
        this.value = txn.getValue();
        this.nonce = txn.getNonce();
        this.type = txn.getType();
        this.gasPrice = txn.getGasPrice();
        this.gasLimit = txn.getGasLimit();
        this.gasUsed = txn.getGasUsed();
        this.data = txn.getData();
        this.timestamp = txn.getTimestamp();
        this.currentTimestamp = new Date();
        this.timeDiff = System.currentTimeMillis() - txn.getTimestamp().getTime();
        this.createdAt = txn.getCreatedAt();
        return this;
    }

    public TransactionVo build(NebPendingTransaction txn) {
        this.hash = txn.getHash();
        this.value = txn.getValue();
        this.nonce = txn.getNonce();
        this.type = txn.getType();
        this.gasPrice = txn.getGasPrice();
        this.gasLimit = txn.getGasLimit();
        this.data = txn.getData();
        this.timestamp = txn.getTimestamp();
        this.currentTimestamp = new Date();
        this.timeDiff = System.currentTimeMillis() - txn.getTimestamp().getTime();
        this.createdAt = txn.getCreatedAt();
        return this;
    }

    public TransactionVo setBlockVo(String blockHash, Long blockHeight) {
        this.block = new BlockVo(blockHash, blockHeight);
        return this;
    }

    public TransactionVo setFromVo(String fromHash, NebAddress address) {
        this.from = address != null ? (new AddressVo().build(address)) : new AddressVo(fromHash);
        return this;
    }

    public TransactionVo setToVo(String toHash, NebAddress address) {
        this.to = address != null ? (new AddressVo().build(address)) : new AddressVo(toHash);
        return this;
    }

    public String getTxFee() {
        if (StringUtils.isEmpty(gasUsed) || StringUtils.isEmpty(gasPrice)) {
            return "";
        }
        return new BigDecimal(gasUsed).multiply(new BigDecimal(gasPrice)).stripTrailingZeros().toPlainString();
    }
}
