package com.sofn.agzirz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirz.enums.UserRoleType;
import com.sofn.agzirz.mapper.FkyjzhMapper;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.service.FkyjzhService;
import com.sofn.agzirz.sysapi.SysFileApi;
import com.sofn.agzirz.sysapi.bean.SysFileManageForm;
import com.sofn.agzirz.sysapi.bean.SysFileManageVo;
import com.sofn.agzirz.sysapi.bean.SysFileVo;
import com.sofn.agzirz.sysapi.bean.SysOrganization;
import com.sofn.agzirz.util.ServiceHelpUtil;
import com.sofn.agzirz.vo.FkyjzhVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FkyjzhServiceImpl extends ServiceImpl<FkyjzhMapper, FKYJZH> implements FkyjzhService {

    @Autowired
    private FkyjzhMapper fkyjzhMapper;

    @Autowired
    private SysFileApi sysFileApi;

    @Autowired
    private SysFileApi sysRegionApi;

    @Override
    public PageUtils<FkyjzhVo> getFkyjzhByPage(Map<String, Object> params, int pageNo, int pageSize) {

        PageHelper.offsetPage(pageNo,pageSize);
        //根据角色，行政级别不同，添加不同的查询条件
        ServiceHelpUtil.DaoQeuryParam daoQeuryParam = ServiceHelpUtil.getUserRoleQueryParam();
        if(daoQeuryParam!=null){
            params.put("colName",daoQeuryParam.getColName());
            params.put("colVal", daoQeuryParam.getColVal());
        }
        List<FKYJZH> fkyjzhs = fkyjzhMapper.getFkyjzhByCondition(params);
        PageInfo<FKYJZH> pageInfo = new PageInfo<>(fkyjzhs);
        List<FkyjzhVo> list = new ArrayList<>();
        for(FKYJZH fkyjzh : fkyjzhs){
            FkyjzhVo fkyjzhVo = FkyjzhVo.getFkyjzhVo(fkyjzh);
            String name = UserUtil.getUsernameById(fkyjzh.getReportUser());
            fkyjzhVo.setReportUserName(name);
            list.add(fkyjzhVo);
        }
        PageInfo<FkyjzhVo> pageInfo2 = new PageInfo<>(list);
        pageInfo2.setTotal(pageInfo.getTotal());
        pageInfo2.setPageNum(pageInfo.getPageNum());
        pageInfo2.setPageSize(pageInfo.getPageSize());
        PageUtils pageUtils = PageUtils.getPageUtils(pageInfo2);
        return pageUtils;
    }

    @Override
    public FkyjzhVo getFkyjzhById(String id) {
        FKYJZH fkyjzh = fkyjzhMapper.getFkyjzhById(id);
        if (fkyjzh == null){
            throw new SofnException("防控应急指挥信息异常,无相关详情!");
        }
        FkyjzhVo fkyjzhVo = FkyjzhVo.getFkyjzhVo(fkyjzh);
        if(!StringUtils.isBlank(fkyjzhVo.getEventImgs())){
            //根据文件图片idS批量获取对应文件List
            Result<List<SysFileManageVo>> listResult = sysFileApi.batchGetFileInfo(fkyjzhVo.getEventImgs());
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
            fkyjzhVo.setImgsUrl(list);
        }
        String eventLocation = fkyjzhVo.getEventLocation();
        if(StringUtils.isNotBlank(eventLocation)){
            String regionName = "";
            List<String> regionCodeList = new ArrayList<>(IdUtil.getIdsByStr(eventLocation));
            for(String codeId : regionCodeList){
                Result<String> result = sysRegionApi.getSysRegionName(codeId);
                if(null!=result){
                    String name = result.getData();
                    regionName = regionName+name;
                }
            }
            fkyjzhVo.setEventLocationName(regionName);
        }
        return fkyjzhVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFkyjzh(FKYJZH fkyjzh) {
        UserRoleType userRoleType = ServiceHelpUtil.getUserRoleType();
        //专家角色不存省市县
        if (!userRoleType.equals(UserRoleType.EXPERT)) {
            String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
            if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
                throw new SofnException("未获取到登录用户所属机构信息!");
            }
            SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
            String regioncode = sysOrganization.getRegioncode();
            List<String> areaList = JsonUtils.json2List(regioncode, String.class);
            if(areaList.size()==1){
                fkyjzh.setProvinceId(areaList.get(0));
            }
            if(areaList.size()==2){
                fkyjzh.setProvinceId(areaList.get(0));
                fkyjzh.setCityId(areaList.get(1));
            }
            if(areaList.size()==3){
                fkyjzh.setProvinceId(areaList.get(0));
                fkyjzh.setCityId(areaList.get(1));
                fkyjzh.setCountyId(areaList.get(2));
            }
        }
        //上传文件图片处理
        if(!StringUtils.isBlank(fkyjzh.getEventImgs())){
            //激活上传图片文件
            String newpicIdsStr = fkyjzh.getEventImgs();
            //判断激活图片是否全部激活
            checkPicOnOrOff(newpicIdsStr);
        }

        ServiceHelpUtil.AreaRegionCode regionCode = ServiceHelpUtil.getRegionCode();

        if (regionCode != null) {
            fkyjzh.setProvinceId(regionCode.getProvinceId());
            fkyjzh.setCityId(regionCode.getCityId());
            fkyjzh.setCountyId(regionCode.getCountyId());
        }
        fkyjzh.setEnableStatus("Y");
        this.save(fkyjzh);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFkyjzh(FKYJZH fkyjzh,String userId) {
        FKYJZH fkyjzhOld = fkyjzhMapper.getFkyjzhById(fkyjzh.getFkyjzhNo());

        if(!fkyjzhOld.getReportUser().equals(userId)){
            throw new SofnException("只能修改本人上传的数据");
        }

        if(!StringUtils.isBlank(fkyjzh.getEventImgs())){
            if(!StringUtils.isBlank(fkyjzhOld.getEventImgs())){
                //获取旧的图片IDs
                String oldIds = fkyjzhOld.getEventImgs();
                List<String> oldList = new ArrayList<>(IdUtil.getIdsByStr(oldIds));
                List<String> oldList2 = new ArrayList<>(IdUtil.getIdsByStr(oldIds));
                //获取新的图片IDs
                String newIds = fkyjzh.getEventImgs();
                List<String> newList = new ArrayList<>(IdUtil.getIdsByStr(newIds));
                List<String> newList2 = new ArrayList<>(IdUtil.getIdsByStr(newIds));

                //获取需要激活的图片ID
                newList.removeAll(oldList);
                //获取需要删除的图片ID
                oldList2.removeAll(newList2);

                if(newList.size()>0){
                    String fileOn = IdUtil.getStrIdsByList(newList);
                    //激活图片
                    checkPicOnOrOff(fileOn);
                }

                if(oldList2.size()>0){
                    String delete = IdUtil.getStrIdsByList(oldList2);
                    //删除图片
                    sysFileApi.batchDeleteFile(delete);
                }
            }else{
                //已保存的图片为空的情况
                checkPicOnOrOff(fkyjzh.getEventImgs());
            }
        }else {
            //修改图片为空的情况
            if(!StringUtils.isBlank(fkyjzhOld.getEventImgs())){
                sysFileApi.batchDeleteFile(fkyjzhOld.getEventImgs());
            }
        }
        fkyjzh.setEnableStatus("Y");

        UserRoleType userRoleType = ServiceHelpUtil.getUserRoleType();
        //专家角色不存省市县
        if (!userRoleType.equals(UserRoleType.EXPERT)) {
            String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
            if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
                throw new SofnException("未获取到登录用户所属机构信息!");
            }
            SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
            String regioncode = sysOrganization.getRegioncode();
            List<String> areaList = JsonUtils.json2List(regioncode, String.class);
            if(areaList.size()==1){
                fkyjzh.setProvinceId(areaList.get(0));
            }
            if(areaList.size()==2){
                fkyjzh.setProvinceId(areaList.get(0));
                fkyjzh.setCityId(areaList.get(1));
            }
            if(areaList.size()==3){
                fkyjzh.setProvinceId(areaList.get(0));
                fkyjzh.setCityId(areaList.get(1));
                fkyjzh.setCountyId(areaList.get(2));
            }
        }

        this.updateById(fkyjzh);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeFkyjzh(String id,String userId) {
        //移除文件服务器中上传的相关图片
        FKYJZH fkyjzh = fkyjzhMapper.getFkyjzhById(id);
        if(!fkyjzh.getReportUser().equals(userId)){
            throw new SofnException("只能删除本人上传的数据");
        }
        if(!StringUtils.isBlank(fkyjzh.getEventImgs())){
            sysFileApi.batchDeleteFile(fkyjzh.getEventImgs());
        }
        fkyjzh.setEnableStatus("N");
        this.updateById(fkyjzh);
    }

    //判断激活图片是否全部激活
    private void checkPicOnOrOff(String newPicIdsStr) {
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirz");
        sysFileManageForm.setIds(newPicIdsStr);
        sysFileManageForm.setRemark("外来入侵指挥图片");
        //调用文件激活接口激活相关图片文件
        Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);
        //激活后相关文件list
        List<SysFileVo> fileOnData = listResult.getData();

        //未激活文件图片id集合
        List<String> idsByOff = IdUtil.getIdsByStr(newPicIdsStr);
        //已激活文件图片id集合
        List<String> idsByOn = fileOnData.stream().map(SysFileVo::getId).collect(Collectors.toList());
        if (idsByOff.size() != idsByOn.size()) {
            String strIdsByList = IdUtil.getStrIdsByList(idsByOn);
            sysFileApi.batchDeleteFile(strIdsByList);
            throw new SofnException("图片上传激活失败,请重新上传!");
        }
    }

    @Override
    public List<FKYJZH> getFkyjzhList(Map<String, Object> params) {
        //根据角色，行政级别不同，添加不同的查询条件
        ServiceHelpUtil.DaoQeuryParam daoQeuryParam = ServiceHelpUtil.getUserRoleQueryParam();
        if(daoQeuryParam!=null){
            params.put("colName",daoQeuryParam.getColName());
            params.put("colVal", daoQeuryParam.getColVal());
        }
        List<FKYJZH> fkyjzhs = fkyjzhMapper.getFkyjzhByCondition(params);
        return fkyjzhs;
    }

}
