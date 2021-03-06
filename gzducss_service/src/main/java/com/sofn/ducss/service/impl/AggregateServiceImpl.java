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
        List<AreaRegionCode> regionList = checkQueryRights(vo);//??????????????????????????????????????????

        //????????????????????????????????????????????????????????????
        List<StrawProduceResVo> spList = getStrawProduceByRegionList(regionList, vo.getYear());

        //?????????????????????
        List<StrawProduceResVo> resList = new ArrayList();
        //??????????????????
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        //??????map
        Map<String, StrawProduceResVo> produceMap = spList.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));

        StrawProduceResVo temp = null;
        //???????????????????????????????????????????????????
        for (SysDict dict : dictList) {
            temp = produceMap.get(dict.getDictcode());
            if (temp == null)
                temp = new StrawProduceResVo();
            temp.setStrawName(dict.getDictname());  //??????????????????????????????????????????
            temp.setStrawType(dict.getDictcode());
            resList.add(temp);
        }

        //????????????
        StrawProduceResVo sum = addSumProduce(resList);
        resList.add(sum);

        return resList;
    }

    /**
     * ??????????????????
     *
     * @param resList
     * @return
     */
    private StrawProduceResVo addSumProduce(List<StrawProduceResVo> resList) {
        StrawProduceResVo sum = new StrawProduceResVo();
        for (StrawProduceResVo produce : resList) {
            sum = addProduce(sum, produce);
        }

        sum.setStrawName("??????");
        sum.setStrawType("sum");
        return sum;
    }

    /**
     * ??????????????????2
     *
     * @param resList
     * @return
     */
    public static StrawProduceResVo2 addSumProduce2(List<StrawProduceResVo2> resList) {
        StrawProduceResVo2 sum = new StrawProduceResVo2();
        for (StrawProduceResVo2 produce : resList) {
            sum = addProduce2(sum, produce);
        }

        sum.setStrawName("??????");
        sum.setStrawType("sum");

        sum.setTheoryResourceRate(sum.getTheoryResourceRate().setScale(0, RoundingMode.HALF_UP));
        sum.setCollectResourceRate(sum.getCollectResourceRate().setScale(0, RoundingMode.HALF_UP));

        return sum;
    }

    /**
     * ??????regionList?????????????????????
     *
     * @param regionList ????????????
     * @param year       ??????
     * @return
     */
    private List<StrawProduceResVo> getStrawProduceByRegionList(List<AreaRegionCode> regionList, String year) {
        List<StrawProduce> produces = new ArrayList<>();
        for (AreaRegionCode regionCode : regionList) {
            produces.addAll(getStrawProduceByRegion(regionCode, year));
        }
        //????????????????????????
        List<StrawProduceResVo> produceResList = produces.stream().map(p -> {
            StrawProduceResVo temp = new StrawProduceResVo();
            BeanUtils.copyProperties(p, temp);
            return temp;
        }).collect(Collectors.toList());

        Map<String, StrawProduceResVo> produceMap = new HashMap();
        StrawProduceResVo mapItem = null;
        //????????????????????????????????????
        for (StrawProduceResVo produce : produceResList) {
            mapItem = produceMap.get(produce.getStrawType());
            if (mapItem != null) {
                mapItem = addProduce(mapItem, produce); //??????
                produceMap.put(mapItem.getStrawType(), mapItem);
            } else
                produceMap.put(produce.getStrawType(), produce);
        }

        List<StrawProduceResVo> resList = produceMap.values().stream().collect(Collectors.toList());
        return resList;
    }

    /**
     * ??????regionList?????????????????????2
     *
     * @param regionList ????????????
     * @param year       ??????
     * @return
     */
    private List<StrawProduceResVo2> getStrawProduceByRegionList2(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawProduceResVo2> produceResList = new ArrayList<>();
        // ????????????
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode regionCode : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), regionCode.getLastId(), year, checkInfoFlag);
            produceResList.addAll(getStrawProduceByRegion2(regionCode, year, statusList));
        }
        /**
         * ??????4????????????
         */
        //14???????????????????????????
        BigDecimal theoryResourceSum = BigDecimal.ZERO;
        //14?????????????????????????????????
        BigDecimal collectResourceSum = BigDecimal.ZERO;
        //????????????????????????????????????
        Map<String, StrawProduceResVo2> produceMap = new HashMap<>();
        for (StrawProduceResVo2 produce : produceResList) {
            StrawProduceResVo2 mapItem = produceMap.get(produce.getStrawType());
            if (mapItem != null) {
                //??????
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
     * ?????????????????????
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
     * ?????????????????????2
     *
     * @param mapItem
     * @param produce
     * @return
     */
    public static StrawProduceResVo2 addProduce2(StrawProduceResVo2 mapItem, StrawProduceResVo2 produce) {
        mapItem.setTheoryResource(mapItem.getTheoryResource().add(produce.getTheoryResource()));
        mapItem.setCollectResource(mapItem.getCollectResource().add(produce.getCollectResource()));
        //????????????sum
        mapItem.setGrainYield(mapItem.getGrainYield().add(produce.getGrainYield()));
        //????????????sum
        mapItem.setSeedArea(mapItem.getSeedArea().add(produce.getSeedArea()));

        mapItem.setCollectResourceRate(mapItem.getCollectResourceRate().add(produce.getCollectResourceRate()));
        mapItem.setTheoryResourceRate(mapItem.getTheoryResourceRate().add(produce.getTheoryResourceRate()));


        return mapItem;
    }


    /**
     * ?????????????????????????????????
     *
     * @param regionCode ????????????
     * @param year       ??????
     * @return
     */
    private List<StrawProduce> getStrawProduceByRegion(AreaRegionCode regionCode, String year) {
        //??????ID????????????????????????
        List<StrawProduce> spList = new ArrayList();

        if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //??????????????????
            List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(regionCode.getLastId(), year);

            spList = new ArrayList();
            for (ProStillDetail deatil : psdList) { //?????????????????????bean??????
                deatil.calculateResource();
                StrawProduce strawProduce = new StrawProduce();
                BeanUtils.copyProperties(deatil, strawProduce);
                spList.add(strawProduce);
            }
        } else {  //???????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(regionCode.getLastId());
            //??????????????????????????????????????????????????????????????????????????????
            spList = strawProduceMapper.sumStrawProduce(IdUtil.getIdsByStr(childrenIds), year, null);
        }
        return spList;
    }

    /**
     * ?????????????????????????????????2
     *
     * @param regionCode ????????????
     * @param year       ??????
     * @return
     */
    private List<StrawProduceResVo2> getStrawProduceByRegion2(AreaRegionCode regionCode, String year, List<String> status) {
        //??????ID????????????????????????
        List<StrawProduceResVo2> spList = new ArrayList<>();
        /**
         * ??????4
         */
        if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {
            //??????????????????
            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);
            List<ProStillDetail> psdList;
            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                psdList = proStillDetailMapper.getProStillDetailListByAreaId(regionCode.getLastId(), year);
            } else {
                psdList = proStillDetailMapper.getProStillDetailListByAreaId2(regionCode.getLastId(), year, status);
            }
            for (ProStillDetail deatil : psdList) { //?????????????????????bean??????
                deatil.calculateResource();
                StrawProduceResVo2 strawProduce = new StrawProduceResVo2();
                BeanUtils.copyProperties(deatil, strawProduce);
                spList.add(strawProduce);
            }
        } else {
            //???????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(regionCode.getLastId());
            //????????????????????????????????????????????????????????????????????????
            //Constants.ExamineState.PASSED
            spList = strawProduceMapper.sumStrawProduceTwo(IdUtil.getIdsByStr(childrenIds), year, status);
        }
        return spList;
    }


    @Override
    public List<StrawUtilizeResVo> getStrawUtilzeData(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//??????????????????????????????????????????

        //????????????????????????
        List<StrawUtilizeResVo> utilizes = getStrawUtilizeDataByRegionList(regionList, vo.getYear());
        //?????????Map
        Map<String, StrawUtilizeResVo> utilizeMap = utilizes.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));
        //????????????
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        List<StrawUtilizeResVo> resList = new ArrayList();
        StrawUtilizeResVo temp = null;
        //????????????????????????
        for (SysDict strawType : dictList) {
            temp = utilizeMap.get(strawType.getDictcode());
            if (temp == null)
                temp = new StrawUtilizeResVo(); //???????????????????????????

            temp.setStrawType(strawType.getDictcode());
            temp.setStrawName(strawType.getDictname());

            resList.add(temp);
        }

        //????????????
        StrawUtilizeResVo sum = addSumUtilize(resList);
        resList.add(sum);

        return resList;
    }

    /**
     * ???????????????????????????2
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawUtilizeResVo3> getStrawUtilzeData2(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//??????????????????????????????????????????

        //????????????????????????
        List<StrawUtilizeResVo3> utilizes = getStrawUtilizeDataByRegionList2(regionList, vo.getYear(), vo.getCheckInfoFlag());
        //?????????Map
        Map<String, StrawUtilizeResVo3> utilizeMap = utilizes.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));
        //????????????
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        List<StrawUtilizeResVo3> resList = new ArrayList();
        StrawUtilizeResVo3 temp = null;
        //????????????????????????
        for (SysDictionary strawType : dictList) {
            temp = utilizeMap.get(strawType.getDictKey());
            if (temp == null)
                temp = new StrawUtilizeResVo3(); //???????????????????????????

            temp.setStrawType(strawType.getDictKey());
            temp.setStrawName(strawType.getDictValue());

            resList.add(temp);
            // ??????
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

        //????????????
        StrawUtilizeResVo3 sum = addSumUtilize2(resList);
        resList.add(0, sum);


        return resList;
    }


    /**
     * ????????????
     *
     * @param resList
     * @return
     */
    private StrawUtilizeResVo addSumUtilize(List<StrawUtilizeResVo> resList) {
        StrawUtilizeResVo sum = new StrawUtilizeResVo();

        for (StrawUtilizeResVo utilize : resList) {
            sum = addStrawUtilize(sum, utilize);
        }
        //???????????????
        //???????????????????????????????????????????????????????????????????????????
//        sum.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(sum.getProStrawUtilize(),sum.getMainTotalOther(),sum.getYieldAllExport(),sum.getCollectResource()));
        sum.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(sum.getProStrawUtilize(), null, null, sum.getCollectResource()));
        sum.setStrawName("??????");
        sum.setStrawType("sum");
        return sum;
    }

    /**
     * ????????????2
     *
     * @param resList
     * @return
     */
    public static StrawUtilizeResVo3 addSumUtilize2(List<StrawUtilizeResVo3> resList) {
        StrawUtilizeResVo3 sum = new StrawUtilizeResVo3();

        for (StrawUtilizeResVo3 utilize : resList) {
            sum = addStrawUtilizeNoReturnSource(sum, utilize);
        }
        //?????????????????????
        sum.setComprehensiveRate(StrawCalculatorUtil2.calculationComprehensiveRote(sum.getStrawUsage(), sum.getCollectResource()));
        ///??????????????????????????????
        sum.setComprehensiveIndex(StrawCalculatorUtil2.calculationComprehensiveindex(sum.getAllTotal(), null, null, sum.getCollectResource()));
        //???????????????????????????
        sum.setIndustrializationIndex(StrawCalculatorUtil2.calculationinIndustrializationIndex(sum.getMainTotal(), sum.getCollectResource()));


        sum.setStrawName("??????");
        sum.setStrawType("sum");
        return sum;
    }


    /**
     * ?????????????????????????????????????????????????????????????????????
     *
     * @param regionList ????????????
     * @param year       ??????
     * @return
     */
    private List<StrawUtilizeResVo> getStrawUtilizeDataByRegionList(List<AreaRegionCode> regionList, String year) {
        List<StrawUtilizeResVo> utilizes = new ArrayList();
        for (AreaRegionCode regionCode : regionList) {
            //??????????????????????????????????????????????????????????????????
            utilizes.addAll(getStrawUtilizeDataByRegion(regionCode, year));
        }

        //??????????????????????????????
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
        //???????????????
        resList.forEach(sum -> {
            sum.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(sum.getProStrawUtilize(), sum.getMainTotalOther(), sum.getYieldAllExport(), sum.getCollectResource()));
            //???????????????????????????????????????????????????????????????
            sum.setProStrawUtilize(StrawCalculatorUtil.calculationComprehensiveUtilize(sum.getProStrawUtilize(), sum.getMainTotalOther(), sum.getYieldAllExport()));
        });

        return resList;
    }


    /**
     * ?????????????????????????????????????????????????????????????????????2
     *
     * @param regionList ????????????
     * @param year       ??????
     * @return
     */
    private List<StrawUtilizeResVo3> getStrawUtilizeDataByRegionList2(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();
        // ????????????
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode regionCode : regionList) {
            //?????????????????????????????????????????????????????????????????????
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), regionCode.getLastId(), year, checkInfoFlag);
            utilizes.addAll(getStrawUtilizeDataByRegion2(regionCode, year, statusList));
        }

        //??????????????????????????????
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
            //?????????????????????
            sum.setComprehensiveRate(StrawCalculatorUtil2.calculationComprehensiveRote(sum.getStrawUsage(), sum.getCollectResource()));
            ///??????????????????????????????
            sum.setComprehensiveIndex(StrawCalculatorUtil2.calculationComprehensiveindex(sum.getAllTotal(), null, null, sum.getCollectResource()));
            //???????????????????????????
            sum.setIndustrializationIndex(StrawCalculatorUtil2.calculationinIndustrializationIndex(sum.getMainTotal(), sum.getCollectResource()));
        });

        return resList;
    }


    /**
     * ?????????????????????????????????
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
     * ?????????????????????????????????2
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
     * ?????????????????????????????????
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
     * ???????????????????????????????????? ????????????????????????
     *
     * @param region ????????????
     * @param year   ??????
     * @return
     */
    private List<StrawUtilizeResVo> getStrawUtilizeDataByRegion(AreaRegionCode region, String year) {
        List<StrawUtilizeResVo> utilizes = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //????????????
            utilizes = getCountyStrawUtilize(region.getLastId(), year);
        } else {//???????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            List<StrawUtilizeSum> susList = strawUtilizeSumMapper.selectStrawUtilizeByAreaIds(IdUtil.getIdsByStr(childrenIds), year);
            utilizes = assemblyStrawUtilizeBySusList(susList);
        }

        return utilizes;
    }

    /**
     * ???????????????????????????????????? ????????????????????????2
     *
     * @param region ????????????
     * @param year   ??????
     * @return
     */
    private List<StrawUtilizeResVo3> getStrawUtilizeDataByRegion2(AreaRegionCode region, String year, List<String> status) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //????????????

            SysOrganization sysOrganization = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysOrganization.class);

            if (sysOrganization.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
                utilizes = getCountyStrawUtilize2(region.getLastId(), year);
            } else {
                utilizes = getCountyStrawUtilizeStatus(region.getLastId(), year, status);
            }


        } else {//???????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            //List<StrawUtilizeSum>  susList = strawUtilizeSumMapper.selectStrawUtilizeByAreaIds(childrenIds, year);
            //????????????????????????????????????
            List<StrawUsageSum> susList = strawUsageSumMapper.selectStrawUtilizeByAreaIds(IdUtil.getIdsByStr(childrenIds), year, status);

            utilizes = assemblyStrawUtilizeBySusList2(susList);
        }

        return utilizes;
    }


    /**
     * ??????????????????
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
     * ??????????????????2
     *
     * @param susList
     * @return
     */
    //todo copy ?????? 3?????????
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
     * ???????????????????????????????????????
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawProduceUtilizeResVo> findStrawProduceAndUtilzeData(AggregateQueryVo vo) {
        //?????????????????????????????????????????????
        List<AreaRegionCode> regionList = checkQueryRightsNew(vo);
        //???????????????????????????????????????
        List<StrawProduceUtilizeResVo> resList = getProduceUtilizeByRegionList2(regionList, vo.getYear(), vo.getCheckInfoFlag());
        if (resList.size() > 0) {
            //??????????????????
            List<String> areaIds = resList.stream().map(p -> p.getAreaId()).collect(Collectors.toList());
            final Map<String, String> nameMap = SysRegionUtil.getRegionNameMapByCodes(areaIds);
            resList.forEach(p -> {
                p.setAreaName(nameMap.get(p.getAreaId()));
                //???????????????????????????????????????"??????"
                if (p.getAreaId().equals(Constants.ZHONGGUO_AREA_ID))
                    p.setAreaName(Constants.ZHONGGUO_AREA_NAME);
            });
        }

        return resList;
    }

    /**
     * ???????????????????????????????????????2
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawProduceUtilizeResVo2> findStrawProduceAndUtilzeData2(AggregateQueryVo vo) {
        //?????????????????????????????????????????????
        List<AreaRegionCode> regionList = checkQueryRights(vo);
        //???????????????????????????????????????
        List<StrawProduceUtilizeResVo> resList = getProduceUtilizeByRegionList(regionList, vo.getYear(), vo.getCheckInfoFlag());
        if (resList.size() > 0) {
            //??????????????????
            List<String> areaIds = resList.stream().map(p -> p.getAreaId()).collect(Collectors.toList());
            final Map<String, String> nameMap = SysRegionUtil.getRegionNameMapByCodes(areaIds);
            resList.forEach(p -> {
                p.setAreaName(nameMap.get(p.getAreaId()));
                //???????????????????????????????????????"??????"
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
     * ?????????????????????????????????????????????????????????????????????????????????
     *
     * @param regionList
     * @param year
     * @return
     */
    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegionList(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<StrawProduceUtilizeResVo> resList = new ArrayList<>();
        List<StrawProduceUtilizeResVo> temp = null;
        // ????????????
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
        // ????????????
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode region : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), region.getLastId(), year, checkInfoFlag);
            temp = getProduceUtilizeByRegion2(region, year, statusList);
            resList.addAll(temp);
        }

        return resList;
    }

    /**
     * ????????????????????????????????????????????????????????????
     *
     * @param region
     * @param year
     * @return
     */
    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegion(AreaRegionCode region, String year, List<String> statusList) {
        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) { //??????
            //???????????????????????????
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
            // 2021-04-28 ???????????????????????????????????????????????????
            //????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            if (!StringUtils.hasText(childrenIds))   //????????????
                return puList;
            //????????????????????????
            List<String> areaIds = IdUtil.getIdsByStr(childrenIds);
            List<ProductionUsageSum> productionUsageSumList = productionUsageSumMapper.selectProductionUsageSum(areaIds, year, statusList);
            //List<StrawUtilizeSumTotal> stList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(childrenIds, year, status);
            // ???????????????
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
            //????????????(???????????????)
            StrawProduceUtilizeResVo sum = addSumProduceUtilize(puList);
            calculationProduceUtilize(sum);
            sum.setAreaId(region.getLastId());
            puList.add(0, sum);
            //??????
            //puList.forEach(p -> calculationProduceUtilize(p));
        }

        //????????????????????? = ??????????????? - ????????? + ?????????
        //puList.forEach(p-> p.setProStrawUtilize( StrawCalculatorUtil.calculationComprehensiveUtilize(p.getProStrawUtilize(),p.getMainTotalOther(),p.getYieldAllExport())));

        return puList;
    }

    private List<StrawProduceUtilizeResVo> getProduceUtilizeByRegion2(AreaRegionCode region, String year, List<String> statusList) {
        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        List<StrawProduceUtilizeResVo> puList2 = new ArrayList();
        if (region.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) { //??????
            //???????????????????????????
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
            //????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(region.getLastId());
            if (!StringUtils.hasText(childrenIds))   //????????????
                return puList;
            //????????????????????????
            List<ProductionUsageSum> productionUsageSumList = productionUsageSumMapper.selectProductionUsageSum(IdUtil.getIdsByStr(childrenIds), year, statusList);
            //List<StrawUtilizeSumTotal> stList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(childrenIds, year, status);

            puList2 = getStrawProduceUtilizeResVo(productionUsageSumList);

            // puList = assemblyProduceUtilizeBySTList(stList);
            //????????????(???????????????)
            StrawProduceUtilizeResVo sum = addSumProduceUtilize(puList2);
            calculationProduceUtilize(sum);
            sum.setAreaId(region.getLastId());
            puList.add(sum);
            //??????
            //puList.forEach(p -> calculationProduceUtilize(p));
        }

        //????????????????????? = ??????????????? - ????????? + ?????????
        //puList.forEach(p-> p.setProStrawUtilize( StrawCalculatorUtil.calculationComprehensiveUtilize(p.getProStrawUtilize(),p.getMainTotalOther(),p.getYieldAllExport())));

        return puList;
    }

    /**
     * ????????????
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
     * ?????????????????????????????????????????????????????????????????????????????????
     *
     * @param pu
     */
    private void calculationProduceUtilize(StrawProduceUtilizeResVo pu) {
//        //???????????????
//        pu.setComprehensive(StrawCalculatorUtil.calculationComprehensiveUtilizeRatio(pu.getProStrawUtilize(),pu.getMainTotalOther(),pu.getYieldAllExport(),pu.getCollectResource()));
//        //????????????????????????
//        pu.setComprehensiveIndex(StrawCalculatorUtil.calculationComprehensiveIndex(pu.getProStrawUtilize(),pu.getYieldAllExport(),pu.getCollectResource()));
//        //???????????????????????????
//        pu.setIndustrializationIndex(StrawCalculatorUtil.calculationinIndustrializationIndex(pu.getMainTotal(),pu.getCollectResource()));

        //?????????????????????
        pu.setComprehensive(StrawCalculatorUtil2.calculationComprehensiveRote(pu.getProStrawUtilize(), pu.getCollectResource()));
        ///??????????????????????????????
        pu.setComprehensiveIndex(StrawCalculatorUtil2.calculationComprehensiveindex(pu.getSum(), null, null, pu.getCollectResource()));
        //???????????????????????????
        pu.setIndustrializationIndex(StrawCalculatorUtil2.calculationinIndustrializationIndex(pu.getMainTotal(), pu.getCollectResource()));


    }

    /**
     * ?????????????????????????????????
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
     * ????????????bean????????????resVo
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
     * ?????????????????????????????????????????????
     *
     * @param areaId
     * @param year
     * @return
     */
    private StrawProduceUtilizeResVo getCountyProduceUtilize(String areaId, String year) {
        //????????????????????????
        CountyProduceUtilizeBase countyBaseInfo = getCountyBaseInfo(areaId, year);

        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        if (countyBaseInfo.hasProFill() && countyBaseInfo.hasUtilize()) {
            Map<String, DisperseUtilizeDetail> dudMap = countyBaseInfo.getDudMap();
            Map<String, StrawUtilizeDetail> sudMap = countyBaseInfo.getSudMap();
            StrawProduceUtilizeResVo temp = null;
            for (ProStillDetail psd : countyBaseInfo.getPsdList()) {
                //???????????????????????????????????????
                temp = StrawCalculatorUtil2.assemblyProduceUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
                puList.add(temp);
            }
        }

        StrawProduceUtilizeResVo pu = new StrawProduceUtilizeResVo();
        pu.setAreaId(areaId);

        for (StrawProduceUtilizeResVo temp : puList) {
            pu = addProduceUtilize(pu, temp);
        }

        //??????
        calculationProduceUtilize(pu);

        return pu;
    }

    private StrawProduceUtilizeResVo getCountyProduceUtilizeStatus(String areaId, String year, List<String> status) {
        //????????????????????????
        CountyProduceUtilizeBase countyBaseInfo = getCountyBaseInfoStatus(areaId, year, status);

        List<StrawProduceUtilizeResVo> puList = new ArrayList();
        if (countyBaseInfo.hasProFill() && countyBaseInfo.hasUtilize()) {
            Map<String, DisperseUtilizeDetail> dudMap = countyBaseInfo.getDudMap();
            Map<String, StrawUtilizeDetail> sudMap = countyBaseInfo.getSudMap();
            StrawProduceUtilizeResVo temp = null;
            for (ProStillDetail psd : countyBaseInfo.getPsdList()) {
                //???????????????????????????????????????
                temp = StrawCalculatorUtil2.assemblyProduceUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
                puList.add(temp);
            }
        }

        StrawProduceUtilizeResVo pu = new StrawProduceUtilizeResVo();
        pu.setAreaId(areaId);

        for (StrawProduceUtilizeResVo temp : puList) {
            pu = addProduceUtilize(pu, temp);
        }

        //??????
        calculationProduceUtilize(pu);

        return pu;
    }

    /**
     * ???????????????????????????????????????
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
     * ??????????????????????????????
     *
     * @param areaId
     * @param year
     * @return
     */
    private List<StrawUtilizeResVo> getCountyStrawUtilize(String areaId, String year) {
        List<StrawUtilizeResVo> utilizes = new ArrayList();   //??????????????????

        //?????? ?????????????????????????????????
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        if (psdList.isEmpty())  //??????????????????????????????
            return utilizes;

        //?????? ???????????????
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);
        //?????? ?????????????????????
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);
        if (dudList.isEmpty() && sudList.isEmpty())  //??????????????????????????????
            return utilizes;

        //?????????Map
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
            //???????????????????????????
            temp = StrawCalculatorUtil.assemblyUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
            utilizes.add(temp);
        }

        return utilizes;
    }


    /**
     * ??????????????????????????????2
     *
     * @param areaId
     * @param year
     * @return
     */
    private List<StrawUtilizeResVo3> getCountyStrawUtilize2(String areaId, String year) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();   //??????????????????

        //?????? ?????????????????????????????????
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        if (psdList.isEmpty())  //??????????????????????????????
            return utilizes;

        //?????? ???????????????(??????????????????????????????)
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);
        //?????? ?????????????????????
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);
        if (dudList.isEmpty() && sudList.isEmpty())  //??????????????????????????????
            return utilizes;

        //?????????????????????????????????????????????map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        for (DisperseUtilizeDetail detail : dudList) {
            dudMap.put(detail.getStrawType(), detail);
        }
        //???????????????????????????????????????map
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
        for (StrawUtilizeDetail detail : sudList) {
            sudMap.put(detail.getStrawType(), detail);
        }

        StrawUtilizeResVo3 temp = null;
        for (ProStillDetail psd : psdList) {
            //???????????????????????????
            temp = StrawCalculatorUtil2.assemblyUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
            utilizes.add(temp);
        }

        return utilizes;
    }


    /**
     * ??????????????????????????????2
     *
     * @param areaId
     * @param year
     * @return
     */
    private List<StrawUtilizeResVo3> getCountyStrawUtilizeStatus(String areaId, String year, List<String> status) {
        List<StrawUtilizeResVo3> utilizes = new ArrayList();   //??????????????????

        //?????? ?????????????????????????????????
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId2(areaId, year, status);
        if (psdList.isEmpty())  //??????????????????????????????
            return utilizes;

        //?????? ???????????????(??????????????????????????????)
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaIdStatus(areaId, year, status);
        //?????? ?????????????????????
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaIdStatus(areaId, year, status);
        if (dudList.isEmpty() && sudList.isEmpty())  //??????????????????????????????
            return utilizes;

        //?????????????????????????????????????????????map
        Map<String, DisperseUtilizeDetail> dudMap = new HashMap();
        for (DisperseUtilizeDetail detail : dudList) {
            dudMap.put(detail.getStrawType(), detail);
        }
        //???????????????????????????????????????map
        Map<String, StrawUtilizeDetail> sudMap = new HashMap();
        for (StrawUtilizeDetail detail : sudList) {
            sudMap.put(detail.getStrawType(), detail);
        }

        StrawUtilizeResVo3 temp = null;
        for (ProStillDetail psd : psdList) {
            //???????????????????????????
            temp = StrawCalculatorUtil2.assemblyUtilize(psd, dudMap.get(psd.getStrawType()), sudMap.get(psd.getStrawType()));
            utilizes.add(temp);
        }

        return utilizes;
    }

    /**
     * ?????????????????????????????????
     * ?????????????????????????????????
     *
     * @param vo ??????????????????
     * @return List<AreaRegionCode> ????????????????????????
     * @throws com.sofn.ducss.exception.SofnException ?????????????????????????????????
     */
    private List<AreaRegionCode> checkQueryRights(AggregateQueryVo vo) {
        String[] ids = null;
        if (!StringUtils.hasText(vo.getAreaIds())) {  //??????????????????
            ids = new String[]{"100000"};
        } else
            ids = vo.getAreaIds().split(",");
        List<AreaRegionCode> regionList = new ArrayList();
        AreaRegionCode temp = null;  //????????????

        //?????????areaId????????????????????????????????????????????????????????????
        Set<String> provinceSet = new HashSet<>();  //???
        Set<String> citySet = new HashSet<>();  //???
        for (String areaId : ids) {
            temp = SysRegionUtil.getRegionCodeByLastCode(areaId);
            regionList.add(temp);

            //??????????????????areaId?????????set???
            if (temp.getRegionLevel().equals(RegionLevel.PROVINCE.getCode())) provinceSet.add(temp.getProvinceId());
            if (temp.getRegionLevel().equals(RegionLevel.CITY.getCode())) provinceSet.add(temp.getCityId());
        }
        //??????????????????
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();

        //????????????
        String errMsg = null;
        //???????????????????????????
        for (AreaRegionCode regionCode : regionList) {
            if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {   //??????
                if (citySet.contains(regionCode.getCityId())) {
                    errMsg = "???????????????????????????????????????????????????";
                    break;
                }
                if ((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCountyId()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
                ) {
                    errMsg = "???????????????????????????" + regionCode.getLastName() + "?????????";
                    break;
                }

            } else if (regionCode.getRegionLevel().equals(RegionLevel.CITY.getCode())) { //???
                if (provinceSet.contains(regionCode.getProvinceId())) {
                    errMsg = "???????????????????????????????????????????????????";
                    break;
                }

                if ((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
                ) {
                    errMsg = "???????????????????????????" + regionCode.getLastName() + "?????????";
                    break;
                }

            } else if (regionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode())) { //???
                if ((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()))
                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
                ) {
                    errMsg = "???????????????????????????" + regionCode.getLastName() + "?????????";
                    break;
                }
            } else { //?????????
                if (!currentRegionCode.getRegionLevel().equals(RegionLevel.MINISTRY.getCode())) {
                    errMsg = "???????????????????????????" + regionCode.getLastName() + "?????????";
                    break;
                }
            }
        }

        if (StringUtils.hasText(errMsg))
            ServiceHelpUtil.throwException(errMsg);

        if (regionList.size() == 0)
            ServiceHelpUtil.throwException("?????????????????????????????????????????????");

        return regionList;
    }

    //???????????????????????????????????????
    private List<AreaRegionCode> checkQueryRightsNew(AggregateQueryVo vo) {
        String id = null;
        if (!StringUtils.hasText(vo.getAreaIds())) {  //??????????????????
            id = "100000";
        } else
            id = vo.getAreaIds();
        List<AreaRegionCode> regionList = new ArrayList();
        AreaRegionCode temp = null;  //????????????

        //?????????areaId????????????????????????????????????????????????????????????
//        Set<String> provinceSet = new HashSet<>();  //???
//        Set<String> citySet = new HashSet<>();  //???
        temp = SysRegionUtil.getRegionCodeByLastCode(id);
        regionList.add(temp);
        List<SysRegionTreeVo> sysRegionTreeVoList = sysApi.getListByParentId(id, Constants.APPID, "");
        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            temp = SysRegionUtil.getRegionCodeByLastCode2(sysRegionTreeVo.getRegionCode());
            if (temp != null) {
                regionList.add(temp);
            }


            //??????????????????areaId?????????set???
//            if(temp.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()))    provinceSet.add(temp.getProvinceId());
//            if(temp.getRegionLevel().equals(RegionLevel.CITY.getCode()))    provinceSet.add(temp.getCityId());
        }
        //??????????????????
