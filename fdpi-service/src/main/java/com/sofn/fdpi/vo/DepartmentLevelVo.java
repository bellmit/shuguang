package com.sofn.fdpi.vo;

import lombok.Data;

/**
 * 直属机构id和级别对象
 */
@Data
public class DepartmentLevelVo {
    //直属机构id
    private String sysDeptId;
    //直属机构级别，省级、市级、区级
    private String sysLevel;
}
