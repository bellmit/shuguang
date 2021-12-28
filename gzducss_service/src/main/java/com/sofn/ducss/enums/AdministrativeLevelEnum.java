/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 18:37
 */
package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 行政区划级别
 *
 * @author jiangtao
 * @version 1.0
 **/
@Getter
public enum AdministrativeLevelEnum {

    /**
     * 行政级别
     *
     **/
    COUNTY("1","county"),
    CITY("2","city"),
    PROVINCE("3","province"),
    COUNTRY("4","country")
    ;

    private String code;
    private String description;

    AdministrativeLevelEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
