package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 打印状态
 * @Author yumao
 * @Date 2019/12/27 14:29
 **/
@Getter
public enum PrintStatusEnum {

    ALREADY_PRINTED("1", "已打印"),
    NOT_PRINTED("0", "未打印");


    private String key;
    private String val;

    PrintStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        PrintStatusEnum[] arr = PrintStatusEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (PrintStatusEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        PrintStatusEnum[] arr = values();
        for (PrintStatusEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
