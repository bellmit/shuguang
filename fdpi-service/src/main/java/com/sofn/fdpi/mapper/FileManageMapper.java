package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.model.Signboard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FileManageMapper extends BaseMapper<FileManage> {
 int add(Map map);
 int del(String id);

 int batchInsert(@Param("list")List<FileManage> list);
 int delBySourceId(@Param("sourceId") String sourceId);
 int updateFileStatusForDelete(@Param("fileSourceId")String fileSourceId);
}
