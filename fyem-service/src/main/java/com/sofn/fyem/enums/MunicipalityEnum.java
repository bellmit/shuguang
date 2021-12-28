package com.sofn.fyem.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 直辖市枚举
 * @Author: DJH
 * @Date: 2020/9/3 18:04
 */
@Getter
public enum MunicipalityEnum {

    BEI_JING("110000", "北京市"),
    SHANG_HAI("310000", "上海市"),
    TIAN_JIN("120000", "天津市"),
    CHONG_QING("500000", "重庆市");

    private String adCode;
    private String adName;

    MunicipalityEnum(String adCode, String adName) {
        this.adCode = adCode;
        this.adName = adName;
    }

    public static List<String> adCodesList(){
        List<String> adCodes = new ArrayList<>();
        for (MunicipalityEnum muniEnum: MunicipalityEnum.values()){
            adCodes.add(muniEnum.getAdCode());
        }
        return adCodes;
    }
}
