package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel("空间分析vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpatialAnalystResourcesVoList {

    @ApiModelProperty(name = "longitude",value ="经度(东经、西经)" )
    private String longitude;

    @ApiModelProperty(name = "latitude",value ="纬度(南纬、北纬)" )
    private String latitude;

    @ApiModelProperty(name = "currentProtectionArea",value ="当前保护区总面积（公顷）" )
    private double currentProtectionArea;

    @ApiModelProperty(name = "coreRegionArea",value ="核心区面积(公顷)" )
    private double coreRegionArea;

    @ApiModelProperty(name = "basinOrSeaArea",value ="所属流域或海区" )
    private String basinOrSeaArea;

    @ApiModelProperty(name = "riverOrMaritimeSpace",value ="所属水系或海域" )
    private String riverOrMaritimeSpace;

    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String majorProtectObject;

    @ApiModelProperty(name = "experimentRegionArea",value ="实验区面积(公顷)" )
    private String experimentRegionArea;

    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ApiModelProperty(name = "regionCode",value ="区域码" )
    private String regionCode;

}
