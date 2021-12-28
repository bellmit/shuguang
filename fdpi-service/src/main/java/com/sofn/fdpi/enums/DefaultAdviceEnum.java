package com.sofn.fdpi.enums;

import lombok.Getter;

@Getter
public enum DefaultAdviceEnum {
    BINDING_NOT_REPORTED("1", "绑定未上报"),
    REPORT_DEFAULT_ADVICE("2", "已上报！"),
    RETURN_FIRST_DEFAULT_ADVICE("3","审核退回！"),
    APPROVE_FIRST_DEFAULT_ADVICE("4", "审核通过！"),
    RETURN_SECOND_DEFAULT_ADVICE("5","复审退回！"),
    APPROVE_SECOND_DEFAULT_ADVICE("6", "复审审核通过！"),
    BINDING_CANCEL("7","上报撤回！");

    private String code;
    private String msg;
    DefaultAdviceEnum(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public static DefaultAdviceEnum getDefaultAdviceEnumByCode(String code){
        for(DefaultAdviceEnum defaultAdviceEnum : DefaultAdviceEnum.values()){
            if (code.equals(defaultAdviceEnum.getCode())){
                return defaultAdviceEnum;
            }
        }
        return null;
    }

}
