package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 鱼类
 */
@Getter
public enum FishEnum {

    G("g", "克"),
    WEI("wei", "尾"),
    TIAO("tiao", "条"),
    KG("kg", "公斤");


    private String key;
    private String val;

    FishEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        FishEnum[] values = FishEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (FishEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        FishEnum[] arr = values();
        for (FishEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
