package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.exception.SofnException;
import com.sofn.sys.mapper.SysRoleResourceMapper;
import com.sofn.sys.model.SysRoleResource;
import com.sofn.sys.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 角色资源类关联表相关接口
 * @author sofn
 */
@Service
@Slf4j
public class RoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper,SysRoleResource>
        implements RoleResourceService {

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;


    @Override
    public void addPermissionForRole(String roleId,String ids) {
        String[] idArray=ids.split(",");
        try{
            for (String s : idArray) {
                SysRoleResource srr = new SysRoleResource(UUID.randomUUID().toString(), roleId, s);
                sysRoleResourceMapper.insert(srr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("角色授予权限异常");
        }

    }

    @Override
    public void cancelPermissionForRole(String roleId,String ids) {
        String[] idArray=ids.split(",");
        try{
            for (String s : idArray) {
                sysRoleResourceMapper.delete(new QueryWrapper<SysRoleResource>().eq("role_id", roleId).eq
                        ("resource_id", s)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("角色取消授权异常");
        }

    }

    @Override
    public List<String> findRoleIdByResourceId(String resourceId) {
        return sysRoleResourceMapper.findRoleIdByResourceId(resourceId);
    }


    @Override
    public  List<SysRoleResource> findPermissionForRole(String roleId) {
        return sysRoleResourceMapper.findPermissionForRole(roleId);
    }

}
