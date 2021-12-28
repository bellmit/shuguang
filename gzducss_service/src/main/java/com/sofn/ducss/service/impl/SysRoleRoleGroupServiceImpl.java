package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.ducss.mapper.SysRoleRoleGroupMapper;
import com.sofn.ducss.model.SysRoleRoleGroup;
import com.sofn.ducss.service.SysRoleRoleGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysRoleRoleGroupServiceImpl extends ServiceImpl<SysRoleRoleGroupMapper, SysRoleRoleGroup> implements SysRoleRoleGroupService {
    @Autowired
    private SysRoleRoleGroupMapper  sysRoleRoleGroupMapper;

    @Override
    public List<SysRoleRoleGroup> getListByGroupId(String groupId) {
        return sysRoleRoleGroupMapper.getListByGroupId(groupId);
    }


}
