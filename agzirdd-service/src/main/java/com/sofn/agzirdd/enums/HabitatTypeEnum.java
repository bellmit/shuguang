package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * 生境类型枚举
 * @author Administrator
 */
@Getter
public enum HabitatTypeEnum {

    /**
     * PLOWLAND(耕地) FOREST(林地) GRASSLAND(草地) OTHER(其他)
     */
    PLOWLAND("1","耕地"),
    FOREST("2","FOREST"),
    GRASSLAND("3","GRASSLAND"),
    OTHER("4","OTHER");

    private String code;
    private String description;

    private HabitatTypeEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(HabitatTypeEnum habitatTypeEnum:HabitatTypeEnum.values()){
            if(code.equals(habitatTypeEnum.getCode())){
                return habitatTypeEnum.getDescription();
            }
        }
        return  null;
    }
}
