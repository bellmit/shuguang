package com.sofn.fdpi.service.impl;

import com.sofn.common.service.BaseService;
import com.sofn.fdpi.mapper.PapersYearInspectRetailMapper;
import com.sofn.fdpi.model.PapersYearInspectRetail;
import com.sofn.fdpi.service.PapersYearInspectRetailService;
import com.sofn.fdpi.vo.PapersYearInspectProcessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("papersYearInspectRetailService")
public class PapersYearInspectRetailServiceImpl extends BaseService<PapersYearInspectRetailMapper, PapersYearInspectRetail> implements PapersYearInspectRetailService {
//    @Autowired
//    private PapersYearInspectRetailMapper papersYearInspectRetailMapper;
//    @Override
//    public List<PapersYearInspectProcessVo> listForCondition(Map<String, Object> map) {
//        return papersYearInspectRetailMapper.listForCondition(map);
//    }
}
