package com.sofn.ducss.vo.DataAnalysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/8/24 11:30
 * @description
 **/
@Data
@ApiModel(value = "数据分析返回实体类")
public class DataKing implements Serializable {

    private static final long serialVersionUID = -1196757980524477972L;
    @ApiModelProperty(value = "排序id")
    private String id;

    @ApiModelProperty(value = "区域")
    private String area_Name;

    @ApiModelProperty(value = "类型")
    private String strawName;

    @ApiModelProperty(value = "年份")
    private String gtime;

    @ApiModelProperty(value = "指标数组")
    private HashMap<String, Map<String, Object>> indicatorArray = new HashMap<>();

    @ApiModelProperty(value = "指标数组最终")
    private HashMap<String, Map<String, Object>> indicatorArrays = new HashMap<>();
}
