package com.sofn.ducss.service.impl;

import com.google.common.collect.Lists;
import com.sofn.ducss.enums.*;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.DataAnalysisAreaMapper;
import com.sofn.ducss.mapper.DataAnalysisCityMapper;
import com.sofn.ducss.mapper.DataAnalysisProviceMapper;
import com.sofn.ducss.mapper.SysDictionaryMapper;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SearchService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.SysDictUtil;
import com.sofn.ducss.vo.DataAnalysis.DataKing;
import com.sofn.ducss.vo.DataAnalysis.DataKingChanVo;
import com.xiaoleilu.hutool.bean.BeanUtil;
import org.apache.commons.collections4.CollectionUtils;
/*import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;*/
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;*/
/*import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;*/
/*import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;*/
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/10/9  16:48
 * @description
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SysApi sysApi;

    @Autowired
    private DataAnalysisAreaMapper dataAnalysisArea;

    @Autowired
    private DataAnalysisCityMapper dataAnalysisCity;

    @Autowired
    private DataAnalysisProviceMapper dataAnalysisProvice;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    //???????????????????????????
    public final static Integer PAGE_SIZE = 10000000;

 /*   @Override
    public List search(Map<String, String> searchMap) throws Exception {
        //??????????????????Es
        if (null != searchMap) {
            //??????????????????
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //0:?????????match_all
            if (!StringUtils.isEmpty(searchMap.get("year"))) {
                boolQuery.must(QueryBuilders.termsQuery("year", searchMap.get("year").split(",")));
            }
            if (!StringUtils.isEmpty(searchMap.get("area"))
                    && !"allProvinces".contains(searchMap.get("area"))
                    && !"allCities".contains(searchMap.get("area"))
                    && !"allCounties".contains(searchMap.get("area"))) {
                BoolQueryBuilder boolQuery2 = QueryBuilders.boolQuery();
                boolQuery2.should(QueryBuilders.termsQuery("area_id", searchMap.get("area").split(",")));
                boolQuery2.should(QueryBuilders.termsQuery("city_id", searchMap.get("area").split(",")));
                boolQuery2.should(QueryBuilders.termsQuery("province_id", searchMap.get("area").split(",")));
                boolQuery.must(boolQuery2);
            }
            if (!StringUtils.isEmpty(searchMap.get("cropType"))) {
                boolQuery.must(QueryBuilders.termsQuery("straw_type", searchMap.get("cropType").split(",")));
            }
            String pageNum = searchMap.get("pageNum");
            if (null == pageNum) {
                pageNum = "1";
            }
            //??????
            //??????province_id???????????????????????????????????????????????????sum
//            TermsAggregationBuilder termsBuilder = AggregationBuilders.terms("sum").field("province_id.keyword");
            //?????????????????????????????????????????????
//            TermsAggregationBuilder aAggregationBuilder2 = AggregationBuilders.terms("region_count").field("straw_type.keyword");
//            TermsAggregationBuilder aAggregationBuilder3 = AggregationBuilders.terms("region_count2").field("year.keyword");
            //4. ?????????????????????
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            nativeSearchQueryBuilder.withQuery(boolQuery).withPageable(PageRequest.of(Integer.parseInt(pageNum) - 1, PAGE_SIZE));
//            nativeSearchQueryBuilder.addAggregation(termsBuilder.subAggregation(aAggregationBuilder2));
//            10: ????????????, ??????????????????
            AggregatedPage<ElascticsearchModel> aggregatedPage = esTemplate.queryForPage(nativeSearchQueryBuilder.build(), ElascticsearchModel.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                    List<T> list = new ArrayList<>();
                    SearchHits hits = searchResponse.getHits();
                    if (null != hits) {
                        for (SearchHit hit : hits) {
                            ElascticsearchModel skuInfo = JSON.parseObject(hit.getSourceAsString(), ElascticsearchModel.class);
                            list.add((T) skuInfo);
                        }
                    }
                    return new AggregatedPageImpl<T>(list, pageable, hits.getTotalHits(), searchResponse.getAggregations());
                }
            });
            //13. ??????????????????
            ArrayList<ElascticsearchModel> list = new ArrayList<>();
            list.addAll(aggregatedPage.getContent());
            //??????
//            List<ElascticsearchModel> unique = list.stream().collect(
//
//                    collectingAndThen(
//
//                            toCollection(() -> new TreeSet<>(comparing(ElascticsearchModel::getId))), ArrayList::new)
//            );
            //-----------????????????????????????start--------------
            ArrayList<DataKing> kingArrayList = new ArrayList<>();
            DataKing dataKing;
            List<SysRegionForm> areaList = sysApi.getSysRegionByPage("", "", "", 0, 0L, 0, 1000000).getData().getList();
            Map<String, String> addressNameMap = areaList.stream().collect(
                    Collectors.toMap((p) -> p.getRegionCode(), (p) -> p.getRegionName()));

            if (list.size() > 0) {
                HashMap<String, Object> map3;
                HashMap<String, Map<String, Object>> map;
                for (ElascticsearchModel elascticsearchModel : list) {
                    map = new HashMap<>();
                    if (searchMap.get("area").contains(elascticsearchModel.getAreaId()) && !elascticsearchModel.getAreaId().equals("0")) {
                        elascticsearchModel.setAreaName(addressNameMap.get(elascticsearchModel.getAreaId()));
                    }
                    if (searchMap.get("area").contains(elascticsearchModel.getCityId()) && !elascticsearchModel.getCityId().equals("0")) {
                        elascticsearchModel.setAreaName(addressNameMap.get(elascticsearchModel.getCityId()));
                    }
                    if (searchMap.get("area").contains(elascticsearchModel.getProviceId()) && !elascticsearchModel.getProviceId().equals("0")) {
                        elascticsearchModel.setAreaName(addressNameMap.get(elascticsearchModel.getProviceId()));
                    }
                    //?????????
                    if (searchMap.get("area").contains("allProvinces")) {
                        elascticsearchModel.setAreaName(addressNameMap.get(elascticsearchModel.getProviceId()));
                    }
                    //?????????
                    if (searchMap.get("area").contains("allCities")) {
                        elascticsearchModel.setAreaName(addressNameMap.get(elascticsearchModel.getCityId()));
                    }
                    //?????????
                    if (searchMap.get("area").contains("allCounties")) {
                        elascticsearchModel.setAreaName(addressNameMap.get(elascticsearchModel.getAreaId()));
                    }
                    //????????????
                    map3 = new HashMap<>();
                    //????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.GRAINYIELD.getNum())) {
                        map3.put("grainYield", elascticsearchModel.getGrainYield());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.KUSUGANI.getNum())) {
                        map3.put("grassValleyRatio", elascticsearchModel.getGrassValleyRatio());
                    }
                    //???????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.COLLECTABLECOE.getNum())) {
                        map3.put("collectionRatio", elascticsearchModel.getCollectionRatio());
                    }
                    //????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.SOWNAREA.getNum())) {
                        map3.put("seedArea", elascticsearchModel.getSeedArea());
                    }
                    //????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.RETURNAREA.getNum())) {
                        map3.put("returnArea", elascticsearchModel.getReturnArea());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.CALLOUT.getNum())) {
                        map3.put("exportYield", elascticsearchModel.getExportYield());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.YIELD.getNum())) {
                        map3.put("theoryResource", elascticsearchModel.getTheoryResource());
                    }
                    //??????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.COLLECTABLEAMO.getNum())) {
                        map3.put("collectResource", elascticsearchModel.getCollectResource());
                    }
                    //?????????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.MARKETENTITY.getNum())) {
                        map3.put("marketEnt", elascticsearchModel.getMarketEnt());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.FERTILIZATION.getNum())) {
                        map3.put("fertilising", elascticsearchModel.getFertilizes());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.FEED.getNum())) {
                        map3.put("forage", elascticsearchModel.getFeeds());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.FUELLED.getNum())) {
                        map3.put("fuel", elascticsearchModel.getFuelleds());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.BASEMAT.getNum())) {
                        map3.put("base", elascticsearchModel.getBaseMats());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.MATERIALIZATION.getNum())) {
                        map3.put("material", elascticsearchModel.getMaterializations());
                    }
                    //???????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECENTRALIZED.getNum())) {
                        map3.put("reuse", elascticsearchModel.getReuse());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECENTRALIZEDs.getNum())) {
                        map3.put("fertilisings", elascticsearchModel.getFertilisingd());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECENTRA.getNum())) {
                        map3.put("forages", elascticsearchModel.getForaged());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECENTR.getNum())) {
                        map3.put("fuels", elascticsearchModel.getFueld());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECENT.getNum())) {
                        map3.put("bases", elascticsearchModel.getBased());
                    }
                    //?????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEN.getNum())) {
                        map3.put("materials", elascticsearchModel.getMateriald());
                    }
                    //???????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECE.getNum())) {
                        map3.put("returnResource", elascticsearchModel.getReturnResource());
                    }
                    //?????????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DEC.getNum())) {
                        map3.put("other", elascticsearchModel.getOther());
                    }
                    //??????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECES.getNum())) {
                        map3.put("fertilize", elascticsearchModel.getFertilize());
                    }
                    //??????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEB.getNum())) {
                        map3.put("feed", elascticsearchModel.getFeed());
                    }
                    //??????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECESS.getNum())) {
                        map3.put("fuelled", elascticsearchModel.getFuelled());
                    }
                    //??????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECESD.getNum())) {
                        map3.put("baseMat", elascticsearchModel.getBaseMat());
                    }
                    //??????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEG.getNum())) {
                        map3.put("materialization", elascticsearchModel.getMaterialization());
                    }
                    //???????????????=???????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEH.getNum())) {
                        //???????????????
                        map3.put("totol", elascticsearchModel.getStrawUtilization());
                    }
                    //???????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEND.getNum())) {
                        map3.put("totolRate", elascticsearchModel.getTotolRate());
                    }
                    //????????????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEBF.getNum())) {
                        map3.put("comprUtilIndex", elascticsearchModel.getComprUtilIndex());
                    }
                    //???????????????????????????
                    if (searchMap.get("analysisIndex").contains(AnalysisIndexEnum.DECEBG.getNum())) {
                        map3.put("induUtilIndex", elascticsearchModel.getInduUtilIndex());
                    }
                    map.put(elascticsearchModel.getYear(), map3);
                    dataKing = new DataKing();
                    dataKing.setArea_Name(elascticsearchModel.getAreaName());
                    dataKing.setGtime(elascticsearchModel.getYear());
                    dataKing.setStrawName(CropsEnum.getByStrawTypeEnglish(elascticsearchModel.getStrawType()));
                    dataKing.setIndicatorArray(map);
                    kingArrayList.add(dataKing);
                }
            }
            ArrayList<DataKing> arrayList = new ArrayList<>();
            Map<String, List<DataKing>> collect = kingArrayList.stream().collect(Collectors.groupingBy(e -> fetchGroupKey(e)));
            DataKing king;
            HashMap<String, Map<String, Object>> mapFinally;
            for (List<DataKing> value : collect.values()) {
                if (value.size() > 1) {
                    king = new DataKing();
                    //??????????????????????????????????????????????????????????????????????????????
                    HashMap<String, Map<String, Object>> map = new HashMap<>();
                    //????????????
                    ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                    Map<String, Object> result1 = new HashMap<String, Object>();
                    for (DataKing dataKing1 : value) {
                        king.setArea_Name(dataKing1.getArea_Name());
                        king.setGtime(dataKing1.getGtime());
                        king.setStrawName(dataKing1.getStrawName());
                        indexMaps.addAll(dataKing1.getIndicatorArray().values());
                    }
                    for (Map<String, Object> maps : indexMaps) {
                        Set<String> strings = maps.keySet();
                        for (String string : strings) {
                            String id = string;
                            BigDecimal values = new BigDecimal(0);
                            if (!org.springframework.util.StringUtils.isEmpty(maps.get(string))) {
                                if (maps.get(string).toString().contains("%")) {
                                    values = new BigDecimal(maps.get(string).toString().replaceAll("%", ""));
                                } else {
                                    values = new BigDecimal(maps.get(string).toString());
                                }
                                if (result1.containsKey(id)) {
                                    BigDecimal temp = new BigDecimal(0);
                                    if (result1.get(id).toString().contains("%")) {
                                        temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                    } else {
                                        temp = new BigDecimal(result1.get(id).toString());
                                    }
                                    values = values.add(temp);
                                }
//                            if ("induUtilIndex".equals(id) || "comprUtilIndex".equals(id) || "totolRate".equals(id)) {
//                                result1.put(id, values + "%");
//                            } else {
//                                result1.put(id, values);
//                            }
                                result1.put(id, values);
                            }
                        }
                    }
                    mapFinally = new HashMap<>();
                    mapFinally.put(king.getGtime(), result1);
                    king.setIndicatorArray(mapFinally);
                    value.clear();
                    value.add(king);
                }
            }
            for (List<DataKing> value : collect.values()) {
                arrayList.addAll(value);
            }
            //-----------????????????????????????end--------------
            //11. ?????????
            ArrayList<Object> objects = new ArrayList<>();
            Map<String, List<DataKing>> collects = arrayList.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
            DataKing dataKingFinally;
            ArrayList<HashMap<String, Map<String, Object>>> hashMaps;
            for (Map.Entry<String, List<DataKing>> stringListEntry : collects.entrySet()) {
                dataKingFinally = new DataKing();
                dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                hashMaps = new ArrayList<>();
                for (DataKing dataKing1 : stringListEntry.getValue()) {
                    hashMaps.add(dataKing1.getIndicatorArray());
//                    dataKingFinally.setIndicatorArrays(hashMaps);
                }

                objects.add(dataKingFinally);
            }
            return objects;
        }
        return null;
    }*/

    /**
     * ???????????????
     *
     * @param paramMap
     * @return
     * @throws Exception
     */
    @Override
    public PageUtils search2(Map<String, String> paramMap) throws Exception {
        if (null != paramMap) {
            //???????????????????????????00000???????????????????????????
            if (paramMap.get("allCropType").contains("0")) {
                //?????????
                Integer count = 0;
                Integer countPage = 0;
                ArrayList<ElascticsearchModel> elascticsearchModels = new ArrayList<>();
                ArrayList<ElascticsearchModel> elascticsearchModelsTemp = new ArrayList<>();
                HashMap<String, Object> map = new HashMap<>();
                for (Map.Entry<String, String> stringStringEntry : paramMap.entrySet()) {
                    if (stringStringEntry.getKey().equals("pageNum") || stringStringEntry.getKey().equals("pageSize")) {
                        map.put(stringStringEntry.getKey(), Integer.parseInt(stringStringEntry.getValue()));
                    } else {
                        map.put(stringStringEntry.getKey(), stringStringEntry.getValue().split(","));
                    }
                    if (stringStringEntry.getKey().equals("pageNum")) {
                        int pageSize = (Integer.parseInt(stringStringEntry.getValue()) - 1) * Integer.parseInt(paramMap.get("pageSize"));
                        map.replace(stringStringEntry.getKey(), pageSize);
                    }
                }
                //??????????????????????????????allProvinces allCities allCounties ?????????
                //???????????????????????????????????????????????????????????????
                if ("allProvinces".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getListByNoLimit(map);

                    fixProvinceErrorInfo(list);

                    //???????????????
                    elascticsearchModels.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCities".contains(paramMap.get("area"))) {
                    List<DataAnalysisCity> list = dataAnalysisCity.getListByNoLimit(map);
                    elascticsearchModels.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisArea> list = dataAnalysisArea.getListByNoLimit(map);
                    elascticsearchModels.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if (!"allProvinces".contains(paramMap.get("area")) && !"allCities".contains(paramMap.get("area")) && !"allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getList(map);
                    List<DataAnalysisCity> list2 = dataAnalysisCity.getList(map);
                    List<DataAnalysisArea> list3 = dataAnalysisArea.getList(map);
                    elascticsearchModelsTemp.addAll(convertList2List(list, ElascticsearchModel.class));
                    elascticsearchModelsTemp.addAll(convertList2List(list2, ElascticsearchModel.class));
                    elascticsearchModelsTemp.addAll(convertList2List(list3, ElascticsearchModel.class));
                    elascticsearchModels.addAll(elascticsearchModelsTemp);
                }
                //??????????????????
                ArrayList<DataKing> kingArrayList = new ArrayList<>();
                DataKing dataKing;
                if (elascticsearchModels.size() > 0) {
                    HashMap<String, Object> map3;
                    HashMap<String, Map<String, Object>> map2;
                    for (ElascticsearchModel elascticsearchModel : elascticsearchModels) {
                        map2 = new HashMap<>();
                        //????????????
                        map3 = new LinkedHashMap<>();
                        //????????????
                        map3.put("grainYield", elascticsearchModel.getGrainYield());
                        //?????????
                        if (elascticsearchModel.getAreaName().split("/").length == 2 || elascticsearchModel.getAreaName().split("/").length == 3) {
                            map3.put("grassValleyRatio", "------");
                        } else {
                            map3.put("grassValleyRatio", elascticsearchModel.getGrassValleyRatio());
                        }
                        //???????????????
                        if (elascticsearchModel.getAreaName().split("/").length == 2 || elascticsearchModel.getAreaName().split("/").length == 3) {
                            map3.put("collectionRatio", "------");
                        } else {
                            map3.put("collectionRatio", elascticsearchModel.getCollectionRatio());
                        }
                        //????????????
                        map3.put("seedArea", elascticsearchModel.getSeedArea());
                        //????????????
                        map3.put("returnArea", elascticsearchModel.getReturnArea());
                        //?????????
                        map3.put("exportYield", elascticsearchModel.getExportYield());
                        //?????????
                        map3.put("theoryResource", elascticsearchModel.getTheoryResource());
                        //??????????????????
                        map3.put("collectResource", elascticsearchModel.getCollectResource());
                        //?????????????????????
                        map3.put("marketEnt", elascticsearchModel.getMarketEnt());
                        //?????????
                        map3.put("fertilising", elascticsearchModel.getFertilizes());
                        //?????????
                        map3.put("forage", elascticsearchModel.getFeeds());
                        //?????????
                        map3.put("fuel", elascticsearchModel.getFuelleds());
                        //?????????
                        map3.put("base", elascticsearchModel.getBaseMats());
                        //?????????
                        map3.put("material", elascticsearchModel.getMaterializations());
                        //???????????????
                        map3.put("reuse", elascticsearchModel.getReuse());
                        //?????????
                        map3.put("fertilisings", elascticsearchModel.getFertilisingd());
                        //?????????
                        map3.put("forages", elascticsearchModel.getForaged());
                        //?????????
                        map3.put("fuels", elascticsearchModel.getFueld());
                        //?????????
                        map3.put("bases", elascticsearchModel.getBased());
                        //?????????
                        map3.put("materials", elascticsearchModel.getMateriald());
                        //???????????????
                        map3.put("returnResource", elascticsearchModel.getReturnResource());
                        //?????????????????????
                        map3.put("other", elascticsearchModel.getOther());
                        //??????????????????
                        map3.put("fertilize", elascticsearchModel.getFertilize());
                        //??????????????????
                        map3.put("feed", elascticsearchModel.getFeed());
                        //??????????????????
                        map3.put("fuelled", elascticsearchModel.getFuelled());
                        //??????????????????
                        map3.put("baseMat", elascticsearchModel.getBaseMat());
                        //??????????????????
                        map3.put("materialization", elascticsearchModel.getMaterialization());
                        //???????????????=???????????????
                        //???????????????
                        map3.put("totol", elascticsearchModel.getStrawUtilization());
                        //???????????????
                        map3.put("totolRate", elascticsearchModel.getTotolRate());
                        //????????????????????????
                        map3.put("comprUtilIndex", elascticsearchModel.getComprUtilIndex());
                        //???????????????????????????
                        map3.put("induUtilIndex", elascticsearchModel.getInduUtilIndex());
                        map2.put(elascticsearchModel.getYear(), map3);
                        dataKing = new DataKing();
                        if (!elascticsearchModel.getAreaId().equals("0")) {
                            dataKing.setId(elascticsearchModel.getAreaId());
                        }
                        if (!elascticsearchModel.getCityId().equals("0")) {
                            dataKing.setId(elascticsearchModel.getCityId());
                        }
                        if (!elascticsearchModel.getProviceId().equals("0")) {
                            dataKing.setId(elascticsearchModel.getProviceId());
                        }
                        dataKing.setArea_Name(elascticsearchModel.getAreaName());
                        dataKing.setGtime(elascticsearchModel.getYear());
                        dataKing.setStrawName(elascticsearchModel.getStrawName());
                        dataKing.setIndicatorArray(map2);
                        kingArrayList.add(dataKing);
                    }
                }
                if (paramMap.get("area").contains("six")) {
                    for (DataKing king : kingArrayList) {
                        king.setArea_Name(SixRegionEnum.getByStrawTypeEnglish(king.getId()));
                    }
                    ArrayList<DataKing> objects = new ArrayList<>();
                    Map<String, List<DataKing>> collects = kingArrayList.stream().collect(Collectors.groupingBy(e -> fetchGroupKeysByYear(e)));
                    DataKing king;
                    HashMap<String, Map<String, Object>> mapFinally;
                    for (List<DataKing> value : collects.values()) {
                        if (value.size() > 1) {
                            king = new DataKing();
                            //??????????????????????????????????????????????????????????????????????????????
//                        HashMap<String, Map<String, Object>> map = new HashMap<>();
                            //????????????
                            ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                            Map<String, Object> result1 = new LinkedHashMap<String, Object>();
                            for (DataKing dataKing1 : value) {
                                king.setId(dataKing1.getId());
                                king.setArea_Name(dataKing1.getArea_Name());
                                king.setGtime(dataKing1.getGtime());
                                king.setStrawName(dataKing1.getStrawName());
                                indexMaps.addAll(dataKing1.getIndicatorArray().values());
                            }
                            for (Map<String, Object> mapss : indexMaps) {
                                Set<String> strings = mapss.keySet();
                                for (String string : strings) {
                                    String id = string;
                                    BigDecimal values = new BigDecimal(0);
                                    if (!org.springframework.util.StringUtils.isEmpty(mapss.get(string))) {
                                        if (string.equals("grassValleyRatio") || string.equals("collectionRatio")) {
                                            result1.put(id, "------");
                                        } else {
                                            if (mapss.get(string).toString().contains("%")) {
                                                values = new BigDecimal(mapss.get(string).toString().replaceAll("%", ""));
                                            } else {
                                                values = new BigDecimal(mapss.get(string).toString());
                                            }
                                            if (result1.containsKey(id)) {
                                                BigDecimal temp = new BigDecimal(0);
                                                if (result1.get(id).toString().contains("%")) {
                                                    temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                                } else {
                                                    temp = new BigDecimal(result1.get(id).toString());
                                                }
                                                values = values.add(temp);
                                            }
                                            result1.put(id, values);
                                        }
                                    }
                                }
                            }
                            //???????????????????????????????????????????????????????????????
                            //???????????????
                            BigDecimal totol = new BigDecimal(0);
                            //????????????
                            BigDecimal collectResource = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal marketEnt = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal reuse = new BigDecimal(0);
                            //???????????????
                            BigDecimal returnResource = new BigDecimal(0);
                            //??????
                            BigDecimal heJi = new BigDecimal(0);

                            totol = (BigDecimal) result1.get("totol");
                            collectResource = (BigDecimal) result1.get("collectResource");
                            marketEnt = (BigDecimal) result1.get("marketEnt");
                            reuse = (BigDecimal) result1.get("reuse");
                            returnResource = (BigDecimal) result1.get("returnResource");
                            heJi = marketEnt.add(reuse).add(returnResource);
                            for (String s : result1.keySet()) {
                                if (s.equals("totolRate")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                                    }
                                } else if (s.equals("comprUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, heJi.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                } else if (s.equals("induUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                }
                            }
                            mapFinally = new HashMap<>();
                            mapFinally.put(king.getGtime(), result1);
                            king.setIndicatorArray(mapFinally);
                            value.clear();
                            value.add(king);
                        }
                    }
                    for (List<DataKing> value : collects.values()) {
                        for (DataKing dataKing1 : value) {
                            objects.add(dataKing1);
                        }
                    }
                    Map<String, List<DataKing>> collect1 = objects.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    objects.clear();
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collect1.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps = new LinkedHashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                            dataKingFinally.setIndicatorArrays(hashMaps);
                        }
                        objects.add(dataKingFinally);
                    }
                    List<DataKing> collect = objects.stream().sorted(Comparator.comparing(DataKing::getId).thenComparing(DataKing::getStrawName, Comparator.reverseOrder())).collect(Collectors.toList());
                    if (collect != null && collect.size() > 0) {
                        collect = groupAreaIdAndSort(collect);
                    }
                    //????????????????????????
                    PageUtils pageUtils = new PageUtils();
                    count = collect.size();
                    countPage = (int) Math.ceil(Double.parseDouble(String.valueOf(collect.size() / Double.valueOf(paramMap.get("pageSize")))));
                    List<DataKing> dataKings = page2(collect, Integer.parseInt(paramMap.get("pageSize")), Integer.parseInt(paramMap.get("pageNum")));
                    pageUtils.setList(dataKings);
                    //?????????
                    pageUtils.setCurrPage(Integer.parseInt(paramMap.get("pageNum")));
                    //??????????????????
                    pageUtils.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
                    //?????????
                    pageUtils.setTotalCount(count);
                    //?????????
                    pageUtils.setTotalPage(countPage);
                    return pageUtils;
                } else {
                    ArrayList<DataKing> objects = new ArrayList<>();
                    Map<String, List<DataKing>> collects = kingArrayList.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collects.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps = new LinkedHashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                            dataKingFinally.setIndicatorArrays(hashMaps);
                        }
                        objects.add(dataKingFinally);
                    }
                    List<DataKing> collect = objects.stream().sorted(Comparator.comparing(DataKing::getId).thenComparing(DataKing::getStrawName, Comparator.reverseOrder())).collect(Collectors.toList());
                    if (collect != null && collect.size() > 0) {
                        collect = groupAreaIdAndSort(collect);
                    }
                    collect = collect.stream().sorted(Comparator.comparing(DataKing::getId)).collect(Collectors.toList());
                    //????????????????????????
                    PageUtils pageUtils = new PageUtils();
                    count = collect.size();
                    countPage = (int) Math.ceil(Double.parseDouble(String.valueOf(collect.size() / Double.valueOf(paramMap.get("pageSize")))));
                    List<DataKing> dataKings = page2(collect, Integer.parseInt(paramMap.get("pageSize")), Integer.parseInt(paramMap.get("pageNum")));
                    pageUtils.setList(dataKings);
                    //?????????
                    pageUtils.setCurrPage(Integer.parseInt(paramMap.get("pageNum")));
                    //??????????????????
                    pageUtils.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
                    //?????????
                    pageUtils.setTotalCount(count);
                    //?????????
                    pageUtils.setTotalPage(countPage);
                    return pageUtils;
                }
            }
            //?????????????????????11111???
            if (paramMap.get("allCropType").contains("1")) {
                Integer countPage = 0;
                ArrayList<ElascticsearchModel> elascticsearchModelss = new ArrayList<>();
                ArrayList<ElascticsearchModel> elascticsearchModelsTemps = new ArrayList<>();
                HashMap<String, Object> map = new HashMap<>();
                for (Map.Entry<String, String> stringStringEntry : paramMap.entrySet()) {
                    if (stringStringEntry.getKey().equals("pageNum") || stringStringEntry.getKey().equals("pageSize")) {
                        map.put(stringStringEntry.getKey(), Integer.parseInt(stringStringEntry.getValue()));
                    } else {
                        map.put(stringStringEntry.getKey(), stringStringEntry.getValue().split(","));
                    }
                    if (stringStringEntry.getKey().equals("pageNum")) {
                        int pageSize = (Integer.parseInt(stringStringEntry.getValue()) - 1) * Integer.parseInt(paramMap.get("pageSize"));
                        map.replace(stringStringEntry.getKey(), pageSize);
                    }
                }
                //??????????????????????????????allProvinces allCities allCounties ?????????
                //???????????????????????????????????????????????????????????????
                if ("allProvinces".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getListsForAll(map);
                    fixProvinceErrorInfo(list);
                    elascticsearchModelss.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCities".contains(paramMap.get("area"))) {
                    List<DataAnalysisCity> list = dataAnalysisCity.getListsForAll(map);
                    elascticsearchModelss.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisArea> list = dataAnalysisArea.getListsForAll(map);
                    elascticsearchModelss.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if (!"allProvinces".contains(paramMap.get("area")) && !"allCities".contains(paramMap.get("area")) && !"allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getListForAll(map);
                    List<DataAnalysisCity> list2 = dataAnalysisCity.getListForAll(map);
                    List<DataAnalysisArea> list3 = dataAnalysisArea.getListForAll(map);
                    elascticsearchModelsTemps.addAll(convertList2List(list, ElascticsearchModel.class));
                    elascticsearchModelsTemps.addAll(convertList2List(list2, ElascticsearchModel.class));
                    elascticsearchModelsTemps.addAll(convertList2List(list3, ElascticsearchModel.class));
                    elascticsearchModelss.addAll(elascticsearchModelsTemps);
                }
                //??????????????????
                List<DataKing> kingArrayList2 = new ArrayList<>();
                DataKing dataKing2;
                if (elascticsearchModelss.size() > 0) {
                    HashMap<String, Object> map3;
                    HashMap<String, Map<String, Object>> map2;
                    for (ElascticsearchModel elascticsearchModel : elascticsearchModelss) {
                        map2 = new HashMap<>();
                        //????????????
                        map3 = new LinkedHashMap<>();
                        //????????????
                        map3.put("grainYield", elascticsearchModel.getGrainYield());
                        //?????????
                        map3.put("grassValleyRatio", elascticsearchModel.getGrassValleyRatio());
                        //???????????????
                        map3.put("collectionRatio", elascticsearchModel.getCollectionRatio());
                        //????????????
                        map3.put("seedArea", elascticsearchModel.getSeedArea());
                        //????????????
                        map3.put("returnArea", elascticsearchModel.getReturnArea());
                        //?????????
                        map3.put("exportYield", elascticsearchModel.getExportYield());
                        //?????????
                        map3.put("theoryResource", elascticsearchModel.getTheoryResource());
                        //??????????????????
                        map3.put("collectResource", elascticsearchModel.getCollectResource());
                        //?????????????????????
                        map3.put("marketEnt", elascticsearchModel.getMarketEnt());
                        //?????????
                        map3.put("fertilising", elascticsearchModel.getFertilizes());
                        //?????????
                        map3.put("forage", elascticsearchModel.getFeeds());
                        //?????????
                        map3.put("fuel", elascticsearchModel.getFuelleds());
                        //?????????
                        map3.put("base", elascticsearchModel.getBaseMats());
                        //?????????
                        map3.put("material", elascticsearchModel.getMaterializations());
                        //???????????????
                        map3.put("reuse", elascticsearchModel.getReuse());
                        //?????????
                        map3.put("fertilisings", elascticsearchModel.getFertilisingd());
                        //?????????
                        map3.put("forages", elascticsearchModel.getForaged());
                        //?????????
                        map3.put("fuels", elascticsearchModel.getFueld());
                        //?????????
                        map3.put("bases", elascticsearchModel.getBased());
                        //?????????
                        map3.put("materials", elascticsearchModel.getMateriald());
                        //???????????????
                        map3.put("returnResource", elascticsearchModel.getReturnResource());
                        //?????????????????????
                        map3.put("other", elascticsearchModel.getOther());
                        //??????????????????
                        map3.put("fertilize", elascticsearchModel.getFertilize());
                        //??????????????????
                        map3.put("feed", elascticsearchModel.getFeed());
                        //??????????????????
                        map3.put("fuelled", elascticsearchModel.getFuelled());
                        //??????????????????
                        map3.put("baseMat", elascticsearchModel.getBaseMat());
                        //??????????????????
                        map3.put("materialization", elascticsearchModel.getMaterialization());
                        //???????????????=???????????????
                        //???????????????
                        map3.put("totol", elascticsearchModel.getStrawUtilization());
                        //???????????????
                        map3.put("totolRate", elascticsearchModel.getTotolRate());
                        //????????????????????????
                        map3.put("comprUtilIndex", elascticsearchModel.getComprUtilIndex());
                        //???????????????????????????
                        map3.put("induUtilIndex", elascticsearchModel.getInduUtilIndex());
                        map2.put(elascticsearchModel.getYear(), map3);
                        dataKing2 = new DataKing();
                        if (!elascticsearchModel.getAreaId().equals("0")) {
                            dataKing2.setId(elascticsearchModel.getAreaId());
                        }
                        if (!elascticsearchModel.getCityId().equals("0")) {
                            dataKing2.setId(elascticsearchModel.getCityId());
                        }
                        if (!elascticsearchModel.getProviceId().equals("0")) {
                            dataKing2.setId(elascticsearchModel.getProviceId());
                        }
                        dataKing2.setArea_Name(elascticsearchModel.getAreaName());
                        dataKing2.setGtime(elascticsearchModel.getYear());
                        dataKing2.setStrawName(elascticsearchModel.getStrawName());
                        dataKing2.setIndicatorArray(map2);
                        kingArrayList2.add(dataKing2);
                    }
                }
                //HHHH
                if (paramMap.get("area").contains("six")) {
                    for (DataKing king : kingArrayList2) {
                        king.setArea_Name(SixRegionEnum.getByStrawTypeEnglish(king.getId()));
                    }
                    Map<String, List<DataKing>> collect = kingArrayList2.stream().collect(Collectors.groupingBy(e -> fetchGroupKeyByArea2(e)));
                    DataKing king;
                    HashMap<String, Map<String, Object>> mapFinally;
                    ArrayList<DataKing> objects = new ArrayList<>();
                    for (List<DataKing> value : collect.values()) {
                        if (value.size() > 1) {
                            king = new DataKing();
                            //??????????????????????????????????????????????????????????????????????????????
//                        HashMap<String, Map<String, Object>> map = new HashMap<>();
                            //????????????
                            ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                            Map<String, Object> result1 = new LinkedHashMap<String, Object>();
                            for (DataKing dataKing1 : value) {
                                king.setId(dataKing1.getId());
                                king.setArea_Name(dataKing1.getArea_Name());
                                king.setGtime(dataKing1.getGtime());
                                king.setStrawName("????????????");
                                indexMaps.addAll(dataKing1.getIndicatorArray().values());
                            }
                            for (Map<String, Object> mapss : indexMaps) {
                                Set<String> strings = mapss.keySet();
                                for (String string : strings) {
                                    String id = string;
                                    BigDecimal values = new BigDecimal(0);
                                    if (!org.springframework.util.StringUtils.isEmpty(mapss.get(string))) {
                                        if (string.equals("grassValleyRatio") || string.equals("collectionRatio")) {
                                            result1.put(id, "------");
                                        } else {
                                            if (mapss.get(string).toString().contains("%")) {
                                                values = new BigDecimal(mapss.get(string).toString().replaceAll("%", ""));
                                            } else {
                                                values = new BigDecimal(mapss.get(string).toString());
                                            }
                                            if (result1.containsKey(id)) {
                                                BigDecimal temp = new BigDecimal(0);
                                                if (result1.get(id).toString().contains("%")) {
                                                    temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                                } else {
                                                    temp = new BigDecimal(result1.get(id).toString());
                                                }
                                                values = values.add(temp);
                                            }
                                            result1.put(id, values);
                                        }
                                    }
                                }
                            }
                            //???????????????????????????????????????????????????????????????
                            //???????????????
                            BigDecimal totol = new BigDecimal(0);
                            //????????????
                            BigDecimal collectResource = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal marketEnt = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal reuse = new BigDecimal(0);
                            //???????????????
                            BigDecimal returnResource = new BigDecimal(0);
                            //??????
                            BigDecimal heJi = new BigDecimal(0);

                            totol = (BigDecimal) result1.get("totol");
                            collectResource = (BigDecimal) result1.get("collectResource");
                            marketEnt = (BigDecimal) result1.get("marketEnt");
                            reuse = (BigDecimal) result1.get("reuse");
                            returnResource = (BigDecimal) result1.get("returnResource");
                            heJi = marketEnt.add(reuse).add(returnResource);
                            for (String s : result1.keySet()) {
                                if (s.equals("totolRate")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                                    }
                                } else if (s.equals("comprUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, heJi.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                } else if (s.equals("induUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                }
                            }
                            mapFinally = new HashMap<>();
                            mapFinally.put(king.getGtime(), result1);
                            king.setIndicatorArray(mapFinally);
                            value.clear();
                            value.add(king);
                        }
                    }
                    for (List<DataKing> value : collect.values()) {
                        for (DataKing dataKing1 : value) {
                            objects.add(dataKing1);
                        }
                    }
                    Map<String, List<DataKing>> collect1 = objects.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    objects.clear();
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collect1.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps = new LinkedHashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                            dataKingFinally.setIndicatorArrays(hashMaps);
                        }
                        objects.add(dataKingFinally);
                    }
                    List<DataKing> collectOne = objects.stream().sorted(Comparator.comparing(DataKing::getId).thenComparing(DataKing::getStrawName, Comparator.reverseOrder())).collect(Collectors.toList());
                    if (collectOne != null && collectOne.size() > 0) {
                        collectOne = groupAreaIdAndSort(collectOne);
                    }
                    countPage = (int) Math.ceil(Double.parseDouble(String.valueOf(objects.size() / Double.valueOf(paramMap.get("pageSize")))));
                    //????????????????????????
                    PageUtils pageUtils = new PageUtils();
                    List<DataKing> dataKings = page2(collectOne, Integer.parseInt(paramMap.get("pageSize")), Integer.parseInt(paramMap.get("pageNum")));
                    pageUtils.setList(dataKings);
                    //?????????
                    pageUtils.setCurrPage(Integer.parseInt(paramMap.get("pageNum")));
                    //??????????????????
                    pageUtils.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
                    //?????????
                    pageUtils.setTotalCount(objects.size());
                    //?????????
                    pageUtils.setTotalPage(countPage);
                    return pageUtils;
                } else {
                    Map<String, List<DataKing>> collect = kingArrayList2.stream().collect(Collectors.groupingBy(e -> fetchGroupKeyByArea2(e)));
                    DataKing king;
                    HashMap<String, Map<String, Object>> mapFinally;
                    for (List<DataKing> value : collect.values()) {
                        if (value.size() > 1) {
                            king = new DataKing();
                            //??????????????????????????????????????????????????????????????????????????????
//                        HashMap<String, Map<String, Object>> map = new HashMap<>();
                            //????????????
                            ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                            Map<String, Object> result1 = new LinkedHashMap<String, Object>();
                            for (DataKing dataKing1 : value) {
                                king.setId(dataKing1.getId());
                                king.setArea_Name(dataKing1.getArea_Name());
                                king.setGtime(dataKing1.getGtime());
                                king.setStrawName("????????????");
                                indexMaps.addAll(dataKing1.getIndicatorArray().values());
                            }
                            for (Map<String, Object> mapss : indexMaps) {
                                Set<String> strings = mapss.keySet();
                                for (String string : strings) {
                                    String id = string;
                                    BigDecimal values = new BigDecimal(0);
                                    if (!org.springframework.util.StringUtils.isEmpty(mapss.get(string))) {
                                        if (string.equals("grassValleyRatio") || string.equals("collectionRatio")) {
                                            result1.put(id, "------");
                                        } else {
                                            if (mapss.get(string).toString().contains("%")) {
                                                values = new BigDecimal(mapss.get(string).toString().replaceAll("%", ""));
                                            } else {
                                                values = new BigDecimal(mapss.get(string).toString());
                                            }
                                            if (result1.containsKey(id)) {
                                                BigDecimal temp = new BigDecimal(0);
                                                if (result1.get(id).toString().contains("%")) {
                                                    temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                                } else {
                                                    temp = new BigDecimal(result1.get(id).toString());
                                                }
                                                values = values.add(temp);
                                            }
                                            result1.put(id, values);
                                        }
                                    }
                                }
                            }
                            //???????????????????????????????????????????????????????????????
                            //???????????????
                            BigDecimal totol = new BigDecimal(0);
                            //????????????
                            BigDecimal collectResource = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal marketEnt = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal reuse = new BigDecimal(0);
                            //???????????????
                            BigDecimal returnResource = new BigDecimal(0);
                            //??????
                            BigDecimal heJi = new BigDecimal(0);

                            totol = (BigDecimal) result1.get("totol");
                            collectResource = (BigDecimal) result1.get("collectResource");
                            marketEnt = (BigDecimal) result1.get("marketEnt");
                            reuse = (BigDecimal) result1.get("reuse");
                            returnResource = (BigDecimal) result1.get("returnResource");
                            heJi = marketEnt.add(reuse).add(returnResource);
                            for (String s : result1.keySet()) {
                                if (s.equals("totolRate")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                                    }
                                } else if (s.equals("comprUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, heJi.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                } else if (s.equals("induUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                }
                            }
                            mapFinally = new HashMap<>();
                            mapFinally.put(king.getGtime(), result1);
                            king.setIndicatorArray(mapFinally);
                            value.clear();
                            value.add(king);
                        }
                    }
                    List<DataKing> objects = new ArrayList<>();
                    for (List<DataKing> value : collect.values()) {
                        for (DataKing dataKing1 : value) {
                            objects.add(dataKing1);
                        }
                    }
                    //??????
                    Map<String, List<DataKing>> collects = objects.stream().collect(Collectors.groupingBy(e -> fetchGroupAreaId(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    objects.clear();
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collects.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        dataKingFinally.setId(stringListEntry.getKey().split("#")[2]);
                        hashMaps = new LinkedHashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                        }
                        dataKingFinally.setIndicatorArrays(hashMaps);
                        objects.add(dataKingFinally);
                    }
                    countPage = (int) Math.ceil(Double.parseDouble(String.valueOf(objects.size() / Double.valueOf(paramMap.get("pageSize")))));
                    //????????????????????????
                    PageUtils pageUtils = new PageUtils();
                    objects = objects.stream().sorted(Comparator.comparing(DataKing::getId)).collect(Collectors.toList());
                    List<DataKing> dataKings = page2(objects, Integer.parseInt(paramMap.get("pageSize")), Integer.parseInt(paramMap.get("pageNum")));
                    pageUtils.setList(dataKings);
                    //?????????
                    pageUtils.setCurrPage(Integer.parseInt(paramMap.get("pageNum")));
                    //??????????????????
                    pageUtils.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
                    //?????????
                    pageUtils.setTotalCount(objects.size());
                    //?????????
                    pageUtils.setTotalPage(countPage);
                    return pageUtils;
                }
            }

            //????????????????????????+??????????????????22222???
            if (paramMap.get("allCropType").contains("2")) {
                //?????????
                Integer count = 0;
                Integer countPage = 0;
                ArrayList<DataKing> objects = new ArrayList<>();
                HashMap<String, Object> map = new HashMap<>();
                for (Map.Entry<String, String> stringStringEntry : paramMap.entrySet()) {
                    if (stringStringEntry.getKey().equals("pageNum") || stringStringEntry.getKey().equals("pageSize")) {
                        map.put(stringStringEntry.getKey(), Integer.parseInt(stringStringEntry.getValue()));
                    } else {
                        map.put(stringStringEntry.getKey(), stringStringEntry.getValue().split(","));
                    }
                    if (stringStringEntry.getKey().equals("pageNum")) {
                        int pageSize = (Integer.parseInt(stringStringEntry.getValue()) - 1) * Integer.parseInt(paramMap.get("pageSize"));
                        map.replace(stringStringEntry.getKey(), pageSize);
                    }
                }
                //????????????????????????????????????
                ArrayList<ElascticsearchModel> elascticsearchModelss = new ArrayList<>();
                ArrayList<ElascticsearchModel> elascticsearchModelsTemps = new ArrayList<>();
                //??????????????????????????????allProvinces allCities allCounties ?????????
                //???????????????????????????????????????????????????????????????
                if ("allProvinces".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getListsForAll(map);

                    fixProvinceErrorInfo(list);

                    elascticsearchModelss.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCities".contains(paramMap.get("area"))) {
                    List<DataAnalysisCity> list = dataAnalysisCity.getListsForAll(map);
                    elascticsearchModelss.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisArea> list = dataAnalysisArea.getListsForAll(map);
                    elascticsearchModelss.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if (!"allProvinces".contains(paramMap.get("area")) && !"allCities".contains(paramMap.get("area")) && !"allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getListForAll(map);
                    //??????????????????
                    List<DataAnalysisCity> list2 = dataAnalysisCity.getListForAll(map);
                    List<DataAnalysisArea> list3 = dataAnalysisArea.getListForAll(map);
                    elascticsearchModelsTemps.addAll(convertList2List(list, ElascticsearchModel.class));
                    elascticsearchModelsTemps.addAll(convertList2List(list2, ElascticsearchModel.class));
                    elascticsearchModelsTemps.addAll(convertList2List(list3, ElascticsearchModel.class));
                    elascticsearchModelss.addAll(elascticsearchModelsTemps);
                }
                //??????????????????
                ArrayList<DataKing> kingArrayList2 = new ArrayList<>();
                DataKing dataKing2;
                if (elascticsearchModelss.size() > 0) {
                    LinkedHashMap<String, Object> map3;
                    HashMap<String, Map<String, Object>> map2;
                    for (ElascticsearchModel elascticsearchModel : elascticsearchModelss) {
                        map2 = new HashMap<>();
                        //????????????
                        map3 = new LinkedHashMap<>();
                        //????????????
                        map3.put("grainYield", elascticsearchModel.getGrainYield());
                        //?????????
                        map3.put("grassValleyRatio", elascticsearchModel.getGrassValleyRatio());
                        //???????????????
                        map3.put("collectionRatio", elascticsearchModel.getCollectionRatio());
                        //????????????
                        map3.put("seedArea", elascticsearchModel.getSeedArea());
                        //????????????
                        map3.put("returnArea", elascticsearchModel.getReturnArea());
                        //?????????
                        map3.put("exportYield", elascticsearchModel.getExportYield());
                        //?????????
                        map3.put("theoryResource", elascticsearchModel.getTheoryResource());
                        //??????????????????
                        map3.put("collectResource", elascticsearchModel.getCollectResource());
                        //?????????????????????
                        map3.put("marketEnt", elascticsearchModel.getMarketEnt());
                        //?????????
                        map3.put("fertilising", elascticsearchModel.getFertilizes());
                        //?????????
                        map3.put("forage", elascticsearchModel.getFeeds());
                        //?????????
                        map3.put("fuel", elascticsearchModel.getFuelleds());
                        //?????????
                        map3.put("base", elascticsearchModel.getBaseMats());
                        //?????????
                        map3.put("material", elascticsearchModel.getMaterializations());
                        //???????????????
                        map3.put("reuse", elascticsearchModel.getReuse());
                        //?????????
                        map3.put("fertilisings", elascticsearchModel.getFertilisingd());
                        //?????????
                        map3.put("forages", elascticsearchModel.getForaged());
                        //?????????
                        map3.put("fuels", elascticsearchModel.getFueld());
                        //?????????
                        map3.put("bases", elascticsearchModel.getBased());
                        //?????????
                        map3.put("materials", elascticsearchModel.getMateriald());
                        //???????????????
                        map3.put("returnResource", elascticsearchModel.getReturnResource());
                        //?????????????????????
                        map3.put("other", elascticsearchModel.getOther());
                        //??????????????????
                        map3.put("fertilize", elascticsearchModel.getFertilize());
                        //??????????????????
                        map3.put("feed", elascticsearchModel.getFeed());
                        //??????????????????
                        map3.put("fuelled", elascticsearchModel.getFuelled());
                        //??????????????????
                        map3.put("baseMat", elascticsearchModel.getBaseMat());
                        //??????????????????
                        map3.put("materialization", elascticsearchModel.getMaterialization());
                        //???????????????=???????????????
                        //???????????????
                        map3.put("totol", elascticsearchModel.getStrawUtilization());
                        //???????????????
                        map3.put("totolRate", elascticsearchModel.getTotolRate());
                        //????????????????????????
                        map3.put("comprUtilIndex", elascticsearchModel.getComprUtilIndex());
                        //???????????????????????????
                        map3.put("induUtilIndex", elascticsearchModel.getInduUtilIndex());
                        map2.put(elascticsearchModel.getYear(), map3);
                        dataKing2 = new DataKing();
                        if (!elascticsearchModel.getAreaId().equals("0")) {
                            dataKing2.setId(elascticsearchModel.getAreaId());
                        }
                        if (!elascticsearchModel.getCityId().equals("0")) {
                            dataKing2.setId(elascticsearchModel.getCityId());
                        }
                        if (!elascticsearchModel.getProviceId().equals("0")) {
                            dataKing2.setId(elascticsearchModel.getProviceId());
                        }
                        dataKing2.setArea_Name(elascticsearchModel.getAreaName());
                        dataKing2.setGtime(elascticsearchModel.getYear());
                        dataKing2.setStrawName(elascticsearchModel.getStrawName());
                        dataKing2.setIndicatorArray(map2);
                        kingArrayList2.add(dataKing2);
                    }
                }
                if (paramMap.get("area").contains("six")) {
                    for (DataKing king : kingArrayList2) {
                        king.setArea_Name(SixRegionEnum.getByStrawTypeEnglish(king.getId()));
                    }
                    Map<String, List<DataKing>> collect = kingArrayList2.stream().collect(Collectors.groupingBy(e -> fetchGroupKeyByArea2(e)));
                    DataKing king;
                    HashMap<String, Map<String, Object>> mapFinally;
                    for (List<DataKing> value : collect.values()) {
                        if (value.size() > 1) {
                            king = new DataKing();
                            //??????????????????????????????????????????????????????????????????????????????
//                        HashMap<String, Map<String, Object>> map = new HashMap<>();
                            //????????????
                            ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                            Map<String, Object> result1 = new HashMap<String, Object>();
                            for (DataKing dataKing1 : value) {
                                king.setId(dataKing1.getId());
                                king.setArea_Name(dataKing1.getArea_Name());
                                king.setGtime(dataKing1.getGtime());
                                king.setStrawName("????????????");
                                indexMaps.addAll(dataKing1.getIndicatorArray().values());
                            }
                            for (Map<String, Object> mapss : indexMaps) {
                                Set<String> strings = mapss.keySet();
                                for (String string : strings) {
                                    String id = string;
                                    BigDecimal values = new BigDecimal(0);
                                    if (!org.springframework.util.StringUtils.isEmpty(mapss.get(string))) {
                                        if (string.equals("grassValleyRatio") || string.equals("collectionRatio")) {
                                            result1.put(id, "------");
                                        } else {
                                            if (mapss.get(string).toString().contains("%")) {
                                                values = new BigDecimal(mapss.get(string).toString().replaceAll("%", ""));
                                            } else {
                                                values = new BigDecimal(mapss.get(string).toString());
                                            }
                                            if (result1.containsKey(id)) {
                                                BigDecimal temp = new BigDecimal(0);
                                                if (result1.get(id).toString().contains("%")) {
                                                    temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                                } else {
                                                    temp = new BigDecimal(result1.get(id).toString());
                                                }
                                                values = values.add(temp);
                                            }
                                            result1.put(id, values);
                                        }
                                    }
                                }
                            }
                            //???????????????????????????????????????????????????????????????
                            //???????????????
                            BigDecimal totol = new BigDecimal(0);
                            //????????????
                            BigDecimal collectResource = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal marketEnt = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal reuse = new BigDecimal(0);
                            //???????????????
                            BigDecimal returnResource = new BigDecimal(0);
                            //??????
                            BigDecimal heJi = new BigDecimal(0);

                            totol = (BigDecimal) result1.get("totol");
                            collectResource = (BigDecimal) result1.get("collectResource");
                            marketEnt = (BigDecimal) result1.get("marketEnt");
                            reuse = (BigDecimal) result1.get("reuse");
                            returnResource = (BigDecimal) result1.get("returnResource");
                            heJi = marketEnt.add(reuse).add(returnResource);
                            for (String s : result1.keySet()) {
                                if (s.equals("totolRate")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                                    }
                                } else if (s.equals("comprUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, heJi.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                } else if (s.equals("induUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                }
                            }
                            mapFinally = new HashMap<>();
                            mapFinally.put(king.getGtime(), result1);
                            king.setIndicatorArray(mapFinally);
                            value.clear();
                            value.add(king);
                        }
                    }
                    for (List<DataKing> value : collect.values()) {
                        for (DataKing dataKing1 : value) {
                            objects.add(dataKing1);
                        }
                    }
                    Map<String, List<DataKing>> collect1 = objects.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    objects.clear();
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collect1.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps = new HashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                            dataKingFinally.setIndicatorArrays(hashMaps);
                        }
                        objects.add(dataKingFinally);
                    }
                } else {
                    Map<String, List<DataKing>> collect = kingArrayList2.stream().collect(Collectors.groupingBy(e -> fetchGroupKeyByArea(e)));
                    DataKing king;
                    HashMap<String, Map<String, Object>> mapFinally;
                    for (List<DataKing> value : collect.values()) {
                        if (value.size() > 1) {
                            king = new DataKing();
                            //??????????????????????????????????????????????????????????????????????????????
//                        HashMap<String, Map<String, Object>> map = new HashMap<>();
                            //????????????
                            ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                            Map<String, Object> result1 = new LinkedHashMap<>();
                            for (DataKing dataKing1 : value) {
                                king.setId(dataKing1.getId());
                                king.setArea_Name(dataKing1.getArea_Name());
                                king.setGtime(dataKing1.getGtime());
                                king.setStrawName("????????????");
                                indexMaps.addAll(dataKing1.getIndicatorArray().values());
                            }
                            for (Map<String, Object> mapss : indexMaps) {
                                Set<String> strings = mapss.keySet();
                                for (String string : strings) {
                                    String id = string;
                                    BigDecimal values = new BigDecimal(0);
                                    if (!org.springframework.util.StringUtils.isEmpty(mapss.get(string))) {
                                        if (string.equals("grassValleyRatio") || string.equals("collectionRatio")) {
                                            result1.put(id, "------");
                                        } else {
                                            if (mapss.get(string).toString().contains("%")) {
                                                values = new BigDecimal(mapss.get(string).toString().replaceAll("%", ""));
                                            } else {
                                                values = new BigDecimal(mapss.get(string).toString());
                                            }
                                            if (result1.containsKey(id)) {
                                                BigDecimal temp = new BigDecimal(0);
                                                if (result1.get(id).toString().contains("%")) {
                                                    temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                                } else {
                                                    temp = new BigDecimal(result1.get(id).toString());
                                                }
                                                values = values.add(temp);
                                            }
                                            result1.put(id, values);
                                        }
                                    }
                                }
                            }
                            //???????????????????????????????????????????????????????????????
                            //???????????????
                            BigDecimal totol = new BigDecimal(0);
                            //????????????
                            BigDecimal collectResource = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal marketEnt = new BigDecimal(0);
                            //?????????????????????
                            BigDecimal reuse = new BigDecimal(0);
                            //???????????????
                            BigDecimal returnResource = new BigDecimal(0);
                            //??????
                            BigDecimal heJi = new BigDecimal(0);

                            totol = (BigDecimal) result1.get("totol");
                            collectResource = (BigDecimal) result1.get("collectResource");
                            marketEnt = (BigDecimal) result1.get("marketEnt");
                            reuse = (BigDecimal) result1.get("reuse");
                            returnResource = (BigDecimal) result1.get("returnResource");
                            heJi = marketEnt.add(reuse).add(returnResource);
                            for (String s : result1.keySet()) {
                                if (s.equals("totolRate")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, totol.divide(collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
                                    }
                                } else if (s.equals("comprUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, heJi.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                } else if (s.equals("induUtilIndex")) {
                                    if (collectResource.compareTo(new BigDecimal(0)) == 0) {
                                        result1.put(s, new BigDecimal(0));
                                    } else {
                                        result1.put(s, marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP));
                                    }
                                }
                            }
                            mapFinally = new HashMap<>();
                            mapFinally.put(king.getGtime(), result1);
                            king.setIndicatorArrays(mapFinally);
                            value.clear();
                            value.add(king);
                        }
                    }
                    //???????????????list?????????
                    ArrayList<DataKing> newArraylist = new ArrayList<>();
                    for (List<DataKing> value : collect.values()) {
                        newArraylist.addAll(value);
                    }
                    Map<String, List<DataKing>> collectss = newArraylist.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally2;
                    HashMap<String, Map<String, Object>> hashMaps2;
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collectss.entrySet()) {
                        dataKingFinally2 = new DataKing();
                        dataKingFinally2.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally2.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally2.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps2 = new LinkedHashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArrays();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps2.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                        }
                        dataKingFinally2.setIndicatorArrays(hashMaps2);
                        objects.add(dataKingFinally2);
                    }
                }
                //?????????????????????
                ArrayList<ElascticsearchModel> elascticsearchModels = new ArrayList<>();
                ArrayList<ElascticsearchModel> elascticsearchModelsTemp = new ArrayList<>();
                //??????????????????????????????allProvinces allCities allCounties ?????????
                //???????????????????????????????????????????????????????????????
                if ("allProvinces".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getListByNoLimit(map);
                    elascticsearchModels.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCities".contains(paramMap.get("area"))) {
                    List<DataAnalysisCity> list = dataAnalysisCity.getListByNoLimit(map);
                    elascticsearchModels.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if ("allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisArea> list = dataAnalysisArea.getListByNoLimit(map);
                    elascticsearchModels.addAll(convertList2List(list, ElascticsearchModel.class));
                }
                if (!"allProvinces".contains(paramMap.get("area")) && !"allCities".contains(paramMap.get("area")) && !"allCounties".contains(paramMap.get("area"))) {
                    List<DataAnalysisProvice> list = dataAnalysisProvice.getList(map);
                    //??????????????????????????????????????????????????????
                    changeErrorInfo(list);
                    List<DataAnalysisCity> list2 = dataAnalysisCity.getList(map);
                    List<DataAnalysisArea> list3 = dataAnalysisArea.getList(map);
                    elascticsearchModelsTemp.addAll(convertList2List(list, ElascticsearchModel.class));
                    elascticsearchModelsTemp.addAll(convertList2List(list2, ElascticsearchModel.class));
                    elascticsearchModelsTemp.addAll(convertList2List(list3, ElascticsearchModel.class));
                    elascticsearchModels.addAll(elascticsearchModelsTemp);
                }
                //??????????????????
                ArrayList<DataKing> kingArrayList = new ArrayList<>();
                DataKing dataKing;
                if (elascticsearchModels.size() > 0) {
                    HashMap<String, Object> map3;
                    HashMap<String, Map<String, Object>> map2;
                    for (ElascticsearchModel elascticsearchModel : elascticsearchModels) {
                        map2 = new HashMap<>();
                        //????????????
                        map3 = new LinkedHashMap<>();
                        //????????????
                        map3.put("grainYield", elascticsearchModel.getGrainYield());
                        //?????????
                        if (elascticsearchModel.getAreaName().split("/").length == 2 || elascticsearchModel.getAreaName().split("/").length == 3) {
                            map3.put("grassValleyRatio", "------");
                        } else {
                            map3.put("grassValleyRatio", elascticsearchModel.getGrassValleyRatio());
                        }
                        //???????????????
                        if (elascticsearchModel.getAreaName().split("/").length == 2 || elascticsearchModel.getAreaName().split("/").length == 3) {
                            map3.put("collectionRatio", "------");
                        } else {
                            map3.put("collectionRatio", elascticsearchModel.getCollectionRatio());
                        }
                        //????????????
                        map3.put("seedArea", elascticsearchModel.getSeedArea());
                        //????????????
                        map3.put("returnArea", elascticsearchModel.getReturnArea());
                        //?????????
                        map3.put("exportYield", elascticsearchModel.getExportYield());
                        //?????????
                        map3.put("theoryResource", elascticsearchModel.getTheoryResource());
                        //??????????????????
                        map3.put("collectResource", elascticsearchModel.getCollectResource());
                        //?????????????????????
                        map3.put("marketEnt", elascticsearchModel.getMarketEnt());
                        //?????????
                        map3.put("fertilising", elascticsearchModel.getFertilizes());
                        //?????????
                        map3.put("forage", elascticsearchModel.getFeeds());
                        //?????????
                        map3.put("fuel", elascticsearchModel.getFuelleds());
                        //?????????
                        map3.put("base", elascticsearchModel.getBaseMats());
                        //?????????
                        map3.put("material", elascticsearchModel.getMaterializations());
                        //???????????????
                        map3.put("reuse", elascticsearchModel.getReuse());
                        //?????????
                        map3.put("fertilisings", elascticsearchModel.getFertilisingd());
                        //?????????
                        map3.put("forages", elascticsearchModel.getForaged());
                        //?????????
                        map3.put("fuels", elascticsearchModel.getFueld());
                        //?????????
                        map3.put("bases", elascticsearchModel.getBased());
                        //?????????
                        map3.put("materials", elascticsearchModel.getMateriald());
                        //???????????????
                        map3.put("returnResource", elascticsearchModel.getReturnResource());
                        //?????????????????????
                        map3.put("other", elascticsearchModel.getOther());
                        //??????????????????
                        map3.put("fertilize", elascticsearchModel.getFertilize());
                        //??????????????????
                        map3.put("feed", elascticsearchModel.getFeed());
                        //??????????????????
                        map3.put("fuelled", elascticsearchModel.getFuelled());
                        //??????????????????
                        map3.put("baseMat", elascticsearchModel.getBaseMat());
                        //??????????????????
                        map3.put("materialization", elascticsearchModel.getMaterialization());
                        //???????????????=???????????????
                        //???????????????
                        map3.put("totol", elascticsearchModel.getStrawUtilization());
                        //???????????????
                        map3.put("totolRate", elascticsearchModel.getTotolRate());
                        //????????????????????????
                        map3.put("comprUtilIndex", elascticsearchModel.getComprUtilIndex());
                        //???????????????????????????
                        map3.put("induUtilIndex", elascticsearchModel.getInduUtilIndex());
                        map2.put(elascticsearchModel.getYear(), map3);
                        dataKing = new DataKing();
                        if (!elascticsearchModel.getAreaId().equals("0")) {
                            dataKing.setId(elascticsearchModel.getAreaId());
                        }
                        if (!elascticsearchModel.getCityId().equals("0")) {
                            dataKing.setId(elascticsearchModel.getCityId());
                        }
                        if (!elascticsearchModel.getProviceId().equals("0")) {
                            dataKing.setId(elascticsearchModel.getProviceId());
                        }
                        dataKing.setArea_Name(elascticsearchModel.getAreaName());
                        dataKing.setGtime(elascticsearchModel.getYear());
                        dataKing.setStrawName(elascticsearchModel.getStrawName());
                        dataKing.setIndicatorArray(map2);
                        kingArrayList.add(dataKing);
                    }
                }
                if (paramMap.get("area").contains("six")) {
                    for (DataKing king : kingArrayList) {
                        king.setArea_Name(SixRegionEnum.getByStrawTypeEnglish(king.getId()));
                    }
                    Map<String, List<DataKing>> collects = kingArrayList.stream().collect(Collectors.groupingBy(e -> fetchGroupKeysByYear(e)));
                    DataKing king;
                    HashMap<String, Map<String, Object>> mapFinally;
                    for (List<DataKing> value : collects.values()) {
                        if (value.size() > 1) {
                            king = new DataKing();
                            //??????????????????????????????????????????????????????????????????????????????
//                        HashMap<String, Map<String, Object>> map = new HashMap<>();
                            //????????????
                            ArrayList<Map<String, Object>> indexMaps = new ArrayList<>();
                            Map<String, Object> result1 = new LinkedHashMap<String, Object>();
                            for (DataKing dataKing1 : value) {
                                king.setId(dataKing1.getId());
                                king.setArea_Name(dataKing1.getArea_Name());
                                king.setGtime(dataKing1.getGtime());
                                king.setStrawName(dataKing1.getStrawName());
                                indexMaps.addAll(dataKing1.getIndicatorArray().values());
                            }
                            for (Map<String, Object> mapss : indexMaps) {
                                Set<String> strings = mapss.keySet();
                                for (String string : strings) {
                                    String id = string;
                                    BigDecimal values = new BigDecimal(0);
                                    if (!org.springframework.util.StringUtils.isEmpty(mapss.get(string))) {
                                        if (string.equals("grassValleyRatio") || string.equals("collectionRatio")) {
                                            result1.put(id, "------");
                                        } else {
                                            if (mapss.get(string).toString().contains("%")) {
                                                values = new BigDecimal(mapss.get(string).toString().replaceAll("%", ""));
                                            } else {
                                                values = new BigDecimal(mapss.get(string).toString());
                                            }
                                            if (result1.containsKey(id)) {
                                                BigDecimal temp = new BigDecimal(0);
                                                if (result1.get(id).toString().contains("%")) {
                                                    temp = new BigDecimal(result1.get(id).toString().replaceAll("%", ""));
                                                } else {
                                                    temp = new BigDecimal(result1.get(id).toString());
                                                }
                                                values = values.add(temp);
                                            }
                                            result1.put(id, values);
                                        }
                                    }
                                }
                            }
                            mapFinally = new HashMap<>();
                            mapFinally.put(king.getGtime(), result1);
                            king.setIndicatorArray(mapFinally);
                            value.clear();
                            value.add(king);
                        }
                    }
                    for (List<DataKing> value : collects.values()) {
                        for (DataKing dataKing1 : value) {
                            objects.add(dataKing1);
                        }
                    }
                    Map<String, List<DataKing>> collect1 = objects.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    objects.clear();
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collect1.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps = new LinkedHashMap<>();
                        if (dataKingFinally.getStrawName().equals("????????????")) {
                            for (DataKing dataKing1 : stringListEntry.getValue()) {
                                HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArrays();
                                for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                    hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                                }
                                dataKingFinally.setIndicatorArrays(hashMaps);
                            }
                        } else {
                            for (DataKing dataKing1 : stringListEntry.getValue()) {
                                HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                                for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                    hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                                }
                                dataKingFinally.setIndicatorArrays(hashMaps);
                            }
                        }
                        objects.add(dataKingFinally);
                    }
                } else {
                    Map<String, List<DataKing>> collects = kingArrayList.stream().collect(Collectors.groupingBy(e -> fetchGroupKeys(e)));
                    DataKing dataKingFinally;
                    HashMap<String, Map<String, Object>> hashMaps;
                    for (Map.Entry<String, List<DataKing>> stringListEntry : collects.entrySet()) {
                        dataKingFinally = new DataKing();
                        dataKingFinally.setId(stringListEntry.getValue().get(0).getId());
                        dataKingFinally.setArea_Name(stringListEntry.getKey().split("#")[0]);
                        dataKingFinally.setStrawName(stringListEntry.getKey().split("#")[1]);
                        hashMaps = new LinkedHashMap<>();
                        for (DataKing dataKing1 : stringListEntry.getValue()) {
                            HashMap<String, Map<String, Object>> indicatorArray = dataKing1.getIndicatorArray();
                            for (Map.Entry<String, Map<String, Object>> stringMapEntry : indicatorArray.entrySet()) {
                                hashMaps.put(stringMapEntry.getKey(), stringMapEntry.getValue());
                            }
                        }
                        dataKingFinally.setIndicatorArrays(hashMaps);
                        objects.add(dataKingFinally);
                    }
                }
                List<DataKing> collect1 = objects.stream().sorted(Comparator.comparing(DataKing::getId).thenComparing(DataKing::getId, Comparator.naturalOrder())).collect(Collectors.toList());
                if (paramMap.get("area") != null && paramMap.get("area").indexOf("six") != -1) {
                    collect1 = this.groupRegionAndSort(collect1);
                } else {
                    collect1 = groupAreaIdAndSort(collect1);
                    collect1 = collect1.stream().sorted(Comparator.comparing(DataKing::getId)).collect(Collectors.toList());
                }
                //????????????????????????
                count = collect1.size();
                countPage = (int) Math.ceil(Double.parseDouble(String.valueOf(count / Double.valueOf(paramMap.get("pageSize")))));
                PageUtils pageUtils = new PageUtils();
                List<DataKing> dataKings = page2(collect1, Integer.parseInt(paramMap.get("pageSize")), Integer.parseInt(paramMap.get("pageNum")));
                pageUtils.setList(dataKings);
                //?????????
                pageUtils.setCurrPage(Integer.parseInt(paramMap.get("pageNum")));
                //??????????????????
                pageUtils.setPageSize(Integer.parseInt(paramMap.get("pageSize")));
                //?????????
                pageUtils.setTotalCount(count);
                //?????????
                pageUtils.setTotalPage(countPage);
                return pageUtils;
            }
        }
        return null;
    }

    private void fixProvinceErrorInfo(List<DataAnalysisProvice> list) {
        Map<String, String> areagMap = new HashMap<>(); //????????????map
        //???????????????????????????areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (regionTree.size() > 0) {
            for (SysRegionTreeVo treeDatum : regionTree) {
                areagMap.put(treeDatum.getRegionCode(), treeDatum.getRegionName());
            }
        } else {
            throw new SofnException("??????????????????");
        }

        List<SysDictionary> sysDictionaries = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>();//????????????map
        for (SysDictionary sysDict : sysDictionaries) {
            strawTypeMap.put(sysDict.getDictKey(), sysDict.getDictValue());
        }

        for (DataAnalysisProvice dataAnalysisProvice : list) {
            //?????????????????????????????????????????????
            String provinceId = dataAnalysisProvice.getProviceId();
            dataAnalysisProvice.setAreaName("/" + areagMap.get(provinceId));
            dataAnalysisProvice.setStrawName(strawTypeMap.get(dataAnalysisProvice.getStrawType()));
        }
    }

    /**
     * (?????????)????????????????????????
     *
     * @param list
     */
    private void changeErrorInfo(List<DataAnalysisProvice> list) {
        StringBuffer buffer = new StringBuffer();
        for (SixRegionEnum value : SixRegionEnum.values()) {
            buffer.append(value.getCode()).append(",");
        }
        String areaCodes = buffer.substring(0, buffer.length() - 1);
        Map<String, String> stringMap = sysApi.getRegionNameMapsByCodes(areaCodes, null);
        //????????????
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>();
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        for (DataAnalysisProvice dataAnalysisProvice : list) {
            //???????????????????????????
            String provinceId = dataAnalysisProvice.getProviceId();
            dataAnalysisProvice.setAreaName("/" + stringMap.get(provinceId));
            dataAnalysisProvice.setStrawName(strawTypeMap.get(dataAnalysisProvice.getStrawType()));
        }
    }


    /**
     * ??????????????????14?????????????????????
     *
     * @param dataKing
     */
    private List<DataKing> groupRegionAndSort(List<DataKing> dataKing) {
        //????????????list
        List<DataKingChanVo> listAll = new ArrayList<>();
        //?????????????????????????????????????????????????????????
        Map<String, List<DataKing>> map = dataKing.stream().collect(Collectors.groupingBy((DataKing::getArea_Name)));
        if (map.get(SixRegionEnum.NORTH_REGION.getDescription()) != null) {
            List<DataKing> sixRegionList = map.get(SixRegionEnum.NORTH_REGION.getDescription());
            sortList(listAll, sixRegionList);
        }
        if (map.get(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription()) != null) {
            List<DataKing> sixRegionList = map.get(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
            sortList(listAll, sixRegionList);
        }
        if (map.get(SixRegionEnum.NORTHEAST_REGION.getDescription()) != null) {
            List<DataKing> sixRegionList = map.get(SixRegionEnum.NORTHEAST_REGION.getDescription());
            sortList(listAll, sixRegionList);
        }
        if (map.get(SixRegionEnum.NORTHWEST_REGION.getDescription()) != null) {
            List<DataKing> sixRegionList = map.get(SixRegionEnum.NORTHWEST_REGION.getDescription());
            sortList(listAll, sixRegionList);
        }
        if (map.get(SixRegionEnum.SOUTHWEST_REGION.getDescription()) != null) {
            List<DataKing> sixRegionList = map.get(SixRegionEnum.SOUTHWEST_REGION.getDescription());
            sortList(listAll, sixRegionList);
        }
        if (map.get(SixRegionEnum.SOUTH_REGION.getDescription()) != null) {
            List<DataKing> sixRegionList = map.get(SixRegionEnum.SOUTH_REGION.getDescription());
            sortList(listAll, sixRegionList);
        }
        ArrayList<DataKing> arrayList = new ArrayList<>();
        for (DataKingChanVo kingChanVo : listAll) {
            DataKing king = new DataKing();
            BeanUtils.copyProperties(kingChanVo, king);
            arrayList.add(king);
        }
        return arrayList;
    }

    /**
     * ??????????????????14?????????????????????(?????????areaid)
     *
     * @param dataKing
     */
    private List<DataKing> groupAreaIdAndSort(List<DataKing> dataKing) {
        //????????????list
        List<DataKingChanVo> listAll = new ArrayList<>();
        //?????????????????????????????????????????????????????????
        Map<String, List<DataKing>> map = dataKing.stream().collect(Collectors.groupingBy((DataKing::getArea_Name)));
        //????????????key
        Set<String> set = map.keySet();
        for (String key : set) {
            if (map.get(key) != null) {
                List<DataKing> kingList = map.get(key);
                sortList(listAll, kingList);
            }
        }
        ArrayList<DataKing> arrayList = new ArrayList<>();
        for (DataKingChanVo kingChanVo : listAll) {
            DataKing king = new DataKing();
            BeanUtils.copyProperties(kingChanVo, king);
            arrayList.add(king);
        }
        return arrayList;
    }

    /**
     * ??????????????????14?????????????????????
     *
     * @param listAll
     * @param sixRegionList
     */
    private void sortList(List<DataKingChanVo> listAll, List<DataKing> sixRegionList) {
        List<DataKingChanVo> list = new ArrayList<>();
        //??????????????????
        for (DataKing king : sixRegionList) {
            DataKingChanVo chanVo = new DataKingChanVo();
            BeanUtils.copyProperties(king, chanVo);
            if (CropsSortEnum.EARLY_RICE.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.EARLY_RICE.getIndex());
            }
            if (CropsSortEnum.MIDDLE_RICE.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.MIDDLE_RICE.getIndex());
            }
            if (CropsSortEnum.DOUBLE_LATE_RICE.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.DOUBLE_LATE_RICE.getIndex());
            }
            if (CropsSortEnum.WHEAT.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.WHEAT.getIndex());
            }
            if (CropsSortEnum.CORN.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.CORN.getIndex());
            }
            if (CropsSortEnum.POTATO.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.POTATO.getIndex());
            }
            if (CropsSortEnum.SWEET_POTATO.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.SWEET_POTATO.getIndex());
            }
            if (CropsSortEnum.CASSAVA.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.CASSAVA.getIndex());
            }
            if (CropsSortEnum.PEANUT.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.PEANUT.getIndex());
            }
            if (CropsSortEnum.RAPE.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.RAPE.getIndex());
            }
            if (CropsSortEnum.SOYBEAN.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.SOYBEAN.getIndex());
            }
            if (CropsSortEnum.COTTON.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.COTTON.getIndex());
            }
            if (CropsSortEnum.SUGAR_CANE.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.SUGAR_CANE.getIndex());
            }
            if (CropsSortEnum.OTHER.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.OTHER.getIndex());
            }
            if (CropsSortEnum.TOTAL.getChineseName().equals(chanVo.getStrawName())) {
                chanVo.setStrawTypeSortId(CropsSortEnum.TOTAL.getIndex());
            }
            list.add(chanVo);
        }
        list.sort(Comparator.comparing(DataKingChanVo::getStrawTypeSortId));
        listAll.addAll(list);
    }

    //??????
    private static String fetchGroupKey(DataKing king) {
        return king.getArea_Name() + "#" + king.getGtime() + "#" + king.getStrawName();
    }

    //?????????????????????????????????
    private static String fetchGroupKeys(DataKing king) {
        return king.getArea_Name() + "#" + king.getStrawName();
    }

    //?????????????????????????????????
    private static String fetchGroupAreaId(DataKing king) {
        return king.getArea_Name() + "#" + king.getStrawName() + "#" + king.getId();
    }

    //??????????????????????????????????????????
    private static String fetchGroupKeysByYear(DataKing king) {
        return king.getGtime() + "#" + king.getArea_Name() + "#" + king.getStrawName();
    }


    //??????????????????
    private static String fetchGroupKeyByArea(DataKing king) {
        return king.getArea_Name() + "#" + king.getGtime();
    }

    //??????????????????
    private static String fetchGroupKeyByArea2(DataKing king) {
        return king.getArea_Name() + "#" + king.getGtime();
    }

    public static <E, T> List<T> convertList2List(List<E> input, Class<T> clzz) {
        List<T> output = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(input)) {
            for (E source : input) {
                T target = BeanUtils.instantiate(clzz);
                BeanUtil.copyProperties(source, target);
                output.add(target);
            }
        }
        return output;
    }

    //list??????
    public static List<ElascticsearchModel> page(List<ElascticsearchModel> dataList, int pageSize, int currentPage) {

        List<ElascticsearchModel> currentPageList = new ArrayList<>();

        if (dataList != null && dataList.size() > 0) {

            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);

            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {

                ElascticsearchModel data = dataList.get(currIdx + i);

                currentPageList.add(data);

            }

        }

        return currentPageList;

    }

    //list??????2
    public static List<DataKing> page2(List<DataKing> dataList, int pageSize, int currentPage) {

        List<DataKing> currentPageList = new ArrayList<>();

        if (dataList != null && dataList.size() > 0) {

            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);

            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {

                DataKing data = dataList.get(currIdx + i);

                currentPageList.add(data);

            }

        }

        return currentPageList;

    }
}
