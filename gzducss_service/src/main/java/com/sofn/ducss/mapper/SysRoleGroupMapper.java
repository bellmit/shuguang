package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.SysRoleGroup;

import java.util.List;
import java.util.Map;

public  interface SysRoleGroupMapper extends BaseMapper<SysRoleGroup>  {
    int getSysGroupByName(String groupName, String id);
    List<SysRoleGroup> getSysGroupByCondition(Map<String, Object> params);
}
