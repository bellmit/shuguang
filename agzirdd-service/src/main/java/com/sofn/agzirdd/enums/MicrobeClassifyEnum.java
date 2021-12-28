package com.sofn.agzirdd.enums;

import lombok.Getter;

/**
 * 采集模块-微生物分类
 * @author Administrator
 */
@Getter
public enum MicrobeClassifyEnum {

    /**
     * BACTERIA(细菌) FUNGUS(真菌) PROTIST(原生生物) VIRUS(病毒物)
     */
    BACTERIA("1","细菌"),
    FUNGUS("2","真菌"),
    PROTIST("3","原生生物"),
    VIRUS("4","病毒物");

    private String code;
    private String description;

    private MicrobeClassifyEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    /**
     * 根据枚举code获取对应的description
     * @param code code
     * @return description
     */
    public static String getDescriptionByCode(String code){
        for(MicrobeClassifyEnum microbeClassifyEnum:MicrobeClassifyEnum.values()){
            if(code.equals(microbeClassifyEnum.getCode())){
                return microbeClassifyEnum.getDescription();
            }
        }
        return  null;
    }
}
