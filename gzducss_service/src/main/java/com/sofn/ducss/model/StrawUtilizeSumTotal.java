package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@TableName("straw_utilize_sum_total")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class StrawUtilizeSumTotal extends Model<StrawUtilizeSumTotal> {
    private String id;

    private String year;

    private String provinceId;

    private String cityId;

    private String areaId;

    private BigDecimal mainFertilising;

    private BigDecimal mainForage;

    private BigDecimal mainFuel;

    private BigDecimal mainBase;

    private BigDecimal mainMaterial;

    private BigDecimal mainTotal;

    private BigDecimal mainTotalOther;

    private BigDecimal disperseFertilising;

    private BigDecimal disperseForage;

    private BigDecimal disperseFuel;

    private BigDecimal disperseBase;

    private BigDecimal disperseMaterial;

    private BigDecimal disperseTotal;

    private BigDecimal proStrawUtilize;

    private BigDecimal returnRatio;

    private BigDecimal comprehensive;

    private BigDecimal comprehensiveIndex;

    private BigDecimal industrializationIndex;

    private BigDecimal collectResource;

    private BigDecimal yieldAllNum;

    private BigDecimal theoryResource;

    private BigDecimal exportYieldTotal;

    private BigDecimal fertilising;

    private BigDecimal forage;

    private BigDecimal fuel;

    private BigDecimal base;

    private BigDecimal material;

    private BigDecimal grassValleyRatio;

    private BigDecimal returnResource;

    private BigDecimal collectionRatio;

    private String level;
}