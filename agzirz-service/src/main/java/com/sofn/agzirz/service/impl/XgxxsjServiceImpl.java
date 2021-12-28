package com.sofn.agzirz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirz.enums.UserRoleType;
import com.sofn.agzirz.mapper.FkyjzhMapper;
import com.sofn.agzirz.mapper.XgxxsjMapper;
import com.sofn.agzirz.model.FKYJZH;
import com.sofn.agzirz.model.Xgxxsj;
import com.sofn.agzirz.service.FkyjzhService;
import com.sofn.agzirz.service.XgxxsjService;
import com.sofn.agzirz.sysapi.SysFileApi;
import com.sofn.agzirz.sysapi.bean.SysFileManageForm;
import com.sofn.agzirz.sysapi.bean.SysFileManageVo;
import com.sofn.agzirz.sysapi.bean.SysFileVo;
import com.sofn.agzirz.sysapi.bean.SysOrganization;
import com.sofn.agzirz.util.ServiceHelpUtil;
import com.sofn.agzirz.vo.FkyjzhVo;
import com.sofn.agzirz.vo.XgxxsjVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class XgxxsjServiceImpl extends ServiceImpl<XgxxsjMapper, Xgxxsj> implements XgxxsjService {

    @Autowired
    private XgxxsjMapper xgxxsjMapper;

    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public PageUtils<XgxxsjVo> getXgxxsjByPage(Map<String, Object> params, int pageNo, int pageSize) {

        PageHelper.offsetPage(pageNo,pageSize);
        //根据角色，行政级别不同，添加不同的查询条件
        ServiceHelpUtil.DaoQeuryParam daoQeuryParam = ServiceHelpUtil.getUserRoleQueryParam();
        if(daoQeuryParam!=null){
            params.put("colName",daoQeuryParam.getColName());
            params.put("colVal", daoQeuryParam.getColVal());
        }
        List<Xgxxsj> xgxxsjs = xgxxsjMapper.getXgxxsjByCondition(params);
        PageInfo<Xgxxsj> pageInfo = new PageInfo<>(xgxxsjs);
        List<XgxxsjVo> list = new ArrayList<>();
        for(Xgxxsj xgxxsj : xgxxsjs){
            XgxxsjVo xgxxsjVo = XgxxsjVo.getXgxxsjVo(xgxxsj);
            String name = UserUtil.getUsernameById(xgxxsj.getReportUser());
            xgxxsjVo.setReportUserName(name);
            list.add(xgxxsjVo);
        }
        PageInfo<XgxxsjVo> pageInfo2 = new PageInfo<>(list);
        pageInfo2.setTotal(pageInfo.getTotal());
        pageInfo2.setPageNum(pageInfo.getPageNum());
        pageInfo2.setPageSize(pageInfo.getPageSize());
        PageUtils pageUtils = PageUtils.getPageUtils(pageInfo2);
        return pageUtils;
    }

    @Override
    public XgxxsjVo getXgxxsjById(String id) {
        Xgxxsj xgxxsj = xgxxsjMapper.getXgxxsjById(id);
        if (xgxxsj == null){
            throw new SofnException("相关信息收集信息异常,无相关详情!");
        }
        XgxxsjVo xgxxsjVo = XgxxsjVo.getXgxxsjVo(xgxxsj);
        if(!StringUtils.isBlank(xgxxsjVo.getRelevantFiles())){
            //根据文件图片idS批量获取对应文件List
            Result<List<SysFileManageVo>> listResult = sysFileApi.batchGetFileInfo(xgxxsjVo.getRelevantFiles());
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
            xgxxsjVo.setRelevantFilesUrl(list);
        }
        return xgxxsjVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addXgxxsj(Xgxxsj xgxxsj) {
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
                xgxxsj.setProvinceId(areaList.get(0));
            }
            if(areaList.size()==2){
                xgxxsj.setProvinceId(areaList.get(0));
                xgxxsj.setCityId(areaList.get(1));
            }
            if(areaList.size()==3){
                xgxxsj.setProvinceId(areaList.get(0));
                xgxxsj.setCityId(areaList.get(1));
                xgxxsj.setCountyId(areaList.get(2));
            }
        }

        //上传文件图片处理
        if(!StringUtils.isBlank(xgxxsj.getRelevantFiles())){
            //激活上传图片文件
            String newpicIdsStr = xgxxsj.getRelevantFiles();
            //判断激活图片是否全部激活
            checkPicOnOrOff(newpicIdsStr);
        }

        ServiceHelpUtil.AreaRegionCode regionCode = ServiceHelpUtil.getRegionCode();

        if (regionCode != null) {
            xgxxsj.setProvinceId(regionCode.getProvinceId());
            xgxxsj.setCityId(regionCode.getCityId());
            xgxxsj.setCountyId(regionCode.getCountyId());
        }
        xgxxsj.setEnableStatus("Y");
        this.save(xgxxsj);
    }

    @Override
    public void updateXgxxsj(Xgxxsj xgxxsj,String userId) {

        Xgxxsj xgxxsjOld = xgxxsjMapper.getXgxxsjById(xgxxsj.getXgxxsjNo());
        if(!xgxxsjOld.getReportUser().equals(userId)){
            throw new SofnException("只能修改本人上传的数据");
        }

        if(!StringUtils.isBlank(xgxxsj.getRelevantFiles())){
            if(!StringUtils.isBlank(xgxxsjOld.getRelevantFiles())){
                //获取旧的图片IDs
                String oldIds = xgxxsjOld.getRelevantFiles();
                List<String> oldList = new ArrayList<>(IdUtil.getIdsByStr(oldIds));
                List<String> oldList2 = new ArrayList<>(IdUtil.getIdsByStr(oldIds));
                //获取新的图片IDs
                String newIds = xgxxsj.getRelevantFiles();
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
                checkPicOnOrOff(xgxxsj.getRelevantFiles());
            }
        }else {
            //修改图片为空的情况
            if(!StringUtils.isBlank(xgxxsjOld.getRelevantFiles())){
                sysFileApi.batchDeleteFile(xgxxsjOld.getRelevantFiles());
            }
        }

        xgxxsj.setEnableStatus("Y");

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
                xgxxsj.setProvinceId(areaList.get(0));
            }
            if(areaList.size()==2){
                xgxxsj.setProvinceId(areaList.get(0));
                xgxxsj.setCityId(areaList.get(1));
            }
            if(areaList.size()==3){
                xgxxsj.setProvinceId(areaList.get(0));
                xgxxsj.setCityId(areaList.get(1));
                xgxxsj.setCountyId(areaList.get(2));
            }
        }

        this.updateById(xgxxsj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeXgxxsj(String id,String userId) {
        //移除文件服务器中上传的相关图片
        Xgxxsj xgxxsj = xgxxsjMapper.getXgxxsjById(id);
        if(!xgxxsj.getReportUser().equals(userId)){
            throw new SofnException("只能删除本人上传的数据");
        }
        if(!StringUtils.isBlank(xgxxsj.getRelevantFiles())){
            sysFileApi.batchDeleteFile(xgxxsj.getRelevantFiles());
        }
        xgxxsj.setEnableStatus("N");
        this.updateById(xgxxsj);
    }

    //判断激活图片是否全部激活
    private void checkPicOnOrOff(String newPicIdsStr) {
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirz");
        sysFileManageForm.setIds(newPicIdsStr);
        sysFileManageForm.setRemark("外来入侵指挥文件");
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
    public List<Xgxxsj> getXgxxsjList(Map<String, Object> params) {
        //根据角色，行政级别不同，添加不同的查询条件
        ServiceHelpUtil.DaoQeuryParam daoQeuryParam = ServiceHelpUtil.getUserRoleQueryParam();
        if(daoQeuryParam!=null){
            params.put("colName",daoQeuryParam.getColName());
            params.put("colVal", daoQeuryParam.getColVal());
        }
        List<Xgxxsj> xgxxsjs = xgxxsjMapper.getXgxxsjByCondition(params);
        return xgxxsjs;
    }

}
