package com.sofn.ducss.service.impl;

import com.deepoove.poi.data.PictureRenderData;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.mapper.ReportCollectionResourceValueMapper;
import com.sofn.ducss.model.wordmodel.ReportUtilizeInfo;
import com.sofn.ducss.model.wordmodel.ReportWordProduct;
import com.sofn.ducss.service.ReportCollectionResourceValueService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2021/1/4 14:33
 */
@Service
@Slf4j
public class ReportCollectionResourceValueServiceImpl implements ReportCollectionResourceValueService {


    @Autowired
    private ReportCollectionResourceValueMapper reportCollectionResourceValueMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    //????????????????????????
    //private static final String templateFolder = getClassLoader().getResource("static/").getPath();
    private static File file = null;


    @Autowired
    private FormCallbackConfig formCallbackConfig;

    @Override
    public Map<String, Object> getReturnInfo(String year) {
        Map<String, Object> returnMaps = Maps.newHashMap();
        // ????????????????????????
        int yearNum = Integer.parseInt(year);
        String lastYear = --yearNum + "";
        String regionYear = syncSysRegionService.getYearByYear(year);
        Map<String, Object> returnInfoGroupByChina = reportCollectionResourceValueMapper.getReturnInfoGroupByChina(year, regionYear);
        if (!CollectionUtils.isEmpty(returnInfoGroupByChina)) {
            returnMaps.put("param_142", BigDecimalUtil.get100MillionTonsAndScale2((BigDecimal) returnInfoGroupByChina.get("number1")));
            returnMaps.put("param_143", BigDecimalUtil.getProportion(returnInfoGroupByChina.get("number1"), returnInfoGroupByChina.get("number4"), 1));
            returnMaps.put("param_144", BigDecimalUtil.get100MillionTonsAndScale2((BigDecimal) returnInfoGroupByChina.get("number3")));
            BigDecimal disperseRate = BigDecimalUtil.setScale2((BigDecimal) returnInfoGroupByChina.get("number6")).setScale(1, BigDecimal.ROUND_HALF_UP);
            returnMaps.put("param_145", BigDecimalUtil.setScale2((BigDecimal) returnInfoGroupByChina.get("number6")).setScale(1, BigDecimal.ROUND_HALF_UP));
            returnMaps.put("param_148", BigDecimalUtil.getTenThousand((BigDecimal) returnInfoGroupByChina.get("number2"), 2));
            BigDecimal mainRate = ((BigDecimal) returnInfoGroupByChina.get("number5")).setScale(1, BigDecimal.ROUND_HALF_UP);
            returnMaps.put("param_149", mainRate);
            Map<String, Object> oldReturnInfoGroupByChina = reportCollectionResourceValueMapper.getReturnInfoGroupByChina(lastYear, regionYear);
            if (!CollectionUtils.isEmpty(oldReturnInfoGroupByChina)) {
                BigDecimal oldDisperseRate = BigDecimalUtil.setScale2((BigDecimal) oldReturnInfoGroupByChina.get("number6")).setScale(1, BigDecimal.ROUND_HALF_UP);
                String upOrDown = getUpOrDown(disperseRate, oldDisperseRate);
                returnMaps.put("param_147", upOrDown);
                BigDecimal oldMainRate = ((BigDecimal) oldReturnInfoGroupByChina.get("number5")).setScale(1, BigDecimal.ROUND_HALF_UP);
                upOrDown = getUpOrDown(mainRate, oldMainRate);
                returnMaps.put("param_151", upOrDown);
            }
        }


        //  ??????????????????
        List<ReportUtilizeInfo> returnInfoGroupByProvince = reportCollectionResourceValueMapper.getReturnInfoGroupByProvince(year, regionYear);
        if (!CollectionUtils.isEmpty(returnInfoGroupByProvince)) {
            // ??????
            ReportUtilizeInfo reportUtilizeInfo = returnInfoGroupByProvince.get(0);
            returnMaps.put("param_152", reportUtilizeInfo.getAreaName());
            returnMaps.put("param_153", reportUtilizeInfo.getNumber1().setScale(1, BigDecimal.ROUND_HALF_UP) + "%");
            // ??????
            ReportUtilizeInfo reportUtilizeInfo1 = returnInfoGroupByProvince.get(1);
            returnMaps.put("param_154", reportUtilizeInfo1.getAreaName());
            returnMaps.put("param_155", reportUtilizeInfo1.getNumber1().setScale(1, BigDecimal.ROUND_HALF_UP));
            // 45%~60%
            //30%~45%
            List<String> areaName1 = Lists.newArrayList();
            List<String> areaName2 = Lists.newArrayList();
            BigDecimal disperseCount = new BigDecimal(0);
            BigDecimal collectResourceCount = new BigDecimal(0);

            for (ReportUtilizeInfo utilizeInfo : returnInfoGroupByProvince) {
                disperseCount = disperseCount.add(utilizeInfo.getNumber2());
                collectResourceCount = collectResourceCount.add(utilizeInfo.getNumber3());
                if (new BigDecimal("60").compareTo(utilizeInfo.getNumber1()) > 0 &&
                        new BigDecimal("45").compareTo(utilizeInfo.getNumber1()) < 0) {
                    areaName1.add(utilizeInfo.getAreaName());
                } else if (new BigDecimal("45").compareTo(utilizeInfo.getNumber1()) > 0 &&
                        new BigDecimal("30").compareTo(utilizeInfo.getNumber1()) < 0) {
                    areaName2.add(utilizeInfo.getAreaName());
                }
            }
            returnMaps.put("param_156", Joiner.on("???").join(areaName1));
            returnMaps.put("param_157", areaName1.size());
            returnMaps.put("param_158", Joiner.on("???").join(areaName2));
            returnMaps.put("param_159", areaName2.size());
            BigDecimal avg = new BigDecimal(0);
            if (collectResourceCount.compareTo(BigDecimal.ZERO) > 0) {
                // ?????????
                avg = collectResourceCount.divide(new BigDecimal(returnInfoGroupByProvince.size()), 1, BigDecimal.ROUND_HALF_UP);
            }
            // ?????????????????????
            List<String> areaName3 = Lists.newArrayList();
            for (ReportUtilizeInfo utilizeInfo : returnInfoGroupByProvince) {
                if (avg.compareTo(utilizeInfo.getNumber1()) < 0) {
                    areaName3.add(utilizeInfo.getAreaName());
                }
            }

            returnMaps.put("param_160", avg.setScale(1, BigDecimal.ROUND_HALF_UP) + "%");
            returnMaps.put("param_1000", Joiner.on("???").join(areaName3));
            // ?????????????????????
            getReturnSixRegionValue(returnInfoGroupByProvince, avg, returnMaps);
            getReturnStraw(year, regionYear, returnMaps);

        }
        return returnMaps;
    }

