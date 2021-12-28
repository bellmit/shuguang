package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SturgeonReprint;

import java.util.List;
import java.util.Map;

public interface SturgeonReprintMapper extends BaseMapper<SturgeonReprint> {

    List<SturgeonReprint> listByParams(Map<String, Object> params);

    String getTodayMaxApplyNum(String todayStr);

    String getYearMaxSequenceNum(String contractNum);
}
