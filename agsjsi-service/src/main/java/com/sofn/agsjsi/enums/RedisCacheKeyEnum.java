package com.sofn.agsjsi.enums;

import lombok.Getter;

/**
 * 提供给第三方的基础数据下拉，保存再redis中，枚举表名和描述
 * WXY
 * 2020-3-10 09:33:08
 */
@Getter
public enum RedisCacheKeyEnum {
    POINT_COLLECT_KEY("AGSJSI:POINT_COLLECT_LIST","农业湿地示范点收集表存入redis的key值，用于第三方下拉");

    private String code;
    private String msg;

    RedisCacheKeyEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
