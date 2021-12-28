package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 标识变更记录中的状态
 * @Author yumao
 * @Date 2019/12/27 9:23
 **/
@Getter
public enum SignboardChangeStatusEnum {

    DISTRIBUTE("1", "配发"),
    ACTIVATE("2", "在养"),
    SHIFT_REDUCE("3", "转移-减少"),
    SHIFT_INCREASE("4", "转移-增加"),
    RENEWAL("5", "换发"),
    REPLACEMENT("6", "补发"),
    CANCELLATION("7", "注销");

    private String key;
    private String val;

    SignboardChangeStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SignboardChangeStatusEnum[] arr = SignboardChangeStatusEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SignboardChangeStatusEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SignboardChangeStatusEnum[] arr = values();
        for (SignboardChangeStatusEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
