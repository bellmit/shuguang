package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.TbDepartment;
import com.sofn.fdpi.vo.TbDepartmentVo;

import java.util.List;
import java.util.Map;

public interface TbDepartmentMapper extends BaseMapper<TbDepartment> {
    List<TbDepartmentVo> ListByCondition(Map<String,Object> map);
    int deleteDepartment(Map<String,Object> map);
    TbDepartmentVo getOneForCheckRepeat(Map<String,Object> map);
    TbDepartmentVo getOneById(Map<String,Object> map);
}
