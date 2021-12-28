package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 发送对象枚举
 *
 * @author jiangtao
 * @date 2020/10/30
 */
@Getter
public enum SendObjectEnum {

    /**
     * 发送对象枚举
     *
     * @author jiangtao
     * @date 2020/10/30
     */
    ALL_MINISTRY("1","全部部级"),
    ALL_PROVINCE("2","全部省级"),
    ALL_CITY("3","全部市级"),
    ALL_COUNTY("4","全部县级"),
    CUSTOM("5","自定义"),
    ;

    private String code;
    private String description;

    SendObjectEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
