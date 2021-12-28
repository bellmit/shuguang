package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.SysRoleRoleGroup;

import java.util.List;

public interface SysRoleRoleGroupService extends IService<SysRoleRoleGroup> {
    List<SysRoleRoleGroup> getListByGroupId(String groupId);
}
