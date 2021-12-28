package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 罚没类型
 * @Auther: Bay
 * @Date: 2020/1/13 09:40
 */
@Getter
public enum ForfeiTypeEnum {
    IS_LIVE("1", "活体"),
    IS_GOODS("0", "制品");
    private String key;
    private String val;

    ForfeiTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }
    public static List<SelectVo> getSelect() {
        ForfeiTypeEnum[] values = ForfeiTypeEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (ForfeiTypeEnum obj : values){
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String key) {
        ForfeiTypeEnum[] arr = values();
        for (ForfeiTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
