package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PeritoneumSpec;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-24 14:37
 */
public interface PeritoneumSpecMapper extends BaseMapper<PeritoneumSpec> {
    /**
     * 根据濒管办ID 批量删除
     * @param peritoneumId 濒管办ID
     */
    void delByperitoneumId(String peritoneumId);
}
