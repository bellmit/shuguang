package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 鳄鼍类
 */
@Getter
public enum AlligatorsEnum {

    G("g", "克"),
    WEI("wei", "尾"),
    ZHI("zhi", "只"),
    TIAO("tiao", "条"),
    KG("kg", "公斤");


    private String key;
    private String val;

    AlligatorsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        AlligatorsEnum[] values = AlligatorsEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (AlligatorsEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        AlligatorsEnum[] arr = values();
        for (AlligatorsEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
