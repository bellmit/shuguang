package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 蛙类
 */
@Getter
public enum FrogsEnum {

    G("g", "克"),
    ZHI("zhi", "只"),
    KG("kg", "公斤");


    private String key;
    private String val;

    FrogsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        FrogsEnum[] values = FrogsEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (FrogsEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        FrogsEnum[] arr = values();
        for (FrogsEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
