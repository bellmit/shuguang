package com.sofn.fyem.enums;

import lombok.Getter;

/**
 * @Description: 是与否枚举
 * @Author: Administrator
 */
@Getter
public enum YesOrNoEnum {

    /**
     * 存在于不存在枚举对应参数
     */
    NO("0","不存在"),
    YES("1","存在");

    private String code;
    private String description;

    private YesOrNoEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(YesOrNoEnum yesOrNoEnum: YesOrNoEnum.values()){
            if(code.equals(yesOrNoEnum.getCode())){
                return yesOrNoEnum.getDescription();
            }
        }
        return  null;
    }
}
