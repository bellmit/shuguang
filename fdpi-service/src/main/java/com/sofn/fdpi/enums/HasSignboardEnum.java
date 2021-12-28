package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 物种是否使用标识
 * @Auther: Bay
 * @Date: 2020/1/13 09:23
 */
@Getter
public enum HasSignboardEnum {
    NO_USE("0","不使用"),
    OMNIDISTANCE("1","全程使用"),
    SALE("2","销售使用");
    private String key;
    private String val;

    HasSignboardEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }
    public static List<SelectVo> getSelect() {
        HasSignboardEnum[] values = HasSignboardEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (HasSignboardEnum obj : values){
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String key) {
        HasSignboardEnum[] arr = values();
        for (HasSignboardEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
