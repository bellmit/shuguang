package com.sofn.fyrpa.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("资源环境信息vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentResourcesVo {

    @ApiModelProperty(name = "basinOrSeaArea",value ="所属流域或海区" )
    private String basinOrSeaArea;

    @ApiModelProperty(name = "riverOrMaritimeSpace",value ="所属水系或海域" )
    private String riverOrMaritimeSpace;

    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String majorProtectObject;

    @ApiModelProperty(name = "longitudeStart",value ="开始经度(东经、西经)" )
    private String longitudeStart;

    @ApiModelProperty(name = "longitudeEnd",value ="结束经度(东经、西经)" )
    private String longitudeEnd;

    @ApiModelProperty(name = "latitudeStart",value ="开始纬度(南纬、北纬)" )
    private String latitudeStart;

    @ApiModelProperty(name = "latitudeEnd",value ="结束纬度(南纬、北纬)" )
    private String latitudeEnd;

    @ApiModelProperty(name = "currentProtectionArea",value ="当前保护区总面积（公顷）" )
    private double currentProtectionArea;

    @ApiModelProperty(name = "coreRegionArea",value ="核心区面积(公顷)" )
    private double coreRegionArea;

    @ApiModelProperty(name = "experimentRegionArea",value ="实验区面积(公顷)" )
    private double experimentRegionArea;

    @ApiModelProperty(name = "longitudeRange",value ="经度范围名称" )
    private String longitudeRange;

    @ApiModelProperty(name = "latitudeRange",value ="纬度范围名称" )
    private String latitudeRange;

    @ApiModelProperty(name = "startTime",value ="开始时间" )
    private String startTime;

    @ApiModelProperty(name = "endTime",value ="结束时间" )
    private String endTime;

}