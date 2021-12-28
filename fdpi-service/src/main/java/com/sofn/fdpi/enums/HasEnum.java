package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 有/无
 * @Author yumao
 * @Date 2019/12/27 14:29
 **/
@Getter
public enum HasEnum {

    IS_Y("1", "有"),
    IS_N("0", "无");


    private String key;
    private String val;

    HasEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        HasEnum[] arr = HasEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (HasEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        HasEnum[] arr = values();
        for (HasEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
