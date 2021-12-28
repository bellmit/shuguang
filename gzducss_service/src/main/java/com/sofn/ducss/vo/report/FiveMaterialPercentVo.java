/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-16 16:32
 */
package com.sofn.ducss.vo.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 五料化比例实体
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "五料化比例实体")
@NoArgsConstructor
@AllArgsConstructor
public class FiveMaterialPercentVo {

    @ApiModelProperty(name = "areaId", value = "省份id")
    private String areaId;

    @ApiModelProperty(name = "areaName", value = "大区名称")
    private String areaName;

    @ApiModelProperty(name = "fertilisingPercent", value = "肥料化利用比例")
    private BigDecimal fertilisingPercent;

    @ApiModelProperty(name = "foragePercent", value = "饲料化利用比例")
    private BigDecimal foragePercent;

    @ApiModelProperty(name = "fuelPercent", value = "燃料化利用比例")
    private BigDecimal fuelPercent;

    @ApiModelProperty(name = "basePercent", value = "基料化利用比例")
    private BigDecimal basePercent;

    @ApiModelProperty(name = "materialPercent", value = "原料化利用比例")
    private BigDecimal materialPercent;
}
