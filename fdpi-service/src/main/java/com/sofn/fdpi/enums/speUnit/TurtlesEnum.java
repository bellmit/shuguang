package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 海龟类
 */
@Getter
public enum TurtlesEnum {


    ZHI("zhi", "只");


    private String key;
    private String val;

    TurtlesEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        TurtlesEnum[] values = TurtlesEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (TurtlesEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        TurtlesEnum[] arr = values();
        for (TurtlesEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