    @Override
    public Map<String, Object> getFiveMaterials(String year) {
        Map<String,Object> returnMaps = Maps.newHashMap();
        int yearNum = Integer.parseInt(year);
        String oldYear = --yearNum + "";
        String regionYear = syncSysRegionService.getYearByYear(year);
        Map<String, BigDecimal> fiveMaterials = reportCollectionResourceValueMapper.getFiveMaterials(year, regionYear);
        if(!CollectionUtils.isEmpty(fiveMaterials)){

            // ?????????
            BigDecimal num1 = fiveMaterials.get("fertilisingc").setScale(1,BigDecimal.ROUND_HALF_UP);
            // ?????????
            BigDecimal num2 =  fiveMaterials.get("foragec").setScale(1,BigDecimal.ROUND_HALF_UP);
            // ?????????
            BigDecimal num3 = fiveMaterials.get("fuelc").setScale(1,BigDecimal.ROUND_HALF_UP);
            // ?????????
            BigDecimal num4 = fiveMaterials.get("basec").setScale(1,BigDecimal.ROUND_HALF_UP);
            // ?????????
            BigDecimal num5 = fiveMaterials.get("materialc").setScale(1,BigDecimal.ROUND_HALF_UP);

            returnMaps.put("wuliaohuaPer", num1 + "%???" + num2 +  "%???" +num3 + "%???" +num4 + "%???" + num5 + "%" );
            // ????????????
            // ????????????

            Map<String, BigDecimal> oldFiveMaterials = reportCollectionResourceValueMapper.getFiveMaterials(oldYear, regionYear);
            if(!CollectionUtils.isEmpty(oldFiveMaterials)){
                // ?????????
                BigDecimal num11 = oldFiveMaterials.get("fertilisingc").setScale(1,BigDecimal.ROUND_HALF_UP);
                // ?????????
                BigDecimal num21 =  oldFiveMaterials.get("foragec").setScale(1,BigDecimal.ROUND_HALF_UP);
                // ?????????
                BigDecimal num31 = oldFiveMaterials.get("fuelc").setScale(1,BigDecimal.ROUND_HALF_UP);
                // ?????????
                BigDecimal num41 = oldFiveMaterials.get("basec").setScale(1,BigDecimal.ROUND_HALF_UP);
                // ?????????
                BigDecimal num51 = oldFiveMaterials.get("materialc").setScale(1,BigDecimal.ROUND_HALF_UP);

                // ???????????????????????????

                String upOrDown1 = getUpOrDown(num1,num11);
                String upOrDown2 = getUpOrDown(num2,num21);
                String upOrDown3 = getUpOrDown(num3,num31);
                String upOrDown4 = getUpOrDown(num4,num41);
                String upOrDown5 = getUpOrDown(num5,num51);
                List<String> material1 = Lists.newArrayList();
                List<String> material2 = Lists.newArrayList();

                List<String> material1Number = Lists.newArrayList();
                List<String> material2Number = Lists.newArrayList();
                // ??????  ??????
                String top1ChangeWuliao = "?????????";
                String otherChangeWuliao = "?????????";

                if(upOrDown1.contains(top1ChangeWuliao)){
                    material1.add("?????????");
                    material1Number.add(upOrDown1.replace(top1ChangeWuliao,"") + "%");
                }else if(upOrDown1.contains(otherChangeWuliao)){
                    material2.add("?????????");
                    material2Number.add(upOrDown1.replace(otherChangeWuliao, "")+ "%");
                }

                if(upOrDown2.contains(top1ChangeWuliao)){
                    material1.add("?????????");
                    material1Number.add(upOrDown2.replace(top1ChangeWuliao,"")+ "%");
                }else if(upOrDown2.contains(otherChangeWuliao)){
                    material2.add("?????????");
                    material2Number.add(upOrDown2.replace(otherChangeWuliao, "")+ "%");
                }

                if(upOrDown3.contains(top1ChangeWuliao)){
                    material1.add("?????????");
                    material1Number.add(upOrDown3.replace(top1ChangeWuliao,"")+ "%");
                }else if(upOrDown3.contains(otherChangeWuliao)){
                    material2.add("?????????");
                    material2Number.add(upOrDown3.replace(otherChangeWuliao, "")+ "%");
                }

                if(upOrDown4.contains(top1ChangeWuliao)){
                    material1.add("?????????");
                    material1Number.add(upOrDown4.replace(top1ChangeWuliao,"")+ "%");
                }else if(upOrDown4.contains(otherChangeWuliao)){
                    material2.add("?????????");
                    material2Number.add(upOrDown4.replace(otherChangeWuliao, "")+ "%");
                }
                if(upOrDown5.contains(top1ChangeWuliao)){
                    material1.add("?????????");
                    material1Number.add(upOrDown5.replace(top1ChangeWuliao,"")+ "%");
                }else if(upOrDown5.contains(otherChangeWuliao)){
                    material2.add("?????????");
                    material2Number.add(upOrDown5.replace(otherChangeWuliao, "")+ "%");
                }
                returnMaps.put("top1ChangeWuliao", Joiner.on("???").join(material1) );
                returnMaps.put("top1WuliaoChange",  "?????????" + Joiner.on("???").join(material1Number) );
                returnMaps.put("otherChangeWuliao",Joiner.on("???").join(material2)  );
                returnMaps.put("otherWuliaoChange", "?????????" + Joiner.on("???").join(material2Number)  );
            }
            // ??????
            // ????????????
            Map<String, Double> realData = Maps.newLinkedHashMap();
            realData.put("?????????",num1.doubleValue());
            realData.put("?????????",num2.doubleValue());
            realData.put("?????????",num3.doubleValue());
            realData.put("?????????",num4.doubleValue());
            realData.put("?????????",num5.doubleValue());
            byte[] bytes = pieChart("", realData, 500, 400);
            returnMaps.put("temp1111", new PictureRenderData(500, 400, ".png", bytes));
        }
        return returnMaps;
    }

