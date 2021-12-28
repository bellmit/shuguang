package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 鳖类
 */
@Getter
public enum TortoiseEnum {


    ZHI("zhi", "只"),
    G("g", "克"),
    KG("kg", "公斤");


    private String key;
    private String val;

    TortoiseEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        TortoiseEnum[] values = TortoiseEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (TortoiseEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        TortoiseEnum[] arr = values();
        for (TortoiseEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
