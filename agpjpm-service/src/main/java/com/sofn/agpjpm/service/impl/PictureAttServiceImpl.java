package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjpm.mapper.PictureAttMapper;
import com.sofn.agpjpm.model.PictureAtt;
import com.sofn.agpjpm.service.PictureAttService;
import com.sofn.agpjpm.util.FileUtil;
import com.sofn.agpjpm.vo.PictureAttForm;
import com.sofn.agpjpm.vo.PictureAttVo;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片附件服务类
 **/
@Service(value = "pictureAccessoriesService")
public class PictureAttServiceImpl extends BaseService<PictureAttMapper, PictureAtt> implements PictureAttService {

    @Resource
    private PictureAttMapper paMapper;
    @Resource
    private FileUtil fileUtil;

    @Override
    public PictureAttVo save(PictureAttForm form, String fileSource) {
        PictureAtt entity = new PictureAtt();
        BeanUtils.copyProperties(form, entity);
        entity.setFileSource(fileSource);
        entity.preInsert();
        entity.setCrtTime(System.currentTimeMillis());
        paMapper.insert(entity);
        return PictureAttVo.entity2Vo(entity);
    }

    @Override
    public void deleteBySourceId(String sourceId) {
        paMapper.deleteLogicBySourceId(sourceId);
    }

    @Override
    public List<PictureAttVo> listBySourceId(String sourceId) {

        QueryWrapper<PictureAtt> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId).eq("del_flag", BoolUtils.N).orderByAsc("crt_time");
        List<PictureAtt> list = paMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<PictureAttVo> listVo = new ArrayList<>(list.size());
            for (PictureAtt pa : list) {
                listVo.add(PictureAttVo.entity2Vo(pa));
            }
            return listVo;
        }
        return null;
    }


    private void deleteBySourceIdAndFileUse(String sourceId, String fileUse) {

        paMapper.deleteBySourceIdAndFileUse(sourceId, fileUse);
    }

    @Override
    @Transactional
    public void updateSourceId(String sourceId, List<PictureAttForm> forms, String fileSource, String fileUse) {
        //选逻辑删除
        this.deleteBySourceIdAndFileUse(sourceId, fileUse);
        List<String> needActivationIds = new ArrayList<>();
        //循环比较旧数据和新数据,有则更新删除状态为N, 没有则需新增且到支撑平台去激活
        if (!CollectionUtils.isEmpty(forms)) {
            for (PictureAttForm form : forms) {
                String id = form.getId();
                //没有ID,直接新增数据,并且保存需要激活的文件ID到支撑平台去激活;
                if (StringUtils.isEmpty(id)) {
                    form.setSourceId(sourceId);
                    form.setFileUse(fileUse);
                    needActivationIds.add(this.save(form, fileSource).getFileId());
                }
                //有ID,更新删除状态为N
                else {
                    PictureAtt entity = new PictureAtt();
                    entity.setId(id);
                    entity.setDelFlag(BoolUtils.N);
                    paMapper.updateById(entity);
                }
            }
        }
        //系统激活文件
        fileUtil.activationFile(String.join(",", needActivationIds));
    }

}
