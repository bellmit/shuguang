package com.sofn.agzirdd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionLevelEnum {
    COUNTRY("ad_ministry","国家"),
    PROVINCE("ad_province","省"),
    CITY("ad_city","市"),
    COUNTY("ad_county","县"),
    ;
    private String code;
    private String description;

}
