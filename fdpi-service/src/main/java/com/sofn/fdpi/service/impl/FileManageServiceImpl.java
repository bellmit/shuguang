package com.sofn.fdpi.service.impl;

import com.google.common.collect.Maps;
import com.sofn.fdpi.mapper.FileManageMapper;
import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.service.FileManageService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.vo.FileManageForm;
import com.sofn.fdpi.vo.FileManageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service(value = "fileManageService")
public class FileManageServiceImpl implements FileManageService {
    @Autowired
    private FileManageMapper fileManageMapper;
    @Autowired
    private SysRegionApi sysRegionApi;

    @Override
    public FileManage insertFileMange(FileManageForm form, String source, String sourceId) {
        FileManage entity = new FileManage();
        BeanUtils.copyProperties(form, entity);
        entity.setFileSource(source);
        entity.setFileSourceId(sourceId);
        fileManageMapper.insert(entity);
        return entity;
    }

    @Override
    public List<FileManageVo> listBySourceId(String sourceId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("FILE_SOURCE_ID", sourceId);
        List<FileManage> list = fileManageMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(list)) {
            List<FileManageVo> listVo = new ArrayList<>(list.size());
            list.forEach(entity -> {
                FileManageVo vo = new FileManageVo();
                BeanUtils.copyProperties(entity, vo);
                listVo.add(vo);
            });
            return listVo;
        }
        return null;
    }

    @Override
    public List<FileManageVo> listBySourceIdAndFileStatusOne(String sourceId, String fileStatus) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("FILE_SOURCE_ID", sourceId);
        map.put("FILE_STATUS", fileStatus);
        List<FileManage> list = fileManageMapper.selectByMap(map);
        if (!CollectionUtils.isEmpty(list)) {
            List<FileManageVo> listVo = new ArrayList<>(list.size());
            list.forEach(entity -> {
                FileManageVo vo = new FileManageVo();
                BeanUtils.copyProperties(entity, vo);
                listVo.add(vo);
            });
            return listVo;
        }
        return null;
    }

    @Override
    public List<FileManage> list(String sourceId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("FILE_SOURCE_ID", sourceId);
        List<FileManage> list = fileManageMapper.selectByMap(map);
        return list;
    }

    @Override
    public List<FileManage> listforPaper(String sourceId, String fileStatus) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("FILE_SOURCE_ID", sourceId);
        map.put("FILE_STATUS", fileStatus);
        List<FileManage> list = fileManageMapper.selectByMap(map);
        return list;
    }

    @Override
    public void deleteBatchIds(List<String> ids) {
        fileManageMapper.deleteBatchIds(ids);
    }

    @Override
    public int add(String id, String fileSourceId) {
        Map map = Maps.newHashMap();
        map.put("id", id);
        map.put("fileSourceId", fileSourceId);
        return fileManageMapper.add(map);
    }

    @Override
    public int del(String id) {
        List<FileManage> list = list(id);
        for (FileManage f :
                list) {
            sysRegionApi.delFile(f.getId());
        }
        return fileManageMapper.del(id);
    }

    @Override
    public int batchInsert(List<FileManage> fileList) {
        int result = 0;
        for (FileManage fm : fileList) {
            result += fileManageMapper.insert(fm);
        }
        return result;
    }

    @Override
    public int delBySourceId(String sourceId) {
        return fileManageMapper.delBySourceId(sourceId);
    }

    @Override
    public int updateFileStatusForDelete(String fileSourceId) {
        return fileManageMapper.updateFileStatusForDelete(fileSourceId);
    }
}
