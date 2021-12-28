package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.WarningMonitor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 预警图信息条件Vo
 * @author Chlf
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WarningMonitorVoAdd extends WarningMonitor {
    @ApiModelProperty(name = "amountLastYear", value = "上一年发生面积")
    private String amountLastYear;

    @ApiModelProperty(name = "amountChange", value = "比上一年发生面积变化")
    private String amountChange;

    @ApiModelProperty(name = "areaName", value = "地区名合称,如‘四川省-成都市-高新区’")
    private String areaName;

    @ApiModelProperty(name = "adLevel", value = "地图所在级别")
    private String adLevel;

    @ApiModelProperty(name = "adCode", value = "地图所在区划编码")
    private String adCode;

    @ApiModelProperty(name = "count", value = "区域总和")
    private String count;

}
