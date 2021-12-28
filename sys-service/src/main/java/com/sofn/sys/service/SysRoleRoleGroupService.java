package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysRoleRoleGroup;

import java.util.List;

public interface SysRoleRoleGroupService  extends IService<SysRoleRoleGroup> {
    List<SysRoleRoleGroup> getListByGroupId(String groupId);
}
