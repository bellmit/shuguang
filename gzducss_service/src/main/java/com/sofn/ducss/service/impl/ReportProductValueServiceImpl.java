package com.sofn.ducss.service.impl;

import com.deepoove.poi.data.PictureRenderData;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.mapper.ReportProductValueMapper;
import com.sofn.ducss.model.wordmodel.ReportWordProduct;
import com.sofn.ducss.service.ReportProductValueService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.util.IdUtil;
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
 * 产生量值
 *
 * @author heyongjie
 * @date 2020/12/31 10:32
 */
@Service
@Slf4j
public class ReportProductValueServiceImpl implements ReportProductValueService {

    @Autowired
    private ReportProductValueMapper reportProductValueMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    //本地调试使用路径
    //private static final String templateFolder = getClassLoader().getResource("static/").getPath();
    private static File file = null;


    @Autowired
    private FormCallbackConfig formCallbackConfig;

    @Override
    public Map<String, Object> getValue(String year) {
        Map<String, Object> returnMaps = Maps.newHashMap();
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportWordProduct> productInfoGroupByProvince = reportProductValueMapper.getProductInfoGroupByProvince(year, regionYear);
        if (!CollectionUtils.isEmpty(productInfoGroupByProvince)) {
            returnMaps.put("year", year);
            returnMaps.put("countProvince", productInfoGroupByProvince.size());
            BigDecimal count = new BigDecimal("0");

            for (ReportWordProduct stringObjectMap : productInfoGroupByProvince) {
                count = count.add(stringObjectMap.getProduce());
            }
            returnMaps.put("produceC", count.divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP));
            // 排名第一的省份、产生量万吨保留二位小数 、 产生量占比（保留1位小数）
            returnMaps.put("top1ProduceProvince", productInfoGroupByProvince.get(0).getAreaName());
            returnMaps.put("top1ProvinceProduce",
                    BigDecimalUtil.getTenThousand(productInfoGroupByProvince.get(0).getProduce(), 2));
            returnMaps.put("top1ProvinceProducePer", BigDecimalUtil.getProportion(productInfoGroupByProvince.get(0).getProduce(), count, 1));
            // 排名第二的省份、产生量万吨保留二位小数、 产生量占比（保留1位小数）
            returnMaps.put("secondProduceProvince", productInfoGroupByProvince.get(1).getAreaName());
            returnMaps.put("secondProvinceProduce", BigDecimalUtil.getTenThousand(productInfoGroupByProvince.get(1).getProduce(), 2));
            returnMaps.put("secondProvinceProducePer", BigDecimalUtil.getProportion(productInfoGroupByProvince.get(1).getProduce(), count, 1));

            // 产生量5000~7999万吨的数据
            List<String> provinceNames = Lists.newArrayList();
            List<String> provincePers = Lists.newArrayList();
            for (ReportWordProduct stringObjectMap : productInfoGroupByProvince) {
                BigDecimal produce = (stringObjectMap.getProduce()).divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN);
                if (produce.compareTo(new BigDecimal(5000)) > 0 && produce.compareTo(new BigDecimal(7999)) < 0) {
                    provinceNames.add(stringObjectMap.getAreaName());
                    provincePers.add(BigDecimalUtil.getProportion(stringObjectMap.getProduce(), count, 1).toString() + "%");
                }
            }
            returnMaps.put("provinceProduceRange1", Joiner.on("、").join(provinceNames));
            returnMaps.put("provinceProduceRange1Per", Joiner.on("、").join(provincePers));
            // 产生量2000~5000万吨
            provinceNames.clear();
            for (ReportWordProduct stringObjectMap : productInfoGroupByProvince) {
                BigDecimal produce = (stringObjectMap.getProduce()).divide(new BigDecimal(10000), 0, BigDecimal.ROUND_DOWN);
                if (produce.compareTo(new BigDecimal(2000)) > 0 && produce.compareTo(new BigDecimal(5000)) < 0) {
                    provinceNames.add(stringObjectMap.getAreaName());
                }
            }
            returnMaps.put("provinceProduceRange2", Joiner.on("、").join(provinceNames));
            returnMaps.put("provinceProduceRange2Number", provinceNames.size());
            getPictureByProvinceProduct(productInfoGroupByProvince, returnMaps, count);
            // 获取六大区数据
            getSixRegionValue(productInfoGroupByProvince, returnMaps, count);

        }
        return returnMaps;
    }

    @Override
    public Map<String, Object> getAreaValue(String year) {
        Map<String, Object> returnMaps = Maps.newHashMap();
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportWordProduct> productInfoGroupByArea = reportProductValueMapper.getProductInfoGroupByArea(null, year, regionYear);
        if (!CollectionUtils.isEmpty(productInfoGroupByArea)) {
            // 总共多少个县
            returnMaps.put("countyCount", productInfoGroupByArea.size());
            BigDecimal count = new BigDecimal("0");
            for (ReportWordProduct reportWordProduct : productInfoGroupByArea) {
                count = count.add(reportWordProduct.getProduce());
            }
            // 每个县平均
            returnMaps.put("countyProduceAve", BigDecimalUtil.getTenThousand(
                    count.divide(new BigDecimal(productInfoGroupByArea.size()), 10, BigDecimal.ROUND_HALF_UP)
            ).setScale(2, BigDecimal.ROUND_HALF_UP));
            count = new BigDecimal("0");
            for (ReportWordProduct reportWordProduct : productInfoGroupByArea) {
                if (reportWordProduct.getProduce().compareTo(new BigDecimal("1000000")) > 0) {
                    count = count.add(new BigDecimal("1"));
                }
            }
            returnMaps.put("above1Count", count);

            returnMaps.put("above1Per", BigDecimalUtil.getProportion(count, new BigDecimal(productInfoGroupByArea.size()), 1));

            count = new BigDecimal("0");
            for (ReportWordProduct reportWordProduct : productInfoGroupByArea) {
                if (reportWordProduct.getProduce().compareTo(new BigDecimal("500000")) > 0) {
                    count = count.add(new BigDecimal("1"));
                }
            }
            returnMaps.put("above5Count", productInfoGroupByArea.size());
            returnMaps.put("above5Per", BigDecimalUtil.getProportion(count, new BigDecimal(productInfoGroupByArea.size()), 1));
            return returnMaps;
        }

        return null;
    }

    @Override
    public Map<String, Object> getStrawValue(String year) {
        String template = "%s、%s和%s";
        Map<String, Object> returnMaps = Maps.newHashMap();
        String regionYear = syncSysRegionService.getYearByYear(year);
        List<ReportWordProduct> productInfoGroupStraw = reportProductValueMapper.getProductInfoGroupStraw(year, regionYear);
        if (!CollectionUtils.isEmpty(productInfoGroupStraw)) {
            ReportWordProduct reportWordProduct = productInfoGroupStraw.get(0);
            ReportWordProduct reportWordProduct1 = productInfoGroupStraw.get(1);
            ReportWordProduct reportWordProduct2 = productInfoGroupStraw.get(2);
            BigDecimal count = new BigDecimal("0");
            for (ReportWordProduct wordProduct : productInfoGroupStraw) {
                count = count.add(wordProduct.getProduce());
            }
            for (ReportWordProduct wordProduct : productInfoGroupStraw) {
                wordProduct.setProportion(BigDecimalUtil.getProportion(wordProduct.getProduce(), count, 1));
            }
            reportWordProduct.getAreaName();
            returnMaps.put("top3ProduceCrop", String.format(template, reportWordProduct.getAreaName(),
                    reportWordProduct1.getAreaName(), reportWordProduct2.getAreaName()));
            returnMaps.put("top3ProduceCropPer", String.format(template, reportWordProduct.getProportion() + "%",
                    reportWordProduct1.getProportion() + "%", reportWordProduct2.getProportion() + "%"));
            BigDecimal countp = reportWordProduct.getProportion().add(reportWordProduct1.getProportion()).add(reportWordProduct2.getProportion());
            returnMaps.put("top3ProduceCropTotalPer", countp);

            Map<String, Double> realData = Maps.newLinkedHashMap();
            for (ReportWordProduct wordProduct : productInfoGroupStraw) {
                realData.put(wordProduct.getAreaName(), wordProduct.getProduce().doubleValue());
            }
            byte[] bytes = pieChart("", realData, 500, 400);
            returnMaps.put("pictureFour", new PictureRenderData(500, 400, ".png", bytes));
            // 全国水稻秸秆主要分布在{{riceTop2ProduceArea}}，秸秆产生量分别占全国的{{riceTop2ProduceAreaPer}}....
            // 按照指标和六大区进行分组   组装成大区数据
            SixRegionEnum[] values = SixRegionEnum.values();
            List<String> straws = productInfoGroupStraw.stream().map(ReportWordProduct::getAreaId).collect(Collectors.toList());
            List<ReportWordProduct> productInfoGroupAreaAndStraw = reportProductValueMapper.getProductInfoGroupAreaAndStraw(year, regionYear);
            List<ReportWordProduct> newInfo = Lists.newArrayList();
            for (SixRegionEnum value : values) {
                for (String straw : straws) {
                    ReportWordProduct reportWordProduct3 = new ReportWordProduct();
                    reportWordProduct3.setAreaName(value.getDescription());
                    String code = value.getCode();
                    // 计算产生量值
                    BigDecimal temp = new BigDecimal("0");
                    for (ReportWordProduct wordProduct : productInfoGroupAreaAndStraw) {
                        if(code.contains(wordProduct.getAreaId())  && straw.equals(wordProduct.getStrawCode())){
                            temp = temp.add(wordProduct.getProduce());
                            reportWordProduct3.setStrawCode(wordProduct.getStrawCode());
                            reportWordProduct3.setStrawName(wordProduct.getStrawName());
                        }
                    }
                    reportWordProduct3.setProduce(temp);

                    newInfo.add(reportWordProduct3);
                }
            }
            // 水稻分为三个指标，分别为：中季和一季晚稻    双季晚稻  早稻   将三个指标合并成rice
            // rice
            for (SixRegionEnum value : values) {
                ReportWordProduct reportWordProduct3 = new ReportWordProduct();
                reportWordProduct3.setAreaName(value.getDescription());
                BigDecimal temp = new BigDecimal(0);
                List<ReportWordProduct> collect = newInfo.stream().filter(item -> value.getDescription().equals(item.getAreaName()) &&
                        ("early_rice".equals(item.getStrawCode()) || "middle_rice".equals(item.getStrawCode()) || "double_late_rice".equals(item.getStrawCode())))
                        .collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(collect)){
                    for (ReportWordProduct wordProduct : collect) {
                        temp = temp.add(wordProduct.getProduce());
                    }
                }
                reportWordProduct3.setStrawCode("rice");
                reportWordProduct3.setStrawName("水稻");
                reportWordProduct3.setProduce(temp);
                newInfo.add(reportWordProduct3);
            }

            // 将三种早稻进行一个合并
            List<ReportWordProduct> collect = newInfo.stream().filter(item -> "rice".equals(item.getStrawCode())).collect(Collectors.toList());
            // 计算全国的水稻的产生量
            setStrawValue(collect, returnMaps,"riceTop2ProduceArea","riceTop2ProduceAreaPer",
                    "riceMiddle2ProduceArea","riceMiddle2ProduceAreaPer","riceLast2ProduceArea","riceLast2ProduceAreaPer"
            ,"pictureFive");
            // 全国小麦信息
            List<ReportWordProduct> collect1 = newInfo.stream().filter(item -> "wheat".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect1, returnMaps,"wheatTop2ProduceArea","wheatTop2ProduceAreaPer",
                    "wheatMiddle2ProduceArea","wheatMiddle2ProduceAreaPer","wheatLast2ProduceArea","adfasdf"
                    ,"pictureSix");
            // 全国 玉米信息 corn

            List<ReportWordProduct> collect2 = newInfo.stream().filter(item -> "corn".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect2, returnMaps,"cornTop2ProduceArea","cornTop2ProduceAreaPer",
                    "cornMiddle3ProduceArea","cornMiddle3ProduceAreaPer","cornLast1ProduceArea","cornLast1ProduceAreaPer"
                    ,"pictureSeven");
            // 全国马铃薯     potato
            List<ReportWordProduct> collect3 = newInfo.stream().filter(item -> "potato".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect3, returnMaps,"potatoTop2ProduceArea","potatoTop2ProduceAreaPer",
                    "potatoMiddle2ProduceArea","potatoMiddle2ProduceAreaPer","potatoLast2ProduceArea","potatoLast2ProduceAreaPer"
                    ,"pictureEight");

            // 全国甘薯   sweet_potato
            List<ReportWordProduct> collect4 = newInfo.stream().filter(item -> "potato".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect4, returnMaps,"sweetpotatoTop3ProduceArea","sweetpotatoTop3ProduceAreaPer",
                    "sweetpotatoMiddle1ProduceArea","sweetpotatoMiddle1ProduceAreaPer","sweetpotatoLast2ProduceArea","sweetpotatoLast2ProduceAreaPer"
                    ,"pictureNine");

            // 全国木薯  cassava  TODO
            List<ReportWordProduct> collect5 = newInfo.stream().filter(item -> "cassava".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect5, returnMaps,"cassavaTop2ProduceArea","cassavaTop2ProduceAreaPer",
                    "","","",""
                    ,"pictureTen");
            // 全国花生
            List<ReportWordProduct> collect6 = newInfo.stream().filter(item -> "peanut".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect6, returnMaps,"peanutTop3ProduceArea","peanutTop3ProduceAreaPer",
                    "peanutMiddle2ProduceArea","peanutMiddle2ProduceAreaPer","peanutLast1ProduceArea","peanutLast1ProduceAreaPer"
                    ,"pictureEleven");

            // 全国油菜  rape
            List<ReportWordProduct> collect7 = newInfo.stream().filter(item -> "rape".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect7, returnMaps,"rapeTop2ProduceArea","rapeTop2ProduceAreaPer",
                    "rapeMiddle2ProduceArea","rapeMiddle2ProduceAreaPer","rapeLast2ProduceArea","rapeLast2ProduceAreaPer"
                    ,"pictureTwelve");
            // 全国油菜  soybean
            List<ReportWordProduct> collect8 = newInfo.stream().filter(item -> "soybean".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect8, returnMaps,"soybeanTop2ProduceArea","soybeanTop2ProduceAreaPer",
                    "soybeanMiddle2ProduceArea","soybeanMiddle2ProduceAreaPer","soybeanLast2ProduceArea","soybeanLast2ProduceAreaPer"
                    ,"pictureThirteen");

            // 全国棉花
            List<ReportWordProduct> collect9 = newInfo.stream().filter(item -> "cotton".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect9, returnMaps,"cottonTop1ProduceArea","cottonTop1ProduceAreaPer",
                    "cottonMiddle2ProduceArea","cottonMiddle2ProduceAreaPer","cottonLast1ProduceArea","cottonLast1ProduceAreaPer"
                    ,"pictureFourteen");

            // 全国甘蔗
            List<ReportWordProduct> collect10 = newInfo.stream().filter(item -> "sugar_cane".equals(item.getStrawCode())).collect(Collectors.toList());
            setStrawValue(collect10, returnMaps,"sugarcaneTop2ProduceArea","sugarcaneTop2ProduceAreaPer",
                    "sugarcaneLast1ProduceArea","sugarcaneLast1ProduceAreaPer","",""
                    ,"pictureFifteen");
            return returnMaps;
        }


        return null;
    }

    /**
     * 设置产生量中的各个作物的各个指标
     * 模板内容如下
     * 全国水稻秸秆主要分布在{{riceTop2ProduceArea}}，秸秆产生量分别占全国的{{riceTop2ProduceAreaPer}}；其次为{{riceMiddle2ProduceArea}}，
     * 水稻秸秆产生量分别占全国的{{riceMiddle2ProduceAreaPer}}；{{riceLast2ProduceArea}}仅占{{riceLast2ProduceAreaPer}}（图6）。
     * @param collect  数据
     * @param returnMaps  返回内容
     * @param param1  参数1
     * @param param2  参数2
     * @param param3  参数3
     * @param param4  参数4
     * @param param5  参数5
     * @param param6  参数6
     * @param pictureParamName   图片参数
     */
    private void setStrawValue(List<ReportWordProduct> collect,  Map<String, Object> returnMaps,
                               String param1,String param2,String param3,String param4,String param5,String param6,
                               String pictureParamName){

        // 计算全国的水稻的产生量
        BigDecimal bigDecimal = new BigDecimal(0);
        for (ReportWordProduct wordProduct : collect) {
            bigDecimal = bigDecimal.add(wordProduct.getProduce());
        }
        collect.sort((o1,o2)->o2.getProduce().compareTo(o1.getProduce()));
        // 计算各个区域的产生量占比
        for (ReportWordProduct wordProduct : collect) {
            wordProduct.setProportion(BigDecimalUtil.getProportion(wordProduct.getProduce(), bigDecimal, 1));
        }
        // 水稻第一和第二的区域
        ReportWordProduct reportWordProduct3 = collect.get(0);
        ReportWordProduct reportWordProduct4 = collect.get(1);
        returnMaps.put(param1,reportWordProduct3.getAreaName() + "和" +  reportWordProduct4.getAreaName());
        returnMaps.put(param2, reportWordProduct3.getProportion() + "%和" + reportWordProduct4.getProportion() + "%");
        ReportWordProduct reportWordProduct5 = collect.get(2);
        ReportWordProduct reportWordProduct6 = collect.get(3);
        returnMaps.put(param3, reportWordProduct5.getAreaName() + "和" + reportWordProduct6.getAreaName());
        returnMaps.put(param4, reportWordProduct5.getProportion() + "%和" + reportWordProduct6.getProportion() + "%");
        ReportWordProduct reportWordProduct7 = collect.get(4);
        ReportWordProduct reportWordProduct8 = collect.get(5);
        returnMaps.put(param5,reportWordProduct7.getAreaName() + "和" +  reportWordProduct8.getAreaName());
        returnMaps.put(param6,reportWordProduct7.getProportion() + "%和" +  reportWordProduct7.getProportion() + "%");
        // 生成图片
        Map<String, Double> realData = Maps.newLinkedHashMap();
        for (ReportWordProduct reportWordProduct : collect) {
            realData.put(reportWordProduct.getAreaName(), reportWordProduct.getProportion().doubleValue());
        }
        byte[] pictureFive = pieChart("", realData, 500, 400);
        returnMaps.put(pictureParamName,new PictureRenderData(500, 400, ".png", pictureFive));
    }

    /**
     * 获取六大区的数据
     *
     * @param productInfoGroupByProvince 各个省的信息
     * @param returnMaps                 返回信息
     * @param allCount                   全国总计的
     */
    private void getSixRegionValue(List<ReportWordProduct> productInfoGroupByProvince, Map<String, Object> returnMaps, BigDecimal allCount) {
        if (!CollectionUtils.isEmpty(productInfoGroupByProvince)) {

            Map<String, ReportWordProduct> provinceInfo = Maps.newHashMap();
            for (ReportWordProduct stringObjectMap : productInfoGroupByProvince) {
                provinceInfo.put(stringObjectMap.getAreaId(), stringObjectMap);
            }
            // 构造六大区数据
            SixRegionEnum[] values = SixRegionEnum.values();
            List<ReportWordProduct> sixRegionDatas = Lists.newArrayList();
            for (SixRegionEnum value : values) {
                if (value.getDescription().contains("区")) {
                    List<String> idsByStr = IdUtil.getIdsByStr(value.getCode());
                    ReportWordProduct sixRegionData = new ReportWordProduct();
                    sixRegionData.setAreaName(value.getDescription());

                    BigDecimal count = new BigDecimal("0");
                    for (String areaId : idsByStr) {
                        ReportWordProduct stringObjectMap = provinceInfo.get(areaId);
                        if (stringObjectMap != null) {
                            count = count.add(stringObjectMap.getProduce());
                        }
                    }
                    sixRegionData.setProduce(count);
                    sixRegionData.setProportion(BigDecimalUtil.getProportion(count, allCount, 2));
                    sixRegionDatas.add(sixRegionData);
                }
            }
            // 排序
            sixRegionDatas.sort((o1, o2) -> o2.getProduce().compareTo(o1.getProduce()));
            // 产生量最高的区
            returnMaps.put("top1ProduceArea", sixRegionDatas.get(0).getAreaName());
            returnMaps.put("top1AreaProduce", (sixRegionDatas.get(0).getProduce())
                    .divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_DOWN));
            returnMaps.put("top1AreaProducePer", BigDecimalUtil.getProportion(sixRegionDatas.get(0).getProduce(), allCount, 1));
            // 产生量排名第二第三的区
            ReportWordProduct stringObjectMap1 = sixRegionDatas.get(1);
            ReportWordProduct stringObjectMap = sixRegionDatas.get(2);
            List<String> regionNames = Lists.newArrayList(stringObjectMap1.getAreaName(), stringObjectMap.getAreaName());
            List<String> regionNamesPer = Lists.newArrayList();
            regionNamesPer.add(BigDecimalUtil.getProportion(stringObjectMap1.getProduce(), allCount, 1).toString() + "%");
            regionNamesPer.add(BigDecimalUtil.getProportion(stringObjectMap.getProduce(), allCount, 1).toString() + "%");
            returnMaps.put("secAndThirdProduceArea", Joiner.on("和").join(regionNames));
            returnMaps.put("secAndThirdAreaProducePer", Joiner.on("和").join(regionNamesPer));
            regionNames.clear();
            regionNamesPer.clear();
            // 剩下三个区
            ReportWordProduct stringObjectMap2 = sixRegionDatas.get(3);
            ReportWordProduct stringObjectMap3 = sixRegionDatas.get(4);
            ReportWordProduct stringObjectMap4 = sixRegionDatas.get(5);
            regionNames = Lists.newArrayList(stringObjectMap2.getAreaName(), stringObjectMap3.getAreaName(), stringObjectMap4.getAreaName());
            regionNamesPer.add(BigDecimalUtil.getProportion(stringObjectMap2.getProduce(), allCount, 1).toString() + "%");
            regionNamesPer.add(BigDecimalUtil.getProportion(stringObjectMap3.getProduce(), allCount, 1).toString() + "%");
            regionNamesPer.add(BigDecimalUtil.getProportion(stringObjectMap4.getProduce(), allCount, 1).toString() + "%");
            returnMaps.put("otherProduceArea", regionNames.get(0) + "、" + regionNames.get(1) + "和" + regionNames.get(2));
            returnMaps.put("otherAreaProducePer", regionNamesPer.get(0) + "、" + regionNamesPer.get(1) + "和" + regionNamesPer.get(2));
            // 生成图片
            getPoctureByProvinceSixRegion(sixRegionDatas, returnMaps);
        }

    }

    /**
     * 生成六大区产生量图片
     *
     * @param sixRegionDatas 六大区数据
     * @param returnMaps     返回值
     */
    private void getPoctureByProvinceSixRegion(List<ReportWordProduct> sixRegionDatas, Map<String, Object> returnMaps) {

        Map<String, Map<String, Double>> datas = Maps.newHashMap();
        Map<String, Double> realData = Maps.newLinkedHashMap();
        sixRegionDatas.sort((o1, o2) -> o2.getProduce().compareTo(o1.getProduce()));
        for (ReportWordProduct sixRegionData : sixRegionDatas) {
            realData.put(sixRegionData.getAreaName(), BigDecimalUtil.getTenThousand(sixRegionData.getProduce()).doubleValue());
        }

        datas.put("产生量（万吨）", realData);
        byte[] pictureFive = barChart("产生量（万吨）",
                datas, "产生量（万吨）", "%", 500, 400);
        returnMaps.put("pictureTwo", new PictureRenderData(500, 400, ".png", pictureFive));
        // 产生量排序
        sixRegionDatas.sort((o1, o2) -> o2.getProportion().compareTo(o1.getProportion()));
        realData.clear();
        for (ReportWordProduct sixRegionData : sixRegionDatas) {
            realData.put(sixRegionData.getAreaName(), sixRegionData.getProportion().doubleValue());
        }
        byte[] bytes = pieChart("", realData, 500, 400);
        returnMaps.put("pictureThree", new PictureRenderData(500, 400, ".png", bytes));
    }

    /**
     * 获取全国各省市的参数
     *
     * @param productInfoGroupByProvince 各省的产品信息
     * @param returnMaps                 返回的信息
     */
    private void getPictureByProvinceProduct(List<ReportWordProduct> productInfoGroupByProvince, Map<String, Object> returnMaps, BigDecimal count) {
        /*-------------------------------全国各省市的参数-----------------------*/

        Map<String, Map<String, Double>> datas = Maps.newHashMap();
        Map<String, Double> realData = Maps.newLinkedHashMap();


        for (ReportWordProduct stringObjectMap : productInfoGroupByProvince) {
            stringObjectMap.setProportion(BigDecimalUtil.getProportion(stringObjectMap.getProduce(), count, 2));

        }
        productInfoGroupByProvince.sort((o1, o2) -> o2.getProportion().compareTo(o1.getProportion()));
        for (ReportWordProduct stringDoubleMap : productInfoGroupByProvince) {
            realData.put(stringDoubleMap.getAreaName(), stringDoubleMap.getProportion().doubleValue());
        }

        datas.put("产生量占比", realData);

        byte[] pictureFive = barChart("产生量占比",
                datas, "产生量占比", "%", 500, 400);
        returnMaps.put("pictureOne", new PictureRenderData(500, 400, ".png", pictureFive));
    }




    /**
     * 将图片转化为字节数组
     * @return 字节数组
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
        //删除临时文件
        file.delete();
        return buffer;
    }

    /**
     *@Description //饼状图
     *@Date 10:12 2020/11/24
     *@Param [title-图标题, datas-显示数据, width-所保存饼状图的宽, height-所保存饼状图的高]
     *@Return base64 String
     **/
    private byte[] pieChart(String title, Map<String, Double> datas, int width, int height) {

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(getFont(Font.BOLD, 20f));
        //设置图例的字体
        standardChartTheme.setRegularFont(getFont( Font.PLAIN, 15f));
        //设置轴向的字体
        standardChartTheme.setLargeFont(getFont( Font.PLAIN, 15f));
        //设置主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        //根据jfree生成一个本地饼状图
        DefaultPieDataset pds = new DefaultPieDataset();
        datas.forEach(pds::setValue);
        //图标标题、数据集合、是否显示图例标识、是否显示tooltips、是否支持超链接
        JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, false);
        //设置抗锯齿
        chart.setTextAntiAlias(false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("暂无数据");
        //忽略无值的分类
        plot.setIgnoreNullValues(true);
        plot.setBackgroundAlpha(0f);
        //设置标签阴影颜色
        plot.setShadowPaint(new Color(255,255,255));
        //设置标签生成器(默认{0})
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({2})"));
        String filePath = formCallbackConfig.getDir() +"/" + (int) (Math.random() * 100000) + "ducss.png";
        //String filePath = templateFolder + (int) (Math.random() * 100000) + "ducss.png";
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (IOException e1) {
            log.error("生成饼状图失败！");
        }
        return imgToByte(filePath);
    }

    /**
     * 提供静态方法：获取报表图形2：柱状图
     * @param title        标题
     * @param datas        数据
     * @param type         分类（第一季，第二季.....）
     * @param danwei       柱状图的数量单位
     */
    private byte[] barChart(String title,Map<String,Map<String,Double>> datas,String type,String danwei,  int width, int height){
        //种类数据集
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        //获取迭代器：
        Set<Map.Entry<String, Map<String, Double>>> set1 =  datas.entrySet();    //总数据
        Iterator iterator1 = set1.iterator();                        //第一次迭代
        Iterator iterator2 = null;
        HashMap<String, Double> map =  null;
        Set<Map.Entry<String,Double>> set2=null;
        Map.Entry entry1=null;
        Map.Entry entry2=null;

        while(iterator1.hasNext()){
            entry1=(Map.Entry) iterator1.next();                    //遍历分类

            map=(HashMap<String, Double>) entry1.getValue();   //得到每次分类的详细信息
            set2=map.entrySet();                               //获取键值对集合
            iterator2=set2.iterator();                         //再次迭代遍历
            while (iterator2.hasNext()) {
                entry2= (Map.Entry) iterator2.next();
                ds.setValue(Double.parseDouble(entry2.getValue().toString()),//每次统计数量
                        entry2.getKey().toString(),                          //名称
                        entry1.getKey().toString());                         //分类
            }
        }

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(getFont( Font.BOLD, 20f));
        //设置图例的字体
        standardChartTheme.setRegularFont(getFont(Font.PLAIN, 15f));
        //设置轴向的字体
        standardChartTheme.setLargeFont(getFont( Font.PLAIN, 15f));
        //设置主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        //创建柱状图,柱状图分水平显示和垂直显示两种
        JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true, true, true);

        //得到绘图区
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        LegendTitle legendTitle = new LegendTitle(plot);//创建图例
        legendTitle.setPosition(RectangleEdge.TOP); //设置图例的位置
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

    //自定义字体
    private Font getFont(int style, Float size) {
        Font defFont = new Font("黑体", style, 19);
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
                //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
                InputStream stream = resource.getInputStream();

                file = this.asFile(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //将流写入文档
    public File asFile(InputStream inputStream) throws IOException{
        //Linux目录下的/usr/
        String tmpPath = formCallbackConfig.getDir() + File.separator + "simsun.ttc";
        //String tmpPath = templateFolder + File.separator +  File.separator + "simsun.ttc";
        File targetFile = new File(tmpPath);
        //将流读到目标文件中
        FileUtils.copyInputStreamToFile(inputStream, targetFile);

        inputStream.close();
        return targetFile;
    }



}
