package com.sofn.dhhrp.service.impl;


import com.google.common.collect.Lists;
import com.sofn.dhhrp.Api.AhhdpApi;
import com.sofn.dhhrp.mapper.AnalysisMapper;
import com.sofn.dhhrp.model.Baseinfo;
import com.sofn.dhhrp.service.AnalysisService;
import com.sofn.dhhrp.util.ApiUtil;
import com.sofn.dhhrp.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 14:08
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;
    @Autowired
    private AhhdpApi ahhdpApi;


    @Override
    public List<MonitoringPointVo> getList1(Map map) {
        List<MonitoringPointVo> list = analysisMapper.getList1(map);
        List<MonitoringPointVo> res = Lists.newArrayListWithCapacity(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (MonitoringPointVo l : list) {
                Baseinfo baseinfoVo = analysisMapper.getById(l.getId());
                l.setRegionInCh(baseinfoVo.getProvinceName() + "-" + baseinfoVo.getCityName() + "-" + baseinfoVo.getCountyName());
                pointColor(l);
                if (StringUtils.hasText(l.getColor())) {
                    res.add(l);
                }
            }
        }

        return res;

    }


    @Override
    public EarlyWarning getMapResult(String pointName) {
        Map<String, String> varietyNameMap = ApiUtil.getResultMap(ahhdpApi.listForAllName());
        //        取出当前保护点下的所有物种
        EarlyWarning ew = new EarlyWarning();
        ew.setPointName(pointName);
        List<NewSpeInfo> spe = analysisMapper.getSpe(pointName);
        if (!CollectionUtils.isEmpty(spe)) {
            List<AnalysisVo> as = new ArrayList<>();
//        遍历每一个物种
            spe.forEach(o -> {
                //        根据具体的物种得到对应物种设置的阈值
                List<TholdResult> tholdResults = analysisMapper.thresholdForSpe(o.getVariety());
//            得到单个物种的阈值结果 按风险等级排序，取出最大的一条记录
                if (!CollectionUtils.isEmpty(tholdResults)) {
                    List<AnalysisVo> param = analysisMapper.param(o.getVariety(), pointName, tholdResults);
                    if (param.size() > 0) {
                        as.add(param.get(0));
                    }
                }
            });
            as.forEach(o -> {
                o.setVariety(varietyNameMap.get(o.getVariety()));
            });
            ew.setAnalysis(as);
        }

        return ew;
    }


    private MonitoringPointVo pointColor(MonitoringPointVo l) {
        EarlyWarning mapResult = getMapResult(l.getPointName());
        if (!CollectionUtils.isEmpty(mapResult.getAnalysis())) {
            List<AnalysisVo> analysis = mapResult.getAnalysis();
            List<Color> colors = new ArrayList();
            analysis.forEach(o -> {
                Color color = new Color();
                BeanUtils.copyProperties(o, color);
                color.setOrder(Integer.parseInt(o.getGrade()));
                colors.add(color);
            });
            List<Color> collect = colors.stream().sorted(Comparator.comparing(Color::getOrder).reversed()).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(collect)) {
                l.setColor(collect.get(0).getColor());
            }
//        } else {
////            产品要求超出阈值显示红色 写死
//            l.setColor("ff0000");
        }

        return l;
    }
}
