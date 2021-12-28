package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.StrawUtilize;
import com.sofn.ducss.vo.AggregateMainUtilizeQueryVo;
import com.sofn.ducss.vo.MainUtilizeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface StrawUtilizeMapper extends BaseMapper<StrawUtilize> {

    List<StrawUtilize> getStrawUtilizeByPage(Map<String, Object> params);

    List<StrawUtilize> getStrawUtilize(Map<String, Object> params);

    StrawUtilize selectStrawUtilizeById(@Param(value = "id") String id);

    String selectFarmerNo(@org.apache.ibatis.annotations.Param("year") String year, @org.apache.ibatis.annotations.Param("areaId") String areaId, @org.apache.ibatis.annotations.Param("type") String type);

    Integer insertStrawUtilize(StrawUtilize strawUtilize);

    Integer updateStrawUtilize(StrawUtilize strawUtilize);

    Integer deleteById(String id);

    String selectStrawUtilizeDetailIdByYear(Map<String, Object> params);

    List<MainUtilizeVo> selectMainUtilizeIdByRegionCode(@Param("year") String year, @Param("provinceId") String provinceId, @Param("cityId") String cityId, @Param("areaId") String areaId);

    List<MainUtilizeVo> selectMainUtilizeIdByAreaIds(AggregateMainUtilizeQueryVo vo);

    Integer getStrawUtilizeTotalCount(Map<String, Object> params);


    /**
     * 根据年份,区域集合获取市场化主体
     *
     * @param map 参数map
     * @return Integer 布尔类型
     */
    Integer getStrawUtilizeCountByCondition(Map<String, Object> map);

    /**
     * 根据年份,区域集合获取市场化主体并根据区域分组
     *
     * @param map 参数map
     * @return Integer 布尔类型
     */
    List<Integer> getStrawUtilizeCountGroupByArea(Map<String, Object> map);

    List<String> getMainNames(String countyId);

    Integer getCompanyCountByCondition(Map<String, Object> map);

    List<StrawUtilize> listGroupByYearAndAreaId(Map<String, Object> queryMap);
}