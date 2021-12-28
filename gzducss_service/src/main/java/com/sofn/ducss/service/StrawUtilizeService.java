package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.StrawUtilize;
import com.sofn.ducss.util.PageUtils;

import java.util.List;

public interface StrawUtilizeService extends IService<StrawUtilize> {

    PageUtils<StrawUtilize> getStrawUtilizeByPage(Integer pageNo, Integer pageSize, String year, String mainName, String countyId, String dateBegin, String dateEnd);

    PageUtils<StrawUtilize> getStrawUtilize(String year, String countyId,Integer pageNo, Integer pageSize);

    StrawUtilize selectStrawUtilizeById(String id);

    String selectStrawUtilizeDetailIdByYear(String countyId,String mainName);

    String findFarmerNo(String year, String areaId, String type);

    List<String> getMainNames(String countyId);

    //根据年度，省ID查询该年度该省从事秸秆利用的市场主体个数
    int countCompanyByYearAndProvince(String year, String areaId);



}
