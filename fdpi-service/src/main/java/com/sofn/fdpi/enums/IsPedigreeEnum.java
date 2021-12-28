package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 是否谱系
 * @Auther: Bay
 * @Date: 2020/1/13 09:29
 */
@Getter
public enum IsPedigreeEnum {
    IS_Y("1", "是"),
    IS_N("0", "否");
    private String key;
    private String val;

    IsPedigreeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }
    public static List<SelectVo> getSelect() {
        IsPedigreeEnum[] values = IsPedigreeEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (IsPedigreeEnum obj : values){
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String key) {
        IsPedigreeEnum[] arr = values();
        for (IsPedigreeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
