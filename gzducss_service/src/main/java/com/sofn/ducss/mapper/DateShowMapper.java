/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 11:01
 */
package com.sofn.ducss.mapper;

import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.vo.DateShow.ColumnPieChartVo;
import com.sofn.ducss.vo.DateShow.HistogramVo;
import com.sofn.ducss.vo.DateShow.MaterialUtilizationVo;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 数据展示Mapper
 *
 * @author jiangtao
 * @version 1.0
 **/
@Component
public interface DateShowMapper {

    /**
     * 所选区域内近5年或近10年的利用量、(等其他数据)数据 年份分组
     *
     * @param   map 参数map
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSumResVo> getColumnLineData(Map<String,Object> map);

    /**
     * 所选当前完成数据情况
     *
     * @param   map 参数map
     * @return  boolean 布尔类型
     */
    Integer getCompleteCountByCondition(Map<String,Object> map);

    /**
     * 所选区域当前材料利用量列表
     * @param   map 参数map
     * @return  boolean 布尔类型
     */
    List<MaterialUtilizationVo> getMaterialInfo(Map<String,Object> map);


    /**
     * 所选区域当前年度粮食产量
     *
     * @param   map 参数map
     * @return  boolean 布尔类型
     */
    List<HistogramVo> getGrainYieldByCondition(Map<String,Object> map);

    /**
     * 所选区域当前年度可收集量,利用量,利用率,粮食利用量
     *
     * @param   map 参数map
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSum> getOtherProduction(Map<String,Object> map);

    /**
     * 所选区域内利用量等数据根据区域分组
     *
     * @param   map 参数map
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSumResVo> getDataByAreaCode(Map<String,Object> map);

    /**
     * 所选区域内产生量,可收集量,利用量,利用率等数据
     *
     * @param   year
     * @param   areaCodes
     * @param   status 状态集合
     *
     * @return  boolean 布尔类型
     */
    StrawUtilizeSumResVo getStrawResourceData(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("status") List<String> status);

    /**
     * 数据展示首页,市场主体根据等级不同地图展示
     *
     * @param   year   年份
     * @param   areaCodes 区域集合
     * @param   status 状态集合
     * @param   dataType 数据类型
     *
     * @return   List<ColumnPieChartVo> sum数组
     */
    List<ColumnPieChartVo> getReportCountyNumByAdministrativeLevel(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType);
    /**
     * 数据展示首页,市场主体根据等级不同地图展示
     *
     * @param   year   年份
     * @param   areaCodes 区域集合
     * @param   status 状态集合
     * @param   dataType 数据类型
     *
     * @return   List<ColumnPieChartVo> sum数组
     */
    List<ColumnPieChartVo> getStrawUtilizeNumByAdministrativeLevel(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType);

    /**
     * 数据展示首页,分散农户利用量根据等级不同地图展示
     *
     * @param   year   年份
     * @param   areaCodes 区域集合
     * @param   status 状态集合
     * @param   dataType 数据类型
     *
     * @return   List<ColumnPieChartVo> sum数组
     */
    List<ColumnPieChartVo> getDisperseUtilizeNumByAdministrativeLevel(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType);
    /**
     * 所选区域内产生量,可收集量,利用量,利用率等数据根据区域分组(产生量,收集量柱图)
     *
     * @param   year   年份
     * @param   areaCodes 区域集合
     * @param   status 状态集合
     * @param   dataType 数据类型
     *
     * @return   List<ColumnPieChartVo> sum数组
     */
    List<ColumnPieChartVo> getStrawResourceDataGroupByAreaId(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType);


    /**
     * 所选区域当前年度可收集量,利用量,利用率,粮食利用量根据秸秆类型分组(产生量,收集量柱图)
     * @param year  年份
     * @param areaCodes  区域集合
     * @param status 状态集合
     * @param dataType 数据类型
     *
     * @return  boolean 布尔类型
     */
    List<ColumnPieChartVo> getStrawResourceDataByPie(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType);

    /**
     * 所选区域当前年度(产生量,收集量柱图)
     * @param year  年份
     * @param areaCodes  区域集合
     * @param status 状态集合
     * @param dataType 数据类型
     * @param strawType 数据类型
     * @return  List<ColumnPieChartVo> 数组
     */
    ColumnPieChartVo getStrawResourceByPie(@Param("year") String year, @Param("areaCodes") List<String> areaCodes, @Param("status") List<String> status, @Param("dataType") String dataType
    ,@Param("strawType") String strawType);

    /**
     * 所选区域内产生量,可收集量,利用量,利用率等数据根据区域分组
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSumResVo> getStrawUtilizeByAreaCode(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("status") List<String> status);


    /**
     * 所选区域内利用情况根据秸秆类型获取数据并根据区域分组
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     * @param   strawType 秸秆类型
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSum> getStrawSumByStrawType(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("status") List<String> status,@Param("strawType") String strawType);

    /**
     * 所选区域内利用情况获取数据并根据秸秆类型分组
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     *
     * @return  boolean 布尔类型
     */
    List<StrawUtilizeSum> getStrawSumGroupByStrawType(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("status") List<String> status);

    /**
     * 所选区域内利用情况根据秸秆类型获取数据不分组用于六大区
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   status 状态集合
     * @param   strawType 秸秆类型
     *
     * @return  boolean 布尔类型
     */
    StrawUtilizeSum getStrawSum(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("status") List<String> status,@Param("strawType") String strawType);


    /**
     * 所选区域集合(及指定秸秆类型)的播种面积
     *
     * @param   year 年份
     * @param   areaCodes  区域集合
     * @param   strawType 秸秆类型
     *
     * @return  boolean 布尔类型
     */
    ColumnPieChartVo getSeedArea(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("strawType") String strawType);


    /**
     * 获取指定数据是否审核通过
     *
     * @param   map 条件map
     *
     * @return  boolean 布尔类型
     */
    Integer getAuditNumByCondition(Map<String,Object> map);

    /**
     * 查询下级审核通过的区域id
     *
     * @param year 年份
     * @param areaCodes 区域ids
     * @param status 区域ids
     *
     * @return  List<String> 区域ids
     */
    List<String> getAuditOrReportAreaIds(@Param("year") String year,@Param("areaCodes") List<String> areaCodes,@Param("statues") List<String> status);
}
