package com.sofn.agzirdd.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.CFTJEnum;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.enums.TypeEnum;
import com.sofn.agzirdd.excelmodel.CollectionAnimalSpecimenExcel;
import com.sofn.agzirdd.excelmodel.CollectionMicrobeSpecimenExcel;
import com.sofn.agzirdd.excelmodel.CollectionPlantSpecimenExcel;
import com.sofn.agzirdd.mapper.SpecimenCollectionMapper;
import com.sofn.agzirdd.model.*;
import com.sofn.agzirdd.service.*;
import com.sofn.agzirdd.sysapi.SysDropDownApi;
import com.sofn.agzirdd.sysapi.bean.DropDownVo;
import com.sofn.agzirdd.util.SetValueUtil;
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
 * @Description: 物种采集模块-标本采集基本信息
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
@Service
public class SpecimenCollectionServiceImpl extends ServiceImpl<SpecimenCollectionMapper, SpecimenCollection> implements SpecimenCollectionService {

    /**
     * 物种采集模块-标本采集基本信息
     */
    @Autowired
    private SpecimenCollectionMapper specimenCollectionMapper;

    /**
     * 物种采集模块-植物标本采集
     */
    @Autowired
    private CollectionPlantSpecimenService collectionPlantSpecimenService;

    /**
     * 物种采集模块-动物标本采集
     */
    @Autowired
    private CollectionAnimalSpecimenService collectionAnimalSpecimenService;

    /**
     * 物种采集模块-微生物标本
     */
    @Autowired
    private CollectionMicrobeSpecimenService collectionMicrobeSpecimenService;

    /**
     * 物种采集模块-审核记录
     */
    @Autowired
    private CollectionExaminaRecordService collectionExaminaRecordService;

    /**
     * 区域行政接口
     */
    @Resource
    private SetValueUtil setValueUtil;
    /**
     * 获取植被类型接口
     */
    @Autowired
    private SysDropDownApi sysDropDownApi;

