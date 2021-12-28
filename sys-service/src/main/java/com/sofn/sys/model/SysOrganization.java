package com.sofn.sys.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Created by sofn
 */
@ApiModel(value="组织管理对象")
@TableName("SYS_ORGANIZATION")
@Data
public class SysOrganization extends BaseModel<SysOrganization> {


    @ApiModelProperty(value = "组织机构名称")
    private String organizationName;
    /**
     * 父编号
     */
    @ApiModelProperty(value = "父编号")
    private String parentId;
    /**
     * 父编号列表，如1/2/
     */
    @ApiModelProperty(value = "父编号列表")
    private String parentIds;


    /**
     * 叶子节点
     */
    private String leaf;

    /**
     * 排序
     */
    private String priority;
    /**
     * 区域码
     */
    @ApiModelProperty(value = "区域码")
    @NotBlank(message = "区域码不能为空")
    private String regioncode;
    /**
     * 末级区域码
     */
    @ApiModelProperty(value = "末级区域码")
    private String regionLastCode;
    /**
     * 区域码名称
     */
    @ApiModelProperty(value = "区域码名称")
    private String regioncodeName;
    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private String organizationLevel;
    /**
     * 级别名称
     */
    @ApiModelProperty(value = "级别名称")
    private String organizationLevelName;
    /**
     * 是否第三方机构
     */
    @ApiModelProperty(value = "是否非行政机构（Y:行政机构/N:非行政机构）")
    private String thirdOrg;
    /**
     * 所在地址
     */
    @ApiModelProperty(value = "所在地址")
    private String address;
    /**
     * 地址区域码
     */
    @ApiModelProperty(value = "地址末级区域码")
    private String addressLastCode;
    /**
     * 所在地址名称
     */
    @ApiModelProperty(value = "所在地址名称")
    private String addressName;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String principal;

    /**
     * 负责人电话
     */
    @ApiModelProperty(value = "负责人电话")
    private String phone;

    /**
     * 机构职能
     */
    @ApiModelProperty(value = "机构职能")
    private String orgFunction;

    /**
     * 排序
     */
    private Integer sort;

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
    public String getParentIds() {
        return parentIds;
    }


    @TableField(exist = false)
    private String orgType;

}
