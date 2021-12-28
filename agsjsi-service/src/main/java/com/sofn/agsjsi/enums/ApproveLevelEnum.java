package com.sofn.agsjsi.enums;

import lombok.Getter;

/**
 * 审核级别
 * wuXY
 */
@Getter
public enum ApproveLevelEnum {
    APPROVE_ZERO_LEVEL("0","非审核级别(提示不能审核)"),
    APPROVE_FIRST_LEVEL("1","一级审核，市"),
    APPROVE_SECOND_LEVEL("2","二级审核，省"),
    APPROVE_THREE_LEVEL("3","三级审核，总站"),
    APPROVE_FOUR_LEVEL("4","四级审核，专家");
    private String level;
    private String msg;
    ApproveLevelEnum(String level, String msg){
        this.level=level;
        this.msg=msg;
    }
}
