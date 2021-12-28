package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 数据展示利用量情况相关枚举
 * @author jiangtao
 * @date 2020/11/11
 */
@Getter
public enum StrawUtilizeConditionEnum {
    /**
     * 数据展示利用量情况相关枚举
     *
     */

    STRAW_UTILIZE("pro_straw_utilize","综合利用量"),
    COMPREHENSIVE("comprehensive","综合利用率"),
    FERTILISING("fertilising","肥料化利用比例"),
    FORAGE("forage","饲料化利用比例"),
    FUEL("fuel","燃料化利用比例"),
    BASE("base","基料化利用比例"),
    MATERIAL("material","原料化利用比例"),
    COMPREHENSIVE_INDEX("comprehensive_index","综合利用能力指数"),
    INDUSTRIALIZATION_INDEX("industrialization_index","产业化利用能力指数");

    private String code;
    private String description;

    StrawUtilizeConditionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}