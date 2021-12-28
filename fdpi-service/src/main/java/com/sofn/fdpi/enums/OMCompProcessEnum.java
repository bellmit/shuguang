package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 欧鳗企业审核状态
 **/
@Getter
public enum OMCompProcessEnum {

    APPLY("0", "申报"),
    UN_PASS("1", "不通过"),
    PASS("2", "通过");

    private String key;
    private String val;

    OMCompProcessEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        OMCompProcessEnum[] arr = OMCompProcessEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (OMCompProcessEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        OMCompProcessEnum[] arr = values();
        for (OMCompProcessEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
