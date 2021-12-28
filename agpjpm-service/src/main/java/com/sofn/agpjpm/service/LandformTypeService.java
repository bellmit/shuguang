package com.sofn.agpjpm.service;

import com.sofn.agpjpm.model.LandformType;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:53
 */
public interface LandformTypeService {

    void save(LandformType landformType, String surveyId);
    List<LandformType> getBySurveyId(String surveyId);
    void delete(String surveyId);
    void update(LandformType landformType,String surveyId);
}
