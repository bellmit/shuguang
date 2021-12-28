package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * @Description: 危险程度枚举
 * @Author: Administrator
 */
@Getter
public enum DamageDegreeEnum {

    /**
     * 危害程度:0-轻度；1-中度；2-重度
     */
    MILD("0","轻度"),
    MEDIUM("1","中度"),
    SERIOUS("2","重度");

    private String code;
    private String description;

    private DamageDegreeEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(DamageDegreeEnum damageDegreeEnum:DamageDegreeEnum.values()){
            if(code.equals(damageDegreeEnum.getCode())){
                return damageDegreeEnum.getDescription();
            }
        }
        return  null;
    }
}
