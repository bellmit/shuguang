package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.model.SignboardApplyList;
import com.sofn.fdpi.vo.*;

import java.util.List;
import java.util.Map;

public interface SignboardApplyListService extends IService<SignboardApplyList> {

    SignboardApplyList insertSignboardApplyList(SignboardApplyListForm signboardApplyListForm);

    List<SignboardApplyListVo> listByApplyId(String applyId);

    PageUtils<SignboardApplyListVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    void deleteByApplyId(String applyId);

    Integer countApplyList(String applyId);

    void deleteBatchIds(List<String> ids);

    void updateDelFlagByPringId(String pringId);

    int delByApplyIds(List<String> applyIds);
}
