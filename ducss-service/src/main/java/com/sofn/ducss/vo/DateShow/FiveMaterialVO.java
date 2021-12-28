/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-30 15:15
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 五料化占比量
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "五料化占比量")
@NoArgsConstructor
@AllArgsConstructor
public class FiveMaterialVO {

    @ApiModelProperty(name = "mainFertilising", value = "肥料化")
    private BigDecimal fertilising;

    @ApiModelProperty(name = "mainForage", value = "饲料化")
    private BigDecimal forage;

    @ApiModelProperty(name = "mainFuel", value = "燃料化")
    private BigDecimal fuel;

    @ApiModelProperty(name = "mainBase", value = "基料化")
    private BigDecimal base;

    @ApiModelProperty(name = "mainMaterial", value = "原料化")
    private BigDecimal material;
}
