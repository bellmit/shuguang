package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sofn.agpjpm.constants.Constants;
import com.sofn.agpjpm.mapper.TargetSpecMapper;
import com.sofn.agpjpm.model.TargetSpec;
import com.sofn.agpjpm.service.PictureAttService;
import com.sofn.agpjpm.service.SpeciesMonitorService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.util.ApiUtil;
import com.sofn.agpjpm.util.FileUtil;
import com.sofn.agpjpm.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 监测表服务类
 **/
@Service(value = "speciesMonitorService")
public class SpeciesMonitorServiceImpl extends BaseService<TargetSpecMapper, TargetSpec> implements SpeciesMonitorService {

    @Resource
    private TargetSpecMapper targetSpecMapper;

    @Resource
    private PictureAttService pictureAttService;

    @Resource
    private JzbApi jzbApi;

    @Resource
    private FileUtil fileUtil;

    @Override
    @Transactional
    public SpeciesMonitorVo save(SpeciesMonitorForm form, String sourceId, Integer sort) {
        this.validaTargetSpec(form);
        TargetSpec entity = new TargetSpec();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        entity.setSourceId(sourceId);
        entity.setSpecSource(Constants.SPEC_SOURCE_MONITOR);
        entity.setCrtTime(System.currentTimeMillis());
        targetSpecMapper.insert(entity);
        SpeciesMonitorVo vo = SpeciesMonitorVo.entity2Vo(entity);
        //处理文件信息
        StringBuilder ids = new StringBuilder();
        String idsPlant = this.handleFileInfo(form.getPlantPic(), vo, "plant");
        if (StringUtils.hasText(idsPlant)) {
            ids.append("," + idsPlant);
        }
        String idsCommunity = this.handleFileInfo(form.getCommunityPic(), vo, "community");
        if (StringUtils.hasText(idsCommunity)) {
            ids.append("," + idsCommunity);
        }
        //激活系统文件
        if (StringUtils.hasText(ids.toString())) {
            fileUtil.activationFile(ids.toString().substring(1));
        }
        return vo;
    }

