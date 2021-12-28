package com.sofn.ahhrm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:16
 */
@Data
@TableName("threshold_sub")
@ApiModel(value = "阀值条件对象")
public class ThresholdSub {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "阈值ID：前端不用管")
    private String thresholdId;
    @ApiModelProperty(value = "条件1数值1大于2小于3大于等于4小于等于5等于6不等于")
    private String condition1;
    @ApiModelProperty(value = "条件1")
    private Double val1;
    @ApiModelProperty(value = "条件2数值1大于2小于3大于等于4小于等于5等于6不等于")
    private String condition2;
    @ApiModelProperty(value = "条件2")
    private Double val2;
    @ApiModelProperty(value = "风险等级")
    private String grade;
    @ApiModelProperty(value = "颜色标识")
    private String color;
}
