package com.sofn.agzirz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.model.Xgxxsj;
import feign.Param;

import java.util.List;
import java.util.Map;

public interface XgxxsjMapper extends BaseMapper<Xgxxsj> {

    List<Xgxxsj> getXgxxsjByCondition(Map<String, Object> params);

    Xgxxsj getXgxxsjById(@Param(value = "id") String id);

}