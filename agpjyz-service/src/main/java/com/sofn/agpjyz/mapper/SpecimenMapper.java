package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.Source;
import com.sofn.agpjyz.model.Specimen;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 14:16
 */
public interface SpecimenMapper extends BaseMapper<Specimen> {
    List<Specimen> listByParams(Map<String, Object> params);
}
