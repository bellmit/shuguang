package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "趋势VO对象")
public class TrendVo {

    @ApiModelProperty(value = "年份")
    private String year;
    @ApiModelProperty(value = "分布面积")
    private Double distribution;
    @ApiModelProperty(value = "种群数量")
    private Integer amount;
}
