package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 物种保护级别
 * @Auther: Bay
 * @Date: 2020/1/13 09:06
 */
@Getter
public enum SpeProLevelEnum {
    ONE_LEVEL("1", "一级"),
    TWO_LEVEL("2", "二级"),
    SPECIAL_LEVEL("3", "特殊要求"),
    PENDING_APPROVAL("4", "待核准"),
    PROVINCIAL_KEY("5", "省重点");


    private String key;
    private String val;

    SpeProLevelEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SpeProLevelEnum[] values = SpeProLevelEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (SpeProLevelEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SpeProLevelEnum[] arr = values();
        for (SpeProLevelEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
