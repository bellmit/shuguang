package com.sofn.ahhrm.service.impl;

import com.sofn.ahhrm.mapper.AnalysieMapper;
import com.sofn.ahhrm.model.Baseinfo;
import com.sofn.ahhrm.service.AnalysieService;
import com.sofn.ahhrm.util.RedisUserUtil;
import com.sofn.ahhrm.vo.ChartVo;
import com.sofn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "analysieService")
public class AnalysieServiceImpl implements AnalysieService {

    @Autowired
    private AnalysieMapper analysieMapper;

    @Override
    public List<Baseinfo> listBaseinfo(String pointName, String year, String index, String variety) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("pointName", pointName);
        map.put("year", year);
        map.put("variety", variety);
        RedisUserUtil.perfectParams(map);
        return analysieMapper.listByParams(map).stream().
                sorted(Comparator.comparing(Baseinfo::getMonitoringTime).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<ChartVo> getEffective(String pointName, String year, String index, String variety) {
        List<Baseinfo> baseinfos = this.listBaseinfo(pointName, year, index, variety);
        List<ChartVo> chartVos = ChartVo.getAllMonthEffectiveNullData();
        Integer currentMonth = 0;
        for (int i = 0; i < baseinfos.size(); i++) {
            Baseinfo baseinfo = baseinfos.get(i);
            String monthStr = DateUtils.format(baseinfo.getMonitoringTime(), "MM");
            int month = Integer.parseInt(monthStr);
            if (currentMonth != month) {
                ChartVo vo = chartVos.get(month - 1);
                vo.setEffectiveGroup(baseinfo.getEffectiveGroup());
                currentMonth = month;
            }
        }
        return chartVos;
    }

    @Override
    public List<ChartVo> getRatio(String pointName, String year, String index, String variety) {
        List<Baseinfo> baseinfos = this.listBaseinfo(pointName, year, index, variety);
        List<ChartVo> chartVos = ChartVo.getAllMonthRatioNullData();
        Integer currentMonth = 0;
        for (int i = 0; i < baseinfos.size(); i++) {
            Baseinfo baseinfo = baseinfos.get(i);
            String monthStr = DateUtils.format(baseinfo.getMonitoringTime(), "MM");
            int month = Integer.parseInt(monthStr);
            if (currentMonth != month) {
                String proportion = baseinfo.getProportion();
                if (StringUtils.hasText(proportion)) {
                    String[] ratio = proportion.split(":");
                    ChartVo vo1 = chartVos.get(month - 1);
                    ChartVo vo2 = chartVos.get(month - 1 + 12);
                    vo1.setRatio(Integer.parseInt(ratio[0]));
                    vo2.setRatio(Integer.parseInt(ratio[1]));
                }
                currentMonth = month;
            }
        }
        return chartVos;
    }
}
