package com.sofn.fyrpa.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("资源环境信息对象")
@Data
@TableName(value = "environment_resources")
public class EnvironmentResources {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "basinOrSeaArea",value ="所属流域或海区" )
    private String basinOrSeaArea;

    @ApiModelProperty(name = "riverOrMaritimeSpace",value ="所属水系或海域" )
    private String riverOrMaritimeSpace;

    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String majorProtectObject;

    @ApiModelProperty(name = "longitude",value ="经度(东经、西经)" )
    private String longitude;

    @ApiModelProperty(name = "latitude",value ="纬度(南纬、北纬)" )
    private String latitude;

    @ApiModelProperty(name = "currentProtectionArea",value ="当前保护区总面积（公顷）" )
    private double currentProtectionArea;

    @ApiModelProperty(name = "coreRegionArea",value ="核心区面积(公顷)" )
    private double coreRegionArea;

    @ApiModelProperty(name = "experimentRegionArea",value ="实验区面积(公顷)" )
    private double experimentRegionArea;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    private Date createTime;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    @ApiModelProperty(name = "startTime",value ="开始时间" )
    private Date startTime;

    @ApiModelProperty(name = "endTime",value ="结束时间" )
    private Date endTime;

}