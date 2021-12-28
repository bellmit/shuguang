package com.sofn.sys.mapper;

import com.sofn.sys.model.SysRoleGroup;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public  interface SysRoleGroupMapper extends BaseMapper<SysRoleGroup>  {
    int getSysGroupByName(String groupName,String id);
    List<SysRoleGroup> getSysGroupByCondition(Map<String, Object> params);
}
