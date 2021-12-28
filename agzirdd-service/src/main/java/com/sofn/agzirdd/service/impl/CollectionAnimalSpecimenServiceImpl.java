package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.agzirdd.mapper.CollectionAnimalSpecimenMapper;
import com.sofn.agzirdd.model.CollectionAnimalSpecimen;
import com.sofn.agzirdd.model.CollectionPlantSpecimen;
import com.sofn.agzirdd.service.CollectionAnimalSpecimenService;
import com.sofn.agzirdd.sysapi.SysFileApi;
import com.sofn.agzirdd.sysapi.bean.SysFileManageForm;
import com.sofn.agzirdd.sysapi.bean.SysFileManageVo;
import com.sofn.agzirdd.sysapi.bean.SysFileVo;
import com.sofn.agzirdd.vo.CollectionAnimalSpecimenVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 物种采集模块-动物标本采集
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
@Service
public class CollectionAnimalSpecimenServiceImpl extends ServiceImpl<CollectionAnimalSpecimenMapper, CollectionAnimalSpecimen> implements CollectionAnimalSpecimenService {

    /**
     * 物种采集模块-动物标本采集
     */
    @Autowired
    private CollectionAnimalSpecimenMapper collectionAnimalSpecimenMapper;

    /**
     * 文件图片api
     */
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public List<CollectionAnimalSpecimen> getCollectionAnimalSpecimenByQuery(Map<String, Object> params) {
        return collectionAnimalSpecimenMapper.getCollectionAnimalSpecimenByQuery(params);
    }

    @Override
    public CollectionAnimalSpecimen getCollectionAnimalSpecimenBySpecimenCollectionId(String specimenCollectionId) {

        return collectionAnimalSpecimenMapper.getCollectionAnimalSpecimenBySpecimenCollectionId(specimenCollectionId);
    }

    @Override
    public CollectionAnimalSpecimenVo getCollectionAnimalSpecimenVo(String specimenCollectionId) {

        CollectionAnimalSpecimen collectionAnimalSpecimen = collectionAnimalSpecimenMapper.getCollectionAnimalSpecimenBySpecimenCollectionId(specimenCollectionId);
        CollectionAnimalSpecimenVo collectionAnimalSpecimenVo = CollectionAnimalSpecimenVo.getCollectionAnimalSpecimenVo(collectionAnimalSpecimen);
        if(StringUtils.isNotBlank(collectionAnimalSpecimen.getImg())){
            //存在图片
            String picImg = collectionAnimalSpecimen.getImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionAnimalSpecimenVo.setImgUrl(picStr.toString());
        }
        return collectionAnimalSpecimenVo;
    }

    /**
     * 获取图片id所对应的url
     * @param picImg picImg
     * @return StringBuilder
     */
    @NotNull
    private StringBuilder getImgUrl(String picImg) {
        //用于存放图片url地址
        StringBuilder picStr = new StringBuilder("");
        //获取图片信息
        Result<List<SysFileManageVo>> listResult = sysFileApi.batchGetFileInfo(picImg);
        //对获取后的图片信息进行遍历
        listResult.getData().forEach(
                baseDate -> {
                    //放入拼接后的url地址
                    picStr.append(baseDate.getFilePath()).append(",");
                }
        );
        return picStr;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCollectionAnimalSpecimen(CollectionAnimalSpecimen collectionAnimalSpecimen) {

        //上传图片处理
        //判断是否存在图片
        if(StringUtils.isNotBlank(collectionAnimalSpecimen.getImg())){
            //存在图片
            String picImg = collectionAnimalSpecimen.getImg();
            //激活图片相关业务
            checkImg(picImg);
        }

        collectionAnimalSpecimen.setId(IdUtil.getUUId());
        collectionAnimalSpecimen.setCreateTime(new Date());
        this.save(collectionAnimalSpecimen);
    }

    /**
     * 激活图片相关业务
     * @param picImg picImg
     */
    private void checkImg(String picImg) {
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirdd");
        sysFileManageForm.setRemark("采集-动物图片");
        sysFileManageForm.setIds(picImg);
        //调用文件激活接口激活相关图片文件
        Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);
        //激活后相关文件list
        List<SysFileVo> fileOnData = listResult.getData();
        //未激活文件图片id集合
        List<String> idsByOff = IdUtil.getIdsByStr(picImg);
        //已激活文件图片id集合
        List<String> idsByOn = fileOnData.stream().map(SysFileVo::getId).collect(Collectors.toList());
        if (idsByOff.size() != idsByOn.size()) {
            String strIdsByList = IdUtil.getStrIdsByList(idsByOn);
            sysFileApi.batchDeleteFile(strIdsByList);
            throw new SofnException("图片上传激活失败,请重新上传!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCollectionAnimalSpecimen(CollectionAnimalSpecimen collectionAnimalSpecimen) {

        //获取未修改前的数据
        CollectionAnimalSpecimen oldCollectionAnimalSpecimen = this.getById(collectionAnimalSpecimen.getId());
        //判断是否存在图片
        String newImg = collectionAnimalSpecimen.getImg();
        if(StringUtils.isNotBlank(oldCollectionAnimalSpecimen.getImg()) && StringUtils.isNotBlank(oldCollectionAnimalSpecimen.getImg())){
            //获取新数据图片String
            String oldImg = oldCollectionAnimalSpecimen.getImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }

        this.updateById(collectionAnimalSpecimen);
    }

    /**
     * 修改图片及相关业务
     * @param newImg newImg
     * @param oldImg oldImg
     */
    private void updatePic(String newImg, String oldImg) {
        //新图片ids集合
        List<String> newPic01 = Lists.newArrayList(IdUtil.getIdsByStr(newImg));
        List<String> newPic02 =  Lists.newArrayList(IdUtil.getIdsByStr(newImg));
        //旧图片ids集合
        List<String> oldPic01 =  Lists.newArrayList(IdUtil.getIdsByStr(oldImg));
        List<String> oldPic02 =  Lists.newArrayList(IdUtil.getIdsByStr(oldImg));

        //去掉新数据中未修改图片id保留新的图片id用于激活
        newPic01.removeAll(oldPic01);
        //判断是否修改图片
        if (newPic01.size() > 0) {
            //去掉旧数据中未修改图片id保留旧数据中修改前的图片id用于删除
            oldPic02.removeAll(newPic02);

            IdUtil.getStrIdsByList(newPic01);
            //激活图片相关业务
            checkImg(IdUtil.getStrIdsByList(newPic01));
            if(oldPic02.size()>0){
                //删除修改前的文件图片
                sysFileApi.batchDeleteFile(IdUtil.getStrIdsByList(oldPic02));
            }
        }else{
            //去掉旧数据中未修改图片id保留旧数据中修改前的图片id用于删除
            oldPic02.removeAll(newPic02);
            if(oldPic02.size()>0){
                //删除修改前的文件图片
                sysFileApi.batchDeleteFile(IdUtil.getStrIdsByList(oldPic02));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeCollectionAnimalSpecimen(String specimenCollectionId) {

        CollectionAnimalSpecimen collectionAnimalSpecimen = collectionAnimalSpecimenMapper.getCollectionAnimalSpecimenBySpecimenCollectionId(specimenCollectionId);
        //判断是否存在图片
        if(StringUtils.isNotBlank(collectionAnimalSpecimen.getImg())){
            //存在图片
            String picImg = collectionAnimalSpecimen.getImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        return collectionAnimalSpecimenMapper.deleteCollectionAnimalSpecimen(specimenCollectionId);
    }
}
