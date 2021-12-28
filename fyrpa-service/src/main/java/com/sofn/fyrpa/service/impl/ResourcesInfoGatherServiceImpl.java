package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.fyrpa.enums.DeleteEnum;
import com.sofn.fyrpa.mapper.AquaticResourcesProtectionInfoMapper;
import com.sofn.fyrpa.mapper.EnvironmentResourcesMapper;
import com.sofn.fyrpa.mapper.InformationAuditMapper;
import com.sofn.fyrpa.mapper.ManagerOrgMapper;
import com.sofn.fyrpa.model.AquaticResourcesProtectionInfo;
import com.sofn.fyrpa.model.EnvironmentResources;
import com.sofn.fyrpa.model.InformationAudit;
import com.sofn.fyrpa.model.ManagerOrg;
import com.sofn.fyrpa.service.ResourcesInfoGatherService;
import com.sofn.fyrpa.sysapi.SysFileApi;
import com.sofn.fyrpa.sysapi.bean.SysFileManageVo;
import com.sofn.fyrpa.util.ExportUtil;
import com.sofn.fyrpa.vo.*;
import com.sofn.fyrpa.web.ExcelModel.AquaticResourcesProtectionInfoExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Service
public class ResourcesInfoGatherServiceImpl implements ResourcesInfoGatherService {

    @Autowired
    private EnvironmentResourcesMapper environmentResourcesMapper;
    @Autowired
    private ManagerOrgMapper managerOrgMapper;
    @Autowired
    private InformationAuditMapper informationAuditMapper;
    @Autowired
    private AquaticResourcesProtectionInfoMapper aquaticResourcesProtectionInfoMapper;
    @Autowired(required = false)
    private SysFileApi sysFileApi;

    @Override
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByCondition(Integer pageNo, Integer pageSize, String submitTime, String[] regionCodeArr, String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name, String majorProtectObject) {
        Page<AquaticResourcesProtectionInfoVoList> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }

