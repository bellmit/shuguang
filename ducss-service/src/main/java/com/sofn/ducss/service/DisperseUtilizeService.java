package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.DisperseUtilize;
import com.sofn.ducss.model.ProStill;

import java.util.List;
import java.util.Map;

public interface DisperseUtilizeService extends IService<DisperseUtilize> {

    PageUtils<DisperseUtilize> getDisperseUtilizeByPage(Integer pageNo, Integer pageSize, List<String> years, String userName, String countyId, String dateBegin, String dateEnd);

    PageUtils<DisperseUtilize> getDisperseUtilize(List<String> years, List<String> countyIds, Integer pageNo, Integer pageSize);

    DisperseUtilize selectDisperseUtilizeById(String id);

    String selectDisperseUtilizeIdByYear(String countyId, String farmerName);

    String findFarmerNo(String year, String areaId, String type);

    List<String> getFarmerNames(String countyId);

    PageUtils<DisperseUtilize> listGroupByYearAndAreaId(Map<String, Object> queryMap);
}
