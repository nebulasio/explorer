package io.nebulas.explorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Block implements Serializable {
    private String hash;
    private String parentHash;
    private Long height;
    private Long nonce;
    private String coinbase;
    private String miner;
    private Long timestamp;
    private Integer chainId;
    private String stateRoot;
    private String txsRoot;
    private String eventsRoot;
    private DposContext dposContext;
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return CollectionUtils.isEmpty(transactions) ? Collections.EMPTY_LIST : transactions;
    }
}
