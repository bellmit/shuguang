package com.sofn.fdzem.vo;

import com.sofn.fdzem.model.BiologicalResidueMonitoring;
import com.sofn.fdzem.model.PlanktonMonitoring;
import com.sofn.fdzem.model.SedimentMonitoring;
import com.sofn.fdzem.model.WaterQualityMonitoring;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("趋势分析监测站数据封装类")
public class TrendAnalysisVo {

    @ApiModelProperty("所属年度")
    private String year;
    @ApiModelProperty("监测站名称")
    private String name;

    @ApiModelProperty("溶解氧/mg/L")
    private double dO;
    @ApiModelProperty("亚硝酸盐氮/mg/L")
    private double nitriteNitrogen;
    @ApiModelProperty("硝酸盐氮/mg/L")
    private double nitrateNitrogen;
    @ApiModelProperty("PH")
    private double ph;
    @ApiModelProperty("水深/m")
    private double waterDepth;

    @ApiModelProperty("石油类/mg/L")
    private double petroleum;
    @ApiModelProperty("铜/ug/L")
    private double cu;
    @ApiModelProperty("锌/ug/L")
    private double zn;
    @ApiModelProperty("铅/ug/L")
    private double pb;
    @ApiModelProperty("镉/ug/L")
    private double cd;
    @ApiModelProperty("汞/ug/L")
    private double hg;
    @ApiModelProperty("砷/ug/L")
    private double asA;
    @ApiModelProperty("铬/ug/L")
    private double cr;

    @ApiModelProperty("叶绿素a/ug/L")
    private double chlorophyll;
    @ApiModelProperty("浮游植物密度/1000个/m3")
    private double phytoplanktonDensity;
    @ApiModelProperty("浮游植物种类数/个")
    private double phytoplanktonSpeciesNumber;
    @ApiModelProperty("浮游植物多样性指数/H'")
    private double phytoplanktonDiversityIndex;
    @ApiModelProperty("浮游动物密度/1000个/m3")
    private double zooplanktonDensity;
    @ApiModelProperty("浮游动物生物量/mg/m3")
    private double zooplanktonBiomass;
    @ApiModelProperty("浮游动物种类数/个")
    private double zooplanktonSpeciesNumber;
    @ApiModelProperty("浮游动物多样性指数/H")
    private double zooplanktonDiversityIndex;


    @ApiModelProperty("大肠菌群/个/100g")
    private double coliform;
//    @ApiModelProperty("铜/mg/kg")
//    private double cu;
//    @ApiModelProperty("铅/mg/kg")
//    private double pb;
//    @ApiModelProperty("镉/mg/kg")
//    private double cd;
    @ApiModelProperty("总汞/mg/kg")
    private double tHg;
    @ApiModelProperty("无机砷/mg/kg")
    private double iAs;
//    @ApiModelProperty("铬/mg/kg")
//    private double cr;
    @ApiModelProperty("甲基汞/mg/k")
    private double meHg;
}
