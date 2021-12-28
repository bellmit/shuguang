package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesApply;
import com.sofn.ahhdp.model.FarmApply;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 11:13
 */
public interface DirectoriesApplyMapper extends BaseMapper<DirectoriesApply> {
    List<DirectoriesApply> listByParams(Map<String, Object> params);
}
