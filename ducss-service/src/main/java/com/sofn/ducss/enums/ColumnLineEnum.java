/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 14:44
 */
package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 最近几年类型
 *
 * @author jiangtao
 * @version 1.0
 **/
@Getter
public enum ColumnLineEnum {
    /**
     * 近几年类型
     *
     **/
    FIVEYERS("1","近5年"),
    TENYERS("2","近10年");



    private String code;
    private String description;

    ColumnLineEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
