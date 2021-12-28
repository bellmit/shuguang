package com.sofn.agzirz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleType {
    COUNTY("agzirz_county","县级")
    ,CITY("agzirz_city","市级")
    ,PROVINCE("agzirz_province","省级")
    ,CENTER("agzirz_center","总站")
    ,EXPERT("agzirz_expert","专家")
    ;
    private String code;
    private String descp;
}
