package io.nebulas.explorer.repository;

import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.mapper.NebTransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Component
@AllArgsConstructor
public class NebTransactionRepository {
    private final NebTransactionMapper nebTransactionMapper;

    public NebTransaction selectByHash(String hash) {
        return nebTransactionMapper.selectByHash(hash);
    }

    public Integer batchSaveNebTransaction(List<NebTransaction> transactions) {
        return nebTransactionMapper.batchSaveNebTransaction(transactions);
    }

    public List<NebTransaction> selectByBlockHeight(Long blockHeight) {
        return nebTransactionMapper.selectByBlockHeight(blockHeight);
    }

    public Integer saveNebTransaction(NebTransaction transaction) {
        return nebTransactionMapper.saveNebTransaction(transaction);
    }

}
