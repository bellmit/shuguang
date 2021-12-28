package com.sofn.common.enums;

import lombok.Getter;

/**
 *
 *常量
 *
 * Created by heyongjie on 2019/5/29 11:27
 */
@Getter
public enum CommonEnum {

    DEL_FLAG_Y("Y","已删除"),
    DEL_FLAG_N("N","未删除"),

    SLASH("/","斜杠"),

    // 行政区划根节点
    SYS_REGION_ROOT_NODE("100000","行政区划"),
    SYS_DICT_CACHE_KEY("dict_cache_key","字典按类型缓存键"),
    SYS_ROLE_RESOURCE_CACHE_KEY("role_resource_cache_key","角色对应的资源菜单缓存键"),
    SYS_REGION_NAME_CODE_CACHE_KEY("sys_region_name_code","区划名称-区划代码缓存键"),
    SYS_REGION_CODE_NAME_CACHE_KEY("sys_region_code_name","区划代码-区划名称缓存键");

    private String code;
    private String description;

    CommonEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

}
