package com.sofn.dhhrp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.dhhrp.model.Baseinfo;

import java.util.List;
import java.util.Map;

public interface AnalysieMapper extends BaseMapper<Baseinfo> {

    List<Baseinfo> listByParams(Map<String, Object> params);

}
