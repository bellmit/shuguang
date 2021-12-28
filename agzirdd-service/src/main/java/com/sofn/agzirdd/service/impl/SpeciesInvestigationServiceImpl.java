package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.*;
import com.sofn.agzirdd.excelmodel.SpeciesInvestigationExcel;
import com.sofn.agzirdd.mapper.SpeciesInvestigationMapper;
import com.sofn.agzirdd.model.DistributionMap;
import com.sofn.agzirdd.model.InvestigatContent;
import com.sofn.agzirdd.model.InvestigatExaminaRecord;
import com.sofn.agzirdd.model.SpeciesInvestigation;
import com.sofn.agzirdd.service.DistributionMapService;
import com.sofn.agzirdd.service.InvestigatContentService;
import com.sofn.agzirdd.service.InvestigatExaminaRecordService;
import com.sofn.agzirdd.service.SpeciesInvestigationService;
import com.sofn.agzirdd.sysapi.bean.SysRegionTreeVo;
import com.sofn.agzirdd.util.AuditUtil;
import com.sofn.agzirdd.util.SetValueUtil;
import com.sofn.agzirdd.vo.*;
import com.sofn.common.exception.SofnException;
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

/**
 * @Description: 物种调查模块-调查基本信息
 * @Author: mcc
 * @Date: 2020\3\13 0013
 */
@Service
public class SpeciesInvestigationServiceImpl extends ServiceImpl<SpeciesInvestigationMapper, SpeciesInvestigation> implements SpeciesInvestigationService {

    /**
     * 物种调查模块-调查基本信息
     */
    @Autowired
    private SpeciesInvestigationMapper speciesInvestigationMapper;

    /**
     * 物种调查模块-调查内容
     */
    @Autowired
    private InvestigatContentService investigatContentService;

    /**
     * 物种调查模块-审核记录
     */
    @Autowired
    private InvestigatExaminaRecordService investigatExaminaRecordService;

    /**
     * 区域行政接口
     */
    @Resource
    private SetValueUtil setValueUtil;
    /**
     * 分布图数据接口
     */
    @Autowired
    private DistributionMapService distributionMapService;


