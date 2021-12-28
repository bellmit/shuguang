package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 鲵类
 */
@Getter
public enum Salamanders2Enum {

    WEI("wei", "尾"),
    G("g", "克"),
    ZHI("zhi", "只"),
    TIAO("tiao", "条"),
    KG("kg", "公斤");


    private String key;
    private String val;

    Salamanders2Enum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        Salamanders2Enum[] values = Salamanders2Enum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (Salamanders2Enum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        Salamanders2Enum[] arr = values();
        for (Salamanders2Enum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
