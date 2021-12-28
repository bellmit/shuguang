package com.sofn.fyem.enums;

import lombok.Getter;

/**
 * @Description: 查看级别枚举
 * @Author: DJH
 * @Date: 2020/5/13 10:48
 */
@Getter
public enum ViewLevelEnum {
    LEVEL_CITY("fyem_city", "市级"),
    LEVEL_PROVINCE("fyem_prov", "省级"),
    LEVEL_MASTER("fyem_master", "部级");

    private String level;
    private String describe;

    ViewLevelEnum(String level, String describe) {
        this.level = level;
        this.describe = describe;
    }
}
