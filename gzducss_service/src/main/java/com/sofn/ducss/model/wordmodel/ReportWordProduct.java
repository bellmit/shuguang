package com.sofn.ducss.model.wordmodel;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  产生量信息
 * @author heyongjie
 * @date 2020/12/31 16:25
 */
@Data
public class ReportWordProduct {

    /**
     * 区划ID
     */
    private String areaId;

    /**
     * 区划名称
     */
    private String areaName;

    /**
     * 作物代码
     */
    private String strawCode;

    /**
     * 作物名称
     */
    private String strawName;

    /**
     * 产生量
     */
    private BigDecimal produce;

    /**
     * 年度
     */
    private String year;

    /**
     * 占总量的百分比
     */
    private BigDecimal proportion;

}
