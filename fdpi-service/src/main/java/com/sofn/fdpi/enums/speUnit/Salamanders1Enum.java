package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 螈类
 */
@Getter
public enum Salamanders1Enum {

    G("g", "克"),
    WEI("wei", "尾"),
    ZHI("zhi", "只"),
    TIAO("tiao", "条"),
    KG("kg", "公斤");


    private String key;
    private String val;

    Salamanders1Enum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        Salamanders1Enum[] values = Salamanders1Enum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (Salamanders1Enum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        Salamanders1Enum[] arr = values();
        for (Salamanders1Enum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
