package com.sofn.fyrpa.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("水产种质资源保护区信息对象volist")
@Data
public class AquaticResourcesProtectionInfoVoList {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "submitTime",value ="填报年度" )
    private String submitTime;

    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ApiModelProperty(name = "longitude",value ="经度(东经、西经)" )
    private String longitude;

    @ApiModelProperty(name = "latitude",value ="纬度(南纬、北纬)" )
    private String latitude;

    @ApiModelProperty(name = "currentProtectionArea",value ="保护区总面积(公顷)" )
    private String  currentProtectionArea;

    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String  majorProtectObject;

    @ApiModelProperty(name = "approveTime",value ="当前级别批准时间" )
    @JSONField(format = "yyyy/MM/dd")
    private Date approveTime;

    @ApiModelProperty(name = "approveDocNumber",value ="当前级别批准文号" )
    private String approveDocNumber;

    @ApiModelProperty(name = "managerOrgName",value ="管理机构名称" )
    private String managerOrgName;

    @ApiModelProperty(name = "submitUnit",value ="填报单位" )
    private String submitUnit;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "createUserId",value ="用户id" )
    private String createUserId;

    @ApiModelProperty(name = "areaName",value ="区域名称" )
    private String areaName;

   }