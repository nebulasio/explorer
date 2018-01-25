package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.repository.NebTransactionRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * nebulas transaction related operation service
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Service
@AllArgsConstructor
public class NebTransactionService {
    private final NebTransactionRepository nebTransactionRepository;

    public Integer saveNebTransaction(NebTransaction transaction) {
        return nebTransactionRepository.saveNebTransaction(transaction);
    }

    /**
     * batch save nebluas transaction entity
     *
     * @param transactions
     * @return
     */
    public Integer batchSaveNebTransaction(List<NebTransaction> transactions) {
        return nebTransactionRepository.batchSaveNebTransaction(transactions);
    }

    /**
     * get nebulas transaction by hash
     *
     * @param hash
     * @return
     */
    public NebTransaction getNebTransactionByHash(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        return nebTransactionRepository.selectByHash(hash);
    }

    public List<NebTransaction> getNebTransactionByBlockHeight(Long blockHeight) {
        return nebTransactionRepository.selectByBlockHeight(blockHeight);
    }

}
