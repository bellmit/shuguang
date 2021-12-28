package com.sofn.fyem.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description: 用户角色级别枚举
 * @Author: DJH
 * @Date: 2020/4/27 15:18
 */
@Getter
public enum RoleLevelEnum {
    LEVEL_COUNTY("fyem_county", "县级"),
    LEVEL_CITY_ADD("fyem_city_add", "市级填报员"),
    LEVEL_CITY("fyem_city", "市级审核员"),
    LEVEL_PROVINCE_ADD("fyem_prov_add", "省级填报员"),
    LEVEL_PROVINCE("fyem_prov", "省级审核员"),
    LEVEL_MASTER_ADD("fyem_master_add", "部级填报员"),
    LEVEL_MASTER("fyem_master", "部级审核员");

    private String level;
    private String describe;

    RoleLevelEnum(String level, String describe) {
        this.level = level;
        this.describe = describe;
    }

    public static Optional<RoleLevelEnum> resolve(final String level) {
        return Stream.of(values())
                .filter(role -> StringUtils.equalsIgnoreCase(level, role.getLevel()))
                .findFirst();
    }
}
