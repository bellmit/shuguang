package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PapersYearInspectRetail;
import com.sofn.fdpi.vo.PapersYearInspectProcessVo;

import java.util.List;
import java.util.Map;

/**
 * 证书年审明细表
 */
public interface PapersYearInspectRetailMapper extends BaseMapper<PapersYearInspectRetail> {
    List<PapersYearInspectProcessVo> listForCondition(Map<String,Object> map);

}
