package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.model.SignboardApplyList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/27 17:30
 **/
public interface SignboardApplyListMapper extends BaseMapper<SignboardApplyList> {

    List<SignboardApplyList> listByApplyId(@Param("applyId") String applyId);

    List<SignboardApplyList> listByParams(Map<String, Object> params);

    void updateDelFlagByPringId(@Param("pringId")  String pringId);
}
