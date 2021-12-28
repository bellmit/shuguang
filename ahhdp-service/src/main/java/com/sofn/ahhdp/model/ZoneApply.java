package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author yumao
 * @Date 2020/4/17 11:15
 **/
@ApiModel("保护区申请对象")
@Data
@TableName("ZONE_APPLY")
public class ZoneApply {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "保护区名称")
    private String areaName;
    @ApiModelProperty(value = "新保护区名称")
    private String newName;
    @ApiModelProperty(value = "建设单位")
    private String company;
    @ApiModelProperty(value = "新建设单位")
    private String newCompany;
    @ApiModelProperty(value = "保护区范围")
    private String areaRange;
    @ApiModelProperty(value = "新保护区范围")
    private String newRange;
//    @ApiModelProperty(value = "状态 1进行中0完成")
//    private String status;
    @ApiModelProperty(value = "审核状态 1通过0不通过")
    private String auditStatus;
    @ApiModelProperty(value = "审核状态名称")
    @TableField(exist = false)
    private String auditStatusName;
    @ApiModelProperty(value = "变更人")
    private String operator;
    @ApiModelProperty(value = "变更时间")
    private Date changeTime;
    @ApiModelProperty(value = "审核意见")
    private String opinion;
    @ApiModelProperty(value = "审核人")
    private String auditor;
    @ApiModelProperty(value = "审核时间")
    private Date auditTime;
    @ApiModelProperty(value = "能否审核")
    @TableField(exist = false)
    private Boolean canAudit;
    @ApiModelProperty(value = "能否编辑(申请)")
    @TableField(exist = false)
    private Boolean canEdit;
}

