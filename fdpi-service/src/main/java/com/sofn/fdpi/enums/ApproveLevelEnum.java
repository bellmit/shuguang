package com.sofn.fdpi.enums;

import lombok.Getter;

/**
 * 审核级别
 * wuXY
 */
@Getter
public enum ApproveLevelEnum {
    APPROVE_ZERO_LEVEL("0","非审核级别(提示不能审核)"),
    APPROVE_FIRST_LEVEL("1","区级/市级直属审核(初审)"),
    APPROVE_SECOND_LEVEL("2","省级直属审核（初设/初设和复审）"),
    APPROVE_SECOND_NO_LEVEL("4","省级非直属审核（复审）"),
    APPROVE_THREE_LEVEL("3","部级审核（终审）");
    private String level;
    private String msg;
    ApproveLevelEnum(String level, String msg){
        this.level=level;
        this.msg=msg;
    }
}
