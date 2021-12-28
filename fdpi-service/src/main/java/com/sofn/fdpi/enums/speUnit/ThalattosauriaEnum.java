package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 海龙类
 */
@Getter
public enum ThalattosauriaEnum {

    G("g", "克"),
    WEI("wei", "尾"),
    ZHI("zhi", "只"),
    KG("kg", "公斤"),
    PAIR("pair", "对");


    private String key;
    private String val;

    ThalattosauriaEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        ThalattosauriaEnum[] values = ThalattosauriaEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (ThalattosauriaEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        ThalattosauriaEnum[] arr = values();
        for (ThalattosauriaEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
