package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-06 14:46
 */
@Getter
public enum CompanyStatusEnum {

    DEACTIVATE("0", "停用"),
    ENABLE("1", "启用");

    private String key;
    private String val;

    CompanyStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        CompanyStatusEnum[] arr = CompanyStatusEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (CompanyStatusEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        CompanyStatusEnum[] arr = values();
        for (CompanyStatusEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
