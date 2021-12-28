package com.sofn.ducss.vo;

import lombok.Data;

@Data
public class DataAnalyVo {

    private int id;
    private String year;

    private String strawType;

    private String areaName;

    private String strawName;

    /***
     * 粮食产量
     */
    private String grainYield;
    /***
     * 草谷比
     */
    private String grassValleyRatio;

    /***
     * 可收集系数
     */
    private String collectionRatio;


    /***
     * 播种面积
     */
    private String seedArea;

    /***
     * 还田面积
     */
    private String returnArea;

    /***
     * 调出量
     */
    private String exportYield;

    /***
     * 产生量
     */
    private String theoryResource;

    /***
     * 可收集量
     */
    private String collectResource;
    /***
     * 市场主体利用量
     */
    private String marketEnt;

    /***
     * 市场主体肥料化
     */
    private String fertilising;

    /***
     * 市场主体饲料化
     */
    private String forage;
    /***
     * 市场主体燃料化
     */
    private String fuel;
    /***
     * 市场主体基料化
     */
    private String base;
    /***
     * 市场主体原料化
     */
    private String material;
    /***
     * 分散利用量
     */
    private String reuse;
    /***
     * 分散肥料化
     */
    private String fertilisings;
    /***
     * 分散饲料化
     */
    private String forages;
    /***
     * 分散肥料化
     */
    private String fuels;
    /***
     * 分散基料化
     */
    private String bases;
    /***
     * 分散原料化
     */
    private String materials;
    /***
     * 直接还田量
     */
    private String returnResource;
    /***
     * 市场主体调入量
     */
    private String other;
    /***
     * 肥料化利用量
     */
    private String fertilize;
    /***
     * 饲料化利用量
     */
    private String feed;
    /***
     * 燃料化利用量
     */
    private String fuelled;
    /***
     * 基料化利用量
     */
    private String baseMat;
    /***
     * 原料化利用量
     */
    private String materialization;
    /***
     * 秸秆利用量
     */
    private String totol;
    /***
     * 综合利用率
     */
    private String totolRate;
    /***
     * 综合利用能力指数
     */
    private String comprUtilIndex;
    /***
     * 产业化利用能力指数
     */
    private String induUtilIndex;

    /***
     * 秸秆利用量 只用于计算综合利用率
     */
    private String totolV2;

    /**
     * 可收集量 只用于计算综合利用率
     */
    private String collectResourceV2;


}
