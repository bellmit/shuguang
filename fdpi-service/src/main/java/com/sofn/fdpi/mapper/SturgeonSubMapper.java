package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.SturgeonSub;
import com.sofn.fdpi.vo.SelectVo;

import java.util.List;


public interface SturgeonSubMapper extends BaseMapper<SturgeonSub> {

    List<SelectVo> listSignboardCodeByApplyId(String applyId);
}
