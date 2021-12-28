package com.sofn.agsjdm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agsjdm.model.BasicsCollection;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 12:59
 */
public interface BasicsCollectionMapper extends BaseMapper<BasicsCollection> {
    List<BasicsCollection> listPage(Map map);
}
