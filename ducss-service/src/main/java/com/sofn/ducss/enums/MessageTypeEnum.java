package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 消息通知类型
 *
 * @author jiangtao
 * @date 2020/10/30
 */
@Getter
public enum MessageTypeEnum {


    /**
     *消息操作类型枚举
     *
     * */
    CREATE_NOTICE("1","部级创建通知"),
    NOTICE("2","通知"),
    REPORT_MESSAGE("3","上报消息"),
    AUDIT_MESSAGE("4","审核消息")
    ;

    private String code;
    private String description;

    MessageTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
