package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.TargetSpecies;
import com.sofn.agpjyz.vo.YearVo;

import java.util.List;
import java.util.Map;

public interface TargetSpeciesCollectMapper extends BaseMapper<TargetSpecies> {

    List<TargetSpecies> listByParams(Map<String, Object> params);

    List<TargetSpecies>  listByName(String protectValue);
    List<YearVo>  getYear();
    TargetSpecies getByParams(Map<String, Object> params);
    void updateAmount(String id);
}
