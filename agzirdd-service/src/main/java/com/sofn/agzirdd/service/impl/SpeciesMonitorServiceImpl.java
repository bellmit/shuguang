package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.*;
import com.sofn.agzirdd.excelmodel.SpeciesMonitorExcel;
import com.sofn.agzirdd.mapper.SpeciesMonitorMapper;
import com.sofn.agzirdd.model.*;
import com.sofn.agzirdd.service.*;
import com.sofn.agzirdd.sysapi.SysDropDownApi;
import com.sofn.agzirdd.sysapi.bean.DropDownVo;
import com.sofn.agzirdd.sysapi.bean.SysRegionTreeVo;
import com.sofn.agzirdd.util.AuditUtil;
import com.sofn.agzirdd.util.SetValueUtil;
import com.sofn.agzirdd.util.SofnExceptionUtil;
import com.sofn.agzirdd.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 物种监测模块-监测基本信息ServiceImpl
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Service
public class SpeciesMonitorServiceImpl extends ServiceImpl<SpeciesMonitorMapper, SpeciesMonitor> implements SpeciesMonitorService {

    /**
     * 物种监测-监测基本信息
     */
    @Autowired
    private SpeciesMonitorMapper speciesMonitorMapper;

    /**
     * 物种监测模块-入侵物种
     */
    @Autowired
    private MonitorInvasiveAlienSpeciesService monitorInvasiveAlienSpeciesService;

    /**
     * 物种监测模块-伴生物种
     */
    @Autowired
    private MonitorCompanionSpeciesService monitorCompanionSpeciesService;

    /**
     * 物种监测模块-监测内容
     */
    @Autowired
    private MonitorContentService monitorContentService;

    /**
     * 物种监测模块-监测审核记录
     */
    @Autowired
    private MonitorExaminaRecordService monitorExaminaRecordService;

    /**
     * 区域行政接口
     */
    @Resource
    private SetValueUtil setValueUtil;
    /**
     * 下拉框接口
     */
    @Autowired
    private SysDropDownApi sysDropDownApi;
    /**
     * 预警接口
     */
    @Autowired
    private WarningMonitorService warningMonitorService;