    /**
     * ????????????????????????
     *
     * @param year       ??????
     * @param regionYear ????????????
     * @param returnMaps Map<String,Object>
     */
    private void getReturnStraw(String year, String regionYear, Map<String, Object> returnMaps) {
        List<ReportUtilizeInfo> utilizeRateInfoGroupStraw = reportCollectionResourceValueMapper.getUtilizeRateInfoGroupStraw(year, regionYear);
        if (!CollectionUtils.isEmpty(utilizeRateInfoGroupStraw)) {
            // ??????40% ?????????
            List<String> strawNames = Lists.newArrayList();
            // 30%~40%
            List<String> strawNames2 = Lists.newArrayList();
            // ??????15%
            List<String> strawNames3 = Lists.newArrayList();
            for (ReportUtilizeInfo reportUtilizeInfo : utilizeRateInfoGroupStraw) {
                if(new BigDecimal(40).compareTo(reportUtilizeInfo.getNumber1()) < 0){
                    strawNames.add(reportUtilizeInfo.getStrawName());
                }else if(new BigDecimal(30).compareTo(reportUtilizeInfo.getNumber1()) >  0){
                    strawNames2.add(reportUtilizeInfo.getStrawName());
                }else if(new BigDecimal(15).compareTo(reportUtilizeInfo.getNumber1()) > 0){
                    strawNames3.add(reportUtilizeInfo.getStrawName());
                }
            }
            returnMaps.put("param_172", Joiner.on("???").join(strawNames));

            returnMaps.put("param_174", Joiner.on("???").join(strawNames2));
            returnMaps.put("param_175", Joiner.on("???").join(strawNames3));

            ReportUtilizeInfo reportUtilizeInfo = utilizeRateInfoGroupStraw.get(utilizeRateInfoGroupStraw.size() - 1);

            returnMaps.put("param_176", reportUtilizeInfo.getStrawName());
            returnMaps.put("param_177", reportUtilizeInfo.getNumber1().setScale(1, BigDecimal.ROUND_HALF_UP)
                    + "%");
            // {{@param_1651}}
            // ??????
            // ????????????
            Map<String, Map<String, Double>> datas = Maps.newHashMap();
            Map<String, Double> realData = Maps.newLinkedHashMap();
            for (ReportUtilizeInfo utilizeInfo : utilizeRateInfoGroupStraw) {
                realData.put(utilizeInfo.getStrawName(), utilizeInfo.getNumber1().doubleValue());
            }
            datas.put("", realData);
            byte[] pictureFive = barChart("????????????????????????",
                    datas, "", "%", 500, 400);
            returnMaps.put("param_1651", new PictureRenderData(500, 400, ".png", pictureFive));

        }


    }

    /**
     * ????????????????????????
     *
     * @param returnInfoGroupByProvince ????????????
     * @param avg                       ?????????
     * @param returnMaps                Map<String, Object>
     */
    private void getReturnSixRegionValue(List<ReportUtilizeInfo> returnInfoGroupByProvince, BigDecimal avg, Map<String, Object> returnMaps) {
        if (!CollectionUtils.isEmpty(returnInfoGroupByProvince)) {
            // ?????????????????????
            List<ReportUtilizeInfo> sixRegionValue = Lists.newArrayList();
            for (SixRegionEnum value : SixRegionEnum.values()) {
                ReportUtilizeInfo reportUtilizeInfo = new ReportUtilizeInfo();
                reportUtilizeInfo.setAreaName(value.getDescription());
                BigDecimal disperseCount = new BigDecimal(0);
                BigDecimal collectResourceCount = new BigDecimal(0);
                for (ReportUtilizeInfo reportUtilizeInfo2 : returnInfoGroupByProvince) {
                    disperseCount = disperseCount.add(reportUtilizeInfo2.getNumber2());
                    collectResourceCount = collectResourceCount.add(reportUtilizeInfo2.getNumber3());
                }
                reportUtilizeInfo.setNumber2(disperseCount);
                reportUtilizeInfo.setNumber3(collectResourceCount);
                if (collectResourceCount.compareTo(BigDecimal.ZERO) == 0) {
                    reportUtilizeInfo.setNumber1(BigDecimal.ZERO);
                } else {
                    reportUtilizeInfo.setNumber1(BigDecimalUtil.getProportion(disperseCount, collectResourceCount, 1));
                }
                sixRegionValue.add(reportUtilizeInfo);
            }
            sixRegionValue.sort((o1, o2) -> o2.getNumber1().compareTo(o1.getNumber1()));
            ReportUtilizeInfo reportUtilizeInfo = sixRegionValue.get(0);
            returnMaps.put("param_161", reportUtilizeInfo.getAreaName());
            returnMaps.put("param_162", reportUtilizeInfo.getNumber1());
            List<ReportUtilizeInfo> reportUtilizeInfoList = Lists.newArrayList(sixRegionValue);

            sixRegionValue.remove(0);
            // ?????????????????????
            List<ReportUtilizeInfo> areaName1 = Lists.newArrayList();
            // ?????????????????????
            List<ReportUtilizeInfo> areaName2 = Lists.newArrayList();
            for (ReportUtilizeInfo utilizeInfo : sixRegionValue) {
                if (avg.compareTo(utilizeInfo.getNumber1()) < 0) {
                    areaName1.add(utilizeInfo);
                } else {
                    areaName2.add(utilizeInfo);
                }
            }
            returnMaps.put("param_164", Joiner.on("???").join(areaName1.stream().map(ReportUtilizeInfo::getAreaName).collect(Collectors.toList())));
            returnMaps.put("param_166", Joiner.on("???").join(areaName1.stream().map(item -> item.getNumber1() + "%").collect(Collectors.toList())));
            returnMaps.put("param_169", Joiner.on("???").join(areaName2.stream().map(ReportUtilizeInfo::getAreaName).collect(Collectors.toList())));

            ReportUtilizeInfo reportUtilizeInfo1 = sixRegionValue.get(sixRegionValue.size() - 1);
            returnMaps.put("param_170", reportUtilizeInfo1.getAreaName());
            returnMaps.put("param_171", reportUtilizeInfo1.getNumber1() + "%");
            // ????????????
            Map<String, Map<String, Double>> datas = Maps.newHashMap();
            Map<String, Double> realData = Maps.newLinkedHashMap();
            for (ReportUtilizeInfo utilizeInfo : reportUtilizeInfoList) {
                realData.put(utilizeInfo.getAreaName(), utilizeInfo.getNumber1().doubleValue());
            }
            datas.put("", realData);
            byte[] pictureFive = barChart("????????????????????????",
                    datas, "", "%", 500, 400);
            returnMaps.put("param_1650", new PictureRenderData(500, 400, ".png", pictureFive));
        }

    }


    @Override
    public List<ReportWordProduct> getReportWordInfo(String year) {
        String regionYear = syncSysRegionService.getYearByYear(year);
        return reportCollectionResourceValueMapper.getInfoGroupByProvince(year, regionYear);
    }

