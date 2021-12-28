package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdpi.mapper.OmFileMapper;
import com.sofn.fdpi.model.OmFile;
import com.sofn.fdpi.service.OmFileService;
import com.sofn.fdpi.util.FileUtil;
import com.sofn.fdpi.vo.OmFileForm;
import com.sofn.fdpi.vo.OmFileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("omFileService")
public class OmFileServiceImpl implements OmFileService {

    @Resource
    private OmFileMapper omFileMapper;

    @Resource
    private FileUtil fileUtil;


    @Override
    @Transactional
    public void add(String sourceId, List<OmFileForm> files) {
        if (!CollectionUtils.isEmpty(files)) {
            for (OmFileForm off : files) {
                OmFile entity = new OmFile();
                BeanUtils.copyProperties(off, entity);
                entity.setId(IdUtil.getUUId());
                entity.setDelFlag(BoolUtils.N);
                entity.setSourceId(sourceId);
                omFileMapper.insert(entity);
            }
            fileUtil.activationFile(String.join(",",
                    files.stream().map(OmFileForm::getFileId).collect(Collectors.toList())));
        }
    }

    @Override
    @Transactional
    public void update(String sourceId, List<OmFileForm> files) {
        List<OmFileVO> ofvs = this.listBySourceId(sourceId);
        List<String> oldIds = ofvs.stream().map(OmFileVO::getId).collect(Collectors.toList());
        List<String> oldFileIds = ofvs.stream().map(OmFileVO::getFileId).collect(Collectors.toList());
        List<String> keepFileIds = Lists.newArrayList();
        List<String> keepIds = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(files)) {
            List<OmFileForm> newFiles = Lists.newArrayList();
            for (OmFileForm off : files) {
                if (StringUtils.isEmpty(off.getId())) {
                    newFiles.add(off);
                } else {
                    keepIds.add(off.getId());
                    keepFileIds.add(off.getFileId());
                }
            }
            this.add(sourceId, newFiles);
            oldFileIds.removeAll(keepFileIds);
            oldIds.removeAll(keepIds);
        }
        this.delete(oldIds);
        fileUtil.batchDeleteFile(String.join(",", oldFileIds));
    }

    @Override
    public void delete(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            UpdateWrapper<OmFile> updateWrapper = new UpdateWrapper<>();
            updateWrapper.in("id", ids).set("del_flag", BoolUtils.Y);
            omFileMapper.update(null, updateWrapper);
        }
    }

    @Override
    public List<OmFileVO> listBySourceId(String sourceId) {
        QueryWrapper<OmFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("source_id", sourceId);
        List<OmFile> ofs = omFileMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(ofs)) {
            List<OmFileVO> ofvs = Lists.newArrayListWithCapacity(ofs.size());
            for (OmFile of : ofs) {
                ofvs.add(OmFileVO.entity2Vo(of));
            }
            return ofvs;
        }
        return Collections.EMPTY_LIST;
    }

}

