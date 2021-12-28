package com.sofn.fdpi.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Deacription TODO
 * @Author yumao
 * @Date 2020/1/9 16:45
 **/
@Data
public class OrganizationInfo {

    /**
     * 机构ID(主键)
     */
    private String id;

    /**
     * 组织机构名称
     */
    private String organizationName;
    /**
     * 机构级别 ministry部级,province省级,city市级,county县级,country乡级
     */
    private String organizationLevel;
    /**
     * 末级区域码
     */
    private String regionLastCode;
    /**
     * 是否非行政机构（N:行政机构/Y:非行政机构）
     */
    private String thirdOrg;
    /**
     * 区域码(老格式,弃用)
     */
    private String regioncode;

    private String delFlag;
    /**
     * 机构职能
     */
    private String orgFunction;
}
