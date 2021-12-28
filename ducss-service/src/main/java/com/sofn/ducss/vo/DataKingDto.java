package com.sofn.ducss.vo;

import com.sofn.ducss.model.DataAnalysisProvice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@ApiModel(value = "分析展示 dto")
public class DataKingDto {
    private static final long serialVersionUID = -1196757980524477972L;
    @ApiModelProperty(value = "排序id")
    private String id;

    @ApiModelProperty(value = "区域")
    private String areaName;

    @ApiModelProperty(value = "区域code")
    private String regionCode;

    @ApiModelProperty(value = "指标数组最终")
    private  Map<String, Map<String,Object>> indicatorArrays = new HashMap<>();

}
