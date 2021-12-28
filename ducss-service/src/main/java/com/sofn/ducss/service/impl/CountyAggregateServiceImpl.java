package com.sofn.ducss.service.impl;


import com.sofn.ducss.mapper.DisperseUtilizeDetailMapper;
import com.sofn.ducss.mapper.ProStillDetailMapper;
import com.sofn.ducss.mapper.StrawUtilizeDetailMapper;
import com.sofn.ducss.model.DisperseUtilizeDetail;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.StrawUtilizeDetail;
import com.sofn.ducss.service.CountyAggregateService;
import com.sofn.ducss.util.StrawCalculatorUtil2;
import com.sofn.ducss.vo.StrawUtilizeResVo3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountyAggregateServiceImpl implements CountyAggregateService {

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

    @Override
    public List<StrawUtilizeResVo3> getCountyStrawUtilize2(String areaId, String year) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList<>();   //要返回的数据
        //获取 产生量与直接还田量数据
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        if (psdList.isEmpty())  //县级未填写产生量数据
            return utilizes;
        //获取 分散利用量(农户分散比例已经计算)
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);
        //获取 市场主体利用量
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);
        if (dudList.isEmpty() && sudList.isEmpty())  //县级未填写利用量数据
            return utilizes;

        //把秸秆分散利用以秸秆类型组装到map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap<>();
        for (DisperseUtilizeDetail detail : dudList) {
            dudMap.put(detail.getStrawType(), detail);
        }
        //把秸秆利用以秸秆类型组装到map
        Map<String, StrawUtilizeDetail> sudMap = new HashMap<>();
        for (StrawUtilizeDetail detail : sudList) {
            sudMap.put(detail.getStrawType(), detail);
        }
        StrawUtilizeResVo3 temp = null;
        for (ProStillDetail psd : psdList) {
            //组装利用量汇总数据
            temp = StrawCalculatorUtil2.assemblyUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
            utilizes.add(temp);
        }
        return utilizes;
    }
}
