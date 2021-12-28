package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @Description: 二级指标
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class SecondEvaluateIndicatorVo {

    @ApiModelProperty(name = "parentId", value = "父级id(所属一级指标id->二级指标必须选择)")
    private String parentId;

    @ApiModelProperty(name = "secondId", value = "二级指标id")
    private String secondId;

    @ApiModelProperty(name = "basicReleaseId", value = "增值放流基础数据id")
    private String basicReleaseId;

    @ApiModelProperty(name = "indicatorName", value = "指标名称")
    private String indicatorName;

    @ApiModelProperty(name = "referenceValue", value = "参考值(评价值)")
    private Double referenceValue;

    @ApiModelProperty(name = "totalValue", value = "分值(总分值)")
    private Double totalValue;

    @ApiModelProperty(name = "valueId", value = "指标评价分数表Id")
    private String valueId;

    @ApiModelProperty(name = "numericaValue", value = "数值")
    private Double numericaValue;

    @ApiModelProperty(name = "actualValue", value = "得分")
    private Double actualValue;

}