    @Override
    public PageUtils<SpeciesInvestigation> getSpeciesInvestigationByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SpeciesInvestigation> speciesInvestigationList = speciesInvestigationMapper.getSpeciesInvestigationByCondition(params);
        PageInfo<SpeciesInvestigation> pageInfo = new PageInfo<>(speciesInvestigationList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SpeciesInvestigation> getSpeciesInvestigationListByParam(Map<String, Object> params) {
        return speciesInvestigationMapper.getSpeciesInvestigationByCondition(params);
    }

    @Override
    public PageUtils<SpeciesInvestigationForm> getSpeciesInvestigationForm(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<SpeciesInvestigationForm> speciesInvestigationForm = speciesInvestigationMapper.getSpeciesInvestigationForm(params);
        speciesInvestigationForm.forEach(
                baseData ->{
                    //判断获取地址相关数据是否存在
                    if (StringUtils.isNotBlank(baseData.getCountyId())) {
                        //拼接市区县详细地址
                        String areaName = baseData.getProvinceName() + baseData.getCityName() + baseData.getCountyName();
                        baseData.setAreaName(areaName);
                    } else {
                        baseData.setAreaName("---");
                    }
                }
        );

        PageInfo<SpeciesInvestigationForm> pageInfo = new PageInfo<>(speciesInvestigationForm);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SpeciesInvestigationForm> getSpeciesInvestigationList(Map<String, Object> params) {
        List<SpeciesInvestigationForm> speciesInvestigationForm = speciesInvestigationMapper.getSpeciesInvestigationForm(params);
        speciesInvestigationForm.forEach(
                baseData ->{
                    if(StringUtils.isNotBlank(baseData.getCountyId())) {
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
        return speciesInvestigationForm;
    }

    @Override
    public SpeciesInvestigation getSpeciesInvestigationById(String id) {
        return speciesInvestigationMapper.getSpeciesInvestigationById(id);
    }

    @Override
    public SpeciesInvestigationVo getSpeciesInvestigationAllById(String id) {
        //获取物种调查-调查基本信息
        SpeciesInvestigation speciesInvestigation = speciesInvestigationMapper.getSpeciesInvestigationById(id);
        if(null==speciesInvestigation) return new SpeciesInvestigationVo();

        //将po转换为vo
        SpeciesInvestigationVo speciesInvestigationVo = SpeciesInvestigationVo.getSpeciesInvestigationVo(speciesInvestigation);
        //将省市县id转换为省市县名称
        if(StringUtils.isNotBlank(speciesInvestigationVo.getCountyId())){
            //拼接市区县详细地址
            String areaName = speciesInvestigationVo.getProvinceName() + "/" + speciesInvestigationVo.getCityName() + "/" + speciesInvestigationVo.getCountyName();
            speciesInvestigationVo.setAreaName(areaName);
        }else{
            speciesInvestigationVo.setAreaName("---");
        }

        //判断该数据是否存在入侵物种
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("speciesInvestigationId", id);
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesInvestigation.getHasAlienSpecies())) {
            //调查内容
            InvestigatContentVo investigatContentVo = investigatContentService.getInvestigatContentVo(id);

            speciesInvestigationVo.setInvestigatContentVo(investigatContentVo);
        }
        //获取审核记录
        List<InvestigatExaminaRecord> investigatExaminaRecordList = investigatExaminaRecordService.getInvestigatExaminaRecordByCondition(params);
        if(investigatExaminaRecordList.size()>0){
            speciesInvestigationVo.setInvestigatExaminaRecordList(investigatExaminaRecordList);
        }
        return speciesInvestigationVo;
    }

    @Override
    public SpeciesInvestigationExcel getSpeciesInvestigationExcel(String id) {
        //获取物种入侵-调查基本信息
        SpeciesInvestigation speciesInvestigation = speciesInvestigationMapper.getSpeciesInvestigationById(id);
        SpeciesInvestigationExcel speciesInvestigationExcel = new SpeciesInvestigationExcel();

        if(StringUtils.isNotBlank(speciesInvestigation.getCountyId())){
            //拼接市区县详细地址
            String areaName = speciesInvestigation.getProvinceName() + speciesInvestigation.getCityName() + speciesInvestigation.getCountyName() + speciesInvestigation.getTown();
            speciesInvestigationExcel.setAreaName(areaName);
        }else{
            speciesInvestigationExcel.setAreaName("---");
        }

        //将是否存在入侵物种枚举转换为对应参数
        String description = HasSpeciesEnum.getDescriptionByCode(speciesInvestigation.getHasAlienSpecies());
        speciesInvestigationExcel.setHasAlienSpeciesName(description);

        BeanUtils.copyProperties(speciesInvestigation,speciesInvestigationExcel);
        //判断该数据是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesInvestigation.getHasAlienSpecies())){


            InvestigatContentVo investigatContentVo = investigatContentService.getInvestigatContentVo(id);
            /*修复导出时没显示生物类型*/
            investigatContentVo.setSpeciesTypeName(investigatContentVo.getSpeciesType());
            //获取监测内容
            if(investigatContentVo != null){

                if(StringUtils.isNotBlank(investigatContentVo.getDamageDegree())){
                    String damageDegreeName = DamageDegreeEnum.getDescriptionByCode(investigatContentVo.getDamageDegree());
                    speciesInvestigationExcel.setDamageDegreeName(damageDegreeName);
                }
                if(StringUtils.isNotBlank(investigatContentVo.getNewSpecies())){
                    String newSpeciesName = YseOrNoEnum.getDescriptionByCode(investigatContentVo.getNewSpecies());
                    speciesInvestigationExcel.setNewSpeciesName(newSpeciesName);
                }
                if(StringUtils.isNotBlank(investigatContentVo.getHasHarm())){
                    String hasHarmName = YseOrNoEnum.getDescriptionByCode(investigatContentVo.getHasHarm());
                    speciesInvestigationExcel.setHasHarmName(hasHarmName);
                }
                if(StringUtils.isNotBlank(investigatContentVo.getHasMeasures())){
                    String hasMeasuresName = YseOrNoEnum.getDescriptionByCode(investigatContentVo.getHasMeasures());
                    speciesInvestigationExcel.setHasMeasuresName(hasMeasuresName);
                }
                if(StringUtils.isNotBlank(investigatContentVo.getHasUtilize())){
                    String hasUtilizeName = YseOrNoEnum.getDescriptionByCode(investigatContentVo.getHasUtilize());
                    speciesInvestigationExcel.setHasUtilizeName(hasUtilizeName);
                }

                BeanUtils.copyProperties(investigatContentVo,speciesInvestigationExcel);
            }

        }
        return speciesInvestigationExcel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Map<String, Object> params) {
        String status0 = StatusEnum.STATUS_0.getCode();
        String status1 = StatusEnum.STATUS_1.getCode();
        String status2 = StatusEnum.STATUS_2.getCode();

        //获取当前传递状态
        String id = params.get("id").toString();
        String status = params.get("status").toString();
        String remark = "";
        if(!status2.equals(status)){
            remark = params.get("remark").toString();
        }

        //只有非"已保存","已提交","已撤回"这种状态下才会存在审核记录
        if(status.equals(status0) || status.equals(status1) || status.equals(status2)){

        }else{
            //审核意见相关
            InvestigatExaminaRecord investigatExaminaRecord = new InvestigatExaminaRecord();
            investigatExaminaRecord.setId(IdUtil.getUUId());
            investigatExaminaRecord.setSpeciesInvestigationId(id);
            investigatExaminaRecord.setStatus(status);
            User loginUser = UserUtil.getLoginUser();
            investigatExaminaRecord.setAuditor(loginUser.getNickname());
            investigatExaminaRecord.setOpinion(remark);
            investigatExaminaRecord.setCreateTime(new Date());
            investigatExaminaRecordService.addInvestigatExaminaRecord(investigatExaminaRecord);
        }

        speciesInvestigationMapper.updateStatus(params);
        //-----------------chlf start---------------------------//
        /*
         *@Description //插入数据到外来物种分布图的表中
         *@Date 16:06 2020/4/3   5/6   8/17修改
         **/
        SpeciesInvestigation speciesInvestigation = speciesInvestigationMapper.getSpeciesInvestigationById(id);
        InvestigatContent investigatContent = investigatContentService.getInvestigatContentBySpeciesInvestigationId(id);
        //若是总站审核通过
        if(status.equals(StatusEnum.STATUS_7.getCode())
                  && null!=investigatContent && "1".equals(investigatContent.getNewSpecies())){
            DistributionMap distributionMap = new DistributionMap();
            distributionMap.setCityId(speciesInvestigation.getCityId());
            distributionMap.setCountyId(speciesInvestigation.getCountyId());
            distributionMap.setFinder(speciesInvestigation.getInvestigator());
            distributionMap.setGps(investigatContent.getGps());
            distributionMap.setLatitude(speciesInvestigation.getLatitude());
            distributionMap.setLongitude(speciesInvestigation.getLongitude());
            distributionMap.setProvinceId(speciesInvestigation.getProvinceId());
            distributionMap.setSpeciesId(investigatContent.getSpeciesId());
            distributionMap.setSpeciesName(investigatContent.getSpeciesName());
            distributionMap.setId(UUID.randomUUID().toString());
            distributionMap.setCreateTime(speciesInvestigation.getInvestigationTime());
            distributionMap.setArea(investigatContent.getArea());
            distributionMap.setAmount(investigatContent.getAmount());
            distributionMap.setInvestigatorCompany(speciesInvestigation.getInvestigatorCompany());
            distributionMap.setResultImg(investigatContent.getResultImg());
            distributionMap.setUtilizeImg(investigatContent.getUtilizeImg());
            distributionMap.setSpeciesImg(investigatContent.getSpeciesImg());
            distributionMap.setSpeciesInvestigationId(speciesInvestigation.getId());
            //组装省市区的方法去掉了，换掉了
            distributionMapService.save((DistributionMap) setValueUtil.setNameById(distributionMap));
        }else if(status.equals(StatusEnum.STATUS_8.getCode())){ //总站退回
            //删除相关数据
            distributionMapService.removeBySpecInveId(speciesInvestigation.getId());
        }
        //-----------------chlf end ---------------------------//

        if((StatusEnum.STATUS_7.getCode().equals(status) || StatusEnum.STATUS_8.getCode().equals(status))
                && RoleCodeEnum.COUNTY.getCode().equals(speciesInvestigation.getRoleCode())){
            //如果是总站通过，则更新这条数据为最新数据
            //更新之前的数据为旧数据
            this.baseMapper.updateStatusToOld(speciesInvestigation.getProvinceId(),speciesInvestigation.getCityId(),speciesInvestigation.getCountyId()
                    ,speciesInvestigation.getBelongYear(),speciesInvestigation.getRoleCode());
            //更新最新数据状态
            this.baseMapper.updateStatusToNew(speciesInvestigation.getProvinceId(),speciesInvestigation.getCityId(),speciesInvestigation.getCountyId()
                    ,speciesInvestigation.getBelongYear(),speciesInvestigation.getRoleCode());
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSpeciesInvestigation(SpeciesInvestigationVo speciesInvestigationVo) {

        SpeciesInvestigation speciesInvestigation =
                SpeciesInvestigationVo.getSpeciesInvestigation(speciesInvestigationVo);
        Map<String, Object> params = Maps.newHashMap();
        params.put("formNumber",speciesInvestigation.getFormNumber());
        List<SpeciesInvestigation> oldList = speciesInvestigationMapper.getSpeciesInvestigationByParam(params);
        if(oldList.size()>0){
            throw new SofnException("当前表格编号已存在");
        }
        //放入id,创建人,创建人id,创建时间.当前年份
        speciesInvestigation.setId(IdUtil.getUUId());
        User loginUser = UserUtil.getLoginUser();
        speciesInvestigation.setCreateUserId(loginUser.getId());
        speciesInvestigation.setCreateUserName(loginUser.getNickname());
        speciesInvestigation.setCreateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(speciesInvestigation.getInvestigationTime());
        String belongYear = calendar.get(Calendar.YEAR) + "";
        speciesInvestigation.setBelongYear(belongYear);
//        //获取当前用户角色列表
//        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
//        //判断当前用户是否为县级填报
//        if(roleColde.contains("agzirdd_county")){
//            speciesInvestigation.setRoleCode("agzirdd_county");
//        }
//        //是否为专家填报
//        if(roleColde.contains("agzirdd_expert")){
//            speciesInvestigation.setRoleCode("agzirdd_expert");
//        }
        //保存物种调查-基本信息
        boolean si = this.save((SpeciesInvestigation) setValueUtil.setNameById(speciesInvestigation));
        //判断该数据是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesInvestigationVo.getHasAlienSpecies())){
            //若有新发外来物种，则此时数据较为特殊，直接表示为专家填报
//            if("1".equals(speciesInvestigationVo.getInvestigatContentVo().getNewSpecies())){
//               speciesInvestigation.setRoleCode("agzirdd_expert");
//            }
            //获取并保存物种-调查内容
            InvestigatContent investigatContent = speciesInvestigationVo.getInvestigatContentVo();
            investigatContent.setSpeciesInvestigationId(speciesInvestigation.getId());
            investigatContentService.addInvestigatContent(investigatContent);
        }
        if(!si){
            throw new SofnException("保存物种调查相关信息异常,请重新操作!");
        }
    }


    //重新组装成省市区名称组合
    private String assemblingInfo(List<SysRegionTreeVo> strList){
        StringBuffer strBuf = new StringBuffer();
        for(SysRegionTreeVo vo :strList){
            strBuf.append(vo.getRegionName());
        }

        return strBuf.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpeciesInvestigation(SpeciesInvestigationVo speciesInvestigationVo) {
        //监测年度
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(speciesInvestigationVo.getInvestigationTime());
        String belongYear = calendar.get(Calendar.YEAR) + "";
        speciesInvestigationVo.setBelongYear(belongYear);

        //将vo转换为po对象
        SpeciesInvestigation speciesInvestigation =
                SpeciesInvestigationVo.getSpeciesInvestigation(speciesInvestigationVo);

        //判断是否存在物种入侵
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesInvestigation.getHasAlienSpecies())) {

            InvestigatContent oldInvestigatContent =
                    investigatContentService.getInvestigatContentBySpeciesInvestigationId(speciesInvestigation.getId());

            //获取并修改物种-调查内容
            InvestigatContent investigatContent = speciesInvestigationVo.getInvestigatContentVo();
            if(null != oldInvestigatContent){
                investigatContentService.updateInvestigatContent(investigatContent);
            }else{
                investigatContent.setSpeciesInvestigationId(speciesInvestigation.getId());
                investigatContentService.addInvestigatContent(investigatContent);
            }
        }else{

            InvestigatContent investigatContent = investigatContentService.getInvestigatContentBySpeciesInvestigationId(speciesInvestigation.getId());
            if(investigatContent != null){
                investigatContentService.removeInvestigatContent(speciesInvestigation.getId());
            }
        }

        //修改调查模块-调查基本信息
        setValueUtil.setNameById(speciesInvestigation);
        boolean si = this.updateById(speciesInvestigation);
        if(!si){
            throw new SofnException("修改物种调查相关信息异常,请重新操作!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSpeciesInvestigation(String id) {
        SpeciesInvestigation speciesInvestigation = speciesInvestigationMapper.getSpeciesInvestigationById(id);
        //判断是否存在入侵物种
        if(HasSpeciesEnum.EXIST.getCode().equals(speciesInvestigation.getHasAlienSpecies())) {
            //存在入侵物种
            //放入查询条件
            Map<String, Object> params = Maps.newHashMap();
            params.put("speciesInvestigationId", id);
            boolean ic = true;
            boolean ier = true;
            //删除调查内容->先判断是否存在数据.存在即删除
            InvestigatContent investigatContent = investigatContentService.getInvestigatContentBySpeciesInvestigationId(id);
            if(investigatContent != null){
                ic = investigatContentService.removeInvestigatContent(id);
            }
            //删除审核记录->先判断是否存在数据.存在即删除
            List<InvestigatExaminaRecord> investigatExaminaRecordList = investigatExaminaRecordService.getInvestigatExaminaRecordByCondition(params);
            if(investigatExaminaRecordList.size()>0){
                ier = investigatExaminaRecordService.removeInvestigatExaminaRecord(id);
            }
            if(!ic || !ier){
                throw new SofnException("删除异常,请重新操作!");
            }
        }
        this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAudit(String remark, List<BatchAuditItemVo> items) {
        if(items.size()==0)
            throw new SofnException("要审核的项不能为空");

        Map<String, Object> params = Maps.newHashMap();
        params.put("remark",remark);

        //循环处理
        SpeciesInvestigation si = null;
        for (BatchAuditItemVo item : items) {
            si = this.getById(item.getId());
            if(si==null)
                throw new SofnException("错误的id"+item.getId());

            AuditUtil.checkAuditRight(si.getStatus());
            AuditUtil.checkAuditNewStatus(item.getStatus());

            params.put("id",item.getId());
            params.put("status",item.getStatus());
            this.updateStatus(params);
        }
    }
}
