package io.nebulas.explorer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import io.nebulas.explorer.domain.NebDipAward;

@Mapper
public interface NebDipAwardMapper {

    public int insert(NebDipAward award);

    public List<NebDipAward> queryByWeek(@Param("week") int week,
                                         @Param("weekYear") int weekYear,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);

    public long queryTotalAwardByWeek(@Param("week") int week,
                                      @Param("weekYear") int weekYear);

    public int countByWeek(@Param("week") int week,
                           @Param("weekYear") int weekYear);
}
