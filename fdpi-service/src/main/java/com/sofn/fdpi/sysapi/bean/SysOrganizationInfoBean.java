package com.sofn.fdpi.sysapi.bean;

import lombok.Data;

/**
 * 调用支撑平台根据组织机构ID 和要查询的父机构级别查询出父机构接口获取的信息
 * @author heyongjie
 * @date 2019/9/5 11:07
 */
@Data
public class SysOrganizationInfoBean {

    /**
     * 机构ID
     */
    private String organizationId;

    /**
     * 机构名称
     */
    private String organizationName;

    /**
     * 机构级别
     */
    private String organizationLevel;

    public SysOrganizationInfoBean(){}

    public SysOrganizationInfoBean(String organizationId, String organizationName, String organizationLevel) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.organizationLevel = organizationLevel;
    }
}
