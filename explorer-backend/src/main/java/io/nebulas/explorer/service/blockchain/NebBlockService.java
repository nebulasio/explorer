package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.mapper.NebBlockMapper;
import io.nebulas.explorer.model.PageIterator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Nebulas block related operation service
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-24
 */
@AllArgsConstructor
@Service
public class NebBlockService {
    private final NebBlockMapper nebBlockMapper;

    /**
     * save block information
     *
     * @param entity block bean
     * @return saved result
     */
    public boolean addNebBlock(NebBlock entity) {
        return (null != entity) && nebBlockMapper.add(entity) > 0;
    }

    /**
     * update block finality property
     *
     * @param maxIrreversibleBlockHeight the max irreversible block height
     * @return effect rows number
     */
    public Integer updateBlockIrreversible(Long maxIrreversibleBlockHeight) {
        return nebBlockMapper.updateBlockIrreversible(maxIrreversibleBlockHeight);
    }

    /**
     * calculate block total number
     *
     * @return block total number
     */
    public long countBlockCnt() {
        return nebBlockMapper.count();
    }

    /**
     * According to miner hash query block number
     *
     * @param miner miner hash
     * @return the number of blocks
     */
    public Long countBlockCntByMiner(String miner) {
        if (StringUtils.isEmpty(miner)) {
            return 0L;
        }
        return nebBlockMapper.countByMiner(miner);
    }

    /**
     * Query the highest height value of the block
     *
     * @return the highest height value of the block
     */
    public Long getMaxHeight() {
        return nebBlockMapper.getMaxHeight();
    }

    /**
     * According to block height query block information
     *
     * @param height block height
     * @return block information
     */
    public NebBlock getNebBlockByHeight(Long height) {
        return nebBlockMapper.getByHeight(height);
    }

    /**
     * According to block hash query block information
     *
     * @param hash block hash
     * @return block information
     */
    public NebBlock getNebBlockByHash(String hash) {
        if (StringUtils.isEmpty(hash)) {
            return null;
        }
        return nebBlockMapper.getByHash(hash);
    }

    /**
     * Query block information
     *
     * @param page     current page
     * @param pageSize number of information per page
     * @return block page iterator
     */
    public PageIterator<NebBlock> findNebBlockPageIterator(int page, int pageSize) {
        long cnt = nebBlockMapper.count();
        PageIterator<NebBlock> pageIterator = PageIterator.create(page, pageSize, cnt);
        if (cnt > 0) {
            List<NebBlock> blkList = nebBlockMapper.findOrderByHeightDesc(pageIterator.getOffset(), pageIterator.getPageSize());
            pageIterator.setData(blkList);
        }
        return pageIterator;
    }

    /**
     * Query block information by miner
     *
     * @param miner    block miner
     * @param page     current page
     * @param pageSize number of information per page
     * @return block page iterator
     */
    public PageIterator<NebBlock> findNebBlockPageIteratorByMiner(String miner, int page, int pageSize) {
        long cnt = nebBlockMapper.countByMiner(miner);
        PageIterator<NebBlock> pageIterator = PageIterator.create(page, pageSize, cnt);
        if (cnt > 0) {
            List<NebBlock> blkList = nebBlockMapper.findByMiner(miner, pageIterator.getOffset(), pageIterator.getPageSize());
            pageIterator.setData(blkList);
        }
        return pageIterator;
    }

    /**
     * Query block information
     *
     * @param page     current page
     * @param pageSize number of information per page
     * @return block list
     */
    public List<NebBlock> findNebBlockOrderByHeight(int page, int pageSize) {
        return nebBlockMapper.findOrderByHeightDesc((page - 1) * pageSize, pageSize);
    }

    /**
     * Query block information by miner
     *
     * @param miner    block miner
     * @param page     current page
     * @param pageSize number of information per page
     * @return block list
     */
    public List<NebBlock> findNebBlockByMiner(String miner, int page, int pageSize) {
        if (StringUtils.isEmpty(miner)) {
            return Collections.emptyList();
        }
        return nebBlockMapper.findByMiner(miner, (page - 1) * pageSize, pageSize);
    }

    /**
     * Query block with block height between fromHeight and toHeight
     *
     * @param fromHeight the begin height
     * @param toHeight   the end height
     * @return block list
     */
    public List<NebBlock> findNebBlockBetweenHeight(long fromHeight, long toHeight) {
        return nebBlockMapper.findNebBlockBetweenHeight(fromHeight, toHeight);
    }

}