//        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();

        //????????????
//        String errMsg = null;
        //???????????????????????????
//        for (AreaRegionCode regionCode : regionList) {
//            if(regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())){   //??????
//                if(citySet.contains(regionCode.getCityId())){
//                    errMsg = "???????????????????????????????????????????????????";
//                    break;
//                }
//                if((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCountyId()))
//                      || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
//                      || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
//                ){
//                    errMsg = "???????????????????????????"+regionCode.getLastName()+"?????????";
//                    break;
//                }
//
//            }else if(regionCode.getRegionLevel().equals(RegionLevel.CITY.getCode())){ //???
//                if(provinceSet.contains(regionCode.getProvinceId())){
//                    errMsg = "???????????????????????????????????????????????????";
//                    break;
//                }
//
//                if((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getCityId()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
//                ){
//                    errMsg = "???????????????????????????"+regionCode.getLastName()+"?????????";
//                    break;
//                }
//
//            }else if(regionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode())){ //???
//                if((currentRegionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.CITY.getCode()))
//                        || (currentRegionCode.getRegionLevel().equals(RegionLevel.PROVINCE.getCode()) && !currentRegionCode.getLastId().equals(regionCode.getProvinceId()))
//                ){
//                    errMsg = "???????????????????????????"+regionCode.getLastName()+"?????????";
//                    break;
//                }
//            }else{ //?????????
//                if(!currentRegionCode.getRegionLevel().equals(RegionLevel.MINISTRY.getCode())){
//                    errMsg = "???????????????????????????"+regionCode.getLastName()+"?????????";
//                    break;
//                }
//            }
//        }

