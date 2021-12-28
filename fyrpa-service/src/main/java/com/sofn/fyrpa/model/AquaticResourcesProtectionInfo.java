package com.sofn.fyrpa.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("水产种质资源保护区信息对象")
@Data
@TableName(value = "aquatic_resources_protection_info")
public class AquaticResourcesProtectionInfo {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "regionCode",value ="保护区所在的地区code" )
    private String regionCode;

    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ApiModelProperty(name = "departmentManager",value ="主管部门" )
    private String departmentManager;

    @ApiModelProperty(name = "approveTime",value ="当前级别批准时间" )
    private Date approveTime;

    @ApiModelProperty(name = "approveDocNumber",value ="当前级别批准文号" )
    private String approveDocNumber;

    @ApiModelProperty(name = "isAdjust",value ="保护区设立以来是否有过调整" )
    private String isAdjust;

    @ApiModelProperty(name = "adjustTime",value ="调整时间" )
    private Date adjustTime;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    private Date createTime;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    @ApiModelProperty(name = "managerOrgId",value ="管理部门id" )
    private String managerOrgId;

    @ApiModelProperty(name = "environmentResourcesId",value ="环境资源id" )
    private String environmentResourcesId;

    @ApiModelProperty(name = "fileIds",value ="文件id" )
    private String fileIds;

    @ApiModelProperty(name = "submitTime",value ="报送时间" )
     private String submitTime;

    @ApiModelProperty(name = "submitUnit",value ="填报单位" )
     private String submitUnit;

    @ApiModelProperty(name = "totalScore",value ="总评分" )
    private double totalScore;

    @ApiModelProperty(name = "isFlag",value ="是否启用" )
    private String isFlag;

    @ApiModelProperty(name = "createUserId",value ="用户id" )
    private String createUserId;

    @ApiModelProperty(name = "areaName",value ="区域名称" )
    private String areaName;

    @ApiModelProperty(name = "reportTime",value ="上报时间" )
    private Date reportTime;
}