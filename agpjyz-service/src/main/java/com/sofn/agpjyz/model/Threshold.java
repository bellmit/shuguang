package com.sofn.agpjyz.model;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 阀值
 *
 * @Author yumao
 * @Date 2020/3/6 10:27
 **/
@Data
@TableName("THRESHOLD")
@ApiModel(value = "阀值对象")
public class Threshold {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "监测预警ID")
    private String warningId;
    @ApiModelProperty(value = "条件1数值1大于2小于3大于等于4小于等于5等于6不等于")
    private String case1;
    @ApiModelProperty(value = "条件1")
    private Double case1Value;
    @ApiModelProperty(value = "条件2数值1大于2小于3大于等于4小于等于5等于6不等于")
    private String case2;
    @ApiModelProperty(value = "条件2")
    private Double case2Value;
    @ApiModelProperty(value = "风险等级")
    private String riskLevel;
    @ApiModelProperty(value = "颜色标识")
    private String colorMark;


}
