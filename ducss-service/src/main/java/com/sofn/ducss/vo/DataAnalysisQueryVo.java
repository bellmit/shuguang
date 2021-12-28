package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/8/3  9:44
 *  数据分析查询条件封装类
 **/
@Data
@ApiModel("数据分析查询条件封装类")
public class DataAnalysisQueryVo implements Serializable {

    private static final long serialVersionUID = 2314303247994775976L;
    @ApiModelProperty(value = "所属年度")
    private List<String> year;

    @ApiModelProperty(value = "所属区域")
    private List<String> area;

    @ApiModelProperty(value = "作物类型")
    private List<String> cropType;

    @ApiModelProperty(value = "分析指标")
    private List<String> analysisIndex;

}
