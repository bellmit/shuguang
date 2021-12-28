package com.sofn.agpjyz.service;

import com.sofn.agpjyz.vo.SurveyInfoVo;
import com.sofn.agpjyz.vo.TrendVo;

import java.util.List;
import java.util.Map;

public interface IndexService {

    List<String> listArea(String year, String specId, String areaCode, String queryCode);

    Map<String, Object> getAmountAndNames(Map<String, Object> params);

    SurveyInfoVo getInfo(String areaCode, String year, String id);

    List<TrendVo> listTrend(Map<String, Object> params);

}
