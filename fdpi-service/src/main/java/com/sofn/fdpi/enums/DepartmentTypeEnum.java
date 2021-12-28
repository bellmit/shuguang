package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum DepartmentTypeEnum {

    PAPER_RECORD("0", "证书备案"),
    PAPER_BINDING("1", "证书变更审核"),
    PAPER_YEAR_INSPECT("2","证书年审审核"),
    PAPER_PRINT("3", "证书打印"),
    SIGNBOARD("4", "标识审核");


    private String key;
    private String val;

    DepartmentTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        DepartmentTypeEnum[] arr = DepartmentTypeEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (DepartmentTypeEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        DepartmentTypeEnum[] arr = values();
        for (DepartmentTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
