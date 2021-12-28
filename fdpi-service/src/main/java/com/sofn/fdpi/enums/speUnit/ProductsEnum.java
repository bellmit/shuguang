package com.sofn.fdpi.enums.speUnit;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 制品类
 */
@Getter
public enum ProductsEnum {

    G("g", "克"),
    GE("ge", "个"),
    ZHI("zhi", "只"),
    KG("kg", "公斤"),
    PAIR("pair", "对");


    private String key;
    private String val;

    ProductsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        ProductsEnum[] values = ProductsEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (ProductsEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        ProductsEnum[] arr = values();
        for (ProductsEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
