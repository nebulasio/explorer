package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.repository.NebBlockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * nebulas block related operation service
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@Service
@AllArgsConstructor
public class NebBlockService {
    private final NebBlockRepository nebBlockRepository;

    /**
     * save nebulas block
     *
     * @param entity
     * @return
     */
    public boolean saveNebBlock(NebBlock entity) {
        if (null == entity) {
            return false;
        }
        return nebBlockRepository.save(entity) > 0;
    }

    /**
     * Get nebulas block information by block height
     *
     * @param height block height
     * @return block entity
     */
    public NebBlock getByHeight(Long height) {
        return nebBlockRepository.getByHeight(height);
    }

    public Long getMaxHeight() {
        return nebBlockRepository.getMaxHeight();
    }

}
