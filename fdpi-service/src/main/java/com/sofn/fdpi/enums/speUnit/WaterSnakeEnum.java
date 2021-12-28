package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 水蛇类
 */
@Getter
public enum WaterSnakeEnum {

    G("g", "克"),
    TIAO("tiao", "条"),
    ZHI("zhi", "只"),
    KG("kg", "公斤");


    private String key;
    private String val;

    WaterSnakeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        WaterSnakeEnum[] values = WaterSnakeEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (WaterSnakeEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        WaterSnakeEnum[] arr = values();
        for (WaterSnakeEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
