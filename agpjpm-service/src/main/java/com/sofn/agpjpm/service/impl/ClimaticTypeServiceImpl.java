package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjpm.mapper.ClimaticTypeMapper;
import com.sofn.agpjpm.model.ClimaticType;
import com.sofn.agpjpm.model.SoilType;
import com.sofn.agpjpm.service.ClimaticTypeService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.util.ApiUtil;
import com.sofn.agpjpm.vo.DropDownVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:56
 */
@Service("climaticService")
public class ClimaticTypeServiceImpl implements ClimaticTypeService {
    @Autowired
    private ClimaticTypeMapper climaticTypeMapper;
    @Autowired
    private JzbApi jzbApi;
    @Override
    public void save(ClimaticType cimaticType, String surveyId) {
        if (!StringUtils.isEmpty(cimaticType.getClimaticId())){
            cimaticType.setId(IdUtil.getUUId());
            cimaticType.setSurveyId(surveyId);
            climaticTypeMapper.insert(cimaticType);
        }
    }

    @Override
    public List<ClimaticType> getBySurveyId(String surveyId) {
        QueryWrapper<ClimaticType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        List<ClimaticType> cimaticTypes = climaticTypeMapper.selectList(queryWrapper);
        Result<List<DropDownVo>> listResult1 = jzbApi.listForClimateType();
        cimaticTypes.forEach(o->{
            Boolean aBoolean = ApiUtil.existIdFromAgpjzb(listResult1, o.getClimaticId());
            if (!aBoolean) {
                o.setClimaticId("");
            }
            });
        return cimaticTypes;
    }

    @Override
    public void delete(String surveyId) {
        QueryWrapper<ClimaticType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        climaticTypeMapper.delete(queryWrapper);
    }

    @Override
    public void update(ClimaticType cimaticType,String surveyId) {
        delete(surveyId);
        save(cimaticType,surveyId);
    }
}
