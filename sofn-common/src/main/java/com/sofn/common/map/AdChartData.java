package com.sofn.common.map;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *@Description 行政区域图表数据对象
 *@Author quzhijie
 *@Date 2020/7/24 18:09
 *@Version 1.0
 **/
@Data
public class AdChartData {

  /**
   * 统计维度列表
   */
  private List<String> dimensionList;

  /**
   * 统计表头名称列表
   */
  private Map<String,String> headerMap;

  /**
   * 统计数据列表
   */
  private List<Map<String,Object>> dataList;
}
