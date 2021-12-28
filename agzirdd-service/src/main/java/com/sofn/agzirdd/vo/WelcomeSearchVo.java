package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WelcomeSearchVo {

    @ApiModelProperty(value="年份")
    @NotBlank(message = "年份必传")
    private String year;

    @ApiModelProperty(value="物种名称")
    private String speciesName;

    @ApiModelProperty(value="行政级别,ad_ministry部级、ad_province省、ad_city市",allowableValues = "ad_ministry,ad_province,ad_city")
    private String adLevel;

    @ApiModelProperty(value="行政代码")
    private String adCode;

    @ApiModelProperty(value="指标分类名称",hidden = true)
    private String classificationName;
}
