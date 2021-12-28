package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.PictureAccessories;
import org.apache.ibatis.annotations.Param;

public interface PictureAccessoriesMapper extends BaseMapper<PictureAccessories> {

    void deleteLogicBySourceId(@Param("sourceId") String sourceId);

    void deleteBySourceIdAndFileUse(@Param("sourceId") String sourceId, @Param("fileUse") String fileUse);
}
