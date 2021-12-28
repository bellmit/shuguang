package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sofn.common.utils.IdUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysRoleRoleGroupMapper;
import com.sofn.sys.mapper.SysUserRoleMapper;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.model.SysRoleRoleGroup;
import com.sofn.sys.model.SysUserRole;
import com.sofn.sys.service.SysRoleRoleGroupService;
import com.sofn.sys.service.SysRoleService;
import com.sofn.sys.service.SysUserRolePermissionService;
import com.sofn.sys.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysRoleRoleGroupServiceImpl extends ServiceImpl<SysRoleRoleGroupMapper,SysRoleRoleGroup> implements SysRoleRoleGroupService {
    @Autowired
    private SysRoleRoleGroupMapper  sysRoleRoleGroupMapper;

    @Override
    public List<SysRoleRoleGroup> getListByGroupId(String groupId) {
        return sysRoleRoleGroupMapper.getListByGroupId(groupId);
    }


}
