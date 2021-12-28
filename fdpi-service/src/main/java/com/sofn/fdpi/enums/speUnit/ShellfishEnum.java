package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 贝类
 */
@Getter
public enum ShellfishEnum {

    ZHI("zhi", "只"),
    G("g", "克"),
    KG("kg", "公斤");


    private String key;
    private String val;

    ShellfishEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        ShellfishEnum[] values = ShellfishEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (ShellfishEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        ShellfishEnum[] arr = values();
        for (ShellfishEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
