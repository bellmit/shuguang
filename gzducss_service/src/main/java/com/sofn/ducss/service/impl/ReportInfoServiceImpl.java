/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-12-10 9:35
 */
package com.sofn.ducss.service.impl;


import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.ReportDataMapper;
import com.sofn.ducss.mapper.StrawUtilizeSumTotalMapper;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.model.StrawUtilizeSumTotal;
import com.sofn.ducss.service.ReportInfoService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.util.SysDictUtil;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class ReportInfoServiceImpl implements ReportInfoService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private StrawUtilizeSumTotalMapper strawUtilizeSumTotalMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @Override
    public Map<String, Object> getMyInfo(String year) {
        //若不输入年度,则取当前年的上一年度
        if (StringUtils.isEmpty(year) || StringUtils.isNumeric(year) == false) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
        }
        DecimalFormat df = new DecimalFormat("0.00");

        //所有填充数据map
        Map<String, Object> map = new HashMap<>();

        //查询已上报和已通过的数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //获取全国省份
        List<String> areaCodes = new ArrayList<>();
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (CollectionUtils.isNotEmpty(regionTree)) {
            for (SysRegionTreeVo treeDatum : regionTree) {
                areaCodes.add(treeDatum.getRegionCode());
            }
        } else {
            throw new SofnException("下级区域异常");
        }

        //分作物对比
        String name70 = "";
        String name40 = "";
        String name30 = "";
        String minName = "";
        Double min = 0.00;

        Map<String, Object> areaAll = new HashMap<>();//全国各类农作物直接还田比例

        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>();
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
            //获取全国单作物数据
            StrawUtilizeSum theoryResource = reportDataMapper.getSumTheoryResourceByStrawType(year, areaCodes, status, sysDict.getDictcode(), "theory_resource");
            //作物直接还田比例
            Double returnRatio = theoryResource.getProStillField().divide(theoryResource.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
            if (returnRatio <= min) {
                minName = sysDict.getDictname();
                min = returnRatio;
            }
            if (returnRatio >= 70) {
                name70 = name70 + sysDict.getDictname() + "、";
            } else if (returnRatio >= 40) {
                name40 = name40 + sysDict.getDictname() + "、";
            } else if (returnRatio >= 30) {
                name30 = name30 + sysDict.getDictname() + "、";
            }
            areaAll.put(sysDict.getDictname(), returnRatio);
        }
        map.put("param_136", name70.equals("") ? name70 : name70.substring(0, name70.length() - 1));
        map.put("param_137", name40.equals("") ? name40 : name40.substring(0, name40.length() - 1));
        map.put("param_138", name30.equals("") ? name30 : name30.substring(0, name30.length() - 1));
        map.put("param_139", minName);
        map.put("param_140", df.format(min));
        map.put("my_table_8", areaAll);


        //查询全国所有作物数据
        StrawUtilizeSumResVo nationalData = reportDataMapper.getSumStrawUtilizeByAreaCode(year, areaCodes, status);
        if (nationalData != null) {
            //全国肥料化
            BigDecimal fertilisingTr = nationalData.getMainFertilising().add(nationalData.getDisperseFertilising()).add(nationalData.getReturnResource());
            //全国肥料化平均利用率
            Double fertilisingTrRate = fertilisingTr.divide(nationalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();

            //全国饲料化
            BigDecimal forageTr = nationalData.getMainForage().add(nationalData.getDisperseForage());
            //全国饲料化平均利用率
            Double forageTrRate = forageTr.divide(nationalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();

            //全国燃料化
            BigDecimal fuelTr = nationalData.getMainFuel().add(nationalData.getDisperseFuel());
            //全国燃料化平均利用率
            Double fuelTrRate = fuelTr.divide(nationalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();

            //全国基料化
            BigDecimal baseTr = nationalData.getMainBase().add(nationalData.getDisperseBase());
            //全国基料化平均利用率
            Double baseTrRate = baseTr.divide(nationalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();

            //全国原料化
            BigDecimal mTr = nationalData.getMainMaterial().add(nationalData.getDisperseMaterial());
            //全国原料化平均利用率
            Double mTrRate = mTr.divide(nationalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();

            //全国秸秆产业化利用能力指数
            BigDecimal industrializationIndexTr = nationalData.getIndustrializationIndex();

            //全国秸秆直接还田量
            BigDecimal returnResource = nationalData.getReturnResource();
            //全国直接还田比例
            BigDecimal returnRatio = nationalData.getReturnResource().divide(nationalData.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));

            //对比上一年数据
            String beforeYear = String.valueOf(Integer.parseInt(year) - 1);
            StrawUtilizeSumResVo nationalDataLast = reportDataMapper.getSumStrawUtilizeByAreaCode(beforeYear, areaCodes, status);
            //全国上一年度直接还田比例
            BigDecimal returnRatioLast = nationalDataLast.getReturnResource().divide(nationalDataLast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));

            if (returnRatio.compareTo(returnRatioLast) > -1) {
                map.put("param_116", "上升" + returnRatio.subtract(returnRatioLast));
            } else {
                map.put("param_116", "下降" + returnRatioLast.subtract(returnRatio));
            }
            map.put("param_112", year);
            map.put("param_113", BigDecimalUtil.setScale2(returnResource));
            map.put("param_114", BigDecimalUtil.setScale2(returnRatio));
            map.put("param_115", beforeYear);

            map.put("param_82", year);
            map.put("param_85", year);
            map.put("param_86", BigDecimalUtil.setScale2(industrializationIndexTr));

            map.put("param_20", df.format(forageTrRate) + "%");
            map.put("param_35", df.format(fuelTrRate) + "%");
            map.put("param_54", df.format(baseTrRate) + "%");
            map.put("param_69", df.format(mTrRate) + "%");
            map.put("my_param_12", df.format(fertilisingTrRate) + "%");
            List<StrawUtilizeSumTotal> sumTotalList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds2(areaCodes, year, status);

            //肥料化
            int iF = 0;//超过全国平均水平的省个数
            String provinceNameF = "";//超过全国平均水平的省名称
            Double fMax = 0.00;// 省肥料化最大值
            String fMaxName = "";// 肥料化最大值的省名称
            String provinceNameFor75F = "";//超过75%小于90%省名称
            int jF = 0;//超过75%小于90%省个数
            String provinceNameFor60F = "";//超过60%小于75%省名称
            String provinceNameFor45F = "";//超过45%小于60%省名称
            String provinceNameFor30F = "";//超过30%小于45%省名称
            String provinceNameFor15F = "";//超过15%小于30%省名称

            //饲料化
            int i = 0;//超过全国平均水平的省个数
            String provinceName = "";//超过全国平均水平的省名称
            String provinceNameFor90 = "";//超过90%省名称
            String provinceNameFor75 = "";//超过60%小于75%省名称
            String provinceNameFor14 = "";//小于14%省名称
            int j = 0;//小于14%个数
            String minProvinceName = "";//饲料化利用率最低省名称
            Double minProvinceRate = 0.00;//饲料化利用率最低数

            //燃料化
            int iRan = 0;//超过全国平均水平的省个数
            String provinceNameRan = "";//超过全国平均水平的省名称
            String provinceNameFor25Ran = "";//大于25%小于30%省名称
            String provinceNameFor20Ran = "";//大于20%小于25%省名称以及确定的值
            String provinceNameFor15Ran = "";//大于15%小于20%省名称
            String provinceNameFor10Ran = "";//大于10%小于15%省名称
            String provinceNameFor5Ran = "";//大于5%小于10%省名称
            String provinceNameForThin5Ran = "";//小于5%省名称
            int jRan = 0;//大于5%小于10%省个数
            int kRan = 0;//不足5%省个数
            String minProvinceNameRan = "";//燃料化利用率最低省名称
            Double minProvinceRateRan = 0.00;//燃料化利用率最低数

            //基料化
            int iBase = 0;//超过全国平均水平的省个数
            String provinceNameBase = "";//超过全国平均水平的省名称
            String maxProvinceName = "";//基料化利用率最高省名称
            Double maxProvinceRate = 0.00;//基料化利用率最高数
            String provinceNameForThin5Base = "";//小于0.5%且不为0的省名称
            int jBase = 0;//小于0.5%且不为0的省个数
            String provinceNameFor0Base = "";//0的省名称

            //原料化
            int iM = 0;//超过全国平均水平的省个数
            String provinceNameM = "";//超过全国平均水平的省名称
            String provinceNameFor0M = "";//0的省名称
            int jM = 0;//为0的省个数

            //秸秆利用能力指数
            BigDecimal comprehensiveIndexMax = new BigDecimal(0.00);// 综合利用能力指数最大值
            String comprehensiveIndexMaxName = "";// 综合利用能力指数最大值的省名称
            String industrializationIndexThanCountry = "";//产业化利用能力指数超过全国的省名称
            int iI = 0;//产业化利用能力指数超过全国的省个数
            BigDecimal industrializationIndexMax = new BigDecimal(0.00);// 产业化利用能力指数最大值
            String industrializationIndexMaxName = "";// 产业化利用能力最大值的省名称
            String industrializationIndexFor20 = "";// 产业化利用能力处于0.20—0.25的省名称
            String industrializationIndexFor15 = "";// 产业化利用能力处于0.15—0.20的省名称
            String industrializationIndexFor10 = "";// 产业化利用能力处于0.10—0.15的省名称
            String industrializationIndexFor5 = "";// 产业化利用能力处于0.05—0.10的省名称
            String industrializationIndexFor0 = "";// 产业化利用能力处于0—0.05的省名称

            //秸秆直接还田情况
            String rFor75 = "";// 超过75省名称
            String rFor60 = "";// 超过60省名称
            String rFor45 = "";// 超过45省名称
            String rFor15 = "";// 低于15省名称
            int rthan75 = 0;
            int rthan60 = 0;
            int rthan45 = 0;
            int rthin15 = 0;

            for (StrawUtilizeSumTotal strawUtilizeSumTotal : sumTotalList) {
                String name = sysApi.getSysRegionName(strawUtilizeSumTotal.getAreaId());
                //全国肥料化
                BigDecimal fertilising = strawUtilizeSumTotal.getMainFertilising().add(strawUtilizeSumTotal.getDisperseFertilising()).add(strawUtilizeSumTotal.getReturnResource());
                //全国肥料化平均利用率
                Double fertilisingRate = fertilising.divide(strawUtilizeSumTotal.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //省饲料化
                BigDecimal forage = strawUtilizeSumTotal.getMainForage().add(strawUtilizeSumTotal.getDisperseForage());
                //省饲料化利用率
                Double forageRate = forage.divide(strawUtilizeSumTotal.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //省燃料化
                BigDecimal fuel = strawUtilizeSumTotal.getMainFuel().add(strawUtilizeSumTotal.getDisperseFuel());
                //省燃料化利用率
                Double fuelRate = fuel.divide(strawUtilizeSumTotal.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //省基料化
                BigDecimal base = strawUtilizeSumTotal.getMainBase().add(strawUtilizeSumTotal.getDisperseBase());
                //省基料化利用率
                Double baseRate = base.divide(strawUtilizeSumTotal.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //省原料化
                BigDecimal m = strawUtilizeSumTotal.getMainMaterial().add(strawUtilizeSumTotal.getDisperseMaterial());
                //省原料化利用率
                Double mRate = m.divide(strawUtilizeSumTotal.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //省产业化利用能力指数
                BigDecimal industrializationIndex = strawUtilizeSumTotal.getIndustrializationIndex();
                //省综合利用能力指数
                BigDecimal comprehensiveIndex = strawUtilizeSumTotal.getComprehensiveIndex();
                //全省直接还田比例
                Double returnRatioProvince = strawUtilizeSumTotal.getReturnResource().divide(strawUtilizeSumTotal.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();

                if (returnRatioProvince >= 75 && returnRatioProvince <= 90) {
                    rFor75 = rFor75 + name + "、";
                    rthan75++;
                }
                if (returnRatioProvince >= 60 && returnRatioProvince < 75) {
                    rFor60 = rFor60 + name + "、";
                    rthan60++;
                }
                if (returnRatioProvince >= 45 && returnRatioProvince < 60) {
                    rFor45 = rFor45 + name + "、";
                    rthan45++;
                }
                if (returnRatioProvince <= 15) {
                    rFor15 = rFor15 + name + "、";
                    rthin15++;
                }

                //获取最小值和最小值的省NAME
                if (forageRate < minProvinceRate) {
                    minProvinceRate = forageRate;
                    minProvinceName = name;
                }
                if (fuelRate < minProvinceRateRan) {
                    minProvinceRateRan = fuelRate;
                    minProvinceNameRan = name;
                }

                //获取基料化的最大值以及NAME
                if (baseRate > maxProvinceRate) {
                    maxProvinceRate = baseRate;
                    maxProvinceName = name;
                }

                //获取综合利用最大值的省NAME以及值
                if (comprehensiveIndex.compareTo(comprehensiveIndexMax) > -1) {
                    comprehensiveIndexMax = comprehensiveIndex;
                    comprehensiveIndexMaxName = name;
                }

                //获取产业化利用最大值的省NAME以及值
                if (industrializationIndex.compareTo(industrializationIndexMax) > -1) {
                    industrializationIndexMax = industrializationIndex;
                    industrializationIndexMaxName = name;
                }

                //获取肥料化最大值的省NAME以及值
                if (fertilisingRate > fMax) {
                    fMax = fertilisingRate;
                    fMaxName = name;
                }

                //获取超过全国平均值的省NAMEs和个数
                //肥料化利用率
                if (fertilisingRate > fertilisingTrRate) {
                    iF++;
                    if (iF < 7) {
                        provinceNameF = provinceNameF + name + "、";
                    }
                }
                //饲料化利用率
                if (forageRate > forageTrRate) {
                    i++;
                    if (i < 7) {
                        provinceName = provinceName + name + "、";
                    }
                }
                //燃料化利用率
                if (fuelRate > fuelTrRate) {
                    iRan++;
                    if (iRan < 7) {
                        provinceNameRan = provinceNameRan + name + "、";
                    }
                }
                //基料化利用率
                if (baseRate > baseTrRate) {
                    iBase++;
                    if (iBase < 7) {
                        provinceNameBase = provinceNameBase + name + "、";
                    }
                }
                //原料化利用率
                if (mRate > mTrRate) {
                    iM++;
                    if (iM < 7) {
                        provinceNameM = provinceNameM + name + "、";
                    }
                }
                //产业化能力指数
                if (industrializationIndex.compareTo(industrializationIndexTr) == 1) {
                    iI++;
                    industrializationIndexThanCountry = industrializationIndexThanCountry + name + "、";
                }

                //判断肥料化区间
                if (fertilisingRate >= 75 && fertilisingRate <= 90) {
                    jF++;
                    provinceNameFor75F = provinceNameFor75F + name + "、";
                }
                if (fertilisingRate >= 60 && fertilisingRate < 75) {
                    provinceNameFor60F = provinceNameFor60F + name + "、";
                }
                if (fertilisingRate >= 45 && fertilisingRate < 60) {
                    provinceNameFor45F = provinceNameFor45F + name + "、";
                }
                if (fertilisingRate >= 30 && fertilisingRate < 45) {
                    provinceNameFor30F = provinceNameFor30F + name + "、";
                }
                if (fertilisingRate >= 15 && fertilisingRate < 30) {
                    provinceNameFor15F = provinceNameFor15F + name + "、";
                }

                //判断饲料化区间
                if (forageRate > 90) {
                    provinceNameFor90 = provinceNameFor90 + name + "、";
                }
                if (forageRate >= 60 && forageRate <= 75) {
                    provinceNameFor75 = provinceNameFor75 + name + "、";
                }
                if (forageRate < 14) {
                    j++;
                    if (j < 4) {
                        provinceNameFor14 = provinceNameFor14 + name + "、";
                    }
                }

                //判断燃料化区间
                if (fuelRate >= 25 && fuelRate <= 30) {
                    provinceNameFor25Ran = provinceNameFor25Ran + name + "、";
                }
                if (fuelRate < 25 && fuelRate >= 20) {
                    provinceNameFor20Ran = provinceNameFor20Ran + name + "燃料化利用比例" + fuelRate + "%，";
                }
                if (fuelRate < 20 && fuelRate >= 15) {
                    provinceNameFor15Ran = provinceNameFor15Ran + name + "、";
                }
                if (fuelRate < 15 && fuelRate >= 10) {
                    provinceNameFor10Ran = provinceNameFor10Ran + name + "、";
                }
                if (fuelRate < 10 && fuelRate >= 5) {
                    provinceNameFor5Ran = provinceNameFor5Ran + name + "、";
                    jRan++;
                }
                if (fuelRate < 5) {
                    provinceNameForThin5Ran = provinceNameForThin5Ran + name + "、";
                    kRan++;
                }

                //判断基料化区间
                if (baseRate < 0.5 && baseRate > 0) {
                    provinceNameForThin5Base = provinceNameForThin5Base + name + "、";
                    jBase++;
                }
                if (baseRate == 0) {
                    provinceNameFor0Base = provinceNameFor0Base + name + "、";
                }

                //判断原料化区间
                if (mRate == 0) {
                    jM++;
                    provinceNameFor0M = provinceNameFor0M + name + "、";
                }

                //判断产业化区间
                if (industrializationIndex.compareTo(new BigDecimal(0.20)) > -1 && industrializationIndex.compareTo(new BigDecimal(0.25)) == -1) {
                    industrializationIndexFor20 = industrializationIndexFor20 + name + "、";
                }
                if (industrializationIndex.compareTo(new BigDecimal(0.15)) > -1 && industrializationIndex.compareTo(new BigDecimal(0.20)) == -1) {
                    industrializationIndexFor15 = industrializationIndexFor15 + name + "、";
                }
                if (industrializationIndex.compareTo(new BigDecimal(0.10)) > -1 && industrializationIndex.compareTo(new BigDecimal(0.15)) == -1) {
                    industrializationIndexFor10 = industrializationIndexFor10 + name + "、";
                }
                if (industrializationIndex.compareTo(new BigDecimal(0.05)) > -1 && industrializationIndex.compareTo(new BigDecimal(0.10)) == -1) {
                    industrializationIndexFor5 = industrializationIndexFor5 + name + "、";
                }
                if (industrializationIndex.compareTo(new BigDecimal(0.00)) > -1 && industrializationIndex.compareTo(new BigDecimal(0.05)) == -1) {
                    industrializationIndexFor0 = industrializationIndexFor0 + name + "、";
                }
            }

            map.put("param_117", rFor75.equals("") ? rFor75 : rFor75.substring(0, rFor75.length() - 1));
            map.put("param_118", rthan75);
            map.put("param_119", rFor60.equals("") ? rFor60 : rFor60.substring(0, rFor60.length() - 1));
            map.put("param_120", rthan60);
            map.put("param_122", rFor45.equals("") ? rFor45 : rFor45.substring(0, rFor45.length() - 1));
            map.put("param_123", rthan45);
            map.put("param_124", rthin15);
            map.put("param_125", rFor15.equals("") ? rFor15 : rFor15.substring(0, rFor15.length() - 1));

            //燃料化数据判断
            if (!provinceNameFor0Base.equals("")) {
                provinceNameFor0Base = provinceNameFor0Base.substring(0, provinceNameFor0Base.length() - 1);
                map.put("param_58", provinceNameFor0Base + "基料化利用比例为0");
            } else {
                map.put("param_58", "");
            }

            //肥料化数据填充到map
            map.put("my_param_8", fMaxName);
            map.put("my_param_9", df.format(fMax));
            map.put("my_param_10", provinceNameF.equals("") ? provinceNameF : provinceNameF.substring(0, provinceNameF.length() - 1));
            map.put("my_param_11", iF);
            map.put("my_param_13", provinceNameFor75F.equals("") ? provinceNameFor75F : provinceNameFor75F.substring(0, provinceNameFor75F.length() - 1));
            map.put("my_param_14", jF);
            map.put("my_param_15", provinceNameFor60F.equals("") ? provinceNameFor60F : provinceNameFor60F.substring(0, provinceNameFor60F.length() - 1));
            map.put("my_param_16", provinceNameFor45F.equals("") ? provinceNameFor45F : provinceNameFor45F.substring(0, provinceNameFor45F.length() - 1));
            map.put("my_param_17", provinceNameFor30F.equals("") ? provinceNameFor30F : provinceNameFor30F.substring(0, provinceNameFor30F.length() - 1));
            map.put("my_param_18", provinceNameFor15F.equals("") ? provinceNameFor15F : provinceNameFor15F.substring(0, provinceNameFor15F.length() - 1));

            //饲料化数据填充到map
            map.put("param_13", provinceName.equals("") ? provinceName : provinceName.substring(0, provinceName.length() - 1));
            map.put("param_21", provinceNameFor90.equals("") ? provinceNameFor90 : provinceNameFor90.substring(0, provinceNameFor90.length() - 1));
            map.put("param_22", provinceNameFor75.equals("") ? provinceNameFor75 : provinceNameFor75.substring(0, provinceNameFor75.length() - 1));
            map.put("param_23", provinceNameFor14.equals("") ? provinceNameFor14 : provinceNameFor14.substring(0, provinceNameFor14.length() - 1));
            map.put("param_19", i);
            map.put("my_param_1", j);
            map.put("param_24", minProvinceName);
            map.put("param_25", df.format(minProvinceRate));

            //燃料化数据填充到mao
            map.put("param_33", provinceNameRan.equals("") ? provinceNameRan : provinceNameRan.substring(0, provinceNameRan.length() - 1));
            map.put("param_34", iRan);
            map.put("param_36", provinceNameFor25Ran.equals("") ? provinceNameFor25Ran : provinceNameFor25Ran.substring(0, provinceNameFor25Ran.length() - 1));
            map.put("my_param_2", provinceNameFor20Ran.equals("") ? provinceNameFor20Ran : provinceNameFor20Ran.substring(0, provinceNameFor20Ran.length() - 1));
            map.put("param_37", provinceNameFor15Ran.equals("") ? provinceNameFor15Ran : provinceNameFor15Ran.substring(0, provinceNameFor15Ran.length() - 1));
            map.put("param_38", provinceNameFor10Ran.equals("") ? provinceNameFor10Ran : provinceNameFor10Ran.substring(0, provinceNameFor10Ran.length() - 1));
            map.put("param_39", provinceNameFor5Ran.equals("") ? provinceNameFor5Ran : provinceNameFor5Ran.substring(0, provinceNameFor5Ran.length() - 1));
            map.put("param_40", jRan);
            map.put("param_41", provinceNameForThin5Ran.equals("") ? provinceNameForThin5Ran : provinceNameForThin5Ran.substring(provinceNameForThin5Ran.length() - 1));
            map.put("param_42", kRan);
            map.put("param_43", minProvinceNameRan);
            map.put("param_44", df.format(minProvinceRateRan) + "%");

            //基料化数据填充到map
            map.put("param_52", provinceNameBase.equals("") ? provinceNameBase : provinceNameBase.substring(0, provinceNameBase.length() - 1));
            map.put("param_53", iBase);
            map.put("param_55", maxProvinceName);
            map.put("param_56", df.format(maxProvinceRate));
            map.put("param_57", provinceNameForThin5Base.equals("") ? provinceNameForThin5Base : provinceNameForThin5Base.substring(0, provinceNameForThin5Base.length() - 1));
            map.put("my_param_3", jBase);

            //原料化数据填充到map
            map.put("param_67", provinceNameM.equals("") ? provinceNameM : provinceNameM.substring(0, provinceNameM.length() - 1));
            map.put("param_68", iM);
            map.put("param_70", provinceNameFor0M.equals("") ? provinceNameFor0M : provinceNameFor0M.substring(0, provinceNameFor0M.length() - 1));
            map.put("param_71", jM);

            //利用能力指数填充到map
            map.put("param_83", comprehensiveIndexMaxName);
            map.put("param_84", BigDecimalUtil.setScale2(comprehensiveIndexMax));
            map.put("param_87", industrializationIndexThanCountry.equals("") ? industrializationIndexThanCountry : industrializationIndexThanCountry.substring(0, industrializationIndexThanCountry.length() - 1));
            map.put("param_88", iI);
            map.put("param_89", industrializationIndexMaxName);
            map.put("param_90", BigDecimalUtil.setScale2(industrializationIndexMax));
            map.put("param_91", industrializationIndexFor20.equals("") ? industrializationIndexFor20 : industrializationIndexFor20.substring(0, industrializationIndexFor20.length() - 1));
            map.put("my_param_4", industrializationIndexFor15.equals("") ? industrializationIndexFor15 : industrializationIndexFor15.substring(0, industrializationIndexFor15.length() - 1));
            map.put("my_param_5", industrializationIndexFor10.equals("") ? industrializationIndexFor10 : industrializationIndexFor10.substring(0, industrializationIndexFor10.length() - 1));
            map.put("my_param_6", industrializationIndexFor5.equals("") ? industrializationIndexFor5 : industrializationIndexFor5.substring(0, industrializationIndexFor5.length() - 1));
            map.put("param_93", industrializationIndexFor0.equals("") ? industrializationIndexFor0 : industrializationIndexFor0.substring(0, industrializationIndexFor0.length() - 1));

            /*-----------六大区数据----------------*/
            Map<String, Object> areaForage = new HashMap<>();//六大区饲料化数据
            Map<String, Object> areaFuel = new HashMap<>();//六大区燃料化图表数据
            Map<String, Object> areaBase = new HashMap<>();//六大区基料化图表数据
            Map<String, Object> areaM = new HashMap<>();//六大区原料化图表数据
            Map<String, Object> areaI = new HashMap<>();//六大区产业化利用能力指数图表数据
            Map<String, Object> areaF = new HashMap<>();//六大区肥料化图表数据
            Map<String, Object> areaR = new HashMap<>();//六大区直接还田比例

            //肥料化
            String maxFRateName = SixRegionEnum.NORTH_REGION.getDescription();//肥料化利用率最大的区
            Double maxFRate = 0.00;//肥料化利用率最大的区的值
            String fThanCountryName = "";//肥料化利用率超过全国平均水平的区域名称
            String fThan60Name = "";//肥料化利用率处于60%—75%的区域名称
            String fThan45Name = "";//肥料化利用率处于45%—60%的区域名称
            String fThan30Name = "";//肥料化利用率处于30%—45%的区域名称
            String fThan15Name = "";//肥料化利用率处于15%—30%的区域名称
            String fThan0Name = "";//肥料化利用率处于0%—15%的区域名称

            //饲料化
            String maxForageRateName = SixRegionEnum.NORTH_REGION.getDescription();//饲料化利用率最大的区
            Double maxForageRate = 0.00;//饲料化利用率最大的区的值
            String areaOf2025 = "";//饲料化利用率20%—25%之间的区名称
            String areaOf10 = "";//饲料化利用率小于10%的区名称
            String areaOfOther = "";//饲料化利用率不属于任何区间的区名称

            //燃料化
            String maxFuelRateNameForRan = SixRegionEnum.NORTH_REGION.getDescription();//燃料化利用率最大的区
            Double maxFuelRateForRan = 0.00;//燃料化利用率最大的区的值
            String fuelThanCountryName = "";//燃料化利用率超过全国平均水平的区域名称
            String fuelThanCountryRate = "";//燃料化利用率超过全国平均水平的区域具体值
            String fuelThinCountryName = "";//燃料化利用率低于全国平均水平的区域名称
            Double fuelThinCountryRate = 0.00;//燃料化利用率低于全国平均水平的平均值
            Double fuelThinCountryRateAll = 0.00;//燃料化利用率低于全国平均水平的总值
            int m = 0;//燃料化利用率低于全国平均水平的个数

            //基料化
            String baseThanCountryName = "";//基料化利用率超过全国平均水平的区域名称
            String baseThanCountryRate = "";//基料化利用率超过全国平均水平的区域具体值
            String baseThinCountryName = "";//基料化利用率低于全国平均水平的区域名称
            String baseThinCountryRate = "";//基料化利用率低于全国平均水平的具体值

            //原料化
            String mThanCountryName = "";//原料化利用率超过全国平均水平的区域名称
            String mThanCountryRate = "";//燃料化利用率超过全国平均水平的区域具体值
            String mThinCountryName = "";//燃料化利用率低于全国平均水平的区域名称
            String mThinCountryRate = "";//燃料化利用率低于全国平均水平的具体值

            //区产业化LIST
            List<StrawUtilizeSumResVo> list = new ArrayList<>();

            //华北区
            Double northForageRate = 0.00;
            Double northFuelRate = 0.00;
            Double northBaseRate = 0.00;
            Double northMRate = 0.00;
            Double northFertilisingRate = 0.00;
            List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
            StrawUtilizeSumResVo nationalDataFornorth = reportDataMapper.getSumStrawUtilizeByAreaCode(year, north_region_list, status);
            if (nationalDataFornorth != null) {
                //区肥料化
                BigDecimal fertilisingTr1 = nationalDataFornorth.getMainFertilising().add(nationalDataFornorth.getDisperseFertilising()).add(nationalDataFornorth.getReturnResource());
                //区肥料化平均利用率
                northFertilisingRate = fertilisingTr1.divide(nationalDataFornorth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区饲料化
                BigDecimal forageTr1 = nationalDataFornorth.getMainForage().add(nationalDataFornorth.getDisperseForage());
                //区饲料化利用率
                northForageRate = forageTr1.divide(nationalDataFornorth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区燃料化
                BigDecimal fuelTr1 = nationalDataFornorth.getMainFuel().add(nationalDataFornorth.getDisperseFuel());
                //区燃料化利用率
                northFuelRate = fuelTr1.divide(nationalDataFornorth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区基料化
                BigDecimal baseTr1 = nationalDataFornorth.getMainBase().add(nationalDataFornorth.getDisperseBase());
                //区基料化利用率
                northBaseRate = baseTr1.divide(nationalDataFornorth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区原料化
                BigDecimal mTr1 = nationalDataFornorth.getMainMaterial().add(nationalDataFornorth.getDisperseMaterial());
                //区原料化利用率
                northMRate = mTr1.divide(nationalDataFornorth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区直接还田比例
                BigDecimal returnRatio1 = nationalDataFornorth.getReturnResource().divide(nationalDataFornorth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                nationalDataFornorth.setReturnRatio(returnRatio1);
                areaR.put(SixRegionEnum.NORTH_REGION.getDescription(), returnRatio1 + "%");

                //肥料化
                if (northFertilisingRate > maxFRate) {
                    maxFRate = northFertilisingRate;
                    maxFRateName = SixRegionEnum.NORTH_REGION.getDescription();
                }
                if (northFertilisingRate > fertilisingTrRate) {
                    fThanCountryName = fThanCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                }
                if (northFertilisingRate >= 60 && northFertilisingRate <= 75) {
                    fThan60Name = fThan60Name + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                } else if (northFertilisingRate >= 45 && northFertilisingRate < 60) {
                    fThan45Name = fThan45Name + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                } else if (northFertilisingRate >= 30 && northFertilisingRate < 45) {
                    fThan30Name = fThan30Name + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                } else if (northFertilisingRate >= 15 && northFertilisingRate < 30) {
                    fThan15Name = fThan15Name + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                } else if (northFertilisingRate >= 0 && northFertilisingRate < 15) {
                    fThan0Name = fThan0Name + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                }

                //饲料化
                if (northForageRate > maxForageRate) {
                    maxForageRate = northForageRate;
                    maxForageRateName = SixRegionEnum.NORTH_REGION.getDescription();
                }
                if (northForageRate >= 20 && northForageRate <= 25) {
                    areaOf2025 = areaOf2025 + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                } else if (northForageRate < 10) {
                    areaOf10 = areaOf10 + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                } else {
                    areaOfOther = areaOfOther + SixRegionEnum.NORTH_REGION.getDescription() + "饲料化利用比例" + northForageRate + "%，";
                }

                //燃料化
                if (northFuelRate > maxFuelRateForRan) {
                    maxFuelRateForRan = northFuelRate;
                    maxFuelRateNameForRan = SixRegionEnum.NORTH_REGION.getDescription();
                }
                if (northFuelRate > fuelTrRate) {
                    fuelThanCountryName = fuelThanCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                    fuelThanCountryRate = fuelThanCountryRate + northFuelRate + "%、";
                } else {
                    fuelThinCountryName = fuelThinCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                    fuelThinCountryRateAll = fuelThinCountryRateAll + northFuelRate;
                    m++;
                }

                //基料化
                if (northBaseRate > baseTrRate) {
                    baseThanCountryName = baseThanCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                    baseThanCountryRate = baseThanCountryRate + northBaseRate + "%、";
                } else {
                    baseThinCountryName = baseThinCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                    baseThinCountryRate = baseThinCountryRate + northBaseRate + "%、";
                }

                //原料化
                if (northMRate > mTrRate) {
                    mThanCountryName = baseThanCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                    mThanCountryRate = baseThanCountryRate + northMRate + "%、";
                } else {
                    mThinCountryName = baseThinCountryName + SixRegionEnum.NORTH_REGION.getDescription() + "、";
                    mThinCountryRate = baseThinCountryRate + northMRate + "%、";
                }
                nationalDataFornorth.setAreaName(SixRegionEnum.NORTH_REGION.getDescription());
                list.add(nationalDataFornorth);
            }
            areaForage.put(SixRegionEnum.NORTH_REGION.getDescription(), df.format(northForageRate) + "%");
            areaFuel.put(SixRegionEnum.NORTH_REGION.getDescription(), df.format(northFuelRate) + "%");
            areaBase.put(SixRegionEnum.NORTH_REGION.getDescription(), df.format(northBaseRate) + "%");
            areaM.put(SixRegionEnum.NORTH_REGION.getDescription(), df.format(northMRate) + "%");
            areaF.put(SixRegionEnum.NORTH_REGION.getDescription(), df.format(northFertilisingRate) + "%");
            areaI.put(SixRegionEnum.NORTH_REGION.getDescription(), BigDecimalUtil.setScale2(nationalDataFornorth.getIndustrializationIndex()));

            //长江区
            Double changForageRate = 0.00;
            Double changFuelRate = 0.00;
            Double changBaseRate = 0.00;
            Double changMRate = 0.00;
            Double changFertilisingRate = 0.00;
            List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
            StrawUtilizeSumResVo nationalDataForchang = reportDataMapper.getSumStrawUtilizeByAreaCode(year, chang_jiang_river_region_list, status);
            if (nationalDataForchang != null) {
                //区肥料化
                BigDecimal fertilisingTr2 = nationalDataForchang.getMainFertilising().add(nationalDataForchang.getDisperseFertilising()).add(nationalDataForchang.getReturnResource());
                //区肥料化平均利用率
                changFertilisingRate = fertilisingTr2.divide(nationalDataForchang.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区饲料化
                BigDecimal forageTr2 = nationalDataForchang.getMainForage().add(nationalDataForchang.getDisperseForage());
                //区饲料化利用率
                changForageRate = forageTr2.divide(nationalDataForchang.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区燃料化
                BigDecimal fuelTr2 = nationalDataForchang.getMainFuel().add(nationalDataForchang.getDisperseFuel());
                //区燃料化利用率
                changFuelRate = fuelTr2.divide(nationalDataForchang.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区基料化
                BigDecimal baseTr2 = nationalDataForchang.getMainBase().add(nationalDataForchang.getDisperseBase());
                //区基料化利用率
                changBaseRate = baseTr2.divide(nationalDataForchang.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区原料化
                BigDecimal mTr2 = nationalDataForchang.getMainMaterial().add(nationalDataForchang.getDisperseMaterial());
                //区原料化利用率
                changMRate = mTr2.divide(nationalDataForchang.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区直接还田比例
                BigDecimal returnRatio2 = nationalDataForchang.getReturnResource().divide(nationalDataForchang.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                nationalDataForchang.setReturnRatio(returnRatio2);
                areaR.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), returnRatio2 + "%");

                //肥料化
                if (changFertilisingRate > maxFRate) {
                    maxFRate = changFertilisingRate;
                    maxFRateName = SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription();
                }
                if (changFertilisingRate > fertilisingTrRate) {
                    fThanCountryName = fThanCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                }
                if (changFertilisingRate >= 60 && changFertilisingRate <= 75) {
                    fThan60Name = fThan60Name + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                } else if (changFertilisingRate >= 45 && changFertilisingRate < 60) {
                    fThan45Name = fThan45Name + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                } else if (changFertilisingRate >= 30 && changFertilisingRate < 45) {
                    fThan30Name = fThan30Name + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                } else if (changFertilisingRate >= 15 && changFertilisingRate < 30) {
                    fThan15Name = fThan15Name + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                } else if (changFertilisingRate >= 0 && changFertilisingRate < 15) {
                    fThan0Name = fThan0Name + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                }

                //饲料化
                if (changForageRate > maxForageRate) {
                    maxForageRate = changForageRate;
                    maxForageRateName = SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription();
                }
                if (changForageRate >= 20 && changForageRate <= 25) {
                    areaOf2025 = areaOf2025 + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                } else if (changForageRate < 10) {
                    areaOf10 = areaOf10 + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                } else {
                    areaOfOther = areaOfOther + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "饲料化利用比例" + changForageRate + "%，";
                }

                //燃料化
                if (changFuelRate > maxFuelRateForRan) {
                    maxFuelRateForRan = changFuelRate;
                    maxFuelRateNameForRan = SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription();
                }
                if (changFuelRate > fuelTrRate) {
                    fuelThanCountryName = fuelThanCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                    fuelThanCountryRate = fuelThanCountryRate + changFuelRate + "%、";
                } else {
                    fuelThinCountryName = fuelThinCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                    fuelThinCountryRateAll = fuelThinCountryRateAll + changFuelRate;
                    m++;
                }

                //基料化
                if (changBaseRate > baseTrRate) {
                    baseThanCountryName = baseThanCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                    baseThanCountryRate = baseThanCountryRate + changBaseRate + "%、";
                } else {
                    baseThinCountryName = baseThinCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                    baseThinCountryRate = baseThinCountryRate + changBaseRate + "%、";
                }

                //原料化
                if (changMRate > mTrRate) {
                    mThanCountryName = baseThanCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                    mThanCountryRate = baseThanCountryRate + changMRate + "%、";
                } else {
                    mThinCountryName = baseThinCountryName + SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription() + "、";
                    mThinCountryRate = baseThinCountryRate + changMRate + "%、";
                }
                nationalDataForchang.setAreaName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
                list.add(nationalDataForchang);
            }
            areaForage.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), df.format(changForageRate) + "%");
            areaFuel.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), df.format(changFuelRate) + "%");
            areaBase.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), df.format(changBaseRate) + "%");
            areaM.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), df.format(changMRate) + "%");
            areaF.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), df.format(changFertilisingRate) + "%");
            areaI.put(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription(), BigDecimalUtil.setScale2(nationalDataForchang.getIndustrializationIndex()));

            //东北区
            Double northeastForageRate = 0.00;
            Double northeastFuelRate = 0.00;
            Double northeastBaseRate = 0.00;
            Double northeastMRate = 0.00;
            Double northeastFertilisingRate = 0.00;
            List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
            StrawUtilizeSumResVo nationalDataFornortheast = reportDataMapper.getSumStrawUtilizeByAreaCode(year, northeast_region_list, status);
            if (nationalDataFornortheast != null) {
                //区肥料化
                BigDecimal fertilisingTr3 = nationalDataFornortheast.getMainFertilising().add(nationalDataFornortheast.getDisperseFertilising()).add(nationalDataFornortheast.getReturnResource());
                //区肥料化平均利用率
                northeastFertilisingRate = fertilisingTr3.divide(nationalDataFornortheast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区饲料化
                BigDecimal forageTr3 = nationalDataFornortheast.getMainForage().add(nationalDataFornortheast.getDisperseForage());
                //区饲料化利用率
                northeastForageRate = forageTr3.divide(nationalDataFornortheast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区燃料化
                BigDecimal fuelTr3 = nationalDataFornortheast.getMainFuel().add(nationalDataFornortheast.getDisperseFuel());
                //区燃料化利用率
                northeastFuelRate = fuelTr3.divide(nationalDataFornortheast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区基料化
                BigDecimal baseTr3 = nationalDataFornortheast.getMainBase().add(nationalDataFornortheast.getDisperseBase());
                //区基料化利用率
                northeastBaseRate = baseTr3.divide(nationalDataFornortheast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区原料化
                BigDecimal mTr3 = nationalDataFornortheast.getMainMaterial().add(nationalDataFornortheast.getDisperseMaterial());
                //区原料化利用率
                northeastMRate = mTr3.divide(nationalDataFornortheast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区直接还田比例
                BigDecimal returnRatio3 = nationalDataFornortheast.getReturnResource().divide(nationalDataFornortheast.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                nationalDataFornortheast.setReturnRatio(returnRatio3);
                areaR.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), returnRatio3 + "%");

                //肥料化
                if (northeastFertilisingRate > maxFRate) {
                    maxFRate = northeastFertilisingRate;
                    maxFRateName = SixRegionEnum.NORTHEAST_REGION.getDescription();
                }
                if (northeastFertilisingRate > fertilisingTrRate) {
                    fThanCountryName = fThanCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                }
                if (northeastFertilisingRate >= 60 && northeastFertilisingRate <= 75) {
                    fThan60Name = fThan60Name + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                } else if (northeastFertilisingRate >= 45 && northeastFertilisingRate < 60) {
                    fThan45Name = fThan45Name + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                } else if (northeastFertilisingRate >= 30 && northeastFertilisingRate < 45) {
                    fThan30Name = fThan30Name + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                } else if (northeastFertilisingRate >= 15 && northeastFertilisingRate < 30) {
                    fThan15Name = fThan15Name + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                } else if (northeastFertilisingRate >= 0 && northeastFertilisingRate < 15) {
                    fThan0Name = fThan0Name + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                }

                //饲料化
                if (northeastForageRate > maxForageRate) {
                    maxForageRate = northeastForageRate;
                    maxForageRateName = SixRegionEnum.NORTHEAST_REGION.getDescription();
                }
                if (northeastForageRate >= 20 && northeastForageRate <= 25) {
                    areaOf2025 = areaOf2025 + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                } else if (northeastForageRate < 10) {
                    areaOf10 = areaOf10 + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                } else {
                    areaOfOther = areaOfOther + SixRegionEnum.NORTHEAST_REGION.getDescription() + "饲料化利用比例" + northeastForageRate + "%，";
                }

                //燃料化
                if (northeastFuelRate > maxFuelRateForRan) {
                    maxFuelRateForRan = northeastFuelRate;
                    maxFuelRateNameForRan = SixRegionEnum.NORTHEAST_REGION.getDescription();
                }
                if (northeastFuelRate > fuelTrRate) {
                    fuelThanCountryName = fuelThanCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                    fuelThanCountryRate = fuelThanCountryRate + northeastFuelRate + "%、";
                } else {
                    fuelThinCountryName = fuelThinCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                    fuelThinCountryRateAll = fuelThinCountryRateAll + northeastFuelRate;
                    m++;
                }

                //基料化
                if (northeastBaseRate > baseTrRate) {
                    baseThanCountryName = baseThanCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                    baseThanCountryRate = baseThanCountryRate + northeastBaseRate + "%、";
                } else {
                    baseThinCountryName = baseThinCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                    baseThinCountryRate = baseThinCountryRate + northeastBaseRate + "%、";
                }

                //原料化
                if (northeastMRate > mTrRate) {
                    mThanCountryName = baseThanCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                    mThanCountryRate = baseThanCountryRate + northeastMRate + "%、";
                } else {
                    mThinCountryName = baseThinCountryName + SixRegionEnum.NORTHEAST_REGION.getDescription() + "、";
                    mThinCountryRate = baseThinCountryRate + northeastMRate + "%、";
                }
                nationalDataFornortheast.setAreaName(SixRegionEnum.NORTHEAST_REGION.getDescription());
                list.add(nationalDataFornortheast);
            }
            areaForage.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), df.format(northeastForageRate) + "%");
            areaFuel.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), df.format(northeastFuelRate) + "%");
            areaBase.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), df.format(northeastBaseRate) + "%");
            areaM.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), df.format(northeastMRate) + "%");
            areaF.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), df.format(northeastFertilisingRate) + "%");
            areaI.put(SixRegionEnum.NORTHEAST_REGION.getDescription(), BigDecimalUtil.setScale2(nationalDataFornortheast.getIndustrializationIndex()));

            //西北区
            Double northwestForageRate = 0.00;
            Double northwestFuelRate = 0.00;
            Double northwestBaseRate = 0.00;
            Double northwestMRate = 0.00;
            Double northwestFertilisingRate = 0.00;
            List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
            StrawUtilizeSumResVo nationalDataFornorthwest = reportDataMapper.getSumStrawUtilizeByAreaCode(year, northwest_region_list, status);
            if (nationalDataFornorthwest != null) {
                //区肥料化
                BigDecimal fertilisingTr4 = nationalDataFornorthwest.getMainFertilising().add(nationalDataFornorthwest.getDisperseFertilising()).add(nationalDataFornorthwest.getReturnResource());
                //区肥料化平均利用率
                northwestFertilisingRate = fertilisingTr4.divide(nationalDataFornorthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区饲料化
                BigDecimal forageTr4 = nationalDataFornorthwest.getMainForage().add(nationalDataFornorthwest.getDisperseForage());
                //区饲料化利用率
                northwestForageRate = forageTr4.divide(nationalDataFornorthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区燃料化
                BigDecimal fuelTr4 = nationalDataFornorthwest.getMainFuel().add(nationalDataFornorthwest.getDisperseFuel());
                //区燃料化利用率
                northwestFuelRate = fuelTr4.divide(nationalDataFornorthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区基料化
                BigDecimal baseTr4 = nationalDataFornorthwest.getMainBase().add(nationalDataFornorthwest.getDisperseBase());
                //区基料化利用率
                northwestBaseRate = baseTr4.divide(nationalDataFornorthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区原料化
                BigDecimal mTr4 = nationalDataFornorthwest.getMainMaterial().add(nationalDataFornorthwest.getDisperseMaterial());
                //区原料化利用率
                northwestMRate = mTr4.divide(nationalDataFornorthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区直接还田比例
                BigDecimal returnRatio4 = nationalDataFornorthwest.getReturnResource().divide(nationalDataFornorthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                nationalDataFornorthwest.setReturnRatio(returnRatio4);
                areaR.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), returnRatio4 + "%");

                //肥料化
                if (northwestFertilisingRate > maxFRate) {
                    maxFRate = northwestFertilisingRate;
                    maxFRateName = SixRegionEnum.NORTHWEST_REGION.getDescription();
                }
                if (northwestFertilisingRate > fertilisingTrRate) {
                    fThanCountryName = fThanCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                }
                if (northwestFertilisingRate >= 60 && northwestFertilisingRate <= 75) {
                    fThan60Name = fThan60Name + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                } else if (northwestFertilisingRate >= 45 && northwestFertilisingRate < 60) {
                    fThan45Name = fThan45Name + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                } else if (northwestFertilisingRate >= 30 && northwestFertilisingRate < 45) {
                    fThan30Name = fThan30Name + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                } else if (northwestFertilisingRate >= 15 && northwestFertilisingRate < 30) {
                    fThan15Name = fThan15Name + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                } else if (northwestFertilisingRate >= 0 && northwestFertilisingRate < 15) {
                    fThan0Name = fThan0Name + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                }

                //饲料化
                if (northwestForageRate > maxForageRate) {
                    maxForageRate = northwestForageRate;
                    maxForageRateName = SixRegionEnum.NORTHWEST_REGION.getDescription();
                }
                if (northwestForageRate >= 20 && northwestForageRate <= 25) {
                    areaOf2025 = areaOf2025 + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                } else if (northwestForageRate < 10) {
                    areaOf10 = areaOf10 + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                } else {
                    areaOfOther = areaOfOther + SixRegionEnum.NORTHWEST_REGION.getDescription() + "饲料化利用比例" + northwestForageRate + "%，";
                }

                //燃料化
                if (northwestFuelRate > maxFuelRateForRan) {
                    maxFuelRateForRan = northwestFuelRate;
                    maxFuelRateNameForRan = SixRegionEnum.NORTHWEST_REGION.getDescription();
                }
                if (northwestFuelRate > fuelTrRate) {
                    fuelThanCountryName = fuelThanCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                    fuelThanCountryRate = fuelThanCountryRate + northwestFuelRate + "%、";
                } else {
                    fuelThinCountryName = fuelThinCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                    fuelThinCountryRateAll = fuelThinCountryRateAll + northwestFuelRate;
                    m++;
                }

                //基料化
                if (northwestBaseRate > baseTrRate) {
                    baseThanCountryName = baseThanCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                    baseThanCountryRate = baseThanCountryRate + northwestBaseRate + "%、";
                } else {
                    baseThinCountryName = baseThinCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                    baseThinCountryRate = baseThinCountryRate + northwestBaseRate + "%、";
                }

                //原料化
                if (northwestMRate > mTrRate) {
                    mThanCountryName = baseThanCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                    mThanCountryRate = baseThanCountryRate + northwestMRate + "%、";
                } else {
                    mThinCountryName = baseThinCountryName + SixRegionEnum.NORTHWEST_REGION.getDescription() + "、";
                    mThinCountryRate = baseThinCountryRate + northwestMRate + "%、";
                }
                nationalDataFornorthwest.setAreaName(SixRegionEnum.NORTHWEST_REGION.getDescription());
                list.add(nationalDataFornorthwest);
            }
            areaForage.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), df.format(northwestForageRate) + "%");
            areaFuel.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), df.format(northwestFuelRate) + "%");
            areaBase.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), df.format(northwestBaseRate) + "%");
            areaM.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), df.format(northwestMRate) + "%");
            areaF.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), df.format(northwestFertilisingRate) + "%");
            areaI.put(SixRegionEnum.NORTHWEST_REGION.getDescription(), BigDecimalUtil.setScale2(nationalDataFornorthwest.getIndustrializationIndex()));

            //西南区
            Double southwestForageRate = 0.00;
            Double southwestFuelRate = 0.00;
            Double southwestBaseRate = 0.00;
            Double southwestMRate = 0.00;
            Double southwestFertilisingRate = 0.00;
            List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
            StrawUtilizeSumResVo nationalDataForsouthwest = reportDataMapper.getSumStrawUtilizeByAreaCode(year, southwest_region_list, status);
            if (nationalDataForsouthwest != null) {
                //区肥料化
                BigDecimal fertilisingTr5 = nationalDataForsouthwest.getMainFertilising().add(nationalDataForsouthwest.getDisperseFertilising()).add(nationalDataForsouthwest.getReturnResource());
                //区肥料化平均利用率
                southwestFertilisingRate = fertilisingTr5.divide(nationalDataForsouthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区饲料化
                BigDecimal forageTr5 = nationalDataForsouthwest.getMainForage().add(nationalDataForsouthwest.getDisperseForage());
                //区饲料化利用率
                southwestForageRate = forageTr5.divide(nationalDataForsouthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区燃料化
                BigDecimal fuelTr5 = nationalDataForsouthwest.getMainFuel().add(nationalDataForsouthwest.getDisperseFuel());
                //区燃料化利用率
                southwestFuelRate = fuelTr5.divide(nationalDataForsouthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区基料化
                BigDecimal baseTr5 = nationalDataForsouthwest.getMainBase().add(nationalDataForsouthwest.getDisperseBase());
                //区基料化利用率
                southwestBaseRate = baseTr5.divide(nationalDataForsouthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区原料化
                BigDecimal mTr5 = nationalDataForsouthwest.getMainMaterial().add(nationalDataForsouthwest.getDisperseMaterial());
                //区原料化利用率
                southwestMRate = mTr5.divide(nationalDataForsouthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区直接还田比例
                BigDecimal returnRatio5 = nationalDataForsouthwest.getReturnResource().divide(nationalDataForsouthwest.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                nationalDataForsouthwest.setReturnRatio(returnRatio5);
                areaR.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), returnRatio5 + "%");

                //肥料化
                if (southwestFertilisingRate > maxFRate) {
                    maxFRate = southwestFertilisingRate;
                    maxFRateName = SixRegionEnum.SOUTHWEST_REGION.getDescription();
                }
                if (southwestFertilisingRate > fertilisingTrRate) {
                    fThanCountryName = fThanCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                }
                if (southwestFertilisingRate >= 60 && southwestFertilisingRate <= 75) {
                    fThan60Name = fThan60Name + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                } else if (southwestFertilisingRate >= 45 && southwestFertilisingRate < 60) {
                    fThan45Name = fThan45Name + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                } else if (southwestFertilisingRate >= 30 && southwestFertilisingRate < 45) {
                    fThan30Name = fThan30Name + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                } else if (southwestFertilisingRate >= 15 && southwestFertilisingRate < 30) {
                    fThan15Name = fThan15Name + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                } else if (southwestFertilisingRate >= 0 && southwestFertilisingRate < 15) {
                    fThan0Name = fThan0Name + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                }

                //饲料化
                if (southwestForageRate > maxForageRate) {
                    maxForageRate = southwestForageRate;
                    maxForageRateName = SixRegionEnum.SOUTHWEST_REGION.getDescription();
                }
                if (southwestForageRate >= 20 && southwestForageRate <= 25) {
                    areaOf2025 = areaOf2025 + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                } else if (southwestForageRate < 10) {
                    areaOf10 = areaOf10 + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                } else {
                    areaOfOther = areaOfOther + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "饲料化利用比例" + southwestForageRate + "%，";
                }

                //燃料化
                if (southwestFuelRate > maxFuelRateForRan) {
                    maxFuelRateForRan = southwestFuelRate;
                    maxFuelRateNameForRan = SixRegionEnum.SOUTHWEST_REGION.getDescription();
                }
                if (southwestFuelRate > fuelTrRate) {
                    fuelThanCountryName = fuelThanCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                    fuelThanCountryRate = fuelThanCountryRate + southwestFuelRate + "%、";
                } else {
                    fuelThinCountryName = fuelThinCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                    fuelThinCountryRateAll = fuelThinCountryRateAll + southwestFuelRate;
                    m++;
                }

                //基料化
                if (southwestBaseRate > baseTrRate) {
                    baseThanCountryName = baseThanCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                    baseThanCountryRate = baseThanCountryRate + southwestBaseRate + "%、";
                } else {
                    baseThinCountryName = baseThinCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                    baseThinCountryRate = baseThinCountryRate + southwestBaseRate + "%、";
                }

                //原料化
                if (southwestMRate > mTrRate) {
                    mThanCountryName = baseThanCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                    mThanCountryRate = baseThanCountryRate + southwestMRate + "%、";
                } else {
                    mThinCountryName = baseThinCountryName + SixRegionEnum.SOUTHWEST_REGION.getDescription() + "、";
                    mThinCountryRate = baseThinCountryRate + southwestMRate + "%、";
                }
                nationalDataForsouthwest.setAreaName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
                list.add(nationalDataForsouthwest);
            }
            areaForage.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), df.format(southwestForageRate) + "%");
            areaFuel.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), df.format(southwestFuelRate) + "%");
            areaBase.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), df.format(southwestBaseRate) + "%");
            areaM.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), df.format(southwestMRate) + "%");
            areaF.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), df.format(southwestFertilisingRate) + "%");
            areaI.put(SixRegionEnum.SOUTHWEST_REGION.getDescription(), BigDecimalUtil.setScale2(nationalDataForsouthwest.getIndustrializationIndex()));

            //华南区
            Double southForageRate = 0.00;
            Double southFuelRate = 0.00;
            Double southBaseRate = 0.00;
            Double southMRate = 0.00;
            Double southFertilisingRate = 0.00;
            List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));
            StrawUtilizeSumResVo nationalDataForsouth = reportDataMapper.getSumStrawUtilizeByAreaCode(year, south_region_list, status);
            if (nationalDataForsouth != null) {
                //区肥料化
                BigDecimal fertilisingTr6 = nationalDataForsouth.getMainFertilising().add(nationalDataForsouth.getDisperseFertilising()).add(nationalDataForsouth.getReturnResource());
                //区肥料化平均利用率
                southFertilisingRate = fertilisingTr6.divide(nationalDataForsouth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区饲料化
                BigDecimal forageTr6 = nationalDataForsouth.getMainForage().add(nationalDataForsouth.getDisperseForage());
                //区饲料化利用率
                southForageRate = forageTr6.divide(nationalDataForsouth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区燃料化
                BigDecimal fuelTr6 = nationalDataForsouth.getMainFuel().add(nationalDataForsouth.getDisperseFuel());
                //区燃料化利用率
                southFuelRate = fuelTr6.divide(nationalDataForsouth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区基料化
                BigDecimal baseTr6 = nationalDataForsouth.getMainBase().add(nationalDataForsouth.getDisperseBase());
                //区基料化利用率
                southBaseRate = baseTr6.divide(nationalDataForsouth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区原料化
                BigDecimal mTr6 = nationalDataForsouth.getMainMaterial().add(nationalDataForsouth.getDisperseMaterial());
                //区原料化利用率
                southMRate = mTr6.divide(nationalDataForsouth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue();
                //区直接还田比例
                BigDecimal returnRatio6 = nationalDataForsouth.getReturnResource().divide(nationalDataForsouth.getCollectResource(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                nationalDataForsouth.setReturnRatio(returnRatio6);
                areaR.put(SixRegionEnum.SOUTH_REGION.getDescription(), returnRatio6 + "%");

                //肥料化
                if (southFertilisingRate > maxFRate) {
                    maxFRate = southFertilisingRate;
                    maxFRateName = SixRegionEnum.SOUTH_REGION.getDescription();
                }
                if (southFertilisingRate > fertilisingTrRate) {
                    fThanCountryName = fThanCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                }
                if (southFertilisingRate >= 60 && southFertilisingRate <= 75) {
                    fThan60Name = fThan60Name + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                } else if (southFertilisingRate >= 45 && southFertilisingRate < 60) {
                    fThan45Name = fThan45Name + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                } else if (southFertilisingRate >= 30 && southFertilisingRate < 45) {
                    fThan30Name = fThan30Name + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                } else if (southFertilisingRate >= 15 && southFertilisingRate < 30) {
                    fThan15Name = fThan15Name + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                } else if (southFertilisingRate >= 0 && southFertilisingRate < 15) {
                    fThan0Name = fThan0Name + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                }

                //饲料化
                if (southForageRate > maxForageRate) {
                    maxForageRate = southForageRate;
                    maxForageRateName = SixRegionEnum.SOUTH_REGION.getDescription();
                }
                if (southForageRate >= 20 && southForageRate <= 25) {
                    areaOf2025 = areaOf2025 + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                } else if (southForageRate < 10) {
                    areaOf10 = areaOf10 + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                } else {
                    areaOfOther = areaOfOther + SixRegionEnum.SOUTH_REGION.getDescription() + "饲料化利用比例" + southForageRate + "%，";
                }

                //燃料化
                if (southFuelRate > maxFuelRateForRan) {
                    maxFuelRateForRan = southFuelRate;
                    maxFuelRateNameForRan = SixRegionEnum.SOUTH_REGION.getDescription();
                }
                if (southFuelRate > fuelTrRate) {
                    fuelThanCountryName = fuelThanCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                    fuelThanCountryRate = fuelThanCountryRate + southFuelRate + "%、";
                } else {
                    fuelThinCountryName = fuelThinCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                    fuelThinCountryRateAll = fuelThinCountryRateAll + southFuelRate;
                    m++;
                }

                //基料化
                if (southBaseRate > baseTrRate) {
                    baseThanCountryName = baseThanCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                    baseThanCountryRate = baseThanCountryRate + southBaseRate + "%、";
                } else {
                    baseThinCountryName = baseThinCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                    baseThinCountryRate = baseThinCountryRate + southBaseRate + "%、";
                }

                //原料化
                if (southMRate > mTrRate) {
                    mThanCountryName = baseThanCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                    mThanCountryRate = baseThanCountryRate + southMRate + "%、";
                } else {
                    mThinCountryName = baseThinCountryName + SixRegionEnum.SOUTH_REGION.getDescription() + "、";
                    mThinCountryRate = baseThinCountryRate + southMRate + "%、";
                }
                nationalDataForsouth.setAreaName(SixRegionEnum.SOUTH_REGION.getDescription());
                list.add(nationalDataForsouth);
            }
            areaForage.put(SixRegionEnum.SOUTH_REGION.getDescription(), df.format(southForageRate) + "%");
            areaFuel.put(SixRegionEnum.SOUTH_REGION.getDescription(), df.format(southFuelRate) + "%");
            areaBase.put(SixRegionEnum.SOUTH_REGION.getDescription(), df.format(southBaseRate) + "%");
            areaM.put(SixRegionEnum.SOUTH_REGION.getDescription(), df.format(southMRate) + "%");
            areaF.put(SixRegionEnum.SOUTH_REGION.getDescription(), df.format(southFertilisingRate) + "%");
            areaI.put(SixRegionEnum.SOUTH_REGION.getDescription(), BigDecimalUtil.setScale2(nationalDataForsouth.getIndustrializationIndex()));

            //图表数据填充
            map.put("my_table_1", areaForage);
            map.put("my_table_2", areaFuel);
            map.put("my_table_3", areaBase);
            map.put("my_table_4", areaM);
            map.put("my_table_5", areaI);
            map.put("my_table_6", areaF);
            map.put("my_table_7", areaR);

            //肥料化区数据填充
            map.put("my_param_19", fThanCountryName.equals("") ? fThanCountryName : fThanCountryName.substring(0, fThanCountryName.length() - 1));
            map.put("param_8", maxFRateName);
            map.put("param_9", df.format(maxFRate));
            map.put("my_param_20", fThan60Name.equals("") ? fThan60Name : fThan60Name.substring(0, fThan60Name.length() - 1));
            map.put("param_10", fThan45Name.equals("") ? fThan45Name : fThan45Name.substring(0, fThan45Name.length() - 1));
            map.put("my_param_21", fThan30Name.equals("") ? fThan30Name : fThan30Name.substring(0, fThan30Name.length() - 1));
            map.put("my_param_22", fThan15Name.equals("") ? fThan15Name : fThan15Name.substring(0, fThan15Name.length() - 1));
            map.put("my_param_23", fThan0Name.equals("") ? fThan0Name : fThan0Name.substring(0, fThan0Name.length() - 1));

            //饲料化区数据填充
            map.put("param_26", maxForageRateName);
            map.put("param_27", df.format(maxForageRate) + "%");
            map.put("param_28", areaOf2025.equals("") ? areaOf2025 : areaOf2025.substring(0, areaOf2025.length() - 1));
            map.put("param_31", areaOf10.equals("") ? areaOf10 : areaOf10.substring(0, areaOf10.length() - 1));
            map.put("param_29", areaOfOther.equals("") ? areaOfOther : areaOfOther.substring(0, areaOfOther.length() - 1));

            //燃料化区数据填充
            //求燃料化低于全国的平均值
            if (m != 0) {
                fuelThinCountryRate = fuelThinCountryRateAll / Double.valueOf(m);
                BigDecimal b = new BigDecimal(fuelThinCountryRate);
                fuelThinCountryRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            map.put("param_45", maxFuelRateNameForRan);
            map.put("param_46", df.format(maxFuelRateForRan) + "%");
            map.put("param_47", fuelThanCountryName.equals("") ? fuelThanCountryName : fuelThanCountryName.substring(0, fuelThanCountryName.length() - 1));
            map.put("param_48", fuelThanCountryRate.equals("") ? fuelThanCountryRate : fuelThanCountryRate.substring(0, fuelThanCountryRate.length() - 1));
            map.put("param_50", fuelThinCountryName.equals("") ? fuelThinCountryName : fuelThinCountryName.substring(0, fuelThinCountryName.length() - 1));
            map.put("param_51", df.format(fuelThinCountryRate) + "%");

            //基料化区数据填充
            map.put("param_61", baseThanCountryName.equals("") ? baseThanCountryName : baseThanCountryName.substring(0, baseThanCountryName.length() - 1));
            map.put("param_63", baseThanCountryRate.equals("") ? baseThanCountryRate : baseThanCountryRate.substring(0, baseThanCountryRate.length() - 1));
            map.put("param_65", baseThinCountryName.equals("") ? baseThinCountryName : baseThinCountryName.substring(0, baseThinCountryName.length() - 1));
            map.put("param_66", baseThinCountryRate.equals("") ? baseThinCountryRate : baseThinCountryRate.substring(0, baseThinCountryRate.length() - 1));

            //原料化区数据填充
            map.put("param_73", mThanCountryName.equals("") ? mThanCountryName : mThanCountryName.substring(0, mThanCountryName.length() - 1));
            map.put("param_75", df.format(mTrRate));
            map.put("param_76", mThanCountryRate.equals("") ? mThanCountryRate : mThanCountryRate.substring(0, mThanCountryRate.length() - 1));
            map.put("param_78", mThinCountryName.equals("") ? mThinCountryName : mThinCountryName.substring(0, mThinCountryName.length() - 1));
            map.put("param_79", mThinCountryRate.equals("") ? mThinCountryRate : mThinCountryRate.substring(0, mThinCountryRate.length() - 1));

            //产业化数据填充
            //排序
            list.sort(new Comparator<StrawUtilizeSumResVo>() {
                @Override
                public int compare(StrawUtilizeSumResVo o1, StrawUtilizeSumResVo o2) {
                    return o2.getIndustrializationIndex().compareTo(o1.getIndustrializationIndex());
                }
            });
            map.put("param_94", list.get(0).getAreaName());
            map.put("param_95", list.get(1).getAreaName());
            map.put("my_param_7", BigDecimalUtil.setScale2(list.get(0).getIndustrializationIndex()));
            map.put("my_param_8", BigDecimalUtil.setScale2(list.get(1).getIndustrializationIndex()));
            map.put("param_96", list.get(2).getAreaName());
            map.put("param_97", list.get(3).getAreaName());
            map.put("param_98", BigDecimalUtil.setScale2(list.get(2).getIndustrializationIndex()));
            map.put("param_99", BigDecimalUtil.setScale2(list.get(3).getIndustrializationIndex()));
            map.put("param_100", list.get(4).getAreaName());
            map.put("param_101", list.get(5).getAreaName());
            map.put("param_102", BigDecimalUtil.setScale2(list.get(4).getIndustrializationIndex()));
            map.put("param_103", BigDecimalUtil.setScale2(list.get(5).getIndustrializationIndex()));

            //直接还田数据填充
            //排序
            list.sort(new Comparator<StrawUtilizeSumResVo>() {
                @Override
                public int compare(StrawUtilizeSumResVo o1, StrawUtilizeSumResVo o2) {
                    return o2.getReturnRatio().compareTo(o1.getReturnRatio());
                }
            });
            map.put("param_126", list.get(0).getAreaName());
            map.put("param_127", BigDecimalUtil.setScale2(list.get(0).getReturnRatio()));
            map.put("param_128", list.get(1).getAreaName());
            map.put("param_129", list.get(2).getAreaName());
            map.put("param_130", BigDecimalUtil.setScale2(list.get(1).getReturnRatio()));
            map.put("param_131", BigDecimalUtil.setScale2(list.get(2).getReturnRatio()));
            map.put("param_132", list.get(3).getAreaName());
            map.put("param_133", list.get(4).getAreaName());
            map.put("my_param_24", BigDecimalUtil.setScale2(list.get(3).getReturnRatio()));
            map.put("my_param_25", BigDecimalUtil.setScale2(list.get(4).getReturnRatio()));
            map.put("param_134", list.get(5).getAreaName());
            map.put("param_135", BigDecimalUtil.setScale2(list.get(5).getReturnRatio()));

            //获取所有县级的汇总数据
            List<StrawUtilizeSumTotal> allCountyTotal = new ArrayList<>();
            int all = 0;//全国县的数量
            int thanAll = 0;//超过全国平均值的县个数
            int than20 = 0;//超过0.20的县个数
            int than40 = 0;//超过0.40的县个数
            int than1 = 0;//超过1的县个数


            String regionYear = syncSysRegionService.getYearByYear(year);
            List<StrawUtilizeSumTotal> sumTotalList2 = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds3(year, status, regionYear);
            allCountyTotal.addAll(sumTotalList2);
            for (StrawUtilizeSumTotal strawUtilizeSumTotal : allCountyTotal) {
                all++;
                BigDecimal industrializationIndexCounty = strawUtilizeSumTotal.getIndustrializationIndex();
                if (industrializationIndexCounty.compareTo(industrializationIndexTr) == 1) {
                    thanAll++;
                }
                if (industrializationIndexCounty.compareTo(new BigDecimal(0.20)) > -1) {
                    than20++;
                }
                if (industrializationIndexCounty.compareTo(new BigDecimal(0.40)) == 1) {
                    than40++;
                }
                if (industrializationIndexCounty.compareTo(new BigDecimal(1.00)) == 1) {
                    than1++;
                }
            }
            map.put("param_104", thanAll);
            map.put("param_105", df.format((float) thanAll / (float) all));
            map.put("param_106", than20);
            map.put("param_107", df.format((float) than20 / (float) all));
            map.put("param_108", than40);
            map.put("param_109", df.format((float) than40 / (float) all));
            map.put("param_110", than1);
            map.put("param_111", df.format((float) than1 / (float) all));

        }

        return map;
    }

}
