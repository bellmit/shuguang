package com.sofn.fyrpa.enums;

import lombok.Getter;

@Getter
public enum FyrpaEnum {

    fyrpa_id("fyrpa","水产种质资源保护区信息子系统ID");

    private String code;
    private String description;

    private FyrpaEnum(String code, String description){
        this.code = code;
        this.description = description;
    }
}
