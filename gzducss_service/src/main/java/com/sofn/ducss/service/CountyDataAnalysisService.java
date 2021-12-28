/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-14 17:16
 */
package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.DataAnalysisArea;
import com.sofn.ducss.vo.StrawUsageVo;

import java.util.List;

/**
 * 县级数据分析
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface CountyDataAnalysisService extends IService<DataAnalysisArea> {

    /**
     * 数据分析根据区域id获取数据保存至数据分析中间表
     *
     * @param year     年份
     * @param areaCode 区域等级
     */
    void insertCountyDataAnalysis(String year, String areaCode);


    /**
     * 数据分析根据区域id获取数据保存至市级表
     *
     * @param year     年份
     * @param areaCode 市级id
     */
    void insertCityDataAnalysis(String year, String areaCode);

    /**
     * 数据分析根据区域id获取数据保存至省级表（6大区）
     *
     * @param year     年份
     * @param areaCode 市级id
     */
    void insertProvinceDataAndSixRegionAnalysis(String year, String areaCode);


    /**
     * 市级退回时删除退回县数据
     *
     * @param year     年份
     * @param areaCode 市级id
     */
    boolean deleCountyDataAnalysis(String year, String areaCode);

    /**
     * 省级退回时删除退回市数据
     *
     * @param year     年份
     * @param areaCode 市级id
     */
    boolean deleCityDataAnalysis(String year, String areaCode);

    /**
     * 部级退回时删除退回省数据
     *
     * @param year     年份
     * @param areaCode 市级id
     */
    boolean deleProvinceDataAnalysis(String year, String areaCode);

    List<StrawUsageVo> findDataByAreaIdsAndYears(List<String> areaIds, List<String> years);

    List<StrawUsageVo> getCountyDataAnalysis(String year, String areaCode);
}


