package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 未上报/已上报/初审退回/初审通过/复审退回/复审通过/终审退回/终审通过
 * @Author yumao
 * @Date 2019/12/27 11:16
 **/
@Getter
public enum SignboardApplyProcessEnum {

    UN_REPORT("1", "未上报"),
    REPORT("2", "已上报"),
    AUDIT_RETURN("3", "初审退回"),
    AUDIT("4", "初审通过"),
    RE_AUDIT_RETURN("5", "复审退回"),
    RE_AUDIT("6", "复审通过"),
    FINAL_AUDIT_RETURN("7", "部级退回"),
    FINAL_AUDIT("8", "标识核发单位审核"),
    ALLOTMENT("9","标识核发单位配发"),
    CANCEL("10","撤回");


    private String key;
    private String val;

    SignboardApplyProcessEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SignboardApplyProcessEnum[] ps2s = SignboardApplyProcessEnum.values();
        List<SelectVo> list = new ArrayList<>(ps2s.length);
        for (SignboardApplyProcessEnum ps : ps2s) {
            SelectVo sv = new SelectVo();
            sv.setKey(ps.getKey());
            sv.setVal(ps.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SignboardApplyProcessEnum[] arr = values();
        for (SignboardApplyProcessEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }

}
