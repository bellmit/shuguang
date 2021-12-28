package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.agzirdd.enums.YseOrNoEnum;
import com.sofn.agzirdd.mapper.InvestigatContentMapper;
import com.sofn.agzirdd.model.InvestigatContent;
import com.sofn.agzirdd.service.InvestigatContentService;
import com.sofn.agzirdd.sysapi.SysFileApi;
import com.sofn.agzirdd.sysapi.bean.SysFileManageForm;
import com.sofn.agzirdd.sysapi.bean.SysFileManageVo;
import com.sofn.agzirdd.sysapi.bean.SysFileVo;
import com.sofn.agzirdd.vo.InvestigatContentVo;
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
 * @Description: 物种调查模块-调查内容
 * @Author: mcc
 * @Date: 2020\3\13 0013
 */
@Service
public class InvestigatContentServiceImpl extends ServiceImpl<InvestigatContentMapper, InvestigatContent> implements InvestigatContentService {

    /**
     * 物种调查模块-调查内容
     */
    @Autowired
    private InvestigatContentMapper investigatContentMapper;

    /**
     * 文件图片api
     */
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public List<InvestigatContent> getInvestigatContentByQuery(Map<String, Object> params) {
        return investigatContentMapper.getInvestigatContentByQuery(params);
    }

    @Override
    public InvestigatContent getInvestigatContentBySpeciesInvestigationId(String speciesInvestigationId) {

        return investigatContentMapper.getInvestigatContentBySpeciesInvestigationId(speciesInvestigationId);
    }

