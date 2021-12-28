package com.sofn.ahhdp.enums;

import lombok.Getter;

/**
 * @Deacription 状态
 **/
@Getter
public enum StatusEnum {

    DOING("1", "进行中"),
    FINISH("0", "完成");

    private String key;
    private String val;

    StatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static String getVal(String key) {
        StatusEnum[] arr = values();
        for (StatusEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
