package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 区域级别
 */
@AllArgsConstructor
@Getter
public enum RegionLevel {

    /**
     * 区域级别
     */
    MINISTRY("ministry","部级")
    ,PROVINCE("province","省级")
    ,CITY("city","市级")
    ,COUNTY("county","区县级")
    ,COUNTRY("country","乡级")
    ;
    private String code;
    private String description;
}
