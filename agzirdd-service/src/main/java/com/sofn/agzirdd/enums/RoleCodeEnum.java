package com.sofn.agzirdd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleCodeEnum {
    MINISTRY("agzirdd_terminal","总站人员"),
    EXPERT("agzirdd_expert","专家"),
    PROVINCE("agzirdd_province","省级人员"),
    CITY("agzirdd_city","市级人员"),
    COUNTY("agzirdd_county","县级填报员"),
    ;
    private String code;
    private String description;

    public static String getDescriptionByCode(String code){
        for (RoleCodeEnum r : RoleCodeEnum.values()) {
            if(r.getCode().equals(code))
                return r.getDescription();
        }
        return "";
    }
}
