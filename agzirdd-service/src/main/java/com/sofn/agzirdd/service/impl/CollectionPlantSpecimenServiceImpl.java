package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.agzirdd.mapper.CollectionPlantSpecimenMapper;
import com.sofn.agzirdd.model.CollectionPlantSpecimen;
import com.sofn.agzirdd.service.CollectionPlantSpecimenService;
import com.sofn.agzirdd.sysapi.SysFileApi;
import com.sofn.agzirdd.sysapi.bean.SysFileManageForm;
import com.sofn.agzirdd.sysapi.bean.SysFileManageVo;
import com.sofn.agzirdd.sysapi.bean.SysFileVo;
import com.sofn.agzirdd.vo.CollectionPlantSpecimenVo;
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
 * @Description: 物种采集模块-植物标本采集
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
@Service
public class CollectionPlantSpecimenServiceImpl extends ServiceImpl<CollectionPlantSpecimenMapper, CollectionPlantSpecimen> implements CollectionPlantSpecimenService {

    /**
     * 物种采集模块-植物标本采集
     */
    @Autowired
    private CollectionPlantSpecimenMapper collectionPlantSpecimenMapper;

    /**
     * 文件图片api
     */
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public List<CollectionPlantSpecimen> getCollectionPlantSpecimenByQuery(Map<String, Object> params) {
        return collectionPlantSpecimenMapper.getCollectionPlantSpecimenByQuery(params);
    }

    @Override
    public CollectionPlantSpecimen getCollectionPlantSpecimenBySpecimenCollectionId(String specimenCollectionId) {
        return collectionPlantSpecimenMapper.getCollectionPlantSpecimenBySpecimenCollectionId(specimenCollectionId);
    }

