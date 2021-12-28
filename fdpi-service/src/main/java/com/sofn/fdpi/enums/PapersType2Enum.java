package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-06 14:36
 */
@Getter
public enum PapersType2Enum {
    ARTIFICIAL_BREEDING("1", "人工繁育许可证"),
//    DOMESTICATION_PROPAGATION("2", "驯养繁殖许可证"),
    MANAGEMENT_UTILIZATION("3", "经营利用许可证"),
    NO_PAPERS("4", "无证书备案");
    private String key;
    private String val;

    PapersType2Enum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        PapersType2Enum[] arr = PapersType2Enum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (PapersType2Enum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        PapersType2Enum[] arr = values();
        for (PapersType2Enum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
