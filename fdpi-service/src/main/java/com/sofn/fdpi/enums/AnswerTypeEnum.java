package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 解答状态
 * @Auther: Bay
 * @Date: 2020/1/13 09:46
 */
@Getter
public enum AnswerTypeEnum {
    IS_Answer("1", "是"),
    NO_Answer("0", "否");
    private String key;
    private String val;

    AnswerTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }
    public static List<SelectVo> getSelect() {
        AnswerTypeEnum[] values = AnswerTypeEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (AnswerTypeEnum obj : values){
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String key) {
        AnswerTypeEnum[] arr = values();
        for (AnswerTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
