package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenxin
 * @create 2021/1/19
 */
@Getter
public enum ComeStockFlowEnum {

    REDUCE("reduce","转移减少"),
    INCREASE("increase","转移增加"),
    REGISTER("register","注册"),
    CHANGE("change","变更");

    private String code;
    private String msg;

    ComeStockFlowEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static List<SelectVo> getSelect() {
        ComeStockFlowEnum[] values = ComeStockFlowEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (ComeStockFlowEnum obj : values){
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getCode());
            sv.setVal(obj.getMsg());
            list.add(sv);
        }
        return list;
    }
    public static String getVal(String code) {
        ComeStockFlowEnum[] arr = values();
        for (ComeStockFlowEnum obj : arr) {
            if(obj.code.equals(code)){
                return obj.msg;
            }
        }
        return null;
    }

}
