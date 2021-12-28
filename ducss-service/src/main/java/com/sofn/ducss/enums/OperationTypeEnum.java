package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 消息操作类型枚举
 *
 * @author jiangtao
 * @date 2020/10/30
 */
@Getter
public enum OperationTypeEnum {

    /**
     *消息操作类型枚举
     *
     * */
    SAVE("1","保存"),
    SEND("2","下发")
    ;


    private String code;
    private String description;


    OperationTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
