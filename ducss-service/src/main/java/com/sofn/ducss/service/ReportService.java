/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:31
 */
package com.sofn.ducss.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 部级报告生成接口
 *
 * @author jiangtao
 * @version 1.0
 **/
public interface ReportService {

    /**
     * 部级主要粮食作物秸秆产生与利用情况报告部分
     *
     * @param year 年份
     * @return map
     */
    Map<String, Object> getMajorYieldReport(String year, List<String> areaCodes, String strawType);

    /**
     * 部级主要粮食作物秸秆产生与利用情况报告部分
     *
     * @param request          请求
     * @param response       返回
     * @param year  年份
     *
     */
    // void getMajorYieldReportTotal(HttpServletRequest request, HttpServletResponse response, String year);


    /**
     * 不同地区秸秆产生与利用情况分析报告部分
     *
     * @param year       请求
     * @param regionCode 返回
     * @param regionName 年份
     */
    Map<String, Object> getDifferentRegionReportMap(String year, List<String> areaCodes, String regionCode, String regionName);


    /**
     * 不同地区秸秆产生与利用情况分析报告部分
     *
     * @param request          请求
     * @param response       返回
     * @param year  年份
     *
     */
    // void getDifferentRegionReport(HttpServletRequest request, HttpServletResponse response, String year);


    /**
     * 获取全国农作物秸秆产生与利用情况分析报告
     */
    Map<String, Object> getProductAndUseInfoRort(String year);

}
