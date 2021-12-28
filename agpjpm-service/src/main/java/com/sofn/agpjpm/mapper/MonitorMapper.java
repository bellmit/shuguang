package com.sofn.agpjpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjpm.model.Monitor;

import java.util.List;
import java.util.Map;

public interface MonitorMapper extends BaseMapper<Monitor> {

    List<Monitor> listByParams(Map<String, Object> params);
}
