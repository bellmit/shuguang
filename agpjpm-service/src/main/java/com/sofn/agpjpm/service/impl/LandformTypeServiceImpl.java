package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjpm.mapper.LandformTypeMapper;
import com.sofn.agpjpm.model.LandformType;
import com.sofn.agpjpm.model.LandformType;
import com.sofn.agpjpm.service.LandformTypeService;
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
 * @Date: 2020-06-10 9:53
 */
@Service("lService")
public class LandformTypeServiceImpl  implements LandformTypeService {
    @Autowired
    private LandformTypeMapper landformTypeMapper;
    @Autowired
    private JzbApi jzbApi;
    @Override
    public void save(LandformType landformType, String surveyId) {
        if (!StringUtils.isEmpty(landformType.getLandformId())){

            landformType.setId(IdUtil.getUUId());
            landformType.setSurveyId(surveyId);
            landformTypeMapper.insert(landformType);
        }

    }

    @Override
    public List<LandformType> getBySurveyId(String surveyId) {
        QueryWrapper<LandformType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        List<LandformType> landformTypes = landformTypeMapper.selectList(queryWrapper);
        Result<List<DropDownVo>> listResult1 = jzbApi.listForTopography();
        landformTypes.forEach(o->{
            Boolean aBoolean = ApiUtil.existIdFromAgpjzb(listResult1, o.getLandformId());
            if (!aBoolean) {
                o.setLandformId("");
            }
        });

        return landformTypes;
    }

    @Override
    public void delete(String surveyId) {
        QueryWrapper<LandformType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("survey_id", surveyId);
        landformTypeMapper.delete(queryWrapper);
    }

    @Override
    public void update(LandformType landformType,String surveyId) {
        delete(surveyId);
        save(landformType,surveyId);
    }
}
