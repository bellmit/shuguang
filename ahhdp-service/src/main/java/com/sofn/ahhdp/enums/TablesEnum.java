package com.sofn.ahhdp.enums;

import lombok.Getter;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-08-12 9:56
 */
@Getter
public enum TablesEnum {
    SPE_KEY_ALL("AHHDP_SPE_ALL_LIST","所有省份的物种"),
    SPE_KEY_BY_PROVINCE_CODE("AHHDP_SPE_KEY_BY_PROVINCE_LIST","HASH item=provinceCode通过省份过滤物种");
    private String code;
    private String msg;

    TablesEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
