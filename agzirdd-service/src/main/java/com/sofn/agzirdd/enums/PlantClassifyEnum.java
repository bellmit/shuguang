package com.sofn.agzirdd.enums;

import lombok.Getter;

@Getter
public enum PlantClassifyEnum {

    /**
     * ARBOR(乔木) SHRUB(灌木) SUFFRUTEX(亚灌木) HERB(草本一年生,二年生,三年生)
     * STAND(站立) PRONE(平卧) CRAWL(匍匐) CLIMB(攀援) TWINE(缠绕)
     */
    ARBOR("1","乔木"),
    SHRUB("2","灌木"),
    SUFFRUTEX("3","亚灌木"),
    HERB_ONEYEAR("41","草本一年生"),
    HERB_TWOYEAR("42","草本二年生"),
    HERB_THREEYEAR("43","草本三年生"),
    STAND("5","站立"),
    PRONE("6","PRONE"),
    CRAWL("7","CRAWL"),
    CLIMB("8","攀援"),
    TWINE("9","缠绕");

    private String code;
    private String description;

    private PlantClassifyEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(PlantClassifyEnum plantClassifyEnum:PlantClassifyEnum.values()){
            if(code.equals(plantClassifyEnum.getCode())){
                return plantClassifyEnum.getDescription();
            }
        }
        return  null;
    }

}
