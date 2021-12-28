package com.sofn.ahhrm.service;

import com.sofn.ahhrm.vo.BaseinfoSubForm;
import com.sofn.ahhrm.vo.BaseinfoSubVo;

import java.util.List;

public interface BaseinfoSubService {
    /**
     * 新增
     */
    BaseinfoSubVo save(BaseinfoSubForm form, String baseId, Integer sort);

    /**
     * 根据资源ID删除
     */
    void deleteByBaseId(String baseId);

    /**
     * 根据资源ID查找
     */
    List<BaseinfoSubVo> listByBaseId(String baseId);


}
