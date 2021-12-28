package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 数据展示选着不同数据类型枚举
 *
 * @author jiangtao
 * @date 2020/11/6
 */
@Getter
public enum DataTypeEnum {

    /**
     *数据范围类型枚举
     *
     * */
    THEORY_RESOURCE("theory_resource","产生量"),
    COLLECT_RESOURCE("collect_resource","可收集量"),
            ;
    private String code;
    private String description;

    DataTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}