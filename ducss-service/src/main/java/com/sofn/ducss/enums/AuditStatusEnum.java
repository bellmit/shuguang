package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 流程枚举
 *
 * @author jiangtao
 * @date 2020/11/1
 */
@Getter
public enum AuditStatusEnum {
    /**
     *消息状态枚举类型
     *
     * */
    SAVE("0","保存未上报"),
    REPORTED("1","已上报"),
    READ("2","已读"),
    RETURNED("3","已退回"),
    WITHDRAWN("4","已撤回"),
    PASSED("5","已通过"),
    ISSUE("6","已下发"),
    PUBLISH("7","已公布"),
    ;
    private String code;
    private String description;


    AuditStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
