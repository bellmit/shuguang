package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.MonitoringWarning;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:17
 */
public interface MonitoringWarningMapper  extends BaseMapper<MonitoringWarning> {

    List<MonitoringWarning> listByParams(Map<String, Object> params);
    MonitoringWarning listByParamsTwo(Map<String, Object> params);
    MonitoringWarning listByParamsThree(Map<String, Object> params);
    MonitoringWarning list(Map<String, Object> params);
}
