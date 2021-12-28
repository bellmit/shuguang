package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjyz.mapper.PictureAccessoriesMapper;
import com.sofn.agpjyz.model.PictureAccessories;
import com.sofn.agpjyz.service.PictureAccessoriesService;
import com.sofn.agpjyz.util.FileUtil;
import com.sofn.agpjyz.vo.PictureAccessoriesForm;
import com.sofn.agpjyz.vo.PictureAccessoriesVo;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 图片附件服务类
 **/
@Service(value = "pictureAccessoriesService")
public class PictureAccessoriesServiceImpl extends BaseService<PictureAccessoriesMapper, PictureAccessories> implements PictureAccessoriesService {

    @Autowired
    private PictureAccessoriesMapper paMapper;
    @Autowired
    private FileUtil fileUtil;

    @Override
    public PictureAccessoriesVo save(PictureAccessoriesForm form, String fileSource) {
        PictureAccessories entity = new PictureAccessories();
        BeanUtils.copyProperties(form, entity);
        entity.setFileSource(fileSource);
        entity.preInsert();
        entity.setCrtTime(System.currentTimeMillis());
        paMapper.insert(entity);
        return PictureAccessoriesVo.entity2Vo(entity);
    }

    @Override
    public void deleteBySourceId(String sourceId) {
        paMapper.deleteLogicBySourceId(sourceId);
    }

    @Override
    public List<PictureAccessoriesVo> listBySourceId(String sourceId) {
        QueryWrapper<PictureAccessories> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("SOURCE_ID", sourceId).eq("DEL_FLAG", BoolUtils.N).orderByAsc("CRT_TIME");
        List<PictureAccessories> list = paMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(list)) {
            List<PictureAccessoriesVo> listVo = new ArrayList<>(list.size());
            for (PictureAccessories pa : list) {
                listVo.add(PictureAccessoriesVo.entity2Vo(pa));
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
    public void updateSourceId(String sourceId, List<PictureAccessoriesForm> forms, String fileSource, String fileUse) {
        //选逻辑删除
        this.deleteBySourceIdAndFileUse(sourceId, fileUse);
        List<String> needActivationIds = new ArrayList<>();
        //循环比较旧数据和新数据,有则更新删除状态为N, 没有则需新增且到支撑平台去激活
        if (!CollectionUtils.isEmpty(forms)) {
            for (PictureAccessoriesForm form : forms) {
                String id = form.getId();
                //没有ID,直接新增数据,并且保存需要激活的文件ID到支撑平台去激活;
                if (StringUtils.isEmpty(id)) {
                    form.setSourceId(sourceId);
                    form.setFileUse(fileUse);
                    needActivationIds.add(this.save(form, fileSource).getFileId());
                }
                //有ID,更新删除状态为N
                else {
                    PictureAccessories entity = new PictureAccessories();
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
