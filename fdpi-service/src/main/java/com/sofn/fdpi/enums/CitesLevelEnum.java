package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: CITES 级别
 * @Auther: Bay
 * @Date: 2020/1/13 09:20
 */
@Getter
public enum CitesLevelEnum {
    ONE_LEVEL("1", "I级"),
    TWO_LEVEL("2", "II级"),
    THREE_LEVEL("3", "III级"),
    NO_LEVEL("4", "暂未列入");

    private String key;
    private String val;

    CitesLevelEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        CitesLevelEnum[] values = CitesLevelEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (CitesLevelEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        CitesLevelEnum[] arr = values();
        for (CitesLevelEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
