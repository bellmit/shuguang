package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.excelmodel.DisperseUtilizeExportExcel;
import com.sofn.ducss.service.DisperseUtilizeDetailService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.vo.DisperseUtilizeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@Lazy
public class DisperseUtilizeDetailServiceImpl extends ServiceImpl<DisperseUtilizeDetailMapper, DisperseUtilizeDetail> implements DisperseUtilizeDetailService {
    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

    @Autowired
    private DisperseUtilizeMapper disperseUtilizeMapper;

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private CountryTaskMapper countryTaskMapper;

    @Autowired
    private StoredProcedureMapper storedProcedureMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Autowired
    private com.sofn.ducss.service.AsyncService asyncService;


    @Override
    public List<DisperseUtilizeDetail> getDisperseUtilizeDetail(String disperseUtilizeId) {
        List<DisperseUtilizeDetail> list2 = disperseUtilizeDetailMapper.getDisperseUtilizeDetail(disperseUtilizeId);
        if (list2.size() > 0) {
            List<DisperseUtilizeDetail> result = new ArrayList<>();
            Map<String, DisperseUtilizeDetail> detailMap = list2.stream().collect(Collectors.toMap(DisperseUtilizeDetail::getStrawType, Function.identity(), (key1, key2) -> key2));
            List<SysDictionary> list3 = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

            Iterator<SysDictionary> iterator = list3.iterator();
            while (iterator.hasNext()) {
                SysDictionary sysDict = iterator.next();
                String key = sysDict.getDictKey();
                for (DisperseUtilizeDetail disperseUtilizeDetail : list2) {
                    String type = disperseUtilizeDetail.getStrawType();
                    if (key.equals(type)) {
                        disperseUtilizeDetail.setStrawName(sysDict.getDictValue());
                        result.add(disperseUtilizeDetail);
                        detailMap.remove(type);
                        iterator.remove();
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(list3)) {
                for (SysDictionary sysDict : list3) {
                    DisperseUtilizeDetail disperseUtilizeDetail = new DisperseUtilizeDetail();
                    disperseUtilizeDetail.setStrawName(sysDict.getDictValue());
                    disperseUtilizeDetail.setStrawType(sysDict.getDictKey());
                    disperseUtilizeDetail.setApplication("");
                    result.add(disperseUtilizeDetail);
                }
            }

            if (detailMap.size() > 0) {
                List<SysDictionary> otherDict = sysDictionaryMapper.getOtherDictionaries(Constants.DictionaryType.STRAW_TYPE, new ArrayList<>(detailMap.keySet()));
                for (SysDictionary sysDict : otherDict) {
                    for (Map.Entry<String, DisperseUtilizeDetail> entry : detailMap.entrySet()) {
                        if (entry.getKey().equals(sysDict.getDictKey())) {
                            DisperseUtilizeDetail detail = entry.getValue();
                            if (detail.getFertilising().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getForage().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getFuel().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getBase().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getMaterial().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getSownArea().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getYieldPerMu().compareTo(BigDecimal.ZERO) > 0
                                    || detail.getReuse().compareTo(BigDecimal.ZERO) > 0) {
                                detail.setStrawName(sysDict.getDictValue());
                                result.add(detail);
                            }
                        }
                    }
                }
            }
/*            if (CollectionUtils.isNotEmpty(list3)) {
                for (SysDictionary sysDict : list3) {
                    DisperseUtilizeDetail disperseUtilizeDetail = new DisperseUtilizeDetail();
                    disperseUtilizeDetail.setStrawName(sysDict.getDictValue());
                    disperseUtilizeDetail.setStrawType(sysDict.getDictKey());
                    disperseUtilizeDetail.setApplication("");
                    result.add(disperseUtilizeDetail);
                }
            }*/
            return result;
        } else {// 新增
            //List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
            List<SysDictionary> list = sysDictionaryMapper.getAllSysDictionaries(Constants.DictionaryType.STRAW_TYPE);

            List<DisperseUtilizeDetail> result = new ArrayList<DisperseUtilizeDetail>();
            for (SysDictionary sysDict : list) {
                DisperseUtilizeDetail disperseUtilizeDetail = new DisperseUtilizeDetail();
                disperseUtilizeDetail.setStrawName(sysDict.getDictValue());
                disperseUtilizeDetail.setStrawType(sysDict.getDictKey());
                disperseUtilizeDetail.setApplication("");
                result.add(disperseUtilizeDetail);
            }
            return result;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addOrUpdateDisperseUtilizeDetail(DisperseUtilizeVo disperseUtilizeVo, String userId) {
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
         * 原因：判断用户在录入分散利用量之前，产生量与还田量的填写情况是否对应
         */
        //-------------------------------START-----------------------------------//
        List<ProStillDetail> ducProStilldetailList = proStillDetailMapper.getProStillDetailListByAreaId(countyId, disperseUtilizeVo.getYear());
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
        isMatch(dpsSet, disperseUtilizeVo.getDisperseUtilizeDetailList());
        //-------------------------------END-----------------------------------//
        //再判断亩产量是否在要求控制的范围内
        checkYieldPerMuAndPercentageOfWuliao(disperseUtilizeVo.getDisperseUtilizeDetailList());

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", disperseUtilizeVo.getYear());
        params.put("areaId", countyId);
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("当前年度填报任务已经上报不能修改/新增！");
            } else if (disperseUtilizeVo.getDisperseUtilizeId().equals("")) {// 新增操作
//                Map<String, Object> params2 = Maps.newHashMap();
//                params2.put("year", disperseUtilizeVo.getYear());
//                params2.put("areaId", countyId);
//                Integer count2 = disperseUtilizeMapper.getDisperseUtilizeTotalCount(params2);
//                if(count2>0){
//                    throw new SofnException("已经存在本年度的相关数据不能重复添加！");
//                }
                // 新增判重
                Integer flag = disperseUtilizeMapper.isDisperseExists(disperseUtilizeVo.getYear(), disperseUtilizeVo.getFarmerName(),
                        disperseUtilizeVo.getFarmerPhone(), countyId);
                if (flag > 0) {
                    throw new SofnException("该年度已存在相同农户数据，请勿重复添加！");
                }
                DisperseUtilize ducDisperseUtilize = new DisperseUtilize();
                ducDisperseUtilize.setId(IdUtil.getUUId());
                String farmerNo = createFillNumber(countyId);
                ducDisperseUtilize.setFillNo(farmerNo);
                ducDisperseUtilize.setAreaId(countyId);
                ducDisperseUtilize.setCityId(cityId);
                ducDisperseUtilize.setProvinceId(provinceId);
                ducDisperseUtilize.setCreateDate(disperseUtilizeVo.getAddTime());
                ducDisperseUtilize.setAddress(disperseUtilizeVo.getAddress());
                ducDisperseUtilize.setCreateUserId(userId);
                ducDisperseUtilize.setCreateUserName(UserUtil.fetchLoginUserNameInToken());
                ducDisperseUtilize.setReportArea(disperseUtilizeVo.getDepartment());
                ducDisperseUtilize.setFarmerName(disperseUtilizeVo.getFarmerName());
                ducDisperseUtilize.setFarmerNo("FS" + farmerNo);
                ducDisperseUtilize.setFarmerPhone(disperseUtilizeVo.getFarmerPhone());
                ducDisperseUtilize.setYear(disperseUtilizeVo.getYear());
                ducDisperseUtilize.setCreateTime(new Date());
                disperseUtilizeMapper.insertDisperseUtilize(ducDisperseUtilize);
                updateCountryTask(1, 0, task);

                List<DisperseUtilizeDetail> detailList = disperseUtilizeVo.getDisperseUtilizeDetailList();
                for (DisperseUtilizeDetail detail : detailList) {
                    if ((detail.getReuse().compareTo(new BigDecimal(0)) == 1) && StringUtils.isEmpty(detail.getApplication())) {
                        throw new SofnException("请填写农户 " + detail.getFarmerName() + " 的[" + detail.getStrawName() + "]的收集利用量用途类型");
                    }

                    detail.setId(IdUtil.getUUId());
                    detail.setUtilizeId(ducDisperseUtilize.getId());
                    setComprehensiveIndexValue(ducDisperseUtilize, detail);
                }

                List<DisperseUtilizeDetail> list3 = new ArrayList<>();
                List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE);
                for (SysDict sysDict : list) {
                    String name = sysDict.getDictname();
                    for (DisperseUtilizeDetail disperseUtilizeDetail : detailList) {
                        String name2 = disperseUtilizeDetail.getStrawName();
                        if (name.equals(name2)) {
                            list3.add(disperseUtilizeDetail);
                        }
                    }
                }
                disperseUtilizeDetailMapper.insertList(list3);
                //调用存储过程--->进行增量同步
                StoredProcedure storedProcedure = null;
                List<StoredProcedure> storedProcedureList = new ArrayList<>();
                for (DisperseUtilizeDetail proStillDetail : list3) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(ducDisperseUtilize.getAreaId());
                    storedProcedure.setYearData(ducDisperseUtilize.getYear());
                    storedProcedureList.add(storedProcedure);
                }
                storedProcedureMapper.insertBatch(storedProcedureList);
                LogUtil.addLog(LogEnum.LOG_TYPE_ADD.getCode(), "新增-" + disperseUtilizeVo.getYear() + "-<分散利用量填报>");
                return "添加成功!";

            } else {
                DisperseUtilize ducDisperseUtilize = new DisperseUtilize();
                ducDisperseUtilize.setId(disperseUtilizeVo.getDisperseUtilizeId());
                ducDisperseUtilize.setAddress(disperseUtilizeVo.getAddress());
                ducDisperseUtilize.setYear(disperseUtilizeVo.getYear());
                ducDisperseUtilize.setReportArea(disperseUtilizeVo.getDepartment());
                ducDisperseUtilize.setFarmerName(disperseUtilizeVo.getFarmerName());
                ducDisperseUtilize.setFarmerNo(disperseUtilizeVo.getFarmerNo());
                ducDisperseUtilize.setFarmerPhone(disperseUtilizeVo.getFarmerPhone());
                ducDisperseUtilize.setCreateDate(disperseUtilizeVo.getAddTime());
                DisperseUtilize disperseUtilize = disperseUtilizeMapper.selectById(disperseUtilizeVo.getDisperseUtilizeId());
                if (disperseUtilizeVo.getDisperseUtilizeDetailList().size() > 0) {
                    for (DisperseUtilizeDetail disperseUtilizeDetail : disperseUtilizeVo.getDisperseUtilizeDetailList()) {
                        setComprehensiveIndexValue(disperseUtilize, disperseUtilizeDetail);
                    }
                }

                disperseUtilizeMapper.updateDisperseUtilize(ducDisperseUtilize);
                ArrayList<DisperseUtilizeDetail> disperseUtilizeDetails = null;
                for (DisperseUtilizeDetail disperseUtilizeDetail : disperseUtilizeVo.getDisperseUtilizeDetailList()) {
                    disperseUtilizeDetails = Lists.newArrayList(disperseUtilizeDetail);
                    disperseUtilizeDetailMapper.updateList(disperseUtilizeDetails);
                }
                //调用存储过程--->进行增量同步
                StoredProcedure storedProcedure = null;
                List<StoredProcedure> storedProcedureList = new ArrayList<>();
                for (DisperseUtilizeDetail proStillDetail : disperseUtilizeDetails) {
                    storedProcedure = new StoredProcedure();
                    storedProcedure.setId(UUID.randomUUID().toString());
                    storedProcedure.setStrawTypeData(proStillDetail.getStrawType());
                    storedProcedure.setAreaIdData(ducDisperseUtilize.getAreaId());
                    storedProcedure.setYearData(ducDisperseUtilize.getYear());
                    storedProcedureList.add(storedProcedure);
                }
                storedProcedureMapper.insertBatch(storedProcedureList);
                LogUtil.addLog(LogEnum.LOG_TYPE_EDIT.getCode(), "编辑-" + disperseUtilizeVo.getYear() + "-<分散利用量填报>");
                return "修改成功!";
            }
        } else {
            throw new SofnException("请检查当年任务是否生成！");
        }

    }

