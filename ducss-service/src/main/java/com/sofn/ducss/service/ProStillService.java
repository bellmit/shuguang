package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.model.DisperseUtilize;
import com.sofn.ducss.model.ProStill;

import java.util.List;
import java.util.Map;

public interface ProStillService extends IService<ProStill> {

    PageUtils<ProStill> getProStillByPage(Integer pageNo, Integer pageSize, List<String> years, List<String> countyIds, String dateBegin, String dateEnd);

    ProStill getProStill(List<String> years, List<String> countyIds);

    ProStill selectProStillById(String id);

    String selectProStillIdByYear(String countyId);

    PageUtils<ProStill> listGroupByYearAndAreaId(Map<String, Object> queryMap);

}
