package com.sofn.agsjsi.vo;

import lombok.Data;

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
    //审核级别，0：无审核；1：一级审核；2：二级审核；3：三级审核
    private String approveLevel;

}
