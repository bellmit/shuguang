package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "调查信息VO类")
public class SurveyInfoVo {

    @ApiModelProperty(value = "物种ID")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specValue;
    @ApiModelProperty(value = "分布面积")
    private Double distribution;
    @ApiModelProperty(value = "种群数量")
    private Integer amount;
}
