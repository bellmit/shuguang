package com.sofn.common.map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *@Description 地图视图数据
 *@Author quzhijie
 *@Date 2020/7/24 17:28
 *@Version 1.0
 **/
@ApiModel(value = "地图统计数据对象")
@Data
public class MapViewData implements Serializable {

  /**
   * 指标代码
   */
  private String indexCode;

  /**
   * 地图当前行政级别，根据行政级别返回该级别数据
   */
  @ApiModelProperty("地图当前行政级别")
  private String adLevel;

  /**
   * 地图视图类型
   * {@link com.sofn.common.map.MapViewType}
   */
  @ApiModelProperty("地图视图类型，描点：point，区域：area，全部[包括描点和区域]：all")
  private String viewType;

  /**
   * 地图行政区域视图数据列表
   */
  @ApiModelProperty("地图行政区域视图数据列表,Map的key表示维度")
  private Map<String,List<AdAreaData>> adAreaDataList;

  /**
   * 地图行政区域描点视图数据列表
   */
  @ApiModelProperty("地图行政区域描点视图数据列表,Map的key表示维度")
  private Map<String,List<AdPointData>> adPointDataList;

  /**
   * 统计数据
   */
  @ApiModelProperty("统计数据")
  private AdStatisticsData adStatisticsData;
}
