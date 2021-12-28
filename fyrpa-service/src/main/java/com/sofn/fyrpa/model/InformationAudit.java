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

@ApiModel("信息审核对象")
@Data
@TableName(value = "information_audit")
public class InformationAudit {

    @ApiModelProperty(name = "id",value ="主键id" )
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(name = "auditMind",value ="审核意见" )
    private String auditMind;

    @ApiModelProperty(name = "checker",value ="审核人" )
    private String checker;

    @ApiModelProperty(name = "auditUnit",value ="审核单位" )
    private String auditUnit;

    @ApiModelProperty(name = "auditTime",value ="审核时间" )
    @JSONField(format = "yyyy-MM-dd")
    private Date auditTime;

    @ApiModelProperty(name = "status",value ="状态" )
    private String status;

    @ApiModelProperty(name = "createTime",value ="创建时间" )
    private Date createTime;

    @ApiModelProperty(name = "isDel",value ="删除标识" )
    private String isDel;

    private String protectionInfoId;


}