/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:35
 */
package com.sofn.ducss.service.impl;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.mapper.DateShowMapper;
import com.sofn.ducss.mapper.ReportDataMapper;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.service.ReportCollectionResourceValueService;
import com.sofn.ducss.service.ReportInfoService;
import com.sofn.ducss.service.ReportProductValueService;
import com.sofn.ducss.service.ReportService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.JfreeUtil;
import com.sofn.ducss.util.SysDictUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.DateShow.EntitySortVo;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import com.sofn.ducss.vo.report.FiveMaterialPercentVo;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private DateShowMapper dateShowMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private ReportProductValueService reportProductValueService;

    @Autowired
    private ReportCollectionResourceValueService reportCollectionResourceValueService;

    @Autowired
    private ReportInfoService reportInfoService;


    @Autowired
    private FormCallbackConfig formCallbackConfig;

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
    public Map<String, Object> getMajorYieldReport(String year, List<String> areaCodes, String strawType) {
        //玉米秸秆产生量
        //若不输入年度,则取当前年的上一年度
        /*if (StringUtils.isEmpty(year) || !StringUtils.isNumeric(year)) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
        }*/
        //获取数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //获取全国省份
        // List<String> areaCodes = new ArrayList<>();
        //获取当前区域下一级areacode
        /*Result<List<SysRegionTreeVo>> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (regionTree.getData().size() > 0) {
            for (SysRegionTreeVo treeDatum : regionTree.getData()) {
                areaCodes.add(treeDatum.getRegionCode());
            }
        } else {
            throw new SofnException("下级区域异常");
        }*/
        //String strawType = "";
        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>();
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        //获取各省单作物数据
        List<StrawUtilizeSum> list = reportDataMapper.getTheoryResourceByStrawType(year, areaCodes, status, strawType, "theory_resource");
        //获取全国单作物数据
        StrawUtilizeSum theoryResource = reportDataMapper.getSumTheoryResourceByStrawType(year, areaCodes, status, strawType, "theory_resource");
        //查询全国所有作物数据
        StrawUtilizeSumResVo sumResVo = reportDataMapper.getSumStrawUtilizeByAreaCode(year, areaCodes, status);
        //获取区域名称map
        Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodes), year);
        /*-----------六大区数据----------------*/
        //华北区
        List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
        //长江区
        List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
        //东北区
        List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
        //西北区
        List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
        //西南区
        List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
        //华南区
        List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));

        List<StrawUtilizeSum> sumArrayList = new ArrayList<>();
        //华北区数据
        StrawUtilizeSum north_region_sum = dateShowMapper.getStrawSum(year, north_region_list, status, strawType);
        north_region_sum.setAreaName(SixRegionEnum.NORTH_REGION.getDescription());
        sumArrayList.add(north_region_sum);
        StrawUtilizeSum chang_jiang_river_sum = dateShowMapper.getStrawSum(year, chang_jiang_river_region_list, status, strawType);
        chang_jiang_river_sum.setAreaName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
        sumArrayList.add(chang_jiang_river_sum);
        StrawUtilizeSum northeast_region_sum = dateShowMapper.getStrawSum(year, northeast_region_list, status, strawType);
        northeast_region_sum.setAreaName(SixRegionEnum.NORTHEAST_REGION.getDescription());
        sumArrayList.add(northeast_region_sum);
        StrawUtilizeSum northwest_region_sum = dateShowMapper.getStrawSum(year, northwest_region_list, status, strawType);
        northwest_region_sum.setAreaName(SixRegionEnum.NORTHWEST_REGION.getDescription());
        sumArrayList.add(northwest_region_sum);
        StrawUtilizeSum southwest_region_sum = dateShowMapper.getStrawSum(year, southwest_region_list, status, strawType);
        southwest_region_sum.setAreaName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
        sumArrayList.add(southwest_region_sum);
        StrawUtilizeSum south_region_sum = dateShowMapper.getStrawSum(year, south_region_list, status, strawType);
        south_region_sum.setAreaName(SixRegionEnum.SOUTH_REGION.getDescription());
        sumArrayList.add(south_region_sum);
        //排序
        sumArrayList.sort(new Comparator<StrawUtilizeSum>() {
            @Override
            public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                return o2.getTheoryResource().compareTo(o1.getTheoryResource());
            }
        });
        //获取全国数据
        Map<String, Object> map = new HashMap<>(12);
        //替换作物类型为中文
        strawType = strawTypeMap.get(strawType);
        /*-------------填充数据区域-----------------*/
        if (list != null && list.size() > 0 && theoryResource != null && sumResVo != null) {
            /*---------------产生量------------------*/
            map.put("crop", strawType);
            map.put("year", year);
            map.put("provinceNum", String.valueOf(list.size()));
            map.put("theoryResource", theoryResource.getTheoryResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //占全国秸秆产生量
            map.put("theoryResourcePencent", theoryResource.getTheoryResource().divide(sumResVo.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            /*------------产生量分省来看-------------------*/
            map.put("provinceOne", mapsByCodes.get(list.get(0).getAreaId()));
            map.put("cropOnetheoryResource", list.get(0).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("cropOnetheoryResourcePercent", list.get(0).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceTwo", mapsByCodes.get(list.get(1).getAreaId()));
            map.put("provinceThree", mapsByCodes.get(list.get(2).getAreaId()));
            map.put("provinceTwoTheoryResource", list.get(1).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceThreeTheoryResource", list.get(2).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceTwoTheoryResourcePercent", list.get(1).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceThreeTheoryResourcePercent", list.get(2).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceFour", mapsByCodes.get(list.get(3).getAreaId()));
            map.put("provinceFive", mapsByCodes.get(list.get(4).getAreaId()));
            map.put("provinceSix", mapsByCodes.get(list.get(5).getAreaId()));
            map.put("provinceSixTheoryResource", list.get(5).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceFourTheoryResource", list.get(3).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceFourTheoryResourcePercent", list.get(3).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceFiveTheoryResourcePercent", list.get(4).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceSixTheoryResourcePercent", list.get(5).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceSeven", mapsByCodes.get(list.get(6).getAreaId()));
            map.put("provinceEight", mapsByCodes.get(list.get(7).getAreaId()));
            map.put("provinceNine", mapsByCodes.get(list.get(8).getAreaId()));
            map.put("provinceTen", mapsByCodes.get(list.get(9).getAreaId()));
            map.put("provinceTenTheoryResource", list.get(9).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceSevenTheoryResource", list.get(6).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            /*---------全国各省区玉米秸秆产生量占比柱图------------*/
            Map<String, Map<String, Double>> theoryResourceMap = new HashMap<>(12);
            Map<String, Double> mapData = new LinkedHashMap<>(12);
            for (StrawUtilizeSum sum : list) {
                String s = mapsByCodes.get(sum.getAreaId());
                mapData.put(mapsByCodes.get(sum.getAreaId()), sum.getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            theoryResourceMap.put("全国各省区" + strawType + "秸秆产生量占比", mapData);
            byte[] pictureOne = barChart("全国各省区" + strawType + "秸秆产生量占比", theoryResourceMap, "秸秆产生量占比", "%", 500, 400);
            map.put("pictureTwo", new PictureRenderData(500, 400, ".png", pictureOne));

            /*--------------产生量分区来看-----------------*/
            map.put("sixRegionOne", sumArrayList.get(0).getAreaName());
            map.put("sixRegionOneTheoryResource", sumArrayList.get(0).getTheoryResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            map.put("sixRegionOneTheoryResourcePercent", sumArrayList.get(0).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionTwo", sumArrayList.get(1).getAreaName());
            map.put("sixRegionTwoTheoryResource", sumArrayList.get(1).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("sixRegionTwoTheoryResourcePercent", sumArrayList.get(1).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionThree", sumArrayList.get(2).getAreaName() + "、" + sumArrayList.get(3).getAreaName() + "、" + sumArrayList.get(4).getAreaName());
            map.put("sixRegionThreeTheoryResource", sumArrayList.get(4).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("sixRegionFiveTheoryResource", sumArrayList.get(2).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("sixRegionThreeTheoryResourcePercent", sumArrayList.get(2).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionFourTheoryResourcePercent", sumArrayList.get(3).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionFiveTheoryResourcePercent", sumArrayList.get(4).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionSix", sumArrayList.get(5).getAreaName());
            map.put("sixRegionSixTheoryResource", sumArrayList.get(5).getTheoryResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("sixRegionSixTheoryResourcePercent", sumArrayList.get(5).getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");

            /*---------全国六大区玉米秸秆产生量------------*/
            Map<String, Map<String, Double>> sixRegiontheoryResourceMap = new HashMap<>(12);
            Map<String, Double> sixRegionMapDataOne = new LinkedHashMap<>(12);
            Map<String, Double> sixRegionMapDataTwo = new LinkedHashMap<>(12);
            for (StrawUtilizeSum sum : sumArrayList) {
                sixRegionMapDataOne.put(sum.getAreaName(), sum.getTheoryResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                sixRegionMapDataTwo.put(sum.getAreaName(), Double.parseDouble(sum.getTheoryResource().divide(theoryResource.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString()));
            }
            sixRegiontheoryResourceMap.put("全国六大区" + strawType + "秸秆产生量", sixRegionMapDataOne);
            byte[] pictureThree = barChart("全国六大区" + strawType + "秸秆产生量", sixRegiontheoryResourceMap, "秸秆产生量", "亿吨", 500, 400);
            map.put("pictureThree", new PictureRenderData(500, 400, ".png", pictureThree));

            /*---------全国六大区玉米秸秆产生量占比------------*/
            byte[] pictureFour = pieChart("全国六大区" + strawType + "秸秆产生量占比", sixRegionMapDataTwo, 500, 400);
            map.put("pictureFour", new PictureRenderData(500, 400, ".png", pictureFour));

            /*
             * 可收集量数据区域
             * */
            /*-------------可收集量分省来看-----------------*/
            map.put("cropCollectResource", theoryResource.getCollectResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //收集量占全国
            map.put("cropCollectResourcePercent", theoryResource.getCollectResource().divide(sumResVo.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            //根据可收集量排序
            list.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getCollectResource().compareTo(o1.getCollectResource());
                }
            });
            map.put("provinceCOne", mapsByCodes.get(list.get(0).getAreaId()));
            map.put("provinceCOneCollectResource", list.get(0).getCollectResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceCOneCollectResourcePercent", list.get(0).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceCTwo", mapsByCodes.get(list.get(1).getAreaId()));
            map.put("provinceCThree", mapsByCodes.get(list.get(2).getAreaId()));
            map.put("provinceCTwoCollectResource", list.get(1).getCollectResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceCThreeCollectResource", list.get(2).getCollectResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("provinceCTwoCollectResourcePercent", list.get(1).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceCThreeCollectResourcePercent", list.get(2).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("provinceCFour", mapsByCodes.get(list.get(3).getAreaId()));
            map.put("provinceCFive", mapsByCodes.get(list.get(4).getAreaId()));
            map.put("provinceCSix", mapsByCodes.get(list.get(5).getAreaId()));
            map.put("provinceCSeven", mapsByCodes.get(list.get(6).getAreaId()));
            map.put("provinceCEight", mapsByCodes.get(list.get(7).getAreaId()));
            map.put("provinceCNine", mapsByCodes.get(list.get(8).getAreaId()));
            map.put("provinceCTen", mapsByCodes.get(list.get(9).getAreaId()));
            map.put("provinceCNum", 7);
            map.put("provinceCTenCollectResource", list.get(9).getCollectResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            /*----------图片5---------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : list) {
                mapData.put(mapsByCodes.get(sum.getAreaId()), sum.getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            theoryResourceMap.put("全国各省区" + strawType + "秸秆可收集量占比", mapData);
            byte[] pictureFive = barChart("全国各省区" + strawType + "可收集量占比", theoryResourceMap, "秸秆产生量占比", "%", 500, 400);
            map.put("pictureFive", new PictureRenderData(500, 400, ".png", pictureFive));

            /*----------收集量分区来看------------*/
            //根据可收集量排序六大区集合数据
            sumArrayList.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getCollectResource().compareTo(o1.getCollectResource());
                }
            });
            map.put("sixRegionCOne", sumArrayList.get(0).getAreaName());
            map.put("sixRegionCOneCollectResource", sumArrayList.get(0).getCollectResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            map.put("sixRegionCOnePercent", sumArrayList.get(0).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionCTwo", sumArrayList.get(1).getAreaName());
            map.put("sixRegionCTwoPercent", sumArrayList.get(1).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionCThree", sumArrayList.get(2).getAreaName());
            map.put("sixRegionCFour", sumArrayList.get(3).getAreaName());
            map.put("sixRegionCFive", sumArrayList.get(4).getAreaName());
            map.put("sixRegionCThreePercent", sumArrayList.get(2).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionCFourPercent", sumArrayList.get(3).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("sixRegionCFivePercent", sumArrayList.get(4).getCollectResource().divide(theoryResource.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");

            /*----------图片6-全国六大区玉米秸秆可收集量--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : sumArrayList) {
                mapData.put(sum.getAreaName(), sum.getCollectResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆可收集量", mapData);
            byte[] pictureSix = barChart("全国六大区" + strawType + "秸秆可收集量", theoryResourceMap, "秸秆可收集量", "亿吨", 500, 400);
            map.put("pictureSix", new PictureRenderData(500, 400, ".png", pictureSix));

            /*--------------秸秆利用情况-----------------*/
            map.put("proStrawUtilize", theoryResource.getProStrawUtilize().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            map.put("fertilising", (theoryResource.getMainFertilising().add(theoryResource.getDisperseFertilising()).add(theoryResource.getProStillField())).divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            map.put("forage", (theoryResource.getMainForage().add(theoryResource.getDisperseForage())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("fuel", (theoryResource.getMainFuel().add(theoryResource.getDisperseFuel())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("base", (theoryResource.getMainBase().add(theoryResource.getDisperseBase())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));
            map.put("material", (theoryResource.getMainMaterial().add(theoryResource.getDisperseMaterial())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP));

            /*-------------------图片9全国玉米秸秆“五料化”利用量------------------------*/
            List<EntitySortVo<BigDecimal>> entitySortVos = new ArrayList<>();
            BigDecimal fertilising = (theoryResource.getMainFertilising().add(theoryResource.getDisperseFertilising()).add(theoryResource.getProStillField())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            EntitySortVo<BigDecimal> fertilisingEntitySortVo = new EntitySortVo<>();
            fertilisingEntitySortVo.setName("肥料化利用");
            fertilisingEntitySortVo.setT(fertilising);
            entitySortVos.add(fertilisingEntitySortVo);
            BigDecimal forage = (theoryResource.getMainForage().add(theoryResource.getDisperseForage())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            EntitySortVo<BigDecimal> forageEntitySortVo = new EntitySortVo<>();
            forageEntitySortVo.setName("饲料化利用");
            forageEntitySortVo.setT(forage);
            entitySortVos.add(forageEntitySortVo);
            BigDecimal fuel = (theoryResource.getMainFuel().add(theoryResource.getDisperseFuel())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            EntitySortVo<BigDecimal> fuelEntitySortVo = new EntitySortVo<>();
            fuelEntitySortVo.setName("燃料化利用");
            fuelEntitySortVo.setT(fuel);
            entitySortVos.add(fuelEntitySortVo);
            BigDecimal base = (theoryResource.getMainBase().add(theoryResource.getDisperseBase())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            EntitySortVo<BigDecimal> baseEntitySortVo = new EntitySortVo<>();
            baseEntitySortVo.setName("基料化利用");
            baseEntitySortVo.setT(base);
            entitySortVos.add(baseEntitySortVo);
            BigDecimal material = (theoryResource.getMainMaterial().add(theoryResource.getDisperseMaterial())).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP);
            EntitySortVo<BigDecimal> materialEntitySortVo = new EntitySortVo<>();
            materialEntitySortVo.setName("原料化利用");
            materialEntitySortVo.setT(material);
            entitySortVos.add(materialEntitySortVo);
            entitySortVos.sort(new Comparator<EntitySortVo<BigDecimal>>() {
                @Override
                public int compare(EntitySortVo<BigDecimal> o1, EntitySortVo<BigDecimal> o2) {
                    return o2.getT().compareTo(o1.getT());
                }
            });
            mapData.clear();
            theoryResourceMap.clear();
            for (EntitySortVo<BigDecimal> sortVo : entitySortVos) {
                mapData.put(sortVo.getName(), sortVo.getT().doubleValue());
            }
            theoryResourceMap.put("全国" + strawType + "秸秆“五料化”利用量", mapData);
            byte[] pictureNine = barChart("全国" + strawType + "秸秆“五料化”利用量", theoryResourceMap, "秸秆可收集量", "万吨", 500, 400);
            map.put("pictureNine", new PictureRenderData(500, 400, ".png", pictureNine));

            /*-----------------综合利用率---------------------*/
            map.put("comprehensive", theoryResource.getComprehensive().setScale(2, 2).toString() + "%");
            //转换为比例
            BigDecimal fertilisingTr = theoryResource.getMainFertilising().add(theoryResource.getDisperseFertilising()).add(theoryResource.getProStillField());
            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
            if (theoryResource.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                map.put("fertilisingPercent", "0%");
            } else {
                BigDecimal fertilisingScale = fertilisingTr.divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                map.put("fertilisingPercent", fertilisingScale.toString() + "%");
            }
            BigDecimal forageTr = theoryResource.getMainForage().add(theoryResource.getDisperseForage());
            // 饲料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
            if (theoryResource.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                map.put("foragePercent", "0%");
            } else {
                BigDecimal forageScale = forageTr.divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP);
                map.put("foragePercent", forageScale.toString() + "%");
            }
            BigDecimal fuelTr = theoryResource.getMainFuel().add(theoryResource.getDisperseFuel());
            // 燃料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
            if (theoryResource.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                map.put("fuelPercent", "0%");
            } else {
                BigDecimal fuelScale = fuelTr.divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP);
                map.put("fuelPercent", fuelScale.toString() + "%");
            }
            BigDecimal baseTr = theoryResource.getMainBase().add(theoryResource.getDisperseBase());
            // 基料利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
            if (theoryResource.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                map.put("basePercent", "0%");
            } else {
                BigDecimal baseScale = baseTr.divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP);
                map.put("basePercent", baseScale.toString() + "%");
            }
            BigDecimal materialTr = theoryResource.getMainMaterial().add(theoryResource.getDisperseMaterial());
            // 原料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
            if (theoryResource.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                map.put("materialPercent", "0%");
            } else {
                BigDecimal materialScale = materialTr.divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP);
                map.put("materialPercent", materialScale.toString() + "%");
            }
            //重新计算利用率
            for (StrawUtilizeSum sum : list) {
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    sum.setComprehensive(BigDecimal.ZERO);
                } else {
                    BigDecimal comprehensive = sum.getProStrawUtilize().subtract(sum.getMainTotalOther())
                            .add(sum.getYieldAllExport())
                            .divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP);
                    sum.setComprehensive(comprehensive);
                }
            }
            list.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getComprehensive().compareTo(o1.getComprehensive());
                }
            });
            //筛选利用率大于95%的数据
            List<StrawUtilizeSum> ninetyFivePercentList = new ArrayList<>();
            List<StrawUtilizeSum> ninetyPercentList = new ArrayList<>();
            List<StrawUtilizeSum> eightyFivePercentList = new ArrayList<>();
            for (StrawUtilizeSum sum : list) {
                if (sum.getComprehensive().compareTo(new BigDecimal("0.95")) >= 0) {
                    ninetyFivePercentList.add(sum);
                }
                if (sum.getComprehensive().compareTo(new BigDecimal("0.90")) >= 0 && sum.getComprehensive().compareTo(new BigDecimal("0.95")) < 0) {
                    ninetyPercentList.add(sum);
                }
                if (sum.getComprehensive().compareTo(new BigDecimal("0.85")) >= 0 && sum.getComprehensive().compareTo(new BigDecimal("0.90")) < 0) {
                    eightyFivePercentList.add(sum);
                }
            }
            if (ninetyFivePercentList.size() > 0) {
                String provinceName = "";
                for (StrawUtilizeSum sum : ninetyFivePercentList) {
                    provinceName = provinceName + mapsByCodes.get(sum.getAreaId()) + "、";
                }
                provinceName = provinceName.substring(0, provinceName.lastIndexOf("、"));
                map.put("psuProvinceTwo", provinceName);
                map.put("psuProvinceNumTwo", ninetyFivePercentList.size());
            }
            if (ninetyPercentList.size() > 0) {
                String provinceName = "";
                for (StrawUtilizeSum sum : ninetyPercentList) {
                    provinceName = provinceName + mapsByCodes.get(sum.getAreaId()) + "、";
                }
                provinceName = provinceName.substring(0, provinceName.lastIndexOf("、"));
                map.put("psuProvinceThree", provinceName);
                map.put("psuProvinceNumThree", ninetyPercentList.size());
            }
            if (eightyFivePercentList.size() > 0) {
                String provinceName = "";
                for (StrawUtilizeSum sum : eightyFivePercentList) {
                    provinceName = provinceName + mapsByCodes.get(sum.getAreaId()) + "、";
                }
                provinceName = provinceName.substring(0, provinceName.lastIndexOf("、"));
                map.put("psuProvinceFour", provinceName);
                map.put("psuProvinceNumFour", eightyFivePercentList.size());
            }
            map.put("psuProvinceOne", mapsByCodes.get(list.get((list.size() - 1)).getAreaId()));
            map.put("psuProvinceOnePercent", list.get((list.size() - 1)).getComprehensive().multiply(new BigDecimal("100")).toString() + "%");

            /*------------------利用率分区来看-----------------------------*/
            for (StrawUtilizeSum sum : sumArrayList) {
                //重新计算秸秆利用率
                BigDecimal comprehensive = sum.getProStrawUtilize().subtract(sum.getMainTotalOther())
                        .add(sum.getYieldAllExport())
                        .divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setComprehensive(comprehensive);
            }
            //排序
            sumArrayList.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getComprehensive().compareTo(o1.getComprehensive());
                }
            });
            map.put("psuSixRegionOne", sumArrayList.get(0).getAreaName());
            map.put("psuSixRegionOnePercent", sumArrayList.get(0).getComprehensive().toString() + "%");
            map.put("psuSixRegionTwo", sumArrayList.get(1).getAreaName());
            map.put("psuSixRegionThree", sumArrayList.get(2).getAreaName());
            map.put("psuSixRegionFour", sumArrayList.get(3).getAreaName());
            map.put("psuSixRegionFive", sumArrayList.get(4).getAreaName());
            map.put("psuSixRegionSix", sumArrayList.get(5).getAreaName());
            map.put("psuSixRegionTwoPercent", sumArrayList.get(1).getComprehensive().toString() + "%");
            map.put("psuSixRegionThreePercent", sumArrayList.get(2).getComprehensive().toString() + "%");
            map.put("psuSixRegionFourPercent", sumArrayList.get(3).getComprehensive().toString() + "%");
            map.put("psuSixRegionFivePercent", sumArrayList.get(4).getComprehensive().toString() + "%");
            map.put("psuSixRegionSixPercent", sumArrayList.get(5).getComprehensive().toString() + "%");

            /*----------图片10-全国六大区玉米秸秆综合利用率分布图--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : sumArrayList) {
                mapData.put(sum.getAreaName(), sum.getComprehensive().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆综合利用率", mapData);
            byte[] pictureTen = barChart("全国六大区" + strawType + "秸秆综合利用率", theoryResourceMap, "秸秆综合利用率", "%", 500, 400);
            map.put("pictureTen", new PictureRenderData(500, 400, ".png", pictureTen));

            /*------------------肥料化利用比例分省看-----------------*/
            //重新处理五料化比例到新集合中
            List<FiveMaterialPercentVo> fiveMaterialPercentVos = new ArrayList<>();
            for (StrawUtilizeSum sum : list) {
                FiveMaterialPercentVo materialPercentVo = new FiveMaterialPercentVo();
                materialPercentVo.setAreaId(sum.getAreaId());
                //转换为比例
                BigDecimal provinceFertilising = sum.getMainFertilising().add(sum.getDisperseFertilising()).add(sum.getProStillField());
                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setFertilisingPercent(BigDecimal.ZERO);
                } else {
                    BigDecimal fertilisingScale = provinceFertilising.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setFertilisingPercent(fertilisingScale);
                }
                BigDecimal provinceForage = sum.getMainForage().add(sum.getDisperseForage());
                // 饲料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setForagePercent(BigDecimal.ZERO);
                } else {
                    BigDecimal forageScale = provinceForage.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setForagePercent(forageScale);
                }
                BigDecimal provinceFuel = sum.getMainFuel().add(sum.getDisperseFuel());
                // 燃料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setFuelPercent(BigDecimal.ZERO);
                } else {
                    BigDecimal fuelScale = provinceFuel.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setFuelPercent(fuelScale);
                }
                BigDecimal provinceBase = sum.getMainBase().add(sum.getDisperseBase());
                // 基料利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setBasePercent(BigDecimal.ZERO);
                } else {
                    BigDecimal baseScale = provinceBase.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setBasePercent(baseScale);
                }
                BigDecimal provinceMaterial = sum.getMainMaterial().add(sum.getDisperseMaterial());
                // 原料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setMaterialPercent(BigDecimal.ZERO);
                } else {
                    BigDecimal materialScale = provinceMaterial.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setMaterialPercent(materialScale);
                }
                fiveMaterialPercentVos.add(materialPercentVo);
            }
            //肥料化排序
            fiveMaterialPercentVos.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getFertilisingPercent().compareTo(o1.getFertilisingPercent());
                }
            });
            map.put("fertilisingProvinceOne", mapsByCodes.get(fiveMaterialPercentVos.get(0).getAreaId()));
            map.put("fertilisingProvinceOnePercent", fiveMaterialPercentVos.get(0).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceTwo", mapsByCodes.get(fiveMaterialPercentVos.get(1).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(2).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(3).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(4).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(5).getAreaId()));
            map.put("fertilisingProvinceTwoPercent", fiveMaterialPercentVos.get(5).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceSixPercent", fiveMaterialPercentVos.get(1).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceThree", mapsByCodes.get(fiveMaterialPercentVos.get(6).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(7).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(8).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(9).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(10).getAreaId()));
            map.put("fertilisingProvinceElevenPercent", fiveMaterialPercentVos.get(6).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceSevenPercent", fiveMaterialPercentVos.get(10).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceFour", mapsByCodes.get(fiveMaterialPercentVos.get(11).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(12).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(13).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(14).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(15).getAreaId()));
            map.put("fertilisingProvinceTwelvePercent", fiveMaterialPercentVos.get(15).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceSixteenPercent", fiveMaterialPercentVos.get(11).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceFive", mapsByCodes.get(fiveMaterialPercentVos.get(16).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(17).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(18).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(19).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(20).getAreaId()));
            map.put("fertilisingProvinceTwentyOnePercent", fiveMaterialPercentVos.get(16).getFertilisingPercent().toString() + "%");
            map.put("fertilisingProvinceSeventeenPercent", fiveMaterialPercentVos.get(20).getFertilisingPercent().toString() + "%");

            /*----------图片11- 全国不同省区玉米秸秆肥料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : fiveMaterialPercentVos) {
                mapData.put(mapsByCodes.get(percentVo.getAreaId()), percentVo.getFertilisingPercent().doubleValue());
            }
            theoryResourceMap.put("全国不同省区" + strawType + "秸秆肥料化利用比例", mapData);
            byte[] pictureEleven = barChart("全国不同省区" + strawType + "秸秆肥料化利用比例", theoryResourceMap, "秸秆肥料化利用比例", "%", 500, 400);
            map.put("pictureEleven", new PictureRenderData(500, 400, ".png", pictureEleven));

            /*------------------五料化利用比例分区来看-----------------*/
            List<FiveMaterialPercentVo> sixRegionFiveMaterialPercent = new ArrayList<>();
            for (StrawUtilizeSum sum : sumArrayList) {
                FiveMaterialPercentVo materialPercentVo = new FiveMaterialPercentVo();
                materialPercentVo.setAreaName(sum.getAreaName());
                //转换为比例
                BigDecimal provinceFertilising = sum.getMainFertilising().add(sum.getDisperseFertilising()).add(sum.getProStillField());
                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setFertilisingPercent(BigDecimal.ZERO);
                } else {
                    BigDecimal fertilisingScale = provinceFertilising.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setFertilisingPercent(fertilisingScale);
                }
                BigDecimal provinceForage = sum.getMainForage().add(sum.getDisperseForage());
                // 饲料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setForagePercent(BigDecimal.ZERO);
                } else {
                    BigDecimal forageScale = provinceForage.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setForagePercent(forageScale);
                }
                BigDecimal provinceFuel = sum.getMainFuel().add(sum.getDisperseFuel());
                // 燃料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setFuelPercent(BigDecimal.ZERO);
                } else {
                    BigDecimal fuelScale = provinceFuel.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setFuelPercent(fuelScale);
                }
                BigDecimal provinceBase = sum.getMainBase().add(sum.getDisperseBase());
                // 基料利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setBasePercent(BigDecimal.ZERO);
                } else {
                    BigDecimal baseScale = provinceBase.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setBasePercent(baseScale);
                }
                BigDecimal provinceMaterial = sum.getMainMaterial().add(sum.getDisperseMaterial());
                // 原料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    materialPercentVo.setMaterialPercent(BigDecimal.ZERO);
                } else {
                    BigDecimal materialScale = provinceMaterial.divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    materialPercentVo.setMaterialPercent(materialScale);
                }
                sixRegionFiveMaterialPercent.add(materialPercentVo);
            }
            //排序
            sixRegionFiveMaterialPercent.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getFertilisingPercent().compareTo(o1.getFertilisingPercent());
                }
            });
            map.put("fertilisingSixRegionOne", sixRegionFiveMaterialPercent.get(0).getAreaName());
            map.put("fertilisingSixRegionOnePercent", sixRegionFiveMaterialPercent.get(0).getFertilisingPercent().toString() + "%");
            map.put("fertilisingSixRegionTwo", sixRegionFiveMaterialPercent.get(1).getAreaName());
            map.put("fertilisingSixRegionTwoPercent", sixRegionFiveMaterialPercent.get(1).getFertilisingPercent().toString() + "%");
            map.put("fertilisingSixRegionThree", sixRegionFiveMaterialPercent.get(2).getAreaName());
            map.put("fertilisingSixRegionThreePercent", sixRegionFiveMaterialPercent.get(2).getFertilisingPercent().toString() + "%");
            map.put("fertilisingSixRegionFour", sixRegionFiveMaterialPercent.get(3).getAreaName());
            map.put("fertilisingSixRegionFourPercent", sixRegionFiveMaterialPercent.get(3).getFertilisingPercent().toString() + "%");
            map.put("fertilisingSixRegionFive", sixRegionFiveMaterialPercent.get(4).getAreaName());
            map.put("fertilisingSixRegionFivePercent", sixRegionFiveMaterialPercent.get(4).getFertilisingPercent().toString() + "%");
            map.put("fertilisingSixRegionSix", sixRegionFiveMaterialPercent.get(5).getAreaName());
            map.put("fertilisingSixRegionSixPercent", sixRegionFiveMaterialPercent.get(5).getFertilisingPercent().toString() + "%");

            /*----------图片12-全国六大区玉米秸秆肥料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : sixRegionFiveMaterialPercent) {
                mapData.put(percentVo.getAreaName(), percentVo.getFertilisingPercent().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆肥料化利用比例", mapData);
            byte[] pictureTwelve = barChart("全国不同省区" + strawType + "秸秆肥料化利用比例", theoryResourceMap, "秸秆肥料化利用比例", "%", 500, 400);
            map.put("pictureTwelve", new PictureRenderData(500, 400, ".png", pictureTwelve));

            /*--------------------饲料化利用比例分省来看--------------------------*/
            fiveMaterialPercentVos.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getForagePercent().compareTo(o1.getForagePercent());
                }
            });
            map.put("forageProvinceOne", mapsByCodes.get(fiveMaterialPercentVos.get(0).getAreaId()));
            map.put("forageProvinceOnePercent", fiveMaterialPercentVos.get(0).getForagePercent().toString() + "%");
            map.put("forageProvinceTwo", mapsByCodes.get(fiveMaterialPercentVos.get(1).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(2).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(3).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(4).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(5).getAreaId()));
            map.put("forageProvinceTwoPercent", fiveMaterialPercentVos.get(5).getForagePercent().toString() + "%");
            map.put("forageProvinceSixPercent", fiveMaterialPercentVos.get(1).getForagePercent().toString() + "%");
            map.put("forageProvinceThree", mapsByCodes.get(fiveMaterialPercentVos.get(6).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(7).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(8).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(9).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(10).getAreaId()));
            map.put("forageProvinceElevenPercent", fiveMaterialPercentVos.get(6).getForagePercent().toString() + "%");
            map.put("forageProvinceSevenPercent", fiveMaterialPercentVos.get(10).getForagePercent().toString() + "%");
            map.put("forageProvinceFour", mapsByCodes.get(fiveMaterialPercentVos.get(11).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(12).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(13).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(14).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(15).getAreaId()));
            map.put("forageProvinceSixteenPercent", fiveMaterialPercentVos.get(11).getForagePercent().toString() + "%");
            map.put("forageProvinceTwelvePercent", fiveMaterialPercentVos.get(15).getForagePercent().toString() + "%");
            map.put("forageProvinceFive", mapsByCodes.get(fiveMaterialPercentVos.get(16).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(17).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(18).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(19).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(20).getAreaId()));
            map.put("forageProvinceTwentyOnePercent", fiveMaterialPercentVos.get(16).getForagePercent().toString() + "%");
            map.put("forageProvinceSeventeenPercent", fiveMaterialPercentVos.get(20).getForagePercent().toString() + "%");

            /*----------图片13- 全国不同省区玉米秸秆肥料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : fiveMaterialPercentVos) {
                mapData.put(mapsByCodes.get(percentVo.getAreaId()), percentVo.getForagePercent().doubleValue());
            }
            theoryResourceMap.put("全国不同省区" + strawType + "秸秆饲料化利用比例", mapData);
            byte[] pictureThirteen = barChart("全国不同省区" + strawType + "秸秆饲料化利用比例", theoryResourceMap, "秸秆肥料化利用比例", "%", 500, 400);
            map.put("pictureThirteen", new PictureRenderData(500, 400, ".png", pictureThirteen));

            /*--------------------饲料化利用比例分六大区来看--------------------------*/
            //排序
            sixRegionFiveMaterialPercent.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getForagePercent().compareTo(o1.getForagePercent());
                }
            });
            map.put("forageSixRegionOne", sixRegionFiveMaterialPercent.get(0).getAreaName());
            map.put("forageSixRegionOnePercent", sixRegionFiveMaterialPercent.get(0).getForagePercent().toString() + "%");
            map.put("forageSixRegionTwo", sixRegionFiveMaterialPercent.get(1).getAreaName());
            map.put("forageSixRegionTwoPercent", sixRegionFiveMaterialPercent.get(1).getForagePercent().toString() + "%");
            map.put("forageSixRegionThree", sixRegionFiveMaterialPercent.get(2).getAreaName());
            map.put("forageSixRegionThreePercent", sixRegionFiveMaterialPercent.get(2).getForagePercent().toString() + "%");
            map.put("forageSixRegionFour", sixRegionFiveMaterialPercent.get(3).getAreaName());
            map.put("forageSixRegionFourPercent", sixRegionFiveMaterialPercent.get(3).getForagePercent().toString() + "%");
            map.put("forageSixRegionFive", sixRegionFiveMaterialPercent.get(4).getAreaName());
            map.put("forageSixRegionFivePercent", sixRegionFiveMaterialPercent.get(4).getForagePercent().toString() + "%");
            map.put("forageSixRegionSix", sixRegionFiveMaterialPercent.get(5).getAreaName());
            map.put("forageSixRegionSixPercent", sixRegionFiveMaterialPercent.get(5).getForagePercent().toString() + "%");

            /*----------图片14-全国六大区玉米秸秆肥料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : sixRegionFiveMaterialPercent) {
                mapData.put(percentVo.getAreaName(), percentVo.getForagePercent().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆饲料化利用比例", mapData);
            byte[] pictureFourteen = barChart("全国不同省区" + strawType + "秸秆饲料化利用比例", theoryResourceMap, "秸秆饲料化利用比例", "%", 500, 400);
            map.put("pictureFourteen", new PictureRenderData(500, 400, ".png", pictureFourteen));

            /*--------------------燃料化利用比例分省来看--------------------------*/
            //排序
            fiveMaterialPercentVos.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getFuelPercent().compareTo(o1.getFuelPercent());
                }
            });
            map.put("fuelProvinceOne", mapsByCodes.get(fiveMaterialPercentVos.get(0).getAreaId()));
            map.put("fuelProvinceOnePercent", fiveMaterialPercentVos.get(0).getFuelPercent().toString() + "%");
            map.put("fuelProvinceTwo", mapsByCodes.get(fiveMaterialPercentVos.get(1).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(2).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(3).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(4).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(5).getAreaId()));
            map.put("fuelProvinceTwoPercent", fiveMaterialPercentVos.get(5).getFuelPercent().toString() + "%");
            map.put("fuelProvinceSixPercent", fiveMaterialPercentVos.get(1).getFuelPercent().toString() + "%");
            map.put("fuelProvinceThree", mapsByCodes.get(fiveMaterialPercentVos.get(6).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(7).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(8).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(9).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(10).getAreaId()));
            map.put("fuelProvinceElevenPercent", fiveMaterialPercentVos.get(6).getFuelPercent().toString() + "%");
            map.put("fuelProvinceSevenPercent", fiveMaterialPercentVos.get(10).getFuelPercent().toString() + "%");
            map.put("fuelProvinceFour", mapsByCodes.get(fiveMaterialPercentVos.get(11).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(12).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(13).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(14).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(15).getAreaId()));
            map.put("fuelProvinceSixteenPercent", fiveMaterialPercentVos.get(11).getFuelPercent().toString() + "%");
            map.put("fuelProvinceTwelvePercent", fiveMaterialPercentVos.get(15).getFuelPercent().toString() + "%");
            map.put("fuelProvinceFive", mapsByCodes.get(fiveMaterialPercentVos.get(16).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(17).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(18).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(19).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(20).getAreaId()));
            map.put("fuelProvinceTwentyOnePercent", fiveMaterialPercentVos.get(16).getFuelPercent().toString() + "%");
            map.put("fuelProvinceSeventeenPercent", fiveMaterialPercentVos.get(20).getFuelPercent().toString() + "%");

            /*----------图片15- 全国不同省区玉米秸秆燃料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : fiveMaterialPercentVos) {
                mapData.put(mapsByCodes.get(percentVo.getAreaId()), percentVo.getFuelPercent().doubleValue());
            }
            theoryResourceMap.put("全国不同省区" + strawType + "秸秆燃料化利用比例", mapData);
            byte[] pictureFifteen = barChart("全国不同省区" + strawType + "秸秆燃料化利用比例", theoryResourceMap, "秸秆燃料化利用比例", "%", 500, 400);
            map.put("pictureFifteen", new PictureRenderData(500, 400, ".png", pictureFifteen));

            /*--------------------燃料化利用比例分六大区来看--------------------------*/
            //排序
            sixRegionFiveMaterialPercent.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getFuelPercent().compareTo(o1.getFuelPercent());
                }
            });
            map.put("fuelSixRegionOne", sixRegionFiveMaterialPercent.get(0).getAreaName());
            map.put("fuelSixRegionOnePercent", sixRegionFiveMaterialPercent.get(0).getFuelPercent().toString() + "%");
            map.put("fuelSixRegionTwo", sixRegionFiveMaterialPercent.get(1).getAreaName());
            map.put("fuelSixRegionTwoPercent", sixRegionFiveMaterialPercent.get(1).getFuelPercent().toString() + "%");
            map.put("fuelSixRegionThree", sixRegionFiveMaterialPercent.get(2).getAreaName());
            map.put("fuelSixRegionThreePercent", sixRegionFiveMaterialPercent.get(2).getFuelPercent().toString() + "%");
            map.put("fuelSixRegionFour", sixRegionFiveMaterialPercent.get(3).getAreaName());
            map.put("fuelSixRegionFourPercent", sixRegionFiveMaterialPercent.get(3).getFuelPercent().toString() + "%");
            map.put("fuelSixRegionFive", sixRegionFiveMaterialPercent.get(4).getAreaName());
            map.put("fuelSixRegionFivePercent", sixRegionFiveMaterialPercent.get(4).getFuelPercent().toString() + "%");
            map.put("fuelSixRegionSix", sixRegionFiveMaterialPercent.get(5).getAreaName());
            map.put("fuelSixRegionSixPercent", sixRegionFiveMaterialPercent.get(5).getFuelPercent().toString() + "%");

            /*----------图片16-全国六大区玉米秸秆燃料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : sixRegionFiveMaterialPercent) {
                mapData.put(percentVo.getAreaName(), percentVo.getFuelPercent().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆燃料化利用比例", mapData);
            byte[] pictureSixteen = barChart("全国不同省区" + strawType + "秸秆燃料化利用比例", theoryResourceMap, "秸秆燃料化利用比例", "%", 500, 400);
            map.put("pictureSixteen", new PictureRenderData(500, 400, ".png", pictureSixteen));

            /*--------------------基料化利用比例分省来看--------------------------*/
            //排序
            fiveMaterialPercentVos.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getBasePercent().compareTo(o1.getBasePercent());
                }
            });
            map.put("baseProvinceOne", mapsByCodes.get(fiveMaterialPercentVos.get(0).getAreaId()));
            map.put("baseProvinceOnePercent", fiveMaterialPercentVos.get(0).getBasePercent().toString() + "%");
            map.put("baseProvinceTwo", mapsByCodes.get(fiveMaterialPercentVos.get(1).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(2).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(3).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(4).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(5).getAreaId()));
            map.put("baseProvinceTwoPercent", fiveMaterialPercentVos.get(5).getBasePercent().toString() + "%");
            map.put("baseProvinceSixPercent", fiveMaterialPercentVos.get(1).getBasePercent().toString() + "%");
            map.put("baseProvinceThree", mapsByCodes.get(fiveMaterialPercentVos.get(6).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(7).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(8).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(9).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(10).getAreaId()));
            map.put("baseProvinceElevenPercent", fiveMaterialPercentVos.get(6).getBasePercent().toString() + "%");
            map.put("baseProvinceSevenPercent", fiveMaterialPercentVos.get(10).getBasePercent().toString() + "%");
            map.put("baseProvinceFour", mapsByCodes.get(fiveMaterialPercentVos.get(11).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(12).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(13).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(14).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(15).getAreaId()));
            map.put("baseProvinceSixteenPercent", fiveMaterialPercentVos.get(11).getBasePercent().toString() + "%");
            map.put("baseProvinceTwelvePercent", fiveMaterialPercentVos.get(15).getBasePercent().toString() + "%");
            map.put("baseProvinceFive", mapsByCodes.get(fiveMaterialPercentVos.get(16).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(17).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(18).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(19).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(20).getAreaId()));
            map.put("baseProvinceTwentyOnePercent", fiveMaterialPercentVos.get(16).getBasePercent().toString() + "%");
            map.put("baseProvinceSeventeenPercent", fiveMaterialPercentVos.get(20).getBasePercent().toString() + "%");

            /*----------图片17- 全国不同省区玉米秸秆基料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : fiveMaterialPercentVos) {
                mapData.put(mapsByCodes.get(percentVo.getAreaId()), percentVo.getBasePercent().doubleValue());
            }
            theoryResourceMap.put("全国不同省区" + strawType + "秸秆基料化利用比例", mapData);
            byte[] pictureSeventeen = barChart("全国不同省区" + strawType + "秸秆基料化利用比例", theoryResourceMap, "秸秆基料化利用比例", "%", 500, 400);
            map.put("pictureSeventeen", new PictureRenderData(500, 400, ".png", pictureSeventeen));

            /*--------------------基料化利用比例分六大区来看--------------------------*/
            //排序
            sixRegionFiveMaterialPercent.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getBasePercent().compareTo(o1.getBasePercent());
                }
            });
            map.put("baseSixRegionOne", sixRegionFiveMaterialPercent.get(0).getAreaName());
            map.put("baseSixRegionOnePercent", sixRegionFiveMaterialPercent.get(0).getBasePercent().toString() + "%");
            map.put("baseSixRegionTwo", sixRegionFiveMaterialPercent.get(1).getAreaName());
            map.put("baseSixRegionTwoPercent", sixRegionFiveMaterialPercent.get(1).getBasePercent().toString() + "%");
            map.put("baseSixRegionThree", sixRegionFiveMaterialPercent.get(2).getAreaName());
            map.put("baseSixRegionThreePercent", sixRegionFiveMaterialPercent.get(2).getBasePercent().toString() + "%");
            map.put("baseSixRegionFour", sixRegionFiveMaterialPercent.get(3).getAreaName());
            map.put("baseSixRegionFourPercent", sixRegionFiveMaterialPercent.get(3).getBasePercent().toString() + "%");
            map.put("baseSixRegionFive", sixRegionFiveMaterialPercent.get(4).getAreaName());
            map.put("baseSixRegionFivePercent", sixRegionFiveMaterialPercent.get(4).getBasePercent().toString() + "%");
            map.put("baseSixRegionSix", sixRegionFiveMaterialPercent.get(5).getAreaName());
            map.put("baseSixRegionSixPercent", sixRegionFiveMaterialPercent.get(5).getBasePercent().toString() + "%");

            /*----------图片18-全国六大区玉米秸秆基料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : sixRegionFiveMaterialPercent) {
                mapData.put(percentVo.getAreaName(), percentVo.getBasePercent().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆基料化利用比例", mapData);
            byte[] pictureEighteen = barChart("全国不同省区" + strawType + "秸秆基料化利用比例", theoryResourceMap, "秸秆基料化利用比例", "%", 500, 400);
            map.put("pictureEighteen", new PictureRenderData(500, 400, ".png", pictureEighteen));


            /*--------------------原料化利用比例分省来看--------------------------*/
            //排序
            fiveMaterialPercentVos.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getMaterialPercent().compareTo(o1.getMaterialPercent());
                }
            });
            map.put("materialProvinceOne", mapsByCodes.get(fiveMaterialPercentVos.get(0).getAreaId()));
            map.put("materialProvinceOnePercent", fiveMaterialPercentVos.get(0).getMaterialPercent().toString() + "%");
            map.put("materialProvinceTwo", mapsByCodes.get(fiveMaterialPercentVos.get(1).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(2).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(3).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(4).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(5).getAreaId()));
            map.put("materialProvinceTwoPercent", fiveMaterialPercentVos.get(5).getMaterialPercent().toString() + "%");
            map.put("materialProvinceSixPercent", fiveMaterialPercentVos.get(1).getMaterialPercent().toString() + "%");
            map.put("materialProvinceThree", mapsByCodes.get(fiveMaterialPercentVos.get(6).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(7).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(8).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(9).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(10).getAreaId()));
            map.put("materialProvinceElevenPercent", fiveMaterialPercentVos.get(6).getMaterialPercent().toString() + "%");
            map.put("materialProvinceSevenPercent", fiveMaterialPercentVos.get(10).getMaterialPercent().toString() + "%");
            map.put("materialProvinceFour", mapsByCodes.get(fiveMaterialPercentVos.get(11).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(12).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(13).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(14).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(15).getAreaId()));
            map.put("materialProvinceSixteenPercent", fiveMaterialPercentVos.get(11).getMaterialPercent().toString() + "%");
            map.put("materialProvinceTwelvePercent", fiveMaterialPercentVos.get(15).getMaterialPercent().toString() + "%");
            map.put("materialProvinceFive", mapsByCodes.get(fiveMaterialPercentVos.get(16).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(17).getAreaId()) + "、"
                    + mapsByCodes.get(fiveMaterialPercentVos.get(18).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(19).getAreaId()) + "、" + mapsByCodes.get(fiveMaterialPercentVos.get(20).getAreaId()));
            map.put("materialProvinceTwentyOnePercent", fiveMaterialPercentVos.get(16).getMaterialPercent().toString() + "%");
            map.put("materialProvinceSeventeenPercent", fiveMaterialPercentVos.get(20).getMaterialPercent().toString() + "%");

            /*----------图片19- 全国不同省区玉米秸秆肥料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : fiveMaterialPercentVos) {
                mapData.put(mapsByCodes.get(percentVo.getAreaId()), percentVo.getMaterialPercent().doubleValue());
            }
            theoryResourceMap.put("全国不同省区" + strawType + "秸秆基料化利用比例", mapData);
            byte[] pictureNineteen = barChart("全国不同省区" + strawType + "秸秆基料化利用比例", theoryResourceMap, "秸秆基料化利用比例", "%", 500, 400);
            map.put("pictureNineteen", new PictureRenderData(500, 400, ".png", pictureNineteen));

            /*--------------------原料化利用比例分六大区来看--------------------------*/
            //排序
            sixRegionFiveMaterialPercent.sort(new Comparator<FiveMaterialPercentVo>() {
                @Override
                public int compare(FiveMaterialPercentVo o1, FiveMaterialPercentVo o2) {
                    return o2.getMaterialPercent().compareTo(o1.getMaterialPercent());
                }
            });
            map.put("materialSixRegionOne", sixRegionFiveMaterialPercent.get(0).getAreaName());
            map.put("materialSixRegionOnePercent", sixRegionFiveMaterialPercent.get(0).getMaterialPercent().toString() + "%");
            map.put("materialSixRegionTwo", sixRegionFiveMaterialPercent.get(1).getAreaName());
            map.put("materialSixRegionTwoPercent", sixRegionFiveMaterialPercent.get(1).getMaterialPercent().toString() + "%");
            map.put("materialSixRegionThree", sixRegionFiveMaterialPercent.get(2).getAreaName());
            map.put("materialSixRegionThreePercent", sixRegionFiveMaterialPercent.get(2).getMaterialPercent().toString() + "%");
            map.put("materialSixRegionFour", sixRegionFiveMaterialPercent.get(3).getAreaName());
            map.put("materialSixRegionFourPercent", sixRegionFiveMaterialPercent.get(3).getMaterialPercent().toString() + "%");
            map.put("materialSixRegionFive", sixRegionFiveMaterialPercent.get(4).getAreaName());
            map.put("materialSixRegionFivePercent", sixRegionFiveMaterialPercent.get(4).getMaterialPercent().toString() + "%");
            map.put("materialSixRegionSix", sixRegionFiveMaterialPercent.get(5).getAreaName());
            map.put("materialSixRegionSixPercent", sixRegionFiveMaterialPercent.get(5).getMaterialPercent().toString() + "%");

            /*----------图片20-全国六大区玉米秸秆肥料化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (FiveMaterialPercentVo percentVo : sixRegionFiveMaterialPercent) {
                mapData.put(percentVo.getAreaName(), percentVo.getMaterialPercent().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆基料化利用比例", mapData);
            byte[] pictureTwenty = barChart("全国不同省区" + strawType + "秸秆基料化利用比例", theoryResourceMap, "秸秆基料化利用比例", "%", 500, 400);
            map.put("pictureTwenty", new PictureRenderData(500, 400, ".png", pictureTwenty));

            /*---------------离田还田情况------------------*/
            map.put("proStillField", theoryResource.getProStillField().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            map.put("returnRatio", theoryResource.getProStillField().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("levelUtilizationRatio", (theoryResource.getMainTotal().add(theoryResource.getDisperseTotal())).divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("disperseUtilize", theoryResource.getDisperseTotal().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("mainUtilize", theoryResource.getMainTotal().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");

            /*--------------直接还田情况分省来看------------------*/
            //单作物直接还田量占秸秆可收集量的比例
            ArrayList<StrawUtilizeSum> proStillFieldPercents = new ArrayList<>();
            proStillFieldPercents.addAll(list);
            for (StrawUtilizeSum sum : proStillFieldPercents) {
                BigDecimal proStillFieldPercent = sum.getProStillField().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setProStillField(proStillFieldPercent);
            }
            proStillFieldPercents.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getProStillField().compareTo(o1.getProStillField());
                }
            });
            map.put("returnProvinceOne", mapsByCodes.get(proStillFieldPercents.get(0).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(1).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(2).getAreaId())
                    + "、" + mapsByCodes.get(proStillFieldPercents.get(3).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(4).getAreaId()));
            map.put("returnProvinceOnePercent", proStillFieldPercents.get(4).getProStillField() + "%");
            map.put("returnProvinceFivePercent", proStillFieldPercents.get(0).getProStillField() + "%");
            map.put("returnProvinceTwo", mapsByCodes.get(proStillFieldPercents.get(5).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(6).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(7).getAreaId())
                    + "、" + mapsByCodes.get(proStillFieldPercents.get(8).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(9).getAreaId()));
            map.put("returnProvinceSixPercent", proStillFieldPercents.get(9).getProStillField() + "%");
            map.put("returnProvinceTenPercent", proStillFieldPercents.get(5).getProStillField() + "%");
            map.put("returnProvinceThree", mapsByCodes.get(proStillFieldPercents.get(10).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(11).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(12).getAreaId())
                    + "、" + mapsByCodes.get(proStillFieldPercents.get(13).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(14).getAreaId()));
            map.put("returnProvinceElevenPercent", proStillFieldPercents.get(14).getProStillField() + "%");
            map.put("returnProvinceFifteenPercent", proStillFieldPercents.get(10).getProStillField() + "%");
            map.put("returnProvinceFor", mapsByCodes.get(proStillFieldPercents.get(15).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(16).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(17).getAreaId())
                    + "、" + mapsByCodes.get(proStillFieldPercents.get(18).getAreaId()) + "、" + mapsByCodes.get(proStillFieldPercents.get(19).getAreaId()));
            map.put("returnProvinceSixteenPercent", proStillFieldPercents.get(19).getProStillField() + "%");
            map.put("returnProvinceTwentyPercent", proStillFieldPercents.get(15).getProStillField() + "%");

            /*----------图片21-全国各省区{{crop}}秸秆直接还田比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : proStillFieldPercents) {
                mapData.put(mapsByCodes.get(sum.getAreaId()), sum.getProStillField().doubleValue());
            }
            theoryResourceMap.put("全国各省区" + strawType + "秸秆直接还田比例", mapData);
            byte[] pictureTwentyOne = barChart("全国各省区" + strawType + "秸秆直接还田比例", theoryResourceMap, "秸秆直接还田比例", "%", 500, 400);
            map.put("pictureTwentyOne", new PictureRenderData(500, 400, ".png", pictureTwentyOne));

            ArrayList<StrawUtilizeSum> proStillFieldSixRegion = new ArrayList<>();
            proStillFieldSixRegion.addAll(sumArrayList);

            /*--------------直接还田情况分区来看------------------*/
            for (StrawUtilizeSum sum : proStillFieldSixRegion) {
                //计算还田比例
                BigDecimal proStillFieldPercent = sum.getProStillField().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setProStillField(proStillFieldPercent);
            }
            proStillFieldSixRegion.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getProStillField().compareTo(o1.getProStillField());
                }
            });
            map.put("returnSixRegionOne", proStillFieldSixRegion.get(0).getAreaName());
            map.put("returnSixRegionOnePercent", proStillFieldSixRegion.get(0).getProStillField() + "%");
            map.put("returnSixRegionTwo", proStillFieldSixRegion.get(1).getAreaName());
            map.put("returnSixRegionTwoPercent", proStillFieldSixRegion.get(1).getProStillField() + "%");
            map.put("returnSixRegionThree", proStillFieldSixRegion.get(2).getAreaName());
            map.put("returnSixRegionThreePercent", proStillFieldSixRegion.get(2).getProStillField() + "%");
            map.put("returnSixRegionFour", proStillFieldSixRegion.get(3).getAreaName());
            map.put("returnSixRegionFourPercent", proStillFieldSixRegion.get(3).getProStillField() + "%");
            map.put("returnSixRegionFive", proStillFieldSixRegion.get(4).getAreaName());
            map.put("returnSixRegionFivePercent", proStillFieldSixRegion.get(4).getProStillField() + "%");
            map.put("returnSixRegionSix", proStillFieldSixRegion.get(5).getAreaName());
            map.put("returnSixRegionSixPercent", proStillFieldSixRegion.get(5).getProStillField() + "%");

            /*----------图片22-全国六大区{{crop}}秸秆直接还田比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : proStillFieldSixRegion) {
                mapData.put(sum.getAreaName(), sum.getProStillField().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "秸秆直接还田比例", mapData);
            byte[] pictureTwentyTwo = barChart("全国六大区" + strawType + "秸秆直接还田比例", theoryResourceMap, "秸秆直接还田比例", "%", 500, 400);
            map.put("pictureTwentyTwo", new PictureRenderData(500, 400, ".png", pictureTwentyTwo));

            /*---------------秸秆离田利用情况------------------*/
            /*--------------农户分散利用分省来看------------------*/
            //单作物农户分散利用量占秸秆可收集量的比例
            ArrayList<StrawUtilizeSum> disperses = new ArrayList<>();
            disperses.addAll(list);
            for (StrawUtilizeSum sum : disperses) {
                BigDecimal disperse = sum.getDisperseTotal().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setDisperseTotal(disperse);
            }
            disperses.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getDisperseTotal().compareTo(o1.getDisperseTotal());
                }
            });
            map.put("disperseProvinceOne", mapsByCodes.get(disperses.get(0).getAreaId()));
            map.put("disperseProvinceOnePercent", disperses.get(0).getDisperseTotal() + "%");
            map.put("disperseProvinceTwo", mapsByCodes.get(disperses.get(1).getAreaId()));
            map.put("disperseProvinceTwoPercent", disperses.get(1).getDisperseTotal() + "%");
            map.put("disperseProvinceThree", mapsByCodes.get(disperses.get(2).getAreaId()));
            map.put("disperseProvinceThreePercent", disperses.get(2).getDisperseTotal() + "%");
            map.put("disperseProvinceFour", mapsByCodes.get(disperses.get(3).getAreaId()));
            map.put("disperseProvinceFive", mapsByCodes.get(disperses.get(4).getAreaId()));
            map.put("disperseProvinceSix", mapsByCodes.get(disperses.get(5).getAreaId()));
            map.put("disperseProvinceSeven", mapsByCodes.get(disperses.get(6).getAreaId()));
            map.put("disperseProvinceEight", mapsByCodes.get(disperses.get(7).getAreaId()));
            map.put("disperseProvinceNine", mapsByCodes.get(disperses.get(8).getAreaId()));
            map.put("disperseProvinceTen", mapsByCodes.get(disperses.get(9).getAreaId()));
            map.put("disperseProvinceFourPercent", disperses.get(9).getDisperseTotal() + "%");
            map.put("disperseProvinceTenPercent", disperses.get(4).getDisperseTotal() + "%");
            map.put("disperseProvinceEleven", mapsByCodes.get(disperses.get(10).getAreaId()));
            map.put("disperseProvinceTwelve", mapsByCodes.get(disperses.get(11).getAreaId()));
            map.put("disperseProvinceThirteen", mapsByCodes.get(disperses.get(12).getAreaId()));
            map.put("disperseProvinceFourteen", mapsByCodes.get(disperses.get(13).getAreaId()));
            map.put("disperseProvinceFifteen", mapsByCodes.get(disperses.get(14).getAreaId()));
            map.put("disperseProvinceElevenPercent", disperses.get(14).getDisperseTotal() + "%");
            map.put("disperseProvinceFifteenPercent", disperses.get(10).getDisperseTotal() + "%");

            map.put("disperseProvinceSixteen", mapsByCodes.get(disperses.get(15).getAreaId()));
            map.put("disperseProvinceSevenTeen", mapsByCodes.get(disperses.get(16).getAreaId()));
            map.put("disperseProvinceEighteen", mapsByCodes.get(disperses.get(17).getAreaId()));
            map.put("disperseProvinceNineteen", mapsByCodes.get(disperses.get(18).getAreaId()));
            map.put("disperseProvinceTwenty", mapsByCodes.get(disperses.get(19).getAreaId()));
            map.put("disperseProvinceSixteenPercent", disperses.get(19).getDisperseTotal() + "%");
            map.put("disperseProvinceTwentyPercent", disperses.get(15).getDisperseTotal() + "%");

            /*----------图片23-全国各省区{{crop}}秸秆农户分散利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : disperses) {
                mapData.put(mapsByCodes.get(sum.getAreaId()), sum.getDisperseTotal().doubleValue());
            }
            theoryResourceMap.put("全国各省区" + strawType + "秸秆农户分散利用比例", mapData);
            byte[] pictureTwentyThree = barChart("全国各省区" + strawType + "秸秆农户分散利用比例", theoryResourceMap, "秸秆农户分散利用比例", "%", 500, 400);
            map.put("pictureTwentyThree", new PictureRenderData(500, 400, ".png", pictureTwentyThree));

            ArrayList<StrawUtilizeSum> disperseFieldSixRegion = new ArrayList<>();
            disperseFieldSixRegion.addAll(sumArrayList);

            /*--------------分散利用比例分区来看------------------*/
            for (StrawUtilizeSum sum : disperseFieldSixRegion) {
                //计算还田比例
                BigDecimal disperseFieldPercent = sum.getDisperseTotal().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setDisperseTotal(disperseFieldPercent);
            }
            disperseFieldSixRegion.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getDisperseTotal().compareTo(o1.getDisperseTotal());
                }
            });
            map.put("disperseSixRegionOne", disperseFieldSixRegion.get(0).getAreaName());
            map.put("disperseSixRegionOnePercent", disperseFieldSixRegion.get(0).getDisperseTotal() + "%");
            map.put("disperseSixRegionTwo", disperseFieldSixRegion.get(1).getAreaName());
            map.put("disperseSixRegionTwoPercent", disperseFieldSixRegion.get(1).getDisperseTotal() + "%");
            map.put("disperseSixRegionThree", disperseFieldSixRegion.get(2).getAreaName());
            map.put("disperseSixRegionThreePercent", disperseFieldSixRegion.get(2).getDisperseTotal() + "%");
            map.put("disperseSixRegionFour", disperseFieldSixRegion.get(3).getAreaName());
            map.put("disperseSixRegionFourPercent", disperseFieldSixRegion.get(3).getDisperseTotal() + "%");
            map.put("disperseSixRegionFive", disperseFieldSixRegion.get(4).getAreaName());
            map.put("disperseSixRegionFivePercent", disperseFieldSixRegion.get(4).getDisperseTotal() + "%");
            map.put("disperseSixRegionSix", disperseFieldSixRegion.get(5).getAreaName());
            map.put("disperseSixRegionSixPercent", disperseFieldSixRegion.get(5).getDisperseTotal() + "%");

            /*----------图片22-全国六大区{{crop}}秸秆分散利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : disperseFieldSixRegion) {
                mapData.put(sum.getAreaName(), sum.getDisperseTotal().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "分散利用比例", mapData);
            byte[] pictureTwentyFour = barChart("全国六大区" + strawType + "分散利用比例", theoryResourceMap, "分散利用比例", "%", 500, 400);
            map.put("pictureTwentyFour", new PictureRenderData(500, 400, ".png", pictureTwentyFour));


            /*---------------秸秆离田利用情况------------------*/
            /*--------------农户分散利用分省来看------------------*/
            //单作物农户分散利用量占秸秆可收集量的比例
            ArrayList<StrawUtilizeSum> main = new ArrayList<>();
            main.addAll(list);
            for (StrawUtilizeSum sum : main) {
                BigDecimal mainPercent = sum.getMainTotal().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setMainTotal(mainPercent);
            }
            main.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getMainTotal().compareTo(o1.getMainTotal());
                }
            });
            map.put("mainProvinceOne", mapsByCodes.get(main.get(0).getAreaId()));
            map.put("mainProvinceOnePercent", main.get(0).getMainTotal() + "%");
            map.put("mainProvinceTwo", mapsByCodes.get(main.get(1).getAreaId()));
            map.put("mainProvinceTwoPercent", main.get(1).getMainTotal() + "%");
            map.put("mainProvinceThree", mapsByCodes.get(main.get(2).getAreaId()) + "、" + mapsByCodes.get(main.get(3).getAreaId()) + "、" + mapsByCodes.get(main.get(4).getAreaId())
                    + "、" + mapsByCodes.get(main.get(5).getAreaId()) + "、" + mapsByCodes.get(main.get(6).getAreaId()) + "、" + mapsByCodes.get(main.get(7).getAreaId()) + "、" + mapsByCodes.get(main.get(8).getAreaId()));
            map.put("mainProvinceThreePercent", main.get(8).getMainTotal() + "%");
            map.put("mainProvinceNinePercent", main.get(2).getMainTotal() + "%");
            map.put("mainProvinceFour", mapsByCodes.get(main.get(9).getAreaId()) + "、" + mapsByCodes.get(main.get(10).getAreaId()) + "、" + mapsByCodes.get(main.get(11).getAreaId())
                    + "、" + mapsByCodes.get(main.get(12).getAreaId()) + "、" + mapsByCodes.get(main.get(13).getAreaId()));
            map.put("mainProvinceTenPercent", main.get(13).getMainTotal() + "%");
            map.put("mainProvinceFourteenPercent", main.get(9).getMainTotal() + "%");
            map.put("mainProvinceFive", mapsByCodes.get(main.get(14).getAreaId()) + "、" + mapsByCodes.get(main.get(15).getAreaId()) + "、" + mapsByCodes.get(main.get(16).getAreaId())
                    + "、" + mapsByCodes.get(main.get(17).getAreaId()) + "、" + mapsByCodes.get(main.get(18).getAreaId()));
            map.put("mainProvinceFifteenPercent", main.get(18).getMainTotal() + "%");
            map.put("mainProvinceNineteenPercent", main.get(14).getMainTotal() + "%");

            /*----------图片25-全国各省区{{crop}}秸秆市场主体规模化利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : main) {
                mapData.put(mapsByCodes.get(sum.getAreaId()), sum.getMainTotal().doubleValue());
            }
            theoryResourceMap.put("全国各省区" + strawType + "秸秆市场主体规模化利用比例", mapData);
            byte[] pictureTwentyFive = barChart("全国各省区" + strawType + "秸秆市场主体规模化利用比例", theoryResourceMap, "秸秆市场主体规模化利用比例", "%", 500, 400);
            map.put("pictureTwentyFive", new PictureRenderData(500, 400, ".png", pictureTwentyFive));

            ArrayList<StrawUtilizeSum> mainFieldSixRegion = new ArrayList<>();
            mainFieldSixRegion.addAll(sumArrayList);

            /*--------------主体利用比例分区来看------------------*/
            for (StrawUtilizeSum sum : mainFieldSixRegion) {
                //计算还田比例
                BigDecimal mainFieldPercent = sum.getMainTotal().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                sum.setMainTotal(mainFieldPercent);
            }
            mainFieldSixRegion.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getMainTotal().compareTo(o1.getMainTotal());
                }
            });
            map.put("mainSixRegionOne", mainFieldSixRegion.get(0).getAreaName());
            map.put("mainSixRegionOnePercent", mainFieldSixRegion.get(0).getMainTotal() + "%");
            map.put("mainSixRegionTwo", mainFieldSixRegion.get(1).getAreaName());
            map.put("mainSixRegionTwoPercent", mainFieldSixRegion.get(1).getMainTotal() + "%");
            map.put("mainSixRegionThree", mainFieldSixRegion.get(2).getAreaName());
            map.put("mainSixRegionThreePercent", mainFieldSixRegion.get(2).getMainTotal() + "%");
            map.put("mainSixRegionFour", mainFieldSixRegion.get(3).getAreaName());
            map.put("mainSixRegionFourPercent", mainFieldSixRegion.get(3).getMainTotal() + "%");
            map.put("mainSixRegionFive", mainFieldSixRegion.get(4).getAreaName());
            map.put("mainSixRegionFivePercent", mainFieldSixRegion.get(4).getMainTotal() + "%");
            map.put("mainSixRegionSix", mainFieldSixRegion.get(5).getAreaName());
            map.put("mainSixRegionSixPercent", mainFieldSixRegion.get(5).getMainTotal() + "%");

            /*----------图片22-全国六大区{{crop}}秸秆分散利用比例--------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : mainFieldSixRegion) {
                mapData.put(sum.getAreaName(), sum.getMainTotal().doubleValue());
            }
            theoryResourceMap.put("全国六大区" + strawType + "主体利用比例", mapData);
            byte[] pictureTwentySix = barChart("全国六大区" + strawType + "主体利用比例", theoryResourceMap, "主体利用比例", "%", 500, 400);
            map.put("pictureTwentySix", new PictureRenderData(500, 400, ".png", pictureTwentySix));
        }
        return map;
    }

   /*
    @Override
    public void getMajorYieldReportTotal(HttpServletRequest request, HttpServletResponse response, String year) {
        //玉米秸秆产生量
        //若不输入年度,则取当前年的上一年度
        if(StringUtils.isEmpty(year)||StringUtils.isNumeric(year)==false){
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
        }
        //获取数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //获取全国省份
        List<String> areaCodes = new ArrayList<>();
        //获取当前区域下一级areacode
        Result<List<SysRegionTreeVo>> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (regionTree.getData().size() > 0) {
            for (SysRegionTreeVo treeDatum : regionTree.getData()) {
                areaCodes.add(treeDatum.getRegionCode());
            }
        } else {
            throw new SofnException("下级区域异常");
        }
        //查询当前产量最高的作物
        List<StrawUtilizeSum> byStrawType = reportDataMapper.getSumGroupByStrawType(year, areaCodes, status, "theory_resource");
        String strawType = "";
        if (byStrawType != null && byStrawType.size() > 0) {
            strawType = byStrawType.get(0).getStrawType();
        } else {
            throw new SofnException("当年未有作物产量");
        }
        Map<String, Object> mapOne = this.getMajorYieldReport(year, byStrawType.get(0).getStrawType());
        Map<String, Object> mapTwo = this.getMajorYieldReport(year, byStrawType.get(1).getStrawType());
        Map<String, Object> mapThree  = this.getMajorYieldReport(year, byStrawType.get(2).getStrawType());
        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(mapOne);
        documents.add(mapTwo);
        documents.add(mapThree);
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment; filename=" + "不同地区秸秆产生与利用情况分析报告");
            ServletOutputStream out = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            XWPFTemplate.compile("D:\\idea-workspace\\sofn-eep\\ducss-service\\src\\main\\resources\\static\\majorYieldTwo.docx").render(new HashMap<String, Object>() {
                {
                    put("majorYield",documents);
                }
            }).write(bos);
            bos.flush();
            bos.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */


    @Override
    public Map<String, Object> getDifferentRegionReportMap(String year, List<String> areaCodes, String regionCode, String regionName) {
        /*---------------------------数据查询区域-----------------------*/
        //获取数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //获取全国省份
        // List<String> areaCodes = new ArrayList<>();
        //获取当前区域下一级areacode
        /*Result<List<SysRegionTreeVo>> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (regionTree.getData().size() > 0) {
            for (SysRegionTreeVo treeDatum : regionTree.getData()) {
                areaCodes.add(treeDatum.getRegionCode());
            }
        } else {
            throw new SofnException("下级区域异常");
        }*/
        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>();
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        /*-----------------当年数据区域-------------*/
        //查询全国所有作物数据
        StrawUtilizeSumResVo nationalData = reportDataMapper.getSumStrawUtilizeByAreaCode(year, areaCodes, status);
        //指定大区数据
        List<String> northeastRegionList = Arrays.asList(regionCode.split(","));
        StrawUtilizeSumResVo regionalData = reportDataMapper.getSumStrawUtilizeByAreaCode(year, northeastRegionList, status);
        //指定区域数据分秸秆类型
        List<StrawUtilizeSum> strawSumGroupByStrawType = dateShowMapper.getStrawSumGroupByStrawType(year, northeastRegionList, status);

        /*-----------------上一年数据区域-----------------*/
        String beforeYear = String.valueOf(Integer.parseInt(year) - 1);
        //查询全国所有作物数据
        StrawUtilizeSumResVo beforeYearNationalData = reportDataMapper.getSumStrawUtilizeByAreaCode(beforeYear, areaCodes, status);
        //指定大区数据
        List<String> beforeYearNortheastRegionList = Arrays.asList(regionCode.split(","));
        StrawUtilizeSumResVo beforeYearRegionalData = reportDataMapper.getSumStrawUtilizeByAreaCode(beforeYear, northeastRegionList, status);
        //指定区域数据分秸秆类型
        List<StrawUtilizeSum> beforeYearSumStrawType = dateShowMapper.getStrawSumGroupByStrawType(beforeYear, northeastRegionList, status);

        Map<String, Object> map = new HashMap<>(12);
        if (nationalData != null && regionalData != null && strawSumGroupByStrawType != null) {
            /*-----------------------产生量-----------------*/
            map.put("dbq", regionName);
            map.put("year", year);
            map.put("dbTheoryResource", regionalData.getTheoryResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            map.put("dbTheoryResourcePercent", regionalData.getTheoryResource().divide(nationalData.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            map.put("beforeYear", beforeYear);
            //获取两年数据比较值
            BigDecimal upOrDownPercent = BigDecimal.ZERO;
            if (beforeYearRegionalData == null || beforeYearNationalData == null) {
                map.put("upOrDownPercent", upOrDownPercent);
            } else {
                upOrDownPercent = regionalData.getTheoryResource().divide(nationalData.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP).subtract(beforeYearRegionalData.getTheoryResource().divide(beforeYearNationalData.getTheoryResource(), 2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal("100"));
            }
            if (upOrDownPercent.compareTo(BigDecimal.ZERO) > 0) {
                map.put("upOrDownPercent", "上升" + upOrDownPercent.abs());
            } else {
                map.put("upOrDownPercent", "下降" + upOrDownPercent.abs());
            }
            //当年数据排序
            strawSumGroupByStrawType.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getTheoryResource().compareTo(o1.getTheoryResource());
                }
            });
            //历年数据排序
            beforeYearSumStrawType.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getTheoryResource().compareTo(o1.getTheoryResource());
                }
            });
            Map<String, Double> mapOne = new HashMap<String, Double>(12);
            Map<String, Double> mapTwo = new HashMap<String, Double>(12);
            Map<String, Double> mapThree = new HashMap<String, Double>(12);
            Map<String, Double> mapFour = new HashMap<String, Double>(12);
            Map<String, Double> mapFive = new HashMap<String, Double>(12);
            Map<String, Double> mapSix = new HashMap<String, Double>(12);
            Map<String, Double> mapSeven = new HashMap<String, Double>(12);
            Map<String, Double> mapEight = new HashMap<String, Double>(12);
            Map<String, Double> mapNine = new HashMap<String, Double>(12);
            Map<String, BigDecimal> nowYearMap = strawSumGroupByStrawType.stream().collect(Collectors.toMap(StrawUtilizeSum::getStrawType, StrawUtilizeSum::getTheoryResource));
            Map<String, BigDecimal> beforeYearMap = beforeYearSumStrawType.stream().collect(Collectors.toMap(StrawUtilizeSum::getStrawType, StrawUtilizeSum::getTheoryResource));
            mapOne.put(year, nowYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            if (beforeYearRegionalData == null) {
                mapOne.put(beforeYear, 0.0);
                mapTwo.put(year, nowYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapTwo.put(beforeYear, 0.0);
                mapThree.put(year, nowYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapThree.put(beforeYear, 0.0);
                mapFour.put(year, nowYearMap.get(strawSumGroupByStrawType.get(3).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFour.put(beforeYear, 0.0);
                mapFive.put(year, nowYearMap.get(strawSumGroupByStrawType.get(4).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFive.put(beforeYear, 0.0);
                mapSix.put(year, nowYearMap.get(strawSumGroupByStrawType.get(5).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSix.put(beforeYear, 0.0);
                mapSeven.put(year, nowYearMap.get(strawSumGroupByStrawType.get(6).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSeven.put(beforeYear, 0.0);
                mapEight.put(year, nowYearMap.get(strawSumGroupByStrawType.get(7).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapEight.put(beforeYear, 0.0);
                mapNine.put(year, nowYearMap.get(strawSumGroupByStrawType.get(8).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapNine.put(beforeYear, 0.0);
            } else {
                mapOne.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapTwo.put(year, nowYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapTwo.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapThree.put(year, nowYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapThree.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFour.put(year, nowYearMap.get(strawSumGroupByStrawType.get(3).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFour.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(3).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFive.put(year, nowYearMap.get(strawSumGroupByStrawType.get(4).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFive.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(4).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSix.put(year, nowYearMap.get(strawSumGroupByStrawType.get(5).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSix.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(5).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSeven.put(year, nowYearMap.get(strawSumGroupByStrawType.get(6).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSeven.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(6).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapEight.put(year, nowYearMap.get(strawSumGroupByStrawType.get(7).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapEight.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(7).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapNine.put(year, nowYearMap.get(strawSumGroupByStrawType.get(8).getStrawType()).divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapNine.put(beforeYear, beforeYearMap.get(strawSumGroupByStrawType.get(8).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            }
            Map<String, Map<String, Double>> theoryResourceMap = new LinkedHashMap<>(12);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(0).getStrawType()), mapOne);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(1).getStrawType()), mapTwo);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(2).getStrawType()), mapThree);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(3).getStrawType()), mapFour);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(4).getStrawType()), mapFive);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(5).getStrawType()), mapSix);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(6).getStrawType()), mapSeven);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(7).getStrawType()), mapEight);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(8).getStrawType()), mapNine);
            byte[] pictureOne = barChart(regionName + "不同农作物秸秆产生量构成", theoryResourceMap, "不同农作物秸秆产生量构成", "%", 500, 400);
            map.put("pictureOne", new PictureRenderData(500, 400, ".png", pictureOne));
            map.put("cropOne", strawTypeMap.get(strawSumGroupByStrawType.get(0).getStrawType()));
            map.put("dbcropOneTheoryResource", strawSumGroupByStrawType.get(0).getTheoryResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal dbCropOneTheoryResourcePercent = strawSumGroupByStrawType.get(0).getTheoryResource().divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            map.put("dbCropOneTheoryResourcePercent", dbCropOneTheoryResourcePercent + "%");
            BigDecimal dbBeforeYearCropOneTheoryResourcePercent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                dbBeforeYearCropOneTheoryResourcePercent = beforeYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal subtractCropOne = dbCropOneTheoryResourcePercent.subtract(dbBeforeYearCropOneTheoryResourcePercent);
            if (subtractCropOne.compareTo(BigDecimal.ZERO) > 0) {
                map.put("upOrDownPercentOne", "上升了" + subtractCropOne.abs());
            } else {
                map.put("upOrDownPercentOne", "下降了" + subtractCropOne.abs());
            }
            BigDecimal dbTheoryResourceTwo = strawSumGroupByStrawType.get(0).getTheoryResource().divide(nationalData.getReturnResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            //产生量占全国占比
            map.put("dbTheoryResourceTwo", dbTheoryResourceTwo + "%");
            //去年产生量占全国占比
            BigDecimal beforeDbTheoryResourceTwo = BigDecimal.ZERO;
            if (beforeYearNationalData != null) {
                beforeDbTheoryResourceTwo = beforeYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(beforeYearNationalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal upOrDownPercentTwo = dbTheoryResourceTwo.subtract(beforeDbTheoryResourceTwo);
            if (upOrDownPercentTwo.compareTo(BigDecimal.ZERO) > 0) {
                map.put("upOrDownPercentTwo", "上升了" + upOrDownPercentTwo.abs());
            } else {
                map.put("upOrDownPercentTwo", "下降了" + upOrDownPercentTwo.abs());
            }
            map.put("dbCropTwo", strawTypeMap.get(strawSumGroupByStrawType.get(1).getStrawType()));
            map.put("dbcropTwoTheoryResource", nowYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal dbcropTwoTheoryResourcePecent = strawSumGroupByStrawType.get(1).getTheoryResource().divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            map.put("dbcropTwoTheoryResourcePecent", dbcropTwoTheoryResourcePecent + "%");
            //去年
            BigDecimal beforeYearDbcropTwoTheoryResourcePecent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                beforeYearDbcropTwoTheoryResourcePecent = beforeYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal upOrDownPercentThree = dbcropTwoTheoryResourcePecent.subtract(beforeYearDbcropTwoTheoryResourcePecent);
            if (upOrDownPercentThree.compareTo(BigDecimal.ZERO) > 0) {
                map.put("upOrDownPercentThree", "上升了" + upOrDownPercentThree.abs());
            } else {
                map.put("upOrDownPercentThree", "下降了" + upOrDownPercentThree.abs());
            }
            //第三种作物
            map.put("cropThree", strawTypeMap.get(strawSumGroupByStrawType.get(2).getStrawType()));
            map.put("dbcropThreeTheoryResource", nowYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal dbcropThreeTheoryResourcePercent = strawSumGroupByStrawType.get(2).getTheoryResource().divide(regionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            map.put("dbcropThreeTheoryResourcePercent", dbcropThreeTheoryResourcePercent + "%");
            //去年
            BigDecimal beforeYeardbcropThreeTheoryResourcePercent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                beforeYeardbcropThreeTheoryResourcePercent = beforeYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(beforeYearRegionalData.getTheoryResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal upOrDownPercentFour = dbcropThreeTheoryResourcePercent.subtract(beforeYeardbcropThreeTheoryResourcePercent);
            if (upOrDownPercentThree.compareTo(BigDecimal.ZERO) > 0) {
                map.put("upOrDownPercentFour", "上升了" + upOrDownPercentFour.abs());
            } else {
                map.put("upOrDownPercentFour", "下降了" + upOrDownPercentFour.abs());
            }

            /*---------------------可收集---------------------------*/
            //当年数据排序
            strawSumGroupByStrawType.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getCollectResource().compareTo(o1.getCollectResource());
                }
            });
            //历年数据排序
            beforeYearSumStrawType.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getTheoryResource().compareTo(o1.getTheoryResource());
                }
            });
            Map<String, BigDecimal> dbNowYearMap = strawSumGroupByStrawType.stream().collect(Collectors.toMap(StrawUtilizeSum::getStrawType, StrawUtilizeSum::getCollectResource));
            Map<String, BigDecimal> dbBeforeYearMap = beforeYearSumStrawType.stream().collect(Collectors.toMap(StrawUtilizeSum::getStrawType, StrawUtilizeSum::getCollectResource));
            map.put("dbCollectResource", regionalData.getCollectResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            map.put("dbCollectResourcePercent", regionalData.getCollectResource().divide(nationalData.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).toString() + "%");
            //获取两年数据比较值
            BigDecimal cRUpOrDownPercent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                cRUpOrDownPercent = regionalData.getCollectResource().divide(nationalData.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP).subtract(beforeYearRegionalData.getCollectResource().divide(beforeYearNationalData.getCollectResource(), 2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal("100"));
            }
            if (cRUpOrDownPercent.compareTo(BigDecimal.ZERO) > 0) {
                map.put("cRUpOrDownPercent", "上升" + cRUpOrDownPercent.abs());
            } else {
                map.put("cRUpOrDownPercent", "下降" + cRUpOrDownPercent.abs());
            }
            map.put("dbCropOne", strawTypeMap.get(strawSumGroupByStrawType.get(0).getStrawType()));
            map.put("dbcropOneCollectResource", strawSumGroupByStrawType.get(0).getCollectResource().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal dbCropOneCollectResourcePercent = strawSumGroupByStrawType.get(0).getCollectResource().divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            map.put("dbCropOneCollectResourcePercent", dbCropOneCollectResourcePercent + "%");
            BigDecimal dbBeforeYearCropOneCollectResourcePercent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                dbBeforeYearCropOneCollectResourcePercent = dbBeforeYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal cRUpOrDownPercentOne = dbCropOneCollectResourcePercent.subtract(dbBeforeYearCropOneCollectResourcePercent);
            if (cRUpOrDownPercentOne.compareTo(BigDecimal.ZERO) > 0) {
                map.put("cRUpOrDownPercentOne", "上升" + cRUpOrDownPercentOne.abs());
            } else {
                map.put("cRUpOrDownPercentOne", "上升" + cRUpOrDownPercentOne.abs());
            }

            map.put("dbCropTwo", strawTypeMap.get(strawSumGroupByStrawType.get(1).getStrawType()));
            map.put("dbCropTwoCollectResource", strawSumGroupByStrawType.get(1).getCollectResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal dbCropTwoCollectResourcePercent = strawSumGroupByStrawType.get(1).getCollectResource().divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            map.put("dbCropTwoCollectResourcePercent", dbCropTwoCollectResourcePercent + "%");
            BigDecimal dbBeforeYearCropTwoCollectResourcePercent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                dbBeforeYearCropTwoCollectResourcePercent = dbBeforeYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal cRUpOrDownPercentTwo = dbCropTwoCollectResourcePercent.subtract(dbBeforeYearCropTwoCollectResourcePercent);
            if (cRUpOrDownPercentTwo.compareTo(BigDecimal.ZERO) > 0) {
                map.put("cRUpOrDownPercentTwo", "上升" + cRUpOrDownPercentTwo.abs());
            } else {
                map.put("cRUpOrDownPercentTwo", "上升" + cRUpOrDownPercentTwo.abs());
            }

            map.put("dbCropThree", strawTypeMap.get(strawSumGroupByStrawType.get(2).getStrawType()));
            map.put("dbCropThreeCollectResource", strawSumGroupByStrawType.get(2).getCollectResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            BigDecimal dbCropThreeCollectResourcePercent = strawSumGroupByStrawType.get(2).getCollectResource().divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            map.put("dbCropThreeCollectResourcePercent", dbCropThreeCollectResourcePercent + "%");
            BigDecimal dbBeforeYearCropThreeCollectResourcePercent = BigDecimal.ZERO;
            if (beforeYearRegionalData != null) {
                dbBeforeYearCropThreeCollectResourcePercent = dbBeforeYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal cRUpOrDownPercentThree = dbCropThreeCollectResourcePercent.subtract(dbBeforeYearCropThreeCollectResourcePercent);
            if (cRUpOrDownPercentThree.compareTo(BigDecimal.ZERO) > 0) {
                map.put("cRUpOrDownPercentThree", "上升" + cRUpOrDownPercentThree.abs());
            } else {
                map.put("cRUpOrDownPercentThree", "上升" + cRUpOrDownPercentThree.abs());
            }

            /*-----------------------pictureTwo图片2不同农作物秸秆可收集量构成----------------------------*/
            if (beforeYearRegionalData == null) {
                mapOne.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapOne.put(beforeYear, 0.0);
                mapTwo.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapTwo.put(beforeYear, 0.0);
                mapThree.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapThree.put(beforeYear, 0.0);
                mapFour.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(3).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFour.put(beforeYear, 0.0);
                mapFive.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(4).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFive.put(beforeYear, 0.0);
                mapSix.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(5).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSix.put(beforeYear, 0.0);
                mapSeven.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(6).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSeven.put(beforeYear, 0.0);
                mapEight.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(7).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapEight.put(beforeYear, 0.0);
                mapNine.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(8).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapNine.put(beforeYear, 0.0);

            } else {
                mapOne.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapOne.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(0).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapTwo.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapTwo.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(1).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapThree.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapThree.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(2).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFour.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(3).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFour.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(3).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFive.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(4).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapFive.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(4).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSix.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(5).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSix.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(5).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSeven.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(6).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapSeven.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(6).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapEight.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(7).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapEight.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(7).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapNine.put(year, dbNowYearMap.get(strawSumGroupByStrawType.get(8).getStrawType()).divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                mapNine.put(beforeYear, dbBeforeYearMap.get(strawSumGroupByStrawType.get(8).getStrawType()).divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            }
            theoryResourceMap.clear();
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(0).getStrawType()), mapOne);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(1).getStrawType()), mapTwo);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(2).getStrawType()), mapThree);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(3).getStrawType()), mapFour);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(4).getStrawType()), mapFive);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(5).getStrawType()), mapSix);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(6).getStrawType()), mapSeven);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(7).getStrawType()), mapEight);
            theoryResourceMap.put(strawTypeMap.get(strawSumGroupByStrawType.get(8).getStrawType()), mapNine);
            byte[] pictureTwo = barChart(regionName + "不同农作物秸秆可收集量构成", theoryResourceMap, "不同农作物秸秆可收集量构成", "%", 500, 400);
            map.put("pictureTwo", new PictureRenderData(500, 400, ".png", pictureTwo));

            /*-----------------------秸秆利用情况---------------------*/
            map.put("dbProStrawUtilize", regionalData.getProStrawUtilize().divide(new BigDecimal(100000000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //肥料化
            BigDecimal fertilisingTr = regionalData.getMainFertilising().add(regionalData.getDisperseFertilising()).add(regionalData.getReturnResource());
            map.put("dbFertilising", fertilisingTr.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //饲料化
            BigDecimal forageTr = regionalData.getMainForage().add(regionalData.getDisperseForage());
            map.put("dbForage", forageTr.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //燃料化
            BigDecimal fuelTr = regionalData.getMainFuel().add(regionalData.getDisperseFuel());
            map.put("dbFuel", fuelTr.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //基料化
            BigDecimal baseTr = regionalData.getMainBase().add(regionalData.getDisperseBase());
            map.put("dbbase", baseTr.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //原料化
            BigDecimal materialTr = regionalData.getMainMaterial().add(regionalData.getDisperseMaterial());
            map.put("dbMaterial", materialTr.divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            //当年秸秆综合利用率
            BigDecimal comprehensive = BigDecimal.ZERO;
            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
            if (regionalData.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                comprehensive = regionalData.getProStrawUtilize()
                        .divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            map.put("dbcomprehensive", comprehensive + "%");
            //去年综合利用率
            BigDecimal beforeComprehensive = BigDecimal.ZERO;
            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
            if (beforeYearRegionalData != null && beforeYearRegionalData.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                beforeComprehensive = beforeYearRegionalData.getProStrawUtilize()
                        .divide(beforeYearRegionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            }
            BigDecimal psuUpOrDownPercent = comprehensive.subtract(beforeComprehensive);
            if (psuUpOrDownPercent.compareTo(BigDecimal.ZERO) > 0) {
                map.put("psuUpOrDownPercent", "上升" + psuUpOrDownPercent.abs());
            } else {
                map.put("psuUpOrDownPercent", "上升" + psuUpOrDownPercent.abs());
            }
            //肥料化、饲料化、燃料化、基料化和原料化利用量占比
            map.put("dbFertilisingPercent", fertilisingTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("dbForagePercent", forageTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("dbFuelPercent", fuelTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("dbBasePercent", baseTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("dbMaterialPercent", materialTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            theoryResourceMap.clear();
            Map<String, Double> mapData = new LinkedHashMap<>(12);
            mapData.put("肥料化利用比例", fertilisingTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            mapData.put("饲料化利用比例", forageTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            mapData.put("燃料化利用比例", fuelTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            mapData.put("基料化利用比例", baseTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            mapData.put("原料化利用比例", materialTr.divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
            theoryResourceMap.put(regionName + "秸秆“五料化”利用构成", mapData);
            byte[] pictureThree = barChart(regionName + "秸秆“五料化”利用构成", theoryResourceMap, "秸秆“五料化”利用构成", "%", 500, 400);
            map.put("pictureThree", new PictureRenderData(500, 400, ".png", pictureThree));
            //分作物来看利用率
            for (StrawUtilizeSum sum : strawSumGroupByStrawType) {
                //去年综合利用率
                BigDecimal sumByStrawTypeComprehensive = BigDecimal.ZERO;
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                    sumByStrawTypeComprehensive = sum.getProStrawUtilize()
                            .divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                }
                sum.setComprehensive(sumByStrawTypeComprehensive);
            }
            //排序
            strawSumGroupByStrawType.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getComprehensive().compareTo(o1.getComprehensive());
                }
            });
            map.put("psuCropOne", strawTypeMap.get(strawSumGroupByStrawType.get(0).getStrawType()));
            map.put("psuCropTwo", strawTypeMap.get(strawSumGroupByStrawType.get(1).getStrawType()));
            map.put("psuCropThree", strawTypeMap.get(strawSumGroupByStrawType.get(2).getStrawType()));
            map.put("psuCropFour", strawTypeMap.get(strawSumGroupByStrawType.get(3).getStrawType()));
            map.put("pusCropFive", strawTypeMap.get(strawSumGroupByStrawType.get(4).getStrawType()));
            map.put("pusCropFivePercent", strawSumGroupByStrawType.get(4).getComprehensive() + "%");
            map.put("pusCropFourteen", strawTypeMap.get(strawSumGroupByStrawType.get(13).getStrawType()));
            map.put("pusCropFourteenPercent", strawSumGroupByStrawType.get(13).getComprehensive() + "%");
            /*--------------图片4--------------------------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : strawSumGroupByStrawType) {
                mapData.put(strawTypeMap.get(sum.getStrawType()), sum.getComprehensive().doubleValue());
            }
            theoryResourceMap.put(regionName + "各类农作物秸秆综合利用率", mapData);
            byte[] pictureFour = barChart(regionName + "各类农作物秸秆综合利用率", theoryResourceMap, "各类农作物秸秆综合利用率", "%", 500, 400);
            map.put("pictureFour", new PictureRenderData(500, 400, ".png", pictureFour));
            map.put("dbComprehensiveIndex", regionalData.getComprehensiveIndex().setScale(2, 2));
            map.put("dbIndustryIndex", regionalData.getIndustrializationIndex().setScale(2, 2));
            //获取平均值
            BigDecimal totalIndustryIndex = BigDecimal.ZERO;
            for (StrawUtilizeSum sum : strawSumGroupByStrawType) {
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal industrializationIndex = (sum.getMainTotal()
                            .divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP));
                    totalIndustryIndex = totalIndustryIndex.add(industrializationIndex);
                }
            }
            BigDecimal averageIndustryIndex = totalIndustryIndex.divide(new BigDecimal(strawSumGroupByStrawType.size()), 2, RoundingMode.HALF_UP);
            BigDecimal upOrDownIndustryIndex = regionalData.getIndustrializationIndex().subtract(averageIndustryIndex);
            if (upOrDownIndustryIndex.compareTo(BigDecimal.ZERO) > 0) {
                map.put("upOrDownIndustryIndex", "略高于全国平均水平" + upOrDownIndustryIndex.setScale(2, 2));
            } else {
                map.put("upOrDownIndustryIndex", "略低于全国平均水平" + upOrDownIndustryIndex.setScale(2, 2));
            }

            /*--------------------------秸秆还田离田情况-------------------------*/
            map.put("dbReturnResource", regionalData.getReturnResource().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString());
            map.put("dbReturnResourcePercent", regionalData.getReturnResource().divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            //离田利用量 = 市场主体利用量 +　农户分散利用量
            map.put("dbLeaveField", (regionalData.getMainTotal().add(regionalData.getDisperseTotal())).divide(new BigDecimal(10000), 2, RoundingMode.HALF_UP));
            map.put("dbDisperseLeaveField", (regionalData.getDisperseTotal()).divide(new BigDecimal(10000), 2, RoundingMode.HALF_UP));
            map.put("dbMainUtilize", (regionalData.getMainTotal()).divide(new BigDecimal(10000), 2, RoundingMode.HALF_UP));
            map.put("dbqDisperseLeaveFieldPercent", regionalData.getDisperseTotal().divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");
            map.put("dbMainUtilizePercent", regionalData.getMainTotal().divide(regionalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")) + "%");

            //还田比例计算并排序排序
            List<StrawUtilizeSum> returnRatio = new ArrayList<>(strawSumGroupByStrawType);
            for (StrawUtilizeSum sum : returnRatio) {
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                    //计算还田比例
                    sum.setReturnRatio(sum.getProStillField().divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
                } else {
                    sum.setMainTotal(BigDecimal.ZERO);
                }
            }
            returnRatio.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getReturnRatio().compareTo(o1.getReturnRatio());
                }
            });
            map.put("rrCropOne", strawTypeMap.get(returnRatio.get(0).getStrawType()));
            map.put("rrCropOnePercent", returnRatio.get(0).getReturnRatio() + "%");
            map.put("rrCropTwo", strawTypeMap.get(returnRatio.get(1).getStrawType()));
            map.put("rrCropThree", strawTypeMap.get(returnRatio.get(2).getStrawType()));
            map.put("rrCropFour", strawTypeMap.get(returnRatio.get(3).getStrawType()));
            map.put("rrCropFive", strawTypeMap.get(returnRatio.get(4).getStrawType()));
            map.put("rrCropFivePercent", returnRatio.get(4).getReturnRatio() + "%");
            map.put("rrCropTwoPercent", returnRatio.get(1).getReturnRatio() + "%");
            map.put("rrCropFourteen", strawTypeMap.get(returnRatio.get(13).getStrawType()));
            map.put("rrCropFourteenPercent", returnRatio.get(13).getReturnRatio() + "%");
            /*-------------------------------图片five-----------------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : returnRatio) {
                mapData.put(strawTypeMap.get(sum.getStrawType()), sum.getReturnRatio().doubleValue());
            }
            theoryResourceMap.put(regionName + "各类农作物秸秆直接还田比例", mapData);
            byte[] pictureFive = barChart(regionName + "各类农作物秸秆直接还田比例", theoryResourceMap, "各类农作物秸秆直接还田比例", "%", 500, 400);
            map.put("pictureFive", new PictureRenderData(500, 400, ".png", pictureFive));

            /*--------------分散利用比例----------------*/
            List<StrawUtilizeSum> disPercent = new ArrayList<>(strawSumGroupByStrawType);
            for (StrawUtilizeSum sum : disPercent) {
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                    sum.setDisperseTotal(sum.getDisperseTotal().divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP));
                } else {
                    sum.setMainTotal(BigDecimal.ZERO);
                }
            }
            disPercent.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getDisperseTotal().compareTo(o1.getDisperseTotal());
                }
            });
            map.put("disCropOne", strawTypeMap.get(disPercent.get(0).getStrawType()));
            map.put("disCropOnePercent", disPercent.get(0).getDisperseTotal() + "%");
            map.put("disCropTwo", strawTypeMap.get(disPercent.get(1).getStrawType()));
            map.put("disCropThree", strawTypeMap.get(disPercent.get(2).getStrawType()));
            map.put("disCropFour", strawTypeMap.get(disPercent.get(3).getStrawType()));
            map.put("disCropFourPercent", disPercent.get(3).getDisperseTotal() + "%");
            map.put("disCropTwoPercent", disPercent.get(1).getDisperseTotal() + "%");
            map.put("disCropFourteen", strawTypeMap.get(disPercent.get(13).getStrawType()));
            map.put("disCropFourteenPercent", disPercent.get(13).getDisperseTotal() + "%");

            /*-------------------------------图片Six-----------------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : disPercent) {
                mapData.put(strawTypeMap.get(sum.getStrawType()), sum.getDisperseTotal().doubleValue());
            }
            theoryResourceMap.put(regionName + "各类农作物秸秆农户分散利用比例", mapData);
            byte[] pictureSix = barChart(regionName + "各类农作物秸秆农户分散利用比例", theoryResourceMap, "各类农作物秸秆农户分散利用比例", "%", 500, 400);
            map.put("pictureSix", new PictureRenderData(500, 400, ".png", pictureSix));

            /*-----------------市场化主体利用比例-------------------*/
            List<StrawUtilizeSum> mainPercent = new ArrayList<>(strawSumGroupByStrawType);
            for (StrawUtilizeSum sum : mainPercent) {
                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                    sum.setMainTotal(sum.getMainTotal().divide(sum.getCollectResource(), 2, RoundingMode.HALF_UP));
                } else {
                    sum.setMainTotal(BigDecimal.ZERO);
                }
            }
            mainPercent.sort(new Comparator<StrawUtilizeSum>() {
                @Override
                public int compare(StrawUtilizeSum o1, StrawUtilizeSum o2) {
                    return o2.getMainTotal().compareTo(o1.getMainTotal());
                }
            });
            map.put("mainCropOne", strawTypeMap.get(mainPercent.get(0).getStrawType()));
            map.put("mainCropOnePercent", mainPercent.get(0).getMainTotal() + "%");
            map.put("mainCropTwo", strawTypeMap.get(mainPercent.get(1).getStrawType()));
            map.put("mainCropThree", strawTypeMap.get(mainPercent.get(2).getStrawType()));
            map.put("mainCropFour", strawTypeMap.get(mainPercent.get(3).getStrawType()));
            map.put("mainCropFive", strawTypeMap.get(mainPercent.get(4).getStrawType()));
            map.put("mainCropFivePercent", mainPercent.get(4).getMainTotal() + "%");
            map.put("mainCropTwoPercent", mainPercent.get(1).getMainTotal() + "%");
            map.put("mainCropTwelve", strawTypeMap.get(mainPercent.get(11).getStrawType()));
            map.put("mainCropThirteen", strawTypeMap.get(mainPercent.get(12).getStrawType()));
            map.put("mainCropFourteen", strawTypeMap.get(mainPercent.get(13).getStrawType()));
            map.put("mainCropTwelvePercent", mainPercent.get(11).getMainTotal() + "%");

            /*-------------------------------图片Seven-----------------------*/
            mapData.clear();
            theoryResourceMap.clear();
            for (StrawUtilizeSum sum : mainPercent) {
                mapData.put(strawTypeMap.get(sum.getStrawType()), sum.getMainTotal().doubleValue());
            }
            theoryResourceMap.put(regionName + "各类农作物秸秆市场化主体利用比例", mapData);
            byte[] pictureSeven = barChart(regionName + "各类农作物秸秆市场化主体利用比例", theoryResourceMap, "各类农作物秸秆市场化主体利用比例", "%", 500, 400);
            map.put("pictureSeven", new PictureRenderData(500, 400, ".png", pictureSeven));
        }
        return map;
    }


    /*
    @Override
    public void getDifferentRegionReport(HttpServletRequest request, HttpServletResponse response, String year) {
        //获取东北区
        Map<String, Object> mapOne = this.getDifferentRegionReportMap(year, SixRegionEnum.NORTHEAST_REGION.getCode(), SixRegionEnum.NORTHEAST_REGION.getDescription());
        Map<String, Object> mapTwo = this.getDifferentRegionReportMap(year, SixRegionEnum.NORTH_REGION.getCode(), SixRegionEnum.NORTH_REGION.getDescription());
        Map<String, Object> mapThree = this.getDifferentRegionReportMap(year, SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode(), SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
        Map<String, Object> mapFour = this.getDifferentRegionReportMap(year, SixRegionEnum.SOUTH_REGION.getCode(), SixRegionEnum.SOUTH_REGION.getDescription());
        Map<String, Object> mapFive = this.getDifferentRegionReportMap(year, SixRegionEnum.NORTHWEST_REGION.getCode(), SixRegionEnum.NORTHWEST_REGION.getDescription());
        Map<String, Object> mapSix = this.getDifferentRegionReportMap(year, SixRegionEnum.SOUTHWEST_REGION.getCode(), SixRegionEnum.SOUTHWEST_REGION.getDescription());

        List<Map<String, Object>> documents = new ArrayList<>();
        documents.add(mapOne);
        documents.add(mapTwo);
        documents.add(mapThree);
        documents.add(mapFour);
        documents.add(mapFive);
        documents.add(mapSix);
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment; filename=" + "不同地区秸秆产生与利用情况分析报告");
            ServletOutputStream out = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(out);
            XWPFTemplate.compile("D:\\idea-workspace\\sofn-eep\\ducss-service\\src\\main\\resources\\static\\differentRegionTwo.docx").render(new HashMap<String, Object>() {
                {
                    put("differentRegion",documents);
                }
            }).write(bos);
            bos.flush();
            bos.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

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


    @Override
    public Map<String, Object> getProductAndUseInfoRort(String year) {
        Map<String, Object> documents = new HashMap<>(200);

        documents.put("year", year);
        documents.put("createYear", DateUtils.format(new Date(), "yyyy"));
        documents.put("createMonth", DateUtils.format(new Date(), "MM"));
        // 产生量省信息和图
        try {
            Map<String, Object> value = reportProductValueService.getValue(year);
            documents.putAll(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> areaValue = reportProductValueService.getAreaValue(year);
            documents.putAll(areaValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> strawValue = reportProductValueService.getStrawValue(year);
            documents.putAll(strawValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> collectionResourceValue = reportCollectionResourceValueService.getProvinceInfo(year);
            documents.putAll(collectionResourceValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> provinceInfo = reportCollectionResourceValueService.getUtlize(year);
            documents.putAll(provinceInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> utilzeRateInfoGroupProvince = reportCollectionResourceValueService.getUtilzeRateInfoGroupProvince(year);
            documents.putAll(utilzeRateInfoGroupProvince);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> myInfo = reportInfoService.getMyInfo(year);
            createTable(myInfo);
            documents.putAll(myInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> returnInfo = reportCollectionResourceValueService.getReturnInfo(year);
            documents.putAll(returnInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Map<String, Object> fiveMaterials = reportCollectionResourceValueService.getFiveMaterials(year);
            documents.putAll(fiveMaterials);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\heyongjie\\Desktop\\部级模板\\old-clf\\data.docx"));
//            XWPFTemplate.compile("C:\\Users\\heyongjie\\Desktop\\部级模板\\old-clf\\real-template.docx").render(documents).write(fileOutputStream);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return documents;
    }


    private void createTable(Map<String, Object> myInfo) {

        if (!CollectionUtils.isEmpty(myInfo)) {
            // 全国不同地区农作物秸秆饲料化利用比例信息
            getPicture(myInfo, "my_table_1", "农户分散利用比例");

            //全国六大区农作物秸秆燃料化利用比例
            getPicture(myInfo, "my_table_2", "燃料化利用比例");

            // 全国六大区农作物秸秆基料化利用比例
            getPicture(myInfo, "my_table_3", "基料化利用比例");

            // 全国六大区农作物秸秆原料化利用比例
            getPicture(myInfo, "my_table_4", "原料化利用比例");

            // my_table_5
            getPicture(myInfo, "my_table_5", "产业化利用能力指数");

            // my_table_6
            getPicture(myInfo, "my_table_6", "肥料化利用比例");
            getPicture(myInfo, "my_table_7", "直接还田比例");
            getPicture(myInfo, "my_table_8", "直接还田比例");
            // 全国六大区农作物秸秆原料化利用比例

        }

    }

    /**
     * 将Map中的图片参数转为图片
     *
     * @param myInfo    信息
     * @param paramName 参数  名称
     * @param title     图片标题
     */
    private void getPicture(Map<String, Object> myInfo, String paramName, String title) {
        if (!CollectionUtils.isEmpty(myInfo)) {
            Object my_table_4 = myInfo.get(paramName);
            if (my_table_4 != null) {
                Map<String, Object> myTable2 = (Map) my_table_4;
                Map<String, Map<String, Double>> datas = Maps.newHashMap();
                Map<String, Double> realData = Maps.newLinkedHashMap();
                for (String key : myTable2.keySet()) {
                    if (myTable2.get(key) != null) {
                        String val = myTable2.get(key).toString();
                        val = val.replaceAll("%", "");
                        realData.put(key, (new BigDecimal(val)).doubleValue());
                    }
                }
                datas.put(title, realData);
                byte[] pictureFive = JfreeUtil.barChart(title,
                        datas, "", "%", 500, 400);
                myInfo.put(paramName, new PictureRenderData(500, 400, ".png", pictureFive));
            }
        }

    }

}
