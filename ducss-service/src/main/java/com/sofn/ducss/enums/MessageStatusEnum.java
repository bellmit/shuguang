package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 消息状态枚举类型
 *
 * @author jiangtao
 * @date 2020/10/30
 */
@Getter
public enum MessageStatusEnum {
    /**
     *消息状态枚举类型
     *
     * */
    UNREAD("1","未读"),
    READ("2","已读")
    ;

    private String code;
    private String description;

    MessageStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
