package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 螅类
 */
@Getter
public enum HydraEnum {

    G("g", "克"),
    ZHI("zhi", "只"),
    KG("kg", "公斤");


    private String key;
    private String val;

    HydraEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        HydraEnum[] values = HydraEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (HydraEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        HydraEnum[] arr = values();
        for (HydraEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
