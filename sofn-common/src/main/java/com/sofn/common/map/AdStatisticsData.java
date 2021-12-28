package com.sofn.common.map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *@Description 行政区域统计数据对象
 *@Author quzhijie
 *@Date 2020/7/24 18:09
 *@Version 1.0
 **/
@ApiModel(value = "行政区域统计数据对象")
@Data
public class AdStatisticsData implements Serializable {

  /**
   * 统计维度列表,
   * key:维度标识，value：维度名称
   */
  @ApiModelProperty("统计维度列表,key:维度标识，value：维度名称")
  private Map<String,String> dimensionList;

  /**
   * 统计表头名称列表,
   * key:表头字段标识,value:表头字段名称
   */
  @ApiModelProperty("统计表头名称列表,key:表头字段标识,value:表头字段名称")
  private Map<String,String> headerMap;

  /**
   * 统计数据列表
   * 外部Map key：维度标识，value：维度数据列表；
   * 内部Map key: 表头字段标识，value：字段值
   */
  @ApiModelProperty("统计数据列表,外部Map key：维度标识，value：维度数据列表；内部Map key: 表头字段标识，value：字段值")
  private Map<String,List<Map<String,Object>>> dataMap;

}
