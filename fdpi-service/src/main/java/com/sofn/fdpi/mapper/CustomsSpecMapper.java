package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.CustomsSpec;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 14:59
 */
public interface CustomsSpecMapper extends BaseMapper<CustomsSpec> {
    /**
     * 根据还跟id 批量删除
     * @param customsId 海关id
     */
    void delBycustomsId(String customsId);
}
