package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.mapper.NebBlockMapper;
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
    private final NebBlockMapper nebBlockMapper;

    /**
     * add nebulas block
     *
     * @param entity
     * @return
     */
    public boolean addNebBlock(NebBlock entity) {
        if (null == entity) {
            return false;
        }
        return nebBlockMapper.add(entity) > 0;
    }

    /**
     * Get nebulas block information by block height
     *
     * @param height block height
     * @return block entity
     */
    public NebBlock getByHeight(Long height) {
        return nebBlockMapper.getByHeight(height);
    }

    public Long getMaxHeight() {
        return nebBlockMapper.getMaxHeight();
    }

}
