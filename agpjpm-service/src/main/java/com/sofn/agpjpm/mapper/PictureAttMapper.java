package com.sofn.agpjpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjpm.model.PictureAtt;
import org.apache.ibatis.annotations.Param;

public interface PictureAttMapper extends BaseMapper<PictureAtt> {

    void deleteLogicBySourceId(@Param("sourceId") String sourceId);

    void deleteBySourceIdAndFileUse(@Param("sourceId") String sourceId, @Param("fileUse") String fileUse);
}
