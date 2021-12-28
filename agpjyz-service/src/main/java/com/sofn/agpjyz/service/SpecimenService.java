package com.sofn.agpjyz.service;

import com.sofn.agpjyz.vo.SourceVo;
import com.sofn.agpjyz.vo.SpecimenForm;
import com.sofn.agpjyz.vo.SpecimenLastVo;
import com.sofn.agpjyz.vo.SpecimenVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 14:30
 */
public interface SpecimenService {
    SpecimenVo save(SpecimenForm specimenForm);
    int del(String id);
    SpecimenVo getOne(String id);
    int update(SpecimenForm specimenForm);
    void exportByTemplate(Map<String, Object> params, HttpServletResponse response);
    PageUtils<SpecimenVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
    /**
     * 审核
     */
    void audit(String ids, String status, String auditOpinion);
    /**
     * 撤回
     */
    void cancel(String id);

    /**
     * 审核通过
     */
    void auditPass(String ids, String auditOpinion);

    /**
     * 审核退回
     */
    void auditReturn(String ids, String auditOpinion);

    /**
     * 上次审核信息
     * @return
     */
    SpecimenLastVo getLast();

    void report(String id);
}
