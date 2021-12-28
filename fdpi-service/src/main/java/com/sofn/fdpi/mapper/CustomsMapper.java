package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Customs;


import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 14:53
 */
public interface CustomsMapper extends BaseMapper<Customs> {
    List<Customs> listParam(Map map);
}
