package com.sofn.ducss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 颜色检查枚举
 *  0 没颜色   1  超过阈值    2  低于阈值
 */
@Getter
@AllArgsConstructor
public enum CheckColorEnum {
    COLOR_LOW("2","低于阈值"),
    COLOR_HIGHT("1","超过阈值"),
    COLOR_UNCHANGE("0","没超过阈值"),
    ;
    private String code;
    private String description;

}
