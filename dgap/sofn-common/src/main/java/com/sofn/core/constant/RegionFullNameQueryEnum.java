/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.core.constant;

/**
 * @author lijiang
 * @desc 行政区划全称查询类型枚举
 * @create 2019-01-23 15:19
 **/
public enum RegionFullNameQueryEnum {
    ALL("全部", "all"),
    THREE_LEVEL("省市区", "3rd");
    private String name;
    private String value;

    RegionFullNameQueryEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取枚举的名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取枚举的值
     *
     * @return 枚举值
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegionFullNameQueryEnum{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
