package com.sofn.fyrpa.service;

import com.sofn.fyrpa.vo.AreasVo;
import com.sofn.fyrpa.vo.CityAreasVo;
import com.sofn.fyrpa.vo.CountyAreasVo;
import com.sofn.fyrpa.vo.SpatialAnalystResourcesVoList;

import java.util.List;

public interface SpatialAnalystService {

    /**
     * 按保护区分布查询
     * @param submitTime
     * @param name
     * @return
     */
    List<SpatialAnalystResourcesVoList> selectSpatialAnalystByCondition(String submitTime, String name);


    /**
     * 按保护区数量查询(省级)
     * @param submitTime
     * @param name
     * @return
     */
    List<AreasVo> selectProtectionByCount(String submitTime, String name);

    /**
     * 按保护区数量查询(市级)
     * @param submitTime
     * @param name
     * @return
     */
    List<CityAreasVo> selectCityAreasList(String submitTime, String name);

    /**
     * 按保护区数量查询(县区级)
     * @param submitTime
     * @param name
     * @return
     */
    List<CountyAreasVo> selectCountyAreasList(String submitTime, String name);


}
