package com.sofn.agsjdm.enums;

import lombok.Getter;

@Getter
public enum RiskEnum {

    RISK0("0", "无风险"),
    RISK1("1", "轻"),
    RISK2("2", "重"),
    RISK3("3", "严重");

    private String key;
    private String val;

    RiskEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }


    public static String getVal(String key) {
        RiskEnum[] arr = values();
        for (RiskEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }


}
