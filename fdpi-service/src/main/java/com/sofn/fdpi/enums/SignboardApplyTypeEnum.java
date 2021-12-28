package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * 标识申请类型
 * @Author yumao
 * @Date 2019/12/27 9:23
 **/
@Getter
public enum SignboardApplyTypeEnum {

    ALLOTMENT("1", "配发"),
    RENEWAL("2", "换发"),
    REPLACEMENT("3", "补发"),
    CANCELLATION("4", "注销");

    private String key;
    private String val;

    SignboardApplyTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SignboardApplyTypeEnum[] arr = SignboardApplyTypeEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SignboardApplyTypeEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SignboardApplyTypeEnum[] arr = values();
        for (SignboardApplyTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
