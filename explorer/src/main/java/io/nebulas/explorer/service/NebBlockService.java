package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.mapper.NebBlockMapper;
import io.nebulas.explorer.model.PageIterator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Long getMaxHeight() {
        return nebBlockMapper.getMaxHeight();
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

    /**
     * Get nebulas block information by block hash
     *
     * @param hash block hash
     * @return block entity
     */
    public NebBlock getByHash(String hash){
       if(StringUtils.isEmpty(hash)){
           return null;
       }
       return nebBlockMapper.getByHash(hash);
    }

    /**
     * @param page
     * @param pageSize
     * @return
     */
    public PageIterator<NebBlock> findNebBlockPageIterator(int page, int pageSize) {
        int cnt = nebBlockMapper.count();
        PageIterator<NebBlock> pageIterator = PageIterator.create(page, pageSize, cnt);
        if (cnt > 0) {
            List<NebBlock> blkList = nebBlockMapper.findOrderByHeightDesc(pageIterator.getOffset(), pageIterator.getPageSize());
            pageIterator.setData(blkList);
        }
        return pageIterator;
    }
}
