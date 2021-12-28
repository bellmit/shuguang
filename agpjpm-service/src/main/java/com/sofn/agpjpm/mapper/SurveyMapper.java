package com.sofn.agpjpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjpm.model.Monitor;
import com.sofn.agpjpm.model.Survey;
import com.sofn.agpjpm.vo.ServeyListVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:40
 */
public interface SurveyMapper extends BaseMapper<Survey> {
    List<ServeyListVo> listByParams(Map<String, Object> params);
}
