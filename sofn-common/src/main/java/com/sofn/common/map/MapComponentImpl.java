package com.sofn.common.map;

import java.util.List;
import java.util.Map;

/**
 *@Description 地图组件所需接口
 *@Author quzhijie
 *@Date 2020/7/24 16:58
 *@Version 1.0
 **/
public interface MapComponentImpl {

  /**
   * 获取地图视图数据
   * @param index 指标
   * @param adLevel 行政级别{@link com.sofn.common.map.MapAdLevel}
   * @param adCode 行政区域代码或行政区域名称
   * @param conditions 条件
   */
  MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String,String> conditions);

  /**
   * 获取筛选条件值列表
   * @param index 指标
   */
  List<MapConditions> getMapConditions(String index);

}
