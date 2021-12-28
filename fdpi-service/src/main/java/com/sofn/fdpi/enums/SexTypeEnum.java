package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 性别
 * @Author yumao
 * @Date 2019/12/27 14:29
 **/
@Getter
public enum SexTypeEnum {

    GENDER_MALE("1", "雄"),
    GENDER_FEMALE("2","雌"),
    GENDER_OTHER("0", "其它");


    private String key;
    private String val;

    SexTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SexTypeEnum[] arr = SexTypeEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (SexTypeEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SexTypeEnum[] arr = values();
        for (SexTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