    @Override
    public PageUtils<SpeciesMonitor> getSpeciesMonitorByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<SpeciesMonitor> speciesMonitorList = speciesMonitorMapper.getSpeciesMonitorByCondition(params);
        PageInfo<SpeciesMonitor> pageInfo = new PageInfo<>(speciesMonitorList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SpeciesMonitor> getSpeciesMonitorListByParam(Map<String, Object> params) {

        List<SpeciesMonitor> speciesMonitorList = speciesMonitorMapper.getSpeciesMonitorByCondition(params);
        return speciesMonitorList;
    }


    @Override
    public PageUtils<SpeciesMonitorForm> getSpeciesMonitorForm(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<SpeciesMonitorForm> speciesMonitorForm = speciesMonitorMapper.getSpeciesMonitorForm(params);
        speciesMonitorForm.forEach(
                baseData ->{
                    if(StringUtils.isNotBlank(baseData.getCountyId())){
                        String areaName = baseData.getProvinceName() + baseData.getCityName() + baseData.getCountyName();
                        baseData.setAreaName(areaName);
                    }else{
                        baseData.setAreaName("---");
                    }
                }
        );
        PageInfo<SpeciesMonitorForm> pageInfo = new PageInfo<>(speciesMonitorForm);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SpeciesMonitorForm> getSpeciesMonitorList(Map<String, Object> params) {

        List<SpeciesMonitorForm> speciesMonitorList = speciesMonitorMapper.getSpeciesMonitorForm(params);
        speciesMonitorList.forEach(
                baseData ->{
                    //根据区县id获取上级相关所有地址
                    if(StringUtils.isNotBlank(baseData.getCountyId())){
                        //拼接市区县详细地址
                        String areaName = baseData.getProvinceName() + baseData.getCityName() + baseData.getCountyName();
                        baseData.setAreaName(areaName);

                    }else{
                        baseData.setAreaName("---");
                    }

                    //将是否存在入侵物种枚举转换为对应参数
                    String description = HasSpeciesEnum.getDescriptionByCode(baseData.getHasAlienSpecies());
                    baseData.setHasAlienSpeciesName(description);
                }
        );
        return speciesMonitorList;
    }


    @Override
    public SpeciesMonitor getSpeciesMonitorById(String id) {
        return speciesMonitorMapper.getSpeciesMonitorById(id);
    }

    @Override
    public SpeciesMonitorVo getSpeciesMonitorAllById(String id) {
        //获去物种入侵-基本信息
        SpeciesMonitor speciesMonitor = speciesMonitorMapper.getSpeciesMonitorById(id);
        //将po对象转换为vo对象
        SpeciesMonitorVo speciesMonitorVo = SpeciesMonitorVo.getSpeciesMonitorVo(speciesMonitor);
        //将省市县id转换为省市县名称
        if(StringUtils.isNotBlank(speciesMonitorVo.getCountyId())){
            //拼接市区县详细地址
            String areaName = speciesMonitorVo.getProvinceName() + "/" + speciesMonitorVo.getCityName() + "/" + speciesMonitorVo.getCountyName();
            speciesMonitorVo.setAreaName(areaName);
        }else{
            speciesMonitorVo.setAreaName("---");
        }

        //判断该数据是否存在入侵物种
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("speciesMonitorId",id);
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesMonitor.getHasAlienSpecies())){
            //获取入侵物种数据
            List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList =
                    monitorInvasiveAlienSpeciesService.getMonitorInvasiveAlienSpeciesByCondition(params);
            if(monitorInvasiveAlienSpeciesList.size()>0){
                speciesMonitorVo.setMonitorInvasiveAlienSpeciesList(monitorInvasiveAlienSpeciesList);
            }
            //获取伴生物种数据
            List<MonitorCompanionSpecies> monitorCompanionSpeciesList =
                    monitorCompanionSpeciesService.getMonitorCompanionSpeciesByCondition(params);
            if(monitorCompanionSpeciesList.size()>0){
                speciesMonitorVo.setMonitorCompanionSpeciesList(monitorCompanionSpeciesList);
            }
            //获取监测内容
            MonitorContentVo monitorContentVo = monitorContentService.getMonitorContentVo(id);
            if(monitorContentVo != null){
                speciesMonitorVo.setMonitorContentVo(monitorContentVo);
            }
        }
        //获取审核记录
        List<MonitorExaminaRecord> monitorExaminaRecordList =
                monitorExaminaRecordService.getMonitorExaminaRecordByCondition(params);
        if(monitorExaminaRecordList.size()>0){
            speciesMonitorVo.setMonitorExaminaRecordList(monitorExaminaRecordList);
        }
        return speciesMonitorVo;
    }

