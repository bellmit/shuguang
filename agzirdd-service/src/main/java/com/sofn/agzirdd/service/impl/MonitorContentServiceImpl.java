package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.enums.YseOrNoEnum;
import com.sofn.agzirdd.mapper.MonitorContentMapper;
import com.sofn.agzirdd.model.MonitorContent;
import com.sofn.agzirdd.service.MonitorContentService;
import com.sofn.agzirdd.sysapi.SysFileApi;
import com.sofn.agzirdd.sysapi.bean.SysFileManageForm;
import com.sofn.agzirdd.sysapi.bean.SysFileManageVo;
import com.sofn.agzirdd.sysapi.bean.SysFileVo;
import com.sofn.agzirdd.vo.MonitorContentVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 物种监测模块-监测内容ServiceImpl
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Service
public class MonitorContentServiceImpl extends ServiceImpl<MonitorContentMapper, MonitorContent> implements MonitorContentService {

    /**
     * 物种监测模块-监测内容
     */
    @Autowired
    private MonitorContentMapper monitorContentMapper;

    /**
     * 图片文件上传Api
     */
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public MonitorContent getMonitorContentBySpeciesMonitorId(String speciesMonitorId) {

        return monitorContentMapper.getMonitorContentBySpeciesMonitorId(speciesMonitorId);
    }

    @Override
    public MonitorContentVo getMonitorContentVo(String speciesMonitorId) {
        MonitorContent monitorContent = monitorContentMapper.getMonitorContentBySpeciesMonitorId(speciesMonitorId);
        //将po转vo
        MonitorContentVo monitorContentVo = MonitorContentVo.getMonitorContentVo(monitorContent);
        //判断新数据是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(monitorContent.getHasMeasures())) {
            //新数据存在防控措施
            //判断是否防控效果图片
            if (StringUtils.isNotBlank(monitorContent.getResultImg())) {
                //获取新数据图片String
                String picImg = monitorContent.getResultImg();
                //用于存放图片url地址
                //获取图片id所对应的url
                StringBuilder picStr = getImgUrl(picImg);
                monitorContentVo.setResultImgUrl(picStr.toString());
            }
        }
        //判断是否对其利用
        if (YseOrNoEnum.YES.getCode().equals(monitorContent.getHasUtilize())) {
            //存在其利用
            //判断是否存在利用图片
            if (StringUtils.isNotBlank(monitorContent.getUtilizeImg())) {
                //获取新数据图片String
                String picImg = monitorContent.getUtilizeImg();
                //用于存放图片url地址
                //获取图片id所对应的url
                StringBuilder picStr = getImgUrl(picImg);
                monitorContentVo.setUtilizeImgUrl(picStr.toString());
            }
        }
        //判断是否存在工作图片
        if (StringUtils.isNotBlank(monitorContent.getWorkImg())) {
            //存在工作图片
            //获取新数据图片String
            String picImg = monitorContent.getWorkImg();
            //用于存放图片url地址
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            monitorContentVo.setWorkImgUrl(picStr.toString());
        }
        //判断是否存在物种图片
        if (StringUtils.isNotBlank(monitorContent.getSpeciesImg())) {
            //存在物种图片
            //获取新数据图片String
            String picImg = monitorContent.getSpeciesImg();
            //获取图片id所对应的url
            StringBuilder picStr = getImgUrl(picImg);
            monitorContentVo.setSpeciesImgUrl(picStr.toString());
        }
        return monitorContentVo;
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
    public void addMonitorContent(MonitorContent monitorContent) {

        //上传图片处理
        //判断是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(monitorContent.getHasMeasures())){
            //存在防控措施
            //判断是否防控效果图片
            if(StringUtils.isNotBlank(monitorContent.getResultImg())){
                String picImg = monitorContent.getResultImg();
                //激活图片相关业务
                checkImg(picImg);
            }
        }
        //判断是否对其利用
        if(YseOrNoEnum.YES.getCode().equals(monitorContent.getHasUtilize())){
            //存在其利用
            //判断是否存在利用图片
            if(StringUtils.isNotBlank(monitorContent.getUtilizeImg())){
                String picImg = monitorContent.getUtilizeImg();
                //激活图片相关业务
                checkImg(picImg);
            }
        }
        //判断是否存在工作图片
        if(StringUtils.isNotBlank(monitorContent.getWorkImg())){
            //存在工作图片
            String picImg = monitorContent.getWorkImg();
            //激活图片相关业务
            checkImg(picImg);
        }
        //判断是否存在物种图片
        if(StringUtils.isNotBlank(monitorContent.getSpeciesImg())){
            //存在物种图片
            String picImg = monitorContent.getSpeciesImg();
            //激活图片相关业务
            checkImg(picImg);
        }

