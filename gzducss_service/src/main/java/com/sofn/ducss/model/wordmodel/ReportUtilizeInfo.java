package com.sofn.ducss.model.wordmodel;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 利用量信息
 * @author heyongjie
 * @date 2021/1/4 18:46
 */
@Data
public class ReportUtilizeInfo {


    private String areaId;

    private String areaName;

    private String strawId;

    private String strawName;

    /**
     * 秸秆利用率 =   秸秆使用量 /  可收集量  * 100
     */
    private BigDecimal number1;

    /**
     * 秸秆使用量
     */
    private BigDecimal number2;

    /**
     * 可收集量
     */
    private BigDecimal number3;


}