    @Override
    public SpeciesMonitorExcel getSpeciesMonitorExcel(String id) {

        //获去物种入侵-基本信息
        SpeciesMonitor speciesMonitor = speciesMonitorMapper.getSpeciesMonitorById(id);
        SpeciesMonitorExcel speciesMonitorExcel = new SpeciesMonitorExcel();
        if(StringUtils.isNotBlank(speciesMonitor.getCountyId())){
            //拼接市区县详细地址
            String areaName = speciesMonitor.getProvinceName() + speciesMonitor.getCityId() + speciesMonitor.getCountyName() + speciesMonitor.getTown();
            speciesMonitorExcel.setAreaName(areaName);
        }else{
            speciesMonitorExcel.setAreaName("---");
        }
        //将是否存在入侵物种枚举转换为对应参数
        String description = HasSpeciesEnum.getDescriptionByCode(speciesMonitor.getHasAlienSpecies());
        speciesMonitorExcel.setHasAlienSpeciesName(description);

        BeanUtils.copyProperties(speciesMonitor,speciesMonitorExcel);
        //判断该数据是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesMonitor.getHasAlienSpecies())){
            //放入查询条件
            Map<String, Object> params = Maps.newHashMap();
            params.put("speciesMonitorId",id);
            //获取入侵物种数据
            List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList =
                    monitorInvasiveAlienSpeciesService.getMonitorInvasiveAlienSpeciesByCondition(params);
            if(monitorInvasiveAlienSpeciesList.size()>0){
                for (int i = 0; i < monitorInvasiveAlienSpeciesList.size(); i++){
                    monitorInvasiveAlienSpeciesList.get(i).setOrderNumber(i+1+"");
                }
                speciesMonitorExcel.setMonitorInvasiveAlienSpeciesList(monitorInvasiveAlienSpeciesList);
            }
            //获取伴生物种数据
            List<MonitorCompanionSpecies> monitorCompanionSpeciesList =
                    monitorCompanionSpeciesService.getMonitorCompanionSpeciesByCondition(params);
            if(monitorCompanionSpeciesList.size()>0){
                for (int i = 0; i < monitorInvasiveAlienSpeciesList.size(); i++){
                    monitorInvasiveAlienSpeciesList.get(i).setOrderNumber(i+1+"");
                }
                speciesMonitorExcel.setMonitorCompanionSpeciesList(monitorCompanionSpeciesList);
            }
            //获取监测内容
            MonitorContentVo monitorContentVo = monitorContentService.getMonitorContentVo(id);
            if(monitorContentVo != null){

                if(StringUtils.isNotBlank(monitorContentVo.getDamageDegree())){
                    String damageDegreeName = DamageDegreeEnum.getDescriptionByCode(monitorContentVo.getDamageDegree());
                    speciesMonitorExcel.setDamageDegreeName(damageDegreeName);
                }
                if(StringUtils.isNotBlank(monitorContentVo.getHasMeasures())){
                    String hasMeasuresName = YseOrNoEnum.getDescriptionByCode(monitorContentVo.getHasMeasures());
                    speciesMonitorExcel.setHasMeasuresName(hasMeasuresName);
                }
                if(StringUtils.isNotBlank(monitorContentVo.getHasUtilize())){
                    String hasUtilizeName = YseOrNoEnum.getDescriptionByCode(monitorContentVo.getHasUtilize());
                    speciesMonitorExcel.setHasUtilizeName(hasUtilizeName);
                }

                BeanUtils.copyProperties(monitorContentVo,speciesMonitorExcel);

            }

        }
        return speciesMonitorExcel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Map<String, Object> params) {

        String status0 = StatusEnum.STATUS_0.getCode();
        String status1 = StatusEnum.STATUS_1.getCode();
        String status2 = StatusEnum.STATUS_2.getCode();

        //获取当前传递状态
            String status = params.get("status").toString();
        String id = params.get("id").toString();
        String remark = "";
        if(!status2.equals(status)){
            remark = params.get("remark").toString();
        }

        //只有非"已保存","已提交","已撤回"这种状态下才会存在审核记录
        if(status.equals(status0) || status.equals(status1) || status.equals(status2)){

        }else{
            //审核意见相关
            MonitorExaminaRecord monitorExaminaRecord = new MonitorExaminaRecord();
            monitorExaminaRecord.setId(IdUtil.getUUId());
            monitorExaminaRecord.setSpeciesMonitorId(id);
            monitorExaminaRecord.setStatus(status);
            User loginUser = UserUtil.getLoginUser();
            monitorExaminaRecord.setAuditor(loginUser.getNickname());
            monitorExaminaRecord.setOpinion(remark);
            monitorExaminaRecord.setCreateTime(new Date());
            monitorExaminaRecordService.addMonitorExaminaRecord(monitorExaminaRecord);
        }

        speciesMonitorMapper.updateStatus(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSpeciesMonitor(SpeciesMonitorVo speciesMonitorVo) {
        SpeciesMonitor speciesMonitor = SpeciesMonitorVo.getSpeciesMonitor(speciesMonitorVo);
        Map<String, Object> params = Maps.newHashMap();
        params.put("formNumber",speciesMonitor.getFormNumber());
        List<SpeciesMonitor> oldList = speciesMonitorMapper.getSpeciesMonitorByParam(params);
        if(oldList.size()>0){
            throw new SofnException("当前表格编号已存在");
        }
        //放入id,创建人,创建人id,创建时间.当前年份
        speciesMonitor.setId(IdUtil.getUUId());
        User loginUser = UserUtil.getLoginUser();
        speciesMonitor.setCreateUserId(loginUser.getId());
        speciesMonitor.setCreateUserName(loginUser.getNickname());
        speciesMonitor.setCreateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(speciesMonitorVo.getMonitorTime());
        String belongYear = calendar.get(Calendar.YEAR) + "";
        speciesMonitor.setBelongYear(belongYear);
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //判断当前用户是否为县级填报
        if(roleColde.contains("agzirdd_county")){
            speciesMonitor.setRoleCode("agzirdd_county");
        }
        //是否为专家填报
        if(roleColde.contains("agzirdd_expert")){
            speciesMonitor.setRoleCode("agzirdd_expert");
        }
        //保存物种监测-基本信息
        boolean sm = this.save((SpeciesMonitor) setValueUtil.setNameById(speciesMonitor));
        //判断该数据是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesMonitorVo.getHasAlienSpecies())){
            if(speciesMonitorVo.getMonitorContentVo()==null)
                throw new SofnException("监测内容未传");
            //获取并保存相关入侵物种数据
            List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList = speciesMonitorVo.getMonitorInvasiveAlienSpeciesList();
            if(monitorInvasiveAlienSpeciesList != null && monitorInvasiveAlienSpeciesList.size()>0 ){
                monitorInvasiveAlienSpeciesService.batchAddMonitorInvasiveAlienSpecies(monitorInvasiveAlienSpeciesList,speciesMonitor.getId());

                //-------------------------------chlf start------------------------------------//
                //添加数据到预警表中，每条数据分别插入2条预警信息（发生面积，数量）
                //2020-08-24 chlf 修改，去掉【数量】数据插入到预警表中
                Result<List<DropDownVo>> result = sysDropDownApi.listForIndexType();
                List<DropDownVo> dropDownVoList = result.getData();
                //从其他系统获取下拉框信息
                Map<String,String> dropdownMap = new HashMap<>();
                for(DropDownVo dropDownVo:dropDownVoList){
                    if(ClassifyEnum.AREA.getDescription().equals(dropDownVo.getName())){//指标分类为【发生面积】
                        dropdownMap.put(ClassifyEnum.AREA.getCode(),dropDownVo.getId());
                    }else if(ClassifyEnum.AMOUNT.getDescription().equals(dropDownVo.getName())){//指标分类为【数量】
                        dropdownMap.put(ClassifyEnum.AMOUNT.getCode(),dropDownVo.getId());
                    }else if(ClassifyEnum.ZCJJSS.getDescription().equals(dropDownVo.getName())){//指标分类为【造成经济损失】
                        dropdownMap.put(ClassifyEnum.ZCJJSS.getCode(),dropDownVo.getId());
                    }
                }
                //预警信息列表
                List<WarningMonitor> areaMonitorList = new ArrayList<>();   //发生面积
                List<WarningMonitor> zcjjssMonitorList =null; //造成经济损失
                for(MonitorInvasiveAlienSpecies mia : monitorInvasiveAlienSpeciesList){
                    WarningMonitor warningMonitor = new WarningMonitor();
                    warningMonitor.setAmount(speciesMonitorVo.getMonitorContentVo().getArea());
                    warningMonitor.setBelongYear(belongYear);
                    warningMonitor.setSpeciesMonitorId(speciesMonitor.getId());
                    warningMonitor.setClassificationId(dropdownMap.get(ClassifyEnum.AREA.getCode()));
                    warningMonitor.setClassificationName(ClassifyEnum.AREA.getDescription());
                    warningMonitor.setCreateTime(speciesMonitorVo.getMonitorTime());
                    warningMonitor.setLatitude(mia.getLatitude());
                    warningMonitor.setLongitude(mia.getLongitude());
                    warningMonitor.setId(UUID.randomUUID().toString());
                    warningMonitor.setSpeciesId(mia.getSpeciesId());
                    warningMonitor.setSpeciesName(mia.getSpeciesName());
                    warningMonitor.setProvinceId(speciesMonitorVo.getProvinceId());
                    warningMonitor.setCityId(speciesMonitorVo.getCityId());
                    warningMonitor.setCountyId(speciesMonitorVo.getCountyId());
                    warningMonitor.setCompany(speciesMonitorVo.getCompany());
                    warningMonitor.setMonitor(speciesMonitorVo.getMonitor());
                    warningMonitor.setLatinName(mia.getLatinName());
                    warningMonitor.setNum(mia.getAmount());
                    warningMonitor.setCoverRatio(mia.getCoverRatio());
                    warningMonitor.setWorkImg(speciesMonitorVo.getMonitorContentVo().getWorkImg());
                    warningMonitor.setSpeciesImg(speciesMonitorVo.getMonitorContentVo().getSpeciesImg());
                    warningMonitor.setSummary(speciesMonitorVo.getMonitorContentVo().getSummary());

                    areaMonitorList.add((WarningMonitor) setValueUtil.setNameById(warningMonitor));
                }
                //批量保存
                warningMonitorService.saveBatch(areaMonitorList);

                zcjjssMonitorList = areaMonitorList.stream().map(m -> {
                    m.setId(UUID.randomUUID().toString());
                    m.setAmount(speciesMonitorVo.getMonitorContentVo().getEconomicLoss());
                    m.setClassificationId(dropdownMap.get(ClassifyEnum.ZCJJSS.getCode()));
                    m.setClassificationName(ClassifyEnum.ZCJJSS.getDescription());
                    return m;
                }).collect(Collectors.toList());

                warningMonitorService.saveBatch(zcjjssMonitorList);
                //--------------------------------chlf end-----------------------------------//
            }else{
                throw new SofnException("请至少填写一条外来入侵物种信息!");
            }
            //获取并保存相关伴生物种数据
            List<MonitorCompanionSpecies> monitorCompanionSpeciesList = speciesMonitorVo.getMonitorCompanionSpeciesList();
            if(monitorCompanionSpeciesList!= null && monitorCompanionSpeciesList.size()>0){
                monitorCompanionSpeciesService.batchAddMonitorCompanionSpecies(monitorCompanionSpeciesList,speciesMonitor.getId());
            }
            //获取并保存相关监测内容
            MonitorContent monitorContent = speciesMonitorVo.getMonitorContentVo();
            //放入物种监测表id
            monitorContent.setSpeciesMonitorId(speciesMonitor.getId());
            monitorContentService.addMonitorContent(monitorContent);
        }
        if(!sm){
            throw new SofnException("保存物种监测相关信息异常,请重新操作!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpeciesMonitor(SpeciesMonitorVo speciesMonitorVo) {
        //监测年度
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(speciesMonitorVo.getMonitorTime());
        String belongYear = calendar.get(Calendar.YEAR) + "";
        speciesMonitorVo.setBelongYear(belongYear);

        SpeciesMonitor speciesMonitor = SpeciesMonitorVo.getSpeciesMonitor(speciesMonitorVo);

        Map<String, Object> params = Maps.newHashMap();
        params.put("formNumber",speciesMonitor.getFormNumber());
        List<SpeciesMonitor> oldList = speciesMonitorMapper.getSpeciesMonitorByParam(params);
        if(oldList!=null && oldList.size()>0){
            for (SpeciesMonitor m : oldList) {
                if(!m.getId().equals(speciesMonitorVo.getId())){
                    throw new SofnException("当前表格编号已存在");
                }
            }
        }

        //删除旧数据
        Map<String, String> wmParams = new HashMap();
        wmParams.put("speciesMonitorId", speciesMonitorVo.getId());
        warningMonitorService.deleteByCondition(wmParams);

        //判断是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesMonitor.getHasAlienSpecies())){
            if(speciesMonitorVo.getMonitorContentVo()==null)
                throw new SofnException("监测内容未传");

            //获取并修改相关入侵物种数据
            List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList = speciesMonitorVo.getMonitorInvasiveAlienSpeciesList();
            if(monitorInvasiveAlienSpeciesList != null && monitorInvasiveAlienSpeciesList.size()>0){
                monitorInvasiveAlienSpeciesService.batchAddMonitorInvasiveAlienSpecies(monitorInvasiveAlienSpeciesList,speciesMonitor.getId());
                //-------------------------------chlf start------------------------------------//
                //添加数据到预警表中，数据分别插入预警信息（发生面积）
                //2020-09-14 chlf 修改，数据插入到预警表中
                Result<List<DropDownVo>> result = sysDropDownApi.listForIndexType();
                List<DropDownVo> dropDownVoList = result.getData();
                //从其他系统获取下拉框信息
                Map<String,String> dropdownMap = new HashMap<>();
                for(DropDownVo dropDownVo:dropDownVoList){
                    if(ClassifyEnum.AREA.getDescription().equals(dropDownVo.getName())){//指标分类为【发生面积】
                        dropdownMap.put(ClassifyEnum.AREA.getCode(),dropDownVo.getId());
                    }else if(ClassifyEnum.AMOUNT.getDescription().equals(dropDownVo.getName())){//指标分类为【数量】
                        dropdownMap.put(ClassifyEnum.AMOUNT.getCode(),dropDownVo.getId());
                    }else if(ClassifyEnum.ZCJJSS.getDescription().equals(dropDownVo.getName())){//指标分类为【造成经济损失】
                        dropdownMap.put(ClassifyEnum.ZCJJSS.getCode(),dropDownVo.getId());
                    }
                }

                //预警信息列表
                List<WarningMonitor> areaMonitorList = new ArrayList<>();   //发生面积
                List<WarningMonitor> zcjjssMonitorList = null;   //造成经济损失
                for(MonitorInvasiveAlienSpecies mia : monitorInvasiveAlienSpeciesList){
                    WarningMonitor warningMonitor = new WarningMonitor();
                    warningMonitor.setSpeciesMonitorId(speciesMonitorVo.getId());
                    warningMonitor.setAmount(speciesMonitorVo.getMonitorContentVo().getArea());
                    warningMonitor.setBelongYear(belongYear);
                    warningMonitor.setClassificationId(dropdownMap.get(ClassifyEnum.AREA.getCode()));
                    warningMonitor.setClassificationName(ClassifyEnum.AREA.getDescription());
                    warningMonitor.setCreateTime(speciesMonitorVo.getMonitorTime());
                    warningMonitor.setLatitude(mia.getLatitude());
                    warningMonitor.setLongitude(mia.getLongitude());
                    warningMonitor.setId(UUID.randomUUID().toString());
                    warningMonitor.setSpeciesId(mia.getSpeciesId());
                    warningMonitor.setSpeciesName(mia.getSpeciesName());
                    warningMonitor.setProvinceId(speciesMonitorVo.getProvinceId());
                    warningMonitor.setCityId(speciesMonitorVo.getCityId());
                    warningMonitor.setCountyId(speciesMonitorVo.getCountyId());
                    warningMonitor.setCompany(speciesMonitorVo.getCompany());
                    warningMonitor.setMonitor(speciesMonitorVo.getMonitor());
                    warningMonitor.setLatinName(mia.getLatinName());
                    warningMonitor.setNum(mia.getAmount());
                    warningMonitor.setCoverRatio(mia.getCoverRatio());
                    warningMonitor.setWorkImg(speciesMonitorVo.getMonitorContentVo().getWorkImg());
                    warningMonitor.setSpeciesImg(speciesMonitorVo.getMonitorContentVo().getSpeciesImg());
                    warningMonitor.setSummary(speciesMonitorVo.getMonitorContentVo().getSummary());
                    areaMonitorList.add((WarningMonitor) setValueUtil.setNameById(warningMonitor));
                }

                //批量保存,发生面积
                warningMonitorService.saveBatch(areaMonitorList);

                zcjjssMonitorList = areaMonitorList.stream().map(m -> {
                    m.setId(UUID.randomUUID().toString());
                    m.setAmount(speciesMonitorVo.getMonitorContentVo().getEconomicLoss());
                    m.setClassificationId(dropdownMap.get(ClassifyEnum.ZCJJSS.getCode()));
                    m.setClassificationName(ClassifyEnum.ZCJJSS.getDescription());
                    return m;
                }).collect(Collectors.toList());

                //批量保存,造成经济损失
                warningMonitorService.saveBatch(zcjjssMonitorList);
                //--------------------------------chlf end-----------------------------------//

            }else{
                throw new SofnException("请至少填写一条外来入侵物种信息!");
            }
            //获取并修改相关伴生物种数据
            List<MonitorCompanionSpecies> monitorCompanionSpeciesList = speciesMonitorVo.getMonitorCompanionSpeciesList();
            if(monitorCompanionSpeciesList != null && monitorCompanionSpeciesList.size()>0){
                monitorCompanionSpeciesService.batchAddMonitorCompanionSpecies(monitorCompanionSpeciesList,speciesMonitor.getId());
            }
            MonitorContent oldMonitorContent =
                    monitorContentService.getMonitorContentBySpeciesMonitorId(speciesMonitor.getId());
            MonitorContent monitorContent = speciesMonitorVo.getMonitorContentVo();
            if(null != oldMonitorContent){
                //获取并修改相关监测内容
                monitorContentService.updateMonitorContent(monitorContent);
            }else{
                //获取并保存相关监测内容
                //放入物种监测表id
                monitorContent.setSpeciesMonitorId(speciesMonitor.getId());
                monitorContentService.addMonitorContent(monitorContent);
            }
        }else{

            List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList = speciesMonitorVo.getMonitorInvasiveAlienSpeciesList();
            if(monitorInvasiveAlienSpeciesList != null && monitorInvasiveAlienSpeciesList.size()>0){
                monitorInvasiveAlienSpeciesService.removeMonitorInvasiveAlienSpecies(speciesMonitor.getId());

                //删除相关地区发生面积数据信息
//                Map<String, String> condition = new HashMap<>();
//                condition.put("speciesId", monitorInvasiveAlienSpeciesList.get(0).getSpeciesId());
//                condition.put("belongYear", speciesMonitor.getBelongYear());
//                condition.put("countyId", speciesMonitor.getCountyId());
//                warningMonitorService.deleteByCondition(condition);
            }
            //获取并修改相关伴生物种数据
            List<MonitorCompanionSpecies> monitorCompanionSpeciesList = speciesMonitorVo.getMonitorCompanionSpeciesList();
            if(monitorCompanionSpeciesList != null && monitorCompanionSpeciesList.size()>0){
                monitorCompanionSpeciesService.removeMonitorCompanionSpecies(speciesMonitor.getId());
            }
            //获取并修改相关监测内容
            MonitorContent monitorContent =
                    monitorContentService.getMonitorContentBySpeciesMonitorId(speciesMonitor.getId());
            if(monitorContent != null){
                monitorContentService.removeMonitorContent(speciesMonitor.getId());
            }
        }
        setValueUtil.setNameById(speciesMonitorVo);//更新前替换省市区
        boolean sm = this.updateById(speciesMonitorVo);
        if(!sm){
            throw new SofnException("修改物种监测相关信息异常,请重新操作!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSpeciesMonitor(String id) {

        SpeciesMonitor speciesMonitor = speciesMonitorMapper.getSpeciesMonitorById(id);
        //判断该数据是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesMonitor.getHasAlienSpecies())){
            //存在入侵物种
            //放入查询条件
            Map<String, Object> params = Maps.newHashMap();
            params.put("speciesMonitorId",id);
            boolean mias = true;
            boolean mcs = true;
            boolean mc = true;
            boolean mer = true;
            //删除入侵物种数据->先判断是否存在数据.存在即删除
            List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList =
                    monitorInvasiveAlienSpeciesService.getMonitorInvasiveAlienSpeciesByCondition(params);
            if(monitorInvasiveAlienSpeciesList.size()>0){
                mias = monitorInvasiveAlienSpeciesService.removeMonitorInvasiveAlienSpecies(id);
            }
            //删除伴生物种数据->先判断是否存在数据.存在即删除
            List<MonitorCompanionSpecies> monitorCompanionSpeciesList =
                    monitorCompanionSpeciesService.getMonitorCompanionSpeciesByCondition(params);
            if(monitorCompanionSpeciesList.size()>0){
                mcs = monitorCompanionSpeciesService.removeMonitorCompanionSpecies(id);
            }
            //删除监测内容->先判断是否存在数据.存在即删除
            MonitorContent monitorContent = monitorContentService.getMonitorContentBySpeciesMonitorId(id);
            if(monitorContent != null){
                mc = monitorContentService.removeMonitorContent(id);
            }
            //删除审核记录->先判断是否存在数据.存在即删除
            List<MonitorExaminaRecord> monitorExaminaRecordList =
                    monitorExaminaRecordService.getMonitorExaminaRecordByCondition(params);
            if(monitorExaminaRecordList.size()>0){
                mer = monitorExaminaRecordService.removeMonitorExaminaRecord(id);
            }
            if(!mias || !mcs || !mc || !mer){
                throw new SofnException("删除异常,请重新操作!");
            }

            //删除相关预警监测数据
            warningMonitorService.deleteBySpeciesMonitorId(id);

        }
        //删除基本信息
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAudit(AuditDataVo vo) {
        SpeciesMonitor sm = this.baseMapper.getSpeciesMonitorById(vo.getId());
        if(sm==null)
            SofnExceptionUtil.throwError("数据未找到");

        AuditUtil.checkAuditRight(sm.getStatus());
        String newStatus = "";
        if (vo.isAccept())
            newStatus = AuditUtil.getAcceptNewStatus(sm.getStatus());
        else
            newStatus = AuditUtil.getRejectNewStatus(sm.getStatus());

        //更新状态
        Map<String, Object> params = new HashMap();
        params.put("id",sm.getId());
        params.put("status", newStatus);
        this.baseMapper.updateStatus(params);
        //保存审核记录
        monitorExaminaRecordService.saveByAudit(sm.getId(),newStatus,vo.getRemark());

        //如果是总站通过，并且是县级填报的数据，则更新这条数据为最新数据
        if((StatusEnum.STATUS_7.getCode().equals(newStatus) || StatusEnum.STATUS_8.getCode().equals(newStatus))
                && RoleCodeEnum.COUNTY.getCode().equals(sm.getRoleCode())){
            //更新之前的数据为旧数据
            this.baseMapper.updateStatusToOld(sm.getProvinceId(),sm.getCityId(),sm.getCountyId(),sm.getBelongYear(),sm.getRoleCode());
            //更新最新数据数据码
            this.baseMapper.updateStatusToNew(sm.getProvinceId(),sm.getCityId(),sm.getCountyId(),sm.getBelongYear(),sm.getRoleCode());
        }
    }

}
