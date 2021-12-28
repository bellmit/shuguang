package com.sofn.fdpi.service;

import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.vo.*;

import java.util.List;

public interface FileManageService {

    FileManage insertFileMange(FileManageForm form, String source, String sourceId);

    List<FileManageVo> listBySourceId(String sourceId);
    List<FileManageVo> listBySourceIdAndFileStatusOne(String sourceId,String fileStatus);

    List<FileManage> list(String sourceId);
    List<FileManage> listforPaper(String sourceId,String fileStatus);
    void deleteBatchIds(List<String> ids);
    int add(String  id,String sourceId);
    int del(String id);
    int batchInsert(List<FileManage> fileList);
    int delBySourceId(String sourceId);
    int updateFileStatusForDelete(String fileSourceId);
}
