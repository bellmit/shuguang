package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agzirdd.enums.DamageDegreeEnum;
import com.sofn.agzirdd.excelmodel.WarningThresholdExcel;
import com.sofn.agzirdd.mapper.WarningThresholdMapper;
import com.sofn.agzirdd.model.ThresholdValue;
import com.sofn.agzirdd.model.WarningThreshold;
import com.sofn.agzirdd.service.ThresholdValueService;
import com.sofn.agzirdd.service.WarningThresholdService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.vo.WarningThresholdVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WarningThresholdServiceImpl extends ServiceImpl<WarningThresholdMapper, WarningThreshold> implements WarningThresholdService {
    @Autowired
    private WarningThresholdMapper warningThresholdMapper;
    @Autowired
    private ThresholdValueService thresholdValueService;

    @Override
    public PageUtils<WarningThreshold> getWarningThresholdByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<WarningThreshold> warningThresholdList = warningThresholdMapper.getWarningThresholdByCondition(params);
        PageInfo<WarningThreshold> pageInfo = new PageInfo<>(warningThresholdList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addWarningThreshold(User userInfo, WarningThresholdVo warningThresholdVo) {
        List<ThresholdValue> thresholdValueList = warningThresholdVo.getThresholdValueList();
        if(warningThresholdVo.getClassificationName().indexOf("面积")!=-1
                || "造成经济损失".equals(warningThresholdVo.getClassificationName())){
            if(thresholdValueList.isEmpty()){
                throw new SofnException("阈值参数列表不能为空");
            }
        }
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        WarningThreshold warningThreshold = new WarningThreshold();
        BeanUtils.copyProperties(warningThresholdVo, warningThreshold);
        warningThreshold.setCreateTime(new Date());
        warningThreshold.setCreateUserName(userInfo.getUsername());
        warningThreshold.setId(IdUtil.getUUId());
        //判断当前用户所在机构的类型(Y-行政机构,N-代理机构)
        if(orgData.getThirdOrg().equals("Y")) {
            String address = orgData.getAddress();
            List<String> areaList = JsonUtils.json2List(address, String.class);
            warningThreshold.setProvinceId(areaList.get(0));   //将provinceId存入

            Map<String, Object> params = new HashMap<>();
            params.put("classificationId",warningThresholdVo.getClassificationId());
            params.put("speciesId",warningThresholdVo.getSpeciesId());
            params.put("provinceId",areaList.get(0));
            //根据当前省的物种与指标分类查询是否存在预警阈值
            List<WarningThreshold> warningThresholdList = warningThresholdMapper.getWarningThresholdByCondition(params);
            if(!warningThresholdList.isEmpty()){
                throw new SofnException("相应预警阈值已经存在");
            }
        }

        boolean flag = super.saveOrUpdate(warningThreshold);
        //若主表数据保存成功，且添加的是 发生面积/造成经济损失 的阈值，则执行下一步操作
        if(flag&& (warningThresholdVo.getClassificationName().indexOf("面积")!=-1 || "造成经济损失".equals(warningThresholdVo.getClassificationName()))){
            for (ThresholdValue thresholdValue : thresholdValueList){
                thresholdValue.setWtId(warningThreshold.getId());
            }
            thresholdValueService.saveBatch(thresholdValueList);
        }
        return Result.ok("数据添加成功");
    }

    /*
    *@Author Chlf
    *@Description //判断输入阈值值是否有相同等级
    *@Date 17:30 2020/4/22
    *@Param
    *@Return true 是有相同的， false 没有相同等级
    **/
    private boolean hasSameLevel(List<ThresholdValue> thresholdValueList){
        Set<String> valSet = new HashSet<>();
        for (ThresholdValue value :thresholdValueList){
            valSet.add(value.getRiskLevel());
        }
        if(valSet.size()!=thresholdValueList.size()){
            return true;
        }
        return  false;
    }


    @Override
    public WarningThresholdVo getWarningThresholdVoById(String id) {
        WarningThreshold warningThreshold = warningThresholdMapper.selectByPrimaryKey(id);
        WarningThresholdVo vo = new WarningThresholdVo();
        if(null!=warningThreshold){
            BeanUtils.copyProperties(warningThreshold,vo);
            List<ThresholdValue> thresholdValueList = thresholdValueService.selectByWtId(id);
            vo.setThresholdValueList(thresholdValueList);
        }
        return vo;
    }

    @Override
    @Transactional
    public Result deleteWarningThreshold(String id) {
        //先删数据子表,成功与否不影响主表删除
        thresholdValueService.deleteByWtId(id);
        //删除主表
        warningThresholdMapper.deleteByPrimaryKey(id);
        return Result.ok("删除成功");
    }

    @Override
    @Transactional
    public Result updateWarningThreshold(WarningThresholdVo warningThresholdVo) {
        //修改是否有条件限制
        WarningThreshold warningThresholdTemp = warningThresholdMapper.selectByPrimaryKey(warningThresholdVo.getId());
        if(null==warningThresholdTemp){
            throw new SofnException("对应id对象不存在");
        }

        List<ThresholdValue> thresholdValueList = warningThresholdVo.getThresholdValueList();
        if((warningThresholdVo.getClassificationName().indexOf("面积")!=-1 || "造成经济损失".equals(warningThresholdVo.getClassificationName()) )&&thresholdValueList.isEmpty()){
            throw new SofnException("阈值设置的值不能为空");
        }

        for(ThresholdValue value : thresholdValueList){
            value.setWtId(warningThresholdVo.getId());
        }
        //删除原来阈值
        thresholdValueService.deleteByWtId(warningThresholdVo.getId());
        //重新保存
        if(thresholdValueList.isEmpty()==false){
            thresholdValueService.saveBatch(thresholdValueList);
        }
        WarningThreshold warningThreshold = new WarningThreshold();
        BeanUtils.copyProperties(warningThresholdVo,warningThreshold);
        warningThresholdMapper.updateByPrimaryKey(warningThreshold);

        return Result.ok("修改成功");
    }

    @Override
    public List<WarningThresholdExcel> getWTEByCondition(Map<String, Object> params) {
        List<WarningThresholdExcel> list = warningThresholdMapper.getWarningThresholdExcelByCondition(params);
        for(WarningThresholdExcel excel:list){
            //风险等级显示调整
            excel.setRiskLevel(getRiskLevelByNum(excel.getRiskLevel()));
            if("#FF0000".equals(excel.getColor()))
                excel.setColor("红色");
            else
                excel.setColor("绿色");

            //统一修改为 <=
            excel.setCondition1("<=");
            excel.setCondition2("<=");
//            excel.setCondition1(getConditionVal(1,excel.getCondition1()));
//            excel.setCondition2(getConditionVal(2,excel.getCondition2()));
        }
        return list;
    }

    //将风险等级数值改成相应文字
    private String getRiskLevelByNum(String num){
        if(StringUtils.isEmpty(num))
            return DamageDegreeEnum.MILD.getDescription();

        return DamageDegreeEnum.getDescriptionByCode(num);
    }

    //将条件转为判断符号
    private String getConditionVal(int condition, String num){
       if(StringUtils.isEmpty(num)){
           if(condition==1){
               return ">";
           }else {
               return "<";
           }
       }

       if(condition==1){
           switch (num){
               case "0":
                   return ">=";
               case "1":
                   return "<";
               case "2":
                   return "=";
               case "3":
                   return "!=";
           }
       }else {
           switch (num){
               case "0":
                   return ">=";
               case "1":
                   return "<";
               case "2":
                   return "=";
               case "3":
                   return "!=";
           }
       }

       return ">";
    }

}
