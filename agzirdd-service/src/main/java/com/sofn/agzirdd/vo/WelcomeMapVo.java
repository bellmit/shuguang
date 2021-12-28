package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WelcomeMapVo {

    @ApiModelProperty(value = "区域ID")
    private String areaId;

    @ApiModelProperty(value = "物种类型")
    private String speciesType;

    @ApiModelProperty(value = "物种名称")
    private String speciesName;
}
