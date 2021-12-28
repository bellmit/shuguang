package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.StrawUtilize;

import java.util.List;
import java.util.Map;

public interface StrawUtilizeService extends IService<StrawUtilize> {

    PageUtils<StrawUtilize> getStrawUtilizeByPage(Integer pageNo, Integer pageSize, List<String> years, String mainName, String countyId, String dateBegin, String dateEnd);

    PageUtils<StrawUtilize> getStrawUtilize(List<String> years, List<String> countyIds, Integer pageNo, Integer pageSize);

    StrawUtilize selectStrawUtilizeById(String id);

    String selectStrawUtilizeDetailIdByYear(String countyId, String mainName);

    String findFarmerNo(String year, String areaId, String type);

    List<String> getMainNames(String countyId);

    //根据年度，省ID查询该年度该省从事秸秆利用的市场主体个数
    int countCompanyByYearAndProvince(String year, String areaId);

    PageUtils<StrawUtilize> listGroupByYearAndAreaId(Map<String, Object> queryMap);

    List<StrawUtilize> listByYearAndProvinceId(String year, String provinceId);

}
