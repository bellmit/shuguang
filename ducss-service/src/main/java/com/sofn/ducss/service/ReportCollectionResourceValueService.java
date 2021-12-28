package com.sofn.ducss.service;

import com.sofn.ducss.model.wordmodel.ReportWordProduct;

import java.util.List;
import java.util.Map;

/**
 * 部级报告可收集量接口
 *
 * @author heyongjie
 * @date 2021/1/4 14:17
 */
public interface ReportCollectionResourceValueService {

    /**
     * 获取可收集量信息
     *
     * @param year 年度
     * @return List<ReportWordProduct>
     */
    List<ReportWordProduct> getReportWordInfo(String year);

    /**
     * 获取省的信息   [1, 12]      12  container  1
     *
     * @param year 年度
     * @return Map<String, Object>
     */
    Map<String, Object> getProvinceInfo(String year);

    /**
     * 获取秸秆综合利用量
     * @param year  年度
     * @return     Map<String,Object>
     */
    Map<String,Object> getUtlize(String year);

    /**
     * 根据省分组获取综合利用率
     * @param year  年度
     * @return  Map<String,Object>
     */
    Map<String,Object> getUtilzeRateInfoGroupProvince(String year);


    /**
     * 获取还田信息
     * @param year  年度
     * @return      Map<String,Object>
     */
    Map<String,Object> getReturnInfo(String year);

    /**
     * 获取五料化信息
     * @param year  年度
     * @return  Map<String,Object>
     */
    Map<String,Object> getFiveMaterials(String year);




}
