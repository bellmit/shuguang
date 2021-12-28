package com.sofn.ducss.service.impl;

import com.sofn.ducss.enums.AnalyIndexEnum;
import com.sofn.ducss.enums.SysManageEnum;
import com.sofn.ducss.mapper.DataAnalysisAreaMapper;
import com.sofn.ducss.mapper.DataAnalysisProviceMapper;
import com.sofn.ducss.mapper.SysDictionaryMapper;
import com.sofn.ducss.model.DataAnalysisProvice;
import com.sofn.ducss.model.SysDictionary;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.DataAnalysisService;
import com.sofn.ducss.service.SysRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.util.ExcelDataAnalyUtil;
import com.sofn.ducss.util.ListUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.DataAnalyVo;
import com.sofn.ducss.vo.DataKingDto;
import com.sofn.ducss.vo.SysRegionForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/***
 * 分析数据展示列表(新)
 * @author xl
 * @date 2021/06/07 15:03
 */
@Service
@Slf4j
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Autowired
    SysApi sysApi;

    @Autowired
    private DataAnalysisProviceMapper dataAnalysisProviceMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    DataAnalysisAreaMapper dataAnalysisAreaMapper;


    @Override
    public Result<PageUtils<DataKingDto>> getDataList(HashMap<String, String> paramMap) {
        List<DataKingDto> dataKingDtoList = null;
        if (paramMap != null){
            dataKingDtoList = new ArrayList<>();
            List<String> yearList = getYearList(paramMap); // 年份
            List<String> dictTypeList =getDictTypeList(paramMap.get("cropType")); // 作物类型
            List<String> titleList = getTitleList(paramMap); // 指标内容
            String reginCodes = paramMap.get("area");
            // 区域查询四种格式,查询区域分页列表
            if ("2".contains(paramMap.get("allCropType"))){ // 自定义
                Map<String, Object> params = new HashMap<>();
                params.put("regionName",null);
                params.put("regionCode", reginCodes);
                params.put("parentId",  null);
                params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
                params.put("versionYear",null);
                params.put("versionCode",null);
                Integer pageNo = Integer.parseInt(paramMap.get("pageNum"));
                Integer pageSize = Integer.parseInt(paramMap.get("pageSize"));
                PageUtils<SysRegionForm> result = sysRegionService.getSysRegionByContionsPage(params,pageNo,pageSize);
                if (result != null){
                    List<SysRegionForm> sysRegionFormList = (List<SysRegionForm>) result.getList();
                    if (!ListUtils.isEmpty(sysRegionFormList)){ // 数据
                        DataKingDto dataKingDto = null;
                        for (SysRegionForm item:sysRegionFormList) {
                            dataKingDto = new DataKingDto();
                            dataKingDto.setId(item.getId());
                            String areName = ListUtils.splitToArrayString(item.getAreaName(),item.getRegionName());
                            dataKingDto.setAreaName(areName);
                            dataKingDto.setRegionCode(item.getRegionCode());
                            dataKingDtoList.add(dataKingDto);
                        }
                    }
                    Map<String, Map<String,Object>> map = null;
                    Map<String, Object> s = null;
                    Map<String, Object> dataList = null;
                    // 装载数据
                    for (int i = 0; i < dataKingDtoList.size() ; i++) { // 根据年份, 表头 挂载表头和年份
                        List<DataAnalyVo> mapLists = dataAnalysisProviceMapper.getProviceDataAllSumss(yearList,dataKingDtoList.get(i).getRegionCode());
                        map = new LinkedHashMap<>();
                        for (int j = 0; j < yearList.size() ; j++) {
                            s = new LinkedHashMap<>();
                            Map<String,Map<String,List<DataAnalyVo>>> stringListMap = mapLists.stream().collect(Collectors.groupingBy(DataAnalyVo::getYear,Collectors.groupingBy(DataAnalyVo::getStrawType)));
                            for (int k = 0; k <titleList.size() ; k++) {
                                dataList = new LinkedHashMap<>();
                                for (int l = 0; l < dictTypeList.size() ; l++) {
                                    if (dataKingDtoList.get(i).getRegionCode().equals("440981")) { // (高州)
                                        if (dictTypeList.get(l).equals("all_type")){ // 全部作物
                                            if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                Map<String,String> datasMap = dataAnalysisAreaMapper.getProviceDataAllSumSpecial(yearList.get(j),null);
                                                mathAnalyAllProvice(titleList.get(k),datasMap,dataList,dictTypeList.get(l));
                                            }else { // 普通指标
                                                String num =  dataAnalysisAreaMapper.getProviceDataAllSum(yearList.get(j), AnalyIndexEnum.getSqlValue(titleList.get(k)),null);
                                                dataList.put(dictTypeList.get(l), formatNum(num));
                                            }
                                        }else { // 普通作物（已经排好序列,单个取值后面可以不用遍历排序）
                                            if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                Map<String,String> datasMap = dataAnalysisAreaMapper.getProviceDataAllSumSpecial(yearList.get(j),dictTypeList.get(l));
                                                mathAnalyAllProvice(titleList.get(k),datasMap,dataList,dictTypeList.get(l));
                                            }else { // 普通指标
                                                String num = dataAnalysisAreaMapper.getProviceDataAllSum(yearList.get(j), AnalyIndexEnum.getSqlValue(titleList.get(k)), dictTypeList.get(l));
                                                dataList.put(dictTypeList.get(l), formatNum(num));
                                            }
                                        }
                                    }else {
                                        if (dictTypeList.get(l).equals("all_type")){ // 统计所有作物
                                            if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")) { // 特殊指标重新统计
                                                mathAnalysisAllNum(titleList.get(k),dictTypeList.get(l),dataList,yearList.get(j),stringListMap);
                                            }else { // 普通指标
                                                String  num = statisticsAllType(stringListMap,yearList.get(j),titleList.get(k));
                                                dataList.put(dictTypeList.get(l),  num);
                                            }
                                        }else { // 普通作物
                                            if (stringListMap != null ){
                                                List<DataAnalyVo> data = null;
                                                if (stringListMap.get(yearList.get(j))!= null && stringListMap.get(yearList.get(j)).get(dictTypeList.get(l)) != null ){
                                                    data = stringListMap.get(yearList.get(j)).get(dictTypeList.get(l));
                                                }
                                                if (data != null && data.size() > 0) {
                                                    DataAnalyVo dataAnalysisCity = data.get(0);
                                                    try {
                                                        Map<String, Object> map1 = Obj2Map(dataAnalysisCity);
                                                        if (titleList.get(k).equals("totolRate") || titleList.get(k).equals("comprUtilIndex") || titleList.get(k).equals("induUtilIndex")){ // 特殊指标重新统计
                                                            mathAnalysisNum(titleList.get(k),dictTypeList.get(l),dataList,map1);
                                                        }else { // 普通指标
                                                            Object ss = map1.get(titleList.get(k));
                                                            String num = "";
                                                            if (ss != null){
                                                                num = ss.toString();
                                                            }
                                                            dataList.put(dictTypeList.get(l), formatNum(num));
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }else {
                                                    if (titleList.get(k).equals("totolRate")){
                                                        dataList.put(dictTypeList.get(l), "0.00%");
                                                    }else {
                                                        dataList.put(dictTypeList.get(l), "0.00");
                                                    }
                                                }
                                            }else {
                                                if (titleList.get(k).equals("totolRate")){
                                                    dataList.put(dictTypeList.get(l), "0.00%");
                                                }else {
                                                    dataList.put(dictTypeList.get(l), "0.00");
                                                }
                                            }
                                        }
                                    }
                                }
                                s.put(titleList.get(k),dataList);
                            }
                            map.put(yearList.get(j),s);
                        }
                        dataKingDtoList.get(i).setIndicatorArrays(map);
                    }

                    PageUtils pageUtils = new PageUtils();
                    pageUtils.setList(dataKingDtoList);
                    pageUtils.setCurrPage(result.getCurrPage());
                    pageUtils.setTotalPage(result.getTotalPage());
                    pageUtils.setPageSize(result.getPageSize());
                    pageUtils.setTotalCount(result.getTotalCount());
                    return Result.ok(pageUtils);
                }
            }
        }
        return null;
    }

    /***
     * 获取作物类型
     * @return
     */
    private List<String> getDictTypeList(String cropType){ // 获取查询的作物类型
        if (!cropType.contains(",")){
            List<String> list = new ArrayList<>();
            list.add(cropType);
            return list;
        }
        List<String> croTypeList = ListUtils.springStringToList(cropType);
        if (!ListUtils.isEmpty(croTypeList)){
           List<String> dictList =  sysDictionaryMapper.getDictKeyListBy(croTypeList);
           croTypeList.forEach(item->{
               if (item.equals("all_type")){
                   dictList.add(0, "all_type");
               }
           });
           return dictList;
        }
        return null;
    }

    public Map<String,Object> Obj2Map(Object obj) throws Exception{
       Map<String,Object> map=new HashMap<String, Object>();
       Field[] fields = obj.getClass().getDeclaredFields();
       for(Field field:fields){
       field.setAccessible(true);
       map.put(field.getName(), field.get(obj));
      }
      return map;
    }

    private void mathAnalysisNum(String titleName,String strawName,Map<String, Object> dataList,Map<String,Object> map){
        BigDecimal totol = new BigDecimal(formatNumObject(map.get("totol")));
        BigDecimal collectResource = new BigDecimal(formatNumObject(map.get("collectResource")));
        BigDecimal marketEnt = new BigDecimal(formatNumObject(map.get("marketEnt")));
        BigDecimal reuse = new BigDecimal(formatNumObject(map.get("reuse")));
        BigDecimal returnResource = new BigDecimal(formatNumObject(map.get("returnResource")));
        BigDecimal heJi=marketEnt.add(reuse).add(returnResource);
        if (titleName.equals("totolRate")){ // 综合利用率
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                dataList.put(strawName,formatPercentage(new BigDecimal(0)));
            }else {
                dataList.put(strawName,formatPercentage(totol.divide( collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }
        }else if (titleName.equals("comprUtilIndex")){ //综合利用能力指数
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                dataList.put(strawName,formatString(new BigDecimal(0)));
            }else {
                dataList.put(strawName, formatString(heJi.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }else if (titleName.equals("induUtilIndex")){ // 产业化利用能力指数
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                dataList.put(strawName,formatString(new BigDecimal(0)));
            }else {
                dataList.put(strawName, formatString(marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }
    }

    private void  mathAnalysisAllNum(String titleName,String strawName,Map<String, Object> dataList,String year,Map<String,Map<String,List<DataAnalyVo>>> stringListMap){
        BigDecimal  totol = new BigDecimal(statisticsAllType(stringListMap,year,"totol"));
        BigDecimal  collectResource =  new BigDecimal(statisticsAllType(stringListMap,year,"collectResource"));
        BigDecimal  marketEnt = new BigDecimal(statisticsAllType(stringListMap,year,"marketEnt"));
        BigDecimal  reuse = new BigDecimal(statisticsAllType(stringListMap,year,"reuse"));
        BigDecimal  returnResource = new BigDecimal(statisticsAllType(stringListMap,year,"returnResource"));
        BigDecimal heJi=marketEnt.add(reuse).add(returnResource);
        if (titleName.equals("totolRate")){
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                dataList.put(strawName,formatPercentage(new BigDecimal(0)));
            }else {
                dataList.put(strawName,formatPercentage(totol.divide( collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }
        }else if (titleName.equals("comprUtilIndex")){
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                dataList.put(strawName,formatString(new BigDecimal(0)));
            }else {
                dataList.put(strawName, formatString(heJi.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }else if (titleName.equals("induUtilIndex")){
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                dataList.put(strawName,formatString(new BigDecimal(0)));
            }else {
                dataList.put(strawName,formatString(marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }
    }
    private void  mathAnalyAllProvice(String titleName,Map<String, String> datasMap,Map<String,Object> map,String strawName){
        BigDecimal  totol = new BigDecimal(formatNumObject(datasMap.get("totol")));
        BigDecimal  collectResource =  new BigDecimal(formatNumObject(datasMap.get("collectresource")));
        BigDecimal  marketEnt = new BigDecimal(formatNumObject(datasMap.get("marketent")));
        BigDecimal  reuse = new BigDecimal(formatNumObject(datasMap.get("reuse")));
        BigDecimal  returnResource = new BigDecimal(formatNumObject(datasMap.get("returnresource")));
        BigDecimal heJi=marketEnt.add(reuse).add(returnResource);
        if (titleName.equals("totolRate")){
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                map.put(strawName,formatPercentage(new BigDecimal(0)));
            }else {
                map.put(strawName,formatPercentage(totol.divide( collectResource, 10, RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }
        }else if (titleName.equals("comprUtilIndex")){
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                map.put(strawName,formatString(new BigDecimal(0)));
            }else {
                map.put(strawName, formatString(heJi.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }else if (titleName.equals("induUtilIndex")){
            if (collectResource.compareTo(new BigDecimal(0)) == 0){
                map.put(strawName,formatString(new BigDecimal(0)));
            }else {
                map.put(strawName,formatString(marketEnt.divide(collectResource, 10, RoundingMode.HALF_UP)));
            }
        }
    }

    private List<String> getYearList(HashMap<String, String> paramMap){
        List<String> yearList = null;
        if (paramMap.get("year").contains(",")){ // 取出年份
            yearList = ListUtils.springStringToList(paramMap.get("year"));
        }else {
            yearList = new ArrayList<>();
            yearList.add(paramMap.get("year"));
        }
        return yearList;
    }

    private List<String> getTitleList(HashMap<String, String> paramMap){
        List<String> yearList = null;
        List<String> titleList = new ArrayList<>();
        if (paramMap.get("analysisIndex").contains(",")){ // 取出年份
            yearList = ListUtils.springStringToList(paramMap.get("analysisIndex"));
        }else {
            yearList = new ArrayList<>();
            yearList.add(paramMap.get("analysisIndex"));
        }
        yearList.forEach(item->{
            String value = AnalyIndexEnum.getDataValue(item);
            titleList.add(value);
        });
        return titleList;
    }

    /***
     * 统计全部作物
     * @return
     */
    private String statisticsAllType(Map<String,Map<String,List<DataAnalyVo>>> stringListMap, String year, String title){
        System.out.println(stringListMap);
        BigDecimal total = new BigDecimal("0.00");
        Map<String, List<DataAnalyVo>> userMap =   stringListMap.get(year);
        DataAnalyVo dataAnalysisCity = null;
        if (userMap == null){
            return "0.00";
        }
        for (String item:userMap.keySet()) {
            List<DataAnalyVo> list =userMap.get(item);
            dataAnalysisCity = new DataAnalyVo();
            if (list != null && list.size() >0 ){
                dataAnalysisCity =  userMap.get(item).get(0);
            }
            try {
                Map<String, Object> map1 = Obj2Map(dataAnalysisCity);
                // 判断是否是特殊指标
                Object object = map1.get(title);
                if (object == null){
                    object = new BigDecimal("0.00");
                }
                total = total.add(new BigDecimal(object.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return formatNum(total.toEngineeringString());
    }

    /***
     * 格式化数值
     * @return
     */
    private String formatNum(String num){
        if (StringUtils.isEmpty(num)){
            return "0.00";
        }
        BigDecimal decimal = new BigDecimal(num);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString();
    }

    private String formatNumObject(Object object){
        if (object != null){
            return object.toString();
        }
        return "0";
    }

    private String formatPercentage(BigDecimal decimal){
        if (decimal == null || decimal.compareTo(new BigDecimal(0)) ==0){
            return "0.00%";
        }
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString()+"%";
    }

    private String formatString(BigDecimal decimal){
        if (decimal == null || decimal.compareTo(new BigDecimal("0")) ==0){
            return "0.00";
        }
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toEngineeringString();
    }

    /***
     *  返回六大区列表
     * @param dataKingDtoList
     * @param allCropType
     * @param reginCodes
     * @return
     */
    private List<DataKingDto> getSixDataKing(List<DataKingDto> dataKingDtoList, String allCropType, String reginCodes){
        List<SysDictionary> list = null;
        if (allCropType.equals("1")){// 单个大区
            list = sysDictionaryMapper.getSysDictionaryListByKey("six_region", reginCodes);
        }
        if (allCropType.equals("3")){// 全部大区
            list = sysDictionaryMapper.getSysDictionaryListByKey("six_region", null);

        }
        if (!ListUtils.isEmpty(list)){
            DataKingDto dataKingDto = null;
            for (SysDictionary item:list) {
                dataKingDto = new DataKingDto();
                dataKingDto.setId(item.getId().toString());
                dataKingDto.setAreaName(item.getDictValue());
                dataKingDto.setRegionCode(item.getDictKey());
                dataKingDtoList.add(dataKingDto);
            }
        }
        return dataKingDtoList;
    }



    @Override
    public void datasAnalysisExport(HashMap<String, String> paramMap, HttpServletResponse response) throws Exception {
        // 获取动态表头
        if (paramMap != null){
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
            response.setHeader("Content-Disposition", "attachment;filename=" + "数据分析.xls" + ";filename*=utf-8''"
                    + URLEncoder.encode("数据分析.xls", "utf-8"));
            String analysisIndex = paramMap.get("analysisIndex");
            List<String> headerList = com.sofn.ducss.util.ListUtils.splitToList(analysisIndex); // 表头
            List<String> headList = new ArrayList<>(); // 表头获取结果值列表
            List<String> contextHeadList = new ArrayList<>();
            for (String item:headerList){
                contextHeadList.add(AnalyIndexEnum.getDataValue(item));
                headList.add(AnalyIndexEnum.getValue(item));
            }
            paramMap.put("pageSize","10000");
            paramMap.put("pageNum","1");
            List<String> strawNameList = new ArrayList<>();
            List<String> contextStrawNameList = getDictTypeList(paramMap.get("cropType"));
            for (String item:contextStrawNameList) {
                if (item.equals("all_type")){
                    strawNameList.add("全部作物");
                }else {
                    String value = sysDictionaryMapper.getDictValue(item);
                    strawNameList.add(value);
                }
            }
            Result<PageUtils<DataKingDto>> result = getDataList(paramMap);
            if (result != null && result.getCode()==200){
                List<DataKingDto> dataList = result.getData().getList();
                String year = paramMap.get("year");
                HSSFWorkbook workbook = ExcelDataAnalyUtil.createWorkbook(headList,contextHeadList,strawNameList, contextStrawNameList,dataList, com.sofn.ducss.util.ListUtils.splitToList(year));
                OutputStream ouputStream = response.getOutputStream();
                workbook.write(ouputStream);
                ouputStream.flush();
                ouputStream.close();
            }
        }
    }

    public static void main(String[] args) {
        List<DataAnalysisProvice> list = new ArrayList<>();
        DataAnalysisProvice dataAnalysisProvice = new DataAnalysisProvice();
        dataAnalysisProvice.setYear("2019");
        dataAnalysisProvice.setReturnArea("grainYield");
        dataAnalysisProvice.setStrawType("grassValleyRatio");
        DataAnalysisProvice dataAnalysisProvice1 = new DataAnalysisProvice();
        dataAnalysisProvice1.setYear("2018");
        dataAnalysisProvice1.setReturnArea("grainYield");
        dataAnalysisProvice1.setStrawType("corn");
        list.add(dataAnalysisProvice);
        list.add(dataAnalysisProvice1);
        Map<String,Map<String,List<DataAnalysisProvice>>> userMap = list.stream().collect(Collectors.groupingBy(DataAnalysisProvice::getYear,Collectors.groupingBy(DataAnalysisProvice::getReturnArea)));

//遍历分组后的结果
        userMap.forEach((key1, map) -> {
            System.out.println(key1 + "：");
            map.forEach((key2,user)->
            {
                System.out.println(key2 + "：");
                user.forEach(System.out::println);
            });
            System.out.println("--------------------------------------------------------------------------");
        });
    }
}
