package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 国内标签纸申请状态
 **/
@Getter
public enum SturgeonPaperEnum {

    KEEP("1", "未上报"),
    REPORT("2", "已上报"),
    RETURN("3", "已退回"),
    PASS("4", "已通过"),
    CANCEL("5", "撤回"),
    PRINT("6", "已打印");

    private String key;
    private String val;

    SturgeonPaperEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SturgeonPaperEnum[] arr = SturgeonPaperEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SturgeonPaperEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SturgeonPaperEnum[] arr = values();
        for (SturgeonPaperEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
