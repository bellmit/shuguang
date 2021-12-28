package com.sofn.ducss.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sofn.ducss.enums.*;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.service.AggregateService;
import com.sofn.ducss.service.CountryTaskService;
import com.sofn.ducss.service.CountyDataAnalysisService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.AreaRegionCode;
import com.sofn.ducss.vo.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AggregateServiceImpl implements AggregateService {

    @Resource
    private ProStillDetailMapper proStillDetailMapper;
    @Autowired
    private StrawProduceMapper strawProduceMapper;
    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;
    @Autowired
    private StrawUtilizeMapper strawUtilizeMapper;
    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;
    @Autowired
    private StrawUtilizeSumMapper strawUtilizeSumMapper;
    @Autowired
    private StrawUtilizeSumTotalMapper strawUtilizeSumTotalMapper;
    @Autowired
    private CollectFlowMapper collectFlowMapper;
    //    @Autowired
//    private CountryTaskMapper countryTaskMapper;
    @Autowired
    private CountryTaskService countryTaskService;

    @Autowired
    private StrawUsageSumMapper strawUsageSumMapper;

    @Autowired
    private ReturnLeaveSumMapper returnLeaveSumMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Autowired
    private ProductionUsageSumMapper productionUsageSumMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @Autowired
    private CountyDataAnalysisService countyDataAnalysisService;


    @Override
    public List<StrawProduceResVo> getStrawProduceData(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//判断用户是否有权限查询该区域

        //查出所有的产生量数据，并未按秸秆类型去重
        List<StrawProduceResVo> spList = getStrawProduceByRegionList(regionList, vo.getYear());

        //返回的数据变量
        List<StrawProduceResVo> resList = new ArrayList();
        //获取秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        //组装map
        Map<String, StrawProduceResVo> produceMap = spList.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));

        StrawProduceResVo temp = null;
        //以秸秆类型集合的顺序，循环写入数据
        for (SysDict dict : dictList) {
            temp = produceMap.get(dict.getDictcode());
            if (temp == null)
                temp = new StrawProduceResVo();
            temp.setStrawName(dict.getDictname());  //根据秸秆类型的排序，写入名称
            temp.setStrawType(dict.getDictcode());
            resList.add(temp);
        }

        //手动合计
        StrawProduceResVo sum = addSumProduce(resList);
        resList.add(sum);

        return resList;
    }

    /**
     * 累加合计数据
     *
     * @param resList
     * @return
     */
    private StrawProduceResVo addSumProduce(List<StrawProduceResVo> resList) {
        StrawProduceResVo sum = new StrawProduceResVo();
        for (StrawProduceResVo produce : resList) {
            sum = addProduce(sum, produce);
        }

        sum.setStrawName("合计");
        sum.setStrawType("sum");
        return sum;
    }

    /**
     * 累加合计数据2
     *
     * @param resList
     * @return
     */
    public static StrawProduceResVo2 addSumProduce2(List<StrawProduceResVo2> resList) {
        StrawProduceResVo2 sum = new StrawProduceResVo2();
        for (StrawProduceResVo2 produce : resList) {
            sum = addProduce2(sum, produce);
        }

        sum.setStrawName("合计");
        sum.setStrawType("sum");

        sum.setTheoryResourceRate(sum.getTheoryResourceRate().setScale(0, RoundingMode.HALF_UP));
        sum.setCollectResourceRate(sum.getCollectResourceRate().setScale(0, RoundingMode.HALF_UP));

        return sum;
    }

    /**
     * 根据regionList查询产生量数据
     *
     * @param regionList 区域列表
     * @param year       年份
     * @return
     */
    private List<StrawProduceResVo> getStrawProduceByRegionList(List<AreaRegionCode> regionList, String year) {
        List<StrawProduce> produces = new ArrayList<>();
        for (AreaRegionCode regionCode : regionList) {
            produces.addAll(getStrawProduceByRegion(regionCode, year));
        }
        //转换集合元素类型
        List<StrawProduceResVo> produceResList = produces.stream().map(p -> {
            StrawProduceResVo temp = new StrawProduceResVo();
            BeanUtils.copyProperties(p, temp);
            return temp;
        }).collect(Collectors.toList());

        Map<String, StrawProduceResVo> produceMap = new HashMap();
        StrawProduceResVo mapItem = null;
        //以秸秆类型为单位分组组装
        for (StrawProduceResVo produce : produceResList) {
            mapItem = produceMap.get(produce.getStrawType());
            if (mapItem != null) {
                mapItem = addProduce(mapItem, produce); //累加
                produceMap.put(mapItem.getStrawType(), mapItem);
            } else
                produceMap.put(produce.getStrawType(), produce);
        }

        List<StrawProduceResVo> resList = produceMap.values().stream().collect(Collectors.toList());
        return resList;
    }

    /**
     * 根据regionList查询产生量数据2
     *
     * @param regionList 区域列表
     * @param year       年份
     * @return
     */
    private List<StrawProduceResVo2> getStrawProduceByRegionList2(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawProduceResVo2> produceResList = new ArrayList<>();
        // 当前账号
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode regionCode : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), regionCode.getLastId(), year, checkInfoFlag);
            produceResList.addAll(getStrawProduceByRegion2(regionCode, year, statusList));
        }
        /**
         * 添加4个字段哦
         */
        //14种秸秆类型产量总和
        BigDecimal theoryResourceSum = BigDecimal.ZERO;
        //14种秸秆类型可收集量总和
        BigDecimal collectResourceSum = BigDecimal.ZERO;
        //以秸秆类型为单位分组组装
        Map<String, StrawProduceResVo2> produceMap = new HashMap<>();
        for (StrawProduceResVo2 produce : produceResList) {
            StrawProduceResVo2 mapItem = produceMap.get(produce.getStrawType());
            if (mapItem != null) {
                //累加
                produce = addProduce2(mapItem, produce);
            }
            produceMap.put(produce.getStrawType(), produce);
            theoryResourceSum = theoryResourceSum.add(produce.getTheoryResource());
            collectResourceSum = collectResourceSum.add(produce.getCollectResource());
        }

        List<StrawProduceResVo2> resList = new ArrayList<>(produceMap.values());
        for (StrawProduceResVo2 strawProduceResVo2 : resList) {
            if (collectResourceSum.compareTo(BigDecimal.ZERO) == 0) {
                strawProduceResVo2.setCollectResourceRate(BigDecimal.ZERO);
            } else {
                strawProduceResVo2.setCollectResourceRate(strawProduceResVo2.getCollectResource()
                        .divide(collectResourceSum, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
            }
            if (theoryResourceSum.compareTo(BigDecimal.ZERO) == 0) {
                strawProduceResVo2.setTheoryResourceRate(BigDecimal.ZERO);
            } else {
                strawProduceResVo2.setTheoryResourceRate(strawProduceResVo2.getTheoryResource()
                        .divide(theoryResourceSum, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
            }
        }
        return resList;
    }


    /**
     * 累加产生量数据
     *
     * @param mapItem
     * @param produce
     * @return
     */
    private StrawProduceResVo addProduce(StrawProduceResVo mapItem, StrawProduceResVo produce) {
        mapItem.setTheoryResource(mapItem.getTheoryResource().add(produce.getTheoryResource()));
        mapItem.setCollectResource(mapItem.getCollectResource().add(produce.getCollectResource()));

        return mapItem;
    }

    /**
     * 累加产生量数据2
     *
     * @param mapItem
     * @param produce
     * @return
     */
    public static StrawProduceResVo2 addProduce2(StrawProduceResVo2 mapItem, StrawProduceResVo2 produce) {
        mapItem.setTheoryResource(mapItem.getTheoryResource().add(produce.getTheoryResource()));
        mapItem.setCollectResource(mapItem.getCollectResource().add(produce.getCollectResource()));
        //粮食产量sum
        mapItem.setGrainYield(mapItem.getGrainYield().add(produce.getGrainYield()));
        //播种产量sum
        mapItem.setSeedArea(mapItem.getSeedArea().add(produce.getSeedArea()));

        mapItem.setCollectResourceRate(mapItem.getCollectResourceRate().add(produce.getCollectResourceRate()));
        mapItem.setTheoryResourceRate(mapItem.getTheoryResourceRate().add(produce.getTheoryResourceRate()));


        return mapItem;
    }


    /**
     * 根据区划查询产生量数据
     *
     * @param regionCode 区域信息
     * @param year       年份
     * @return
     */
    private List<StrawProduce> getStrawProduceByRegion(AreaRegionCode regionCode, String year) {
        //根据ID获取行政区划信息
        List<StrawProduce> spList = new ArrayList();

        if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //查询县级数据
            List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(regionCode.getLastId(), year);

            spList = new ArrayList();
            for (ProStillDetail deatil : psdList) { //计算产量并转换bean类型
                deatil.calculateResource();
                StrawProduce strawProduce = new StrawProduce();
                BeanUtils.copyProperties(deatil, strawProduce);
                spList.add(strawProduce);
            }
        } else {  //市级和以上
            String childrenIds = SysRegionUtil.getChildrenRegionIds(regionCode.getLastId());
            //通过所有子区划查，并且聚合数据，「只要上报了，就查」
            spList = strawProduceMapper.sumStrawProduce(IdUtil.getIdsByStr(childrenIds), year, null);
        }
        return spList;
    }

    /**
     * 根据区划查询产生量数据2
     *
     * @param regionCode 区域信息
     * @param year       年份
     * @return
     */
    private List<StrawProduceResVo2> getStrawProduceByRegion2(AreaRegionCode regionCode, String year, List<String> status) {
        //根据ID获取行政区划信息
        List<StrawProduceResVo2> spList = new ArrayList<>();
        /**
         * 添加4
         */
        if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {
            //查询县级数据
            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
            List<ProStillDetail> psdList;
            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                psdList = proStillDetailMapper.getProStillDetailListByAreaId(regionCode.getLastId(), year);
            } else {
                psdList = proStillDetailMapper.getProStillDetailListByAreaId2(regionCode.getLastId(), year, status);
            }
            for (ProStillDetail deatil : psdList) { //计算产量并转换bean类型
                deatil.calculateResource();
                StrawProduceResVo2 strawProduce = new StrawProduceResVo2();
                BeanUtils.copyProperties(deatil, strawProduce);
                spList.add(strawProduce);
            }
        } else {
            //市级和以上
            String childrenIds = SysRegionUtil.getChildrenRegionIds(regionCode.getLastId());
            //通过所有子区划查，并且聚合数据，「查询审核通过」
            //Constants.ExamineState.PASSED
            spList = strawProduceMapper.sumStrawProduceTwo(IdUtil.getIdsByStr(childrenIds), year, status);
        }
        return spList;
    }


    @Override
    public List<StrawUtilizeResVo> getStrawUtilzeData(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//判断用户是否有权限查询该区域

        //获取秸秆类型数据
        List<StrawUtilizeResVo> utilizes = getStrawUtilizeDataByRegionList(regionList, vo.getYear());
        //转换为Map
        Map<String, StrawUtilizeResVo> utilizeMap = utilizes.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));
        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        List<StrawUtilizeResVo> resList = new ArrayList();
        StrawUtilizeResVo temp = null;
        //写入秸秆类型名称
        for (SysDict strawType : dictList) {
            temp = utilizeMap.get(strawType.getDictcode());
            if (temp == null)
                temp = new StrawUtilizeResVo(); //没有数据时的初始化

            temp.setStrawType(strawType.getDictcode());
            temp.setStrawName(strawType.getDictname());

            resList.add(temp);
        }

        //计算合计
        StrawUtilizeResVo sum = addSumUtilize(resList);
        resList.add(sum);

        return resList;
    }

    /**
     * 获取利用量汇总数据2
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawUtilizeResVo3> getStrawUtilzeData2(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//判断用户是否有权限查询该区域

        //获取秸秆类型数据
        List<StrawUtilizeResVo3> utilizes = getStrawUtilizeDataByRegionList2(regionList, vo.getYear(), vo.getCheckInfoFlag());
        //转换为Map
        Map<String, StrawUtilizeResVo3> utilizeMap = utilizes.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));
        //秸秆类型
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        List<StrawUtilizeResVo3> resList = new ArrayList();
        StrawUtilizeResVo3 temp = null;
        //写入秸秆类型名称
        for (SysDictionary strawType : dictList) {
            temp = utilizeMap.get(strawType.getDictKey());
            if (temp == null)
                temp = new StrawUtilizeResVo3(); //没有数据时的初始化

            temp.setStrawType(strawType.getDictKey());
            temp.setStrawName(strawType.getDictValue());

            resList.add(temp);
            // 移除
            utilizeMap.remove(strawType.getDictKey());
        }
        if (utilizeMap.size() > 0) {
            List<SysDictionary> otherDictionaries = sysDictionaryMapper.getOtherDictionaries(Constants.DictionaryType.STRAW_TYPE, new ArrayList<>(utilizeMap.keySet()));
            Map<String, String> otherMap = otherDictionaries.stream().collect(Collectors.toMap(SysDictionary::getDictKey, SysDictionary::getDictValue));
            for (Map.Entry<String, StrawUtilizeResVo3> entry : utilizeMap.entrySet()) {
                entry.getValue().setStrawName(otherMap.get(entry.getKey()));
            }
            resList.addAll(utilizeMap.values());
        }

        //计算合计
        StrawUtilizeResVo3 sum = addSumUtilize2(resList);
        resList.add(0, sum);


        return resList;
    }


    /**
     * 计算合计
     *
     * @param resList
     * @return
     */
    private StrawUtilizeResVo addSumUtilize(List<StrawUtilizeResVo> resList) {
        StrawUtilizeResVo sum = new StrawUtilizeResVo();

        for (StrawUtilizeResVo utilize : resList) {
            sum = addStrawUtilize(sum, utilize);
        }
        //综合利用率
        //这里修改下，因这里秸秆利用量字段存的就是综合利用量
//        sum.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(sum.getProStrawUtilize(),sum.getMainTotalOther(),sum.getYieldAllExport(),sum.getCollectResource()));
        sum.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(sum.getProStrawUtilize(), null, null, sum.getCollectResource()));
        sum.setStrawName("合计");
        sum.setStrawType("sum");
        return sum;
    }

    /**
     * 计算合计2
     *
     * @param resList
     * @return
     */
    public static StrawUtilizeResVo3 addSumUtilize2(List<StrawUtilizeResVo3> resList) {
        StrawUtilizeResVo3 sum = new StrawUtilizeResVo3();

        for (StrawUtilizeResVo3 utilize : resList) {
            sum = addStrawUtilizeNoReturnSource(sum, utilize);
        }
        //计算综合利用率
        sum.setComprehensiveRate(StrawCalculatorUtil2.calculationComprehensiveRote(sum.getStrawUsage(), sum.getCollectResource()));
        ///计算综合利用能力指数
        sum.setComprehensiveIndex(StrawCalculatorUtil2.calculationComprehensiveindex(sum.getAllTotal(), null, null, sum.getCollectResource()));
        //计算产业化能力指数
        sum.setIndustrializationIndex(StrawCalculatorUtil2.calculationinIndustrializationIndex(sum.getMainTotal(), sum.getCollectResource()));


        sum.setStrawName("合计");
        sum.setStrawType("sum");
        return sum;
    }


    /**
     * 根据区域列表，获取利用量汇总数据，该数据未计算
     *
     * @param regionList 区域集合
     * @param year       年份
     * @return
     */
    private List<StrawUtilizeResVo> getStrawUtilizeDataByRegionList(List<AreaRegionCode> regionList, String year) {
        List<StrawUtilizeResVo> utilizes = new ArrayList();
        for (AreaRegionCode regionCode : regionList) {
            //把所有查出的利用量汇总数据，装入一个集合中。
            utilizes.addAll(getStrawUtilizeDataByRegion(regionCode, year));
        }

        //以秸秆类型为单位分组
        Map<String, StrawUtilizeResVo> utilizeMap = new HashMap();
        for (StrawUtilizeResVo utilize : utilizes) {
            StrawUtilizeResVo mapItem = utilizeMap.get(utilize.getStrawType());
            if (mapItem != null) {
                mapItem = addStrawUtilize(mapItem, utilize);
                utilizeMap.put(mapItem.getStrawType(), mapItem);
            } else
                utilizeMap.put(utilize.getStrawType(), utilize);
        }

        List<StrawUtilizeResVo> resList = new ArrayList(utilizeMap.values());
        //综合利用率
        resList.forEach(sum -> {
            sum.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(sum.getProStrawUtilize(), sum.getMainTotalOther(), sum.getYieldAllExport(), sum.getCollectResource()));
            //这里提供给前端秸秆利用量数据，为综合利用量
            sum.setProStrawUtilize(StrawCalculatorUtil.calculationComprehensiveUtilize(sum.getProStrawUtilize(), sum.getMainTotalOther(), sum.getYieldAllExport()));
        });

        return resList;
    }


    /**
     * 根据区域列表，获取利用量汇总数据，该数据未计算2
     *
     * @param regionList 区域集合
     * @param year       年份
     * @return
     */
    private List<StrawUtilizeResVo3> getStrawUtilizeDataByRegionList2(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();
        // 当前账号
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode regionCode : regionList) {
            //把所有查出的新利用量汇总数据，装入一个集合中。
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), regionCode.getLastId(), year, checkInfoFlag);
            utilizes.addAll(getStrawUtilizeDataByRegion2(regionCode, year, statusList));
        }

        //以秸秆类型为单位分组
        Map<String, StrawUtilizeResVo3> utilizeMap = new HashMap();
        for (StrawUtilizeResVo3 utilize : utilizes) {
            StrawUtilizeResVo3 mapItem = utilizeMap.get(utilize.getStrawType());
            if (mapItem != null) {
                mapItem = addStrawUtilize2(mapItem, utilize);
                utilizeMap.put(mapItem.getStrawType(), mapItem);
            } else
                utilizeMap.put(utilize.getStrawType(), utilize);
        }

        List<StrawUtilizeResVo3> resList = new ArrayList(utilizeMap.values());

        resList.forEach(sum -> {
            //计算综合利用率
            sum.setComprehensiveRate(StrawCalculatorUtil2.calculationComprehensiveRote(sum.getStrawUsage(), sum.getCollectResource()));
            ///计算综合利用能力指数
            sum.setComprehensiveIndex(StrawCalculatorUtil2.calculationComprehensiveindex(sum.getAllTotal(), null, null, sum.getCollectResource()));
            //计算产业化能力指数
            sum.setIndustrializationIndex(StrawCalculatorUtil2.calculationinIndustrializationIndex(sum.getMainTotal(), sum.getCollectResource()));
        });

        return resList;
    }


    /**
     * 累加两个同类对象的数据
     *
     * @param utilizeSource
     * @param utilizeAdd
     * @return
     */
    private StrawUtilizeResVo addStrawUtilize(StrawUtilizeResVo utilizeSource, StrawUtilizeResVo utilizeAdd) {
        utilizeSource.setDisperseFertilising(utilizeSource.getDisperseFertilising().add(utilizeAdd.getDisperseFertilising()));
        utilizeSource.setDisperseForage(utilizeSource.getDisperseForage().add(utilizeAdd.getDisperseForage()));
        utilizeSource.setDisperseFuel(utilizeSource.getDisperseFuel().add(utilizeAdd.getDisperseFuel()));
        utilizeSource.setDisperseBase(utilizeSource.getDisperseBase().add(utilizeAdd.getDisperseBase()));
        utilizeSource.setDisperseMaterial(utilizeSource.getDisperseMaterial().add(utilizeAdd.getDisperseMaterial()));
        utilizeSource.setDisperseTotal(utilizeSource.getDisperseTotal().add(utilizeAdd.getDisperseTotal()));
        utilizeSource.setMainFertilising(utilizeSource.getMainFertilising().add(utilizeAdd.getMainFertilising()));
        utilizeSource.setMainForage(utilizeSource.getMainForage().add(utilizeAdd.getMainForage()));
        utilizeSource.setMainFuel(utilizeSource.getMainFuel().add(utilizeAdd.getMainFuel()));
        utilizeSource.setMainBase(utilizeSource.getMainBase().add(utilizeAdd.getMainBase()));
        utilizeSource.setMainMaterial(utilizeSource.getMainMaterial().add(utilizeAdd.getMainMaterial()));
        utilizeSource.setMainTotal(utilizeSource.getMainTotal().add(utilizeAdd.getMainTotal()));
        utilizeSource.setMainTotalOther(utilizeSource.getMainTotalOther().add(utilizeAdd.getMainTotalOther()));
        utilizeSource.setProStillField(utilizeSource.getProStillField().add(utilizeAdd.getProStillField()));
        utilizeSource.setYieldAllExport(utilizeSource.getYieldAllExport().add(utilizeAdd.getYieldAllExport()));
        utilizeSource.setProStrawUtilize(utilizeSource.getProStrawUtilize().add(utilizeAdd.getProStrawUtilize()));
        utilizeSource.setCollectResource(utilizeSource.getCollectResource().add(utilizeAdd.getCollectResource()));
        utilizeSource.setTheoryResource(utilizeSource.getTheoryResource().add(utilizeAdd.getTheoryResource()));

        return utilizeSource;
    }

    /**
     * 累加两个同类对象的数据2
     *
     * @param utilizeSource
     * @param a
     * @return
     */
    private StrawUtilizeResVo3 addStrawUtilize2(StrawUtilizeResVo3 utilizeSource, StrawUtilizeResVo3 a) {


        utilizeSource.setStrawUsage(utilizeSource.getStrawUsage().add(a.getStrawUsage()));
        utilizeSource.setAllTotal(utilizeSource.getAllTotal().add(a.getAllTotal()));
        utilizeSource.setFertilizer(utilizeSource.getFertilizer().add(a.getFertilizer()));
        utilizeSource.setFuel(utilizeSource.getFuel().add(a.getFuel()));
        utilizeSource.setBasic(utilizeSource.getBasic().add(a.getBasic()));
        utilizeSource.setRawMaterial(utilizeSource.getRawMaterial().add(a.getRawMaterial()));
        utilizeSource.setFeed(utilizeSource.getFeed().add(a.getFeed()));
        utilizeSource.setOther(utilizeSource.getOther().add(a.getOther()));
        utilizeSource.setYieldExport(utilizeSource.getYieldExport().add(a.getYieldExport()));
        utilizeSource.setCollectResource(utilizeSource.getCollectResource().add(a.getCollectResource()));
        utilizeSource.setMainTotal(utilizeSource.getMainTotal().add(a.getMainTotal()));
        utilizeSource.setReturnResource(utilizeSource.getReturnResource().add(a.getReturnResource()));
//        utilizeSource.setDisperseFertilising(utilizeSource.getDisperseFertilising().add(utilizeAdd.getDisperseFertilising()));
//        utilizeSource.setDisperseForage(utilizeSource.getDisperseForage().add(utilizeAdd.getDisperseForage()));
//        utilizeSource.setDisperseFuel(utilizeSource.getDisperseFuel().add(utilizeAdd.getDisperseFuel()));
//        utilizeSource.setDisperseBase(utilizeSource.getDisperseBase().add(utilizeAdd.getDisperseBase()));
//        utilizeSource.setDisperseMaterial(utilizeSource.getDisperseMaterial().add(utilizeAdd.getDisperseMaterial()));
//        utilizeSource.setDisperseTotal(utilizeSource.getDisperseTotal().add(utilizeAdd.getDisperseTotal()));
//        utilizeSource.setMainFertilising(utilizeSource.getMainFertilising().add(utilizeAdd.getMainFertilising()));
//        utilizeSource.setMainForage(utilizeSource.getMainForage().add(utilizeAdd.getMainForage()));
//        utilizeSource.setMainFuel(utilizeSource.getMainFuel().add(utilizeAdd.getMainFuel()));
//        utilizeSource.setMainBase(utilizeSource.getMainBase().add(utilizeAdd.getMainBase()));
//        utilizeSource.setMainMaterial(utilizeSource.getMainMaterial().add(utilizeAdd.getMainMaterial()));
//        utilizeSource.setMainTotal(utilizeSource.getMainTotal().add(utilizeAdd.getMainTotal()));
//        utilizeSource.setMainTotalOther(utilizeSource.getMainTotalOther().add(utilizeAdd.getMainTotalOther()));
//        utilizeSource.setProStillField(utilizeSource.getProStillField().add(utilizeAdd.getProStillField()));
//        utilizeSource.setYieldAllExport(utilizeSource.getYieldAllExport().add(utilizeAdd.getYieldAllExport()));
//        utilizeSource.setProStrawUtilize(utilizeSource.getProStrawUtilize().add(utilizeAdd.getProStrawUtilize()));
//        utilizeSource.setCollectResource(utilizeSource.getCollectResource().add(utilizeAdd.getCollectResource()));
//        utilizeSource.setTheoryResource(utilizeSource.getTheoryResource().add(utilizeAdd.getTheoryResource()));

        return utilizeSource;
    }

    /**
     * 竖向累加利用量汇总合计
     *
     * @param utilizeSource
     * @param a
     * @return
     */
    public static StrawUtilizeResVo3 addStrawUtilizeNoReturnSource(StrawUtilizeResVo3 utilizeSource, StrawUtilizeResVo3 a) {


        utilizeSource.setStrawUsage(utilizeSource.getStrawUsage().add(a.getStrawUsage()));
        utilizeSource.setAllTotal(utilizeSource.getAllTotal().add(a.getAllTotal()));
        utilizeSource.setFertilizer(utilizeSource.getFertilizer().add(a.getFertilizer()));
        utilizeSource.setFuel(utilizeSource.getFuel().add(a.getFuel()));
        utilizeSource.setBasic(utilizeSource.getBasic().add(a.getBasic()));
        utilizeSource.setRawMaterial(utilizeSource.getRawMaterial().add(a.getRawMaterial()));
        utilizeSource.setFeed(utilizeSource.getFeed().add(a.getFeed()));
        utilizeSource.setOther(utilizeSource.getOther().add(a.getOther()));
        utilizeSource.setYieldExport(utilizeSource.getYieldExport().add(a.getYieldExport()));
        utilizeSource.setCollectResource(utilizeSource.getCollectResource().add(a.getCollectResource()));
        utilizeSource.setMainTotal(utilizeSource.getMainTotal().add(a.getMainTotal()));
        utilizeSource.setReturnResource(utilizeSource.getReturnResource().add(a.getReturnResource()));


        return utilizeSource;
    }


    /**
     * 根据区域信息，查单个区域 的利用量汇总数据
     *
     * @param region 区域信息
     * @param year   年份
     * @return
     */
    private List<StrawUtilizeResVo> getStrawUtilizeDataByRegion(AreaRegionCode region, String year) {
        List<StrawUtilizeResVo> utilizes = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //查询县级
            utilizes = getCountyStrawUtilize(region.getLastId(), year);
        } else {//市级及以上
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            List<StrawUtilizeSum> susList = strawUtilizeSumMapper.selectStrawUtilizeByAreaIds(IdUtil.getIdsByStr(childrenIds), year);
            utilizes = assemblyStrawUtilizeBySusList(susList);
        }

        return utilizes;
    }

    /**
     * 根据区域信息，查单个区域 的利用量汇总数据2
     *
     * @param region 区域信息
     * @param year   年份
     * @return
     */
    private List<StrawUtilizeResVo3> getStrawUtilizeDataByRegion2(AreaRegionCode region, String year, List<String> status) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //查询县级

            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);

            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                utilizes = getCountyStrawUtilize2(region.getLastId(), year);
            } else {
                utilizes = getCountyStrawUtilizeStatus(region.getLastId(), year, status);
            }


        } else {//市级及以上
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            //List<StrawUtilizeSum>  susList = strawUtilizeSumMapper.selectStrawUtilizeByAreaIds(childrenIds, year);
            //需要从新的秸秆利用汇总查
            List<StrawUsageSum> susList = strawUsageSumMapper.selectStrawUtilizeByAreaIds(IdUtil.getIdsByStr(childrenIds), year, status);

            utilizes = assemblyStrawUtilizeBySusList2(susList);
        }

        return utilizes;
    }


    /**
     * 转换集合元素
     *
     * @param susList
     * @return
     */
    private List<StrawUtilizeResVo> assemblyStrawUtilizeBySusList(List<StrawUtilizeSum> susList) {
        List<StrawUtilizeResVo> resList = new ArrayList<>();
        StrawUtilizeResVo temp = null;
        for (StrawUtilizeSum sus : susList) {
            temp = new StrawUtilizeResVo();
            BeanUtils.copyProperties(sus, temp);
            temp.setYieldAllExport(sus.getExportYieldTotal());
            resList.add(temp);
        }
        return resList;
    }

    /**
     * 转换集合元素2
     *
     * @param susList
     * @return
     */
    //todo copy 还需 3个属性
    private List<StrawUtilizeResVo3> assemblyStrawUtilizeBySusList2(List<StrawUsageSum> susList) {
        List<StrawUtilizeResVo3> resList = new ArrayList<>();
        StrawUtilizeResVo3 temp = null;
        for (StrawUsageSum sus : susList) {
            temp = new StrawUtilizeResVo3();
            BeanUtils.copyProperties(sus, temp);
            resList.add(temp);
        }
        return resList;
    }

    /**
     * 获取产生量与利用量汇总数据
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawProduceUtilizeResVo> findStrawProduceAndUtilzeData(AggregateQueryVo vo) {
        //检查访问权限，并返回区域信集合
        List<AreaRegionCode> regionList = checkQueryRightsNew(vo);
        //获取产生量与利用量汇总数据
        List<StrawProduceUtilizeResVo> resList = getProduceUtilizeByRegionList2(regionList, vo.getYear(), vo.getCheckInfoFlag());
        if (resList.size() > 0) {
            //写入区域名称
            List<String> areaIds = resList.stream().map(p -> p.getAreaId()).collect(Collectors.toList());
            final Map<String, String> nameMap = SysRegionUtil.getRegionNameMapByCodes(areaIds);
            resList.forEach(p -> {
                p.setAreaName(nameMap.get(p.getAreaId()));
                //如果是部级，则设置区域名为"中国"
                if (p.getAreaId().equals(Constants.ZHONGGUO_AREA_ID))
                    p.setAreaName(Constants.ZHONGGUO_AREA_NAME);
            });
        }

        return resList;
    }

    /**
     * 获取产生量与利用量汇总数据2
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawProduceUtilizeResVo2> findStrawProduceAndUtilzeData2(AggregateQueryVo vo) {
        //检查访问权限，并返回区域信集合
        List<AreaRegionCode> regionList = checkQueryRights(vo);
        //获取产生量与利用量汇总数据
        List<StrawProduceUtilizeResVo> resList = getProduceUtilizeByRegionList(regionList, vo.getYear(), vo.getCheckInfoFlag());
        if (resList.size() > 0) {
            //写入区域名称
            List<String> areaIds = resList.stream().map(p -> p.getAreaId()).collect(Collectors.toList());
            final Map<String, String> nameMap = SysRegionUtil.getRegionNameMapByCodes(areaIds);
            resList.forEach(p -> {
                p.setAreaName(nameMap.get(p.getAreaId()));
                //如果是部级，则设置区域名为"中国"
                if (p.getAreaId().equals(Constants.ZHONGGUO_AREA_ID))
                    p.setAreaName(Constants.QUANGUO_AREA_NAME);
            });
        }

        //ArrayList<StrawProduceUtilizeResVo2> vo2s = new ArrayList<>();


        List<StrawProduceUtilizeResVo2> vo2s = BeanUtil.convertListToList(resList, new TypeReference<List<StrawProduceUtilizeResVo2>>() {
        });

        //BeanUtils.copyProperties(resList,vo2s);

//        for (StrawProduceUtilizeResVo2 vo2 : vo2s) {
//            vo2.setSum(vo2.getFertilising().add(vo2.getForage()).add(vo2.getFuel()).add(vo2.getBase()).add(vo2.getMaterial()));
//        }


        return vo2s;
    }


    /**
     * 根据区域集合，获取产生量与利用量汇总数据，以区域为单位
     *
     * @param regionList
     * @param year
     * @return
     */
    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegionList(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawProduceUtilizeResVo> resList = new ArrayList<>();
        List<StrawProduceUtilizeResVo> temp = null;
        // 当前账号
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode region : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), region.getLastId(), year, checkInfoFlag);
            temp = getProduceUtilizeByRegion(region, year, statusList);
            resList.addAll(temp);
        }

        return resList;
    }

    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegionList2(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawProduceUtilizeResVo> resList = new ArrayList<>();
        List<StrawProduceUtilizeResVo> temp = null;
        // 当前账号
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode region : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), region.getLastId(), year, checkInfoFlag);
            temp = getProduceUtilizeByRegion2(region, year, statusList);
            resList.addAll(temp);
        }

        return resList;
    }

    /**
     * 根据区域信息，获取产生量与利用量汇总数据
     *
     * @param region
     * @param year
     * @return
     */
    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegion(AreaRegionCode region, String year, List<String> statusList) {
        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) { //县级
            //查询状态为审核通过
//            List<String> ids = collectFlowMapper.selectAreaIdByIdsAndStatus(year, region.getLastId(), Constants.ExamineState.PASSED.toString());
//            if(!ids.isEmpty()) {
            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
            StrawProduceUtilizeResVo pu;
            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                pu = getCountyProduceUtilize(region.getLastId(), year);
            } else {
                pu = getCountyProduceUtilizeStatus(region.getLastId(), year, statusList);
            }
            puList.add(pu);
        } else {
            // 2021-04-28 经产品确认查询上报和审核通过的状态
            //下级数据
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            if (!StringUtils.hasText(childrenIds))   //没有下级
                return puList;
            //查询当前下级数据
            List<String> areaIds = IdUtil.getIdsByStr(childrenIds);
            List<ProductionUsageSum> productionUsageSumList = productionUsageSumMapper.selectProductionUsageSum(areaIds, year, statusList);
            //List<StrawUtilizeSumTotal> stList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(childrenIds, year, status);
            // 按区划排序
            List<ProductionUsageSum> sortedListByAreaId = new ArrayList<>();
            Map<String, ProductionUsageSum> productionUsageSumMap = productionUsageSumList.stream().collect(Collectors.toMap(ProductionUsageSum::getAreaId, Function.identity(), (key1, key2) -> key2));
            for (String areaId : areaIds) {
                ProductionUsageSum productionUsageSum = productionUsageSumMap.get(areaId);
                if (productionUsageSum != null) {
                    sortedListByAreaId.add(productionUsageSum);
                }
            }
            puList = getStrawProduceUtilizeResVo(sortedListByAreaId);

            // puList = assemblyProduceUtilizeBySTList(stList);
            //本级数据(相当于合计)
            StrawProduceUtilizeResVo sum = addSumProduceUtilize(puList);
            calculationProduceUtilize(sum);
            sum.setAreaId(region.getLastId());
            puList.add(0, sum);
            //计算
            //puList.forEach(p -> calculationProduceUtilize(p));
        }

        //组装综合利用量 = 秸秆利用量 - 调入量 + 调出量
        //puList.forEach(p-> p.setProStrawUtilize( StrawCalculatorUtil.calculationComprehensiveUtilize(p.getProStrawUtilize(),p.getMainTotalOther(),p.getYieldAllExport())));

        return puList;
    }

    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegion2(AreaRegionCode region, String year, List<String> statusList) {
        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        List<StrawProduceUtilizeResVo> puList2 = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) { //县级
            //查询状态为审核通过
//            List<String> ids = collectFlowMapper.selectAreaIdByIdsAndStatus(year, region.getLastId(), Constants.ExamineState.PASSED.toString());
//            if(!ids.isEmpty()) {
            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);

            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                StrawProduceUtilizeResVo pu = getCountyProduceUtilize(region.getLastId(), year);
                puList.add(pu);
            } else {
                StrawProduceUtilizeResVo pu = getCountyProduceUtilizeStatus(region.getLastId(), year, statusList);
                puList.add(pu);
            }

        } else {
            //下级数据
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            if (!StringUtils.hasText(childrenIds))   //没有下级
                return puList;
            //查询当前下级数据
            List<ProductionUsageSum> productionUsageSumList = productionUsageSumMapper.selectProductionUsageSum(IdUtil.getIdsByStr(childrenIds), year, statusList);
            //List<StrawUtilizeSumTotal> stList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(childrenIds, year, status);

            puList2 = getStrawProduceUtilizeResVo(productionUsageSumList);

            // puList = assemblyProduceUtilizeBySTList(stList);
            //本级数据(相当于合计)
            StrawProduceUtilizeResVo sum = addSumProduceUtilize(puList2);
            calculationProduceUtilize(sum);
            sum.setAreaId(region.getLastId());
            puList.add(sum);
            //计算
            //puList.forEach(p -> calculationProduceUtilize(p));
        }

        //组装综合利用量 = 秸秆利用量 - 调入量 + 调出量
        //puList.forEach(p-> p.setProStrawUtilize( StrawCalculatorUtil.calculationComprehensiveUtilize(p.getProStrawUtilize(),p.getMainTotalOther(),p.getYieldAllExport())));

        return puList;
    }

    /**
     * 对象转换
     *
     * @param p
     * @return
     */
    private List<StrawProduceUtilizeResVo> getStrawProduceUtilizeResVo(List<ProductionUsageSum> p) {
        ArrayList<StrawProduceUtilizeResVo> strawProduceUtilizeResVos = new ArrayList<>();
        if (p == null || p.size() == 0) {
            return strawProduceUtilizeResVos;
        } else {
            StrawProduceUtilizeResVo s = null;
            for (ProductionUsageSum pus : p) {
                s = new StrawProduceUtilizeResVo();
                s.setAreaId(pus.getAreaId());
                s.setTheoryResource(pus.getProduce());
                s.setCollectResource(pus.getCollect());
                s.setProStrawUtilize(pus.getStrawUsage());
                s.setFertilising(pus.getFertilizer());
                s.setForage(pus.getFeed());
                s.setFuel(pus.getFuel());
                s.setBase(pus.getBasic());
                s.setMaterial(pus.getRawMaterial());
                s.setComprehensive(pus.getComprehensiveRate());
                s.setComprehensiveIndex(pus.getComprehensiveIndex());
                s.setIndustrializationIndex(pus.getIndustrializationIndex());
                s.setSum(pus.getAllTotal());
                s.setMainTotal(pus.getMainTotal());
                strawProduceUtilizeResVos.add(s);
            }
            return strawProduceUtilizeResVos;
        }
    }

    /**
     * 计算，综合利用率，综合利用能力指数，产业化利用能力指数
     *
     * @param pu
     */
    private void calculationProduceUtilize(StrawProduceUtilizeResVo pu) {
//        //综合利用率
//        pu.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(pu.getProStrawUtilize(),pu.getMainTotalOther(),pu.getYieldAllExport(),pu.getCollectResource()));
//        //综合利用能力指数
//        pu.setComprehensiveIndex(StrawCalculatorUtil.calculationComprehensiveIndex(pu.getProStrawUtilize(),pu.getYieldAllExport(),pu.getCollectResource()));
//        //产业化利用能力指数
//        pu.setIndustrializationIndex(StrawCalculatorUtil.calculationinIndustrializationIndex(pu.getMainTotal(),pu.getCollectResource()));

        //计算综合利用率
        pu.setComprehensive(StrawCalculatorUtil2.calculationComprehensiveRote(pu.getProStrawUtilize(), pu.getCollectResource()));
        ///计算综合利用能力指数
        pu.setComprehensiveIndex(StrawCalculatorUtil2.calculationComprehensiveindex(pu.getSum(), null, null, pu.getCollectResource()));
        //计算产业化能力指数
        pu.setIndustrializationIndex(StrawCalculatorUtil2.calculationinIndustrializationIndex(pu.getMainTotal(), pu.getCollectResource()));


    }

    /**
     * 累加产生量与利用量数据
     *
     * @param puList
     * @return
     */
    private StrawProduceUtilizeResVo addSumProduceUtilize(List<StrawProduceUtilizeResVo> puList) {
        StrawProduceUtilizeResVo sum = new StrawProduceUtilizeResVo();
        for (StrawProduceUtilizeResVo pu : puList) {
            sum = addProduceUtilize(sum, pu);
        }

        return sum;
    }

    /**
     * 由数据库bean，组装成resVo
     *
     * @param stList
     * @return List<StrawProduceUtilizeResVo>
     */
    private List<StrawProduceUtilizeResVo> assemblyProduceUtilizeBySTList(List<StrawUtilizeSumTotal> stList) {
        List<StrawProduceUtilizeResVo> resList = new ArrayList();
        StrawProduceUtilizeResVo temp = null;
        for (StrawUtilizeSumTotal st : stList) {
            temp = new StrawProduceUtilizeResVo();
            temp.setAreaId(st.getAreaId());
            temp.setTheoryResource(st.getTheoryResource());
            temp.setCollectResource(st.getCollectResource());
            temp.setProStrawUtilize(st.getProStrawUtilize());
            temp.setFertilising(st.getMainFertilising().add(st.getDisperseFertilising()));
            temp.setForage(st.getMainForage().add(st.getDisperseForage()));
            temp.setFuel(st.getMainFuel().add(st.getDisperseFuel()));
            temp.setBase(st.getMainBase().add(st.getDisperseBase()));
            temp.setMaterial(st.getMainMaterial().add(st.getDisperseMaterial()));
            temp.setProStillField(st.getReturnResource());
            temp.setYieldAllExport(st.getExportYieldTotal());
            temp.setMainTotal(st.getMainTotal());
            temp.setDisperseTotal(st.getDisperseTotal());
            temp.setMainTotalOther(st.getMainTotalOther());

            resList.add(temp);
        }

        return resList;
    }

    /**
     * 获取县级产生量与利用量汇总数据
     *
     * @param areaId
     * @param year
     * @return
     */
    private StrawProduceUtilizeResVo getCountyProduceUtilize(String areaId, String year) {
        //县级基础数据对象
        CountyProduceUtilizeBase countyBaseInfo = getCountyBaseInfo(areaId, year);

        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        if (countyBaseInfo.hasProFill() && countyBaseInfo.hasUtilize()) {
            Map<String, DisperseUtilizeDetail> dudMap = countyBaseInfo.getDudMap();
            Map<String, StrawUtilizeDetail> sudMap = countyBaseInfo.getSudMap();
            StrawProduceUtilizeResVo temp = null;
            for (ProStillDetail psd : countyBaseInfo.getPsdList()) {
                //组装产生量与利用量汇总数据
                temp = StrawCalculatorUtil2.assemblyProduceUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
                puList.add(temp);
            }
        }

        StrawProduceUtilizeResVo pu = new StrawProduceUtilizeResVo();
        pu.setAreaId(areaId);

        for (StrawProduceUtilizeResVo temp : puList) {
            pu = addProduceUtilize(pu, temp);
        }

        //计算
        calculationProduceUtilize(pu);

        return pu;
    }

    private StrawProduceUtilizeResVo getCountyProduceUtilizeStatus(String areaId, String year, List<String> status) {
        //县级基础数据对象
        CountyProduceUtilizeBase countyBaseInfo = getCountyBaseInfoStatus(areaId, year, status);

        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        if (countyBaseInfo.hasProFill() && countyBaseInfo.hasUtilize()) {
            Map<String, DisperseUtilizeDetail> dudMap = countyBaseInfo.getDudMap();
            Map<String, StrawUtilizeDetail> sudMap = countyBaseInfo.getSudMap();
            StrawProduceUtilizeResVo temp = null;
            for (ProStillDetail psd : countyBaseInfo.getPsdList()) {
                //组装产生量与利用量汇总数据
                temp = StrawCalculatorUtil2.assemblyProduceUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
                puList.add(temp);
            }
        }

        StrawProduceUtilizeResVo pu = new StrawProduceUtilizeResVo();
        pu.setAreaId(areaId);

        for (StrawProduceUtilizeResVo temp : puList) {
            pu = addProduceUtilize(pu, temp);
        }

        //计算
        calculationProduceUtilize(pu);

        return pu;
    }

    /**
     * 累加产生量与利用量汇总数据
     *
     * @param pu
     * @param target
     * @return
     */
    private StrawProduceUtilizeResVo addProduceUtilize(StrawProduceUtilizeResVo pu, StrawProduceUtilizeResVo target) {
        pu.setFertilising(pu.getFertilising().add(target.getFertilising()));
        pu.setForage(pu.getForage().add(target.getForage()));
        pu.setFuel(pu.getFuel().add(target.getFuel()));
        pu.setBase(pu.getBase().add(target.getBase()));
        pu.setMaterial(pu.getMaterial().add(target.getMaterial()));
        pu.setMainTotal(pu.getMainTotal().add(target.getMainTotal()));
        pu.setMainTotalOther(pu.getMainTotalOther().add(target.getMainTotalOther()));
        pu.setYieldAllExport(pu.getYieldAllExport().add(target.getYieldAllExport()));
        pu.setTheoryResource(pu.getTheoryResource().add(target.getTheoryResource()));
        pu.setCollectResource(pu.getCollectResource().add(target.getCollectResource()));
        pu.setProStrawUtilize(pu.getProStrawUtilize().add(target.getProStrawUtilize()));
        pu.setProStillField(pu.getProStillField().add(target.getProStillField()));
        //pu.setDisperseTotal(pu.getDisperseTotal().add(target.getDisperseTotal()));
        pu.setSum(pu.getSum().add(target.getSum()));

        return pu;
    }

    /**
     * 获取区县的利用量数据
     *
     * @param areaId
     * @param year
     * @return
     */
    private List<StrawUtilizeResVo> getCountyStrawUtilize(String areaId, String year) {
        List<StrawUtilizeResVo> utilizes = new ArrayList();   //要返回的数据

        //获取 产生量与直接还田量数据
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        if (psdList.isEmpty())  //县级未填写产生量数据
            return utilizes;

        //获取 分散利用量
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);
        //获取 市场主体利用量
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);
        if (dudList.isEmpty() && sudList.isEmpty())  //县级未填写利用量数据
            return utilizes;

        //组装到Map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        for (DisperseUtilizeDetail detail : dudList) {
            dudMap.put(detail.getStrawType(), detail);
        }
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
        for (StrawUtilizeDetail detail : sudList) {
            sudMap.put(detail.getStrawType(), detail);
        }

        StrawUtilizeResVo temp = null;
        for (ProStillDetail psd : psdList) {
            //组装利用量汇总数据
            temp = StrawCalculatorUtil.assemblyUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
            utilizes.add(temp);
        }

        return utilizes;
    }


    /**
     * 获取区县的利用量数据2
     *
     * @param areaId
     * @param year
     * @return
     */
    private List<StrawUtilizeResVo3> getCountyStrawUtilize2(String areaId, String year) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();   //要返回的数据

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
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        for (DisperseUtilizeDetail detail : dudList) {
            dudMap.put(detail.getStrawType(), detail);
        }
        //把秸秆利用以秸秆类型组装到map
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
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


    /**
     * 获取区县的利用量数据2
     *
     * @param areaId
     * @param year
     * @return
     */
    private List<StrawUtilizeResVo3> getCountyStrawUtilizeStatus(String areaId, String year, List<String> status) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();   //要返回的数据

        //获取 产生量与直接还田量数据
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId2(areaId, year, status);
        if (psdList.isEmpty())  //县级未填写产生量数据
            return utilizes;

        //获取 分散利用量(农户分散比例已经计算)
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaIdStatus(areaId, year, status);
        //获取 市场主体利用量
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaIdStatus(areaId, year, status);
        if (dudList.isEmpty() && sudList.isEmpty())  //县级未填写利用量数据
            return utilizes;

        //把秸秆分散利用以秸秆类型组装到map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        for (DisperseUtilizeDetail detail : dudList) {
            dudMap.put(detail.getStrawType(), detail);
        }
        //把秸秆利用以秸秆类型组装到map
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
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

    /**
     * 判断用户是否有查询权限
     * 并返回所查询的区域信息
     *
     * @param vo 查询参数对象
     * @return List<AreaRegionCode> 所查询的区域信息
     * @throws com.sofn.ducss.exception.SofnException 如果有错误信息直接抛出
     */
    private List<AreaRegionCode> checkQueryRights(AggregateQueryVo vo) {
        String[] ids = null;
        if (!StringUtils.hasText(vo.getAreaIds())) {  //默认是查全国
            ids = new String[]{"100000"};
        } else
            ids = vo.getAreaIds().split(",");
        List<AreaRegionCode> regionList = new ArrayList();
        AreaRegionCode temp = null;  //临时变量

        //存临时areaId值，用于检测多选的区域是否存在上下级关系
        Set<String> provinceSet = new HashSet<>();  //省
        Set<String> citySet = new HashSet<>();  //市
        for (String areaId : ids) {
            temp = SysRegionUtil.getRegionCodeByLastCode(areaId);
            regionList.add(temp);

            //取对应级别的areaId赋值到set中
            if (temp.getRegionLevel().equals(RegionLevel.PROVINCE.getCode())) provinceSet.add(temp.getProvinceId());
            if (temp.getRegionLevel().equals(RegionLevel.CITY.getCode())) provinceSet.add(temp.getCityId());
        }
        //本级区域信息
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();

        //错误信息
        String errMsg = null;
        //循环检测查询的区域
        for (AreaRegionCode regionCode : regionList) {
            if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {   //区县
                if (citySet.contains(regionCode.getCityId())) {
                    errMsg = "选择的区域中存在从属关系，不可查询";
                    break;
                }
                if ((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCountyId()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
                ) {
                    errMsg = "没有权限查询该区域" + regionCode.getLastName() + "的数据";
                    break;
                }

            } else if (regionCode.getRegionLevel().equals(RegionLevel.CITY.getCode())) { //市
                if (provinceSet.contains(regionCode.getProvinceId())) {
                    errMsg = "选择的区域中存在从属关系，不可查询";
                    break;
                }

                if ((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
                ) {
                    errMsg = "没有权限查询该区域" + regionCode.getLastName() + "的数据";
                    break;
                }

            } else if (regionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode())) { //省
                if ((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
                ) {
                    errMsg = "没有权限查询该区域" + regionCode.getLastName() + "的数据";
                    break;
                }
            } else { //查部级
                if (!currentRegionCode.getRegionLevel().equals(RegionLevel.MINISTRY.getCode())) {
                    errMsg = "没有权限查询该区域" + regionCode.getLastName() + "的数据";
                    break;
                }
            }
        }

        if (StringUtils.hasText(errMsg))
            ServiceHelpUtil.throwException(errMsg);

        if (regionList.size() == 0)
            ServiceHelpUtil.throwException("未获取到合格区域代码，无法查询");

        return regionList;
    }

    //新增一个查询权限判断的方法
    private List<AreaRegionCode> checkQueryRightsNew(AggregateQueryVo vo) {
        String id = null;
        if (!StringUtils.hasText(vo.getAreaIds())) {  //默认是查全国
            id = "100000";
        } else
            id = vo.getAreaIds();
        List<AreaRegionCode> regionList = new ArrayList();
        AreaRegionCode temp = null;  //临时变量

        //存临时areaId值，用于检测多选的区域是否存在上下级关系
//        Set<String> provinceSet = new HashSet<>();  //省
//        Set<String> citySet = new HashSet<>();  //市
        temp = SysRegionUtil.getRegionCodeByLastCode(id);
        regionList.add(temp);
        List<SysRegionTreeVo> sysRegionTreeVoList = sysApi.getListByParentId(id, Constants.APPID, "");
        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            temp = SysRegionUtil.getRegionCodeByLastCode2(sysRegionTreeVo.getRegionCode());
            if (temp != null) {
                regionList.add(temp);
            }


            //取对应级别的areaId赋值到set中
//            if(temp.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()))    provinceSet.add(temp.getProvinceId());
//            if(temp.getRegionLevel().equals(RegionLevel.CITY.getCode()))    provinceSet.add(temp.getCityId());
        }
        //本级区域信息
//        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();

        //错误信息
//        String errMsg = null;
        //循环检测查询的区域
//        for (AreaRegionCode regionCode : regionList) {
//            if(regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())){   //区县
//                if(citySet.contains(regionCode.getCityId())){
//                    errMsg = "选择的区域中存在从属关系，不可查询";
//                    break;
//                }
//                if((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCountyId()))
//                      || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
//                      || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
//                ){
//                    errMsg = "没有权限查询该区域"+regionCode.getLastName()+"的数据";
//                    break;
//                }
//
//            }else if(regionCode.getRegionLevel().equals(RegionLevel.CITY.getCode())){ //市
//                if(provinceSet.contains(regionCode.getProvinceId())){
//                    errMsg = "选择的区域中存在从属关系，不可查询";
//                    break;
//                }
//
//                if((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
//                ){
//                    errMsg = "没有权限查询该区域"+regionCode.getLastName()+"的数据";
//                    break;
//                }
//
//            }else if(regionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode())){ //省
//                if((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
//                ){
//                    errMsg = "没有权限查询该区域"+regionCode.getLastName()+"的数据";
//                    break;
//                }
//            }else{ //查部级
//                if(!currentRegionCode.getRegionLevel().equals(RegionLevel.MINISTRY.getCode())){
//                    errMsg = "没有权限查询该区域"+regionCode.getLastName()+"的数据";
//                    break;
//                }
//            }
//        }

//        if(StringUtils.hasText(errMsg))
//            ServiceHelpUtil.throwException(errMsg);

        if (regionList.size() == 0)
            ServiceHelpUtil.throwException("未获取到合格区域代码，无法查询");

        return regionList;
    }

    /**
     * 获取县级基础数据对象
     *
     * @param areaId
     * @param year
     * @return
     */
    private CountyProduceUtilizeBase getCountyBaseInfo(String areaId, String year) {
        CountyProduceUtilizeBase base = new CountyProduceUtilizeBase();
        //获取 产生量与直接还田量数据
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        base.setPsdList(psdList);
        //获取 分散利用量
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);
        base.setDudList(dudList);
        //获取 市场主体利用量
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);
        base.setSudList(sudList);

        //组装到Map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        if (!dudList.isEmpty())
            dudMap = dudList.stream().collect(Collectors.toMap(d -> d.getStrawType(), d -> d));
        base.setDudMap(dudMap);
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
        if (!sudList.isEmpty())
            sudMap = sudList.stream().collect(Collectors.toMap(s -> s.getStrawType(), s -> s));
        base.setSudMap(sudMap);

        return base;
    }

    private CountyProduceUtilizeBase getCountyBaseInfoStatus(String areaId, String year, List<String> status) {
        CountyProduceUtilizeBase base = new CountyProduceUtilizeBase();
        //获取 产生量与直接还田量数据
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId2(areaId, year, status);
        base.setPsdList(psdList);
        //获取 分散利用量
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaIdStatus(areaId, year, status);
        base.setDudList(dudList);
        //获取 市场主体利用量
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaIdStatus(areaId, year, status);
        base.setSudList(sudList);

        //组装到Map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        if (!dudList.isEmpty())
            dudMap = dudList.stream().collect(Collectors.toMap(d -> d.getStrawType(), d -> d));
        base.setDudMap(dudMap);
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
        if (!sudList.isEmpty())
            sudMap = sudList.stream().collect(Collectors.toMap(s -> s.getStrawType(), s -> s));
        base.setSudMap(sudMap);

        return base;
    }

    @Override
    public PageUtils<MainUtilizeResVo> findMainUtilizeDataPage(AggregateMainUtilizeQueryVo vo) {

        List<AreaRegionCode> regionList = checkQueryRights(vo);
        //根据区域集合，查询所有市场主体利用量，并以主体为单位进行数据汇总
        PageUtils<MainUtilizeResVo> resList = getMainUtilizeByRegionListPage(regionList, vo);

        return resList;
    }

    @Override
    public List<MainUtilizeResVo> findMainUtilizeData(AggregateMainUtilizeQueryVo vo) {

        List<AreaRegionCode> regionList = checkQueryRights(vo);
        //根据区域集合，查询所有市场主体利用量，并以主体为单位进行数据汇总
        List<MainUtilizeResVo> resList = getMainUtilizeByRegionList(regionList, vo);

        return resList;
    }

    @Override
    public StrawUtilizeVo findMainUtilizeOneData(String mainId) {
        StrawUtilize su = strawUtilizeMapper.selectStrawUtilizeById(mainId);
        List<StrawUtilizeDetail> detailList = strawUtilizeDetailMapper.getStrawUtilizeDetail(mainId);

        StrawUtilizeVo resVo = new StrawUtilizeVo();    //要返回的实体
        BeanUtils.copyProperties(su, resVo);
        resVo.setStrawUtilizeId(su.getId());
        resVo.setDepartment(su.getReportArea());
        resVo.setStrawUtilizeDetailList(detailList);


        return resVo;
    }

    @Override
    public List<StrawProduceUtilizeResVo> findAreaUtilizeData(AggregateQueryVo vo) {
        Integer ct = countryTaskService.lambdaQuery().select(CountryTask::getId).eq(CountryTask::getYear, vo.getYear())
                .eq(CountryTask::getTaskLevel, RegionLevel.MINISTRY.getCode())
                .eq(CountryTask::getStatus, Constants.ExamineState.PUBLISH.toString())
                .last(" limit 1").count();


        if (ct == null || ct.intValue() == 0)
            ServiceHelpUtil.throwException(vo.getYear() + "年度数据尚未发布，不可查询");

        return findStrawProduceAndUtilzeData(vo);
    }

    /**
     * 获取产生量汇总数据2
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawProduceResVo2> getStrawProduceData2(AggregateQueryVo vo) {
        //判断用户是否有权限查询该区域
        List<AreaRegionCode> regionList = checkQueryRights(vo);
        /**
         * 现在多了四个字段
         */
        //查出所有的产生量数据，并未按秸秆类型去重
        List<StrawProduceResVo2> spList = getStrawProduceByRegionList2(regionList, vo.getYear(), vo.getCheckInfoFlag());
        //组装map
        Map<String, StrawProduceResVo2> produceMap = spList.stream().collect(Collectors.toMap(StrawProduceResVo2::getStrawType, p -> p));

        //返回的数据变量
        List<StrawProduceResVo2> resList = new ArrayList<>();
        //获取秸秆类型
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        //以秸秆类型集合的顺序，循环写入数据
        for (SysDictionary dict : dictList) {
            StrawProduceResVo2 temp = produceMap.get(dict.getDictKey());
            if (temp == null) {
                temp = new StrawProduceResVo2();
            }
            //根据秸秆类型的排序，写入名称
            temp.setStrawName(dict.getDictValue());
            temp.setStrawType(dict.getDictKey());
            resList.add(temp);
            // 移除
            produceMap.remove(dict.getDictKey());
        }
        if (produceMap.size() > 0) {
            List<SysDictionary> otherDictionaries = sysDictionaryMapper.getOtherDictionaries(Constants.DictionaryType.STRAW_TYPE, new ArrayList<>(produceMap.keySet()));
            Map<String, String> otherMap = otherDictionaries.stream().collect(Collectors.toMap(SysDictionary::getDictKey, SysDictionary::getDictValue));
            for (Map.Entry<String, StrawProduceResVo2> entry : produceMap.entrySet()) {
                entry.getValue().setStrawName(otherMap.get(entry.getKey()));
            }
            resList.addAll(produceMap.values());
        }

        //手动合计
        StrawProduceResVo2 sum = addSumProduce2(resList);
        //把合计放第一索引
        resList.add(0, sum);
        return resList;
    }

    /**
     * 获取还田离田汇总
     *
     * @param vo
     * @return
     */
    @Override
    public List<ReturnLeaveSumVo> findReturnLeaveSumData(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//判断用户是否有权限查询该区域
        //查出所有的还田离田汇总，并未按秸秆类型去重
        List<ReturnLeaveSumVo> spList = findReturnLeaveSumData(regionList, vo.getYear(), vo.getCheckInfoFlag());

        //返回的数据变量
        List<ReturnLeaveSumVo> resList = new ArrayList();
        //获取秸秆类型
        //List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

        //组装map
        Map<String, ReturnLeaveSumVo> produceMap = spList.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));

        ReturnLeaveSumVo temp = null;
        //以秸秆类型集合的顺序，循环写入数据
        for (SysDictionary dict : dictList) {
            temp = produceMap.get(dict.getDictKey());
            if (temp == null)
                temp = new ReturnLeaveSumVo();
            temp.setStrawName(dict.getDictValue());  //根据秸秆类型的排序，写入名称
            temp.setStrawType(dict.getDictKey());
            resList.add(temp);
            // 移除
            produceMap.remove(dict.getDictKey());
        }
        if (produceMap.size() > 0) {
            List<SysDictionary> otherDictionaries = sysDictionaryMapper.getOtherDictionaries(Constants.DictionaryType.STRAW_TYPE, new ArrayList<>(produceMap.keySet()));
            Map<String, String> otherMap = otherDictionaries.stream().collect(Collectors.toMap(SysDictionary::getDictKey, SysDictionary::getDictValue));
            for (Map.Entry<String, ReturnLeaveSumVo> entry : produceMap.entrySet()) {
                entry.getValue().setStrawName(otherMap.get(entry.getKey()));
            }
            resList.addAll(produceMap.values());
        }

        //手动合计
        ReturnLeaveSumVo sum = addReturnLeaveList(resList);
        //把合计放第一索引
        resList.add(0, sum);
        return resList;
    }


    /**
     * 计算合计汇总
     *
     * @param resList
     * @return
     */
    public static ReturnLeaveSumVo addReturnLeaveList(List<ReturnLeaveSumVo> resList) {
        ReturnLeaveSumVo returnLeaveSumVo = new ReturnLeaveSumVo();
        returnLeaveSumVo.setStrawType("sum");
        returnLeaveSumVo.setStrawName("合计");

        for (ReturnLeaveSumVo rs : resList) {
            if (rs.getSeedArea() == null) {
                rs.setSeedArea(new BigDecimal(0));
            }
            if (rs.getReturnArea() == null) {
                rs.setReturnArea(new BigDecimal(0));
            }
            returnLeaveSumVo = addReturnLeave(returnLeaveSumVo, rs);
        }
        if (returnLeaveSumVo.getCollectResource() != null) {
            if (returnLeaveSumVo.getCollectResource().compareTo(new BigDecimal(0)) == 0) {
                return returnLeaveSumVo;
            } else {
                    /*
                    returnLeaveSumVo.setReturnRatio(returnLeaveSumVo.getReturnArea()
                            .divide(returnLeaveSumVo.getSeedArea(), 10,RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                    */
                // 2021-3-29 更新直接还田率公式 直接还田率=直接还田量合计/可收集量合计*100
                returnLeaveSumVo.setReturnRatio(returnLeaveSumVo.getProStillField()
                        .divide(returnLeaveSumVo.getCollectResource(), 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                return returnLeaveSumVo;
            }
        }
        return returnLeaveSumVo;
    }

    /**
     * 根据区域id和年份查回填离田汇总
     *
     * @param regionList
     * @param year
     * @return
     */
    private List<ReturnLeaveSumVo> findReturnLeaveSumData(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<ReturnLeaveSum> returnLeaveSum = new ArrayList<>();
        // 当前账号
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode regionCode : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), regionCode.getLastId(), year, checkInfoFlag);
            returnLeaveSum.addAll(findReturnLeaveSumByRegion(regionCode, year, statusList));
        }
        //转换集合元素类型
        List<ReturnLeaveSumVo> returnLeaveList = returnLeaveSum.stream().map(p -> {
            ReturnLeaveSumVo temp = new ReturnLeaveSumVo();
            BeanUtils.copyProperties(p, temp);
            return temp;
        }).collect(Collectors.toList());

        Map<String, ReturnLeaveSumVo> produceMap = new HashMap();
        ReturnLeaveSumVo mapItem = null;
        //以秸秆类型为单位分组组装

        for (ReturnLeaveSumVo produce : returnLeaveList) {
            mapItem = produceMap.get(produce.getStrawType());
            if (mapItem != null) {
                mapItem = addReturnLeave(mapItem, produce); //累加
                produceMap.put(mapItem.getStrawType(), mapItem);
            } else
                produceMap.put(produce.getStrawType(), produce);
        }
        returnLeaveList = produceMap.values().stream().collect(Collectors.toList());

        return returnLeaveList;
    }

    /**
     * 累计汇总
     *
     * @param mapItem
     * @param produce
     * @return
     */
    public static ReturnLeaveSumVo addReturnLeave(ReturnLeaveSumVo mapItem, ReturnLeaveSumVo produce) {

        mapItem.setProStillField(mapItem.getProStillField().add(produce.getProStillField()));
        mapItem.setAllTotal(mapItem.getAllTotal().add(produce.getAllTotal()));

        mapItem.setDisperseTotal(mapItem.getDisperseTotal().add(produce.getDisperseTotal()));

        mapItem.setMainTotal(mapItem.getMainTotal().add(produce.getMainTotal()));

        mapItem.setCollectResource(mapItem.getCollectResource().add(produce.getCollectResource()));

        if (mapItem.getSeedArea() != null) {
            mapItem.setSeedArea(mapItem.getSeedArea().add(produce.getSeedArea()));
        }
        if (mapItem.getReturnArea() != null) {
            mapItem.setReturnArea(mapItem.getReturnArea().add(produce.getReturnArea()));
        }

        return mapItem;


    }

    /**
     * 根据区划id和年份查询还田离田汇总
     *
     * @param regionCode
     * @param year
     * @return
     */
    private List<ReturnLeaveSum> findReturnLeaveSumByRegion(AreaRegionCode regionCode, String year, List<String> status) {
        List<ReturnLeaveSum> spList = null;

        if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //查询县级数据
            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);

            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                List<StrawUtilizeSum> proList = countryTaskService.selectDucStrawUtilizeSum(regionCode.getLastId(), year);
                spList = SumUtil.getReturnLeaveSum(proList);
            } else {
                List<StrawUtilizeSum> proList = countryTaskService.selectDucStrawUtilizeSumStatus(regionCode.getLastId(), year, status);
                spList = SumUtil.getReturnLeaveSum(proList);
            }


            // spList = returnLeaveSumMapper.getReturnLeaveSumByAreaId(regionCode.getLastId(), year);
            if (spList == null) {
                return spList = new ArrayList<ReturnLeaveSum>();
            }
            return spList;

        } else {  //市级和以上
            String childrenIds = SysRegionUtil.getChildrenRegionIds(regionCode.getLastId());
            //通过所有子区划查，并且聚合数据，「只要上报了，就查」

            spList = returnLeaveSumMapper.getReturnLeaveSumByChildrenIdsAndYear(IdUtil.getIdsByStr(childrenIds), year, status);
            for (ReturnLeaveSum s : spList) {
                s.setSeedArea(s.getCollectResource());
                s.setReturnArea(s.getProStillField());
            }
            return spList;
        }


    }

    /**
     * 根据区域集合，查询所有市场主体利用量，并以主体为单位进行数据汇总
     *
     * @param regionList
     * @param vo
     * @return
     */
    private PageUtils<MainUtilizeResVo> getMainUtilizeByRegionListPage(List<AreaRegionCode> regionList, AggregateMainUtilizeQueryVo vo) {
        List<MainUtilizeResVo> muList = new ArrayList();
        //解析出所有的区域，级别到县
        /*for (AreaRegionCode region : regionList) {
            allRegionList.addAll(getMainUtilizeRegionListByRegion(region,vo.getYear()));
        }*/
        PageUtils pu = new PageUtils(muList, 0, vo.getPageSize(), 1);
        if (regionList.size() > 0) {

            //查出UtilizeId集合
            List<MainUtilizeVo> muIds = new ArrayList<>();
            for (AreaRegionCode regionCode : regionList) {
                List<MainUtilizeVo> tempList = strawUtilizeMapper.selectMainUtilizeIdByRegionCode(vo.getYear(), regionCode.getProvinceId(), regionCode.getCityId(), regionCode.getCountyId());
                muIds.addAll(tempList);
            }
            if (muIds.size() > 0) {
                List<String> utilizeIds = muIds.stream().map(m -> m.getUtilizeId()).collect(Collectors.toList());

                //分页,修改为手动分页
//                PageHelper.offsetPage(vo.getPageNo(),vo.getPageSize());

                muList = strawUtilizeDetailMapper.selectMainUtilizeInfoByUtilizeIdsPage(vo, utilizeIds);

                //转换muIds - muIdsMap
                final Map<String, MainUtilizeVo> muIdsMap = muIds.stream().collect(Collectors.toMap(m -> m.getUtilizeId(), m -> m));
                List<String> validIds = new ArrayList();
                //赋值主体信息
                for (MainUtilizeResVo m : muList) {
                    m.setAreaId(muIdsMap.get(m.getUtilizeId()).getAreaId());
                    m.setMainId(muIdsMap.get(m.getUtilizeId()).getMainId());
                    m.setMainName(muIdsMap.get(m.getUtilizeId()).getMainName());
                    m.setYear(vo.getYear());
                    validIds.add(m.getAreaId());
                }
                //查询区域名称
                final Map<String, String> regionNameMap = SysRegionUtil.getRegionNameMapByCodes(validIds);
                muList.forEach(m -> m.setAreaName(regionNameMap.get(m.getAreaId())));

                pu = new PageUtils(muList, muIds.size(), vo.getPageSize(), getCurrPage(vo.getPageNo(), vo.getPageSize()));
            }
        }

        return pu;
    }

    private int getCurrPage(int pageNo, int pageSize) {
        double tempPage = (double) pageNo / pageSize;
        double ceilPage = Math.ceil(tempPage);
        int currPage = (int) ceilPage + 1;

        if (tempPage > ceilPage) {
            currPage += 1;
        }

        return currPage;
    }

    /**
     * 根据区域集合，查询所有市场主体利用量，并以主体为单位进行数据汇总
     *
     * @param regionList
     * @param vo
     * @return
     */
    private List<MainUtilizeResVo> getMainUtilizeByRegionList(List<AreaRegionCode> regionList, final AggregateMainUtilizeQueryVo vo) {
        List<MainUtilizeResVo> muList = new ArrayList();
        List<AreaRegionCode> allRegionList = new ArrayList<>();
        //解析出所有的区域，级别到县
        for (AreaRegionCode region : regionList) {
            allRegionList.addAll(getMainUtilizeRegionListByRegion(region, vo.getYear()));
        }

        if (allRegionList.size() > 0) {
            String areaIds = allRegionList.stream().map(r -> r.getLastId()).collect(Collectors.joining(","));
            vo.setAreaIds(areaIds); //赋值成查出的所有县areaId
            //查出UtilizeId集合
            List<MainUtilizeVo> muIds = strawUtilizeMapper.selectMainUtilizeIdByAreaIds(vo);
            if (muIds.size() > 0) {
                List<String> utilizeIds = muIds.stream().map(m -> m.getUtilizeId()).collect(Collectors.toList());
                muList = strawUtilizeDetailMapper.selectMainUtilizeInfoByUtilizeIds(vo, utilizeIds);
                //转换 allRegionList 为Map
                final Map<String, AreaRegionCode> allRegionMap = allRegionList.stream().collect(Collectors.toMap(m -> m.getLastId(), m -> m));
                //转换muIds - muIdsMap
                final Map<String, MainUtilizeVo> muIdsMap = muIds.stream().collect(Collectors.toMap(m -> m.getUtilizeId(), m -> m));
                //赋值区域名称
                muList.forEach(m -> {
                            m.setAreaId(muIdsMap.get(m.getUtilizeId()).getAreaId());
                            m.setMainId(muIdsMap.get(m.getUtilizeId()).getMainId());
                            m.setMainName(muIdsMap.get(m.getUtilizeId()).getMainName());
                            m.setYear(vo.getYear());
                            m.setAreaName(allRegionMap.get(m.getAreaId()).getFullName());
                        }
                );
            }
        }
        return muList;
    }

    /**
     * 市场主体利用量，获取区域列表
     *
     * @param region
     * @return
     */
    private List<AreaRegionCode> getMainUtilizeRegionListByRegion(AreaRegionCode region, String year) {
        List<AreaRegionCode> regionList = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {
            regionList.add(region);
        } else {
            regionList.addAll(getAuditPassedRegionCodeByParent(region, year));
        }

        return regionList;
    }

    /**
     * 根据上级区域，获取已通过的区域信息，详细到县
     *
     * @param parentRegion
     * @return
     */
    private List<AreaRegionCode> getAuditPassedRegionCodeByParent(AreaRegionCode parentRegion, String year) {
        List<AreaRegionCode> resList = new ArrayList();
        List<AreaRegionCode> children = SysRegionUtil.getChildrenRegionCodeList(parentRegion.getLastId());

        if (!children.isEmpty()) {
            if (parentRegion.getRegionLevel().equals(RegionLevel.CITY.getCode())) {   //parent是市级,直接push返回
                //过滤
                resList.addAll(getAutditPasswdRegionByRegionList(children, year));
            } else {  //parent市级以上，则再查
                children = getAutditPasswdRegionByRegionList(children, year);
                if (children != null && children.size() > 0)
                    children.forEach(c -> resList.addAll(getAuditPassedRegionCodeByParent(c, year)));
            }
        }

        return resList;
    }

    /**
     * 查询数据库，过滤状态为已通过的区域
     *
     * @param regions
     * @return
     */
    private List<AreaRegionCode> getAutditPasswdRegionByRegionList(List<AreaRegionCode> regions, String year) {
        String areaIds = SysRegionUtil.getAreaIdsByRegionList(regions);
        List<String> ids = collectFlowMapper.selectAreaIdByIdsAndStatus(year, areaIds, Constants.ExamineState.PASSED.toString());
        return regions.stream().filter(r -> ids.contains(r.getLastId())).collect(Collectors.toList());
    }

    /**
     * 县级产生量，利用量数据类
     */
    @Getter
    @Setter
    private class CountyProduceUtilizeBase {
        private List<ProStillDetail> psdList;
        private List<DisperseUtilizeDetail> dudList;
        private List<StrawUtilizeDetail> sudList;
        private Map<String, DisperseUtilizeDetail> dudMap;
        private Map<String, StrawUtilizeDetail> sudMap;

        /**
         * 是否有产生量数据
         *
         * @return
         */
        public boolean hasProFill() {
            return psdList != null && psdList.size() > 0;
        }

        /**
         * 是否有分散量数据
         *
         * @return
         */
        public boolean hasUtilize() {
            return (dudList != null && dudList.size() > 0) || (sudList != null && sudList.size() > 0);
        }
    }

    /**
     * 获取收集量和产生量，级秸秆综合利用量
     *
     * @param queryVo
     * @return
     */
    @Override
    public StrawBigDataVo bigDataIndexSum(AggregateQueryVo queryVo) {
        StrawBigDataVo strawBigDataVos = new StrawBigDataVo();

        List<StrawProduceResVo2> strawProduceData2 = getStrawProduceData2(queryVo);

        List<StrawFiveVo> list = getFive(queryVo);

        strawBigDataVos.setStrawFiveVos(list);

        BigDecimal tempT = new BigDecimal(0);
        BigDecimal tempC = new BigDecimal(0);
        Iterator<StrawProduceResVo2> iterator = strawProduceData2.iterator();
        //合并晚稻和中晚稻为：早稻
        while (iterator.hasNext()) {
            StrawProduceResVo2 sp = iterator.next();
            if (sp.getStrawType().equals(CropsEnum.DOUBLE_LATE_RICE.getName()) || sp.getStrawType().equals(CropsEnum.MIDDLE_RICE.getName())) {
                tempT = tempT.add(sp.getTheoryResource());
                tempC = tempC.add(sp.getCollectResource());
                iterator.remove();
            }
        }
        ArrayList<StrawProduceVo> strawProduceVos = new ArrayList<>();
        ArrayList<StrawCollectVo> strawCollectVos = new ArrayList<>();

        StrawProduceVo strawProduceVo = null;
        StrawCollectVo strawCollectVo = null;

        for (StrawProduceResVo2 sp : strawProduceData2) {
            strawProduceVo = new StrawProduceVo();
            strawCollectVo = new StrawCollectVo();

            if (sp.getStrawType().equals(CropsEnum.EARLY_RICE)) {
                sp.setCollectResource(sp.getCollectResource().add(tempC));
                sp.setTheoryResource(sp.getTheoryResource().add(tempT));
            }
            BeanUtils.copyProperties(sp, strawProduceVo);
            BeanUtils.copyProperties(sp, strawCollectVo);
            //除万
            strawProduceVo.setTheoryResource(BigDecimalUtil.getTenThousand(strawProduceVo.getTheoryResource()));
            strawCollectVo.setCollectResource(BigDecimalUtil.getTenThousand(strawProduceVo.getTheoryResource()));

            strawProduceVos.add(strawProduceVo);
            strawCollectVos.add(strawCollectVo);

        }
        strawBigDataVos.setStrawCollectVos(strawCollectVos);
        strawBigDataVos.setStrawProduces(strawProduceVos);

        return strawBigDataVos;

    }

    @Override
    public List<StrawUsageVo> findStrawUsage(AggregateQueryVo queryVo) {
        //检查访问权限，并返回区域信集合
        List<AreaRegionCode> regionList = checkQueryRights(queryVo);
        // 当前账号
        // AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        List<String> areaIds = regionList.stream().map(AreaRegionCode::getLastId).collect(Collectors.toList());
        List<String> years = Lists.newArrayList();
        if (queryVo.getYear() != null && !"".equals(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        // 初始化数据
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        List<StrawUsageVo> result = Lists.newArrayList();
        AreaRegionCode regionCode = regionList.get(0);
        if (RegionLevel.COUNTY.getCode().equals(regionCode.getRegionLevel())) {
            // 县级汇总
            List<StrawUsageVo> countyResult = countyDataAnalysisService.findDataByAreaIdsAndYears(areaIds, years);
            if (countyResult.size() == 0) {
                // 如果是县级并且还未上报就自己查询
                countyResult = countyDataAnalysisService.getCountyDataAnalysis(years.get(0), areaIds.get(0));
            }
            if (CollectionUtils.isNotEmpty(countyResult)) {
                result = countyResult;
            }
        } else {
            // 市级汇总
            List<String> childrenIds = SysRegionUtil.getChildrenRegionIdList(regionCode.getLastId());
            List<StrawUsageVo> cityResult = countyDataAnalysisService.findDataByAreaIdsAndYears(childrenIds, years);
            if (CollectionUtils.isNotEmpty(cityResult)) {
                result = cityResult;
            }
        }
        Map<String, StrawUsageVo> usageVoMap = result.stream().collect(Collectors.toMap(StrawUsageVo::getStrawType, Function.identity(), (key1, key2) -> key2));
        // 排序
        List<StrawUsageVo> finalResult = Lists.newArrayList();
        for (SysDictionary dictionary : dictList) {
            StrawUsageVo usageVo = usageVoMap.get(dictionary.getDictKey());
            if (usageVo == null) {
                usageVo = new StrawUsageVo();
            }
            usageVo.setStrawName(dictionary.getDictValue());
            finalResult.add(usageVo);
            // 移除
            usageVoMap.remove(dictionary.getDictKey());
        }
        if (usageVoMap.size() > 0) {
            List<SysDictionary> otherDictionaries = sysDictionaryMapper.getOtherDictionaries(Constants.DictionaryType.STRAW_TYPE, new ArrayList<>(usageVoMap.keySet()));
            Map<String, String> otherMap = otherDictionaries.stream().collect(Collectors.toMap(SysDictionary::getDictKey, SysDictionary::getDictValue));
            for (Map.Entry<String, StrawUsageVo> entry : usageVoMap.entrySet()) {
                entry.getValue().setStrawName(otherMap.get(entry.getKey()));
            }
            finalResult.addAll(usageVoMap.values());
        }
        // 合计
        StrawUsageVo total = getStrawUsageTotal(finalResult);
        finalResult.add(0, total);
        return finalResult;
    }

    public static StrawUsageVo getStrawUsageTotal(List<StrawUsageVo> usageVoList) {
        StrawUsageVo total = new StrawUsageVo();
        total.setStrawName("合计");
        Set<String> returnTypes = Sets.newHashSet();
        Set<String> leavingTypes = Sets.newHashSet();
        Set<String> totalLeaveTypes = Sets.newHashSet();
        for (StrawUsageVo vo : usageVoList) {
            vo.setReturnType(vo.getReturnType() == null ? "" : ReturnTypeEnum.getTypes(Lists.newArrayList(vo.getReturnType())));
            vo.setLeavingType(vo.getLeavingType() == null ? "" : LeavingTypeEnum.getTypes(Lists.newArrayList(vo.getLeavingType())));
            total.setSeedArea(total.getSeedArea().add(vo.getSeedArea() == null ? BigDecimal.ZERO : vo.getSeedArea()));
            total.setTheoryResource(total.getTheoryResource().add(vo.getTheoryResource() == null ? BigDecimal.ZERO : vo.getTheoryResource()));
            total.setReturnResource(total.getReturnResource().add(vo.getReturnResource() == null ? BigDecimal.ZERO : vo.getReturnResource()));
            total.setLeaveNumber(total.getLeaveNumber().add(vo.getLeaveNumber() == null ? BigDecimal.ZERO : vo.getLeaveNumber()));
            total.setTransportAmount(total.getTransportAmount().add(vo.getTransportAmount() == null ? BigDecimal.ZERO : vo.getTransportAmount()));
            total.setCollectResource(total.getCollectResource().add(vo.getCollectResource() == null ? BigDecimal.ZERO : vo.getCollectResource()));
            total.setStrawUtilization(total.getStrawUtilization().add(vo.getStrawUtilization() == null ? BigDecimal.ZERO : vo.getStrawUtilization()));
            returnTypes.add(vo.getReturnType());
            leavingTypes.add(vo.getLeavingType());

            // 五料化
            total.setFertilize(total.getFertilize().add(vo.getFertilize() == null ? BigDecimal.ZERO : vo.getFertilize()));
            total.setFeed(total.getFeed().add(vo.getFeed() == null ? BigDecimal.ZERO : vo.getFeed()));
            total.setFuelled(total.getFuelled().add(vo.getFuelled() == null ? BigDecimal.ZERO : vo.getFuelled()));
            total.setBaseMat(total.getBaseMat().add(vo.getBaseMat() == null ? BigDecimal.ZERO : vo.getBaseMat()));
            total.setMaterialization(vo.getMaterialization().add(vo.getMaterialization() == null ? BigDecimal.ZERO : vo.getMaterialization()));
            // 显示离田利用方式
            Set<String> leaveTypes = Sets.newHashSet();
            if (vo.getFertilize() != null && vo.getFertilize().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("肥料化");
            }
            if (vo.getFeed() != null && vo.getFeed().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("饲料化");
            }
            if (vo.getFuelled() != null && vo.getFuelled().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("燃料化");
            }
            if (vo.getBaseMat() != null && vo.getBaseMat().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("基料化");
            }
            if (vo.getMaterialization() != null && vo.getMaterialization().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("原料化");
            }
            totalLeaveTypes.addAll(leaveTypes);
            vo.setLeaveType(org.apache.commons.lang3.StringUtils.join(leaveTypes, ","));
        }
        if (total.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
            total.setTotolRate(total.getStrawUtilization().divide(total.getCollectResource(), 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
        }
        returnTypes.remove(null);
        returnTypes.remove("");
        leavingTypes.remove(null);
        leavingTypes.remove("");
        totalLeaveTypes.remove(null);
        totalLeaveTypes.remove("");
        total.setReturnType(org.apache.commons.lang3.StringUtils.join(returnTypes, ","));
        total.setLeavingType(org.apache.commons.lang3.StringUtils.join(leavingTypes, ","));
        total.setLeaveType(org.apache.commons.lang3.StringUtils.join(totalLeaveTypes, ","));
        long noZeroCount = usageVoList.stream().filter(e -> e.getTransportAmount() != null && e.getTransportAmount().compareTo(BigDecimal.ZERO) > 0).count();
        if (noZeroCount > 0) {
            total.setTransportAmount(total.getTransportAmount().divide(BigDecimal.valueOf(noZeroCount), 10, RoundingMode.HALF_UP));
        }
        return total;
    }


    private List<StrawFiveVo> getFive(AggregateQueryVo queryVo) {
        List<StrawProduceUtilizeResVo2> strawProduceAndUtilzeData2 = findStrawProduceAndUtilzeData2(queryVo);
        StrawProduceUtilizeResVo2 sp = strawProduceAndUtilzeData2.get(0);
        ArrayList<StrawFiveVo> sf = new ArrayList<>();
        StrawFiveVo sf1 = new StrawFiveVo();
        sf1.setName("合计");
        sf1.setCount(BigDecimalUtil.getTenThousand(sp.getSum()));

        StrawFiveVo sf2 = new StrawFiveVo();
        sf2.setName("肥料化");
        sf2.setCount(BigDecimalUtil.getTenThousand(sp.getFertilising()));

        StrawFiveVo sf3 = new StrawFiveVo();
        sf3.setName("饲料化");
        sf3.setCount(BigDecimalUtil.getTenThousand(sp.getForage()));

        StrawFiveVo sf4 = new StrawFiveVo();
        sf4.setName("燃料化");
        sf4.setCount(BigDecimalUtil.getTenThousand(sp.getFuel()));

        StrawFiveVo sf5 = new StrawFiveVo();
        sf5.setName("基料化");
        sf5.setCount(BigDecimalUtil.getTenThousand(sp.getBase()));

        StrawFiveVo sf6 = new StrawFiveVo();
        sf6.setName("原料化");
        sf6.setCount(BigDecimalUtil.getTenThousand(sp.getMaterial()));
        sf.add(sf1);
        sf.add(sf2);
        sf.add(sf3);
        sf.add(sf4);
        sf.add(sf5);
        sf.add(sf6);
        return sf;

    }


    private List<String> getStatusList(String currentLevel, String areaId, String year, Boolean checkInfoFlag) {
        List<String> statusList = new ArrayList<>();
        if (checkInfoFlag != null && checkInfoFlag) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("year", year);
            queryMap.put("areaId", areaId);
            CollectFlow collectFlow = collectFlowMapper.selectDucCollectFlow(queryMap);
            if (collectFlow != null) {
                statusList = AggregateSearchStatusUtil.getCheckInfoStatus(collectFlow.getStatus());
            }
        } else {
            statusList = AggregateSearchStatusUtil.getStatusList(currentLevel, areaId);
        }
        return statusList;
    }
}
