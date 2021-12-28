package com.sofn.agzirdd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrganizationLevelEnum {
    MINISTRY("ministry", "部级"),
    PROVINCE("province", "省级"),
    CITY("city", "市级"),
    COUNTY("county", "区县级"),
    COUNTRY("country", "乡镇级")
    ;
    private String code;
    private String description;
    
}
