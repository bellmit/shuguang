package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.excelmodel.YieldAndReturnExportExcel;
import com.sofn.ducss.service.ProStillDetailService;
import com.sofn.ducss.service.StandardSetService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.ProStillVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取年度任务
 */
@Service
@Slf4j
public class ProStillDetailServiceImpl extends ServiceImpl<ProStillDetailMapper, ProStillDetail> implements ProStillDetailService {
    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private CountryTaskMapper countryTaskMapper;

    @Autowired
    private ProStillMapper proStillMapper;

    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private StandardSetService standardSetService;

    @Autowired
    private StoredProcedureMapper storedProcedureMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;


    @Override
    public List<ProStillDetail> getProStillDetail(String proStillId) {
        List<ProStillDetail> list2 = proStillDetailMapper.getProStillDetail(proStillId);


        if (list2.size() > 0) {
            List<ProStillDetail> result = new ArrayList<>();
            List<SysDictionary> list3 = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
            //List<SysDict> list3 = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
            for (SysDictionary sysDict : list3) {
                String name = sysDict.getDictValue();
                for (ProStillDetail proStillDetail : list2) {
                    String name2 = proStillDetail.getStrawName();
                    if (name.equals(name2)) {
                        result.add(proStillDetail);
                    }
                }
            }
            return result;
        } else {// 新增
            List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

            //List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
            List<ProStillDetail> result = new ArrayList<ProStillDetail>();
            for (SysDictionary sysDict : list) {
                ProStillDetail ducProStillDeatil = new ProStillDetail();
                ducProStillDeatil.setStrawName(sysDict.getDictValue());
                ducProStillDeatil.setStrawType(sysDict.getDictKey());
                result.add(ducProStillDeatil);
            }
            return result;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addOrUpdateProStill(ProStillVo proStillVo, String userId) {

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

        //--------草谷比、收集系数等数据判断--------------//
        checkData(proStillVo);
        //-------- END--------------//
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", proStillVo.getYear());
        params.put("areaId", countyId);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() != 1) {
            throw new SofnException("请检查当年任务是否生成！");
        }


        // 如果 prostillid=-1是新增操作 否则是更新
        if (proStillVo.getProStillId().equals("")) {
            //查看是否已经生成过相关的数据，有的话返回
            Map<String, Object> params2 = Maps.newHashMap();
            params2.put("year", proStillVo.getYear());
            params2.put("areaId", countyId);
            Integer count = proStillMapper.getProStillTotalCount(params2);
            if (count < 1) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                ProStill proStill = new ProStill();
                proStill.setId(IdUtil.getUUId());
                proStill.setFillNo(createFillNumber(countyId));
                proStill.setAreaId(countyId);
                proStill.setCityId(cityId);
                proStill.setProvinceId(provinceId);
                proStill.setYear(proStillVo.getYear());
                proStill.setCreateDate(proStillVo.getAddTime());
                proStill.setCreateUserId(userId);
                proStill.setCreateUserName(UserUtil.fetchLoginUserNameInToken());    //增加创建者昵称
                proStill.setReportArea(proStillVo.getDepartment());
                proStill.setCreateTime(new Date());
                proStillMapper.insertProStill(proStill);
                List<ProStillDetail> detailList = proStillVo.getProStillDetails();
                //采用部级设定的草谷比和可收及系数
                //detailList =  getStandard(detailList,proStillVo.getYear(),provinceId);
                for (ProStillDetail detail : detailList) {
                    detail.setId(IdUtil.getUUId());
                    detail.setProStillId(proStill.getId());
                    if (detail.getSeedArea().compareTo(new BigDecimal(0)) == 1) {
                        detail.setReturnRatio(detail.getReturnArea().multiply(new BigDecimal(100)).divide(detail.getSeedArea()
                                , 10, RoundingMode.HALF_UP));
                    }
                    detail.setTheoryResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()));
                    detail.setCollectResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()).multiply(detail.getCollectionRatio()));
                    detail.setReturnResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()).multiply(detail.getCollectionRatio()).multiply(detail.getReturnRatio()));
                    setComprehensiveIndexValue(proStill, detail); //zy1
                }

                List<ProStillDetail> list3 = new ArrayList<>();
                List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
                for (SysDict sysDict : list) {
                    String name = sysDict.getDictname();
                    for (ProStillDetail proStillDetail : detailList) {
                        String name2 = proStillDetail.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(proStillDetail);
                        }
                    }
                }

                // 插入detail
                proStillDetailMapper.insertList(list3);
                //调用存储过程--->进行增量同步
                StoredProcedure storedProcedure;
                for (ProStillDetail proStillDetail : list3) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(proStill.getAreaId());
                    storedProcedure.setYearData(proStill.getYear());
                    storedProcedureMapper.insert(storedProcedure);
                }
                LogUtil.addLog(LogEnum.LOG_TYPE_ADD.getCode(), "新增-" + proStillVo.getYear() + "-<产生量与直接还田量填报>");
                return "添加成功!";
            } else {
                return "已经存在本年度的相关数据不能重复添加！";
            }
        } else {
            // 更新detail
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                return "当前年度填报任务已经上报不能修改！";
            } else {
                ProStill proStills = new ProStill();
                proStills.setId(proStillVo.getProStillId());
                proStills.setReportArea(proStillVo.getDepartment());
                proStillMapper.updateById(proStills);
                List<ProStillDetail> detailList = proStillVo.getProStillDetails();
                //将主表对象提为公共的访问
                ProStill proStill = proStillMapper.selectById(proStillVo.getProStillId());
                for (ProStillDetail detail : detailList) {
                    if (detail.getSeedArea().compareTo(new BigDecimal(0)) == 1) {
                        detail.setReturnRatio(detail.getReturnArea().multiply(new BigDecimal(100)).divide(detail.getSeedArea()
                                , 10, RoundingMode.HALF_UP));
                    }
                    detail.setTheoryResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()));
                    detail.setCollectResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()).multiply(detail.getCollectionRatio()));
                    detail.setReturnResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()).multiply(detail.getCollectionRatio()).multiply(detail.getReturnRatio()));
                    setComprehensiveIndexValue(proStill, detail);
                }
                proStillDetailMapper.updateList(detailList);
                StoredProcedure storedProcedure;
                for (ProStillDetail proStillDetail : detailList) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(proStill.getAreaId());
                    storedProcedure.setYearData(proStill.getYear());
                    storedProcedureMapper.insert(storedProcedure);
                }
                LogUtil.addLog(LogEnum.LOG_TYPE_EDIT.getCode(), "编辑-" + proStillVo.getYear() + "-<产生量与直接还田量填报>");
                return "修改成功!";
            }
        }
    }

    /**
     * 获取部级设定草谷比和可收集系数
     * @param detailList
     * @param provinceId
     * @param year
     * @return
     */
