package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.excelmodel.StrawUtilizeExcel;
import com.sofn.ducss.service.StrawUtilizeDetailService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.ExportDetailUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.vo.StrawUtilizeCombinVo;
import com.sofn.ducss.vo.StrawUtilizeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StrawUtilizeDetailServiceImpl extends ServiceImpl<StrawUtilizeDetailMapper, StrawUtilizeDetail> implements StrawUtilizeDetailService {
    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

    @Autowired
    private StrawUtilizeMapper strawUtilizeMapper;

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private CountryTaskMapper countryTaskMapper;

    @Autowired
    private StoredProcedureMapper storedProcedureMapper;

    @Autowired
    SysDictionaryMapper sysDictionaryMapper;

    @Override
    public List<StrawUtilizeDetail> getStrawUtilizeDetail(String strawUtilizeId) {
        List<StrawUtilizeDetail> list2 = strawUtilizeDetailMapper.getStrawUtilizeDetail(strawUtilizeId);
        if (list2.size() > 0) {
            List<StrawUtilizeDetail> result = new ArrayList<>();
            Map<String, StrawUtilizeDetail> detailMap = list2.stream().collect(Collectors.toMap(StrawUtilizeDetail::getStrawType, Function.identity(), (key1, key2) -> key2));
            List<SysDictionary> list3 = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

            Iterator<SysDictionary> iterator = list3.iterator();
            while (iterator.hasNext()) {
                SysDictionary sysDict = iterator.next();
                String key = sysDict.getDictKey();
                for (StrawUtilizeDetail strawUtilizeDetail : list2) {
                    String type = strawUtilizeDetail.getStrawType();
                    if (type.equals(key)) {
                        strawUtilizeDetail.setStrawName(sysDict.getDictValue());
                        result.add(strawUtilizeDetail);
                        detailMap.remove(type);
                        iterator.remove();
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(list3)) {
                for (SysDictionary sysDict : list3) {
                    StrawUtilizeDetail strawUtilizeDetail = new StrawUtilizeDetail();
                    strawUtilizeDetail.setStrawName(sysDict.getDictValue());
                    strawUtilizeDetail.setStrawType(sysDict.getDictKey());
                    result.add(strawUtilizeDetail);
                }
            }

            if (detailMap.size() > 0) {
                List<SysDictionary> otherDict = sysDictionaryMapper.getOtherDictionaries(Constants.DictionaryType.STRAW_TYPE, new ArrayList<>(detailMap.keySet()));
                for (SysDictionary sysDict : otherDict) {
                    for (Map.Entry<String, StrawUtilizeDetail> entry : detailMap.entrySet()) {
                        if (entry.getKey().equals(sysDict.getDictKey())) {
                            StrawUtilizeDetail detail = entry.getValue();
                            if (detail.getFertilising().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getForage().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getFuel().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getBase().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getMaterial().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getOther().compareTo(BigDecimal.ZERO) > 0) {
                                detail.setStrawName(sysDict.getDictValue());
                                result.add(detail);
                            }
                        }
                    }
                }
            }
            return result;
        } else {// 新增
            //List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
            List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
            List<StrawUtilizeDetail> result = new ArrayList<StrawUtilizeDetail>();
            for (SysDictionary sysDict : list) {
                StrawUtilizeDetail strawUtilizeDetail = new StrawUtilizeDetail();
                strawUtilizeDetail.setStrawName(sysDict.getDictValue());
                strawUtilizeDetail.setStrawType(sysDict.getDictKey());
                result.add(strawUtilizeDetail);
            }
            return result;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addOrUpdateStrawUtilizeDetail(StrawUtilizeVo strawUtilizeVo, String userId) {
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        String cityId = areaList.get(1);
        String provinceId = areaList.get(0);

        /**
         * 原因：判断用户在录入规模化秸秆利用量之前，产生量与还田量的填写情况是否对应
         * by:chlf  2019-04-15 14:35
         */
        //-------------------------------START-----------------------------------//
        List<ProStillDetail> ducProStilldetailList = proStillDetailMapper.getProStillDetailListByAreaId(countyId, strawUtilizeVo.getYear());
        Map<String, BigDecimal> dpsMap = new HashMap<String, BigDecimal>();
        HashSet dpsSet = new HashSet<>();
        BigDecimal count = new BigDecimal(0);
        for (ProStillDetail dpsd : ducProStilldetailList) {
            dpsMap.put(dpsd.getStrawName(), dpsd.getAssigned());
            count = count.add(dpsd.getAssigned());
            //有数据填写的农作物
            if (new BigDecimal(0).compareTo(dpsd.getAssigned()) < 0)
                dpsSet.add(dpsd.getStrawType());
        }
        //若所有农作物未填写，则直接返回
        if (new BigDecimal(0).compareTo(count) > 0) {
            throw new SofnException("请检查产生量与还田量的填写是否完成!");
        }

        //判断输入的数据项是否符合要求
        isDisperseUtilizeMatch(dpsSet, strawUtilizeVo.getStrawUtilizeDetailList());
        //-------------------------------END-----------------------------------//

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", strawUtilizeVo.getYear());
        params.put("areaId", countyId);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("当前年度填报任务已经上报不能修改/新增!");
            } else if (strawUtilizeVo.getStrawUtilizeId().equals("")) {// 新增操作
//                Map<String, Object> params2 = Maps.newHashMap();
//                params2.put("year", strawUtilizeVo.getYear());
//                params2.put("areaId", countyId);
//                Integer count2 = strawUtilizeMapper.getStrawUtilizeTotalCount(params2);
//                if(count2>0){
//                    throw new SofnException("已经存在本年度的相关数据不能重复添加！");
//                }

                StrawUtilize strawUtilize = new StrawUtilize();
                strawUtilize.setId(IdUtil.getUUId());
                strawUtilize.setFillNo(createFillNumber(countyId));
                strawUtilize.setAreaId(countyId);
                strawUtilize.setCityId(cityId);
                strawUtilize.setProvinceId(provinceId);
                strawUtilize.setCreateDate(strawUtilizeVo.getAddTime());
                strawUtilize.setAddress(strawUtilizeVo.getAddress());
                strawUtilize.setCreateUserId(userId);
                strawUtilize.setCreateUserName(UserUtil.fetchLoginUserNameInToken());
                strawUtilize.setReportArea(strawUtilizeVo.getDepartment());
                strawUtilize.setMainName(strawUtilizeVo.getMainName());
                strawUtilize.setMainNo(strawUtilizeVo.getMainNo());
                strawUtilize.setCorporationName(strawUtilizeVo.getCorporationName());
                strawUtilize.setCompanyPhone(strawUtilizeVo.getCompanyPhone());
                strawUtilize.setMobilePhone(strawUtilizeVo.getMobilePhone());
                strawUtilize.setYear(strawUtilizeVo.getYear());
                strawUtilize.setCreateTime(new Date());
                strawUtilizeMapper.insertStrawUtilize(strawUtilize);
                updateCountryTask(0, 1, task);
                //添加区划组合名称字段到详情表中
                String combinStr = provinceId + "," + cityId + "," + countyId;
                String result = sysApi.getRegionNamesByCodes(combinStr);
                List<StrawUtilizeDetail> detailList = strawUtilizeVo.getStrawUtilizeDetailList();
                for (StrawUtilizeDetail detail : detailList) {
                    detail.setId(IdUtil.getUUId());
                    detail.setUtilizeId(strawUtilize.getId());
                    detail.setMarketEnt(detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial()));
                    detail.setArea_Name(result);     //区划组合信息名称
                    detail.setArea_Id(countyId);               //区县ID
                    setComprehensiveIndexValue(strawUtilize, detail);
                }

                List<StrawUtilizeDetail> list3 = new ArrayList<>();
                List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE);
                for (SysDict sysDict : list) {
                    String name = sysDict.getDictname();
                    for (StrawUtilizeDetail strawUtilizeDetail : detailList) {
                        String name2 = strawUtilizeDetail.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(strawUtilizeDetail);
                        }
                    }
                }

                strawUtilizeDetailMapper.insertList(list3);
                //调用存储过程--->进行增量同步
                StoredProcedure storedProcedure;
                for (StrawUtilizeDetail proStillDetail : list3) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(strawUtilize.getAreaId());
                    storedProcedure.setYearData(strawUtilize.getYear());
                    storedProcedureMapper.insert(storedProcedure);
                }
                LogUtil.addLog(LogEnum.LOG_TYPE_ADD.getCode(), "新增-" + strawUtilizeVo.getYear() + "-<规模化秸秆利用量填报>");
                return "添加成功！";
            } else {
                StrawUtilize strawUtilize = new StrawUtilize();
                strawUtilize.setId(strawUtilizeVo.getStrawUtilizeId());
                strawUtilize.setYear(strawUtilizeVo.getYear());
                strawUtilize.setAddress(strawUtilizeVo.getAddress());
                strawUtilize.setReportArea(strawUtilizeVo.getDepartment());
                strawUtilize.setMainName(strawUtilizeVo.getMainName());
                strawUtilize.setMainNo(strawUtilizeVo.getMainNo());
                strawUtilize.setCorporationName(strawUtilizeVo.getCorporationName());
                strawUtilize.setCompanyPhone(strawUtilizeVo.getCompanyPhone());
                strawUtilize.setMobilePhone(strawUtilizeVo.getMobilePhone());
                strawUtilize.setCreateDate(strawUtilizeVo.getAddTime());
                strawUtilizeMapper.updateStrawUtilize(strawUtilize);
                List<StrawUtilizeDetail> detailList = strawUtilizeVo.getStrawUtilizeDetailList();
                StrawUtilize strawUtilize1 = strawUtilizeMapper.selectById(strawUtilizeVo.getStrawUtilizeId());
                for (StrawUtilizeDetail detail : detailList) {
                    detail.setMarketEnt(detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial()));
                    setComprehensiveIndexValue(strawUtilize1, detail);
                }
                strawUtilizeDetailMapper.updateList(strawUtilizeVo.getStrawUtilizeDetailList());
                //调用存储过程--->进行增量同步
                StoredProcedure storedProcedure;
                for (StrawUtilizeDetail proStillDetail : strawUtilizeVo.getStrawUtilizeDetailList()) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(strawUtilize.getAreaId());
                    storedProcedure.setYearData(strawUtilize.getYear());
                    storedProcedureMapper.insert(storedProcedure);
                }
                LogUtil.addLog(LogEnum.LOG_TYPE_EDIT.getCode(), "编辑-" + strawUtilizeVo.getYear() + "-<规模化秸秆利用量填报>");
                return "修改成功！";
            }
        } else {
            throw new SofnException("当前年度填报任务数据出现问题，请核实！");
        }

    }

    //判断填写是否与前一步操作中填写的农作物对应,不对应则直接返回
    //外县购入可以不接受规则控制
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void isDisperseUtilizeMatch(HashSet set, List<StrawUtilizeDetail> list) {

        if (null == set || set.isEmpty()) {
            throw new SofnException("请检查产生量与还田量的填写是否完成!");
        }
        if (list.isEmpty()) {
            throw new SofnException("数据录入有误!");
        }

        Map<String, String> detailMap = new HashMap<String, String>();
        BigDecimal countOther = new BigDecimal(0);
        for (StrawUtilizeDetail detail : list) {
            BigDecimal count = new BigDecimal(0).add(detail.getBase())
                    .add(detail.getFertilising())
                    .add(detail.getForage())
                    .add(detail.getFuel())
                    .add(detail.getMaterial());
            detail.setOther(detail.getOther() == null ? BigDecimal.ZERO : detail.getOther());
            countOther = countOther.add(detail.getOther());   //其他外县购入之和
            if (count.compareTo(detail.getOther()) == -1) {//不允许外县购入数大于总和
                throw new SofnException("【外县来源】数值不能大于五料化之和!");
            }
            //有数据填写数据填写
            if (new BigDecimal(0).compareTo(count) < 0) {
                if (count.compareTo(detail.getOther()) == 0) {//外县购入等于其他之和则可以填
                    set.add(detail.getStrawType());
                }
                detailMap.put(detail.getStrawType(), detail.getStrawName());
            }
        }

        //数据不能为空
        if (new BigDecimal(0).compareTo(countOther) >= 0 && detailMap.isEmpty()) {
            throw new SofnException("录入数据不能都为0!");
        }

        //填写项目是否符合之前填的表
        Set<String> detailSet = detailMap.keySet();
        for (String key : detailSet) {
            if (set.contains(key))     //前一步有的数据项
                continue;

            throw new SofnException("【" + detailMap.get(key) + "】在产生量中未有对应录入！");
        }
    }

    /**
     * 判断输入的农作物亩产量是否在范围内
     */
    private void checkYieldPerMuAndPercentageOfWuliao(List<DisperseUtilizeDetail> list) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", true);
        if (null == list || list.isEmpty()) {
            throw new SofnException("数据录入有误!");
        }

        for (DisperseUtilizeDetail detail : list) {
            //数据为0，未填写则不判断
            if (CropsEnum.EARLY_RICE.getMin().compareTo(detail.getYieldPerMu()) == 0) {
                continue;
            }

            BigDecimal count = detail.getFertilising()
                    .add(detail.getForage())
                    .add(detail.getFuel())
                    .add(detail.getMaterial())
                    .add(detail.getBase());

            if (new BigDecimal(100).compareTo(count) == -1) {
                throw new SofnException("五料化之和不能大于100!");
            }

            //验证亩产取值范围
            resultMap = CropsEnum.checkValue(resultMap, detail.getYieldPerMu(), detail.getStrawType(), detail.getStrawName());
            if (resultMap != null && resultMap.get("strId") != null) {
                break;
            }
        }

        if (resultMap.containsKey("strId")) {
            resultMap.put("success", false);
            if (resultMap.containsKey("maxthreshold")) {
                throw new SofnException("【" + resultMap.get("strId") + "】亩产值超过农作物产量最大范围" + resultMap.get("maxthreshold"));
            } else if (resultMap.containsKey("threshold")) {
                throw new SofnException("【" + resultMap.get("strId") + "】亩产值超过预定的范围" + resultMap.get("threshold"));
            } else if (resultMap.containsKey("maxpercentage")) {
                throw new SofnException("【" + resultMap.get("strId") + "】" + resultMap.get("maxpercentage"));
            }
        }
    }

    /**
     * 生成编号
     *
     * @param areaId
     * @return
     */
    protected String createFillNumber(String areaId) {
        long currentTime = System.currentTimeMillis();
        String fillNumber = areaId + currentTime + (int) (Math.random() * 10) + "";
        return fillNumber;
    }

    /**
     * 修改填报任务中的实际填报值
     *
     * @param factFarmerCount
     * @param factMarketCount
     * @param
     * @throws Exception
     */
    protected void updateCountryTask(Integer factFarmerCount,
                                     Integer factMarketCount, CountryTask task) {
		/*task.setFactNum(task.getFactNum()+factFarmerCount);
		task.setMainNum(task.getMainNum()+factMarketCount);
		countryTaskDao.updateTaskById(task);*/
        //2019.4.8 修改， 实际填报数和主体个数，更新时，在数据库原值基础上做处理*/
        countryTaskMapper.updateDynamicNumById(task.getId(), factFarmerCount, factMarketCount);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteStrawUtilizeById(String strawUtilizeId) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        StrawUtilize strawUtilize = strawUtilizeMapper.selectStrawUtilizeById(strawUtilizeId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", strawUtilize.getYear());
        params.put("areaId", strawUtilize.getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("当前年度填报任务已经上报不能删除！");
            } else {
                updateCountryTask(0, -1, task);
                strawUtilizeMapper.deleteById(strawUtilizeId);
                strawUtilizeDetailMapper.deleteByStrawUtilizeId(strawUtilizeId);
                LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "删除-" + strawUtilize.getYear() + "-<规模化秸秆利用量填报>");
            }
        } else {
            throw new SofnException("当前年度填报任务数据出现问题，请核实！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteByUtilizeIds(List<String> utilizeIds) {
        List<StrawUtilize> strawUtilizeList = strawUtilizeMapper.selectBatchIds(utilizeIds);
        if (CollectionUtils.isEmpty(strawUtilizeList)) {
            throw new SofnException("该数据没有查询到,请核对数据！");
        }
        // 获取查询结果所有年份
        Set<String> years = strawUtilizeList.stream().map(StrawUtilize::getYear).collect(Collectors.toSet());
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("areaId", strawUtilizeList.get(0).getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == years.size()) {
            for (CountryTask task : tasks) {
                if (Constants.ExamineState.REPORTED.equals(task.getStatus())
                        || Constants.ExamineState.READ.equals(task.getStatus())
                        || Constants.ExamineState.PASSED.equals(task.getStatus())) {
                    throw new SofnException("当前年度填报任务已经上报不能删除！");
                } else {
                    updateCountryTask(0, -1, task);
                }
            }
            // 批量删除
            strawUtilizeMapper.deleteBatchIds(utilizeIds);
            deleteByUtilizeIds(utilizeIds);
            LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "批量删除-" + StringUtils.join(years, ",") + "-<规模化秸秆利用量填报>");
        } else {
            throw new SofnException("当前年度填报任务数据出现问题，请核实！");
        }
    }

    @Override
    public void getStrawUtilizeExcelList(Map<String, Object> params, HttpServletResponse response) throws IOException {
        List<StrawUtilizeCombinVo> voList = strawUtilizeDetailMapper.selectCombinVoByCondition(params);
        List<StrawUtilizeExcel> excels = new ArrayList<>(voList.size());
        for (StrawUtilizeCombinVo vo : voList) {
            BigDecimal decimal = vo.getFertilising().add(vo.getForage()).add(vo.getFuel()).add(vo.getBase()).add(vo.getMaterial());
            if (decimal.compareTo(BigDecimal.ZERO) != 0) {
                StrawUtilizeExcel excel = new StrawUtilizeExcel();
                BeanUtils.copyProperties(vo, excel);
                excel.setOwnCountry(decimal.subtract(excel.getOther()));
                excels.add(excel);
            }
        }

        //对数据进行导出处理
        ClassPathResource resource = new ClassPathResource("static/市场主体规模化秸秆利用量.xlsx");
        InputStream inputStream = resource.getInputStream();
        ExportDetailUtil exportDetailUtil = new ExportDetailUtil(inputStream, 1);
        Workbook workbook = exportDetailUtil.getWorkbook();
        String sheetName = "市场主体规模化秸秆利用量";
        workbook.setSheetName(0, sheetName);
        exportDetailUtil.setSheet(workbook.getSheetAt(0));
        Sheet sheet = exportDetailUtil.getSheet();
        int size = excels.size();
        for (int i = 0; i < size; i++) {
            replaceCellValue(sheet, i + 2, 0, excels.get(i).getMainNo());
            replaceCellValue(sheet, i + 2, 0, excels.get(i).getMainNo());
            replaceCellValue(sheet, i + 2, 1, excels.get(i).getMainName());
            replaceCellValue(sheet, i + 2, 2, excels.get(i).getCorporationName());
            replaceCellValue(sheet, i + 2, 3, excels.get(i).getMobilePhone());
            replaceCellValue(sheet, i + 2, 4, excels.get(i).getAddress());
            replaceCellValue(sheet, i + 2, 5, excels.get(i).getStrawName());
            replaceCellValue(sheet, i + 2, 6, excels.get(i).getFertilising());
            replaceCellValue(sheet, i + 2, 7, excels.get(i).getForage());
            replaceCellValue(sheet, i + 2, 8, excels.get(i).getFuel());
            replaceCellValue(sheet, i + 2, 9, excels.get(i).getBase());
            replaceCellValue(sheet, i + 2, 10, excels.get(i).getMaterial());
            replaceCellValue(sheet, i + 2, 11, excels.get(i).getOther());
            replaceCellValue(sheet, i + 2, 12, excels.get(i).getOwnCountry());
        }

        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        //swagger里面的文件名乱码问题是swagger本身问题，;filename*=utf-8''加入后能解决postman的文件名乱码问题
        response.setHeader("Content-Disposition", "attachment;filename=" + "市场主体规模化秸秆利用量.xlsx" + ";filename*=utf-8''"
                + URLEncoder.encode("市场主体规模化秸秆利用量.xlsx", "utf-8"));
        OutputStream os = response.getOutputStream();
        inputStream.close();
        exportDetailUtil.write(os);
    }

    public void replaceCellValue(Cell cell, Object value) {
        String val = value != null ? String.valueOf(value) : "";
        cell.setCellValue(val);
    }

    /**
     * 根据行号，列号进行替换
     *
     * @param rowNum 行号
     * @param colNum 列号
     * @param value  值
     */
    public void replaceCellValue(Sheet sheet, int rowNum, int colNum, Object value) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        replaceCellValue(cell, value);
    }

    /**
     * 设置综合指标的值
     *
     * @param detail
     */
    private void setComprehensiveIndexValue(StrawUtilize strawUtilize, StrawUtilizeDetail detail) {
        //市场主体利用量=五料化之和（'肥料化'+ '饲料化'+ '饲料化'+'基料化'+ '原料化'）
        detail.setMarketEnt(detail.getFertilising().add(detail.getForage()).add(detail.getFuel()).add(detail.getBase()).add(detail.getMaterial()));
    }


    @Override
    public int getCountByCondition(String year, String provinceId, int marketEnd) {
        if (StringUtils.isEmpty(year) || StringUtils.isEmpty(provinceId)) {
            return 0;
        }

        return strawUtilizeDetailMapper.getCountByCondition(year, provinceId, marketEnd);
    }

    @Override
    public void deleteByUtilizeIds(List<String> utilizeIds) {
        UpdateWrapper<StrawUtilizeDetail> uw = new UpdateWrapper<>();
        uw.lambda().in(StrawUtilizeDetail::getUtilizeId, utilizeIds);
        remove(uw);
    }


    public List<StrawUtilizeExcel> getStrawUtilizeWorkbook(Map<String, Object> params) throws IOException {
        List<StrawUtilizeCombinVo> voList = strawUtilizeDetailMapper.selectCombinVoByCondition(params);
        List<StrawUtilizeExcel> excels = new ArrayList<>(voList.size());
        for (StrawUtilizeCombinVo vo : voList) {
            BigDecimal decimal = vo.getFertilising().add(vo.getForage()).add(vo.getFuel()).add(vo.getBase()).add(vo.getMaterial());
            if (decimal.compareTo(BigDecimal.ZERO) != 0) {
                StrawUtilizeExcel excel = new StrawUtilizeExcel();
                BeanUtils.copyProperties(vo, excel);
                excel.setOwnCountry(decimal.subtract(excel.getOther()));
                excels.add(excel);
            }
        }
        return excels;
    }
}
