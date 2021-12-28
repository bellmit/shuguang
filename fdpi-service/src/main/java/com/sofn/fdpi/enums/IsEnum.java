package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 是/否
 * @Author yumao
 * @Date 2019/12/27 14:29
 **/
@Getter
public enum IsEnum {

    IS_Y("1", "是"),
    IS_N("0", "否");


    private String key;
    private String val;

    IsEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        IsEnum[] arr = IsEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (IsEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        IsEnum[] arr = values();
        for (IsEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
