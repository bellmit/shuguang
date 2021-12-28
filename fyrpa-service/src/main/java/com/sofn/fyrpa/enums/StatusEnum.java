package com.sofn.fyrpa.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    IS_QY("1","启用"),
    IS_TY("-1","停用");

    private String key;
    private String val;

    private StatusEnum(String key, String val) {
        this.key = key;
        this.val = val;

    }
}
