package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjpm.mapper.HabitatTypeMapper;
import com.sofn.agpjpm.mapper.SoilTypeMapper;
import com.sofn.agpjpm.model.HabitatType;
import com.sofn.agpjpm.model.SoilType;
import com.sofn.agpjpm.service.HabitatTypeService;
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
 * @Date: 2020-06-10 9:55
 */
@Service("hService")
public class HabitatTypeServiceImpl implements HabitatTypeService {
    @Autowired
    private HabitatTypeMapper habitatTypeMapper;
    @Autowired
    private JzbApi jzbApi;
    @Override
    public void save(HabitatType habitatType, String surveyId) {
        if (!StringUtils.isEmpty(habitatType.getHabitatId())){
            habitatType.setId(IdUtil.getUUId());
            habitatType.setSurveyId(surveyId);
            habitatTypeMapper.insert(habitatType);
        }
    }

    @Override
    public List<HabitatType> getBySurveyId(String surveyId) {
        QueryWrapper<HabitatType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        List<HabitatType> HabitatTypes = habitatTypeMapper.selectList(queryWrapper);
        Result<List<DropDownVo>> listResult1 = jzbApi.listForHabitatType();
        HabitatTypes.forEach(o->{
            Boolean aBoolean = ApiUtil.existIdFromAgpjzb(listResult1, o.getHabitatId());
            if (!aBoolean) {
                o.setHabitatId("");
            }
        });
        return HabitatTypes;
    }

    @Override
    public void delete(String surveyId) {
        QueryWrapper<HabitatType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        habitatTypeMapper.delete(queryWrapper);
    }

    @Override
    public void update(HabitatType habitatType,String surveyId) {
        delete(surveyId);
        save(habitatType,surveyId);
    }
}
