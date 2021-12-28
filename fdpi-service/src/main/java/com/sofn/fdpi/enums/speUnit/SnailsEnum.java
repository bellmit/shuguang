package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺类
 */
@Getter
public enum SnailsEnum {

    ZHI("zhi", "只"),
    G("g", "克"),
    KG("kg", "公斤");

    private String key;
    private String val;

    SnailsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SnailsEnum[] values = SnailsEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (SnailsEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SnailsEnum[] arr = values();
        for (SnailsEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
