package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.DisperseUtilize;
import com.sofn.ducss.util.PageUtils;

import java.util.List;

public interface DisperseUtilizeService extends IService<DisperseUtilize> {

    PageUtils<DisperseUtilize> getDisperseUtilizeByPage(Integer pageNo, Integer pageSize, String year, String userName, String countyId, String dateBegin, String dateEnd, String orderBy, String sortOrder);

    PageUtils<DisperseUtilize> getDisperseUtilize(String year, String countyId, Integer pageNo, Integer pageSize);

    DisperseUtilize selectDisperseUtilizeById(String id);

    String selectDisperseUtilizeIdByYear(String countyId, String farmerName);

    String findFarmerNo(String year, String areaId, String type);

    List<String> getFarmerNames(String countyId);

}
