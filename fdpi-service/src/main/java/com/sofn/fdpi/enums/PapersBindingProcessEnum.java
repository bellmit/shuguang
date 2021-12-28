package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 证书绑定状态
 **/
@Getter
public enum PapersBindingProcessEnum {

    KEEP("1", "未上报"),
    REPORT("2", "上报"),
    RETURN_DIRECTLY("3", "审核退回"),
    PASS_DIRECTLY("4", "审核通过"),
    RETURN("5", "复审退回"),
    PASS("6", "复审通过"),
    CANCEL("7", "撤回");

    private String key;
    private String val;

    PapersBindingProcessEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        PapersBindingProcessEnum[] arr = PapersBindingProcessEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (PapersBindingProcessEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        PapersBindingProcessEnum[] arr = values();
        for (PapersBindingProcessEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
