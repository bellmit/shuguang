package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 一级指标
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class FirstEvaluateIndicatorVo {

    @ApiModelProperty(name = "firstId", value = "一级指标id")
    private String firstId;

    @ApiModelProperty(name = "indicatorName", value = "指标名称")
    private String indicatorName;

    private List<SecondEvaluateIndicatorVo> secondEvaluateIndicatorList;
}
