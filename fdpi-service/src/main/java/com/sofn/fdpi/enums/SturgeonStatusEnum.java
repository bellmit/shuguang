package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 鲟鱼子酱状态
 **/
@Getter
public enum SturgeonStatusEnum {

    KEEP("1", "未上报"),
    REPORT("2", "已上报(待审核)"),
    RETURN("3", "已退回"),
    PASS("4", "已通过"),
    CANCEL("5", "撤回");

    private String key;
    private String val;

    SturgeonStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SturgeonStatusEnum[] arr = SturgeonStatusEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SturgeonStatusEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SturgeonStatusEnum[] arr = values();
        for (SturgeonStatusEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
