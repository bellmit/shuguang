package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Sturgeon;
import com.sofn.fdpi.vo.SelectVo;

import java.util.List;
import java.util.Map;

public interface SturgeonMapper extends BaseMapper<Sturgeon> {

    List<Sturgeon> listByParams(Map<String, Object> params);

    List<SelectVo> listCredentials(Map<String, Object> params);

    String getTodayMaxApplyNum(String todayStr);
}
