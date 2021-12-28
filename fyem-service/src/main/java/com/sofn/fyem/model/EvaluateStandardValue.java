package com.sofn.fyem.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 流放指标评价分数表
 * @Author: mcc
 */
@TableName("EVALUATE_STANDARD_VALUE")
@Data
public class EvaluateStandardValue extends Model<EvaluateStandardValue> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "firstIndicatorId", value = "一级指标id")
    private String firstIndicatorId;

    @ApiModelProperty(name = "secondIndicatorId", value = "二级指标id")
    private String secondIndicatorId;

    @ApiModelProperty(name = "basicReleaseId", value = "增值放流基础数据id")
    private String basicReleaseId;

    @ApiModelProperty(name = "numericaValue", value = "数值")
    private Double numericaValue;

    @ApiModelProperty(name = "actualValue", value = "得分")
    private Double actualValue;


}