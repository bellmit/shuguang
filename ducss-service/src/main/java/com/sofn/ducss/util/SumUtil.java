package com.sofn.ducss.util;

import com.sofn.common.utils.IdUtil;
import com.sofn.ducss.model.ProductionUsageSum;
import com.sofn.ducss.model.ReturnLeaveSum;
import com.sofn.ducss.model.StrawUsageSum;
import com.sofn.ducss.model.StrawUtilizeSum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/11/5 17:44
 * @Version 1.0
 * 通过旧的汇总获得新的汇总
 */
public class SumUtil {

    //抽取多余结果到新的汇总

    public static ProductionUsageSum getProductionUsageSum(StrawUtilizeSum sm) {
        if (sm == null) {
            return null;
        }
        ProductionUsageSum ps = new ProductionUsageSum();
        ps.setId(IdUtil.getUUId());
        ps.setYear(sm.getYear());
        ps.setAreaId(sm.getAreaId());
        //产生量
        ps.setProduce(sm.getTheoryResource());
        //收集量
        ps.setCollect(sm.getCollectResource());
        //秸秆利用量
        ps.setStrawUsage(sm.getProStrawUtilize());
        ps.setCollectResourceV2(sm.getCollectResourceV2());
        ps.setStrawUtilizationV2(sm.getStrawUtilizationV2());
        //综合利用率:秸秆利用量/可收集量 *100%"
        BigDecimal comprehensive = BigDecimal.ZERO;
        if (ps.getCollectResourceV2().compareTo(BigDecimal.ZERO) > 0) {
            comprehensive = ps.getStrawUtilizationV2().divide(ps.getCollectResourceV2(), 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }
        ps.setComprehensiveRate(comprehensive);
        //合计
        ps.setAllTotal(sm.getMainTotal().add(sm.getDisperseTotal()).add(sm.getProStillField()));
        ps.setFertilizer(sm.getMainFertilising().add(sm.getDisperseFertilising()).add(sm.getProStillField()));
        ps.setFuel(sm.getMainFuel().add(sm.getDisperseFuel()));
        ps.setBasic(sm.getMainBase().add(sm.getDisperseBase()));
        ps.setRawMaterial(sm.getMainMaterial().add(sm.getDisperseMaterial()));
        ps.setFeed(sm.getMainForage().add(sm.getDisperseForage()));
        //综合利用能力指数：市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100
        ps.setComprehensiveIndex(sm.getComprehensiveIndex());
        //产业化利用能力指数：市场主体规模化利用量/可收集量*100
        ps.setIndustrializationIndex(sm.getIndustrializationIndex());
        ps.setMainTotal(sm.getMainTotal());

        return ps;

    }

    /**
     * 旧的汇总获得新的汇总
     *
     * @param sus
     * @return
     */
    public static List<StrawUsageSum> strawUsageSum(List<StrawUtilizeSum> sus) {
        if (sus == null || sus.size() == 0) {
            return null;
        }
        ArrayList<StrawUsageSum> usageList = new ArrayList<>();
        StrawUsageSum usage = null;
        for (StrawUtilizeSum s : sus) {
            usage = new StrawUsageSum();
            usage.setId(IdUtil.getUUId());
            usage.setYear(s.getYear());
            usage.setAreaId(s.getAreaId());
            usage.setStrawType(s.getStrawType());
            usage.setStrawName(s.getStrawName());
            usage.setStrawUsage(s.getProStrawUtilize());
            usage.setComprehensiveRate(s.getComprehensive());
            usage.setAllTotal(s.getMainTotal().add(s.getDisperseTotal()).add(s.getProStillField()));
            usage.setFertilizer(s.getMainFertilising().add(s.getDisperseFertilising()).add(s.getProStillField()));
            usage.setFuel(s.getMainFuel().add(s.getDisperseFuel()));
            usage.setBasic(s.getMainBase().add(s.getDisperseBase()));
            usage.setRawMaterial(s.getMainMaterial().add(s.getDisperseMaterial()));
            usage.setFeed(s.getMainForage().add(s.getDisperseForage()));
            usage.setOther(s.getMainTotalOther());
            usage.setYieldExport(s.getYieldAllExport());
            usage.setComprehensiveIndex(s.getComprehensiveIndex());
            usage.setIndustrializationIndex(s.getIndustrializationIndex());
            usage.setCollectResource(s.getCollectResource());
            usage.setMainTotal(s.getMainTotal());
            usage.setStrawUtilizationV2(s.getStrawUtilizationV2());
            usage.setCollectResourceV2(s.getCollectResourceV2());
            usageList.add(usage);
        }
        return usageList;
    }

    /**
     * 通过秸秆利用汇总获得还田离田汇总
     *
     * @param sus
     * @return
     */
    public static List<ReturnLeaveSum> getReturnLeaveSum(List<StrawUtilizeSum> sus) {
        if (sus == null || sus.size() == 0) {
            return null;
        } else {
            ArrayList<ReturnLeaveSum> returnLeaveSumList = new ArrayList<>();
            ReturnLeaveSum rs = null;
            for (StrawUtilizeSum s : sus) {
                if (s.getCollectResource().compareTo(new BigDecimal(0)) == 0) {
                    s.setCollectResource(new BigDecimal(0));
                }
                rs = new ReturnLeaveSum();
                rs.setCollectResource(s.getCollectResource());
                rs.setId(IdUtil.getUUId());
                rs.setYear(s.getYear());
                rs.setAreaId(s.getAreaId());
                rs.setStrawType(s.getStrawType());
                rs.setProStillField(s.getProStillField());
                // TODO
                rs.setReturnRatio(s.getReturnRatio());
                //还田离田汇总不用加直接直接还田量
                rs.setAllTotal(s.getMainTotal().add(s.getDisperseTotal()));
                rs.setDisperseTotal(s.getDisperseTotal());
                rs.setMainTotal(s.getMainTotal());
                rs.setSeedArea(s.getSeedArea());
                rs.setReturnArea(s.getReturnArea());
                returnLeaveSumList.add(rs);
            }
            return returnLeaveSumList;
        }


    }

}