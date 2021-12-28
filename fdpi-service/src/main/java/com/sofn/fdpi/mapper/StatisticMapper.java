package com.sofn.fdpi.mapper;

import com.sofn.fdpi.vo.StatisticVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 统计报表的mapper
 */
public interface StatisticMapper {
    //获取物种分布统计
    List<StatisticVo> statisticSpeciesTypeList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("provinceCode") String provinceCode, @Param("cityCode") String cityCode, @Param("areaCode") String areaCode, @Param("speciesId") String speciesId, @Param("groupBySqlStr") String groupBySqlStr, @Param("regionJoinOnSqlStr") String regionJoinOnSqlStr, @Param("regionType") String regionType);

    //获取物种标识数量统计
    List<StatisticVo> statisticSpeciesSignboardList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("provinceCode") String provinceCode, @Param("cityCode") String cityCode, @Param("areaCode") String areaCode, @Param("speciesId") String speciesId, @Param("groupBySqlStr") String groupBySqlStr, @Param("regionJoinOnSqlStr") String regionJoinOnSqlStr, @Param("regionType") String regionType);

    //获取新注册企业数量统计
    List<StatisticVo> statisticRegisterCompList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("provinceCode") String provinceCode, @Param("cityCode") String cityCode, @Param("areaCode") String areaCode, @Param("groupBySqlStr") String groupBySqlStr, @Param("regionJoinOnSqlStr") String regionJoinOnSqlStr, @Param("regionType") String regionType);
}
