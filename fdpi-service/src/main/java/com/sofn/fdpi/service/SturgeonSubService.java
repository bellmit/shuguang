package com.sofn.fdpi.service;


import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SturgeonSubForm;
import com.sofn.fdpi.vo.SturgeonSubVo;

import java.util.List;

public interface SturgeonSubService {

    /**
     * 新增
     */
    SturgeonSubVo save(SturgeonSubForm form, String applyType);

    /**
     * 根据主表id查询
     */
    List<SturgeonSubVo> listBySturgeonId(String sturgeonId);

    /**
     * 根据主表id删除
     */
    void deleteBySturgeonId(String sturgeonId);

    /**
     * 更新子表 返回标签数
     */
    Integer update(String sturgeonId, String applyType, List<SturgeonSubForm> forms);

    List<SelectVo> listSignboardCode(String applyId);

    int delBySturgeonIds(List<String> applyIds);
}
