/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-31 16:24
 */
package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 14种作物的产量.可收集量,利用量,利用率查询返回Vo
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "14种作物的产量.可收集量,利用量,利用率查询返回Vo")
@NoArgsConstructor
@AllArgsConstructor
public class HistogramVo {

    @ApiModelProperty(name = "strawType", value = "作物类型")
    private String strawType;

    @ApiModelProperty(name = "grainYield", value = "粮食产量")
    private BigDecimal grainYield;

    @ApiModelProperty(name = "collectResource", value = "可收集量")
    private BigDecimal collectResource;

    @ApiModelProperty(name = "proStrawUtilize", value = "利用量")
    private BigDecimal proStrawUtilize;

    @ApiModelProperty(name = "comprehensive", value = "利用率")
    private BigDecimal comprehensive;
}
