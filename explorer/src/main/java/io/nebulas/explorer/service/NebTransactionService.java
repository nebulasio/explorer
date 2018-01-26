package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.mapper.NebTransactionMapper;
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
    private final NebTransactionMapper nebTransactionMapper;

    public Integer addNebTransaction(NebTransaction transaction) {
        return nebTransactionMapper.addNebTransaction(transaction);
    }

    /**
     * batch add nebluas transaction entity
     *
     * @param transactions
     * @return
     */
    public Integer batchAddNebTransaction(List<NebTransaction> transactions) {
        return nebTransactionMapper.batchAddNebTransaction(transactions);
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
        return nebTransactionMapper.getByHash(hash);
    }

    public List<NebTransaction> getNebTransactionByBlockHeight(Long blockHeight) {
        return nebTransactionMapper.getByBlockHeight(blockHeight);
    }

}
