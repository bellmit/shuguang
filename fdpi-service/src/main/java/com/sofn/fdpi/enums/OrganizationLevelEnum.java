package com.sofn.fdpi.enums;

import lombok.Getter;

@Getter
public enum OrganizationLevelEnum {
    COMPANY_ORG_LEVEL("1","企业机构"),
    DIRECT_ORG_LEVEL("2","直属机构区级机构"),
    DIRECT_AND_CITY_ORG_LEVEL("3","直属机构也是市级"),
    DIRECT_AND_PROVINCE_ORG_LEVEL("4","直属机构也是省级"),
    DIRECT_AND_MINISTRY_ORG_LEVEL("5","直属机构也是部级机构"),
    DISTRICT_ORG_LEVEL("6","区级机构"),
    CITY_ORG_LEVEL("7","市级机构"),
    PROVINCE_ORG_LEVEL("8","省级机构"),
    MINISTRY_ORG_LEVEL("9","部级机构"),
    OTHER_ORG_LEVEL("10","其它级别机构，不能查询数据");

    private String id;
    private String msg;
    OrganizationLevelEnum(String id,String msg){
        this.id=id;
        this.msg=msg;
    }
}
