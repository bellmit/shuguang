/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:35
 */
package com.sofn.ducss.service.impl;

import com.deepoove.poi.data.PictureRenderData;
import com.google.common.collect.Lists;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionEnum;
import com.sofn.ducss.model.ProductionUsageSum;
import com.sofn.ducss.model.StrawUtilize;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.SysFileApi;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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

import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部级报告生成实现类
 *
 * @author jiangtao
 * @version 1.0
 **/
@Slf4j
@Service
public class ProvinceReportServiceImpl implements ProvinceReportService {

    @Autowired
    private AggregateService aggregateService;
    @Autowired
    private StrawUtilizeService strawUtilizeService;
    @Autowired
    private StrawUtilizeDetailService strawUtilizeDetailService;
    @Autowired
    private SysApi sysApi;
    @Autowired
    private FormCallbackConfig formCallbackConfig;
    @Autowired
    private SysFileApi sysFileApi;
    @Autowired
    private ProductionUsageSumService productionUsageSumService;
    //本地调试使用路径
    //private static final String templateFolder = getClassLoader().getResource("static/").getPath();
    private static File file = null;

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
    public File asFile(InputStream inputStream) throws IOException {
        //Linux目录下的/usr/
        String tmpPath = formCallbackConfig.getDir() + File.separator + "simsun.ttc";
        //String tmpPath = templateFolder + File.separator +  File.separator + "simsun.ttc";
        File targetFile = new File(tmpPath);
        //将流读到目标文件中
        FileUtils.copyInputStreamToFile(inputStream, targetFile);

        inputStream.close();
        return targetFile;
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

    @Override
    public Map<String, Object> getProvinceReport(String provinceId, String year) {
        //若不输入年度,则取当前年的上一年度
        if (StringUtils.isEmpty(year) || !StringUtils.isNumeric(year)) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
        }
        // ArrayList<String> status = new ArrayList<>();
        // status.add(AuditStatusEnum.PASSED.getCode());

        String cityId = "";                           //市级ID
        Map<String, Object> map = new HashMap<>();    //数据替换的总集合
        //查询条件
        AggregateQueryVo queryVo = new AggregateQueryVo();
        queryVo.setYear(year);

        //判断当前省级是否为直辖市;若是，则单独处理
        if (RegionEnum.VERB_CODES.getCode().contains(provinceId)) {
            map.put("allProOrCity", "全市");
            map.put("cityOrCounty", "区县");
            //直辖市获取虚拟市ID
            cityId = Constants.getZxsAreaId(Integer.parseInt(provinceId)).toString();
            queryVo.setAreaIds(cityId);
        } else {
            map.put("allProOrCity", "全省");
            map.put("cityOrCounty", "市");
            queryVo.setAreaIds(provinceId);
        }
        //获取省名称
        String provinceName = SysRegionUtil.getRegionNameByRegionCode(provinceId, year);
        map.put("province", provinceName);
        //年度
        map.put("year", year);
        //根据年度，省级ID查询各项指标值
        // --- 产生量与利用量汇总（production_usage_sum）表查询------///
        // --产生量、可收集量、利用量、综合利用率、（上一年的综合利用率）---//
        //--top3crop、No1CollectedArea 从straw_produce表中得到---//
        List<ProductionUsageSum> sumList = productionUsageSumService.selectProductionUsageSum(Lists.newArrayList(queryVo.getAreaIds()), year, Lists.newArrayList("5"));
        // List<StrawProduceUtilizeResVo2> list = aggregateService.findStrawProduceAndUtilzeData2(queryVo);
        // 还田离田量查询
        List<ReturnLeaveSumVo> returnLeaveSumVoList = aggregateService.findReturnLeaveSumData(queryVo);
        if (CollectionUtils.isNotEmpty(sumList)) {
            ProductionUsageSum provinceUsage = sumList.get(0);
            StrawProduceUtilizeResVo2 provinceProduce = new StrawProduceUtilizeResVo2();
            provinceProduce.setTheoryResource(provinceUsage.getProduce());
            provinceProduce.setCollectResource(provinceUsage.getCollect());
            provinceProduce.setComprehensive(provinceUsage.getComprehensiveRate());
            provinceProduce.setIndustrializationIndex(provinceUsage.getIndustrializationIndex());
            provinceProduce.setMainTotal(provinceUsage.getMainTotal());
            provinceProduce.setFertilising(provinceUsage.getFertilizer());
            provinceProduce.setForage(provinceUsage.getFeed());
            provinceProduce.setFuel(provinceUsage.getFuel());
            provinceProduce.setBase(provinceUsage.getBasic());
            provinceProduce.setMaterial(provinceUsage.getRawMaterial());

            ReturnLeaveSumVo returnLeaveSumVo = new ReturnLeaveSumVo();
            if (CollectionUtils.isNotEmpty(returnLeaveSumVoList)) {
                returnLeaveSumVo = returnLeaveSumVoList.get(0);
            }
            //临时存放数据
            map.put("produceTemp", provinceProduce.getTheoryResource());
            map.put("collectedTemp", provinceProduce.getCollectResource());

            map.put("produce", BigDecimalUtil.getTenThousand(provinceProduce.getTheoryResource()).setScale(2, RoundingMode.HALF_UP) + "万吨");               //产生量
            map.put("collected", BigDecimalUtil.getTenThousand(provinceProduce.getCollectResource()).setScale(2, RoundingMode.HALF_UP) + "万吨");            //可收集量
            map.put("prostill", BigDecimalUtil.getTenThousand(provinceProduce.getProStrawUtilize()).setScale(2, RoundingMode.HALF_UP) + "万吨");             //秸秆利用量
            map.put("comRatio", provinceProduce.getComprehensive().setScale(2, RoundingMode.HALF_UP) + "%");                         //综合利用率
            map.put("compr_util_index", provinceProduce.getIndustrializationIndex().setScale(2, RoundingMode.HALF_UP));  //产业化利用能力指数
            map.put("return", BigDecimalUtil.getTenThousand(returnLeaveSumVo.getProStillField()).setScale(2, RoundingMode.HALF_UP) + "万吨");                 //直接还田量
            map.put("returnPer", returnLeaveSumVo.getReturnRatio().setScale(2, RoundingMode.HALF_UP) + "%");
            map.put("marketUtilization", BigDecimalUtil.getTenThousand(returnLeaveSumVo.getMainTotal()).setScale(2, RoundingMode.HALF_UP) + "万吨");          //市场规模化利用量
            map.put("marketUtilizationPer", returnLeaveSumVo.getMainTotal().divide(returnLeaveSumVo.getCollectResource(), 2).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP) + "%");  //占比
            map.put("peasantDis", BigDecimalUtil.getTenThousand(returnLeaveSumVo.getDisperseTotal()).setScale(2, RoundingMode.HALF_UP) + "万吨");             //分散利用量
            map.put("peasantDisPer", returnLeaveSumVo.getDisperseTotal().divide(returnLeaveSumVo.getCollectResource(), 2).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP) + "%");  //占比

