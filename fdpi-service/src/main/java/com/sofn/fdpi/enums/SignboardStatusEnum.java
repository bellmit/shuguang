package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 标识状态
 * @Author yumao
 * @Date 2019/12/27 9:23
 **/
@Getter
public enum SignboardStatusEnum {

    UN_ACTIVATE("1", "未激活"),
    FEED("2", "在养"),
    CANCELLATION("3", "已注销"),
    SALE("4", "销售"),
    DEPOSIT("5", "存放");

    private String key;
    private String val;

    SignboardStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SignboardStatusEnum[] arr = SignboardStatusEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SignboardStatusEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SignboardStatusEnum[] arr = values();
        for (SignboardStatusEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
