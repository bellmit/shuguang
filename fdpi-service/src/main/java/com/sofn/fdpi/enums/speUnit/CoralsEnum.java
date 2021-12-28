package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 珊瑚类
 */
@Getter
public enum CoralsEnum {

    G("g", "克"),
    ZHU("zhu", "株"),
    BRANCH("branch", "支"),
    KG("kg", "公斤");


    private String key;
    private String val;

    CoralsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        CoralsEnum[] values = CoralsEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (CoralsEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        CoralsEnum[] arr = values();
        for (CoralsEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
