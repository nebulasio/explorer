package io.nebulas.explorer.model.vo;

import io.nebulas.explorer.domain.NebTransaction;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Nrc20TransactionVo implements Serializable {

    private String hash;

    /**
     * hex string of block hash
     */
    private String blockHash;

    /**
     * block height
     */
    private Long blockHeight;

    /**
     * tx sequence in same block
     */
    private Integer txSeq;
    /**
     * hex string of the sender account address
     */
    private String from;

    /**
     * hex string of the receiver account address
     */
    private String to;

    /**
     * transaction status, 0: failed; 1: success; 2: pending;
     */
    private Integer status;

    /**
     * value of transaction
     */
    private String value;

    /**
     * transaction nonce
     */
    private Long nonce;

    /**
     * transaction timestamp
     */
    private Date timestamp;

    /**
     * transaction type
     */
    private String type;

    /**
     * Gas price
     */
    private String gasPrice;

    /**
     * Gas limit
     */
    private String gasLimit;

    /**
     * Gas used
     */
    private String gasUsed;

    private Date createdAt;

    /**
     * transaction data
     */
    private String data;

    /**
     * contract address. it is not null, when the type of transaction is deploy.
     */
    private String contractAddress;

    private String executeError;


    private long tokenDecimals;


    public Nrc20TransactionVo buildFromNebTransaction(NebTransaction nebTransaction){

        this.hash = nebTransaction.getHash();
        this.blockHash = nebTransaction.getBlockHash();
        this.blockHeight = nebTransaction.getBlockHeight();
        this.txSeq = nebTransaction.getTxSeq();
        this.from = nebTransaction.getFrom();
        this.to = nebTransaction.getTo();
        this.status = nebTransaction.getStatus();
        this.value = nebTransaction.getValue();
        this.nonce = nebTransaction.getNonce();
        this.timestamp = nebTransaction.getTimestamp();
        this.contractAddress = nebTransaction.getContractAddress();
        this.type = nebTransaction.getType();
        this.createdAt = nebTransaction.getCreatedAt();
        this.gasLimit = nebTransaction.getGasLimit();
        this.gasPrice = nebTransaction.getGasPrice();
        this.gasUsed = nebTransaction.getGasUsed();
        this.data = nebTransaction.getData();
        this.executeError = nebTransaction.getExecuteError();

        return this;
    }
}
