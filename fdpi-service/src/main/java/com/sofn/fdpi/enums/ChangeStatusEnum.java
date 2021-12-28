package com.sofn.fdpi.enums;

import lombok.Getter;

/**
 * @Description:
 * @author: xiaobo
 * @Date: 2021-01-13 10:54
 */
@Getter
public enum ChangeStatusEnum {
    KEEP("0", "保存"),
    REPORT("1", "上报"),
    RETURN("2", "审核退回"),
    PASS("3", "审核通过"),
    CANCEL("4","撤回");
    private String key;
    private String val;

    ChangeStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static String getVal(String key) {
        ChangeStatusEnum[] arr = values();
        for (ChangeStatusEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