        String regionCode = StringUtils.join(regionCodeArr, ",");
        IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectGatherListByCondition(page,submitTime,regionCode,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);
        return Result.ok(aquaticResourcesProtectionInfoVoListIPage);
    }

    @Override
    public Result selectDetailsById(String id) {
        QueryWrapper<AquaticResourcesProtectionInfo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",id);
        wrapper1.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);


        if(aquaticResourcesProtectionInfo!=null){
            QueryWrapper<EnvironmentResources>wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id",aquaticResourcesProtectionInfo.getEnvironmentResourcesId());
            wrapper2.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            EnvironmentResources  environmentResources = this.environmentResourcesMapper.selectOne(wrapper2);

            QueryWrapper<ManagerOrg>wrapper3 = new QueryWrapper<>();
            wrapper3.eq("id",aquaticResourcesProtectionInfo.getManagerOrgId());
            wrapper3.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            ManagerOrg managerOrg = this.managerOrgMapper.selectOne(wrapper3);

            ListVo listVo = new ListVo();
            AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo = new AquaticResourcesProtectionInfoVo();
            aquaticResourcesProtectionInfoVo.setApproveTime(DateUtils.format(aquaticResourcesProtectionInfo.getApproveTime()));
            aquaticResourcesProtectionInfoVo.setAdjustTime(DateUtils.format(aquaticResourcesProtectionInfo.getAdjustTime()));
            aquaticResourcesProtectionInfoVo.setRegionCode(aquaticResourcesProtectionInfo.getRegionCode().split(","));

            String fileIds = aquaticResourcesProtectionInfo.getFileIds();
            Result<SysFileManageVo> sysFileApiOne = sysFileApi.getOne(fileIds);
            String fileName = sysFileApiOne.getData().getFileName();
            aquaticResourcesProtectionInfoVo.setFileName(fileName);

            EnvironmentResourcesVo environmentResourcesVo = new EnvironmentResourcesVo();
            environmentResourcesVo.setStartTime(DateUtils.format(environmentResources.getStartTime(),DateUtils.DATE_PATTERN));
            environmentResourcesVo.setEndTime(DateUtils.format(environmentResources.getEndTime(),DateUtils.DATE_PATTERN));
            String latitude = environmentResources.getLatitude();
            String longitude = environmentResources.getLongitude();
            String[] str = latitude.split("-");
            String[] str2 = longitude.split("-");

            environmentResourcesVo.setLatitudeStart(str[0].substring(2));
            environmentResourcesVo.setLatitudeEnd(str[1]);
            environmentResourcesVo.setLatitudeRange(latitude.substring(0,2));

            environmentResourcesVo.setLongitudeStart(str2[0].substring(2));
            environmentResourcesVo.setLongitudeEnd(str2[1]);
            environmentResourcesVo.setLongitudeRange(longitude.substring(0,2));
            ManagerOrgVo managerOrgVo  = new ManagerOrgVo();
            BeanUtils.copyProperties(aquaticResourcesProtectionInfo,aquaticResourcesProtectionInfoVo);
            BeanUtils.copyProperties(environmentResources,environmentResourcesVo);
            BeanUtils.copyProperties(managerOrg,managerOrgVo);
            listVo.setAquaticResourcesProtectionInfoVo(aquaticResourcesProtectionInfoVo);
            listVo.setEnvironmentResourcesVo(environmentResourcesVo);
            listVo.setManagerOrgVo(managerOrgVo);
            return Result.ok(listVo);
        }

        return null;
    }

    @Override
    public Result selectInfoAuditList(String id) {
        QueryWrapper<AquaticResourcesProtectionInfo>wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",id);
        wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);
        if(aquaticResourcesProtectionInfo!=null){
            List<InformationAudit> list = this.informationAuditMapper.selectListData(aquaticResourcesProtectionInfo.getId());
            return Result.ok(list);
        }
        return null;
    }

    @Override
    public void export(String fileName, HttpServletResponse httpServletResponse, String submitTime, String[] regionCodeArr, String basinOrSeaArea, String riverOrMaritimeSpace, String keyword,String name,String majorProtectObject) {
        List<AquaticResourcesProtectionInfoVoList> list = this.aquaticResourcesProtectionInfoMapper.selectGatherListByCondition(submitTime, org.apache.commons.lang3.StringUtils.join(regionCodeArr, ','), basinOrSeaArea, riverOrMaritimeSpace, keyword,name, majorProtectObject);
        /*if(list.isEmpty()|| list.size()==0){
            throw new SofnException("无数据");
         }*/
         if(!CollectionUtils.isEmpty(list)){
             List<AquaticResourcesProtectionInfoExcel> excelList = Lists.newArrayList();
             list.forEach(aquaticResourcesProtectionInfoVoList -> {
                 AquaticResourcesProtectionInfoExcel excel = new AquaticResourcesProtectionInfoExcel();
                 excel.setName(aquaticResourcesProtectionInfoVoList.getName());
                 excel.setLongitude(aquaticResourcesProtectionInfoVoList.getLongitude());
                 excel.setLatitude(aquaticResourcesProtectionInfoVoList.getLatitude());
                 excel.setCurrentProtectionArea(aquaticResourcesProtectionInfoVoList.getCurrentProtectionArea());
                 excel.setMajorProtectObject(aquaticResourcesProtectionInfoVoList.getMajorProtectObject());
                 excel.setApproveTime(aquaticResourcesProtectionInfoVoList.getApproveTime());
                 excel.setApproveDocNumber(aquaticResourcesProtectionInfoVoList.getApproveDocNumber());
                 excel.setManagerOrgName(aquaticResourcesProtectionInfoVoList.getManagerOrgName());
                 excel.setSubmitUnit(aquaticResourcesProtectionInfoVoList.getSubmitUnit());
                 excelList.add(excel);
             });

             ExportUtil.createExcel(AquaticResourcesProtectionInfoExcel.class,excelList,httpServletResponse,fileName);
         } else {
             log.warn("无数据");
         }

    }

    @Override
   public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByAreasSort(String sort,Integer pageNo,Integer pageSize, String submitTime, String[] regionCodeArr,
                                                                                          String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name,String majorProtectObject) {

        Page<AquaticResourcesProtectionInfoVoList> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }
        String regionCode = StringUtils.join(regionCodeArr, ",");

        if(!StringUtils.isEmpty(sort)) {
            if(sort.equals("1")){
                IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoLists = this.aquaticResourcesProtectionInfoMapper.selectGatherListByAreasDesc(page,submitTime,regionCode,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);

                return Result.ok(aquaticResourcesProtectionInfoVoLists);
            }

            if(sort.equals("-1")){
                IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoLists = this.aquaticResourcesProtectionInfoMapper.selectGatherListByAreasAsc(page,submitTime,regionCode,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);
                return Result.ok(aquaticResourcesProtectionInfoVoLists);
            }
        }
       return null;
    }

    @Override
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByTimesSort(String sort,Integer pageNo,Integer pageSize, String submitTime, String[] regionCodeArr,
                                                                                           String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name,String majorProtectObject) {

        Page<AquaticResourcesProtectionInfoVoList> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }

        String regionCode = StringUtils.join(regionCodeArr, ",");

        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals("1")) {
                IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoLists = this.aquaticResourcesProtectionInfoMapper.selectGatherListByTimesDesc(page,submitTime,regionCode,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);
                return Result.ok(aquaticResourcesProtectionInfoVoLists);
            }

            if(sort.equals("-1")){
                IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoLists = this.aquaticResourcesProtectionInfoMapper.selectGatherListByTimesAsc(page,submitTime,regionCode,basinOrSeaArea,riverOrMaritimeSpace,keyword,name,majorProtectObject);
                return Result.ok(aquaticResourcesProtectionInfoVoLists);
            }

        }
        return null;
    }
}
