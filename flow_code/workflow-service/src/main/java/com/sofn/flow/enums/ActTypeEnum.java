package com.sofn.flow.enums;

import lombok.Getter;

@Getter
public enum ActTypeEnum {

    SINGLE("single","抢占任务"),
    ALL("all","会签任务");

    private String code;
    private String desc;

    ActTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public boolean isSingle() {
        return this.code.equalsIgnoreCase(SINGLE.code);
    }

    public boolean isAll() {
        return this.code.equalsIgnoreCase(ALL.code);
    }

    public static ActTypeEnum getByCode(String code){
        for (ActTypeEnum  actTypeEnum : ActTypeEnum.values()) {
            if (actTypeEnum.getCode().equalsIgnoreCase(code)){
                return actTypeEnum;
            }
        }

        return null;
    }

}
