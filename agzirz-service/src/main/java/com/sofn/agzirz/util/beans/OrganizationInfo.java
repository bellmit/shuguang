package com.sofn.agzirz.util.beans;

import lombok.Data;

/**
 * 转换组织机构为实体时使用
 */
@Data
public class OrganizationInfo {
    /**
     * 机构ID(主键)
     */
    private String id;

    /**
     * 组织机构级别
     */
    private String organizationLevel;
    /**
     * 组织机构名称
     */
    private String organizationName;
    /**
     * 末级区域码
     */
    private String regionLastCode;
    /**
     * 区域码数组
     */
    @Deprecated
    private String regioncode;
    /**
     * 是否非行政机构（N:行政机构/Y:非行政机构）
     */
    private String thirdOrg;

}
