package com.sofn.agpjpm.service;

import com.sofn.agpjpm.vo.MonitorVo;
import com.sofn.agpjpm.vo.ServeyListVo;
import com.sofn.agpjpm.vo.SurveyForm;
import com.sofn.agpjpm.vo.SurveyVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:50
 */
public interface SurveyService {
    void save(SurveyForm sur);
    SurveyVo get(String id);
    void update(SurveyForm sur);
    void del(String id);

    /**
     * 分页查询
     */
    PageUtils<ServeyListVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
    /**
     * 根据模板导出
     */
    void exportByTemplate(Map<String, Object> params, HttpServletResponse response);
}
