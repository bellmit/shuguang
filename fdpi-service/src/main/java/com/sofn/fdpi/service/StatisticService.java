package com.sofn.fdpi.service;

import com.sofn.common.map.MapConditions;
import com.sofn.common.map.MapViewData;

import java.util.List;

public interface StatisticService {

    /**
     * 获取页面加载的下拉数据
     * wXY
     * 2020-7-30 18:28:35
     * @return List<MapConditions>
     */
    List<MapConditions> getMapConditions();

    /**
     * 获取地图视图数据
     * @param index 指标
     * @param adLevel 行政级别{@link com.sofn.common.map.MapAdLevel}
     * @param adCode 行政区域代码或行政区域名称
     * @param speciesId 物种id
     * @param year 年度
     */
    MapViewData getMapViewData(String index, String adLevel, String adCode,String speciesId,String year);

}
