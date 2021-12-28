package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 砗磲类
 */
@Getter
public enum TridactylaEnum {

    G("g", "克"),
    ZHI("zhi", "只"),
    ZHU("zhu", "株"),
    BRANCH("branch", "支"),
    KG("kg", "公斤");


    private String key;
    private String val;

    TridactylaEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        TridactylaEnum[] values = TridactylaEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (TridactylaEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        TridactylaEnum[] arr = values();
        for (TridactylaEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
