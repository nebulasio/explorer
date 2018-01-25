package io.nebulas.explorer.repository;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.mapper.NebBlockMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
public class NebBlockRepository {
    private final NebBlockMapper mapper;

    public NebBlock getByHeight(Long height) {
        return mapper.selectByHeight(height);
    }

    public Integer save(NebBlock entity) {
        return mapper.save(entity);
    }

}