    @Override
    @Transactional
    public void deleteBySourceId(String sourceId) {
        //删除附件
        QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId).eq("del_flag", BoolUtils.N);
        List<TargetSpec> targetSpecs = targetSpecMapper.selectList(queryWrapper);
        this.deletePictureAttByIds(targetSpecs.stream().map(t -> t.getId()).collect(Collectors.toList()));
        //删除目标物种
        TargetSpec targetSpec = new TargetSpec();
        targetSpec.setDelFlag(BoolUtils.Y);
        UpdateWrapper<TargetSpec> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("source_id", sourceId);
        targetSpecMapper.update(targetSpec, updateWrapper);
    }

    private void deletePictureAttByIds(List<String> ids) {
        ids.forEach(id -> {
            pictureAttService.deleteBySourceId(id);
        });
    }

    @Override
    public void deleteByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            //逻辑删除物种
            TargetSpec targetSpec = new TargetSpec();
            targetSpec.setDelFlag(BoolUtils.Y);
            UpdateWrapper<TargetSpec> updateWrapper = new UpdateWrapper();
            updateWrapper.in("id", ids);
            targetSpecMapper.update(targetSpec, updateWrapper);
            //删除图片附件
            this.deletePictureAttByIds(ids);
        }
    }

    @Override
    @Transactional
    public void update(List<SpeciesMonitorForm> forms, String sourceId) {

        QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId).eq("del_flag", BoolUtils.N);
        List<TargetSpec> targetSpecs = targetSpecMapper.selectList(queryWrapper);
        //原有物种id列表
        List<String> oldIds = targetSpecs.stream().map(t -> t.getId()).collect(Collectors.toList());
        //现有物种id列表
        List<String> newIds = forms.stream().map(t -> t.getId()).collect(Collectors.toList());
        oldIds.removeAll(newIds);
        //删除已在页面删掉的物种
        this.deleteByIds(oldIds);


        //循环比较旧数据和新数据,有则更新删除状态为N, 没有则需新增
        for (SpeciesMonitorForm form : forms) {
            this.validaTargetSpec(form);
            String id = form.getId();
            //没有ID,直接新增数据
            if (StringUtils.isEmpty(id)) {
                this.save(form, sourceId, null);
            }
            //有ID,更新数据并更改状态为N
            else {
                TargetSpec entity = new TargetSpec();
                BeanUtils.copyProperties(form, entity);
                entity.preUpdate();
                targetSpecMapper.updateById(entity);
                //更新文件信息
                pictureAttService.updateSourceId(id, form.getPlantPic(), Constants.FILE_SOURCE_MONITOR_SPEC, "plant");
                pictureAttService.updateSourceId(id, form.getCommunityPic(), Constants.FILE_SOURCE_MONITOR_SPEC, "community");
            }
        }
    }

    @Override
    public List<SpeciesMonitorVo> listBySourceId(String sourceId) {
        QueryWrapper<TargetSpec> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId).eq("del_flag", BoolUtils.N).orderByAsc("crt_time");
        List<TargetSpec> targetSpecs = targetSpecMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(targetSpecs)) {
            List<SpeciesMonitorVo> speciesMonitorVos = new ArrayList<>(targetSpecs.size());
            targetSpecs.forEach(targetSpec -> {
                speciesMonitorVos.add(SpeciesMonitorVo.entity2Vo(targetSpec));
            });
            return speciesMonitorVos;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<SpeciesMonitorVo> listWithPicBySourceId(String sourceId) {
        List<SpeciesMonitorVo> speciesMonitorVos = this.listBySourceId(sourceId);
        if (!CollectionUtils.isEmpty(speciesMonitorVos)) {
            Map<String, DropDownWithLatinVo> map = ApiUtil.getResultObjMap(jzbApi.listForSpecies());
            for (SpeciesMonitorVo speciesMonitorVo : speciesMonitorVos) {
                DropDownWithLatinVo dropDownWithLatinVo = map.get(speciesMonitorVo.getSpecId());
                if (Objects.nonNull(dropDownWithLatinVo)) {
                    speciesMonitorVo.setAttrName(dropDownWithLatinVo.getAttrName());
                    speciesMonitorVo.setFamilyName(dropDownWithLatinVo.getFamilyName());
                    speciesMonitorVo.setSpecName(dropDownWithLatinVo.getName());
                    speciesMonitorVo.setLatinName(dropDownWithLatinVo.getLatinName());
                } else {
                    speciesMonitorVo.setSpecId("");
                    speciesMonitorVo.setAttrName("");
                    speciesMonitorVo.setFamilyName("");
                    speciesMonitorVo.setSpecName("");
                    speciesMonitorVo.setLatinName("");
                }
                List<PictureAttVo> pictureAttVos = pictureAttService.listBySourceId(speciesMonitorVo.getId());
                if (!CollectionUtils.isEmpty(pictureAttVos)) {
                    speciesMonitorVo.setPlantPic(pictureAttVos.stream().
                            filter(vo -> "plant".equals(vo.getFileUse())).collect(Collectors.toList()));
                    speciesMonitorVo.setCommunityPic(pictureAttVos.stream().
                            filter(vo -> "community".equals(vo.getFileUse())).collect(Collectors.toList()));
                }
            }
        }
        return speciesMonitorVos;
    }

    /**
     * 处理文件信息
     */
    private String handleFileInfo(List<PictureAttForm> pictureAttForms, SpeciesMonitorVo speciesMonitorVo, String fileUse) {
        if (!CollectionUtils.isEmpty(pictureAttForms)) {
            List<PictureAttVo> pictureAttVos = new ArrayList<>(pictureAttForms.size());
            StringBuilder ids = new StringBuilder();
            for (PictureAttForm paForm : pictureAttForms) {
                paForm.setSourceId(speciesMonitorVo.getId());
                paForm.setFileUse(fileUse);
                ids.append("," + paForm.getFileId());
                pictureAttVos.add(pictureAttService.save(paForm, Constants.FILE_SOURCE_MONITOR_SPEC));
            }
            if ("plant".equals(fileUse)) {
                speciesMonitorVo.setPlantPic(pictureAttVos);
            } else if ("community".equals(fileUse)) {
                speciesMonitorVo.setCommunityPic(pictureAttVos);
            }
            return ids.toString().substring(1);
        }
        return "";
    }

    private void validaTargetSpec(SpeciesMonitorForm form) {
        if (StringUtils.isEmpty(form.getSpecId())) {
            throw new SofnException("种名ID不能为空");
        } else if (StringUtils.isEmpty(form.getLatinName())) {
            throw new SofnException("拉丁文种名不能为空");
        }
        if (form.getLatinName().length() > 100) {
            throw new SofnException("拉丁文种名(目标物种)不能超过100");
        } else if (StringUtils.hasText(form.getFamilyName()) && form.getFamilyName().length() > 50) {
            throw new SofnException("科名(目标物种)不能超过50");
        } else if (StringUtils.hasText(form.getAttrName()) && form.getAttrName().length() > 50) {
            throw new SofnException("属名(目标物种)不能超过50");
        } else if (StringUtils.hasText(form.getGrowth()) && form.getGrowth().length() > 100) {
            throw new SofnException("生长状况(目标物种)不能超过100");
        } else if (StringUtils.hasText(form.getDensity()) && form.getDensity().length() > 100) {
            throw new SofnException("分布密度(目标物种)不能超过100");
        } else if (StringUtils.hasText(form.getRichness()) && form.getRichness().length() > 100) {
            throw new SofnException("丰富度(目标物种)不能超过100");
        }
        //分布面积
        Double area = form.getArea();
        if (area != null) {
            if (area < 0) {
                throw new SofnException("分布面积(目标物种)不能小于0");
            } else if (area > 9999999) {
                throw new SofnException("分布面积(目标物种)不能大于9999999");
            }
        }

    }
}
