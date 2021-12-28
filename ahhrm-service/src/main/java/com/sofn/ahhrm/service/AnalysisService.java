package com.sofn.ahhrm.service;

import com.sofn.ahhrm.vo.EarlyWarning;
import com.sofn.ahhrm.vo.MonitoringPointVo;
import com.sofn.ahhrm.vo.MonitoringVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 14:07
 */

public interface AnalysisService {
    List<MonitoringPointVo> getList(Map map);
    EarlyWarning getMapResult(String pointName);
}
