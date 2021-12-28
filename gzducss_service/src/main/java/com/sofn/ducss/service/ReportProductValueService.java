package com.sofn.ducss.service;

import java.util.Map;

/**
 * 产生量报表信息
 * @author heyongjie
 * @date 2020/12/31 10:30
 */
public interface ReportProductValueService {

    /**
     * 获取产生量值报表
     * @param year  年度
     * @return   Map<String,Object>
     */
    Map<String,Object> getValue(String year);

    /**
     * 获取县的信息
     * @param year  年度
     * @return   Map<String,Object>
     */
    Map<String,Object> getAreaValue(String year);

    /**
     * 获取作物的信息
     * @param year  年度
     * @return     Map<String,Object>
     */
    Map<String,Object> getStrawValue(String year);
}
