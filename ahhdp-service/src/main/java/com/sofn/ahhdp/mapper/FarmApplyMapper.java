package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.FarmApply;
import com.sofn.ahhdp.model.ZoneApply;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 14:19
 */
public interface FarmApplyMapper extends BaseMapper<FarmApply> {
    List<FarmApply> listByParams(Map<String, Object> params);
}
