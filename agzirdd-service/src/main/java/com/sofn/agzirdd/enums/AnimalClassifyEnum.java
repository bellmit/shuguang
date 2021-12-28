package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * 采集模块-动物分类
 * @author Administrator
 */
@Getter
public enum AnimalClassifyEnum {

    /**
     * MAMMALIA(哺乳类) BIRDS(鸟类) AMPHIBIANS(两栖类) FISH(鱼类)
     * INVERTEBRATE(无脊椎类) HEXAPODA(六足类)
     */
    MAMMALIA("1","哺乳类"),
    BIRDS("2","鸟类"),
    AMPHIBIANS("3","两栖类"),
    FISH("4","鱼类"),
    INVERTEBRATE("5","无脊椎类"),
    HEXAPODA("6","六足类");

    private String code;
    private String description;

    private AnimalClassifyEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(AnimalClassifyEnum animalClassifyEnum:AnimalClassifyEnum.values()){
            if(code.equals(animalClassifyEnum.getCode())){
                return animalClassifyEnum.getDescription();
            }
        }
        return  null;
    }
}
