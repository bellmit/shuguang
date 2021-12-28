package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 物种来源
 * @Author yumao
 * @Date 2019/12/27 14:24
 **/
@Getter
public enum SpeciesSourceEnum {

    IMPORT("1", "引进"),
    BREED("2", "自繁");


    private String key;
    private String val;

    SpeciesSourceEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SpeciesSourceEnum[] arr = SpeciesSourceEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SpeciesSourceEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SpeciesSourceEnum[] arr = values();
        for (SpeciesSourceEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
