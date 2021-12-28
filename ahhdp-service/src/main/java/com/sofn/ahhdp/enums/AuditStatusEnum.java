package com.sofn.ahhdp.enums;

import com.sofn.ahhdp.vo.DropDownVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 审核状态
 **/
@Getter
public enum AuditStatusEnum {

    UNDO("3", "--"),
    APPLY("2", "申请"),
    PASS("1", "通过"),
    UNPASS("0", "不通过");

    private String key;
    private String val;

    AuditStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<DropDownVo> getSelect() {
        AuditStatusEnum[] arr = AuditStatusEnum.values();
        List<DropDownVo> list = new ArrayList<>(arr.length);
        for (AuditStatusEnum obj : arr) {
            DropDownVo sv = new DropDownVo();
            sv.setId(obj.getKey());
            sv.setName(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        AuditStatusEnum[] arr = values();
        for (AuditStatusEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
