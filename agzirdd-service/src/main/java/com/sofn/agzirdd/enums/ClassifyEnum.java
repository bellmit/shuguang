package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * @Description: 指标分类类型枚举
 * @Author: chlf
 */
@Getter
public enum ClassifyEnum {

    /**
     * 指标分类类型(0-发生面积,1-数量,2-造成经济损失)
     */
    AREA("0","外来入侵物种面积"),
    AMOUNT("1","数量"),
    ZCJJSS("2","造成经济损失"),
    ;


    private String code;
    private String description;

    ClassifyEnum(String code, String description){
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
