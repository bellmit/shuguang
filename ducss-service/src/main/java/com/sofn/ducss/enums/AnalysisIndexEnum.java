package com.sofn.ducss.enums;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/8/3 15:32
 * 数据分析，分析指标前端查询枚举类（值定死）
 **/
@Getter
public enum AnalysisIndexEnum {

    GRAINYIELD("1", new BigDecimal(1)),
    KUSUGANI("2", new BigDecimal(2)),
    COLLECTABLECOE("3", new BigDecimal(3)),
    SOWNAREA("4", new BigDecimal(4)),
    RETURNAREA("5", new BigDecimal(5)),
    CALLOUT("6", new BigDecimal(6)),
    YIELD("7", new BigDecimal(7)),
    COLLECTABLEAMO("8", new BigDecimal(8)),
    MARKETENTITY("9", new BigDecimal(2.0000000000)),
    FERTILIZATION("10", new BigDecimal(10)),
    FEED("11", new BigDecimal(11)),
    FUELLED("12", new BigDecimal(12)),
    BASEMAT("13", new BigDecimal(13)),
    MATERIALIZATION("14", new BigDecimal(14)),
    DECENTRALIZED("15", new BigDecimal(15)),
    DECENTRALIZEDs("16", new BigDecimal(16)),
    DECENTRA("17", new BigDecimal(17)),
    DECENTR("18", new BigDecimal(18)),
    DECENT("19", new BigDecimal(19)),
    DECEN("20", new BigDecimal(20)),
    DECE("21", new BigDecimal(0)),
    DEC("22", new BigDecimal(22)),
    DECES("23", new BigDecimal(10)),
    DECEB("24", new BigDecimal(24)),
    DECESS("25", new BigDecimal(25)),
    DECESD("26", new BigDecimal(26)),
    DECEG("27", new BigDecimal(426165.33)),
    DECEH("28", new BigDecimal(28)),
    DECEND("29", new BigDecimal(29)),
    DECEBF("30", new BigDecimal(30)),
    DECEBG("31", new BigDecimal(31));


    private String num;
    private BigDecimal value;

    AnalysisIndexEnum(String num, BigDecimal value) {
        this.num = num;
        this.value = value;
    }
}
