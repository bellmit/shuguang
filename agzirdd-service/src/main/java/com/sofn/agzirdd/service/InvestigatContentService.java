package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.InvestigatContent;
import com.sofn.agzirdd.vo.InvestigatContentVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-调查内容
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
public interface InvestigatContentService extends IService<InvestigatContent> {


    /**
     * 获取满足条件的调查内容数据
     * @param params params
     * @return List
     */
    List<InvestigatContent> getInvestigatContentByQuery(Map<String,Object> params);

    /**
     * 获取指定speciesInvestigationId物种调查模块-调查内容
     * @param speciesInvestigationId speciesInvestigationId
     * @return InvestigatContent
     */
    InvestigatContent getInvestigatContentBySpeciesInvestigationId(String speciesInvestigationId);

    /**
     * 获取指定speciesInvestigationId物种调查模块-调查内容
     * @param speciesInvestigationId speciesInvestigationId
     * @return InvestigatContentVo
     */
    InvestigatContentVo getInvestigatContentVo(String speciesInvestigationId);

    /**
     * 新增物种调查模块-调查内容
     * @param investigatContent investigatContent
     */
    void addInvestigatContent(InvestigatContent investigatContent);

    /**
     * 修改物种调查模块-调查内容
     * @param investigatContent investigatContent
     */
    void updateInvestigatContent(InvestigatContent investigatContent);

    /**
     * 删除指定speciesInvestigationId物种调查模块-调查内容
     * @param speciesInvestigationId speciesInvestigationId
     * @return true or false
     */
    boolean removeInvestigatContent(String speciesInvestigationId);

}
