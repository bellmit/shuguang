package com.sofn.fdpi.enums;

import lombok.Getter;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-15 15:26
 */
@Getter
public enum AnimalProlevelEnum {
    COUNTRY_LEVEL_ONE("1","国家一级"),
    COUNTRY_LEVEL_TWO("2","国家二级");
    private String id;
    private String msg;
    AnimalProlevelEnum(String id, String msg){
        this.id=id;
        this.msg=msg;
    }
}
