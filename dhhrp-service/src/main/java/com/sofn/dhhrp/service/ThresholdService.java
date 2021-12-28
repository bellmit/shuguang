package com.sofn.dhhrp.service;

import com.sofn.dhhrp.model.Threshold;
import com.sofn.dhhrp.vo.DropDownVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:27
 */
public interface ThresholdService {
    void  save(Threshold threshold);
    void delete(String id);
    void update(Threshold threshold);
    Threshold getOne(String id);
    PageUtils<Threshold> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
    List<DropDownVo> listName();
}
