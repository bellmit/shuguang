/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-13 15:13
 */
package com.sofn.ducss.vo.DataAnalysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据分析转换实体类
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "数据分析转换实体类")
public class DataKingChanVo implements Serializable {
    @ApiModelProperty(value = "排序id")
    private String id;

    @ApiModelProperty(value = "区域")
    private String area_Name;

    @ApiModelProperty(value = "秸秆类型排序id")
    private Integer strawTypeSortId;

    @ApiModelProperty(value = "")
    private String strawName;

    @ApiModelProperty(value = "年份")
    private String gtime;

    @ApiModelProperty(value = "指标数组")
    private HashMap<String, Map<String, Object>> indicatorArray = new HashMap<>();

    @ApiModelProperty(value = "指标数组最终")
    private HashMap<String, Map<String, Object>> indicatorArrays = new HashMap<>();
}
