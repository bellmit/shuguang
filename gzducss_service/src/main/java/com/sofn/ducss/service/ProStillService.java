package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.ProStill;
import com.sofn.ducss.util.PageUtils;

public interface ProStillService extends IService<ProStill> {

    PageUtils<ProStill> getProStillByPage(Integer pageNo, Integer pageSize, String year, String countyId,String dateBegin,String dateEnd);

    ProStill getProStill(String year, String countyId);

    ProStill selectProStillById(String id);

    String selectProStillIdByYear(String countyId);
}
