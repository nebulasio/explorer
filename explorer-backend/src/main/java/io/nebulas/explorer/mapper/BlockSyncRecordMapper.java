package io.nebulas.explorer.mapper;


import io.nebulas.explorer.domain.BlockSyncRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlockSyncRecordMapper {

    int add(BlockSyncRecord record);

    BlockSyncRecord getMaxConfirmedBlock();

    int updateById(BlockSyncRecord record);

    int updateByBlockHeight(@Param("blockHeight") Long blockHeight, @Param("txCnt") Long txCnt);
}