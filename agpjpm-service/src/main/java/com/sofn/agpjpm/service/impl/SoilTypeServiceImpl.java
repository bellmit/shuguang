package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjpm.mapper.SoilTypeMapper;
import com.sofn.agpjpm.model.SoilType;
import com.sofn.agpjpm.service.SoilTypeService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.util.ApiUtil;
import com.sofn.agpjpm.vo.DropDownVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:52
 */
@Service("soilService")
public class SoilTypeServiceImpl implements SoilTypeService {
    @Autowired
    private SoilTypeMapper soilTypeMapper;
    @Autowired
    private JzbApi jzbApi;
    @Override
    public void save(SoilType soilType,String surveyId) {
        if (!StringUtils.isEmpty(soilType.getSoilId())){
            soilType.setId(IdUtil.getUUId());
            soilType.setSurveyId(surveyId);
            soilTypeMapper.insert(soilType);
        }

    }

    @Override
    public List<SoilType> getBySurveyId(String surveyId) {
        QueryWrapper<SoilType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        List<SoilType> soilTypes = soilTypeMapper.selectList(queryWrapper);
        Result<List<DropDownVo>> listResult1 = jzbApi.listForSoilType();
        soilTypes.forEach(o->{
            Boolean aBoolean = ApiUtil.existIdFromAgpjzb(listResult1, o.getSoilId());
            if (!aBoolean){
                o.setSoilId("");
            }
        });
        return soilTypes;
    }

    @Override
    public void delete(String surveyId) {
        QueryWrapper<SoilType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        soilTypeMapper.delete(queryWrapper);
    }

    @Override
    public void update(SoilType soilType,String surveyId) {
        delete(surveyId);
        save(soilType,surveyId);
    }


}
