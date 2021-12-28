package com.sofn.agzirz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirz.enums.UserRoleType;
import com.sofn.agzirz.mapper.SjsjMapper;
import com.sofn.agzirz.model.Sjsj;
import com.sofn.agzirz.service.ISjsjService;
import com.sofn.agzirz.sysapi.AgpjzbApi;
import com.sofn.agzirz.sysapi.SysFileApi;
import com.sofn.agzirz.sysapi.bean.*;
import com.sofn.agzirz.util.ServiceHelpUtil;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 事件收集模块 服务实现类
 * </p>
 *
 * @author simon
 * @since 2020-03-04
 */
@Service
public class SjsjServiceImpl extends ServiceImpl<SjsjMapper, Sjsj> implements ISjsjService {
    @Autowired
    private SysFileApi sysFileApi;
    @Autowired
    private AgpjzbApi agpjzbApi;

    @Override
    public PageUtils<Sjsj> getSjsjPageList(Integer pageNo,Integer pageSize, String disposalOrgani, String eventLocation, Date startTime, Date endTime) {
        PageHelper.offsetPage(pageNo,pageSize);
        Map<String, Object> param = new HashMap();
        param.put("disposalOrgani",disposalOrgani);
        param.put("eventLocation",eventLocation);
        param.put("startTime",startTime);
        param.put("endTime",endTime);

        //根据角色，行政级别不同，添加不同的查询条件
        ServiceHelpUtil.DaoQeuryParam daoQeuryParam = ServiceHelpUtil.getUserRoleQueryParam();
        if(daoQeuryParam!=null){
            param.put("colName",daoQeuryParam.getColName());
            param.put("colVal", daoQeuryParam.getColVal());
        }

        List<Sjsj> list = baseMapper.getPage(param);

        list  = assemblyLocationUserName(list);

        PageInfo<Sjsj> pageInfo = new PageInfo<>(list);

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<Sjsj> getSjsjList(String disposalOrgani, String eventLocation, Date startTime, Date endTime) {
        ServiceHelpUtil.getOrgInfo();

        QueryWrapper<Sjsj> wrapper = generateQueryWrapper(disposalOrgani, eventLocation, startTime, endTime);
        ServiceHelpUtil.DaoQeuryParam daoQeuryParam = ServiceHelpUtil.getUserRoleQueryParam();
        if(daoQeuryParam!=null){
            wrapper.eq(daoQeuryParam.getColName(),daoQeuryParam.getColVal());
        }
        wrapper.orderByDesc("REPORT_TIME");

        List<Sjsj> list = list(wrapper);

        list  = assemblyLocationUserName(list);

        return list;
    }

    /**
     * 组装地点名称，用户名称到实体中
     * @param list
     * @return
     */
    private List<Sjsj> assemblyLocationUserName(List<Sjsj> list){
        if (list != null) {
            Map<String, String> eoMap=remoteGetEmergencyOrg();
            for (Sjsj s : list) {
                String el = s.getEventLocation();
                if(StringUtils.hasText(el)){
                    s.setEventLocationName(getReginNameByCodes(el));
                }
                s.setReportUserName(UserUtil.getUsernameById(s.getReportUser()));

                s.setDisposalOrgani(eoMap.get(s.getDisposalOrgani()));
            }
        }

        return list;
    }

    private Map<String, String> remoteGetEmergencyOrg(){
        Result<List<DropDownVo>> res = agpjzbApi.listForEmergencyOrg();
        Map<String,String> data = new HashMap();
        if (Result.CODE.equals(res.getCode())) {
            for (DropDownVo dd : res.getData()) {
                data.put(dd.getId(),dd.getName());
            }
        }
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrupdateSjsj(Sjsj model) {
        int rows = 0;

        //execute dao method
        if (StringUtils.hasText(model.getSjsjNo())) {
            Sjsj sjsjById = getById(model.getSjsjNo());
            if(sjsjById!=null){

                if(!sjsjById.getReportUser().equals(UserUtil.getLoginUserId()))
                    throw new SofnException("不可编辑他人数据");

                //check different files ids
                Map<String, List<String>> diffMap = getDiffIdList(sjsjById, model);
                List<String> overdueFileIds = diffMap.get("overdueFileIds");
                List<String> newFileIds = diffMap.get("newFileIds");
                if(newFileIds!= null && newFileIds.size()>0){
                    //activate files
                    checkFileOnOrOff(newFileIds);
                }
                if(overdueFileIds!=null && overdueFileIds.size()>0){
                    //delete files
                    sysFileApi.batchDeleteFile(overdueFileIds.stream().collect(Collectors.joining(",")));
                }

                rows = baseMapper.updateById(model);
            }
        }else{

            UserRoleType userRoleType = ServiceHelpUtil.getUserRoleType();
            //除开专家用户，其他都存用户具体的区划信息
            if(!userRoleType.equals(UserRoleType.EXPERT)){
                ServiceHelpUtil.AreaRegionCode regionCode = ServiceHelpUtil.getRegionCode();
                if (regionCode != null) {
                    model.setProvinceId(regionCode.getProvinceId());
                    model.setCityId(regionCode.getCityId());
                    model.setCountyId(regionCode.getCountyId());
                }
            }
            //add
            List<String> sjsjFileIds = getSjsjFileIds(model);
            //activate files
            checkFileOnOrOff(sjsjFileIds);

            model.setEnableStatus("Y");
            model.setSjsjNo(IdUtil.getUUId());

            model.setReportTime(new Date());

            rows = baseMapper.insert(model);
        }
        return rows;
    }

    private Map<String,List<String>> getDiffIdList(Sjsj oldModel, Sjsj newModel) {
        Map<String, List<String>> map = new HashMap();

        if (oldModel != null && newModel != null) {

            final List<String> oldModelFileIds = getSjsjFileIds(oldModel);
            final List<String> newModelFileIds = getSjsjFileIds(newModel);

            List<String> overdueFileIds = oldModelFileIds.stream()
                    .filter(o -> !newModelFileIds.contains(o)).collect(Collectors.toList());
            List<String> newFileIds = newModelFileIds.stream()
                    .filter(o -> !oldModelFileIds.contains(o)).collect(Collectors.toList());

            map.put("newFileIds", newFileIds);
            map.put("overdueFileIds",overdueFileIds);
        }
        return map;
    }

    private List<String> getSjsjFileIds(Sjsj model){
        List<String> list = new ArrayList();
        if (StringUtils.hasText(model.getEventAbtFiles())) {
            list.addAll(IdUtil.getIdsByStr(model.getEventAbtFiles()));
        }
        if (StringUtils.hasText(model.getEventImgs())) {
            list.addAll(IdUtil.getIdsByStr(model.getEventImgs()));
        }
        if (StringUtils.hasText(model.getEventVedios())) {
            list.addAll(IdUtil.getIdsByStr(model.getEventVedios()));
        }
        if (StringUtils.hasText(model.getProcessReport())) {
            list.addAll(IdUtil.getIdsByStr(model.getProcessReport()));
        }

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSjsjById(String sjsjNo) {
        Sjsj model = baseMapper.selectById(sjsjNo);
        boolean flag = false;
        if (model != null && model.getEnableStatus().equals("Y")) {
            if(!model.getReportUser().equals(UserUtil.getLoginUserId()))
                throw new SofnException("不可删除他人数据");

            model.setEnableStatus("N");
            int rows = baseMapper.updateById(model);
            if (rows > 0) {
                //
                List<String> sjsjFileIds = getSjsjFileIds(model);
                if (sjsjFileIds != null && sjsjFileIds.size() > 0) {
                    sysFileApi.batchDeleteFile(IdUtil.getStrIdsByList(sjsjFileIds));
                }
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Sjsj getSjsjShowById(String sjsjNo) {
        Sjsj sjsj = getById(sjsjNo);
        if (sjsj != null) {
            if(StringUtils.hasText(sjsj.getEventAbtFiles())){
                sjsj.setEventAbtFilesList(getFilesJsonByIds(sjsj.getEventAbtFiles()));
            }
            if(StringUtils.hasText(sjsj.getEventImgs())){
                sjsj.setEventImgsList(getFilesJsonByIds(sjsj.getEventImgs()));
            }
            if(StringUtils.hasText(sjsj.getEventVedios())){
                sjsj.setEventVediosList(getFilesJsonByIds(sjsj.getEventVedios()));
            }
            if(StringUtils.hasText(sjsj.getProcessReport())){
                sjsj.setProcessReportList(getFilesJsonByIds(sjsj.getProcessReport()));
            }

            String eventLocation = sjsj.getEventLocation();
            if(StringUtils.hasText(eventLocation)){
                sjsj.setEventLocationName(getReginNameByCodes(eventLocation));
            }
        }
        return sjsj;
    }

    private String getReginNameByCodes(String codes){
        String regionName = "";
        List<String> regionCodeList = new ArrayList<>(IdUtil.getIdsByStr(codes));
        for(String codeId : regionCodeList){
            Result<String> result = sysFileApi.getSysRegionName(codeId);
            if(null!=result){
                String name = result.getData();
                regionName = regionName+name;
            }
        }
        return regionName;
    }

    private List<Map<String, Object>> getFilesJsonByIds(String ids){
        //根据文件图片idS批量获取对应文件List
        Result<List<SysFileManageVo>> listResult = sysFileApi.batchGetFileInfo(ids);
        List<Map<String, Object>> list = new ArrayList<>();
        if(listResult.getData().size()>0){
            for(SysFileManageVo sysFileManageVo:listResult.getData()){
                Map<String, Object> params = Maps.newHashMap();
                params.put("id", sysFileManageVo.getId());
                params.put("url", sysFileManageVo.getFilePath());
                params.put("name", sysFileManageVo.getFileName());
                list.add(params);
            }
        }
        return list;
    }

    private QueryWrapper<Sjsj> generateQueryWrapper(String disposalOrgani, String eventLocation, Date startTime, Date endTime) {
        QueryWrapper<Sjsj> wrapper = Wrappers.query();

        wrapper.eq("ENABLE_STATUS", "Y")//only Y status can be finded
                .ge(startTime != null,"EVENT_TIME", startTime)
                .le(endTime != null,"EVENT_TIME", endTime)
                .like(StringUtils.hasText(disposalOrgani),"DISPOSAL_ORGANI", disposalOrgani)
                .like(StringUtils.hasText(eventLocation),"EVENT_LOCATION", eventLocation) ;

        return wrapper;
    }

    //判断激活图片是否全部激活
    private void checkFileOnOrOff(List<String> idList) {
        if (idList == null || idList.size() == 0) {
            return;
        }
        String fileStr = IdUtil.getStrIdsByList(idList);

        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirz");
        sysFileManageForm.setIds(fileStr);
        sysFileManageForm.setRemark("外来生物入侵防控应急指挥系统文件");
        //调用文件激活接口激活相关图片文件
        Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);
        //激活后相关文件list
        List<SysFileVo> fileOnData = listResult.getData();

        //已激活文件图片id集合
        List<String> idsByOn = fileOnData.stream().map(SysFileVo::getId).collect(Collectors.toList());
        if (idList.size() != idsByOn.size()) {
            String strIdsByList = IdUtil.getStrIdsByList(idsByOn);
            sysFileApi.batchDeleteFile(strIdsByList);
            throw new SofnException("文件上传激活失败,请重新上传!");
        }
    }
}