    //判断分散利用量的填写是否与前一步操作中填写的农作物对应,不对应则直接返回，提示
    @SuppressWarnings("rawtypes")
    private void isMatch(HashSet set, List<DisperseUtilizeDetail> list) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);

        if (null == set || set.isEmpty()) {
            throw new SofnException("请检查产生量与还田量的填写是否完成!");
        }
        if (list.isEmpty()) {
            throw new SofnException("数据录入有误!");
        }

        Map<String, String> detailMap = new HashMap<String, String>();

        boolean flag = false;
        for (DisperseUtilizeDetail detail : list) {
            // 如果为空,初始化,避免空指针
            if (detail.getFertilising() == null) {
                detail.setFertilising(new BigDecimal("0"));
            }
            if (detail.getForage() == null) {
                detail.setForage(new BigDecimal("0"));
            }
            if (detail.getFuel() == null) {
                detail.setFuel(new BigDecimal("0"));
            }
            if (detail.getMaterial() == null) {
                detail.setMaterial(new BigDecimal("0"));
            }
            if (detail.getSownArea() == null) {
                detail.setSownArea(new BigDecimal("0"));
            }
            if (detail.getYieldPerMu() == null) {
                detail.setYieldPerMu(new BigDecimal("0"));
            }
            if (detail.getReuse() == null) {
                detail.setReuse(new BigDecimal("0"));
            }
            BigDecimal count = new BigDecimal(0).add(detail.getBase())
                    .add(detail.getFertilising())
                    .add(detail.getForage())
                    .add(detail.getFuel())
                    .add(detail.getMaterial())
                    .add(detail.getSownArea())
                    .add(detail.getYieldPerMu())
                    .add(detail.getReuse());
            //判断是否都为0
            if (new BigDecimal(0).compareTo(count) != 0) {
                flag = true;
            }

            //数据填写
            if (new BigDecimal(0).compareTo(count) < 0)
                detailMap.put(detail.getStrawType(), detail.getStrawName());
        }
        if (flag == false) {
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

            //最大判断范围 (-无穷, 0],[21000, +无穷)
			/*if(CropsEnum.EARLY_RICE.getMin().compareTo(detail.getYieldPerMu())==1||
					CropsEnum.SUGAR_CANE.getMax().compareTo(detail.getYieldPerMu())<1){
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("maxthreshold", "【"+CropsEnum.EARLY_RICE.getMin()+"~"+CropsEnum.SUGAR_CANE.getMax()+"】");
	            break;
			}
			if(CropsEnum.SUGAR_CANE.getMin().compareTo(detail.getYieldPerMu())>-1&&
					CropsEnum.SUGAR_CANE.getName().equalsIgnoreCase(detail.getStrawType())){//甘蔗
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.SUGAR_CANE.getMin()+"~"+CropsEnum.SUGAR_CANE.getMax()+"】");
	            break;
			}
			if((CropsEnum.CASSAVA.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.CASSAVA.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.CASSAVA.getName().equalsIgnoreCase(detail.getStrawType())){//木薯
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.CASSAVA.getMin()+"~"+CropsEnum.CASSAVA.getMax()+"】");
	            break;
			}
			if(CropsEnum.EARLY_RICE.getMax().compareTo(detail.getYieldPerMu())<1&&
					CropsEnum.EARLY_RICE.getName().equalsIgnoreCase(detail.getStrawType())){//早稻
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.EARLY_RICE.getMin()+"~"+CropsEnum.EARLY_RICE.getMax()+"】");
	            break;
			}
			if(CropsEnum.MIDDLE_RICE.getMax().compareTo(detail.getYieldPerMu())<1&&
					(CropsEnum.MIDDLE_RICE.getName().equalsIgnoreCase(detail.getStrawType())||
					CropsEnum.DOUBLE_LATE_RICE.getName().equalsIgnoreCase(detail.getStrawType()))){//中稻，晚稻
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.MIDDLE_RICE.getMin()+"~"+CropsEnum.MIDDLE_RICE.getMax()+"】");
	            break;
			}
			if((CropsEnum.WHEAT.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.WHEAT.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.WHEAT.getName().equalsIgnoreCase(detail.getStrawType())){   //小麦
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.WHEAT.getMin()+"~"+CropsEnum.WHEAT.getMax()+"】");
	            break;
			}
			if((CropsEnum.CORN.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.CORN.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.CORN.getName().equalsIgnoreCase(detail.getStrawType())){   //玉米
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.CORN.getMin()+"~"+CropsEnum.CORN.getMax()+"】");
	            break;
			}
			if((CropsEnum.POTATO.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.POTATO.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.POTATO.getName().equalsIgnoreCase(detail.getStrawType())){   //马铃薯
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.POTATO.getMin()+"~"+CropsEnum.POTATO.getMax()+"】");
	            break;
			}
			if((CropsEnum.SWEET_POTATO.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.SWEET_POTATO.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.SWEET_POTATO.getName().equalsIgnoreCase(detail.getStrawType())){   //甘薯
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.SWEET_POTATO.getMin()+"~"+CropsEnum.SWEET_POTATO.getMax()+"】");
	            break;
			}
			if((CropsEnum.PEANUT.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.PEANUT.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.PEANUT.getName().equalsIgnoreCase(detail.getStrawType())){   //花生
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.PEANUT.getMin()+"~"+CropsEnum.PEANUT.getMax()+"】");
	            break;
			}
			if((CropsEnum.RAPE.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.RAPE.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.RAPE.getName().equalsIgnoreCase(detail.getStrawType())){   //油菜
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.RAPE.getMin()+"~"+CropsEnum.RAPE.getMax()+"】");
	            break;
			}
			if((CropsEnum.SOYBEAN.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.SOYBEAN.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.SOYBEAN.getName().equalsIgnoreCase(detail.getStrawType())){   //大豆
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.SOYBEAN.getMin()+"~"+CropsEnum.SOYBEAN.getMax()+"】");
	            break;
			}
			if((CropsEnum.COTTON.getMin().compareTo(detail.getYieldPerMu())>-1||
					CropsEnum.COTTON.getMax().compareTo(detail.getYieldPerMu())<1) &&
					CropsEnum.COTTON.getName().equalsIgnoreCase(detail.getStrawType())){   //棉花
				resultMap.put("strId", detail.getStrawStr());
				resultMap.put("threshold", "【"+CropsEnum.COTTON.getMin()+"~"+CropsEnum.COTTON.getMax()+"】");
	            break;
			}*/
        }

        if (resultMap.containsKey("strId")) {
            resultMap.put("success", false);
            if (resultMap.containsKey("maxthreshold"))
                throw new SofnException("【" + resultMap.get("strId") + "】亩产值超过农作物产量最大范围" + resultMap.get("maxthreshold"));
            else if (resultMap.containsKey("threshold"))
                throw new SofnException("【" + resultMap.get("strId") + "】亩产值超过预定的范围" + resultMap.get("threshold"));
            else if (resultMap.containsKey("maxpercentage"))
                throw new SofnException("【" + resultMap.get("strId") + "】" + resultMap.get("maxpercentage"));
        }
    }

    /**
     * 生成编号
     *
     * @param areaId
     * @return
     */
    public String createFillNumber(String areaId) {
        long currentTime = System.currentTimeMillis();
        String fillNumber = areaId + currentTime + (int) (Math.random() * 10) + "";
        return fillNumber;
    }

    @Override
    public void deleteByUtilizeIds(List<String> utilizeIds) {
        UpdateWrapper<DisperseUtilizeDetail> uw = new UpdateWrapper<>();
        uw.lambda().in(DisperseUtilizeDetail::getUtilizeId, utilizeIds);
        remove(uw);
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
    public void deleteDisperseUtilizeById(String disperseUtilizeId) {
        if (StringUtils.isEmpty(disperseUtilizeId)) {
            throw new SofnException("删除编码不能为空");
        }
        Map<String, Object> result = new HashMap<String, Object>(2);
        DisperseUtilize disperseUtilize = disperseUtilizeMapper.selectDisperseUtilizeById(disperseUtilizeId);
        if (disperseUtilize == null) {
            throw new SofnException("该数据没有查询到,请核对数据！");
        }
        if (StringUtils.isEmpty(disperseUtilize.getYear()) || StringUtils.isEmpty(disperseUtilize.getAreaId())) {
            throw new SofnException("该数据异常,请核对数据！");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", disperseUtilize.getYear());
        params.put("areaId", disperseUtilize.getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == 1) {
            CountryTask task = tasks.get(0);
            if (task.getStatus() == Constants.ExamineState.REPORTED
                    || task.getStatus() == Constants.ExamineState.READ
                    || task.getStatus() == Constants.ExamineState.PASSED) {
                throw new SofnException("当前年度填报任务已经上报不能删除！");
            } else {
                updateCountryTask(-1, 0, task);
                disperseUtilizeMapper.deleteById(disperseUtilizeId);
                disperseUtilizeDetailMapper.deleteByDisperseUtilizeId(disperseUtilizeId);
                LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "删除-" + disperseUtilize.getYear() + "-<分散利用量填报>");
            }
        } else {
            throw new SofnException("当前年度填报任务数据出现问题，请核实！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteByUtilizeIds(List<String> utilizeIds) {
        List<DisperseUtilize> disperseUtilizeList = new ArrayList<>(disperseUtilizeMapper.selectBatchIds(utilizeIds));
        if (CollectionUtils.isEmpty(disperseUtilizeList)) {
            throw new SofnException("该数据没有查询到,请核对数据！");
        }
        // 获取查询结果的所有年份的数据
        Set<String> years = disperseUtilizeList.stream().map(DisperseUtilize::getYear).collect(Collectors.toSet());
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("areaId", disperseUtilizeList.get(0).getAreaId());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() == years.size()) {
            for (CountryTask task : tasks) {
                if (Constants.ExamineState.REPORTED.equals(task.getStatus())
                        || Constants.ExamineState.READ.equals(task.getStatus())
                        || Constants.ExamineState.PASSED.equals(task.getStatus())) {
                    throw new SofnException("当前年度填报任务已经上报不能删除！");
                } else {
                    // 年度任务不会有很多，所以就将就放在了for循环里
                    updateCountryTask(-1, 0, task);
                }
            }
            // 批量删除
            disperseUtilizeMapper.deleteBatchIds(utilizeIds);
            deleteByUtilizeIds(utilizeIds);
            LogUtil.addLog(LogEnum.LOG_TYPE_DELETE.getCode(), "批量删除-" + StringUtils.join(years, ",") + "-<分散利用量填报>");
        } else {
            throw new SofnException("当前年度填报任务数据出现问题，请核实！");
        }
    }

    @Override
    public PageUtils<DisperseUtilizeExportExcel> getDisperseUtilizeDetailByPage(Integer pageNo, Integer pageSize, String year, String userName, String countyId, String dateBegin, String dateEnd) {
        PageHelper.offsetPage(pageNo, pageSize);

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("userName", userName);
        params.put("areaId", countyId);
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
        List<DisperseUtilizeDetail> list = disperseUtilizeDetailMapper.findExportDetailByCondion(params);
        List<DisperseUtilizeExportExcel> excelList = new ArrayList<>(list.size());
        for (DisperseUtilizeDetail detail : list) {
            DisperseUtilizeExportExcel excel = new DisperseUtilizeExportExcel();
            BeanUtils.copyProperties(detail, excel);
            //对区县ID转换成区划名称                 //从数据库查询得到下面的为6位数字的code
            //excel.setCountryName(getAreaNameByCode(detail.getCountryName()));

            excelList.add(excel);
        }

        return PageUtils.getPageUtils(new PageInfo(excelList));
    }

    @Override
    public List<DisperseUtilizeExportExcel> getDisperseUtilizeDetailExl(String year, String userName, String countyId, String dateBegin, String dateEnd) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("userName", userName);
        params.put("areaId", countyId);
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
        List<DisperseUtilizeDetail> list = disperseUtilizeDetailMapper.findExportDetailByCondion(params);

        //导出排序
        // 模板信息
        List<DisperseUtilizeDetail> list0 = this.getDisperseUtilizeDetail("");
        //调整表格顺序
        List<DisperseUtilizeDetail> list3 = new ArrayList<>();
        for (DisperseUtilizeDetail disperseUtilizeDetail : list0) {
            String name = disperseUtilizeDetail.getStrawName();
            for (DisperseUtilizeDetail disperseUtilizeDetail12 : list) {
                String name2 = disperseUtilizeDetail12.getStrawName();
                if (name.equals(name2)) {
                    list3.add(disperseUtilizeDetail12);
                }
            }
        }

        List<DisperseUtilizeExportExcel> excelList = new ArrayList<>(list.size());
        for (DisperseUtilizeDetail detail : list) {
            DisperseUtilizeExportExcel excel = new DisperseUtilizeExportExcel();
            BeanUtils.copyProperties(detail, excel);
            //对区县ID转换成区划名称                 //从数据库查询得到下面的为6位数字的code
            excel.setFarmerNo("FN" + detail.getFarmerNo());
            excel.setAddress(detail.getCountryName());
            excelList.add(excel);
        }
        return excelList;
    }


    // 根据区划ID获取区划名称
    // 若存在则直接取出；否则，查支持平台，并存入缓存中
    private String getAreaNameByCode(String codes) {
        return sysApi.getRegionNamesByCodes(codes);
    }

    /**
     * 设置综合指标的值
     *
     * @param detail
     */
    public void setComprehensiveIndexValue(DisperseUtilize proStill, DisperseUtilizeDetail detail) {
        DisperseUtilizeDetail disperseUtilizeDetails = disperseUtilizeDetailMapper.disAssignment(proStill.getAreaId(), proStill.getYear(), detail.getStrawType());
        //查询pro可收集量
        List<ProStillDetail> listByYearAndAreaId = proStillDetailMapper.getListByYearAndAreaId(proStill.getYear(), proStill.getAreaId(), detail.getStrawType());
        BigDecimal collectResource = new BigDecimal(0);
        for (ProStillDetail proStillDetail : listByYearAndAreaId) {
            collectResource = collectResource.add(proStillDetail.getGrainYield().multiply(proStillDetail.getGrassValleyRatio()).multiply(proStillDetail.getCollectionRatio()));
        }
        if (disperseUtilizeDetails != null) {
            if (disperseUtilizeDetails.getYieldAllNum() != null && disperseUtilizeDetails.getYieldAllNum().compareTo(new BigDecimal(0)) != 0) {
                detail.setDisperseFertilisingData(disperseUtilizeDetails.getDisperseFertilising().divide(disperseUtilizeDetails.getYieldAllNum(), 10, RoundingMode.HALF_UP).multiply(collectResource));
                detail.setDisperseForageData(disperseUtilizeDetails.getDisperseForage().divide(disperseUtilizeDetails.getYieldAllNum(), 10, RoundingMode.HALF_UP).multiply(collectResource));
                detail.setDisperseFuelData(disperseUtilizeDetails.getDisperseFuel().divide(disperseUtilizeDetails.getYieldAllNum(), 10, RoundingMode.HALF_UP).multiply(collectResource));
                detail.setDisperseBaseData(disperseUtilizeDetails.getDisperseBase().divide(disperseUtilizeDetails.getYieldAllNum(), 10, RoundingMode.HALF_UP).multiply(collectResource));
                detail.setDisperseMaterialData(disperseUtilizeDetails.getDisperseMaterial().divide(disperseUtilizeDetails.getYieldAllNum(), 10, RoundingMode.HALF_UP).multiply(collectResource));
                detail.setYieldAllNumData(disperseUtilizeDetails.getYieldAllNum());
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void statisticalAssignment() {
        for (int i = 0; i < 10000; i++) {
            IPage<DisperseUtilizeDetail> page = new Page<>(i, 1000);
            QueryWrapper<DisperseUtilizeDetail> objectQueryWrapper = new QueryWrapper<>();
            List<DisperseUtilizeDetail> list = disperseUtilizeDetailMapper.selectPage(page, objectQueryWrapper).getRecords();
            QueryWrapper<DisperseUtilize> objectQueryWrapper2;
            for (DisperseUtilizeDetail disperseUtilizeDetail : list) {
                objectQueryWrapper2 = new QueryWrapper<>();
                objectQueryWrapper2.eq("id", disperseUtilizeDetail.getUtilizeId());
                DisperseUtilize disperseUtilize = disperseUtilizeMapper.selectOne(objectQueryWrapper2);
                setComprehensiveIndexValue(disperseUtilize, disperseUtilizeDetail);
                disperseUtilizeDetailMapper.updateById(disperseUtilizeDetail);
            }
        }
    }
/*
    public static void main(String[] args) {
        DisperseUtilizeDetailService disperseUtilizeDetailService = new DisperseUtilizeDetailServiceImpl();
        System.out.println(disperseUtilizeDetailService.createFillNumber("NF510113").length());
    }*/
}
