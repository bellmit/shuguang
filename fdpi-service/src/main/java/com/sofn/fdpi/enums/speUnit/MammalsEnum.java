package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 哺乳类
 */
@Getter
public enum MammalsEnum {

    TOU("tou", "头");


    private String key;
    private String val;

    MammalsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        MammalsEnum[] values = MammalsEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (MammalsEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        MammalsEnum[] arr = values();
        for (MammalsEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
