package com.sofn.ahhdp.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 10:47
 */
@ApiModel("名录管理对象1")
@Data
@TableName("DIRECTORIES_APPLY")
public class DirectoriesApply {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "所属地区")
    private String oldRegion;
    @ApiModelProperty(value = "所属地区code")
    private String oldRegionCode;
    @ApiModelProperty(value = "所属地区code")
    private String newRegionCode;
    @ApiModelProperty(value = "新所属地区")
    private String newRegion;
    @ApiModelProperty(value = "品种名称")
    private String oldName;
    @ApiModelProperty(value = "新品种名称")
    private String newName;
    @ApiModelProperty(value = "品种类别")
    private String  category;
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
