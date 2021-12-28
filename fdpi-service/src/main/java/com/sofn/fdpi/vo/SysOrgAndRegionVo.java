package com.sofn.fdpi.vo;

import lombok.Data;

import java.util.Map;

/**
 * 获取当前账号归属机构和数据过滤省市区条件
 * wuXY
 * 2020-1-2 13:40:30
 */
@Data
public class SysOrgAndRegionVo {
    //组织机构id
    private String orgId;
    //组织机构名称
    private String orgName;
    //当前系统机构级别编号：OrganizationLevelEnum：1：企业机构；2：直属机构；3：省级机构；4：部级机构；5：直属机构也是省级；6：直属机构也是部级机构；7：市级机构；8：区级机构等
    private String sysOrgLevelId;
    //审核级别，0：无审核；1：一级审核；2：二级审核；3：三级审核
    private String approveLevel;

    //查询接口中通过机构区域code来过滤数据：key:sysOrgProvince,sysOrgCity,sysOrgDistrict
    private Map<String,Object> sysOrgRegionMap;
}
