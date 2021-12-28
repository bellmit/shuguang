package com.sofn.fyem.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Description: 地图级别枚举
 * @Author: DJH
 * @Date: 2020/8/3 11:25
 */
@Getter
public enum AdLevelEnum {

    LEVEL_COUNTY("fyem_adCounty", "县级"),
    LEVEL_CITY("fyem_adCity", "市级"),
    LEVEL_PROVINCE("fyem_adProv", "省级"),
    LEVEL_MASTER("fyem_adMaster", "部级");

    private String level;
    private String desc;

    AdLevelEnum(String level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public static AdLevelEnum getEnum(String level){
        for (AdLevelEnum adLevelEnum : AdLevelEnum.values()){
            if (adLevelEnum.getLevel().equals(level)){
                return adLevelEnum;
            }
        }
        return null;
    }
}
