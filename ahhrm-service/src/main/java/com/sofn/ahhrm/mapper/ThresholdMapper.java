package com.sofn.ahhrm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhrm.model.Threshold;
import com.sofn.ahhrm.vo.DropDownVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:23
 */
public interface  ThresholdMapper extends BaseMapper<Threshold> {
    Threshold existThreshold(Map map);
    Threshold repeatThreshold(Map map);
    List<Threshold> listByParams(Map map);
    List<DropDownVo> listName();
}
