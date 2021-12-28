package com.sofn.sys.enums;

import lombok.Getter;

/**
 * 系统管理中的常量
 * <p>
 * Created by heyongjie on 2019/5/29 11:27
 */
@Getter
public enum SysInterParamEnum {
    DIFFERENT_REQ_GET("0", "get请求方式的键值对"),
    DIFFERENT_REQ_HEADER("1", "请求头"),
    DIFFERENT_REQ_BODY("2", "请求体"),
    DIFFERENT_REQ_RETURN("3", "接口返回参数"),
    ;

    /**
     * 代码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    SysInterParamEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
