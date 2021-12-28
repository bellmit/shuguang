package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典表的dict_type取值枚举
 */
@Getter
@AllArgsConstructor
public enum DictType {
    COMMON_TYPE("common_type","通用类型")
    ,STRAW_TYPE("straw_type","秸秆类型")
    ,AREA_TYPE("area_type","区划类型")

    ;

    private String code;
    private String description;
}
