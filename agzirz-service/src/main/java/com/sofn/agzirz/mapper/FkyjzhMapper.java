package com.sofn.agzirz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirz.model.FKYJZH;
import feign.Param;

import java.util.List;
import java.util.Map;

public interface FkyjzhMapper extends BaseMapper<FKYJZH> {

    List<FKYJZH> getFkyjzhByCondition(Map<String, Object> params);

    FKYJZH getFkyjzhById(@Param(value = "id") String id);

}