/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-19 17:02
 */
package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 数据分析六大区实体
 *
 * @author jiangtao
 * @version 1.0
 **/
@ApiModel("数据分析六大区实体")
@Data
@TableName("data_analysis_six_area")
public class DataAnalysisSixArea {

    @TableId(type = IdType.AUTO)
    private int id;

    private String year;

    private String sixAreaId;

    private String strawType;

    private BigDecimal grainYield;

    private BigDecimal grassValleyRatio;

    private BigDecimal collectionRatio;

    private BigDecimal seedArea;

    private BigDecimal returnArea;

    private BigDecimal exportYield;

    private BigDecimal theoryResource;

    private BigDecimal collectResource;

    private BigDecimal marketEnt;

    private BigDecimal fertilizes;

    private BigDecimal feeds;

    private BigDecimal fuelleds;

    private BigDecimal baseMats;

    private BigDecimal materializations;

    private BigDecimal reuse;

    private BigDecimal fertilisingd;

    private BigDecimal foraged;

    private BigDecimal fueld;

    private BigDecimal based;

    private BigDecimal materiald;

    private BigDecimal returnResource;

    private BigDecimal other;

    private BigDecimal fertilize;

    private BigDecimal feed;

    private BigDecimal fuelled;

    private BigDecimal baseMat;

    private BigDecimal materialization;

    private BigDecimal strawUtilization;

    private BigDecimal totolRate;

    private BigDecimal comprUtilIndex;

    private BigDecimal induUtilIndex;

    private String areaName;

    private String strawName;

    private BigDecimal yieldAllNum;

}
