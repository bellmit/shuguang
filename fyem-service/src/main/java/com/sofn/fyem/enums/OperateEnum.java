package com.sofn.fyem.enums;

import lombok.Getter;

/**
 * @Description: 操作类型枚举
 * @Author: DJH
 * @Date: 2020/4/27 15:18
 */
@Getter
public enum OperateEnum {
    TYPE_RETURN("1", "退回"),
    TYPE_APPROVE("2", "通过");

    private String type;
    private String describe;

    OperateEnum(String type, String describe) {
        this.type = type;
        this.describe = describe;
    }
}
