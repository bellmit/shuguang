package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class DataAnalysisDto {

    @ApiModelProperty(value = "粮食产量")
    private String grainYield;
    @ApiModelProperty(value = "草谷比")
    private String grassValleyRatio;

    @ApiModelProperty(value = "可收集系数")
    private String collectionRatio;

    @ApiModelProperty(value = "播种面积")
    private String seedArea;

    @ApiModelProperty(value = "还田面积")
    private String returnArea;

    @ApiModelProperty(value = "调出量")
    private String exportYield;

    @ApiModelProperty(value = "产生量")
    private String theoryResource;

    @ApiModelProperty(value = "可收集资源量")
    private String collectResource;

    @ApiModelProperty(value = "市场主体利用量")
    private String marketEnt;

    @ApiModelProperty(value = "肥料化")
    private String fertilising;

    @ApiModelProperty(value = "饲料化")
    private String forage;

    @ApiModelProperty(value = "燃料化")
    private String fuel;

    @ApiModelProperty(value = "基料化")
    private String base;

    @ApiModelProperty(value = "原料化")
    private String material;

    private String reuse;

    private String fertilisings;
    private String forages;
    private String fuels;
    private String bases;
    private String materials;
    private String returnResource;
    private String other;
    private String fertilize;
    private String feed;
    private String fuelled;
    private String baseMat;
    private String materialization;
    private String totol;
    private String totolRate;
    private String comprUtilIndex;
    private String induUtilIndex;



    public void setGrainYield(String grainYield) {
        this.grainYield = grainYield;
    }

    public void setGrassValleyRatio(String grassValleyRatio) {
        this.grassValleyRatio = grassValleyRatio;
    }
}
