package com.sofn.ducss.service;

import com.sofn.ducss.map.MapViewData;
import com.sofn.ducss.vo.DateShow.ColumnPieChartVo;

import java.util.List;
import java.util.Map;

/**
 * 还田离田情况地图相关接口
 */
public interface LeavingMapService {

    /**
     * 根据数据类型查询数据
     * @param year  年度
     * @param areaCode  区划代码
     * @param dataType   1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例
     * @return  Map<String, Map<String, Object>>   KEY 为区划代码    VALUE为Map<String, Object>
     *          RETURNRATIO  数据库直接计算的比例
     *          离田还田地图的公式一般为X/Y * 100   VALUE1=X ， VALUE2=Y 可用于计算合计的
     */
    Map<String, Map<String, Object>> getValueByDataType(String year,String areaCode,String dataType, String strawType);


    /**
     * 根据不同的数据类型获取不同的比例用于显示在地图上
     * @param year   父级区划ID
     * @param administrativeLevel   行政级别
     * @param areaCode   区划代码
     * @param dataType   1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例
     * @param isSixRegion   是否是显示六大区数据
     * @param sixRegionCodes   六大区某个区包含的省的ID
     * @param strawType   指标类型
     * @return  MapViewData
     */
    MapViewData getLeavingRatio(String year,String administrativeLevel,String areaCode,String dataType,boolean isSixRegion,String sixRegionCodes, String strawType);

    /**
     * 根据不同的数据类型获取不同的比例用于显示在柱状图上
     * @param year   年份
     * @param areaCode  区划代码
     * @param dataType   1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例
     * @param isSixRegion  isSixRegion   是否是显示六大区数据
     * @param sixRegionCodes   六大区某个区包含的省的ID
     * @param strawType   指标类型
     * @return  List<ColumnPieChartVo>  柱状图数据
     */
    List<ColumnPieChartVo> getColumnPieChartVo(String year,String areaCode,String dataType,boolean isSixRegion,String sixRegionCodes, String strawType);


    /**
     * 展示饼图
     * @param year  年度
     * @param areaCode  区划代码
     * @param dataType   1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例
     * @param isSixRegion   是否显示六大区数据
     * @param sixRegionCodes   如果不传入直接显示六大区的数据  如果传入就只显示这些区域的数据
     * @return List<ColumnPieChartVo>
     */
    List<ColumnPieChartVo> getLeavingPieChart(String year,String areaCode,String dataType,boolean isSixRegion, String sixRegionCodes );



}
