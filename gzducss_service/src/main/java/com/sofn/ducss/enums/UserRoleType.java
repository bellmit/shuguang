package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户角色类型
 */
@Getter
@AllArgsConstructor
public enum UserRoleType {
    COUNTY("county","县级人员")
    ,CITY("city","市级人员")
    ,PROVINCE("province","省级人员")
    ,CENTER("center","总站人员")
    ,EXPERT("expert","专家")
    ;
    private String code;
    private String descp;
}