        //放入id,创建时间
        monitorContent.setId(IdUtil.getUUId());
        monitorContent.setCreateTime(new Date());
        this.save(monitorContent);
    }

    /**
     * 激活图片相关业务
     * @param picImg picImg
     */
    private void checkImg(String picImg) {
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirdd");
        sysFileManageForm.setRemark("监测图片");
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
    public void updateMonitorContent(MonitorContent monitorContent) {
        //获取未修改前的监测内容
        MonitorContent oldMonitorContent = this.getById(monitorContent.getId());
        //上传图片处理
        //判断新数据是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(monitorContent.getHasMeasures())){
            //新数据存在防控措施
            //判断是否防控效果图片
            if(StringUtils.isNotBlank(oldMonitorContent.getResultImg()) && StringUtils.isNotBlank(monitorContent.getResultImg())){
                //获取新数据图片String
                String newImg = monitorContent.getResultImg();
                String oldImg = oldMonitorContent.getResultImg();
                //修改图片及相关业务
                updatePic(newImg, oldImg);
            }else{
                //获取新数据图片String
                String newImg = monitorContent.getResultImg();
                if(StringUtils.isNotBlank(newImg)){
                    //激活图片相关业务
                    checkImg(newImg);
                }
            }
        }

        //判断是否对其利用
        if(YseOrNoEnum.YES.getCode().equals(monitorContent.getHasUtilize())){
            //存在其利用
            //判断是否存在利用图片
            if(StringUtils.isNotBlank(oldMonitorContent.getUtilizeImg()) && StringUtils.isNotBlank(monitorContent.getUtilizeImg())){
                //获取新数据图片String
                String newImg = monitorContent.getUtilizeImg();
                String oldImg = oldMonitorContent.getUtilizeImg();
                //修改图片及相关业务
                updatePic(newImg, oldImg);
            }else{
                //获取新数据图片String
                String newImg = monitorContent.getUtilizeImg();
                if(StringUtils.isNotBlank(newImg)){
                    //激活图片相关业务
                    checkImg(newImg);
                }
            }
        }
        //判断是否存在工作图片
        if(StringUtils.isNotBlank(oldMonitorContent.getWorkImg()) && StringUtils.isNotBlank(monitorContent.getWorkImg())){
            //存在工作图片
            //获取新数据图片String
            String newImg = monitorContent.getWorkImg();
            String oldImg = oldMonitorContent.getWorkImg();

            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = monitorContent.getWorkImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }
        //判断是否存在物种图片
        if(StringUtils.isNotBlank(oldMonitorContent.getSpeciesImg()) && StringUtils.isNotBlank(monitorContent.getSpeciesImg())){
            //存在物种图片
            //获取新数据图片String
            String newImg = monitorContent.getSpeciesImg();
            String oldImg = oldMonitorContent.getSpeciesImg();
            //修改图片及相关业务
            updatePic(newImg, oldImg);
        }else{
            //获取新数据图片String
            String newImg = monitorContent.getSpeciesImg();
            if(StringUtils.isNotBlank(newImg)){
                //激活图片相关业务
                checkImg(newImg);
            }
        }

        this.updateById(monitorContent);
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
    public boolean removeMonitorContent(String speciesMonitorId) {
        //获取需要删除的相关数据
        MonitorContent monitorContent = monitorContentMapper.getMonitorContentBySpeciesMonitorId(speciesMonitorId);
        //判断新数据是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(monitorContent.getHasMeasures())) {
            //新数据存在防控措施
            //判断是否防控效果图片
            if (StringUtils.isNotBlank(monitorContent.getResultImg())) {
                //获取新数据图片String
                //删除文件图片
                sysFileApi.batchDeleteFile(monitorContent.getResultImg());
            }
        }
        //判断是否对其利用
        if (YseOrNoEnum.YES.getCode().equals(monitorContent.getHasUtilize())) {
            //存在其利用
            //判断是否存在利用图片
            if (StringUtils.isNotBlank(monitorContent.getUtilizeImg())) {
                //获取新数据图片String
                //删除文件图片
                sysFileApi.batchDeleteFile(monitorContent.getUtilizeImg());
            }
        }
        //判断是否存在工作图片
        if (StringUtils.isNotBlank(monitorContent.getWorkImg())) {
            //存在工作图片
            //删除文件图片
            sysFileApi.batchDeleteFile(monitorContent.getWorkImg());
        }
        //判断是否存在物种图片
        if (StringUtils.isNotBlank(monitorContent.getSpeciesImg())) {
            //存在物种图片
            //删除文件图片
            sysFileApi.batchDeleteFile(monitorContent.getSpeciesImg());
        }

        return monitorContentMapper.deleteMonitorContent(speciesMonitorId);
    }
}
