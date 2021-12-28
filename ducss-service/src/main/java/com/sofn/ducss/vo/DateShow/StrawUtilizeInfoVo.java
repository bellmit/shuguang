/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-30 18:14
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 市场主体材料
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "火箭图Vo")
@NoArgsConstructor
@AllArgsConstructor
public class StrawUtilizeInfoVo {

    @ApiModelProperty(name = "mainName", value = "市场主体名称")
    private String mainName;

    @ApiModelProperty(name = "fertilising", value = "肥料化")
    private BigDecimal fertilising;

    @ApiModelProperty(name = "forage", value = "饲料化")
    private BigDecimal forage;

    @ApiModelProperty(name = "fuel", value = "燃料化")
    private BigDecimal fuel;

    @ApiModelProperty(name = "base", value = "基料化")
    private BigDecimal base;

    @ApiModelProperty(name = "material", value = "原料化")
    private BigDecimal material;

    @ApiModelProperty(name = "other", value = "外县购入")
    private BigDecimal other;


}