            //【还田离田量】柱状图数据准备
            Map<String, Map<String, Double>> returnAndLeaveMap = new HashMap<>();
            Map<String, Double> mapData = new HashMap<>();
            mapData.put("直接还田量", BigDecimalUtil.getTenThousand(returnLeaveSumVo.getProStillField()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            mapData.put("市场主体规模化利用量", BigDecimalUtil.getTenThousand(provinceProduce.getMainTotal()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            mapData.put("分散利用量", BigDecimalUtil.getTenThousand(returnLeaveSumVo.getDisperseTotal()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            returnAndLeaveMap.put("", mapData);
            //柱状图
            byte[] returnPicStr = barChart("", returnAndLeaveMap, "还田离田量", "万吨", 500, 400);
            map.put("returnPic", new PictureRenderData(500, 400, ".png", returnPicStr));

            // 获取上一年度
            int lastYearInt = Integer.parseInt(year) - 1;
            queryVo.setYear(String.valueOf(lastYearInt));
            // 上一年度数据列表
            // List<StrawProduceUtilizeResVo2> listLast = aggregateService.findStrawProduceAndUtilzeData2(queryVo);
            List<ProductionUsageSum> lastList = productionUsageSumService.selectProductionUsageSum(Lists.newArrayList(queryVo.getAreaIds()), year, Lists.newArrayList("5"));
            // 上一年度还田离田量查询
            List<ReturnLeaveSumVo> returnLeaveSumVoListLast = aggregateService.findReturnLeaveSumData(queryVo);
            if (CollectionUtils.isNotEmpty(lastList)) {
                ReturnLeaveSumVo returnLeaveSumVoLast = new ReturnLeaveSumVo();
                if (CollectionUtils.isNotEmpty(returnLeaveSumVoListLast)) {
                    returnLeaveSumVoLast = returnLeaveSumVoListLast.get(0);
                }
                StrawProduceUtilizeResVo2 lastProduce = new StrawProduceUtilizeResVo2();
                if (CollectionUtils.isNotEmpty(lastList)) {
                    ProductionUsageSum sum = lastList.get(0);
                    lastProduce.setTheoryResource(sum.getProduce());
                    lastProduce.setCollectResource(sum.getCollect());
                    lastProduce.setComprehensive(sum.getComprehensiveRate());
                    lastProduce.setIndustrializationIndex(sum.getIndustrializationIndex());
                    lastProduce.setMainTotal(sum.getMainTotal());
                    lastProduce.setFertilising(sum.getFertilizer());
                    lastProduce.setForage(sum.getFeed());
                    lastProduce.setFuel(sum.getFuel());
                    lastProduce.setBase(sum.getBasic());
                    lastProduce.setMaterial(sum.getRawMaterial());
                }

                BigDecimal lastComprehensice = lastProduce.getComprehensive();
                //比较数据
                map.put("comRatioCompare", provinceProduce.getComprehensive().compareTo(lastComprehensice) > 0 ? "增长" :
                        provinceProduce.getComprehensive().compareTo(lastComprehensice) == 0 ? "持平" : "降低");

                map.put("crcPer", provinceProduce.getComprehensive().compareTo(lastComprehensice) > 0 ? provinceProduce.getComprehensive().intValue() - lastComprehensice.intValue() :
                        lastComprehensice.intValue() - provinceProduce.getComprehensive().intValue());

                BigDecimal lastComprehensiceIndex = lastProduce.getIndustrializationIndex();
                //产业化利用能力指数比较数据
                map.put("indexCompare", provinceProduce.getIndustrializationIndex().compareTo(lastComprehensiceIndex) > 0 ? "增长" :
                        provinceProduce.getIndustrializationIndex().compareTo(lastComprehensiceIndex) == 0 ? "持平" : "降低");

                map.put("indexPer", provinceProduce.getIndustrializationIndex().compareTo(lastComprehensiceIndex) > 0 ? provinceProduce.getIndustrializationIndex().intValue() - lastComprehensiceIndex.intValue() :
                        lastComprehensiceIndex.intValue() - provinceProduce.getIndustrializationIndex().intValue());

                //直接还田量比较数据
                BigDecimal lastReturn = returnLeaveSumVoLast.getProStillField();
                map.put("returnCompare", returnLeaveSumVo.getReturnRatio().compareTo(returnLeaveSumVoLast.getReturnRatio()) > 0 ? "增长" :
                        returnLeaveSumVo.getReturnRatio().compareTo(returnLeaveSumVoLast.getReturnRatio()) == 0 ? "持平" : "降低");

                map.put("rcPer", returnLeaveSumVo.getReturnRatio().compareTo(returnLeaveSumVoLast.getReturnRatio()) > 0 ? returnLeaveSumVo.getReturnRatio().intValue() - returnLeaveSumVoLast.getReturnRatio().intValue() :
                        returnLeaveSumVoLast.getReturnRatio().intValue() - returnLeaveSumVo.getReturnRatio().intValue());

                //市场主体规模化利用量比较数据
                BigDecimal lastMainMarket = returnLeaveSumVoLast.getMainTotal();
                map.put("marketUtilizationCompare", returnLeaveSumVo.getMainTotal().compareTo(lastMainMarket) > 0 ? "增长" :
                        returnLeaveSumVo.getMainTotal().compareTo(lastMainMarket) == 0 ? "持平" : "降低");

                BigDecimal lastMainMartketPerc = lastProduce.getCollectResource().compareTo(new BigDecimal(0)) == 0 ? new BigDecimal(0) : lastMainMarket.divide(lastProduce.getCollectResource(), 2, RoundingMode.HALF_UP);
                BigDecimal recentMarketPerc = provinceProduce.getCollectResource().compareTo(new BigDecimal(0)) == 0 ? new BigDecimal(0) : returnLeaveSumVo.getMainTotal().divide(provinceProduce.getCollectResource(), 2, RoundingMode.HALF_UP);

                //计算百分比差
                map.put("mucPer", returnLeaveSumVo.getMainTotal().compareTo(lastMainMarket) > 0 ? recentMarketPerc.intValue() - lastMainMartketPerc.intValue() :
                        lastMainMartketPerc.intValue() - recentMarketPerc.intValue());

                //分散利用量比较数据
                //分散利用量占收集量的比例
                BigDecimal dcpPer = returnLeaveSumVo.getCollectResource().compareTo(new BigDecimal(0)) > 0 ?
                        returnLeaveSumVo.getDisperseTotal().divide(returnLeaveSumVo.getCollectResource(), 2) : new BigDecimal(0);
                //分散利用量占收集量的比例
                BigDecimal dcpLastPer = returnLeaveSumVoLast.getCollectResource().compareTo(new BigDecimal(0)) > 0 ?
                        returnLeaveSumVoLast.getDisperseTotal().divide(returnLeaveSumVoLast.getCollectResource(), 2) : new BigDecimal(0);
                //BigDecimal lastDisperseTotal= returnLeaveSumVoLast.getDisperseTotal();
                map.put("peasantDisCompare", dcpPer.compareTo(dcpLastPer) > 0 ? "增长" :
                        dcpPer.compareTo(dcpLastPer) == 0 ? "持平" : "降低");

                map.put("peasantDisComparePer", dcpPer.compareTo(dcpLastPer) > 0 ? dcpPer.intValue() - dcpLastPer.intValue() :
                        dcpLastPer.intValue() - dcpPer.intValue());

                BigDecimal fieldLeave = returnLeaveSumVo.getMainTotal().add(returnLeaveSumVo.getDisperseTotal());
                map.put("fieldLeave", BigDecimalUtil.getTenThousand(fieldLeave).setScale(2, RoundingMode.HALF_UP) + "万吨");
                map.put("fieldLeavePer", provinceProduce.getCollectResource().compareTo(new BigDecimal(0)) == 0 ? 0
                        : fieldLeave.divide(provinceProduce.getCollectResource(), 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP) + "%");

            } else {
                map.put("comRatioCompare", "增长");
                map.put("crcPer", provinceProduce.getComprehensive().intValue());
                //产业化利用能力指数比较数据
                map.put("indexCompare", "增长");
                map.put("indexPer", provinceProduce.getIndustrializationIndex().intValue());

                map.put("returnCompare", "增长");
                map.put("rcPer", returnLeaveSumVo.getReturnRatio().intValue());
            }
            //肥料化需要特殊处理，加上直接还田量
            map.put("fertilize", BigDecimalUtil.getTenThousand(provinceProduce.getFertilising()).setScale(2, RoundingMode.HALF_UP) + "万吨");
            map.put("feed", BigDecimalUtil.getTenThousand(provinceProduce.getForage()).setScale(2, RoundingMode.HALF_UP) + "万吨");
            map.put("fuelled", BigDecimalUtil.getTenThousand(provinceProduce.getFuel()).setScale(2, RoundingMode.HALF_UP) + "万吨");
            map.put("materialization", BigDecimalUtil.getTenThousand(provinceProduce.getMaterial()).setScale(2, RoundingMode.HALF_UP) + "万吨");
            map.put("base_mat", BigDecimalUtil.getTenThousand(provinceProduce.getBase()).setScale(2, RoundingMode.HALF_UP) + "万吨");

            map.put("fertilizePer", provinceProduce.getFertilising().divide(provinceProduce.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");
            map.put("feedPer", provinceProduce.getForage().divide(provinceProduce.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");
            map.put("fuelledPer", provinceProduce.getFuel().divide(provinceProduce.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");
            map.put("materializationPer", provinceProduce.getMaterial().divide(provinceProduce.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");
            map.put("base_matPer", provinceProduce.getBase().divide(provinceProduce.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");

            //【五料化】饼状图数据准备
            Map<String, Double> wuliaoMap = new HashMap<>();
            wuliaoMap.put("肥料化", BigDecimalUtil.getTenThousand(provinceProduce.getFertilising()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            wuliaoMap.put("饲料化", BigDecimalUtil.getTenThousand(provinceProduce.getForage()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            wuliaoMap.put("燃料化", BigDecimalUtil.getTenThousand(provinceProduce.getFuel()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            wuliaoMap.put("原料化", BigDecimalUtil.getTenThousand(provinceProduce.getMaterial()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            wuliaoMap.put("基料化", BigDecimalUtil.getTenThousand(provinceProduce.getBase()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            BigDecimal unUsed = provinceProduce.getCollectResource()
                    .subtract(provinceProduce.getFertilising())
                    .subtract(provinceProduce.getForage())
                    .subtract(provinceProduce.getFuel())
                    .subtract(provinceProduce.getMaterial())
                    .subtract(provinceProduce.getBase());

            wuliaoMap.put("未利用", BigDecimalUtil.getTenThousand(unUsed).setScale(2, RoundingMode.HALF_UP).doubleValue());
            //生成【五料化】饼状图
            byte[] wuliaohuaPiePicStr = pieChart("农作物秸秆利用构成", wuliaoMap, 400, 400);
            map.put("wuliaohuaPiePic", new PictureRenderData(500, 400, ".png", wuliaohuaPiePicStr));

        } else {
            map.put("produce", new BigDecimal(0));        //产生量
            map.put("collected", new BigDecimal(0));        //可收集量
            map.put("prostill", new BigDecimal(0));        //秸秆利用量
            map.put("comRatio", new BigDecimal(0));        //综合利用率
        }

        //----------------------------------------按区划分类统计-------------------------------------------------------//
        //获取按区划归类产生量排序列表
        queryVo.setYear(year);
        List<StrawProduceUtilizeResVo2> proListTemp = aggregateService.findStrawProduceAndUtilzeData2(queryVo);
        if (CollectionUtils.isNotEmpty(proListTemp)) {
            //去除省级中合计部分
            List<StrawProduceUtilizeResVo2> proList = proListTemp.subList(1, proListTemp.size());
            proList = proList.stream().sorted(Comparator.comparing(StrawProduceUtilizeResVo2::getTheoryResource).reversed()).collect(Collectors.toList());
            map.put("No1ProArea", proList.get(0).getAreaName());          //产生量第一区县名
            map.put("produceNo1", BigDecimalUtil.getTenThousand(proList.get(0).getTheoryResource()).setScale(2, RoundingMode.HALF_UP) + "万吨");   //第一产生量
            map.put("proPer", proList.get(0).getTheoryResource().divide((BigDecimal) (map.get("produceTemp")), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)) + "%");   //占比
            //【产生量】柱状图数据准备
            Map<String, Map<String, Double>> datas = new HashMap<>();
            //按插入顺序显示
            Map<String, Double> mapData = new LinkedHashMap<>();
            for (StrawProduceUtilizeResVo2 res : proList) {
                mapData.put(res.getAreaName(), BigDecimalUtil.getTenThousand(res.getTheoryResource()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
            datas.put("", mapData);
            //柱状图转为base64
            byte[] producePicStr = barChart("", datas, "秸秆产生量", "秸秆产生量(吨)", 500, 450);
            map.put("producePic", new PictureRenderData(500, 400, ".png", producePicStr));

            ////////////////////////////////////////////////////////////////////////////
            /////////////--------重新按照可收集量排序---------//////////////////////////////
            ////////////////////////////////////////////////////////////////////////////
            proList = proList.stream().sorted(Comparator.comparing(StrawProduceUtilizeResVo2::getCollectResource).reversed()).collect(Collectors.toList());
            map.put("No1CollectedArea", proList.get(0).getAreaName());    //可收集量第一区县名
            map.put("collectedNo1", BigDecimalUtil.getTenThousand(proList.get(0).getCollectResource()).setScale(2, RoundingMode.HALF_UP) + "万吨");             //第一可收集量
            map.put("collectPer", proList.get(0).getCollectResource().divide((BigDecimal) map.get("collectedTemp"), 2, RoundingMode.HALF_DOWN).multiply(new BigDecimal(100)) + "%");
            //【可收集量】柱状图数据准备
            Map<String, Map<String, Double>> datasCollect = new HashMap<>();
            Map<String, Double> collectdeMap = new LinkedHashMap();
            for (int i = 0; i < proList.size(); i++) {

                collectdeMap.put(proList.get(i).getAreaName(), BigDecimalUtil.getTenThousand(proList.get(i).getCollectResource()).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
            datasCollect.put("", collectdeMap);
            //柱状图转为base64
            byte[] collectedPicStr = barChart("", datasCollect, "秸秆可收集量", "秸秆可收集量(吨)", 500, 400);
            map.put("collectPic", new PictureRenderData(500, 400, ".png", collectedPicStr));

            ////////////////////////////////////////////////////////////////////////////
            /////////////--------重新按照综合利用率排序---------////////////////////////////
            ////////////////////////////////////////////////////////////////////////////
            proList = proList.stream().sorted(Comparator.comparing(StrawProduceUtilizeResVo2::getComprehensive).reversed()).collect(Collectors.toList());
            map.put("top5comRatioArea", proList.get(0).getAreaName());    //综合利用率第一区县名
            map.put("top5comRatio", proList.get(0).getComprehensive().setScale(2, RoundingMode.HALF_UP) + "%");   //第一综合利用率
            //【综合利用率】柱状图数据准备
            Map<String, Map<String, Double>> datasComprehensive = new HashMap<>();
            Map<String, Double> comprehensiveMap = new LinkedHashMap<>();
            for (StrawProduceUtilizeResVo2 res : proList) {
                comprehensiveMap.put(res.getAreaName(), res.getComprehensive().setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
            datasComprehensive.put("", comprehensiveMap);
            //柱状图
            byte[] comRatioAreaPicStr = barChart("", datasComprehensive, "秸秆综合利用率", "秸秆综合利用率(%)", 500, 400);
            map.put("comRatioAreaPic", new PictureRenderData(500, 400, ".png", comRatioAreaPicStr));

            ////////////////////////////////////////////////////////////////////////////
            /////////////--------重新按照产业化利用能力指数排序---------////////////////////////////
            ////////////////////////////////////////////////////////////////////////////
            proList = proList.stream().sorted(Comparator.comparing(StrawProduceUtilizeResVo2::getIndustrializationIndex).reversed()).collect(Collectors.toList());
            map.put("top1IndexArea", proList.get(0).getAreaName());    //产业化利用能力指数第一区县名
            map.put("top1Index", proList.get(0).getIndustrializationIndex().setScale(2, RoundingMode.HALF_UP));   //第一产业化利用能力指数
            //【产业化利用能力指数】柱状图数据准备
            Map<String, Map<String, Double>> IndexDatas = new HashMap<>();
            Map<String, Double> ComprehensiveIndexMap = new LinkedHashMap<>();
            for (StrawProduceUtilizeResVo2 res : proList) {
                ComprehensiveIndexMap.put(res.getAreaName(), res.getIndustrializationIndex().doubleValue());
            }
            IndexDatas.put("", ComprehensiveIndexMap);
            //柱状图转为base64
            byte[] indexPicStr = barChart("", IndexDatas, "", "产业化利用能力指数", 500, 400);
            map.put("indexPic", new PictureRenderData(500, 400, ".png", indexPicStr));
        }

        //-----------------------------------------------按作物类型归类------------------------------------------//

        //获取按作物类型归类产生量
        List<StrawProduceResVo2> sprvListTemp = aggregateService.getStrawProduceData2(queryVo);
        if (CollectionUtils.isNotEmpty(sprvListTemp)) {
            //去除省级中合计部分
            List<StrawProduceResVo2> sprvList = sprvListTemp.subList(1, sprvListTemp.size());
            //根据作物中的产生量降序排序
            sprvList = sprvList.stream().sorted(Comparator.comparing(StrawProduceResVo2::getTheoryResource).reversed()).collect(Collectors.toList());
            if (sprvList.size() >= 3) { //若填写了3种以上作物，则选前三(作物名，产生量、产生量占比)
                String top3Sprv = sprvList.get(0).getStrawName() + "、" + sprvList.get(1).getStrawName() + "和" + sprvList.get(2).getStrawName();
                String top3SprvPro = BigDecimalUtil.getTenThousand(sprvList.get(0).getTheoryResource()).setScale(2, RoundingMode.HALF_UP) + "万吨" + "、"
                        + BigDecimalUtil.getTenThousand(sprvList.get(1).getTheoryResource()).setScale(2, RoundingMode.HALF_UP) + "万吨" + "和"
                        + BigDecimalUtil.getTenThousand(sprvList.get(2).getTheoryResource()).setScale(2, RoundingMode.HALF_UP) + "万吨";
                String top3SprvProPer = sprvList.get(0).getTheoryResourceRate() + "%" + "、" + sprvList.get(1).getTheoryResourceRate() + "%" + "和" + sprvList.get(2).getTheoryResourceRate() + "%";
                map.put("top3crop", top3Sprv);             //产生量前三的作物名
                map.put("top3Pro", top3SprvPro);           //前三产生量
                map.put("top3ProPercent", top3SprvProPer); //前三产生量占比
            } else {  //否则，选择一种即可
                String top3Sprv = sprvList.get(0).getStrawName();
                String top3SprvPro = BigDecimalUtil.getTenThousand(sprvList.get(0).getTheoryResource()).setScale(2, RoundingMode.HALF_UP) + "万吨";
                String top3SprvProPer = sprvList.get(0).getTheoryResourceRate() + "%";
                map.put("top3crop", top3Sprv);             //作物名
                map.put("top3Pro", top3SprvPro);           //产生量
                map.put("top3ProPercent", top3SprvProPer); //产生量占比
            }
            //【产生量】饼状图数据准备
            Map<String, Double> sprvMap = new HashMap<>();
            for (int j = 0; j < sprvList.size(); j++) {
                sprvMap.put(sprvList.get(j).getStrawName(), BigDecimalUtil.getTenThousand(sprvList.get(j).getTheoryResource()).doubleValue());
            }
            //生成饼状图
            byte[] sprvPicStr = pieChart("产生量占比情况", sprvMap, 500, 400);
            map.put("proPerPic", new PictureRenderData(500, 400, ".png", sprvPicStr));
            ////////////////////////////////////////////////////////////////////////////
            /////////////--------重新按照可收集量排序---------//////////////////////////////
            ////////////////////////////////////////////////////////////////////////////
            sprvList = sprvList.stream().sorted(Comparator.comparing(StrawProduceResVo2::getCollectResource).reversed()).collect(Collectors.toList());
            String Collectedtop1Crop = sprvList.get(0).getStrawName();
            String top1CropCollect = BigDecimalUtil.getTenThousand(sprvList.get(0).getCollectResource()).setScale(2, RoundingMode.HALF_UP) + "万吨";
            String collectCropPer = sprvList.get(0).getCollectResourceRate() + "%";
            map.put("collectedtop1Crop", Collectedtop1Crop);
            map.put("top1CropCollect", top1CropCollect);
            map.put("collectCropPer", collectCropPer);
            //【可收集量】饼状图数据准备
            Map<String, Double> sprvCollectMap = new LinkedHashMap<>();
            for (int j = 0; j < sprvList.size(); j++) {
                sprvCollectMap.put(sprvList.get(j).getStrawName(), BigDecimalUtil.getTenThousand(sprvList.get(j).getCollectResource()).doubleValue());
            }
            //生成【可收集量】饼状图
            byte[] collectedPerPicStr = pieChart("可收集量占比情况", sprvCollectMap, 500, 400);
            map.put("collectedPerPic", new PictureRenderData(500, 400, ".png", collectedPerPicStr));
        }
        //ArrayList<String> status = new ArrayList<>();
        // status.add(AuditStatusEnum.PASSED.getCode());
        //获取按作物分类计算得到的综合利用率列表
        List<StrawUtilizeResVo3> comRatioListTemp = aggregateService.getStrawUtilzeData2(queryVo);
        if (CollectionUtils.isNotEmpty(comRatioListTemp)) {
            //去除合计部分
            List<StrawUtilizeResVo3> comRatioList = comRatioListTemp.subList(1, comRatioListTemp.size());
            comRatioList = comRatioList.stream().sorted(Comparator.comparing(StrawUtilizeResVo3::getComprehensiveRate).reversed()).collect(Collectors.toList());
            map.put("top3comRatioCrop", comRatioList.get(0).getStrawName());
            map.put("top3CropcomRatio", comRatioList.get(0).getComprehensiveRate().setScale(2, RoundingMode.HALF_UP) + "%");
            //【综合利用率】柱状图数据准备
            Map<String, Map<String, Double>> comRatioDatas = new HashMap<>();
            Map<String, Double> comprehensiveMap = new LinkedHashMap<>();
            for (StrawUtilizeResVo3 res : comRatioList) {
                comprehensiveMap.put(res.getStrawName(), res.getComprehensiveRate().setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
            comRatioDatas.put("", comprehensiveMap);
            //柱状图
            byte[] comRatioCropPicStr = barChart("", comRatioDatas, "各作物秸秆综合利用率", "秸秆综合利用率(%)", 500, 400);
            map.put("comRatioCropPic", new PictureRenderData(500, 400, ".png", comRatioCropPicStr));
        }

        //从事秸秆利用的市场主体个数
        int countMarket = strawUtilizeService.countCompanyByYearAndProvince(year, provinceId);
        //超过1W的
        // int countAbove1 = strawUtilizeDetailService.getCountByCondition(year, provinceId, 10000);
        //超过5W的
        // int countAbove5 = strawUtilizeDetailService.getCountByCondition(year, provinceId, 50000);
        //超过10W的
        // int countAbove10 = strawUtilizeDetailService.getCountByCondition(year, provinceId, 100000);

        // 一次查询
        List<StrawUtilize> marketList = strawUtilizeDetailService.getCountByConditionV2(year, provinceId);
        long countAbove1 = marketList.stream().filter(e -> e.getMarketEnt().compareTo(new BigDecimal("10000")) > 0).count();
        long countAbove5 = marketList.stream().filter(e -> e.getMarketEnt().compareTo(new BigDecimal("50000")) > 0).count();
        long countAbove10 = marketList.stream().filter(e -> e.getMarketEnt().compareTo(new BigDecimal("100000")) > 0).count();

        map.put("countMarket", String.valueOf(countMarket));                     //从straw_utilize表中统计得到
        map.put("countAbove1", countAbove1 - countAbove5);                      //从straw_utilize_detail中计算得到
        map.put("countAbove5", countAbove5 - countAbove10);
        map.put("countAbove10", countAbove10);
        map.put("countAbove1Per", countMarket == 0 ? 0 : (countAbove1 - countAbove5) / countMarket);
        map.put("countAbove5Per", countMarket == 0 ? 0 : (countAbove5 - countAbove10) / countMarket);
        map.put("countAbove10Per", countMarket == 0 ? 0 : (countAbove10) / countMarket);

        return map;
    }

    @Override
    public boolean deleteOldProReport(String provinceId, String year) {
        //根据信息查询省级报告文件
        PageUtils<SysFileManageVo> filePageList = sysFileApi.getSysFileByPage(0, 10, year + "_proReport_" + provinceId, "ducss", null, null, null, null, null).getData();
        //获取文件信息
        List<SysFileManageVo> fileList = filePageList.getList();
        if (fileList.isEmpty() == false) {
            if (StringUtils.isNotBlank(fileList.get(0).getId())) {
                SysFileManageVo file = sysFileApi.getOne(fileList.get(0).getId()).getData();
                if (file.getFileSize() > 0) {
                    //删除文件服务器中的旧文件
                    sysFileApi.delFile(fileList.get(0).getId());
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * 将图片转化为字节数组
     *
     * @return 字节数组
     */
    private static byte[] imgToByte(String filePath) {
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
     * @Description //饼状图
     * @Date 10:12 2020/11/24
     * @Param [title-图标题, datas-显示数据, width-所保存饼状图的宽, height-所保存饼状图的高]
     * @Return base64 String
     **/
    private byte[] pieChart(String title, Map<String, Double> datas, int width, int height) {

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(getFont(Font.BOLD, 20f));
        //设置图例的字体
        standardChartTheme.setRegularFont(getFont(Font.PLAIN, 15f));
        //设置轴向的字体
        standardChartTheme.setLargeFont(getFont(Font.PLAIN, 15f));
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
        plot.setShadowPaint(new Color(255, 255, 255));
        //设置标签生成器(默认{0})
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({2})"));
        String filePath = formCallbackConfig.getDir() + "/" + (int) (Math.random() * 100000) + "ducss.png";
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
     *
     * @param title  标题
     * @param datas  数据
     * @param type   分类（第一季，第二季.....）
     * @param danwei 柱状图的数量单位
     */
    private byte[] barChart(String title, Map<String, Map<String, Double>> datas, String type, String danwei, int width, int height) {
        //种类数据集
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        //获取迭代器：
        Set<Map.Entry<String, Map<String, Double>>> set1 = datas.entrySet();    //总数据
        Iterator iterator1 = set1.iterator();                        //第一次迭代
        Iterator iterator2 = null;
        HashMap<String, Double> map = null;
        Set<Map.Entry<String, Double>> set2 = null;
        Map.Entry entry1 = null;
        Map.Entry entry2 = null;

        while (iterator1.hasNext()) {
            entry1 = (Map.Entry) iterator1.next();                    //遍历分类

            map = (HashMap<String, Double>) entry1.getValue();   //得到每次分类的详细信息
            set2 = map.entrySet();                               //获取键值对集合
            iterator2 = set2.iterator();                         //再次迭代遍历
            while (iterator2.hasNext()) {
                entry2 = (Map.Entry) iterator2.next();
                ds.setValue(Double.parseDouble(entry2.getValue().toString()),//每次统计数量
                        entry2.getKey().toString(),                          //名称
                        entry1.getKey().toString());                         //分类
            }
        }

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(getFont(Font.BOLD, 20f));
        //设置图例的字体
        standardChartTheme.setRegularFont(getFont(Font.PLAIN, 15f));
        //设置轴向的字体
        standardChartTheme.setLargeFont(getFont(Font.PLAIN, 15f));
        //设置主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        //创建柱状图,柱状图分水平显示和垂直显示两种
        JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true, true, true);

        //得到绘图区
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        LegendTitle legendTitle = new LegendTitle(plot);//创建图例
        legendTitle.setPosition(RectangleEdge.TOP); //设置图例的位置
        plot.setForegroundAlpha(1.0f);

        String filePath = formCallbackConfig.getDir() + "/" + (int) (Math.random() * 100000) + "ducss.png";
        //String filePath = templateFolder + (int) (Math.random() * 100000) + "ducss.png";
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgToByte(filePath);
    }


}
