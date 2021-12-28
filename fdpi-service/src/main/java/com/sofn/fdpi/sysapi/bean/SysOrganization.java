package com.sofn.fdpi.sysapi.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;


/**
 * Created by sofn
 */
@TableName("SYS_ORGANIZATION")
@Data
public class SysOrganization extends BaseModel<SysOrganization> {

    private String organizationName;
    /**
     * 父编号
     */
    private String parentId;
    /**
     * 父编号列表，如1/2/
     */
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
    private String regioncode;
    /**
     * 区域码名称
     */
    private String regioncodeName;
    /**
     * 级别
     */
    private String organizationLevel;
    /**
     * 级别名称
     */
    private String organizationLevelName;
    /**
     * 是否非行政机构（N:行政机构/Y:非行政机构）
     */
    private String thirdOrg;
    /**
     * 所在地址
     */
    private String address;
    /**
     * 所在地址名称
     */
    private String addressName;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 负责人电话
     */
    private String phone;

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
