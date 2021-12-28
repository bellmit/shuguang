package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DataAnalysisCityV2 {
    private static final long serialVersionUID = 7884066717341722581L;
    @TableId(type = IdType.AUTO)
    private int id;

    private String year;

    private String cityId;

    private String strawType;

    private String grainYield;

    private String grassValleyRatio;

    private String collectionRatio;

    private String seedArea;

    private String returnArea;

    private String exportYield;

    private String theoryResource;

    private String collectResource;

    private String marketEnt;

    private String fertilizes;

    private String feeds;

    private String fuelleds;

    private String baseMats;

    private String materializations;

    private String reuse;

    private String fertilisingd;

    private String foraged;

    private String fueld;

    private String based;

    private String materiald;

    private String returnResource;

    private String other;

    private String fertilize;

    private String feed;

    private String fuelled;

    private String baseMat;

    private String materialization;

    private String strawUtilization;

    private String totolRate;

    private String comprUtilIndex;

    private String induUtilIndex;

    private String areaName;

    private String strawName;

    private String yieldAllNum;
}
