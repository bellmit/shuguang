package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("区域类")
@Data
public class DataAnalysisArea extends Model<DataAnalysisArea> {
    private static final long serialVersionUID = 2662491304701311199L;
    @TableId(type = IdType.AUTO)
    private int id;

    private String year;

    private String areaId;

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

    private BigDecimal leaveNumber;

    private String returnType;

    private String leavingType;

    private BigDecimal transportAmount;
}