    @Override
    public Map<String, Object> getProvinceInfo(String year) {
        Map<String, Object> returnMap = Maps.newHashMap();

        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportWordProduct> infoGroupByProvince = reportCollectionResourceValueMapper.getInfoGroupByProvince(year, regionYear);
        if (!CollectionUtils.isEmpty(infoGroupByProvince)) {
            BigDecimal count = new BigDecimal(0);
            for (ReportWordProduct reportWordProduct : infoGroupByProvince) {
                count = count.add(reportWordProduct.getProduce());
            }
            returnMap.put("collectedC", BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(count)));
            // ??????????????????
            ReportWordProduct reportWordProduct = infoGroupByProvince.get(0);
            returnMap.put("top1CollectProvince", reportWordProduct.getAreaName());
            returnMap.put("top1ProvinceCollect", BigDecimalUtil.setScale2(BigDecimalUtil.getTenThousand(reportWordProduct.getProduce())));
            returnMap.put("top1ProvinceCollectPer", BigDecimalUtil.getProportion(reportWordProduct.getProduce(), count, 1));
            // ??????????????????
            ReportWordProduct reportWordProduct2 = infoGroupByProvince.get(1);
            returnMap.put("secondCollectProvince", reportWordProduct2.getAreaName());
            returnMap.put("secondProvinceCollect", BigDecimalUtil.setScale2(BigDecimalUtil.getTenThousand(reportWordProduct2.getProduce())));
            returnMap.put("secondProvinceCollectPer", BigDecimalUtil.getProportion(reportWordProduct2.getProduce(), count, 1));

            List<String> regionName1 = Lists.newArrayList();
            List<String> regionName2 = Lists.newArrayList();


            for (ReportWordProduct reportWordProduct1 : infoGroupByProvince) {
                // ???????????????????????????4000??????
                if (new BigDecimal("4000").compareTo(BigDecimalUtil.getTenThousand(reportWordProduct1.getProduce())) < 0) {
                    regionName1.add(reportWordProduct1.getAreaName());
                }
                // 2000~4000??????
                if (new BigDecimal("4000").compareTo(BigDecimalUtil.getTenThousand(reportWordProduct1.getProduce())) > 0 &&

                        new BigDecimal("2000").compareTo(BigDecimalUtil.getTenThousand(reportWordProduct1.getProduce())) < 0
                ) {
                    regionName2.add(reportWordProduct1.getAreaName());
                }
            }
            returnMap.put("collectRange1Province", Joiner.on("???").join(regionName1));
            returnMap.put("collectRange1ProvinceNumber", regionName1.size());

            returnMap.put("collectRange2Province", Joiner.on("???").join(regionName2));
            returnMap.put("collectRange2ProvinceNumber", regionName2.size());
            // ?????????????????????
            getSixRegionValue(infoGroupByProvince, count, returnMap);

            // ?????????????????????
            getInfoByArea(year, returnMap, count);

            // ??????????????????
            getStrawInfo(year, returnMap, count);
            return returnMap;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUtlize(String year) {
        Map<String, Object> returnMap = Maps.newHashMap();
        String regionYear = syncSysRegionService.getYearByYear(year);
        Map<String, Object> utilzeInfo = reportCollectionResourceValueMapper.getUtilzeInfo(year, regionYear);
        returnMap.put("strawUsageC", BigDecimalUtil.get100MillionTonsAndScale2((BigDecimal) utilzeInfo.get("count1")));


        returnMap.put("fertilizerC", BigDecimalUtil.get100MillionTonsAndScale2((BigDecimal) utilzeInfo.get("fertilisingcount")));
        returnMap.put("feedC", BigDecimalUtil.get100MillionTonsAndScale2((BigDecimal) utilzeInfo.get("foragecount")));
        returnMap.put("fuelC", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("fuelcount"), 2));
        returnMap.put("basicC", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("basecount"), 2));
        returnMap.put("materialC", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("materialcount"), 2));

        utilzeInfo.remove("count");
        // ???????????????


        // ??????
        Map<String, Map<String, Double>> datas = Maps.newHashMap();
        Map<String, Double> realData = Maps.newLinkedHashMap();

        realData.put("?????????", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("fertilisingcount"), 2).doubleValue());
        realData.put("?????????", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("foragecount"), 2).doubleValue());
        realData.put("?????????", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("fuelcount"), 2).doubleValue());
        realData.put("?????????", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("basecount"), 2).doubleValue());
        realData.put("?????????", BigDecimalUtil.getTenThousand((BigDecimal) utilzeInfo.get("materialcount"), 2).doubleValue());
        datas.put("test", realData);
        byte[] pictureFive = barChart("test",
                datas, "test", "??????", 500, 400);
        returnMap.put("test", new PictureRenderData(500, 400, ".png", pictureFive));
        return returnMap;
    }

    @Override
    public Map<String, Object> getUtilzeRateInfoGroupProvince(String year) {
        Map<String, Object> returnMaps = Maps.newHashMap();
        Integer yearNum = Integer.parseInt(year);
        String lastYear = --yearNum + "";
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportUtilizeInfo> utilzeRateInfoGroupProvince = reportCollectionResourceValueMapper.getUtilizeRateInfoGroupProvince(year, regionYear);
        if (!CollectionUtils.isEmpty(utilzeRateInfoGroupProvince)) {

            BigDecimal num1Count = new BigDecimal(0);
            BigDecimal num2Count = new BigDecimal(0);
            for (ReportUtilizeInfo reportUtilizeInfo : utilzeRateInfoGroupProvince) {
                num1Count = num1Count.add(reportUtilizeInfo.getNumber2());
                num2Count = num2Count.add(reportUtilizeInfo.getNumber3());
            }
            BigDecimal chinaRate = BigDecimalUtil.getProportion(num1Count, num2Count, 2);
            List<ReportUtilizeInfo> oldUtilzeRateInfoGroupProvince = reportCollectionResourceValueMapper.getUtilizeRateInfoGroupProvince(lastYear, regionYear);

            BigDecimal num3Count = new BigDecimal(0);
            BigDecimal num4Count = new BigDecimal(0);
            for (ReportUtilizeInfo reportUtilizeInfo : oldUtilzeRateInfoGroupProvince) {
                num3Count = num3Count.add(reportUtilizeInfo.getNumber2());
                num4Count = num4Count.add(reportUtilizeInfo.getNumber3());
            }
            BigDecimal oldChinaRate = BigDecimalUtil.getProportion(num3Count, num4Count, 2);
            returnMaps.put("comprehensiveRateC", chinaRate + "%");
            returnMaps.put("lastYear", lastYear + "");
            if (chinaRate.compareTo(oldChinaRate) >= 1) {
                // ?????????
                BigDecimal subtract = chinaRate.subtract(oldChinaRate);
                returnMaps.put("rateChange", "?????????" + subtract);
            } else {
                BigDecimal subtract = oldChinaRate.subtract(chinaRate);
                returnMaps.put("rateChange", "?????????" + subtract);
            }
            // ?????????
            ReportUtilizeInfo reportUtilizeInfo = utilzeRateInfoGroupProvince.get(utilzeRateInfoGroupProvince.size() - 1);
            int temp = reportUtilizeInfo.getNumber1().intValue() / 10 * 10;
            returnMaps.put("last1ProvinceComprehensiveRate", temp);

            // ??????95???   90     85

            List<String> area1 = Lists.newArrayList();
            List<String> area2 = Lists.newArrayList();
            List<String> area3 = Lists.newArrayList();
            for (ReportUtilizeInfo utilizeInfo : utilzeRateInfoGroupProvince) {
                // ??????95???
                if (new BigDecimal("95").compareTo(utilizeInfo.getNumber2()) < 0) {
                    area1.add(utilizeInfo.getAreaName());
                }
                // 90 ~ 95
                if (new BigDecimal("95").compareTo(utilizeInfo.getNumber2()) > 0 &&
                        new BigDecimal("90").compareTo(utilizeInfo.getNumber2()) < 0) {
                    area2.add(utilizeInfo.getAreaName());
                }
                // 85 ~ 90
                if (new BigDecimal("90").compareTo(utilizeInfo.getNumber2()) > 0 &&
                        new BigDecimal("85").compareTo(utilizeInfo.getNumber2()) < 0) {
                    area3.add(utilizeInfo.getAreaName());
                }
            }
            returnMaps.put("above95ComprehensiveRateProvince", Joiner.on("???").join(area1));
            returnMaps.put("param_1", area1.size());
            returnMaps.put("above90ComprehensiveRateProvince", Joiner.on("???").join(area2));
            returnMaps.put("param_3", area2.size());
            returnMaps.put("above85ComprehensiveRateProvince", Joiner.on("???").join(area3));
            returnMaps.put("param_5", area3.size());


            // ???????????????{{top1ComprehensiveRateArea}}???????????????????????????????????????{{top1AreaComprehensiveRate}}??????{{lastYear}}???{{comprehensiveRateChange}}????????????
            List<ReportUtilizeInfo> utilizeRateSixRegion = getUtilizeRateSixRegion(utilzeRateInfoGroupProvince);
            List<ReportUtilizeInfo> utilizeRateSixRegion2 = getUtilizeRateSixRegion(oldUtilzeRateInfoGroupProvince);


            ReportUtilizeInfo reportUtilizeInfo1 = utilizeRateSixRegion.get(0);
            ReportUtilizeInfo reportUtilizeInfo2 = utilizeRateSixRegion2.get(0);
            returnMaps.put("top1ComprehensiveRateArea", reportUtilizeInfo1.getAreaName());
            returnMaps.put("top1AreaComprehensiveRate", reportUtilizeInfo1.getNumber1() + "%");
            returnMaps.put("comprehensiveRateChange", getUpOrDown(reportUtilizeInfo1.getNumber1(), reportUtilizeInfo2.getNumber1()));
            // ?????????????????????90% ?????????

            List<ReportUtilizeInfo> utilizeRateSixRegionCopy = Lists.newArrayList(utilizeRateSixRegion);
            utilizeRateSixRegion.remove(0);

            List<String> sixRegionNames = Lists.newArrayList();
            Map<String, ReportUtilizeInfo> maps = Maps.newHashMap();
            Map<String, ReportUtilizeInfo> oldMaps = Maps.newHashMap();
            for (ReportUtilizeInfo utilizeInfo : utilizeRateSixRegion) {
                if (new BigDecimal("90").compareTo(utilizeInfo.getNumber1()) < 0) {
                    sixRegionNames.add(utilizeInfo.getAreaName());
                }
                maps.put(utilizeInfo.getAreaName(), utilizeInfo);
            }

            for (ReportUtilizeInfo utilizeInfo : utilizeRateSixRegion2) {
                oldMaps.put(utilizeInfo.getAreaName(), utilizeInfo);
            }
            returnMaps.put("above90ComprehensiveRateArea", Joiner.on("???").join(sixRegionNames));
            List<String> overContent = Lists.newArrayList();
            for (String sixRegionName : sixRegionNames) {
                ReportUtilizeInfo reportUtilizeInfo3 = maps.get(sixRegionName);
                ReportUtilizeInfo reportUtilizeInfo4 = oldMaps.get(sixRegionName);
                overContent.add(getUpOrDown(reportUtilizeInfo3.getNumber1(), reportUtilizeInfo4.getNumber1()));
            }
            returnMaps.put("above90ComprehensiveRateAreaChange", Joiner.on("???").join(overContent));
            for (String sixRegionName : sixRegionNames) {
                maps.remove(sixRegionName);
            }
            if (!CollectionUtils.isEmpty(maps)) {
                // ????????????????????????90%???  ?????????
                Set<String> strings = maps.keySet();
                returnMaps.put("otherComprehensiveRateArea", Joiner.on("???").join(strings));

                List<String> tempList = Lists.newArrayList();
                List<String> tempList2 = Lists.newArrayList();
                for (String string : strings) {
                    BigDecimal number1 = maps.get(string).getNumber1();
                    BigDecimal number2 = oldMaps.get(string).getNumber1();
                    tempList.add(number1 + "%");
                    String upOrDown = getUpOrDown(number1, number2);
                    tempList2.add(upOrDown);
                }
                returnMaps.put("otherAreaComprehensiveRate", Joiner.on("???").join(tempList));
                returnMaps.put("otherComprehensiveRateAreaChange", Joiner.on("???").join(tempList2));

            }

            // ??????    ????????? -> {2019???15%, 2018 : 16%}
            Map<String, Map<String, Double>> theoryResourceMap = Maps.newLinkedHashMap();
            for (ReportUtilizeInfo utilizeInfo : utilizeRateSixRegionCopy) {
                Map<String, Double> mapOne=Maps.newHashMap();
                mapOne.put(year, BigDecimalUtil.setScale2(utilizeInfo.getNumber1()).doubleValue());
                for (ReportUtilizeInfo utilizeInfo2 : utilizeRateSixRegion2) {
                    if(utilizeInfo.getAreaName().equals(utilizeInfo2.getAreaName())){
                        mapOne.put(lastYear, BigDecimalUtil.setScale2(utilizeInfo2.getNumber1()).doubleValue());
                    }
                }
                theoryResourceMap.put(utilizeInfo.getAreaName(),mapOne );
            }
            byte[] pictureOne = barChart("???????????????", theoryResourceMap, "", "%", 500, 400);
            returnMaps.put("picture234234",new PictureRenderData(800, 400, ".png", pictureOne) );

            // ??????
            getAreaInfo(year, returnMaps);

            // ??????????????????
            getStrawInfoByUtilize(year, returnMaps);
            return returnMaps;
        }

        return null;
    }


    /**
     * ?????????????????????
     *
     * @param year       ??????
     * @param returnMaps ???????????????
     */
    private void getStrawInfoByUtilize(String year, Map<String, Object> returnMaps) {
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportUtilizeInfo> utilizeRateInfoGroupStraw = reportCollectionResourceValueMapper.getUtilizeRateInfoGroupStraw(year, regionYear);
        if (!CollectionUtils.isEmpty(utilizeRateInfoGroupStraw)) {
            // ??????90?????????
            List<ReportUtilizeInfo> reportUtilizeInfos1 = Lists.newArrayList();
            // ??????80%?????????
            List<ReportUtilizeInfo> reportUtilizeInfos2 = Lists.newArrayList();
            // ??????80%?????????
            List<ReportUtilizeInfo> reportUtilizeInfos3 = Lists.newArrayList();
            for (ReportUtilizeInfo reportUtilizeInfo : utilizeRateInfoGroupStraw) {
                if (new BigDecimal(90).compareTo(reportUtilizeInfo.getNumber1()) < 0) {
                    reportUtilizeInfos1.add(reportUtilizeInfo);
                } else if (new BigDecimal(80).compareTo(reportUtilizeInfo.getNumber1()) < 0) {
                    reportUtilizeInfos2.add(reportUtilizeInfo);
                } else {
                    reportUtilizeInfos3.add(reportUtilizeInfo);
                }
            }


            returnMaps.put("rateAbove90Crop", Joiner.on("???").join(reportUtilizeInfos1.stream().map(ReportUtilizeInfo::getStrawName).collect(Collectors.toList())));
            returnMaps.put("rateAbove80Crop", Joiner.on("???").join(reportUtilizeInfos2.stream().map(ReportUtilizeInfo::getStrawName).collect(Collectors.toList())));
            returnMaps.put("rateAbove70Crop", Joiner.on("???").join(reportUtilizeInfos3.stream().map(ReportUtilizeInfo::getStrawName).collect(Collectors.toList())));
            returnMaps.put("above70CropRate", Joiner.on("???").join(reportUtilizeInfos3.stream().map(item -> {
                BigDecimal num = item.getNumber1();
                num = BigDecimalUtil.setScale2(num);
                return num + "%";
            }).collect(Collectors.toList())));
            // ?????????
            Map<String, Map<String, Double>> datas = Maps.newHashMap();
            Map<String, Double> realData = Maps.newLinkedHashMap();
            for (ReportUtilizeInfo reportUtilizeInfo : utilizeRateInfoGroupStraw) {
                realData.put(reportUtilizeInfo.getStrawName(), BigDecimalUtil.setScale2(reportUtilizeInfo.getNumber1()).doubleValue());
            }
            datas.put("???????????????", realData);
            byte[] pictureFive = barChart("???????????????",
                    datas, "", "%", 500, 400);
            returnMaps.put("ratePicture", new PictureRenderData(500, 400, ".png", pictureFive));


        }

    }

    /**
     * ??????????????????
     *
     * @param year       ??????
     * @param returnMaps ?????????
     */
    private void getAreaInfo(String year, Map<String, Object> returnMaps) {
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportUtilizeInfo> utilizeRateInfoGroupAreaId = reportCollectionResourceValueMapper.getUtilizeRateInfoGroupAreaId(year, regionYear);
        if (!CollectionUtils.isEmpty(utilizeRateInfoGroupAreaId)) {
            // ??????95??????
            List<String> areaNames = Lists.newArrayList();
            List<String> areaNames2 = Lists.newArrayList();
            for (ReportUtilizeInfo utilizeInfo : utilizeRateInfoGroupAreaId) {
                if (new BigDecimal("95").compareTo(utilizeInfo.getNumber1()) < 0) {
                    areaNames.add(utilizeInfo.getAreaName());
                } else if (new BigDecimal("90").compareTo(utilizeInfo.getNumber1()) < 0) {
                    areaNames2.add(utilizeInfo.getAreaName());
                }
            }
            returnMaps.put("rateAbove95CountyCount", areaNames.size());
            returnMaps.put("rateAbove95CountyPer", BigDecimalUtil.getProportion(new BigDecimal(areaNames.size()), new BigDecimal(utilizeRateInfoGroupAreaId.size()), 1) + "%");
            returnMaps.put("rateAbove90CountyCount", areaNames2.size());
            returnMaps.put("rateAbove90CountyPer", BigDecimalUtil.getProportion(new BigDecimal(areaNames2.size()), new BigDecimal(utilizeRateInfoGroupAreaId.size()), 1) + "%");
        }


    }


    /**
     * ????????????1????????????2 ?????????????????????
     *
     * @param number1 ??????1
     * @param number2 ??????2
     * @return String
     */
    private String getUpOrDown(BigDecimal number1, BigDecimal number2) {
        if (number1.compareTo(number2) >= 1) {
            // ?????????
            BigDecimal subtract = number1.subtract(number2);
            return "?????????" + subtract;
        } else {
            BigDecimal subtract = number2.subtract(number1);
            return "?????????" + subtract;
        }
    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param utilzeRateInfoGroupProvince ??????????????????
     * @return ??????????????????
     */
    private List<ReportUtilizeInfo> getUtilizeRateSixRegion(List<ReportUtilizeInfo> utilzeRateInfoGroupProvince) {
        if (!CollectionUtils.isEmpty(utilzeRateInfoGroupProvince)) {
            SixRegionEnum[] values = SixRegionEnum.values();
            List<ReportUtilizeInfo> sixValues = Lists.newArrayList();
            for (SixRegionEnum value : values) {
                ReportUtilizeInfo reportUtilizeInfo = new ReportUtilizeInfo();
                reportUtilizeInfo.setAreaName(value.getDescription());
                BigDecimal number1 = new BigDecimal(0);
                BigDecimal number2 = new BigDecimal(0);
                for (ReportUtilizeInfo reportUtilizeInfo2 : utilzeRateInfoGroupProvince) {
                    if (value.getCode().contains(reportUtilizeInfo2.getAreaId())) {
                        number1 = number1.add(reportUtilizeInfo2.getNumber2());
                        number2 = number2.add(reportUtilizeInfo2.getNumber3());
                    }
                }
                reportUtilizeInfo.setNumber2(number1);
                reportUtilizeInfo.setNumber3(number2);
                reportUtilizeInfo.setNumber1(BigDecimalUtil.getProportion(number1, number2, 2));
                sixValues.add(reportUtilizeInfo);
            }
            sixValues.sort((o1, o2) -> o2.getNumber1().compareTo(o1.getNumber1()));

            return sixValues;
        }
        return null;
    }

    /**
     * ?????????????????????
     *
     * @param year      ??????
     * @param returnMap ?????????
     * @param count     ??????
     */
    private void getStrawInfo(String year, Map<String, Object> returnMap, BigDecimal count) {
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportWordProduct> infoGroupByStraw = reportCollectionResourceValueMapper.getInfoGroupByStraw(year, regionYear);
        if (!CollectionUtils.isEmpty(infoGroupByStraw)) {
            // ??????????????????
            ReportWordProduct reportWordProduct = infoGroupByStraw.get(0);
            returnMap.put("top1CollectCrop", reportWordProduct.getStrawName());
            returnMap.put("top1CropCollect", BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct.getProduce())));
            returnMap.put("top1CropCollectPer", BigDecimalUtil.getProportion(reportWordProduct.getProduce(), count, 1));
            // ?????? ??????????????????
            ReportWordProduct reportWordProduct1 = infoGroupByStraw.get(1);
            ReportWordProduct reportWordProduct2 = infoGroupByStraw.get(2);
            returnMap.put("middle2CollectCrop", reportWordProduct1.getStrawName() + "???" + reportWordProduct2.getStrawName());
            returnMap.put("middle2CropCollect", BigDecimalUtil.get100MillionTonsAndScale2(reportWordProduct1.getProduce()) + "?????????" +
                    BigDecimalUtil.get100MillionTonsAndScale2(reportWordProduct2.getProduce()) + "??????");

            returnMap.put("middle2CropCollectPer", BigDecimalUtil.getProportion(reportWordProduct1.getProduce(), count, 1) + "%???" +
                    BigDecimalUtil.getProportion(reportWordProduct2.getProduce(), count, 1) + "%");

            Map<String, Double> realData = Maps.newLinkedHashMap();
            for (ReportWordProduct sixRegionData : infoGroupByStraw) {
                realData.put(sixRegionData.getStrawName(), sixRegionData.getProduce().doubleValue());
            }
            byte[] bytes = pieChart("", realData, 500, 400);
            returnMap.put("collectionResourcePictureTwo", new PictureRenderData(500, 400, ".png", bytes));
        }
    }


    /**
     * ??????????????????
     *
     * @param year      ??????
     * @param returnMap ?????????
     * @param count     ??????
     */
    private void getInfoByArea(String year, Map<String, Object> returnMap, BigDecimal count) {
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportWordProduct> infoGroupByArea = reportCollectionResourceValueMapper.getInfoGroupByArea(year, regionYear);
        if (!CollectionUtils.isEmpty(infoGroupByArea)) {
            returnMap.put("aveCountyCollect",
                    BigDecimalUtil.setScale2(BigDecimalUtil.getTenThousand(count.divide(new BigDecimal(infoGroupByArea.size()), 10, BigDecimal.ROUND_HALF_UP))));
            List<String> areaName1 = Lists.newArrayList();
            List<String> areaName2 = Lists.newArrayList();


            for (ReportWordProduct reportWordProduct : infoGroupByArea) {
                if (new BigDecimal(100).compareTo(reportWordProduct.getProduce()) < 0) {
                    areaName1.add(reportWordProduct.getAreaName());
                }
                if (new BigDecimal(100).compareTo(BigDecimalUtil.getTenThousand(reportWordProduct.getProduce())) > 0 &&
                        new BigDecimal(50).compareTo(BigDecimalUtil.getTenThousand(reportWordProduct.getProduce())) < 0) {
                    areaName2.add(reportWordProduct.getAreaName());
                }
            }

            returnMap.put("collectAbove100CountyCount", areaName1.size());
            returnMap.put("collectAbove100CountyPer", new BigDecimal(areaName1.size()).divide(new BigDecimal(infoGroupByArea.size()), 2, BigDecimal.ROUND_HALF_UP));
            returnMap.put("collectAbove50CountyCount", areaName2.size());
            returnMap.put("collectAbove50CountyPer", new BigDecimal(areaName2.size()).divide(new BigDecimal(infoGroupByArea.size()), 2, BigDecimal.ROUND_HALF_UP));


        }
    }


    /**
     * ????????????????????????
     *
     * @param infoGroupByProvince ??????????????????
     * @param count               ?????????????????????
     * @param returnMap           ?????????Map
     */
    private void getSixRegionValue(List<ReportWordProduct> infoGroupByProvince, BigDecimal count, Map<String, Object> returnMap) {
        if (!CollectionUtils.isEmpty(infoGroupByProvince)) {
            // ?????????????????????
            List<ReportWordProduct> sixValue = Lists.newArrayList();
            for (SixRegionEnum value : SixRegionEnum.values()) {
                ReportWordProduct reportWordProduct = new ReportWordProduct();
                reportWordProduct.setAreaName(value.getDescription());
                BigDecimal temCount = new BigDecimal(0);
                for (ReportWordProduct reportWordProduct2 : infoGroupByProvince) {
                    if (value.getCode().contains(reportWordProduct2.getAreaId())) {
                        temCount = temCount.add(reportWordProduct2.getProduce());
                    }
                }
                reportWordProduct.setProduce(temCount);
                reportWordProduct.setProportion(BigDecimalUtil.getProportion(temCount, count, 1));
                sixValue.add(reportWordProduct);
            }
            sixValue.sort((o1, o2) -> o2.getProduce().compareTo(o1.getProduce()));

            // ?????????????????????
            ReportWordProduct reportWordProduct = sixValue.get(0);
            returnMap.put("top1CollectArea", reportWordProduct.getAreaName());
            returnMap.put("top1AreaCollect", BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct.getProduce())));
            returnMap.put("top1AreaCollectPer", reportWordProduct.getProportion());
            // ???????????????????????????
            ReportWordProduct reportWordProduct1 = sixValue.get(1);
            ReportWordProduct reportWordProduct2 = sixValue.get(2);
            returnMap.put("middle2CollectArea", reportWordProduct1.getAreaName() + "???" + reportWordProduct2.getAreaName());
            returnMap.put("middle2AreaCollect", BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct1.getProduce())) + "?????????"
                    + BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct2.getProduce())) + "??????");
            returnMap.put("middle2AreaCollectPer", reportWordProduct1.getProportion() + "%???" + reportWordProduct2.getProportion() + "%");

            // ?????????????????????????????????
            ReportWordProduct reportWordProduct3 = sixValue.get(3);
            ReportWordProduct reportWordProduct4 = sixValue.get(4);
            ReportWordProduct reportWordProduct5 = sixValue.get(5);
            String tempfomat = "%s???%s???%s";
            String tempfomat2 = "%s?????????%s?????????%s??????";
            returnMap.put("last3CollectArea", String.format(tempfomat, reportWordProduct3.getAreaName(), reportWordProduct4.getAreaName(),
                    reportWordProduct5.getAreaName()));
            returnMap.put("last3AreaCollect", String.format(tempfomat2,
                    BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct3.getProduce())),
                    BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct4.getProduce())),
                    BigDecimalUtil.setScale2(BigDecimalUtil.get100MillionTons(reportWordProduct5.getProduce()))
            ));
            returnMap.put("last3AreaCollectPer", reportWordProduct3.getProportion() + "%???" + reportWordProduct4.getProportion() + "%???" + reportWordProduct5.getProportion());

            // ??????
            sixValue.forEach(item -> item.setProduce(BigDecimalUtil.setScale2(item.getProduce())));


            Map<String, Map<String, Double>> datas = Maps.newHashMap();
            Map<String, Double> realData = Maps.newLinkedHashMap();
            for (ReportWordProduct sixRegionData : sixValue) {
                realData.put(sixRegionData.getAreaName(), BigDecimalUtil.getTenThousand(sixRegionData.getProduce()).doubleValue());
            }

            datas.put("????????????????????????", realData);
            byte[] pictureFive = barChart("????????????????????????",
                    datas, "", "??????", 500, 400);
            returnMap.put("collectionResourcePictureOne", new PictureRenderData(500, 400, ".png", pictureFive));
        }
    }



    /**
     * ??????????????????????????????
     * @return ????????????
     */
    private static byte[] imgToByte(String filePath){
        File file = new File(filePath);
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        //??????????????????
        file.delete();
        return buffer;
    }

    /**
     *@Description //?????????
     *@Date 10:12 2020/11/24
     *@Param [title-?????????, datas-????????????, width-????????????????????????, height-????????????????????????]
     *@Return base64 String
     **/
    private byte[] pieChart(String title, Map<String, Double> datas, int width, int height) {

        //??????????????????
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //??????????????????
        standardChartTheme.setExtraLargeFont(getFont(Font.BOLD, 20f));
        //?????????????????????
        standardChartTheme.setRegularFont(getFont( Font.PLAIN, 15f));
        //?????????????????????
        standardChartTheme.setLargeFont(getFont( Font.PLAIN, 15f));
        //??????????????????
        ChartFactory.setChartTheme(standardChartTheme);

        //??????jfree???????????????????????????
        DefaultPieDataset pds = new DefaultPieDataset();
        datas.forEach(pds::setValue);
        //?????????????????????????????????????????????????????????????????????tooltips????????????????????????
        JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, false);
        //???????????????
        chart.setTextAntiAlias(false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("????????????");
        //?????????????????????
        plot.setIgnoreNullValues(true);
        plot.setBackgroundAlpha(0f);
        //????????????????????????
        plot.setShadowPaint(new Color(255,255,255));
        //?????????????????????(??????{0})
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({2})"));
        String filePath = formCallbackConfig.getDir() +"/" + (int) (Math.random() * 100000) + "ducss.png";
        //String filePath = templateFolder + (int) (Math.random() * 100000) + "ducss.png";
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (IOException e1) {
            log.error("????????????????????????");
        }
        return imgToByte(filePath);
    }

    /**
     * ???????????????????????????????????????2????????????
     * @param title        ??????
     * @param datas        ??????
     * @param type         ??????????????????????????????.....???
     * @param danwei       ????????????????????????
     */
    private byte[] barChart(String title,Map<String,Map<String,Double>> datas,String type,String danwei,  int width, int height){
        //???????????????
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        //??????????????????
        Set<Map.Entry<String, Map<String, Double>>> set1 =  datas.entrySet();    //?????????
        Iterator iterator1 = set1.iterator();                        //???????????????
        Iterator iterator2 = null;
        HashMap<String, Double> map =  null;
        Set<Map.Entry<String,Double>> set2=null;
        Map.Entry entry1=null;
        Map.Entry entry2=null;

        while(iterator1.hasNext()){
            entry1=(Map.Entry) iterator1.next();                    //????????????

            map=(HashMap<String, Double>) entry1.getValue();   //?????????????????????????????????
            set2=map.entrySet();                               //?????????????????????
            iterator2=set2.iterator();                         //??????????????????
            while (iterator2.hasNext()) {
                entry2= (Map.Entry) iterator2.next();
                ds.setValue(Double.parseDouble(entry2.getValue().toString()),//??????????????????
                        entry2.getKey().toString(),                          //??????
                        entry1.getKey().toString());                         //??????
            }
        }

        //??????????????????
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //??????????????????
        standardChartTheme.setExtraLargeFont(getFont( Font.BOLD, 20f));
        //?????????????????????
        standardChartTheme.setRegularFont(getFont(Font.PLAIN, 15f));
        //?????????????????????
        standardChartTheme.setLargeFont(getFont( Font.PLAIN, 15f));
        //??????????????????
        ChartFactory.setChartTheme(standardChartTheme);
        //???????????????,?????????????????????????????????????????????
        JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true, true, true);

        //???????????????
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        LegendTitle legendTitle = new LegendTitle(plot);//????????????
        legendTitle.setPosition(RectangleEdge.TOP); //?????????????????????
        plot.setForegroundAlpha(1.0f);

        String filePath = formCallbackConfig.getDir() +"/" + (int) (Math.random() * 100000) + "ducss.png";
        //String filePath = templateFolder + (int) (Math.random() * 100000) + "ducss.png";
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  imgToByte(filePath);
    }

    //???????????????
    private Font getFont(int style, Float size) {
        Font defFont = new Font("??????", style, 19);
        try {
            this.initFontFile();
            if (file == null || !file.exists()) {
                return defFont;
            }
            Font nf = Font.createFont(Font.TRUETYPE_FONT, file);
            nf = nf.deriveFont(style, size);
            return nf;
        } catch (Exception e) {

        }
        return defFont;
    }


    private void initFontFile() {
        if (file == null) {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = new Resource[0];
            try {
                resources = resolver.getResources("static/simsun.ttc");
                Resource resource = resources[0];
                //???????????????????????????jar??????????????????????????????????????????????????????????????????????????????jar?????????????????????
                InputStream stream = resource.getInputStream();

                file = this.asFile(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //??????????????????
    public File asFile(InputStream inputStream) throws IOException{
        //Linux????????????/usr/
        String tmpPath = formCallbackConfig.getDir() + File.separator + "simsun.ttc";
        //String tmpPath = templateFolder + File.separator +  File.separator + "simsun.ttc";
        File targetFile = new File(tmpPath);
        //???????????????????????????
        FileUtils.copyInputStreamToFile(inputStream, targetFile);

        inputStream.close();
        return targetFile;
    }
}