    @Override
    public InvestigatContentVo getInvestigatContentVo(String speciesInvestigationId) {

        InvestigatContent investigatContent = investigatContentMapper.getInvestigatContentBySpeciesInvestigationId(speciesInvestigationId);
        if(null == investigatContent){
            return new InvestigatContentVo();
        }
        //将po转vo
        InvestigatContentVo investigatContentVo = InvestigatContentVo.getInvestigatContentVo(investigatContent);
        //判断新数据是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getHasMeasures())) {
            //新数据存在防控措施
            //判断是否防控效果图片
            if (StringUtils.isNotBlank(investigatContent.getResultImg())) {
                //获取新数据图片String
                String picImg = investigatContent.getResultImg();
                //获取图片id所对应的url
                StringBuilder picStr = getImgUrl(picImg);
                investigatContentVo.setResultImgUrl(picStr.toString());
            }
        }
        //判断是否对其利用
        if (YseOrNoEnum.YES.getCode().equals(investigatContent.getHasUtilize())) {
            //存在其利用
            //判断是否存在利用图片
            if (StringUtils.isNotBlank(investigatContent.getUtilizeImg())) {
                //获取新数据图片String
                String picImg = investigatContent.getUtilizeImg();
                //用于存放图片url地址
                //获取图片id所对应的url
                StringBuilder picStr = getImgUrl(picImg);
                investigatContentVo.setUtilizeImgUrl(picStr.toString());
            }
        }
        //判断是否新发物种
        if (YseOrNoEnum.YES.getCode().equals(investigatContent.getNewSpecies())) {
            //判断是否存在新发物种图片
            if (StringUtils.isNotBlank(investigatContent.getSpeciesImg())) {
                //获取新数据图片String
                String picImg = investigatContent.getSpeciesImg();
                //用于存放图片url地址
                //获取图片id所对应的url
                StringBuilder picStr = getImgUrl(picImg);
                investigatContentVo.setSpeciesImgUrl(picStr.toString());
            }
        }
        return investigatContentVo;
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
    public void addInvestigatContent(InvestigatContent investigatContent) {

        //上传图片处理
        //判断是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getHasMeasures())){
            //存在防控措施
            //判断是否防控效果图片
            if(StringUtils.isNotBlank(investigatContent.getResultImg())){
                String picImg = investigatContent.getResultImg();
                //激活图片相关业务
                checkImg(picImg);
            }
        }
        //判断是否对其利用
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getHasUtilize())){
            //存在其利用
            //判断是否存在利用图片
            if(StringUtils.isNotBlank(investigatContent.getUtilizeImg())){
                String picImg = investigatContent.getUtilizeImg();
                //激活图片相关业务
                checkImg(picImg);
            }
        }

        //判断是否新发物种
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getNewSpecies())){
            //判断是否新发物种
            if(StringUtils.isNotBlank(investigatContent.getSpeciesImg())){
                String picImg = investigatContent.getSpeciesImg();
                //激活图片相关业务
                checkImg(picImg);

                //investigatContent.setSpeciesImgUrl(getImgUrl(picImg).toString());
            }
        }

        //放入id,创建时间
        investigatContent.setId(IdUtil.getUUId());
        investigatContent.setCreateTime(new Date());
        this.save(investigatContent);
    }

    /**
     * 激活图片相关业务
     * @param picImg picImg
     */
    private void checkImg(String picImg) {
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId("agzirdd");
        sysFileManageForm.setRemark("调查图片");
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
    public void updateInvestigatContent(InvestigatContent investigatContent) {
        //获取未修改前的监测内容
        InvestigatContent oldInvestigatContent = this.getById(investigatContent.getId());
        //上传图片处理
        //判断新数据是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getHasMeasures())){
            //新数据存在防控措施
            //判断是否防控效果图片
            if(StringUtils.isNotBlank(oldInvestigatContent.getResultImg()) && StringUtils.isNotBlank(investigatContent.getResultImg())){
                //获取新数据图片String
                String newImg = investigatContent.getResultImg();
                String oldImg = oldInvestigatContent.getResultImg();
                //修改图片及相关业务
                updatePic(newImg, oldImg);
            }else{
                //获取新数据图片String
                String newImg = investigatContent.getResultImg();
                if(StringUtils.isNotBlank(newImg)){
                    //激活图片相关业务
                    checkImg(newImg);
                }
            }
        }
        //判断是否对其利用
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getHasUtilize())){
            //存在其利用
            //判断是否存在利用图片
            if(StringUtils.isNotBlank(investigatContent.getUtilizeImg())){
                //获取新数据图片String
                String newImg = investigatContent.getUtilizeImg();
                String oldImg = StringUtils.isNotBlank(oldInvestigatContent.getUtilizeImg())?
                        oldInvestigatContent.getUtilizeImg():"";
                //修改图片及相关业务
                updatePic(newImg, oldImg);
            }else{
                //获取新数据图片String
                String newImg = investigatContent.getUtilizeImg();
                if(StringUtils.isNotBlank(newImg)){
                    //激活图片相关业务
                    checkImg(newImg);
                }
            }
        }
        //判断是否新发物种
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getNewSpecies())){
            //判断是否新发物种
            if(StringUtils.isNotBlank(investigatContent.getSpeciesImg())){
                //获取新数据图片String
                String newImg = investigatContent.getSpeciesImg();
                String oldImg = StringUtils.isNotBlank(oldInvestigatContent.getSpeciesImg())?
                        oldInvestigatContent.getSpeciesImg():"";
                //修改图片及相关业务
                updatePic(newImg, oldImg);
            }else{
                //获取新数据图片String
                String newImg = investigatContent.getSpeciesImg();
                if(StringUtils.isNotBlank(newImg)){
                    //激活图片相关业务
                    checkImg(newImg);
                }
            }
        }
        this.updateById(investigatContent);
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
    public boolean removeInvestigatContent(String speciesInvestigationId) {
        //获取需要删除的相关数据
        InvestigatContent investigatContent = investigatContentMapper.getInvestigatContentBySpeciesInvestigationId(speciesInvestigationId);
        //判断新数据是否存在防控措施
        if(YseOrNoEnum.YES.getCode().equals(investigatContent.getHasMeasures())) {
            //新数据存在防控措施
            //判断是否防控效果图片
            if (StringUtils.isNotBlank(investigatContent.getResultImg())) {
                //获取新数据图片String
                //删除文件图片
                sysFileApi.batchDeleteFile(investigatContent.getResultImg());
            }
        }
        //判断是否对其利用
        if (YseOrNoEnum.YES.getCode().equals(investigatContent.getHasUtilize())) {
            //存在其利用
            //判断是否存在利用图片
            if (StringUtils.isNotBlank(investigatContent.getUtilizeImg())) {
                //获取新数据图片String
                //删除文件图片
                sysFileApi.batchDeleteFile(investigatContent.getUtilizeImg());
            }
        }
        //判断是否新发物种
        if (YseOrNoEnum.YES.getCode().equals(investigatContent.getNewSpecies())) {
            //判断是否存在新发物种图片
            if (StringUtils.isNotBlank(investigatContent.getNewSpecies())) {
                //获取新数据图片String
                //删除文件图片
                sysFileApi.batchDeleteFile(investigatContent.getNewSpecies());
            }
        }
        return investigatContentMapper.deleteInvestigatContent(speciesInvestigationId);
    }
}
