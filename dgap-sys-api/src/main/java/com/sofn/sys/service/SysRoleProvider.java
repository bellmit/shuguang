package com.sofn.sys.service;

import com.sofn.sys.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 角色provider接口
 */
public interface SysRoleProvider {


    /**
     * 获取满足条件的所有角色
     * @param params
     * @return
     */
    List<SysRole> getSysRoleAllList(Map<String,Object> params);


    /**
     * 获取用户所在机构的所有角色
     * @param orgId
     * @param status
     * @return
     */
    public List<SysRole> getOrgRoleByOrgId(String orgId);
}
