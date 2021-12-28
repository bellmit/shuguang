package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * @Description: 存在于不存在枚举
 * @Author: Administrator
 */
@Getter
public enum YseOrNoEnum {

    /**
     * 存在于不存在枚举对应参数
     */
    NO("0","否"),
    YES("1","是");

    private String code;
    private String description;

    private YseOrNoEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(YseOrNoEnum yseOrNoEnum:YseOrNoEnum.values()){
            if(code.equals(yseOrNoEnum.getCode())){
                return yseOrNoEnum.getDescription();
            }
        }
        return  null;
    }
}
