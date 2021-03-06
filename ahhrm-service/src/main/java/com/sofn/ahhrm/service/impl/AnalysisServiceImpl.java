package com.sofn.ahhrm.service.impl;

import com.google.common.collect.Lists;
import com.sofn.ahhrm.Api.AhhdpApi;
import com.sofn.ahhrm.mapper.AnalysisMapper;
import com.sofn.ahhrm.model.Baseinfo;
import com.sofn.ahhrm.service.AnalysisService;
import com.sofn.ahhrm.service.BaseinfoService;
import com.sofn.ahhrm.util.ApiUtil;
import com.sofn.ahhrm.vo.*;
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

    @Autowired
    private BaseinfoService baseinfoService;

    @Override
    public List<MonitoringPointVo> getList(Map map) {
        String variety = (String) map.get("variety");
        List<MonitoringPointVo> list = null;
        if (variety == null || "".equals(variety)) {
            list = analysisMapper.getList(map);
        } else {
            list = analysisMapper.getList1(map);
        }
        List<MonitoringPointVo> res = Lists.newArrayListWithCapacity(list.size());
        if (list != null) {
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
        //        ???????????????????????????????????????
        EarlyWarning ew = new EarlyWarning();
        ew.setPointName(pointName);
        List<NewSpeInfo> spe = analysisMapper.getSpe(pointName);
        if (!CollectionUtils.isEmpty(spe)) {
            List<AnalysisVo> as = new ArrayList<>();
//        ?????????????????????
            spe.forEach(o -> {
                //        ??????????????????????????????????????????????????????
                List<TholdResult> tholdResults = analysisMapper.thresholdForSpe(o.getVariety());
//            ????????????????????????????????? ???????????????????????????????????????????????????
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
//        }else {
////            ???????????????????????????????????? ??????
//            l.setColor("ff0000");
        }
        return l;
    }
}
