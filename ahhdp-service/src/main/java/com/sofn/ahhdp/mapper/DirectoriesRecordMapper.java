package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.ahhdp.model.FarmRecord;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 11:14
 */
public interface DirectoriesRecordMapper extends BaseMapper<DirectoriesRecord> {
    List<DirectoriesRecord> listByParams(Map<String, Object> params);

    List<DirectoriesRecord> listByParamsForPublish(Map<String, Object> params);

    List<String> getYears();
}
