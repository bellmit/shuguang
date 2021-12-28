package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 鱼子酱
 */
@Getter
public enum CaviarEnum {

    G("g", "克"),
    KG("kg", "公斤");


    private String key;
    private String val;

    CaviarEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        CaviarEnum[] values = CaviarEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (CaviarEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        CaviarEnum[] arr = values();
        for (CaviarEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
