package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SturgeonPaper;
import com.sofn.fdpi.vo.SturgeonPaperVo;

import java.util.List;
import java.util.Map;

public interface SturgeonPaperMapper extends BaseMapper<SturgeonPaper> {

    List<SturgeonPaperVo> listByParams(Map<String, Object> params);

    String getTodayMaxApplyNum(String todayStr);
}
