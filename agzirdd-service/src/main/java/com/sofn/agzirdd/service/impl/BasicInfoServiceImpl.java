package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.excelmodel.BasicInfoExcel;
import com.sofn.agzirdd.mapper.BasicInfoMapper;
import com.sofn.agzirdd.model.BasicInfo;
import com.sofn.agzirdd.service.BasicInfoService;
import com.sofn.agzirdd.sysapi.bean.SysRegionTreeVo;
import com.sofn.agzirdd.util.SetValueUtil;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author Administrator
 */
@Service
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements BasicInfoService {

    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Resource
    private SetValueUtil setValueUtil;

    @Override
    public PageUtils<BasicInfo> getBasicInfoListByPage(Map<String, Object> params, int pageNo, int pageSize) {

        PageHelper.offsetPage(pageNo, pageSize);
        List<BasicInfo> basicInfoList = basicInfoMapper.getBasicInfoByCondition(params);
        PageInfo<BasicInfo> pageInfo = new PageInfo<>(basicInfoList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<BasicInfo> getBasicInfoListByQuery(Map<String, Object> params) {
        return basicInfoMapper.getBasicInfoByCondition(params);
    }

    @Override
    public List<BasicInfoExcel> getBasicInfoLisToExport(Map<String, Object> params) {
        List<BasicInfo> basicInfoByCondition = basicInfoMapper.getBasicInfoByCondition(params);
        List<BasicInfoExcel> basicInfoExcelList = new ArrayList<>();
        basicInfoByCondition.forEach(
                baseData ->{
                    BasicInfoExcel basicInfoExcel = new BasicInfoExcel();
                    BeanUtils.copyProperties(baseData,basicInfoExcel);
                    String description = StatusEnum.getDescriptionByCode(baseData.getStatus());
                    basicInfoExcel.setStatus(description);
                    basicInfoExcelList.add(basicInfoExcel);
                }
        );
        return basicInfoExcelList;
    }

    @Override
    public BasicInfo getBasicInfoById(String id) {

        return basicInfoMapper.getBasicInfoById(id);
    }

    @Override
    public void updateStatus(Map<String, Object> params) {
        basicInfoMapper.updateStatus(params);
    }

    @Override
    public void addBasicInfo(BasicInfo basicInfo) {
        User loginUser = UserUtil.getLoginUser();
        Calendar calendar = Calendar.getInstance();
        String belongYear = calendar.get(Calendar.YEAR) + "";
        //放入当前年份
        basicInfo.setBelongYear(belongYear);
        //放入创建人id,创建人名称,创建时间
        basicInfo.setCreateUserId(loginUser.getId());
        basicInfo.setCreateUserName(loginUser.getNickname());
        basicInfo.setCreateTime(new Date());
        basicInfo.setId(IdUtil.getUUId());
        setValueUtil.setNameById(basicInfo);
        basicInfo.setAreaName(basicInfo.getProvinceName() + basicInfo.getCityName() + basicInfo.getCountyName());
        this.save(basicInfo);
    }

    @Override
    public void updateBasicInfo(BasicInfo basicInfo) {
        //更新前替换掉省市区name
        setValueUtil.setNameById(basicInfo);
        basicInfo.setAreaName(basicInfo.getProvinceName() + basicInfo.getCityName() + basicInfo.getCountyName());
        this.updateById(basicInfo);
    }

    @Override
    public void removeBasicInfo(String id) {
        this.removeById(id);
    }
}
