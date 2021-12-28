package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * @Description: 是否存在物种入侵枚举
 * @Author: Administrator
 */
@Getter
public enum HasSpeciesEnum {

    /**
     * 枚举参数对应
     */
    INEXISTENCE("0","不存在入侵物种"),
    EXIST("1","存在入侵物种");

    private String code;
    private String description;

    private HasSpeciesEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(HasSpeciesEnum hasSpeciesEnum:HasSpeciesEnum.values()){
            if(code.equals(hasSpeciesEnum.getCode())){
                return hasSpeciesEnum.getDescription();
            }
        }
        return  null;
    }
}
