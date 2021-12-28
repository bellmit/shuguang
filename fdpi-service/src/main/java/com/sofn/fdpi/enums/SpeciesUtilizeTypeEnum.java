package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 物种利用类型
 * @Author yumao
 * @Date 2019/12/27 14:29
 **/
@Getter
public enum SpeciesUtilizeTypeEnum {

    SHOW("1", "驯养展演"),
    BREED("2", "人工繁育");


    private String key;
    private String val;

    SpeciesUtilizeTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SpeciesUtilizeTypeEnum[] arr = SpeciesUtilizeTypeEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SpeciesUtilizeTypeEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SpeciesUtilizeTypeEnum[] arr = values();
        for (SpeciesUtilizeTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
