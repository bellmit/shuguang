package com.sofn.ducss.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 阈值值管理
 */
@Data
public class ThresholdValueManager {

    private String id;

    /**
     * 数据年度
     */
    private String year;

    /**
     * 表类型
     * 1.数据审核
     * 2. 产生情况汇总
     * 3. 利用情况汇总
     * 4.还田离田情况
     */
    private String tableType;

    /**
     * 指标类型
     * 1-1.产生量（万吨）
     * 1-2.可收集量（万吨）
     * 1-3.综合利用量（万吨）
     * 1-4.综合利用率（%）
     * 1-5. 直接还田量（万吨）
     * 1-6. 离田利用量（万吨）
     * 1-7. 抽样分散户数（个
     * 1-8.市场主体规模化数量（个）
     *
     * 2-1.产生量（万吨）
     * 2-2.产生量占比（%）
     * 2-3.可收集量（万吨）
     * 2-4.可收集量比例（%）
     * 2-5.粮食产量（万吨）
     * 2-6.播种面积（千公顷）
     *
     * 3-1.秸秆利用量（万吨）
     * 3-2 综合利用率（%）
     * 3-3.合计
     * 3-4.肥料化
     * 3-5 饲料化
     * 3-6. 燃料化
     * 3-7.基料化
     * 3-8.原料化
     * 3-9.综合利用能力指数
     * 3-10.产业化利用能力指数
     *
     * 4-1.直接还田量（万吨）
     * 4-2.直接还田率（%）
     * 4-3.合计
     * 4-4.农户分散利用量
     * 4-5.市场主体规模化利用量
     */
    private String targetType;

    /**
     * 计算类型   1，数值  2 比例
     */
    private String computerType;

    /**
     * 阈值1
     */
    private BigDecimal value1;

    /**
     * 操作1
     */
    private String operate1;

    /**
     * 阈值2
     */
    private BigDecimal value2;

    /**
     * 操作2
     */
    private String operate2;


}
