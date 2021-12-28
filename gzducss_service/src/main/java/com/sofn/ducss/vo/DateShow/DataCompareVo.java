package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 数据展示2.0中数据对比Vo
 *
 * @author jiangtao
 * @date 2020/11/15
 */
@Data
@ApiModel(value = "数据展示2.0中数据对比Vo")
@NoArgsConstructor
@AllArgsConstructor
public class DataCompareVo {

    /**
     * 填报县数
     */
    @ApiModelProperty(value = "填报县数")
    private BigDecimal reportCountyNum;

    /**
     * 市场规模化主体数
     */
    @ApiModelProperty(value = "市场规模化主体数")
    private BigDecimal strawUtilizeNum;

    /**
     * 抽样分散户数
     */
    @ApiModelProperty(value = "抽样分散户数")
    private BigDecimal disperseUtilizeNum;

    /**
     * 产生量
     */
    @ApiModelProperty(value = "产生量")
    private BigDecimal theoryResource;

    /**
     * 产生量占比
     */
    @ApiModelProperty(value = "产生量占比")
    private BigDecimal theoryResourceProportion;

    /**
     * 可收集量
     */
    @ApiModelProperty(value = "可收集量")
    private BigDecimal collectResource;

    /**
     * 可收集量占比
     */
    @ApiModelProperty(value = "可收集量占比")
    private BigDecimal collectResourceProportion;

    /**
     * 粮食产量
     */
    @ApiModelProperty(value = "粮食产量")
    private BigDecimal grainYield;

    /**
     * 播种面积
     */
    @ApiModelProperty(value = "播种面积")
    private BigDecimal seedArea;

    /**
     * 秸秆利用量
     */
    @ApiModelProperty(value = "秸秆利用量")
    private BigDecimal proStrawUtilize;

    /**
     * 秸秆利用率
     */
    @ApiModelProperty(value = "秸秆利用率")
    private BigDecimal comprehensive;

    /**
     * 五料化合计
     */
    @ApiModelProperty(value = "五料化合计")
    private BigDecimal allTotal;

    /**
     * 肥料化利用量
     */
    @ApiModelProperty(value = "肥料化利用量")
    private BigDecimal fertilising;

    /**
     * 饲料化利用量
     */
    @ApiModelProperty(value = "饲料化利用量")
    private BigDecimal forage;

    /**
     * 燃料化利用量
     */
    @ApiModelProperty(value = "燃料化利用量")
    private BigDecimal fuel;

    /**
     * 基料化利用量
     */
    @ApiModelProperty(value = "基料化利用量")
    private BigDecimal base;

    /**
     * 原料化利用量
     */
    @ApiModelProperty(value = "原料化利用量")
    private BigDecimal material;

    /**
     * 综合利用指数
     */
    @ApiModelProperty(value = "综合利用指数")
    private BigDecimal comprehensiveIndex;


    /**
     * 产业化利用指数
     */
    @ApiModelProperty(value = "产业化利用指数")
    private BigDecimal industrializationIndex;


    /**
     * 直接还田量
     */
    @ApiModelProperty(value = "直接还田量")
    private BigDecimal returnResource;

    /**
     * 直接还田率
     */
    @ApiModelProperty(value = "直接还田率")
    private BigDecimal returnRatio;


    /**
     * 离田利用量
     */
    @ApiModelProperty(value = "离田利用量")
    private BigDecimal leavingUtilization;

    /**
     * 农户分散利用量
     */
    @ApiModelProperty(value = "农户分散利用量")
    private BigDecimal disperseUtilize;


    /**
     * 市场主体规模化利用量
     */
    @ApiModelProperty(value = "市场主体规模化利用量")
    private BigDecimal mainUtilize;


}