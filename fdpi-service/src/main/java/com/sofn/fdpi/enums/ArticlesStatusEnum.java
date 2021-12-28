package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 文章状态
 * @Auther: Bay
 * @Date: 2020/1/13 09:50
 */
@Getter
public enum ArticlesStatusEnum {
    NO_PUBLISH("2", "未公布"),
    IS_PUBLISH("1", "公布"),
    IS_HIDE("0", "隐藏");
    private String key;
    private String val;

    ArticlesStatusEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }
    public static List<SelectVo> getSelect() {
        ArticlesStatusEnum[] values = ArticlesStatusEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (ArticlesStatusEnum obj : values){
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String key) {
        ArticlesStatusEnum[] arr = values();
        for (ArticlesStatusEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