    @Override
    public PageUtils<SpecimenCollection> getSpecimenCollectionByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SpecimenCollection> specimenCollectionList =
                specimenCollectionMapper.getSpecimenCollectionByCondition(params);
        PageInfo<SpecimenCollection> pageInfo = new PageInfo<>(specimenCollectionList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SpecimenCollection> getSpecimenCollectionListByParam(Map<String, Object> params) {
        List<SpecimenCollection> resList = specimenCollectionMapper.getSpecimenCollectionByCondition(params);
        return resList;
    }

    @Override
    public PageUtils<SpecimenCollectionForm> getSpecimenCollectionForm(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<SpecimenCollectionForm> specimenCollectionForm = specimenCollectionMapper.getSpecimenCollectionForm(params);
        specimenCollectionForm.forEach(
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
        PageInfo<SpecimenCollectionForm> pageInfo = new PageInfo<>(specimenCollectionForm);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<SpecimenCollectionForm> getSpecimenCollectionList(Map<String, Object> params) {

        List<SpecimenCollectionForm> specimenCollectionList =
                specimenCollectionMapper.getSpecimenCollectionForm(params);
        specimenCollectionList.forEach(
                baseData ->{
                    //根据区县id获取上级相关所有地址
                    if(StringUtils.isNotBlank(baseData.getCountyId())){
                        //拼接市区县详细地址
                        String areaName = baseData.getProvinceName() + baseData.getCityName() + baseData.getCountyName();
                        baseData.setAreaName(areaName);
                    }else{
                        baseData.setAreaName("---");
                    }

                    //将是采集标本类型枚举转换为对应参数
                    String description = TypeEnum.getDescriptionByCode(baseData.getType());
                    baseData.setTypeName(description);
                }
        );
        return specimenCollectionList;
    }

    @Override
    public SpecimenCollection getSpecimenCollectionById(String id) {
        return specimenCollectionMapper.getSpecimenCollectionById(id);
    }

    @Override
    public SpecimenCollectionVo getSpecimenCollectionAllById(String id) {
        //获取物种入侵-采集基本信息
        SpecimenCollection specimenCollection = specimenCollectionMapper.getSpecimenCollectionById(id);
        //将po转换为vo
        SpecimenCollectionVo specimenCollectionVo = SpecimenCollectionVo.getSpecimenCollectionVo(specimenCollection);
        //将省市县id转换为省市县名称
        if(StringUtils.isNotBlank(specimenCollectionVo.getCountyId())){
            //拼接市区县详细地址
            String areaName = specimenCollectionVo.getProvinceName() + "/" + specimenCollectionVo.getCityName() + "/" + specimenCollectionVo.getCountyName();
            specimenCollectionVo.setAreaName(areaName);
        }else{
            specimenCollectionVo.setAreaName("---");
        }
        //判断采集标本类型:采集标本类型(0-植物,1-动物,2-微生物)
        if(TypeEnum.PLANT.getCode().equals(specimenCollection.getType())){
            //植物标本
            CollectionPlantSpecimenVo collectionPlantSpecimenVo =
                    collectionPlantSpecimenService.getCollectionPlantSpecimenVo(id);
            specimenCollectionVo.setCollectionPlantSpecimenVo(collectionPlantSpecimenVo);
        }
        if(TypeEnum.ANIMAL.getCode().equals(specimenCollection.getType())){
            //动物标本
            CollectionAnimalSpecimenVo collectionAnimalSpecimenVo =
                    collectionAnimalSpecimenService.getCollectionAnimalSpecimenVo(id);
            specimenCollectionVo.setCollectionAnimalSpecimenVo(collectionAnimalSpecimenVo);
        }
        if(TypeEnum.MICROBE.getCode().equals(specimenCollection.getType())){
            //微生物标本
            CollectionMicrobeSpecimenVo collectionMicrobeSpecimenVo =
                    collectionMicrobeSpecimenService.getCollectionMicrobeSpecimenVo(id);
            specimenCollectionVo.setCollectionMicrobeSpecimenVo(collectionMicrobeSpecimenVo);
        }
        //获取审核记录->先判断是否存在数据.存在即放入
        Map<String, Object> params = Maps.newHashMap();
        params.put("specimenCollectionId",id);
        List<CollectionExaminaRecord> collectionExaminaRecordList =
                collectionExaminaRecordService.getCollectionExaminaRecordByCondition(params);
        if(collectionExaminaRecordList.size()>0){
            specimenCollectionVo.setCollectionExaminaRecordList(collectionExaminaRecordList);
        }
        return specimenCollectionVo;
    }

    @Override
    public CollectionPlantSpecimenExcel getCollectionPlantSpecimenExcel(String id) {
        //获取采集基础信息数据
        SpecimenCollection specimenCollection = specimenCollectionMapper.getSpecimenCollectionById(id);
        CollectionPlantSpecimenExcel collectionPlantSpecimenExcel = new CollectionPlantSpecimenExcel();
        Result<List<DropDownVo>> dropDownList =  sysDropDownApi.listForVegetationType();
        List<DropDownVo> vos = new ArrayList<>();
        if(dropDownList.getCode()==200){
            vos = dropDownList.getData();
        }
        if(StringUtils.isNotBlank(specimenCollection.getCountyId())){
            //拼接市区县详细地址
            String areaName = specimenCollection.getProvinceName() + specimenCollection.getCityName() + specimenCollection.getCountyName() + specimenCollection.getTown();
            collectionPlantSpecimenExcel.setAreaName(areaName);
        }else{
            collectionPlantSpecimenExcel.setAreaName("---");
        }

        BeanUtils.copyProperties(specimenCollection,collectionPlantSpecimenExcel);
        CollectionPlantSpecimenVo collectionPlantSpecimenVo = collectionPlantSpecimenService.getCollectionPlantSpecimenVo(id);
        BeanUtils.copyProperties(collectionPlantSpecimenVo,collectionPlantSpecimenExcel);
        //将植被ID转化为植被名字  chlf 2020-09-10修改
        collectionPlantSpecimenExcel.setTrait(getVegetationNameByIds(vos,collectionPlantSpecimenVo.getTrait()));

        //存放条件代码转换中文
        collectionPlantSpecimenExcel.setCftj(CFTJEnum.getDescriptionByCode(collectionPlantSpecimenExcel.getCftj()));

        return collectionPlantSpecimenExcel;
    }

    //根据植被类型ID获取植被名称
    private String getVegetationNameByIds(List<DropDownVo> vos, String ids){
        if(vos.isEmpty()) return "";
        if(StringUtils.isEmpty(ids)) return "";
        List<String> idList = Arrays.asList(ids.split(","));
        vos = vos.stream().filter(s->idList.contains(s.getId())).collect(Collectors.toList());
        StringBuffer nameBuffer = new StringBuffer();
        if(vos.isEmpty()==false){
            for (DropDownVo vo : vos){
                nameBuffer.append(vo.getName()).append(",");
            }
        }

        return nameBuffer.length()>0?nameBuffer.substring(0,nameBuffer.length()-1):"";
    }


    @Override
    public CollectionAnimalSpecimenExcel getCollectionAnimalSpecimenExcel(String id) {
        //获取采集基础信息数据
        SpecimenCollection specimenCollection = specimenCollectionMapper.getSpecimenCollectionById(id);
        CollectionAnimalSpecimenExcel collectionAnimalSpecimenExcel = new CollectionAnimalSpecimenExcel();
        if(StringUtils.isNotBlank(specimenCollection.getCountyId())){
            //拼接市区县详细地址
            String areaName = specimenCollection.getProvinceName() + specimenCollection.getCityName() + specimenCollection.getCountyName() + specimenCollection.getTown();
            collectionAnimalSpecimenExcel.setAreaName(areaName);
        }else{
            collectionAnimalSpecimenExcel.setAreaName("---");
        }

        BeanUtils.copyProperties(specimenCollection,collectionAnimalSpecimenExcel);
        CollectionAnimalSpecimenVo collectionAnimalSpecimenVo = collectionAnimalSpecimenService.getCollectionAnimalSpecimenVo(id);
        BeanUtils.copyProperties(collectionAnimalSpecimenVo,collectionAnimalSpecimenExcel);

        //存放条件代码转换中文
        collectionAnimalSpecimenExcel.setCftj(CFTJEnum.getDescriptionByCode(collectionAnimalSpecimenExcel.getCftj()));
        return collectionAnimalSpecimenExcel;
    }

    @Override
    public CollectionMicrobeSpecimenExcel getCollectionMicrobeSpecimenExcel(String id) {
        //获取采集基础信息数据
        SpecimenCollection specimenCollection = specimenCollectionMapper.getSpecimenCollectionById(id);
        CollectionMicrobeSpecimenExcel collectionMicrobeSpecimenExcel = new CollectionMicrobeSpecimenExcel();
        if(StringUtils.isNotBlank(specimenCollection.getCountyId())){
            //拼接市区县详细地址
            String areaName = specimenCollection.getProvinceName() + specimenCollection.getCityName() + specimenCollection.getCountyName() + specimenCollection.getTown();
            collectionMicrobeSpecimenExcel.setAreaName(areaName);
        }else{
            collectionMicrobeSpecimenExcel.setAreaName("---");
        }


        BeanUtils.copyProperties(specimenCollection,collectionMicrobeSpecimenExcel);
        CollectionMicrobeSpecimenVo collectionMicrobeSpecimenVo = collectionMicrobeSpecimenService.getCollectionMicrobeSpecimenVo(id);

        BeanUtils.copyProperties(collectionMicrobeSpecimenVo,collectionMicrobeSpecimenExcel);

        //存放条件代码转换中文
        collectionMicrobeSpecimenExcel.setCftj(CFTJEnum.getDescriptionByCode(collectionMicrobeSpecimenExcel.getCftj()));
        return collectionMicrobeSpecimenExcel;
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
            CollectionExaminaRecord collectionExaminaRecord = new CollectionExaminaRecord();
            collectionExaminaRecord.setId(IdUtil.getUUId());
            collectionExaminaRecord.setSpecimenCollectionId(id);
            collectionExaminaRecord.setStatus(status);
            User loginUser = UserUtil.getLoginUser();
            collectionExaminaRecord.setAuditor(loginUser.getNickname());
            collectionExaminaRecord.setOpinion(remark);
            collectionExaminaRecord.setCreateTime(new Date());
            collectionExaminaRecordService.addCollectionExaminaRecord(collectionExaminaRecord);

        }
        specimenCollectionMapper.updateStatus(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSpecimenCollection(SpecimenCollectionVo specimenCollectionVo) {
        //将vo装换位po
        SpecimenCollection specimenCollection = SpecimenCollectionVo.getSpecimenCollection(specimenCollectionVo);
        Map<String, Object> odlParams = Maps.newHashMap();
        odlParams.put("collectNumber",specimenCollection.getCollectNumber());
        List<SpecimenCollection> oldList = specimenCollectionMapper.getSpecimenCollectionByParams(odlParams);
        if(oldList.size()>0){
            throw new SofnException("当前采集编号已存在");
        }
        //放入id,创建人,创建人id,创建时间.当前年份
        specimenCollection.setId(IdUtil.getUUId());
        User loginUser = UserUtil.getLoginUser();
        specimenCollection.setCreateUserId(loginUser.getId());
        specimenCollection.setCreateUserName(loginUser.getNickname());
        specimenCollection.setCreateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(specimenCollection.getCollectTime());
        String belongYear = calendar.get(Calendar.YEAR) + "";
        specimenCollection.setBelongYear(belongYear);
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //判断当前用户是否为县级填报
        if(roleColde.contains("agzirdd_county")){
            specimenCollection.setRoleCode("agzirdd_county");
        }
        //是否为专家填报
        if(roleColde.contains("agzirdd_expert")){
            specimenCollection.setRoleCode("agzirdd_expert");
        }
        //判断采集标本类型:采集标本类型(0-植物,1-动物,2-微生物)
        if(TypeEnum.PLANT.getCode().equals(specimenCollection.getType()) && specimenCollectionVo.getCollectionPlantSpecimenVo()!=null){
            //植物标本
            CollectionPlantSpecimen collectionPlantSpecimen = specimenCollectionVo.getCollectionPlantSpecimenVo();
            collectionPlantSpecimen.setSpecimenCollectionId(specimenCollection.getId());
            collectionPlantSpecimenService.addCollectionPlantSpecimen(collectionPlantSpecimen);

        }
        if(TypeEnum.ANIMAL.getCode().equals(specimenCollection.getType()) && specimenCollectionVo.getCollectionAnimalSpecimenVo()!=null){
            //动物标本
            CollectionAnimalSpecimen collectionAnimalSpecimen = specimenCollectionVo.getCollectionAnimalSpecimenVo();
            collectionAnimalSpecimen.setSpecimenCollectionId(specimenCollection.getId());
            collectionAnimalSpecimenService.addCollectionAnimalSpecimen(collectionAnimalSpecimen);

        }
        if(TypeEnum.MICROBE.getCode().equals(specimenCollection.getType()) && specimenCollectionVo.getCollectionMicrobeSpecimenVo()!=null){
            //微生物标本
            CollectionMicrobeSpecimen collectionMicrobeSpecimen = specimenCollectionVo.getCollectionMicrobeSpecimenVo();
            collectionMicrobeSpecimen.setSpecimenCollectionId(specimenCollection.getId());
            collectionMicrobeSpecimenService.addCollectionMicrobeSpecimen(collectionMicrobeSpecimen);

        }
        this.save((SpecimenCollection) setValueUtil.setNameById(specimenCollection));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpecimenCollection(SpecimenCollectionVo specimenCollectionVo) {
        //监测年度
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(specimenCollectionVo.getCollectTime());
        String belongYear = calendar.get(Calendar.YEAR) + "";
        specimenCollectionVo.setBelongYear(belongYear);

        //将vo转换为po
        SpecimenCollection specimenCollection = SpecimenCollectionVo.getSpecimenCollection(specimenCollectionVo);

        //判断采集标本类型:采集标本类型(0-植物,1-动物,2-微生物)
        if(TypeEnum.PLANT.getCode().equals(specimenCollection.getType()) && specimenCollectionVo.getCollectionPlantSpecimenVo()!=null){
            //植物标本
            CollectionPlantSpecimen collectionPlantSpecimen = specimenCollectionVo.getCollectionPlantSpecimenVo();

            collectionPlantSpecimenService.updateCollectionPlantSpecimen(collectionPlantSpecimen);
        }
        if(TypeEnum.ANIMAL.getCode().equals(specimenCollection.getType()) && specimenCollectionVo.getCollectionAnimalSpecimenVo()!=null){
            //动物标本
            CollectionAnimalSpecimen collectionAnimalSpecimen = specimenCollectionVo.getCollectionAnimalSpecimenVo();

            //激活图片->暂时未动

            collectionAnimalSpecimenService.updateCollectionAnimalSpecimen(collectionAnimalSpecimen);
        }
        if(TypeEnum.MICROBE.getCode().equals(specimenCollection.getType()) && specimenCollectionVo.getCollectionMicrobeSpecimenVo()!=null){
            //微生物标本
            CollectionMicrobeSpecimen collectionMicrobeSpecimen = specimenCollectionVo.getCollectionMicrobeSpecimenVo();

            //激活图片->暂时未动

            collectionMicrobeSpecimenService.updateCollectionMicrobeSpecimen(collectionMicrobeSpecimen);
        }
        setValueUtil.setNameById(specimenCollection);
        this.updateById(specimenCollection);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSpecimenCollection(String id) {

        SpecimenCollection specimenCollection = specimenCollectionMapper.getSpecimenCollectionById(id);
        //判断采集标本类型:采集标本类型(0-植物,1-动物,2-微生物)
        boolean deleteType = true;
        if(TypeEnum.PLANT.getCode().equals(specimenCollection.getType())){
            //植物标本
            deleteType = collectionPlantSpecimenService.removeCollectionPlantSpecimen(id);
        }
        if(TypeEnum.ANIMAL.getCode().equals(specimenCollection.getType())){
            //动物标本
            deleteType = collectionAnimalSpecimenService.removeCollectionAnimalSpecimen(id);
        }
        if(TypeEnum.MICROBE.getCode().equals(specimenCollection.getType())){
            //微生物标本
            deleteType = collectionMicrobeSpecimenService.removeCollectionMicrobeSpecimen(id);
        }
        //删除审核记录->先判断是否存在数据.存在即删除
        boolean cer = true ;
        Map<String, Object> params = Maps.newHashMap();
        params.put("specimenCollectionId",id);
        List<CollectionExaminaRecord> collectionExaminaRecordList =
                collectionExaminaRecordService.getCollectionExaminaRecordByCondition(params);
        if(collectionExaminaRecordList.size()>0){
            cer = collectionExaminaRecordService.removeCollectionExaminaRecord(id);
        }
        if(!deleteType || !cer){
            throw new SofnException("删除异常,请重新操作!");
        }
        //删除采集基本信息
        this.removeById(id);
    }
}
