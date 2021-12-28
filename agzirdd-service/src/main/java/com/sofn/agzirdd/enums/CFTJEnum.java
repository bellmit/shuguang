package com.sofn.agzirdd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CFTJEnum {
    //1-常温、2-冷藏（4℃）、3-冷冻（-20℃）、4-超低温（-80℃）、5-其他
    CW("1","常温"),
    LC("2","冷藏（4℃）"),
    LD("3","冷冻（-20℃）"),
    CDW("4","超低温（-80℃）"),
    QT("5","其他"),
    ;
    private String code;
    private String description;

    public static String getDescriptionByCode(String code){
        for (CFTJEnum c : CFTJEnum.values()) {
            if(code.equals(c.getCode()))
                return c.getDescription();
        }
        return "";
    }
}
