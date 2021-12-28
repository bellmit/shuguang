package com.sofn.fdpi.enums;

import lombok.Getter;

/**
 * @author wenxin
 * @create 2021/1/19
 */
@Getter
public enum PapersSpecSource {

    REGISTER("0","注册"),
    CHANGE("1","变更");

    private String code;
    private String msg;

    PapersSpecSource(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
