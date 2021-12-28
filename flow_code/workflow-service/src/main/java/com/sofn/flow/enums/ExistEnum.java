package com.sofn.flow.enums;

import lombok.Getter;

@Getter
public enum ExistEnum {

    RUNNING("running","存在于正在运行流程中"),
    HISTORY("history","存在于历史流程中"),
    NOT_EXIST("not_exist","不存在");

    private String code;
    private String desc;

    ExistEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public boolean isRunning() {
        return this.code.equalsIgnoreCase(RUNNING.code);
    }

    public boolean isHistory() {
        return this.code.equalsIgnoreCase(HISTORY.code);
    }

    public boolean isExist() {
        return !this.code.equalsIgnoreCase(NOT_EXIST.code);
    }
}
