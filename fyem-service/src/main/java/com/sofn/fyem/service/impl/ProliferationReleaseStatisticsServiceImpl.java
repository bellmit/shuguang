package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.enums.AuditStatueEnum;
import com.sofn.fyem.enums.SpeciesTypeEnum;
import com.sofn.fyem.enums.YesOrNoEnum;
import com.sofn.fyem.mapper.*;
import com.sofn.fyem.model.*;
import com.sofn.fyem.service.ProliferationReleaseStatisticsService;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.sysapi.bean.SysOrganizationForm;
import com.sofn.fyem.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 中央财政农业资源及生态保护补助项目增殖放流情况统计表模块
 * @author Administrator
 */
@Service
public class ProliferationReleaseStatisticsServiceImpl extends ServiceImpl<ProliferationReleaseStatisticsMapper, ProliferationReleaseStatistics> implements ProliferationReleaseStatisticsService {

    /**
     * 增值流放Mapper
     */
    @Autowired
    private ProliferationReleaseStatisticsMapper proliferationReleaseStatisticsMapper;

    /**
     * 水生物放流基础信息Mapper
     */
    @Autowired
    private BasicProliferationReleaseMapper basicProliferationReleaseMapper;

    /**
     * 上报信息管理Mapper
     */
    @Autowired
    private ReporManagementMapper reporManagementMapper;

    /**
     * 省级审核Mapper
     */
    @Autowired
    private ProvinceAuditMapper provinceAuditMapper;

    /**
     * 部级审核Mapper
     */
    @Autowired
    private MinisterAuditMapper ministerAuditMapper;

    /**
     * 上报信息管理Service
     */
    @Autowired
    private ReporManagementService reporManagementService;

    @Autowired
    private SysRegionApi sysRegionApi;

    @Override
    public List<ReleaseStatisticsForm> getReleaseStatisticsForm(Map<String, Object> params) {

        List<ReleaseStatisticsForm> releaseStatisticsFormList = new ArrayList<>();
        ReleaseStatisticsForm releaseStatisticsForm = new ReleaseStatisticsForm();
        List<ProliferationReleaseStatistics> releaseStatisticsList =
                proliferationReleaseStatisticsMapper.getProliferationReleaseStatisticsList(params);
        if(releaseStatisticsList.size()>0){
            ProliferationReleaseStatistics releaseStatistics = releaseStatisticsList.get(0);
            releaseStatisticsForm.setBelongYear(releaseStatistics.getBelongYear());
            releaseStatisticsForm.setCreateTime(releaseStatistics.getCreateTime());
            releaseStatisticsFormList.add(releaseStatisticsForm);
        }

        return releaseStatisticsFormList;
    }

    @Override
    public List<ProliferationReleaseStatistics> getProliferationReleaseStatisticsList(Map<String, Object> params) {
        return proliferationReleaseStatisticsMapper.getProliferationReleaseStatisticsList(params);
    }

