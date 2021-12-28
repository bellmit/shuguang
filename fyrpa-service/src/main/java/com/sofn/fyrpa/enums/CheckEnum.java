package com.sofn.fyrpa.enums;

import lombok.Getter;

@Getter
public enum CheckEnum {

     IS_WSB("-2","未上报"),
     //IS_SSP("-1","省审批"),
    IS_DSP_WJZJ("0","待审(未经专家)"),
//    IS_DSP_YGZJ("1","待审(已过专家)"),
    IS_Ybh("3","已驳回"),
    IS_ZJSP("4","专家审批"),
    IS_DSB("7", "待上报"),
    IS_YTG("2","已通过");
    /*IS_DSP("5","待审批"),
    IS_SJSP("6","省级审批");*/

    private final String key;
    private final String val;

    CheckEnum(String key, String val) {
        this.key = key;
        this.val = val;

    }
}