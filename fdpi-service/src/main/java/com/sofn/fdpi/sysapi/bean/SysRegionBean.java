package com.sofn.fdpi.sysapi.bean;

import lombok.Data;

/**
 * 调用支撑平台根据组织机构ID 和要查询的父机构级别查询出父机构接口获取的信息
 * @author Chenlf
 * @date 2019/9/16 15:00
 */
@Data
public class SysRegionBean {
    /**
     * 父ID
     */
    private String parentId;

    /**
     * 行政区划名称
     */
    private String regionName;

    /**
     * 行政区划代码
     */
    private String regionCode;

    /**
     * 行政区划拼音
     */
    private String regionPinyin;

    /**
     * 行政区划类别
     */
    private String regionType;

    /**
     * 行政区划全称
     */
    private String regionFullname;

    /**
     * 行政区划排序
     */
    private Integer sortid;

    /**
     * 行政区划备注
     */
    private String remark;

    /**
     * 区域Id
     */
    private String areaId;

    /**
     * 预留字段 1
     */
    private String reservedField1;

    /**
     * 预留字段 2
     */
    private String reservedField2;

    /**
     * 行政区划状态
     */
    private String status;
}
