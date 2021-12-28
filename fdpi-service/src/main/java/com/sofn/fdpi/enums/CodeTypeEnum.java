package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription 申请单号类型
 **/
@Getter
public enum CodeTypeEnum {

    PAPER_CHANGE("01", "证书变更"),
    PAPERS_YEAR_INSPECT("02","证书年审"),
    SPE_CHANGE("03","物种变更"),
    SPE_TRANSFER("04","物种转移"),
    SIGNBOARD_APPLY("05","国内标识申请"),
    STURGEON_APPLY("06","CITES物种标识申请"),
    STURGEON_PAPER_APPLY("07","标签纸申请"),
    STURGEON_REPRINT("08", "标识补打"),
    CITES_APPLY("09", "国内鱼子酱标识申请"),
    CITES_PAPER_APPLY("10", "国内鱼子酱标签纸申请"),
    CITES_REPRINT("08", "国内鱼子酱标识补打");


    private String key;
    private String val;

    CodeTypeEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        CodeTypeEnum[] arr = CodeTypeEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (CodeTypeEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        CodeTypeEnum[] arr = values();
        for (CodeTypeEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
