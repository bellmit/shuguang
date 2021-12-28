package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.MonitoringWarning;

import java.util.List;
import java.util.Map;

public interface MonitoringWarningMapper  extends BaseMapper<MonitoringWarning> {

    List<MonitoringWarning> listByParams(Map<String, Object> params);
    MonitoringWarning listByParamsTwo(Map<String, Object> params);
    MonitoringWarning listByParamsThree(Map<String, Object> params);
    MonitoringWarning list(Map<String, Object> params);
}
