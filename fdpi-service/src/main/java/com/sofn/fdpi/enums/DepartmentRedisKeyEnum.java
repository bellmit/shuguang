package com.sofn.fdpi.enums;

import lombok.Getter;

@Getter
public enum DepartmentRedisKeyEnum {
    DEPARTMENT_KEY("DEPARTMENT","redis中表名"),
    DEPARTMENT_DIRECT_TYPE_VALUE("1","直属机构类型_机构id，作为获取获取直属机构的key值"),
    DEPARTMENT_LAW_TYPE_VALUE("2","证书年审直属机构类型_机构id，作为获取获取执法机构的key值");

    private String key;
    private String msg;
    DepartmentRedisKeyEnum(String key,String msg){
        this.key=key;
        this.msg=msg;
    }
}
