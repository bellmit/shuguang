package com.sofn.ducss.model;

import lombok.Data;

import java.util.Date;

/**
 * 阈值年度管理
 */
@Data
public class ThresholdYearManager {

    /**
     * Id
     */
    private String id;

    /**
     * 数据年度
     */
    private String year;

    /**
     * 操作人
     */
    private String operation;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 是否新增
     */
    private String isAdd;

    /**
     * 如果不是新增使用哪年的数据进行对比
     */
    private String oddYear;

}