    @Override
    public ReleaseStatisticsSpeciesVo getReleaseStatisticsSpeciesVo(Map<String, Object> params) {

        String freshWaterCode = SpeciesTypeEnum.FRESHWATERSPECIES.getCode();
        String seaWaterCode = SpeciesTypeEnum.SEAWATERSPECIES.getCode();
        String rareCode = SpeciesTypeEnum.RARESPECIES.getCode();
        ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo = new ReleaseStatisticsSpeciesVo();

        List<ProliferationReleaseStatistics> releaseStatisticsList =
                proliferationReleaseStatisticsMapper.getProliferationReleaseStatisticsList(params);
        releaseStatisticsList.forEach(
                baseData ->{
                    String speciesType = baseData.getSpeciesType();
                    //判断当前数据物种类型(淡水物种.海水物种.珍稀物种)
                    if(freshWaterCode.equals(speciesType)){
                        ReleaseStatisticsFreshWaterSpeciesVo freshWaterSpeciesVo = ReleaseStatisticsFreshWaterSpeciesVo.getReleaseStatisticsFreshWaterSpeciesVo(baseData);
                        releaseStatisticsSpeciesVo.setProvinceId(freshWaterSpeciesVo.getProvinceId());
                        String cityId = freshWaterSpeciesVo.getCityId();
                        String countyId = freshWaterSpeciesVo.getCountyId();
                        if(StringUtils.isNotBlank(cityId)){
                            releaseStatisticsSpeciesVo.setCityId(cityId);
                        }
                        if(StringUtils.isNotBlank(countyId)){
                            releaseStatisticsSpeciesVo.setCountyId(countyId);
                        }
                        releaseStatisticsSpeciesVo.setRoleCode(freshWaterSpeciesVo.getRoleCode());
                        releaseStatisticsSpeciesVo.setBelongYear(freshWaterSpeciesVo.getBelongYear());
                        releaseStatisticsSpeciesVo.setStatus(freshWaterSpeciesVo.getStatus());
                        releaseStatisticsSpeciesVo.setReleaseStatisticsFreshWaterSpeciesVo(freshWaterSpeciesVo);
                    }
                    if(seaWaterCode.equals(speciesType)){
                        ReleaseStatisticsSeaWaterSpeciesVo seaWaterSpeciesVo = ReleaseStatisticsSeaWaterSpeciesVo.getReleaseStatisticsSeaWaterSpeciesVo(baseData);
                        releaseStatisticsSpeciesVo.setReleaseStatisticsSeaWaterSpeciesVo(seaWaterSpeciesVo);
                    }
                    if(rareCode.equals(speciesType)){
                        ReleaseStatisticsRareSpeciesVo rareSpeciesVo = ReleaseStatisticsRareSpeciesVo.getReleaseStatisticsRareSpeciesVo(baseData);
                        releaseStatisticsSpeciesVo.setReleaseStatisticsRareSpeciesVo(rareSpeciesVo);
                    }
                }
        );
        return releaseStatisticsSpeciesVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStatus(Map<String, Object> params) {
        return proliferationReleaseStatisticsMapper.updateStatus(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReleaseStatisticsSpeciesVo(ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo) {

        //获取登录用户信息
        User logUser = UserUtil.getLoginUser();
        //获取当前用户id
        String userId = logUser != null ? logUser.getId() : null;

        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",releaseStatisticsSpeciesVo.getBelongYear());
        //params.put("createUserId",userId);
        User loginUser = UserUtil.getLoginUser();
        if(loginUser == null || loginUser.getOrganizationId().isEmpty()){
            throw new SofnException("新增失败，用户信息异常！组织id为空");
        }
        params.put("organizationId", loginUser.getOrganizationId());
        List<ProliferationReleaseStatistics> list = proliferationReleaseStatisticsMapper.getProliferationReleaseStatisticsList(params);
        if(list.size()>0){
            throw new SofnException("当前年增值流放信息已存在!");
        }
        //区县级新增条件:市级未未上报或市级退回状态可新增
        if(releaseStatisticsSpeciesVo.getCountyId()!=null && !releaseStatisticsSpeciesVo.getCountyId().isEmpty()){
            boolean b = this.countyOperationData(releaseStatisticsSpeciesVo);
            if(!b){
                throw new SofnException("增殖放流信息新增失败，已上报！");
            }
        }

        if (!reporManagementService.able2Modify(releaseStatisticsSpeciesVo.getBelongYear())) {
            throw new SofnException("存在已上报未退回的数据");
        }
        //获取组织相关信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //获取当前机构id
        String orgId = sysOrganization.getId();
        //获取当前机构名称
        String orgName = sysOrganization.getOrganizationName();
        String provinceId = releaseStatisticsSpeciesVo.getProvinceId();
        String cityId = releaseStatisticsSpeciesVo.getCityId();
        String countyId = releaseStatisticsSpeciesVo.getCountyId();

        //根据条件判断是否存在上报信息数据
        Map<String,Object> reporParams = Maps.newHashMap();
        reporParams.put("belongYear",releaseStatisticsSpeciesVo.getBelongYear());
        reporParams.put("organizationId",orgId);

        ReporManagement reporManagement = new ReporManagement();
        List<ReporManagement> reporManagementList = reporManagementService.getReporManagementListByQuery(reporParams);
        if(reporManagementList.size()>0){
            //存在上报信息
            reporManagement = reporManagementList.get(0);
            reporManagement.setHasProliferationReleaseStatistics(YesOrNoEnum.YES.getCode());
            reporManagementService.updateReporManagement(reporManagement);
        }else{
            //不存在上报信息
            reporManagement.setId(IdUtil.getUUId());
            reporManagement.setBelongYear(releaseStatisticsSpeciesVo.getBelongYear());
            reporManagement.setHasBasicProliferationRelease(YesOrNoEnum.NO.getCode());
            reporManagement.setHasProliferationReleaseStatistics(YesOrNoEnum.YES.getCode());
            reporManagement.setOrganizationId(orgId);
            reporManagement.setOrganizationName(orgName);
            reporManagement.setStatus(releaseStatisticsSpeciesVo.getStatus());
            reporManagement.setRoleCode(releaseStatisticsSpeciesVo.getRoleCode());
            reporManagement.setCreateUserId(userId);
            reporManagement.setCreateUserName(logUser.getNickname());
            reporManagement.setCreateTime(new Date());
            reporManagementService.addReporManagement(reporManagement);
        }

        //获取淡水物种信息
        ReleaseStatisticsFreshWaterSpeciesVo freshWaterSpeciesVo =
                releaseStatisticsSpeciesVo.getReleaseStatisticsFreshWaterSpeciesVo();
        if(freshWaterSpeciesVo != null){
            //放入淡水物种信息id,所属年度,组织id.组织名称,创建人id,创建时间
            freshWaterSpeciesVo.setId(IdUtil.getUUId());
            freshWaterSpeciesVo.setBelongYear(releaseStatisticsSpeciesVo.getBelongYear());
            freshWaterSpeciesVo.setProvinceId(provinceId);
            freshWaterSpeciesVo.setCityId(cityId);
            freshWaterSpeciesVo.setCountyId(countyId);
            freshWaterSpeciesVo.setOrganizationId(orgId);
            freshWaterSpeciesVo.setOrganizationName(orgName);
            freshWaterSpeciesVo.setCreateUserId(userId);
            freshWaterSpeciesVo.setRoleCode(releaseStatisticsSpeciesVo.getRoleCode());
            freshWaterSpeciesVo.setCreateTime(new Date());
            freshWaterSpeciesVo.setReporManagementId(reporManagement.getId());
            //保存淡水物种信息
            this.save(freshWaterSpeciesVo);
        }
        //获取海水物种信息
        ReleaseStatisticsSeaWaterSpeciesVo seaWaterSpeciesVo =
                releaseStatisticsSpeciesVo.getReleaseStatisticsSeaWaterSpeciesVo();
        if(seaWaterSpeciesVo != null){
            //放入海水物种信息id,所属年度,组织id.组织名称,创建人id,创建时间
            seaWaterSpeciesVo.setId(IdUtil.getUUId());
            seaWaterSpeciesVo.setBelongYear(releaseStatisticsSpeciesVo.getBelongYear());
            seaWaterSpeciesVo.setProvinceId(provinceId);
            seaWaterSpeciesVo.setCityId(cityId);
            seaWaterSpeciesVo.setCountyId(countyId);
            seaWaterSpeciesVo.setOrganizationId(orgId);
            seaWaterSpeciesVo.setOrganizationName(orgName);
            seaWaterSpeciesVo.setCreateUserId(userId);
            seaWaterSpeciesVo.setRoleCode(releaseStatisticsSpeciesVo.getRoleCode());
            seaWaterSpeciesVo.setCreateTime(new Date());
            seaWaterSpeciesVo.setReporManagementId(reporManagement.getId());
            this.save(seaWaterSpeciesVo);
        }
        //获取珍稀物种信息
        ReleaseStatisticsRareSpeciesVo rareSpeciesVo = releaseStatisticsSpeciesVo.getReleaseStatisticsRareSpeciesVo();
        if(rareSpeciesVo != null){
            //放入珍稀物种信息id,所属年度,组织id.组织名称,创建人id,创建时间
            rareSpeciesVo.setId(IdUtil.getUUId());
            rareSpeciesVo.setBelongYear(releaseStatisticsSpeciesVo.getBelongYear());
            rareSpeciesVo.setProvinceId(provinceId);
            rareSpeciesVo.setCityId(cityId);
            rareSpeciesVo.setCountyId(countyId);
            rareSpeciesVo.setOrganizationId(orgId);
            rareSpeciesVo.setOrganizationName(orgName);
            rareSpeciesVo.setCreateUserId(userId);
            rareSpeciesVo.setRoleCode(releaseStatisticsSpeciesVo.getRoleCode());
            rareSpeciesVo.setCreateTime(new Date());
            rareSpeciesVo.setReporManagementId(reporManagement.getId());
            this.save(rareSpeciesVo);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReleaseStatisticsSpeciesVo(ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo) {
        if (!reporManagementService.able2Modify(releaseStatisticsSpeciesVo.getBelongYear())) {
            throw new SofnException("存在已上报未退回的数据");
        }
        //获取淡水物种信息
        ReleaseStatisticsFreshWaterSpeciesVo freshWaterSpeciesVo =
                releaseStatisticsSpeciesVo.getReleaseStatisticsFreshWaterSpeciesVo();
        if(freshWaterSpeciesVo != null){
           this.updateById(freshWaterSpeciesVo);
        }
        //获取海水物种信息
        ReleaseStatisticsSeaWaterSpeciesVo seaWaterSpeciesVo =
                releaseStatisticsSpeciesVo.getReleaseStatisticsSeaWaterSpeciesVo();
        if(seaWaterSpeciesVo != null){
            this.updateById(seaWaterSpeciesVo);
        }
        //获取珍稀物种信息
        ReleaseStatisticsRareSpeciesVo rareSpeciesVo = releaseStatisticsSpeciesVo.getReleaseStatisticsRareSpeciesVo();
        if(rareSpeciesVo != null){
            this.updateById(rareSpeciesVo);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeReleaseStatisticsSpeciesVo(Map<String, Object> params) {

        List<BasicProliferationRelease> basicList =
                basicProliferationReleaseMapper.getBasicProliferationReleaseListByQuery(params);
        //判断是否存在水生物放流信息数据
        if(basicList.size()>0){
            //当前年存在水生物放流信息
        }else{
            //不存在水生物放流信息
            reporManagementService.deleteReporManagement(params);
        }
        proliferationReleaseStatisticsMapper.deleteProliferationReleaseStatistics(params);
    }

    @Override
    public ReleaseStatisticsCountVo getReleaseStatisticsCount(Map<String, Object> params) {
        Map<String, Object> freshParam = params;
        freshParam.put("speciesType","0");
        ProliferationReleaseStatistics freshWater =
                proliferationReleaseStatisticsMapper.getReleaseStatisticsCount(freshParam);
        Map<String, Object> seaParam = params;
        seaParam.put("speciesType","1");
        ProliferationReleaseStatistics seaWater =
                proliferationReleaseStatisticsMapper.getReleaseStatisticsCount(seaParam);
        Map<String, Object> rareParam = params;
        rareParam.put("speciesType","2");
        ProliferationReleaseStatistics rare =
                proliferationReleaseStatisticsMapper.getReleaseStatisticsCount(rareParam);
        ReleaseStatisticsCountVo countVo = new ReleaseStatisticsCountVo();
        if(null != freshWater && null != seaWater && null != rare){

            //淡水
            countVo.setFreshWaterPlanReleaseAmount(freshWater.getPlanReleaseAmount());
            countVo.setFreshWaterPlanReleaseCapital(freshWater.getPlanReleaseCapital());
            countVo.setFreshWaterPracticalReleaseAmount(freshWater.getPracticalReleaseAmount());
            countVo.setFreshWaterPracticalReleaseCapital(freshWater.getPracticalReleaseCapital());
            countVo.setFreshWaterYearReleaseAmount(freshWater.getYearReleaseAmount());
            countVo.setFreshWaterYearReleaseCapital(freshWater.getYearReleaseCapital());
            //海水
            countVo.setSeaWaterPlanReleaseAmount(seaWater.getPlanReleaseAmount());
            countVo.setSeaWaterPlanReleaseCapital(seaWater.getPlanReleaseCapital());
            countVo.setSeaWaterPracticalReleaseAmount(seaWater.getPracticalReleaseAmount());
            countVo.setSeaWaterPracticalReleaseCapital(seaWater.getPracticalReleaseCapital());
            countVo.setSeaWaterYearReleaseAmount(seaWater.getYearReleaseAmount());
            countVo.setSeaWaterYearReleaseCapital(seaWater.getYearReleaseCapital());
            //珍稀
            countVo.setRarePlanReleaseAmount(rare.getPlanReleaseAmount());
            countVo.setRarePlanReleaseCapital(rare.getPlanReleaseCapital());
            countVo.setRarePracticalReleaseAmount(rare.getPracticalReleaseAmount());
            countVo.setRarePracticalReleaseCapital(rare.getPracticalReleaseCapital());
            countVo.setRareYearReleaseAmount(rare.getYearReleaseAmount());
            countVo.setRareYearReleaseCapital(rare.getYearReleaseCapital());
            //合计
            double sumPlanReleaseAmount = rare.getPlanReleaseAmount()+seaWater.getPlanReleaseAmount()+freshWater.getPlanReleaseAmount();
            countVo.setSumPlanReleaseAmount(sumPlanReleaseAmount);
            double sumPlanReleaseCapital = rare.getPlanReleaseCapital()+seaWater.getPlanReleaseCapital()+freshWater.getPlanReleaseCapital();
            countVo.setSumPlanReleaseCapital(sumPlanReleaseCapital);
            double sumPracticalReleaseAmount = rare.getPracticalReleaseAmount()+seaWater.getPracticalReleaseAmount()+freshWater.getPracticalReleaseAmount();
            countVo.setSumPracticalReleaseAmount(sumPracticalReleaseAmount);
            double sumPracticalReleaseCapital = rare.getPracticalReleaseCapital()+seaWater.getPracticalReleaseCapital()+freshWater.getPracticalReleaseCapital();
            countVo.setSumPracticalReleaseCapital(sumPracticalReleaseCapital);
            double sumYearReleaseAmount = rare.getYearReleaseAmount()+seaWater.getYearReleaseAmount()+freshWater.getYearReleaseAmount();
            countVo.setSumYearReleaseAmount(sumYearReleaseAmount);
            double sumYearReleaseCapital = rare.getYearReleaseCapital()+seaWater.getYearReleaseCapital()+freshWater.getYearReleaseCapital();
            countVo.setSumYearReleaseCapital(sumYearReleaseCapital);
        }
        return countVo;
    }

    @Override
    public List<ReleaseStatisticsCountVo> getReleaseStatisticsAllCount(Map<String, Object> params) {
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        List<ReleaseStatisticsCountVo>  countVoList = new ArrayList<>();
        ReleaseStatisticsCountVo rCountVo = new ReleaseStatisticsCountVo();
        List<ProliferationReleaseStatistics> allList = new ArrayList<>();

        if(roleColde.contains("fyem_county") ||
                roleColde.contains("fyem_city_add") || roleColde.contains("fyem_city") ||
                    roleColde.contains("fyem_prov_add") || roleColde.contains("fyem_prov")){

            //省级用户
            if(roleColde.contains("fyem_prov_add") || roleColde.contains("fyem_prov")){
                //获取满足条件的淡水物种统计数据
                Map<String, Object> freshParam = params;
                freshParam.put("speciesType","0");
                List<ProliferationReleaseStatistics> freshByCtiy = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCity(freshParam);
                //获取满足条件的海水物种统计数据
                Map<String, Object> seaParam = params;
                seaParam.put("speciesType","1");
                List<ProliferationReleaseStatistics> seaByCtiy = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCity(seaParam);
                //获取满足条件的珍稀物种统计数据
                Map<String, Object> rareParam = params;
                rareParam.put("speciesType","2");
                List<ProliferationReleaseStatistics> rareByCtiy = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCity(rareParam);
                if(freshByCtiy.size()>0 && seaByCtiy.size()>0 && rareByCtiy.size()>0){
                    List<ProliferationReleaseStatistics> allList02 = new ArrayList<>();
                    allList.addAll(freshByCtiy);
                    allList.addAll(seaByCtiy);
                    allList.addAll(rareByCtiy);
                    allList.forEach(
                            x->{
                                if(StringUtils.isNotBlank(x.getCityId())){
                                    allList02.add(x);
                                }
                            }
                    );
                    Map<String, List<ProliferationReleaseStatistics>> collect = allList02.stream().collect(Collectors.groupingBy(ProliferationReleaseStatistics::getCityId));
                    getValue(countVoList, collect);
                    rCountVo.setAreaName("全省");
                    //淡水
                    getValueByAll(countVoList, rCountVo);
                }

            }
            //市级用户
            if(roleColde.contains("fyem_city_add") || roleColde.contains("fyem_city")){
                //获取满足条件的淡水物种统计数据
                Map<String, Object> freshParam = params;
                freshParam.put("speciesType","0");
                List<ProliferationReleaseStatistics> freshByCounty = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCounty(freshParam);
                //获取满足条件的海水物种统计数据
                Map<String, Object> seaParam = params;
                seaParam.put("speciesType","1");
                List<ProliferationReleaseStatistics> seaByCounty = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCounty(seaParam);
                //获取满足条件的珍稀物种统计数据
                Map<String, Object> rareParam = params;
                rareParam.put("speciesType","2");
                List<ProliferationReleaseStatistics> rareByCounty = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCounty(rareParam);
                if(freshByCounty.size()>0 && seaByCounty.size() >0 && rareByCounty.size()>0){
                    List<ProliferationReleaseStatistics> allList02 = new ArrayList<>();
                    allList.addAll(freshByCounty);
                    allList.addAll(seaByCounty);
                    allList.addAll(rareByCounty);
                    allList.forEach(
                            x->{
                                if(StringUtils.isNotBlank(x.getCountyId())){
                                    allList02.add(x);
                                }
                            }
                    );
                    Map<String, List<ProliferationReleaseStatistics>> collect = allList02.stream().collect(Collectors.groupingBy(ProliferationReleaseStatistics::getCountyId));
                    getValue(countVoList, collect);
                    rCountVo.setAreaName("全市");
                    //淡水
                    getValueByAll(countVoList, rCountVo);
                }
            }
            //区县级用户
            if(roleColde.contains("fyem_county")){
                String loginUserId = UserUtil.getLoginUserId();
                //获取满足条件的淡水物种统计数据
                Map<String, Object> freshParam = params;
                freshParam.put("speciesType","0");
                freshParam.put("createUserId",loginUserId);
                List<ProliferationReleaseStatistics> freshByUser = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCounty(freshParam);
                //获取满足条件的海水物种统计数据
                Map<String, Object> seaParam = params;
                seaParam.put("speciesType","1");
                seaParam.put("createUserId",loginUserId);
                List<ProliferationReleaseStatistics> seaByUser = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCounty(seaParam);
                //获取满足条件的珍稀物种统计数据
                Map<String, Object> rareParam = params;
                rareParam.put("speciesType","2");
                rareParam.put("createUserId",loginUserId);
                List<ProliferationReleaseStatistics> rareByUser = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByCounty(rareParam);
                if(freshByUser.size() >0 && seaByUser.size()>0 && rareByUser.size()>0){

                    allList.addAll(freshByUser);
                    allList.addAll(seaByUser);
                    allList.addAll(rareByUser);
                    Map<String, List<ProliferationReleaseStatistics>> collect = allList.stream().collect(Collectors.groupingBy(ProliferationReleaseStatistics::getCountyId));
                    getValue(countVoList, collect);
                    //rCountVo.setAreaName("区县");
                    //getValueByAll(countVoList, rCountVo);
                }
            }
        }else{
            //部级用户
            //获取满足条件的淡水物种统计数据
            Map<String, Object> freshParam = params;
            freshParam.put("speciesType","0");
            List<ProliferationReleaseStatistics> freshByProvince = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByProvince(freshParam);
            //获取满足条件的海水物种统计数据
            Map<String, Object> seaParam = params;
            seaParam.put("speciesType","1");
            List<ProliferationReleaseStatistics> seaByProvince = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByProvince(seaParam);
            //获取满足条件的珍稀物种统计数据
            Map<String, Object> rareParam = params;
            rareParam.put("speciesType","2");
            List<ProliferationReleaseStatistics> rareByProvince = proliferationReleaseStatisticsMapper.getReleaseStatisticsCountByProvince(rareParam);
            if(freshByProvince.size()>0 && seaByProvince.size()>0 && rareByProvince.size()>0){
                List<ProliferationReleaseStatistics> allList02 = new ArrayList<>();
                allList.addAll(freshByProvince);
                allList.addAll(seaByProvince);
                allList.addAll(rareByProvince);
                allList.forEach(
                        x->{
                            if(StringUtils.isNotBlank(x.getProvinceId())){
                                allList02.add(x);
                            }
                        }
                );
                Map<String, List<ProliferationReleaseStatistics>> collect = allList02.stream().collect(Collectors.groupingBy(ProliferationReleaseStatistics::getProvinceId));
                getValue(countVoList, collect);
                rCountVo.setAreaName("全国");
                //淡水
                Map<String, Object> allFreshParam = Maps.newHashMap();
                allFreshParam.put("belongYear",params.get("belongYear").toString());
                allFreshParam.put("speciesType","0");
                ProliferationReleaseStatistics allFresh = proliferationReleaseStatisticsMapper.getReleaseStatisticsCount(allFreshParam);
                rCountVo.setFreshWaterPlanReleaseAmount(allFresh.getPlanReleaseAmount());
                rCountVo.setFreshWaterPlanReleaseCapital(allFresh.getPlanReleaseCapital());
                rCountVo.setFreshWaterPracticalReleaseAmount(allFresh.getPracticalReleaseAmount());
                rCountVo.setFreshWaterPracticalReleaseCapital(allFresh.getPracticalReleaseCapital());
                rCountVo.setFreshWaterYearReleaseAmount(allFresh.getYearReleaseAmount());
                rCountVo.setFreshWaterYearReleaseCapital(allFresh.getYearReleaseCapital());
                //海水
                Map<String, Object> allSeaParam = Maps.newHashMap();
                allSeaParam.put("belongYear",params.get("belongYear").toString());
                allSeaParam.put("speciesType","1");
                ProliferationReleaseStatistics allSea = proliferationReleaseStatisticsMapper.getReleaseStatisticsCount(allSeaParam);
                rCountVo.setSeaWaterPlanReleaseAmount(allSea.getPlanReleaseAmount());
                rCountVo.setSeaWaterPlanReleaseCapital(allSea.getPlanReleaseCapital());
                rCountVo.setSeaWaterPracticalReleaseAmount(allSea.getPracticalReleaseAmount());
                rCountVo.setSeaWaterPracticalReleaseCapital(allSea.getPracticalReleaseCapital());
                rCountVo.setSeaWaterYearReleaseAmount(allSea.getYearReleaseAmount());
                rCountVo.setSeaWaterYearReleaseCapital(allSea.getYearReleaseCapital());
                //珍稀
                Map<String, Object> allRareParam = Maps.newHashMap();
                allRareParam.put("belongYear",params.get("belongYear").toString());
                allRareParam.put("speciesType","2");
                ProliferationReleaseStatistics allRare = proliferationReleaseStatisticsMapper.getReleaseStatisticsCount(allRareParam);
                rCountVo.setRarePlanReleaseAmount(allRare.getPlanReleaseAmount());
                rCountVo.setRarePlanReleaseCapital(allRare.getPlanReleaseCapital());
                rCountVo.setRarePracticalReleaseAmount(allRare.getPracticalReleaseAmount());
                rCountVo.setRarePracticalReleaseCapital(allRare.getPracticalReleaseCapital());
                rCountVo.setRareYearReleaseAmount(allRare.getYearReleaseAmount());
                rCountVo.setRareYearReleaseCapital(allRare.getYearReleaseCapital());
            }

        }
        if(roleColde.contains("fyem_county")){

        }else{
            countVoList.add(rCountVo);
        }

        Collections.sort(countVoList);
        return countVoList;
    }

    @Override
    public List<ProliferationReleaseStatistics> getProliferationReleaseStatisticsByBelongYearAndOrgId(final String belongYear, String orgId) {
        if (org.apache.commons.lang3.StringUtils.isAnyBlank(belongYear, orgId)) {
            return Collections.emptyList();
        }

        List<ProliferationReleaseStatistics> list = this.list(Wrappers.<ProliferationReleaseStatistics>lambdaQuery()
                .eq(ProliferationReleaseStatistics::getBelongYear, belongYear)
                .eq(ProliferationReleaseStatistics::getOrganizationId, orgId));
        Result<SysOrganizationForm> result = sysRegionApi.getOrgInfoById(orgId);
        if (Objects.equals(result.getCode(), Result.CODE)) {
            list.addAll(this.getProliferationReleaseStatisticsByBelongYearAndOrgId(belongYear, result.getData().getParentId()));
        }
        return list;
    }

    private void getValueByAll(List<ReleaseStatisticsCountVo> countVoList, ReleaseStatisticsCountVo rCountVo) {
        //淡水
        double freshPlanReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getFreshWaterPlanReleaseAmount).sum();
        double freshPlanReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getFreshWaterPlanReleaseCapital).sum();
        double freshPracticalReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getFreshWaterPracticalReleaseAmount).sum();
        double freshPracticalReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getFreshWaterPracticalReleaseCapital).sum();
        double freshYearReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getFreshWaterYearReleaseAmount).sum();
        double freshYearReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getFreshWaterYearReleaseCapital).sum();
        rCountVo.setFreshWaterPlanReleaseAmount(freshPlanReleaseAmount);
        rCountVo.setFreshWaterPlanReleaseCapital(freshPlanReleaseCapital);
        rCountVo.setFreshWaterPracticalReleaseAmount(freshPracticalReleaseAmount);
        rCountVo.setFreshWaterPracticalReleaseCapital(freshPracticalReleaseCapital);
        rCountVo.setFreshWaterYearReleaseAmount(freshYearReleaseAmount);
        rCountVo.setFreshWaterYearReleaseCapital(freshYearReleaseCapital);
        //海水
        double seaPlanReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getSeaWaterPlanReleaseAmount).sum();
        double seaPlanReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getSeaWaterPlanReleaseCapital).sum();
        double seaPracticalReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getSeaWaterPracticalReleaseAmount).sum();
        double seaPracticalReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getSeaWaterPracticalReleaseCapital).sum();
        double seaYearReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getSeaWaterYearReleaseAmount).sum();
        double seaYearReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getSeaWaterYearReleaseCapital).sum();
        rCountVo.setSeaWaterPlanReleaseAmount(seaPlanReleaseAmount);
        rCountVo.setSeaWaterPlanReleaseCapital(seaPlanReleaseCapital);
        rCountVo.setSeaWaterPracticalReleaseAmount(seaPracticalReleaseAmount);
        rCountVo.setSeaWaterPracticalReleaseCapital(seaPracticalReleaseCapital);
        rCountVo.setSeaWaterYearReleaseAmount(seaYearReleaseAmount);
        rCountVo.setSeaWaterYearReleaseCapital(seaYearReleaseCapital);
        //珍稀
        double rarePlanReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getRarePlanReleaseAmount).sum();
        double rarePlanReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getRarePlanReleaseCapital).sum();
        double rarePracticalReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getRarePracticalReleaseAmount).sum();
        double rarePracticalReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getRarePracticalReleaseCapital).sum();
        double rareYearReleaseAmount = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getRareYearReleaseAmount).sum();
        double rareYearReleaseCapital = countVoList.stream().mapToDouble(ReleaseStatisticsCountVo::getRareYearReleaseCapital).sum();
        rCountVo.setRarePlanReleaseAmount(rarePlanReleaseAmount);
        rCountVo.setRarePlanReleaseCapital(rarePlanReleaseCapital);
        rCountVo.setRarePracticalReleaseAmount(rarePracticalReleaseAmount);
        rCountVo.setRarePracticalReleaseCapital(rarePracticalReleaseCapital);
        rCountVo.setRareYearReleaseAmount(rareYearReleaseAmount);
        rCountVo.setRareYearReleaseCapital(rareYearReleaseCapital);
    }

    private void getValue(List<ReleaseStatisticsCountVo> countVoList, Map<String, List<ProliferationReleaseStatistics>> collect) {
        for (Map.Entry<String, List<ProliferationReleaseStatistics>> entry : collect.entrySet()) {

            ReleaseStatisticsCountVo countVo = new ReleaseStatisticsCountVo();
            String key = entry.getKey();
            countVo.setProvinceId(key);
            Result<String> sysRegionName = sysRegionApi.getSysRegionName(key);
            countVo.setAreaName(sysRegionName.getData());
            List<ProliferationReleaseStatistics> prolifList = entry.getValue();
            prolifList.forEach(
                    baseData -> {
                        String speciesType = baseData.getSpeciesType();
                        if ("0".equals(speciesType)) {
                            //淡水
                            countVo.setFreshWaterPlanReleaseAmount(baseData.getPlanReleaseAmount());
                            countVo.setFreshWaterPlanReleaseCapital(baseData.getPlanReleaseCapital());
                            countVo.setFreshWaterPracticalReleaseAmount(baseData.getPracticalReleaseAmount());
                            countVo.setFreshWaterPracticalReleaseCapital(baseData.getPracticalReleaseCapital());
                            countVo.setFreshWaterYearReleaseAmount(baseData.getYearReleaseAmount());
                            countVo.setFreshWaterYearReleaseCapital(baseData.getYearReleaseCapital());
                        }
                        if ("1".equals(speciesType)) {
                            //海水
                            countVo.setSeaWaterPlanReleaseAmount(baseData.getPlanReleaseAmount());
                            countVo.setSeaWaterPlanReleaseCapital(baseData.getPlanReleaseCapital());
                            countVo.setSeaWaterPracticalReleaseAmount(baseData.getPracticalReleaseAmount());
                            countVo.setSeaWaterPracticalReleaseCapital(baseData.getPracticalReleaseCapital());
                            countVo.setSeaWaterYearReleaseAmount(baseData.getYearReleaseAmount());
                            countVo.setSeaWaterYearReleaseCapital(baseData.getYearReleaseCapital());
                        }
                        if ("2".equals(speciesType)) {
                            //珍稀
                            countVo.setRarePlanReleaseAmount(baseData.getPlanReleaseAmount());
                            countVo.setRarePlanReleaseCapital(baseData.getPlanReleaseCapital());
                            countVo.setRarePracticalReleaseAmount(baseData.getPracticalReleaseAmount());
                            countVo.setRarePracticalReleaseCapital(baseData.getPracticalReleaseCapital());
                            countVo.setRareYearReleaseAmount(baseData.getYearReleaseAmount());
                            countVo.setRareYearReleaseCapital(baseData.getYearReleaseCapital());
                        }
                    }
            );
            countVoList.add(countVo);
        }
    }

    /**
     * 县级是否允许操作(包括新增、修改、删除)
     * @param form
     * @return
     */
    private boolean countyOperationData(ReleaseStatisticsSpeciesVo form){
        //县级操作数据条件：市级未上报或者市级退回状态
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("belongYear",form.getBelongYear());
        List<String> status = new ArrayList<>();
        status.add(AuditStatueEnum.STATUS_NOTREPORT.getStatus());
        status.add(AuditStatueEnum.STATUS_RETURN_CITY.getStatus());
        paramsMap.put("statusList",status);
        paramsMap.put("provinceId",form.getProvinceId());
        //List<ReporManagement> reporManagementByCondition = reporManagementMapper.getReporManagementByCondition2(paramsMap);
        List<MinisterAudit> ministerAuditList = ministerAuditMapper.getInfoByCondition(paramsMap);
        if(ministerAuditList != null && ministerAuditList.size()>0){
            return false;
        }
        paramsMap.put("cityId",form.getCityId());
        //List<ReporManagement> reporManagementByCondition1 = reporManagementMapper.getReporManagementByCondition2(paramsMap);
        List<ProvinceAudit> provinceAuditList = provinceAuditMapper.getInfoByCondition(paramsMap);
        if(provinceAuditList != null && provinceAuditList.size()>0){
            return false;
        }

        //一个区县一条数据
        paramsMap.put("countyId",form.getCountyId());
        return true;
    }
}
