package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:16
 */
@Data
@TableName("THRESHOLD")
@ApiModel(value = "阀值对象")
public class Threshold {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "监测预警ID")
    private String warningId;

    @Pattern(regexp = "[1-6]?")
    @ApiModelProperty(value = "条件1数值1大于2小于3大于等于4小于等于5等于6不等于")
    private String case1;

    @Range
    @ApiModelProperty(value = "条件1")
    private Double case1Value;

    @Pattern(regexp = "[1-6]?")
    @ApiModelProperty(value = "条件2数值1大于2小于3大于等于4小于等于5等于6不等于")
    private String case2;

    @Range
    @ApiModelProperty(value = "条件2")
    private Double case2Value;

    @Pattern(regexp = "[1-4]?")
    @ApiModelProperty(value = "风险等级")
    private String riskLevel;

    @ApiModelProperty(value = "颜色标识")
    private String colorMark;


}