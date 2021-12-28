package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * @Description: 采集标本类型枚举
 * @Author: Administrator
 */
@Getter
public enum TypeEnum {

    /**
     * 采集标本类型(0-植物,1-动物,2-微生物)
     */
    PLANT("0","植物"),
    ANIMAL("1","动物"),
    MICROBE("2","微生物");

    private String code;
    private String description;

    TypeEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(StatusEnum statusEnum:StatusEnum.values()){
            if(code.equals(statusEnum.getCode())){
                return statusEnum.getDescription();
            }
        }
        return  null;
    }
}
