package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: xiaobo
 * @Date: 2021-01-14 17:03
 */
@Getter
public enum TranferStatusEnum {
    KEEP("0", "保存"),
    REPORT("1", "上报"),
    FIRST_RETURN("2", "增加企业直属审核退回"),
    FIRST_PASS("3", "增加企业直属审核通过"),
    SECOND_RETURN("4", "减少企业确认退回"),
    SECOND_PASS("5", "减少企业确认通过"),
    THIRD_RETURN("6", "减少企业直属审核退回"),
    THIRD_PASS("7", "减少企业直属审核通过"),
    CANCEL("8","撤回");
    private String key;
    private String val;
    TranferStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }
    public static String getVal(String key) {
        TranferStatusEnum[] arr = values();
        for (TranferStatusEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
    public static List<SelectVo> getSelect() {
        TranferStatusEnum[] arr = TranferStatusEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (TranferStatusEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
}
