package com.sofn.ducss.enums;

import lombok.Getter;

/**
 * 数据范围类型枚举
 *
 * @author jiangtao
 * @date 2020/11/6
 */
@Getter
public enum DataAreaTypeEnum {
    /**
     *数据范围类型枚举
     *
     * */
    REPORT_COUNTY("reportCounty","填报县数"),
    STRAW_UTILIZE("strawUtilize","市场规模化主体数"),
    DISPERSE_UTILIZE("disperseUtilize","抽样分散户数")
    ;


    private String code;
    private String description;


    DataAreaTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
