package io.nebulas.explorer.util;

import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-26
 */
@Slf4j
public class BlockUtil {
    public static void collectTxs(Block block, List<Transaction> txs, List<NebTransaction> nebTxsList
            , List<String> gasUsedList) {
        int pos = 0;
        for (Transaction tx : txs) {
            String gasUsed = StringUtils.trimToNull(gasUsedList.get(pos));
            NebTransaction nebTxs = NebTransaction.builder()
                    .hash(tx.getHash())
                    .blockHeight(block.getHeight())
                    .blockHash(block.getHash())
                    .from(tx.getFrom())
                    .to(tx.getTo())
                    .status(tx.getStatus())
                    .value(tx.getValue())
                    .nonce(tx.getNonce())
                    .timestamp(new Date(tx.getTimestamp() * 1000))
                    .type(tx.getType())
                    .data(tx.getData())
                    .gasPrice(tx.getGasPrice())
                    .gasLimit(tx.getGasLimit())
                    .gasUsed(gasUsed)
                    .build();
            nebTxsList.add(nebTxs);
            pos++;
        }
    }
}
