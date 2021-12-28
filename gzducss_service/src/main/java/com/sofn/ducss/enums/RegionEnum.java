package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 区划相关枚举
 */
@AllArgsConstructor
@Getter
public enum  RegionEnum {

    VERB_CODES("110000,120000,310000,500000", "直辖市代码" ),

    ROOT_CODE("100000","区划根节点"),

    ;

    private String code;
    private String description;
}
