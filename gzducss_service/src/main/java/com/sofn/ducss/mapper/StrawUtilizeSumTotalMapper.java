package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.model.StrawUtilizeSumTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StrawUtilizeSumTotalMapper extends BaseMapper<StrawUtilizeSumTotal> {

    int deleteByPrimaryKey(Integer id);

    StrawUtilizeSumTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(StrawUtilizeSumTotal record);

    void deleteStrawUtilizeSum(StrawUtilizeSum strawUtilizeSum);

    void insertStrawUtilizeSumTotal(StrawUtilizeSum strawUtilizeSum);

    List<StrawUtilizeSumTotal> selectProduceUtilizeByAreaIds(@Param("childrenIds") List<String> childrenIds, @Param("year") String year, @Param("status") String status);

    List<StrawUtilizeSumTotal> selectProduceUtilizeByAreaIds2(@Param("childrenIds") List<String> childrenIds, @Param("year") String year, @Param("statusAll") List<String> statusAll);

    List<StrawUtilizeSumTotal> selectProduceUtilizeByAreaIds3( @Param("year") String year, @Param("statusAll") List<String> statusAll,@Param("regionYear") String regionYear);

    List<StrawUtilizeSumTotal> selectSumTotalByConditions(Map<String, String> params);
}