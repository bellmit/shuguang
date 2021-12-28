package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("所有省级地图数据vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceVo {

    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ApiModelProperty(name = "currentProtectionArea",value ="当前保护区总面积（公顷）" )
    private double currentProtectionArea;

    @ApiModelProperty(name = "coreRegionArea",value ="核心区面积(公顷)" )
    private double coreRegionArea;

    @ApiModelProperty(name = "experimentRegionArea",value ="实验区面积(公顷)" )
    private double experimentRegionArea;

    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String majorProtectObject;


}
