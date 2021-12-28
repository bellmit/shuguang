package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-16 12:22
 */
@Getter
public enum ForfeiProcessEnum {

    UN_REPORT("1", "未上报"),
    REPORT("2", "已上报"),
    RE_AUDIT_RETURN("3", "复审退回"),
    RE_AUDIT("4", "复审通过"),
    FINAL_AUDIT_RETURN("5", "终审退回"),
    FINAL_AUDIT("6", "终审通过");


    private String key;
    private String val;

    ForfeiProcessEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        ForfeiProcessEnum[] ps2s = ForfeiProcessEnum.values();
        List<SelectVo> list = new ArrayList<>(ps2s.length);
        for (ForfeiProcessEnum ps : ps2s) {
            SelectVo sv = new SelectVo();

            sv.setKey(ps.getKey());
            sv.setVal(ps.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        ForfeiProcessEnum[] arr = values();
        for (ForfeiProcessEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
