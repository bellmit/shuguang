package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Peritoneum;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-24 14:14
 */
public interface PeritoneumMapper extends BaseMapper<Peritoneum> {
  List<Peritoneum> listParam(Map map);
}
