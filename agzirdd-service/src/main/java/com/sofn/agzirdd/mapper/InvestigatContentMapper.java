package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.InvestigatContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-调查内容mapper
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
@Mapper
public interface InvestigatContentMapper extends BaseMapper<InvestigatContent> {

    /**
     * 获取满足条件的调查内容数据
     * @param params params
     * @return List
     */
    List<InvestigatContent> getInvestigatContentByQuery(Map<String,Object> params);

    /**
     * speciesInvestigationId-调查内容
     * @param speciesInvestigationId speciesInvestigationId
     * @return InvestigatContent
     */
    InvestigatContent getInvestigatContentBySpeciesInvestigationId(@Param(value="speciesInvestigationId")String speciesInvestigationId);

    /**
     * speciesInvestigationId-调查内容
     * @param speciesInvestigationId speciesInvestigationId
     * @return true or false
     */
    boolean deleteInvestigatContent(String speciesInvestigationId);
}