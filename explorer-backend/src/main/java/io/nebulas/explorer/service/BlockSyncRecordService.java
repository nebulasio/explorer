package io.nebulas.explorer.service;

import io.nebulas.explorer.domain.BlockSyncRecord;
import io.nebulas.explorer.mapper.BlockSyncRecordMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-26
 */
@Service
@AllArgsConstructor
public class BlockSyncRecordService {
    private BlockSyncRecordMapper blockSyncRecordMapper;

    public boolean add(BlockSyncRecord record) {
        return blockSyncRecordMapper.add(record) > 0;
    }

    public BlockSyncRecord getMaxConfirmedBlock() {
        return blockSyncRecordMapper.getMaxConfirmedBlock();
    }

    public Long getMaxConfirmedBlockHeight() {
        BlockSyncRecord record = getMaxConfirmedBlock();
        if (null == record) {
            return 1L;
        }
        return record.getBlockHeight();
    }

    public boolean updateById(BlockSyncRecord record) {
        return blockSyncRecordMapper.updateById(record) > 0;
    }

    public boolean setConfirmed(Long blkHeight, Long txCnt) {
        return blockSyncRecordMapper.updateByBlockHeight(blkHeight, txCnt) > 0;
    }

}