//        if(StringUtils.hasText(errMsg))
//            ServiceHelpUtil.throwException(errMsg);

        if (regionList.size() == 0)
            ServiceHelpUtil.throwException("?????????????????????????????????????????????");

        return regionList;
    }

    /**
     * ??????????????????????????????
     *
     * @param areaId
     * @param year
     * @return
     */
    private CountyProduceUtilizeBase getCountyBaseInfo(String areaId, String year) {
        CountyProduceUtilizeBase base = new CountyProduceUtilizeBase();
        //?????? ?????????????????????????????????
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        base.setPsdList(psdList);
        //?????? ???????????????
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);
        base.setDudList(dudList);
        //?????? ?????????????????????
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);
        base.setSudList(sudList);

        //?????????Map
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
        //?????? ?????????????????????????????????
        List<ProStillDetail> psdList = proStillDetailMapper.getProStillDetailListByAreaId2(areaId, year, status);
        base.setPsdList(psdList);
        //?????? ???????????????
        List<DisperseUtilizeDetail> dudList = disperseUtilizeDetailMapper.selectDetailByAreaIdStatus(areaId, year, status);
        base.setDudList(dudList);
        //?????? ?????????????????????
        List<StrawUtilizeDetail> sudList = strawUtilizeDetailMapper.selectDetailSumByAreaIdStatus(areaId, year, status);
        base.setSudList(sudList);

        //?????????Map
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
        //????????????????????????????????????????????????????????????????????????????????????????????????
        PageUtils<MainUtilizeResVo> resList = getMainUtilizeByRegionListPage(regionList, vo);

        return resList;
    }

    @Override
    public List<MainUtilizeResVo> findMainUtilizeData(AggregateMainUtilizeQueryVo vo) {

        List<AreaRegionCode> regionList = checkQueryRights(vo);
        //????????????????????????????????????????????????????????????????????????????????????????????????
        List<MainUtilizeResVo> resList = getMainUtilizeByRegionList(regionList, vo);

        return resList;
    }

    @Override
    public StrawUtilizeVo findMainUtilizeOneData(String mainId) {
        StrawUtilize su = strawUtilizeMapper.selectStrawUtilizeById(mainId);
        List<StrawUtilizeDetail> detailList = strawUtilizeDetailMapper.getStrawUtilizeDetail(mainId);

        StrawUtilizeVo resVo = new StrawUtilizeVo();    //??????????????????
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
            ServiceHelpUtil.throwException(vo.getYear() + "???????????????????????????????????????");

        return findStrawProduceAndUtilzeData(vo);
    }

    /**
     * ???????????????????????????2
     *
     * @param vo
     * @return
     */
    @Override
    public List<StrawProduceResVo2> getStrawProduceData2(AggregateQueryVo vo) {
        //??????????????????????????????????????????
        List<AreaRegionCode> regionList = checkQueryRights(vo);
        /**
         * ????????????????????????
         */
        //????????????????????????????????????????????????????????????
        List<StrawProduceResVo2> spList = getStrawProduceByRegionList2(regionList, vo.getYear(), vo.getCheckInfoFlag());
        //??????map
        Map<String, StrawProduceResVo2> produceMap = spList.stream().collect(Collectors.toMap(StrawProduceResVo2::getStrawType, p -> p));

        //?????????????????????
        List<StrawProduceResVo2> resList = new ArrayList<>();
        //??????????????????
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        //???????????????????????????????????????????????????
        for (SysDictionary dict : dictList) {
            StrawProduceResVo2 temp = produceMap.get(dict.getDictKey());
            if (temp == null) {
                temp = new StrawProduceResVo2();
            }
            //??????????????????????????????????????????
            temp.setStrawName(dict.getDictValue());
            temp.setStrawType(dict.getDictKey());
            resList.add(temp);
            // ??????
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

        //????????????
        StrawProduceResVo2 sum = addSumProduce2(resList);
        //????????????????????????
        resList.add(0, sum);
        return resList;
    }

    /**
     * ????????????????????????
     *
     * @param vo
     * @return
     */
    @Override
    public List<ReturnLeaveSumVo> findReturnLeaveSumData(AggregateQueryVo vo) {
        List<AreaRegionCode> regionList = checkQueryRights(vo);//??????????????????????????????????????????
        //???????????????????????????????????????????????????????????????
        List<ReturnLeaveSumVo> spList = findReturnLeaveSumData(regionList, vo.getYear(), vo.getCheckInfoFlag());

        //?????????????????????
        List<ReturnLeaveSumVo> resList = new ArrayList();
        //??????????????????
        //List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

        //??????map
        Map<String, ReturnLeaveSumVo> produceMap = spList.stream().collect(Collectors.toMap(p -> p.getStrawType(), p -> p));

        ReturnLeaveSumVo temp = null;
        //???????????????????????????????????????????????????
        for (SysDictionary dict : dictList) {
            temp = produceMap.get(dict.getDictKey());
            if (temp == null)
                temp = new ReturnLeaveSumVo();
            temp.setStrawName(dict.getDictValue());  //??????????????????????????????????????????
            temp.setStrawType(dict.getDictKey());
            resList.add(temp);
            // ??????
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

        //????????????
        ReturnLeaveSumVo sum = addReturnLeaveList(resList);
        //????????????????????????
        resList.add(0, sum);
        return resList;
    }


    /**
     * ??????????????????
     *
     * @param resList
     * @return
     */
    public static ReturnLeaveSumVo addReturnLeaveList(List<ReturnLeaveSumVo> resList) {
        ReturnLeaveSumVo returnLeaveSumVo = new ReturnLeaveSumVo();
        returnLeaveSumVo.setStrawType("sum");
        returnLeaveSumVo.setStrawName("??????");

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
                // 2021-3-29 ??????????????????????????? ???????????????=?????????????????????/??????????????????*100
                returnLeaveSumVo.setReturnRatio(returnLeaveSumVo.getProStillField()
                        .divide(returnLeaveSumVo.getCollectResource(), 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                return returnLeaveSumVo;
            }
        }
        return returnLeaveSumVo;
    }

    /**
     * ????????????id??????????????????????????????
     *
     * @param regionList
     * @param year
     * @return
     */
    private List<ReturnLeaveSumVo> findReturnLeaveSumData(List<AreaRegionCode> regionList, String year, Boolean checkInfoFlag) {
        List<ReturnLeaveSum> returnLeaveSum = new ArrayList<>();
        // ????????????
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        for (AreaRegionCode regionCode : regionList) {
            List<String> statusList = getStatusList(currentRegionCode.getRegionLevel(), regionCode.getLastId(), year, checkInfoFlag);
            returnLeaveSum.addAll(findReturnLeaveSumByRegion(regionCode, year, statusList));
        }
        //????????????????????????
        List<ReturnLeaveSumVo> returnLeaveList = returnLeaveSum.stream().map(p -> {
            ReturnLeaveSumVo temp = new ReturnLeaveSumVo();
            BeanUtils.copyProperties(p, temp);
            return temp;
        }).collect(Collectors.toList());

        Map<String, ReturnLeaveSumVo> produceMap = new HashMap();
        ReturnLeaveSumVo mapItem = null;
        //????????????????????????????????????

        for (ReturnLeaveSumVo produce : returnLeaveList) {
            mapItem = produceMap.get(produce.getStrawType());
            if (mapItem != null) {
                mapItem = addReturnLeave(mapItem, produce); //??????
                produceMap.put(mapItem.getStrawType(), mapItem);
            } else
                produceMap.put(produce.getStrawType(), produce);
        }
        returnLeaveList = produceMap.values().stream().collect(Collectors.toList());

        return returnLeaveList;
    }

    /**
     * ????????????
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
     * ????????????id?????????????????????????????????
     *
     * @param regionCode
     * @param year
     * @return
     */
    private List<ReturnLeaveSum> findReturnLeaveSumByRegion(AreaRegionCode regionCode, String year, List<String> status) {
        List<ReturnLeaveSum> spList = null;

        if (regionCode.getRegionLevel().equals(RegionLevel.COUNTY.getCode())) {  //??????????????????
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

        } else {  //???????????????
            String childrenIds = SysRegionUtil.getChildrenRegionIds(regionCode.getLastId());
            //??????????????????????????????????????????????????????????????????????????????

            spList = returnLeaveSumMapper.getReturnLeaveSumByChildrenIdsAndYear(IdUtil.getIdsByStr(childrenIds), year, status);
            for (ReturnLeaveSum s : spList) {
                s.setSeedArea(s.getCollectResource());
                s.setReturnArea(s.getProStillField());
            }
            return spList;
        }


    }

    /**
     * ????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param regionList
     * @param vo
     * @return
     */
    private PageUtils<MainUtilizeResVo> getMainUtilizeByRegionListPage(List<AreaRegionCode> regionList, AggregateMainUtilizeQueryVo vo) {
        List<MainUtilizeResVo> muList = new ArrayList();
        //???????????????????????????????????????
        /*for (AreaRegionCode region : regionList) {
            allRegionList.addAll(getMainUtilizeRegionListByRegion(region,vo.getYear()));
        }*/
        PageUtils pu = new PageUtils(muList, 0, vo.getPageSize(), 1);
        if (regionList.size() > 0) {

            //??????UtilizeId??????
            List<MainUtilizeVo> muIds = new ArrayList<>();
            for (AreaRegionCode regionCode : regionList) {
                List<MainUtilizeVo> tempList = strawUtilizeMapper.selectMainUtilizeIdByRegionCode(vo.getYear(), regionCode.getProvinceId(), regionCode.getCityId(), regionCode.getCountyId());
                muIds.addAll(tempList);
            }
            if (muIds.size() > 0) {
                List<String> utilizeIds = muIds.stream().map(m -> m.getUtilizeId()).collect(Collectors.toList());

                //??????,?????????????????????
//                PageHelper.offsetPage(vo.getPageNo(),vo.getPageSize());

                muList = strawUtilizeDetailMapper.selectMainUtilizeInfoByUtilizeIdsPage(vo, utilizeIds);

                //??????muIds - muIdsMap
                final Map<String, MainUtilizeVo> muIdsMap = muIds.stream().collect(Collectors.toMap(m -> m.getUtilizeId(), m -> m));
                List<String> validIds = new ArrayList();
                //??????????????????
                for (MainUtilizeResVo m : muList) {
                    m.setAreaId(muIdsMap.get(m.getUtilizeId()).getAreaId());
                    m.setMainId(muIdsMap.get(m.getUtilizeId()).getMainId());
                    m.setMainName(muIdsMap.get(m.getUtilizeId()).getMainName());
                    m.setYear(vo.getYear());
                    validIds.add(m.getAreaId());
                }
                //??????????????????
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
     * ????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param regionList
     * @param vo
     * @return
     */
    private List<MainUtilizeResVo> getMainUtilizeByRegionList(List<AreaRegionCode> regionList, final AggregateMainUtilizeQueryVo vo) {
        List<MainUtilizeResVo> muList = new ArrayList();
        List<AreaRegionCode> allRegionList = new ArrayList<>();
        //???????????????????????????????????????
        for (AreaRegionCode region : regionList) {
            allRegionList.addAll(getMainUtilizeRegionListByRegion(region, vo.getYear()));
        }

        if (allRegionList.size() > 0) {
            String areaIds = allRegionList.stream().map(r -> r.getLastId()).collect(Collectors.joining(","));
            vo.setAreaIds(areaIds); //???????????????????????????areaId
            //??????UtilizeId??????
            List<MainUtilizeVo> muIds = strawUtilizeMapper.selectMainUtilizeIdByAreaIds(vo);
            if (muIds.size() > 0) {
                List<String> utilizeIds = muIds.stream().map(m -> m.getUtilizeId()).collect(Collectors.toList());
                muList = strawUtilizeDetailMapper.selectMainUtilizeInfoByUtilizeIds(vo, utilizeIds);
                //?????? allRegionList ???Map
                final Map<String, AreaRegionCode> allRegionMap = allRegionList.stream().collect(Collectors.toMap(m -> m.getLastId(), m -> m));
                //??????muIds - muIdsMap
                final Map<String, MainUtilizeVo> muIdsMap = muIds.stream().collect(Collectors.toMap(m -> m.getUtilizeId(), m -> m));
                //??????????????????
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
     * ??????????????????????????????????????????
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
     * ??????????????????????????????????????????????????????????????????
     *
     * @param parentRegion
     * @return
     */
    private List<AreaRegionCode> getAuditPassedRegionCodeByParent(AreaRegionCode parentRegion, String year) {
        List<AreaRegionCode> resList = new ArrayList();
        List<AreaRegionCode> children = SysRegionUtil.getChildrenRegionCodeList(parentRegion.getLastId());

        if (!children.isEmpty()) {
            if (parentRegion.getRegionLevel().equals(RegionLevel.CITY.getCode())) {   //parent?????????,??????push??????
                //??????
                resList.addAll(getAutditPasswdRegionByRegionList(children, year));
            } else {  //parent????????????????????????
                children = getAutditPasswdRegionByRegionList(children, year);
                if (children != null && children.size() > 0)
                    children.forEach(c -> resList.addAll(getAuditPassedRegionCodeByParent(c, year)));
            }
        }

        return resList;
    }

    /**
     * ???????????????????????????????????????????????????
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
     * ????????????????????????????????????
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
         * ????????????????????????
         *
         * @return
         */
        public boolean hasProFill() {
            return psdList != null && psdList.size() > 0;
        }

        /**
         * ????????????????????????
         *
         * @return
         */
        public boolean hasUtilize() {
            return (dudList != null && dudList.size() > 0) || (sudList != null && sudList.size() > 0);
        }
    }

    /**
     * ??????????????????????????????????????????????????????
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
        //????????????????????????????????????
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
            //??????
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
        //?????????????????????????????????????????????
        List<AreaRegionCode> regionList = checkQueryRights(queryVo);
        // ????????????
        // AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        List<String> areaIds = regionList.stream().map(AreaRegionCode::getLastId).collect(Collectors.toList());
        List<String> years = Lists.newArrayList();
        if (queryVo.getYear() != null && !"".equals(queryVo.getYear())) {
            years = Arrays.asList(queryVo.getYear().split(","));
        }
        // ???????????????
        List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        List<StrawUsageVo> result = Lists.newArrayList();
        AreaRegionCode regionCode = regionList.get(0);
        if (RegionLevel.COUNTY.getCode().equals(regionCode.getRegionLevel())) {
            // ????????????
            List<StrawUsageVo> countyResult = countyDataAnalysisService.findDataByAreaIdsAndYears(areaIds, years);
            if (countyResult.size() == 0) {
                // ????????????????????????????????????????????????
                countyResult = countyDataAnalysisService.getCountyDataAnalysis(years.get(0), areaIds.get(0));
            }
            if (CollectionUtils.isNotEmpty(countyResult)) {
                result = countyResult;
            }
        } else {
            // ????????????
            List<String> childrenIds = SysRegionUtil.getChildrenRegionIdList(regionCode.getLastId());
            List<StrawUsageVo> cityResult = countyDataAnalysisService.findDataByAreaIdsAndYears(childrenIds, years);
            if (CollectionUtils.isNotEmpty(cityResult)) {
                result = cityResult;
            }
        }
        Map<String, StrawUsageVo> usageVoMap = result.stream().collect(Collectors.toMap(StrawUsageVo::getStrawType, Function.identity(), (key1, key2) -> key2));
        // ??????
        List<StrawUsageVo> finalResult = Lists.newArrayList();
        for (SysDictionary dictionary : dictList) {
            StrawUsageVo usageVo = usageVoMap.get(dictionary.getDictKey());
            if (usageVo == null) {
                usageVo = new StrawUsageVo();
            }
            usageVo.setStrawName(dictionary.getDictValue());
            finalResult.add(usageVo);
            // ??????
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
        // ??????
        StrawUsageVo total = getStrawUsageTotal(finalResult);
        finalResult.add(0, total);
        return finalResult;
    }

    public static StrawUsageVo getStrawUsageTotal(List<StrawUsageVo> usageVoList) {
        StrawUsageVo total = new StrawUsageVo();
        total.setStrawName("??????");
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

            // ?????????
            total.setFertilize(total.getFertilize().add(vo.getFertilize() == null ? BigDecimal.ZERO : vo.getFertilize()));
            total.setFeed(total.getFeed().add(vo.getFeed() == null ? BigDecimal.ZERO : vo.getFeed()));
            total.setFuelled(total.getFuelled().add(vo.getFuelled() == null ? BigDecimal.ZERO : vo.getFuelled()));
            total.setBaseMat(total.getBaseMat().add(vo.getBaseMat() == null ? BigDecimal.ZERO : vo.getBaseMat()));
            total.setMaterialization(vo.getMaterialization().add(vo.getMaterialization() == null ? BigDecimal.ZERO : vo.getMaterialization()));
            // ????????????????????????
            Set<String> leaveTypes = Sets.newHashSet();
            if (vo.getFertilize() != null && vo.getFertilize().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("?????????");
            }
            if (vo.getFeed() != null && vo.getFeed().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("?????????");
            }
            if (vo.getFuelled() != null && vo.getFuelled().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("?????????");
            }
            if (vo.getBaseMat() != null && vo.getBaseMat().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("?????????");
            }
            if (vo.getMaterialization() != null && vo.getMaterialization().compareTo(BigDecimal.ZERO) > 0) {
                leaveTypes.add("?????????");
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
        sf1.setName("??????");
        sf1.setCount(BigDecimalUtil.getTenThousand(sp.getSum()));

        StrawFiveVo sf2 = new StrawFiveVo();
        sf2.setName("?????????");
        sf2.setCount(BigDecimalUtil.getTenThousand(sp.getFertilising()));

        StrawFiveVo sf3 = new StrawFiveVo();
        sf3.setName("?????????");
        sf3.setCount(BigDecimalUtil.getTenThousand(sp.getForage()));

        StrawFiveVo sf4 = new StrawFiveVo();
        sf4.setName("?????????");
        sf4.setCount(BigDecimalUtil.getTenThousand(sp.getFuel()));

        StrawFiveVo sf5 = new StrawFiveVo();
        sf5.setName("?????????");
        sf5.setCount(BigDecimalUtil.getTenThousand(sp.getBase()));

        StrawFiveVo sf6 = new StrawFiveVo();
        sf6.setName("?????????");
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
