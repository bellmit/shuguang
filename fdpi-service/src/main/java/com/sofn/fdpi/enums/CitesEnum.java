package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum CitesEnum {

    BESTER("00", "杂交鲟"),
    ACIPENSER_SCHRENCKII("01", "施氏鲟"),
    ACIPENSER_GUELDENSTAEDTI("02", "俄罗斯鲟"),
    ACIPENSER_BAERII("03", "西伯利亚鲟"),
    ACIPENSER_STELLATUS("04", "闪光鲟"),
    HUSO_DAURICUS("05", "鳇"),
    HUSO_HUSO("06", "欧洲鳇"),
    ACIPENSER_NUDIVENTRIS("07", "裸腹鲟"),
    ACIPENSER_RUTHENUS("08", "小体鲟"),
    GAOSHOUYU  ("09", "高首鳇"),
    ACIPENSER_DABRYANUS_DUMERIL("10", "长江鲟");


    private String key;
    private String val;

    CitesEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        CitesEnum[] arr = CitesEnum.values();
        List<SelectVo> list = new ArrayList<>(arr.length);
        for (CitesEnum obj : arr) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        CitesEnum[] arr = values();
        for (CitesEnum obj : arr) {
            if(obj.key.equals(key)){
                return obj.val;
            }
        }
        return null;
    }
}
