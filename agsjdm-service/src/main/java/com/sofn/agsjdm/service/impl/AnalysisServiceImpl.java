package com.sofn.agsjdm.service.impl;

import com.sofn.agsjdm.mapper.AnalysisMapper;
import com.sofn.agsjdm.model.Biomonitoring;
import com.sofn.agsjdm.model.ThreatFactor;
import com.sofn.agsjdm.service.*;
import com.sofn.agsjdm.vo.*;
import com.sofn.common.exception.SofnException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 15:10
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private InformationManagementService informationManagementService;
    @Resource
    private AnalysisMapper analysisMapper;


    @Override
    public AnalysisVo getMapResult(Map map) {
        AnalysisVo analysisVo = new AnalysisVo();

        InformationManagementFrom from = informationManagementService.searchByid(map.get("wetlandId").toString());
        String areaCode = from.getAreaCode();
        String cityCode = from.getCityCode();
        String provinceCode = from.getProvinceCode();
        if (StringUtils.isEmpty(areaCode) && StringUtils.isEmpty(cityCode) && StringUtils.isEmpty(provinceCode)) {
            throw new SofnException("当前湿地区未设置区域信息");
        }
        AddrVo addrVo = new AddrVo();
        addrVo.setAreaCode(areaCode);
        addrVo.setCityCode(cityCode);
        addrVo.setProvinceCode(provinceCode);
        addrVo.setRegionInCh(from.getRegionInCh());
        analysisVo.setAddrVo(addrVo);
        if ("1".equals(map.get("testType"))) {
            String year = (String) map.get("year");
            String testType = (String) map.get("testType");
            String indexId = (String) map.get("indexId");
            String wetlandId1 = (String) map.get("wetlandId");
            String chineseName = (String) map.get("chineseName");
//            查询当前条件下 的阈值是否存在
            List<TholdResult> tholdResults = analysisMapper.pollute(map);
            if (CollectionUtils.isEmpty(tholdResults)) {
                throw new SofnException("未设置当前查询条件的阀值");
            }
//            查询当前条件下是否有在生物监测信息采集 录入基础数据
            Biomonitoring biomonitoring = analysisMapper.param(wetlandId1, year, chineseName);
            if (biomonitoring == null) {
                throw new SofnException("生物监测信息采集未录入当前湿地区的信息");
            }
            for(TholdResult tr : tholdResults){
                Integer populationSize = biomonitoring.getPopulationSize();
                if(populationSize>= tr.getCase1Value() &&  populationSize<= tr.getCase2Value()){
                    analysisVo.setColorMark(tr.getColorMark());
                    analysisVo.setMessage("1");
                    break;
                }
            }

        } else {
            String year = (String) map.get("year");
            String testType = (String) map.get("testType");
            String indexId = (String) map.get("indexId");
            String wetlandId1 = (String) map.get("wetlandId");
            //            查询当前条件下 的阈值是否存在
            List<TholdResult> tholdResults = analysisMapper.pollute(map);
            if (CollectionUtils.isEmpty(tholdResults)) {
                throw new SofnException("未设置当前查询条件的阀值");
            }
            //            查询当前条件下是否有在威胁因素 录入基础数据
            ThreatFactor threatFactor = analysisMapper.param1(wetlandId1, year);
            if (threatFactor == null) {
                throw new SofnException("威胁因素基础信息未录入当前湿地区的信息");
            }
            for(TholdResult tr : tholdResults){
                Double pollute = threatFactor.getPollute();
                if(pollute>= tr.getCase1Value() &&  pollute<= tr.getCase2Value()){
                    analysisVo.setColorMark(tr.getColorMark());
                    analysisVo.setMessage("1");
                    break;
                }
            }

        }
        return analysisVo;
    }
}
