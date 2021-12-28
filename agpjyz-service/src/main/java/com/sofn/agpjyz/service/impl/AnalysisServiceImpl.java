package com.sofn.agpjyz.service.impl;

import com.sofn.agpjyz.mapper.AnalysisMapper;
import com.sofn.agpjyz.model.TargetSpecies;
import com.sofn.agpjyz.model.ThreatFactor;
import com.sofn.agpjyz.service.AnalysisService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.vo.AgricultureSpeciesVo;
import com.sofn.agpjyz.vo.AnalysisVo;
import com.sofn.agpjyz.vo.TholdDeatilResult;
import com.sofn.agpjyz.vo.TholdResult;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-08-10 14:08
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;
    @Autowired
    private JzbApi jzbApi;

    @Override
    public AnalysisVo getMapResult(Map map) {
        AnalysisVo m=new AnalysisVo();
        String protectId =(String) map.get("protectId");
        String year =(String) map.get("year");
        String specId =(String) map.get("specId");
        String testType =(String) map.get("testType");
        String indexId =(String) map.get("indexId");
//      获取当前查询植物的分布区域
        Result<AgricultureSpeciesVo> agricultureSpeciesVoResult = jzbApi.get(specId);
        if (agricultureSpeciesVoResult.getData()!=null){
            m.setAddrList(agricultureSpeciesVoResult.getData().getAddrList());
        }
//        如果指标参数为 1 即查询目标物种的指标阈值集合
        if ("1".equals(indexId)){
//            目标物种的指标阈值集合
            List<TholdResult> amount = analysisMapper.amount(protectId, "1", testType, specId);
            if (CollectionUtils.isEmpty(amount)){
                throw new SofnException("未找到当前查询条件的阈值，请设置");
            }
            TargetSpecies param = analysisMapper.param(protectId, specId, year);
            if (param==null){
                throw new SofnException("目标物种基础信息未录入基础信息");
            }
            //            得到查询结果，已按风险等级从高到低排序
            List<TholdDeatilResult> tholdDeatilResults = analysisMapper.warningNumber(protectId, year, "1", testType, specId, amount);
            //            如果是满足多条阈值，取出风险等级最大的一个
            if (tholdDeatilResults.size()>0){
                m.setColorMark(tholdDeatilResults.get(0).getColorMark());
            }
            //            不满足任何阈值，则为超出阈值
            if (tholdDeatilResults.size()==0){
                m.setMessage("1");
            }
        }
//        如果指标参数为 2 即查询威胁因素的指标阈值集合
        if ("2".equals(indexId)){
//           威胁因素基础信息的指标阈值集合
            List<TholdResult> pollute = analysisMapper.pollute(protectId, "2", testType);
            if (CollectionUtils.isEmpty(pollute)){
                throw new SofnException("未找到当前查询条件的阈值，请设置");
            }
            ThreatFactor threatFactor = analysisMapper.param1(protectId, year);
            if (threatFactor==null){
                throw new SofnException(" 威胁因素基础信息未录入当前年份下保护点的基础数据");
            }
            List<TholdDeatilResult> warning = analysisMapper.warning(protectId, year, "2", testType, pollute);
            //            如果是满足多条阈值，取出风险等级最大的一个
            if (warning.size()>0){
                m.setColorMark(warning.get(0).getColorMark());
            }
            //            不满足任何阈值，则为超出阈值
            if (warning.size()==0){
                m.setMessage("1");
//                  产品要求超出阈值显示红色 写死        超出阈值显示红色
                m.setColorMark("ff0000");
            }
        }
        return m;
    }
}
