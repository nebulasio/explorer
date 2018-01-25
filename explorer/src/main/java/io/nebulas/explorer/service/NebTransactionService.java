package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.repository.NebTransactionRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    /**
     * save single nebluas transaction entity
     *
     * @param transaction
     * @return
     */
    public boolean saveNebTransaction(NebTransaction transaction) {

        return true;
    }

    /**
     * batch save nebluas transaction entity
     *
     * @param transactions
     * @return
     */
    public boolean batchSaveNebTransaction(List<NebTransaction> transactions) {


        return true;
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


        return null;
    }

    /**
     *
     * @param blockHeight
     * @return
     */
    public List<NebTransaction> findNebTransactionInBlockByBlockHeight(Long blockHeight){


        return Collections.emptyList();
    }
}
