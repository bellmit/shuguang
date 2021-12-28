/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-30 17:50
 */
package com.sofn.ducss.vo.DateShow;

/**
 * 材料利用率Vo
 *
 * @author jiangtao
 * @version 1.0
 **/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 材料利用率Vo
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "材料利用率Vo")
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUtilizationVo {

    @ApiModelProperty(name = "mainFertilising", value = "市场化主体名称")
    private String mainName;

    @ApiModelProperty(name = "material", value = "原料")
    private BigDecimal material;

    @ApiModelProperty(name = "other", value = "调入量")
    private BigDecimal other;

    @ApiModelProperty(name = "totalMaterial", value = "总量")
    private BigDecimal totalMaterial;
}
