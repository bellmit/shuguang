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
        if(warningThresholdVo.getClassificationName().indexOf("??????")!=-1
                || "??????????????????".equals(warningThresholdVo.getClassificationName())){
            if(thresholdValueList.isEmpty()){
                throw new SofnException("??????????????????????????????");
            }
        }
        //??????????????????????????????????????????
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        WarningThreshold warningThreshold = new WarningThreshold();
        BeanUtils.copyProperties(warningThresholdVo, warningThreshold);
        warningThreshold.setCreateTime(new Date());
        warningThreshold.setCreateUserName(userInfo.getUsername());
        warningThreshold.setId(IdUtil.getUUId());
        //???????????????????????????????????????(Y-????????????,N-????????????)
        if(orgData.getThirdOrg().equals("Y")) {
            String address = orgData.getAddress();
            List<String> areaList = JsonUtils.json2List(address, String.class);
            warningThreshold.setProvinceId(areaList.get(0));   //???provinceId??????

            Map<String, Object> params = new HashMap<>();
            params.put("classificationId",warningThresholdVo.getClassificationId());
            params.put("speciesId",warningThresholdVo.getSpeciesId());
            params.put("provinceId",areaList.get(0));
            //?????????????????????????????????????????????????????????????????????
            List<WarningThreshold> warningThresholdList = warningThresholdMapper.getWarningThresholdByCondition(params);
            if(!warningThresholdList.isEmpty()){
                throw new SofnException("??????????????????????????????");
            }
        }

        boolean flag = super.saveOrUpdate(warningThreshold);
        //????????????????????????????????????????????? ????????????/?????????????????? ????????????????????????????????????
        if(flag&& (warningThresholdVo.getClassificationName().indexOf("??????")!=-1 || "??????????????????".equals(warningThresholdVo.getClassificationName()))){
            for (ThresholdValue thresholdValue : thresholdValueList){
                thresholdValue.setWtId(warningThreshold.getId());
            }
            thresholdValueService.saveBatch(thresholdValueList);
        }
        return Result.ok("??????????????????");
    }

    /*
    *@Author Chlf
    *@Description //??????????????????????????????????????????
    *@Date 17:30 2020/4/22
    *@Param
    *@Return true ?????????????????? false ??????????????????
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
        //??????????????????,?????????????????????????????????
        thresholdValueService.deleteByWtId(id);
        //????????????
        warningThresholdMapper.deleteByPrimaryKey(id);
        return Result.ok("????????????");
    }

    @Override
    @Transactional
    public Result updateWarningThreshold(WarningThresholdVo warningThresholdVo) {
        //???????????????????????????
        WarningThreshold warningThresholdTemp = warningThresholdMapper.selectByPrimaryKey(warningThresholdVo.getId());
        if(null==warningThresholdTemp){
            throw new SofnException("??????id???????????????");
        }

        List<ThresholdValue> thresholdValueList = warningThresholdVo.getThresholdValueList();
        if((warningThresholdVo.getClassificationName().indexOf("??????")!=-1 || "??????????????????".equals(warningThresholdVo.getClassificationName()) )&&thresholdValueList.isEmpty()){
            throw new SofnException("??????????????????????????????");
        }

        for(ThresholdValue value : thresholdValueList){
            value.setWtId(warningThresholdVo.getId());
        }
        //??????????????????
        thresholdValueService.deleteByWtId(warningThresholdVo.getId());
        //????????????
        if(thresholdValueList.isEmpty()==false){
            thresholdValueService.saveBatch(thresholdValueList);
        }
        WarningThreshold warningThreshold = new WarningThreshold();
        BeanUtils.copyProperties(warningThresholdVo,warningThreshold);
        warningThresholdMapper.updateByPrimaryKey(warningThreshold);

        return Result.ok("????????????");
    }

    @Override
    public List<WarningThresholdExcel> getWTEByCondition(Map<String, Object> params) {
        List<WarningThresholdExcel> list = warningThresholdMapper.getWarningThresholdExcelByCondition(params);
        for(WarningThresholdExcel excel:list){
            //????????????????????????
            excel.setRiskLevel(getRiskLevelByNum(excel.getRiskLevel()));
            if("#FF0000".equals(excel.getColor()))
                excel.setColor("??????");
            else
                excel.setColor("??????");

            //??????????????? <=
            excel.setCondition1("<=");
            excel.setCondition2("<=");
//            excel.setCondition1(getConditionVal(1,excel.getCondition1()));
//            excel.setCondition2(getConditionVal(2,excel.getCondition2()));
        }
        return list;
    }

    //???????????????????????????????????????
    private String getRiskLevelByNum(String num){
        if(StringUtils.isEmpty(num))
            return DamageDegreeEnum.MILD.getDescription();

        return DamageDegreeEnum.getDescriptionByCode(num);
    }

    //???????????????????????????
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
