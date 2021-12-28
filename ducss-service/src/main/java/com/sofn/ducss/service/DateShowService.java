/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 9:38
 */
package com.sofn.ducss.service;

import com.sofn.common.map.MapViewData;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.vo.DateShow.*;
import com.sofn.ducss.vo.homePage.DataArea;
import com.sofn.ducss.vo.param.MaterialUtilizationPageParam;

import java.util.List;

/**
 * 数据展示接口
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface DateShowService {

    /**
     * 所选区域内近5年或近10年的利用量、利用率数据
     *
     * @param year                当前起始年份
     * @param type                1代表近5年,2代表近10年
     * @param areaCode            区域id
     * @param administrativeLevel 行政级别
     * @return boolean 布尔类型
     */
    List<ColumnLineVo> getColumnLine(String year, String type, String administrativeLevel, String areaCode);


    /**
     * 所选区域内填报完成率及历年利用率,利用量及利用可收集量
     *
     * @param year                当前起始年份
     * @param areaCode            区域id
     * @param administrativeLevel 行政级别
     * @return boolean 布尔类型
     */
    InstrumentAndRoketVo getInstrumentAndRoket(String year, String administrativeLevel, String areaCode);


    /**
     * 所选区域内当前年度五料化利用量
     *
     * @param year                当前起始年份
     * @param areaCode            区域id
     * @param administrativeLevel 行政级别
     * @return boolean 布尔类型
     */

    FiveMaterialVO getFiveMaterialVO(String year, String administrativeLevel, String areaCode,String searchStr);


    /**
     * 材料利用量列表
     *
     * @param   pageParam 附件上传分页参数
     * @return   PageUtils<SoilAttachmentUploadVo> 分页list
     */
    PageUtils<MaterialUtilizationVo> getMaterialInfo(MaterialUtilizationPageParam pageParam);


    /**
     * 14种作物的产量.可收集量,利用量,利用率查询
     *
     * @param   year 年份
     * @param   areaCode 区域id
     * @param   searchStr 搜索字段
     * @param   administrativeLevel 区域等级
     * @return
     */
    List<HistogramVo> getHistogramByCondition(String year,String areaCode,String searchStr,String administrativeLevel );

    /**
     * 地图展示数据
     *
     * @param   year 年份
     * @param   areaCode 区域id
     * @param   administrativeLevel 区域等级
     * @return
     */
    MapViewData getMapViewData(String year, String administrativeLevel, String areaCode);


    /**
     * 数据展示首页(数据范围 展示填报县,抽样分散户数,市场主体数)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   areaCode 区域id
     * @return DataArea
     */
    DataArea getDataAreaInDataShow(String year,String administrativeLevel,String areaCode);
    /**
     * 数据展示首页(秸秆资源数据)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   areaCode 区域id
     * @return StrawResourceDataVo
     */

    StrawResourceDataVo getStrawResourceDataVo(String year,String administrativeLevel,String areaCode);

    /**
     * 数据展示首页(点击不同数据范围显示不同地图样式)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   areaCode 区域id
     * @param   dataAreaType 数据范围类型
     * @return StrawResourceDataVo
     */
    MapViewData getDataAreaMapView(String year, String administrativeLevel, String areaCode,String dataAreaType);

    /**
     * 秸秆产生情况数据展示(柱图)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   areaCode 区域id
     * @param   dataType 数据类型
     * @param   strawType 秸秆类型
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getTheoryResource(String year,String administrativeLevel,String areaCode,String dataType,String strawType);


    /**
     * 秸秆产生情况数据展示(饼图) 根据秸秆类型分组
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   areaCode 区域id
     * @param   dataType 数据类型
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getTheoryResourceGroupByStrawType(String year,String administrativeLevel,String areaCode,String dataType);

    /**
     * 秸秆产生情况数据地图展示一(展示全国)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   areaCode 区域id
     * @param   dataType 数据范围
     * @return ColumnPieChartVo
     */
    MapViewData getTheoryResourceMapViewOne(String year,String administrativeLevel,String areaCode,String dataType);


    /**
     * 秸秆产生情况(六大区)数据展示(柱图)
     *
     * @param   year 年份
     * @param   areaCode 区域id
     * @param   dataType 数据类型
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getTheoryResourceBySixRegions(String year,String areaCode,String dataType);

    /**
     * 秸秆产生情况(六大区)数据展示(饼图)
     *
     * @param   year 年份
     * @param   areaCode 区域id
     * @param   dataType 数据类型
     * @param   strawType 数据类型
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getResourcePieBySixRegions(String year,String areaCode,String dataType,String strawType);

    /**
     * 秸秆产生情况数据地图展示二(展示六大区)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param   dataType 数据范围
     * @return ColumnPieChartVo
     */
    MapViewData getTheoryResourceMapViewTwo(String year,String administrativeLevel,String dataType);


    /**
     * 秸秆利用情况展示柱图(可选秸秆类型)(根据区域分组)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   dataType 数据范围
     * @param   strawType 秸秆类型
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getProStrawUtilize(String year,String administrativeLevel,String areaCode,String dataType,String strawType);

    /**
     * 当前区域秸秆利用情况展示柱图(根据秸秆类型分组,分别展示五料化,和其他8种数据类型)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   dataType 数据范围
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getProStrawUtilizeGroupByStrawType(String year,String administrativeLevel,String areaCode,String dataType);


    /**
     * 秸秆利用情况展示地图接口(根据区域分组)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   dataType 数据范围
     * @return MapViewData
     */
    MapViewData getProStrawUtilizeMapView(String year,String administrativeLevel,String areaCode,String dataType);


    /**
     * (六大区)秸秆利用情况展示柱图第一个(可选秸秆类型)(根据区域分组)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   dataType 数据范围
     * @param   strawType 秸秆类型
     * @return ColumnPieChartVo
     */
    List<ColumnPieChartVo> getProStrawUtilizeBySixRegions(String year,String administrativeLevel,String areaCode,String dataType,String strawType);

    /**
     * (六大区)秸秆利用情况展示饼图柱图第二个(可选秸秆类型)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   dataType 数据范围
     * @param   strawType 秸秆类型
     * @return ColumnPieChartVo
     */

    List<ColumnPieChartVo> getProStrawUtilizeBySixRegionsTwo(String year,String administrativeLevel,String areaCode,String dataType,String strawType);

    /**
     * 秸秆利用情况展示六大区地图接口(根据区域分组)
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   dataType 数据范围
     * @return MapViewData
     */
    MapViewData getProStrawUtilizeMapViewTwo(String year,String administrativeLevel,String areaCode,String dataType);


    /**
     * 数据展示,数据对比接口
     *
     * @param   year 年份
     * @param administrativeLevel 区域等级
     * @param areaCode 区域等级
     * @param   strawType 数据范围
     * @return MapViewData
     */
    DataCompareVo getDataCompare(String year,String administrativeLevel,String areaCode,String strawType);
}
