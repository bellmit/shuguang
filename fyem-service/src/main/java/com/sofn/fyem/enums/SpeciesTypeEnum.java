package com.sofn.fyem.enums;

import lombok.Getter;

/**
 * @Description: 物种类型枚举
 * @Author: Administrator
 */
@Getter
public enum SpeciesTypeEnum {

    /**
     * 物种类型:
     */
    FRESHWATERSPECIES("0","淡水物种"),
    SEAWATERSPECIES("1","海水物种"),
    RARESPECIES("2","珍稀物种");

    private String code;
    private String description;

    private SpeciesTypeEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(SpeciesTypeEnum speciesTypeEnum: SpeciesTypeEnum.values()){
            if(code.equals(speciesTypeEnum.getCode())){
                return speciesTypeEnum.getDescription();
            }
        }
        return  null;
    }
}
