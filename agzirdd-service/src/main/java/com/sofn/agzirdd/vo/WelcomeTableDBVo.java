package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 数据图表，数据库查询结果Vo
 */
@Data
public class WelcomeTableDBVo {

    @ApiModelProperty(value="年份")
    private String dataYear;

    @ApiModelProperty(value="值")
    private BigDecimal dataVal;

    @ApiModelProperty(value="区域ID")
    private String areaId;
}
