package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 国内鲟鱼子酱状态
 **/
@Getter
public enum SturgeonStatusDomesticEnum {

    CANCEL("0", "撤回"),
    KEEP("1", "未上报"),
    REPORT("2", "已上报"),
    RETURN_DIRECTLY("3", "初审退回"),
    PASS_DIRECTLY("4", "初审通过"),
    RETURN("5", "标识审发单位退回"),
    PASS("6", "标识审发单位通过"),
    PRINT("7", "标识打印");

    private String key;
    private String val;

    SturgeonStatusDomesticEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SturgeonStatusDomesticEnum[] arr = SturgeonStatusDomesticEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SturgeonStatusDomesticEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SturgeonStatusDomesticEnum[] arr = values();
        for (SturgeonStatusDomesticEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