//    private List<ProStillDetail> getStandardValue(List<ProStillDetail> detailList, String provinceId, String year) {
//        //根据区域和年度查询系数管理
//
//        List<StandardValue> standardValues = standardSetService.showStandardValues(year, provinceId);
//        if (CollectionUtils.isEmpty(standardValues)){
//            return detailList;
//        }else {
//            for (ProStillDetail proStillDetail : detailList) {
//                for (StandardValue standardValue : standardValues) {
//                    if (proStillDetail.getStrawType().equals(standardValue.getStrawType())) {
//                        proStillDetail.setCollectionRatio(standardValue.getCollectCoefficient());
//                        proStillDetail.setGrassValleyRatio(standardValue.getGrassValley());
//                    }
//                    break;
//                }
//            }
//            return detailList;
//        }
//        }

    /**
     * 导入时换取国家维护标准的草谷比和可收集系数
     *
     * @param detailList
     * @return
     */
    private List<ProStillDetail> getStandard(List<ProStillDetail> detailList, String year, String provinceId) {


        List<StandardValue> data = standardSetService.getGcByProvince(year, provinceId, true).getData();
        if (CollectionUtils.isEmpty(data)) {
            return detailList;
        }
        for (ProStillDetail proStillDetail : detailList) {
            for (StandardValue datum : data) {
                if (proStillDetail.getStrawType().equals(datum.getStrawType())) {
                    if (datum.getCollectCoefficient().compareTo(new BigDecimal(0)) == 1)
                        proStillDetail.setCollectionRatio(datum.getCollectCoefficient());
                    if (datum.getGrassValley().compareTo(new BigDecimal(0)) == 1)
                        proStillDetail.setGrassValleyRatio(datum.getGrassValley());
                    break;
                }
            }
        }

        return detailList;
    }


    private Map<String, Object> checkData(ProStillVo proStillVo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("msg", "");
        if (null == proStillVo || proStillVo.getProStillDetails().isEmpty()) {
            throw new SofnException("请检查数据是否录入正确！");
        }

        boolean flag = false;
        for (ProStillDetail detail : proStillVo.getProStillDetails()) {
            //判断是否都为0
            BigDecimal count = new BigDecimal(0).add(detail.getGrainYield())
                    .add(detail.getSeedArea())
                    .add(detail.getGrassValleyRatio())
                    .add(detail.getCollectionRatio())
                    .add(detail.getReturnArea())
                    .add(detail.getExportYield());
            if (new BigDecimal(0).compareTo(count) != 0) {
                flag = true;
            }

            if (detail.getCollectionRatio().compareTo(new BigDecimal(0)) == -1
                    || detail.getCollectionRatio().compareTo(new BigDecimal(1)) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的收集系数超过范围[0~1]！");
            }
            //逐个判断各个农作物种类的草谷比输入是否符合要求

            //取消判断最大范围，只要精确范围
//            if ((CropsEnum.OTHER.getName().equalsIgnoreCase(detail.getStrawType())||
//                    CropsEnum.COTTON.getName().equalsIgnoreCase(detail.getStrawType()))
//                            && detail.getGrassValleyRatio().compareTo(CropsEnum.OTHER.getGrassValleyRatio())==1
//            ) {
//                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过最大范围[0~6]！");
//            }
            if ((CropsEnum.CORN.getName().equalsIgnoreCase(detail.getStrawType()) ||              //玉米
                    CropsEnum.RAPE.getName().equalsIgnoreCase(detail.getStrawType()) ||            //油菜
                    CropsEnum.PEANUT.getName().equalsIgnoreCase(detail.getStrawType()) ||        //花生
                    CropsEnum.SOYBEAN.getName().equalsIgnoreCase(detail.getStrawType())) &&
                    detail.getGrassValleyRatio().compareTo(CropsEnum.CORN.getGrassValleyRatio()) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过[0~" + CropsEnum.CORN.getGrassValleyRatio() + "]范围");
            }

            if ((CropsEnum.EARLY_RICE.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.MIDDLE_RICE.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.DOUBLE_LATE_RICE.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.WHEAT.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.POTATO.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.SWEET_POTATO.getName().equalsIgnoreCase(detail.getStrawType()) ||
                    CropsEnum.CASSAVA.getName().equalsIgnoreCase(detail.getStrawType())) &&
                    detail.getGrassValleyRatio().compareTo(CropsEnum.EARLY_RICE.getGrassValleyRatio()) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过[0~" + CropsEnum.EARLY_RICE.getGrassValleyRatio() + "]范围");
            }

            if ((CropsEnum.SUGAR_CANE.getName().equalsIgnoreCase(detail.getStrawType()))         //甘蔗
                    && detail.getGrassValleyRatio().compareTo(CropsEnum.SUGAR_CANE.getGrassValleyRatio()) == 1) {
                throw new SofnException("[" + detail.getStrawName() + "]的草谷比超过[0~" + CropsEnum.SUGAR_CANE.getGrassValleyRatio() + "]范围");
            }

        }
        if (flag == false) {
            throw new SofnException("录入数据不能都为0!");
        }
        return result;
    }

    protected String createFillNumber(String areaId) {
        long currentTime = System.currentTimeMillis();
        String fillNumber = areaId + currentTime + (int) (Math.random() * 10) + "";
        return fillNumber;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProStillById(String proStillId) {
        ProStill proStill = proStillMapper.selectProStillById(proStillId);
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", proStill.getYear());
        params.put("areaId", proStill.getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("当前年度填报任务已经上报不能删除！");
            } else {
                proStillMapper.deleteById(proStillId);
                proStillDetailMapper.deleteByProStillId(proStillId);
                LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "删除-" + proStill.getYear() + "-<产生量与直接还田量填报>");
            }
        } else {
            throw new SofnException("当前年度填报任务数据出现问题，请核实！");
        }
    }

    @Override
    public List<YieldAndReturnExportExcel> getProStillExportExcelById(String proStillId) {
        List<ProStillDetail> detailList = proStillDetailMapper.getProStillDetail(proStillId);
        List<YieldAndReturnExportExcel> exportExcels = new ArrayList<>(detailList.size());
        ProStill proStill = proStillMapper.selectProStillById(proStillId);
        if (null == proStill) return exportExcels;

        Integer year = proStill.getYear() == null ? null : Integer.valueOf(proStill.getYear());
        String countyName = sysApi.getRegionNamesByCodes(proStill.getAreaId(), year).getData();
        //导出排序
        List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        for (SysDictionary sysDictionary : list) {
            String dictValue = sysDictionary.getDictValue();
            for (ProStillDetail detail : detailList) {
                if (dictValue.equals(detail.getStrawName())) {
                    YieldAndReturnExportExcel exportExcel = new YieldAndReturnExportExcel();
                    BeanUtils.copyProperties(detail, exportExcel);
                    exportExcel.setYear(proStill.getYear());
                    exportExcel.setCountyName(countyName);
                    exportExcels.add(exportExcel);
                }
            }

        }

        return exportExcels;
    }

    @Override
    public List<ProStillDetail> getProStillDetailListByAreaId(String areaId, String year) {
        return proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
    }

    @Override
    public List<YieldAndReturnExportExcel> getExportExcelByAredIdAndYear(Map<String, Object> queryMap) {
        List<ProStillDetail> detailList = proStillDetailMapper.getProStillDetailListByAreaIdsAndYears(queryMap);
        List<YieldAndReturnExportExcel> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(detailList)) {
            // 区划名称
            Set<String> areaIds = Sets.newHashSet();
            for (ProStillDetail detail : detailList) {
                areaIds.add(detail.getAreaId().toString());
                areaIds.add(detail.getCityId().toString());
                areaIds.add(detail.getProvinceId().toString());
            }
            List<String> years = (List<String>) queryMap.get("years");
            Map<String, String> areaMap = SysRegionUtil.getRegionNameMapsByCodes(new ArrayList<>(areaIds), CollectionUtils.isNotEmpty(years) ? years.get(years.size() - 1) : null);
            // Map<String, String> areaMap = SysRegionUtil.getRegionNameMapByCodes(areaIds);
            // 作物
            List<SysDictionary> dictList = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

            // Map<String, List<ProStillDetail>> detailMap = detailList.stream().collect(Collectors.groupingBy(ProStillDetail::getYearAndAreaId));
            LinkedHashMap<String, List<ProStillDetail>> detailMap = new LinkedHashMap<>();
            for (ProStillDetail proStillDetail : detailList) {
                List<ProStillDetail> proStillDetails = detailMap.get(proStillDetail.getYearAndAreaId());
                if (CollectionUtils.isEmpty(proStillDetails)) {
                    List<ProStillDetail> firstDetailList = Lists.newArrayList(proStillDetail);
                    detailMap.put(proStillDetail.getYearAndAreaId(), firstDetailList);
                } else {
                    proStillDetails.add(proStillDetail);
                }
            }

            for (Map.Entry<String, List<ProStillDetail>> entry : detailMap.entrySet()) {
                for (SysDictionary dict : dictList) {
                    String dictValue = dict.getDictValue();
                    for (ProStillDetail detail : entry.getValue()) {
                        if (dictValue.equals(detail.getStrawName())) {
                            YieldAndReturnExportExcel exportExcel = new YieldAndReturnExportExcel();
                            BeanUtils.copyProperties(detail, exportExcel);
                            String areaName = SysRegionUtil.getAreaName(areaMap, detail.getProvinceId().toString(), detail.getCityId().toString(), detail.getAreaId().toString());
                            exportExcel.setCountyName(areaName);
                            result.add(exportExcel);
                        }
                    }
                }
            }
        }
        /*List<ProStillDetail> detailList = proStillDetailMapper.getProStillDetailListByAreaId(StringUtils.isEmpty(areaId) ? "00" : areaId,
                StringUtils.isEmpty(year) ? "1900" : year);
        List<YieldAndReturnExportExcel> exportExcels = new ArrayList<>(detailList.size());
        ProStill proStill = proStillMapper.selectProStillById(detailList.isEmpty() ? "" : detailList.get(0).getProStillId());
        if (null == proStill) return exportExcels;

        String countyName = sysApi.getRegionNamesByCodes(proStill.getAreaId()).getData();
        List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);
        for (SysDictionary sysDictionary : list) {
            String dictValue = sysDictionary.getDictValue();
            for (ProStillDetail detail : detailList) {
                if (dictValue.equals(detail.getStrawName())) {
                    YieldAndReturnExportExcel exportExcel = new YieldAndReturnExportExcel();
                    BeanUtils.copyProperties(detail, exportExcel);
                    exportExcel.setCountyName(countyName);
                    exportExcels.add(exportExcel);
                }

            }
        }
        return exportExcels;*/
        return result;
    }

    /**
     * 设置综合指标的值
     *
     * @param detail
     */
    private void setComprehensiveIndexValue(ProStill proStill, ProStillDetail detail) {
        //产生量=粮食产量（吨）*草谷比
        detail.setTheoryResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()));
        //可收集量=粮食产量（吨）*草谷比*收集系数
        detail.setCollectResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()).multiply(detail.getCollectionRatio()));
        //直接还田量=可收集量*（还田面积/播种面积）
        if (detail.getSeedArea().compareTo(BigDecimal.ZERO) == 0) {
            detail.setReturnResource(BigDecimal.ZERO);
        } else {
            detail.setReturnResource(detail.getGrainYield().multiply(detail.getGrassValleyRatio()).multiply(detail.getCollectionRatio()).multiply(detail.getReturnArea().divide(detail.getSeedArea(), 2, RoundingMode.HALF_UP)));
        }

    }
}
