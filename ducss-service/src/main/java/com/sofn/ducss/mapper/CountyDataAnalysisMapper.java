/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-14 17:44
 */
package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据分析县级Mapper
 *
 * @author jiangtao
 * @version 1.0
 **/
@Component
public interface CountyDataAnalysisMapper extends BaseMapper<DataAnalysisArea> {

    List<DataAnalysisArea> listByYearAndAreaIdsAndTotolRateTooMuch(@Param("year") String year, @Param("areaIds") List<String> areaIds);

    /**
     * 查询秸秆生产量与直接还田量
     *
     * @param year    年
     * @param areaId  区域id
     * @param statues 状态集合
     * @return List<ProStillDetail> 布尔类型
     */
    List<ProStillDetail> getProStillDetailListByAreaId(@Param("year") String year, @Param("areaId") String areaId, @Param("statues") List<String> statues);


    /**
     * 查询分散利用量
     *
     * @param year   年
     * @param areaId 区域id
     * @return List<ProStillDetail> 布尔类型
     */
    List<DisperseUtilizeDetail> selectDetailByAreaId(@Param("year") String year, @Param("areaId") String areaId);

    /**
     * 市场主体规模利用量
     *
     * @param year   年
     * @param areaId 区域id
     * @return List<ProStillDetail> 布尔类型
     */
    List<StrawUtilizeDetail> selectDetailSumByAreaId(@Param("year") String year, @Param("areaId") String areaId);


    /**
     * 从县级表中查询市级汇总数据分秸秆类型
     *
     * @param year     年
     * @param areaList 县级区域集合
     * @return List<DataAnalysisCity> 布尔类型
     */
    List<DataAnalysisCity> getDataAnalysisCity(@Param("year") String year, @Param("areaList") List<String> areaList);

    /**
     * 从市级表中查询省级汇总数据分秸秆类型
     *
     * @param year     年
     * @param areaList 县级区域集合
     * @return List<DataAnalysisProvice> 布尔类型
     */
    List<DataAnalysisProvice> getDataAnalysisProvice(@Param("year") String year, @Param("areaList") List<String> areaList);
}
