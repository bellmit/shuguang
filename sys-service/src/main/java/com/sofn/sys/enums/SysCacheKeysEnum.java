package com.sofn.sys.enums;

import lombok.Getter;

/**
 *@Description 缓存key
 *@Author quzhijie
 *@Date 2019/12/31 11:27
 *@Version 1.0
 **/
@Getter
public enum SysCacheKeysEnum {

    SYS_KEY_USER_VERSION("sys:key:user:version","用户缓存版本号"),

    SYS_KEY_CAPTCHA_REPO("sys:key:captcha:repo","验证码库key"),

    SYS_CACHE_KEY_ORG_ID("sys:key:org:id","机构id作为key缓存机构信息"),

    SYS_CACHE_KEY_ROLE_USERID_PREFIX("sys:key:role:userid","用户id为key缓存用户角色列表的key前缀"),

    SYS_CACHE_KEY_SYSTEM_USERID_PREFIX("sys:key:system:userid","用户id为key缓存用户常用列表的key前缀"),

    SYS_CACHE_KEY_SYSTEM_ID("sys:key:system:id","系统id作为key缓存系统信息"),
    ;

    private String code;
    private String desc;

    SysCacheKeysEnum(String code, String desc){
    this.code = code;
    this.desc = desc;
    }
}
