package com.sofn.dhhrp.service;

import com.sofn.dhhrp.vo.EarlyWarning;
import com.sofn.dhhrp.vo.MonitoringPointVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 14:07
 */

public interface AnalysisService {
    List<MonitoringPointVo> getList1(Map map);
    EarlyWarning getMapResult(String pointName);
}
