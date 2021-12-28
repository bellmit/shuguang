package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.Zone;

import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 14:18
 */
public interface FarmMapper extends BaseMapper<Farm> {
    List<Farm> listByParams(Map<String, Object> params);
}
