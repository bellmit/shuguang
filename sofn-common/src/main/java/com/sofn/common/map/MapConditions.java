package com.sofn.common.map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *@Description 筛选条件对象
 *@Author quzhijie
 *@Date 2020/7/24 18:09
 *@Version 1.0
 **/
@ApiModel(value = "筛选条件对象")
@Data
public class MapConditions {
  /**
   * 筛选条件代码，例如年限筛选：year
   */
  @ApiModelProperty("筛选条件代码，例如年限筛选：year")
  private String code;

  /**
   * 筛选条件之列表
   */
  @ApiModelProperty("筛选条件之列表")
  private Map<String,String> valueList;
}