    @Override
    public CollectionPlantSpecimenVo getCollectionPlantSpecimenVo(String specimenCollectionId) {
        CollectionPlantSpecimen collectionPlantSpecimen = collectionPlantSpecimenMapper.getCollectionPlantSpecimenBySpecimenCollectionId(specimenCollectionId);
        //将po转换为vo
        CollectionPlantSpecimenVo collectionPlantSpecimenVo = CollectionPlantSpecimenVo.getCollectionPlantSpecimenVo(collectionPlantSpecimen);
        /*if(StringUtils.isNotBlank(collectionPlantSpecimen.getBarkImg())){
            //存在树皮图片
            String picImg = collectionPlantSpecimen.getBarkImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setBarkImgUrl(picStr.toString());
        }*/
        //判断是否存在根图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getRootImg())){
            //存在图片
            String picImg = collectionPlantSpecimen.getRootImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setRootImgUrl(picStr.toString());
        }
        //判断是否存在茎图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getStemImg())){
            //存在茎图片
            String picImg = collectionPlantSpecimen.getStemImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setStemImgUrl(picStr.toString());
        }
        //判断是否存在叶图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getLeafImg())){
            //存在叶图片
            String picImg = collectionPlantSpecimen.getLeafImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setLeafImgUrl(picStr.toString());
        }
        //判断是否存在花图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getFlowerImg())){
            //存在花图片
            String picImg = collectionPlantSpecimen.getFlowerImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setFlowerImgUrl(picStr.toString());
        }
        //判断是否存在果图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getFruitImg())){
            //存在果图片
            String picImg = collectionPlantSpecimen.getFruitImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setFruitImgUrl(picStr.toString());
        }
        //判断是否存在种子图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getSeedImg())){
            //存在种子图片
            String picImg = collectionPlantSpecimen.getSeedImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            collectionPlantSpecimenVo.setSeedImgUrl(picStr.toString());
        }

        return collectionPlantSpecimenVo;
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
    public void addCollectionPlantSpecimen(CollectionPlantSpecimen collectionPlantSpecimen) {

        //上传图片处理

        //判断是否存在根图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getRootImg())){
            //存在图片
            String picImg = collectionPlantSpecimen.getRootImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        //判断是否存在茎图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getStemImg())){
            //存在茎图片
            String picImg = collectionPlantSpecimen.getStemImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        //判断是否存在叶图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getLeafImg())){
            //存在叶图片
            String picImg = collectionPlantSpecimen.getLeafImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        //判断是否存在花图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getFlowerImg())){
            //存在花图片
            String picImg = collectionPlantSpecimen.getFlowerImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        //判断是否存在果图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getFruitImg())){
            //存在果图片
            String picImg = collectionPlantSpecimen.getFruitImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        //判断是否存在种子图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getSeedImg())){
            //存在种子图片
            String picImg = collectionPlantSpecimen.getSeedImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        collectionPlantSpecimen.setId(IdUtil.getUUId());
        collectionPlantSpecimen.setCreateTime(new Date());
        this.save(collectionPlantSpecimen);
    }

    /**
     * 激活图片相关业务
     * @param picImg picImg
     */
    private void checkImg(String picImg) {
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirdd");
        sysFileManageForm.setRemark("采集-植物图片");
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
    public void updateCollectionPlantSpecimen(CollectionPlantSpecimen collectionPlantSpecimen) {

        //获取未修改前的数据
        CollectionPlantSpecimen oldCollectionPlantSpecimen = this.getById(collectionPlantSpecimen.getId());

        //判断是否存在根图片
        if(StringUtils.isNotBlank(oldCollectionPlantSpecimen.getRootImg()) && StringUtils.isNotBlank(collectionPlantSpecimen.getRootImg())){
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getRootImg();
            String oldImg = oldCollectionPlantSpecimen.getRootImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getRootImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }
        //判断是否存在茎图片
        if(StringUtils.isNotBlank(oldCollectionPlantSpecimen.getStemImg()) && StringUtils.isNotBlank(collectionPlantSpecimen.getStemImg())){
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getStemImg();
            String oldImg = oldCollectionPlantSpecimen.getStemImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getStemImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }
        //判断是否存在叶图片
        if(StringUtils.isNotBlank(oldCollectionPlantSpecimen.getLeafImg()) && StringUtils.isNotBlank(collectionPlantSpecimen.getLeafImg())){
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getLeafImg();
            String oldImg = oldCollectionPlantSpecimen.getLeafImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getLeafImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }
        //判断是否存在花图片
        if(StringUtils.isNotBlank(oldCollectionPlantSpecimen.getFlowerImg()) && StringUtils.isNotBlank(collectionPlantSpecimen.getFlowerImg())){
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getFlowerImg();
            String oldImg = oldCollectionPlantSpecimen.getFlowerImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getFlowerImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }
        //判断是否存在果图片
        if(StringUtils.isNotBlank(oldCollectionPlantSpecimen.getFruitImg()) && StringUtils.isNotBlank(collectionPlantSpecimen.getFruitImg())){
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getFruitImg();
            String oldImg = oldCollectionPlantSpecimen.getFruitImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getFruitImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }
        //判断是否存在种子图片
        if(StringUtils.isNotBlank(oldCollectionPlantSpecimen.getSeedImg()) && StringUtils.isNotBlank(collectionPlantSpecimen.getSeedImg())){
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getSeedImg();
            String oldImg = oldCollectionPlantSpecimen.getSeedImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = collectionPlantSpecimen.getSeedImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }

        this.updateById(collectionPlantSpecimen);
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
    public boolean removeCollectionPlantSpecimen(String specimenCollectionId) {

        CollectionPlantSpecimen collectionPlantSpecimen = collectionPlantSpecimenMapper.getCollectionPlantSpecimenBySpecimenCollectionId(specimenCollectionId);

        //判断是否存在根图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getRootImg())){
            //存在图片
            String picImg = collectionPlantSpecimen.getRootImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        //判断是否存在茎图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getStemImg())){
            //存在茎图片
            String picImg = collectionPlantSpecimen.getStemImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        //判断是否存在叶图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getLeafImg())){
            //存在叶图片
            String picImg = collectionPlantSpecimen.getLeafImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        //判断是否存在花图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getFlowerImg())){
            //存在花图片
            String picImg = collectionPlantSpecimen.getFlowerImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        //判断是否存在果图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getFruitImg())){
            //存在果图片
            String picImg = collectionPlantSpecimen.getFruitImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        //判断是否存在种子图片
        if(StringUtils.isNotBlank(collectionPlantSpecimen.getSeedImg())){
            //存在种子图片
            String picImg = collectionPlantSpecimen.getSeedImg();
            //删除图片信息
            sysFileApi.batchDeleteFile(picImg);
        }
        return collectionPlantSpecimenMapper.deleteCollectionPlantSpecimen(specimenCollectionId);
    }
}
