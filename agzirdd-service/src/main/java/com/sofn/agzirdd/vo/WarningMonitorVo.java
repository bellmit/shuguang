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
public class WarningMonitorVo extends WarningMonitor {

    @ApiModelProperty(name = "color", value = "十六进颜色值，如：#FFB6C1;前端取这个值显示在地图上")
    private String color="#FCF9F2";

    @ApiModelProperty(name = "areaName", value = "地区名合称,如‘四川省-成都市-高新区’")
    private String areaName;

}
