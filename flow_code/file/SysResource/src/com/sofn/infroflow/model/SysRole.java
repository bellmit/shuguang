package com.sofn.infroflow.model;

import com.cvicse.workflow.api.resources.Role;

/**
 * @author heyongjie
 * @date 2020年4月28日14:43:51
 */
public class SysRole {

    /**
     * 角色唯一标识
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色所属系统
     */
    private String appId;

    /**
     * 角色描述
     */
    private String description;
    
    /**
     * 角色代码 
     */
    private String roleCode;

    /**
     * 将系统角色适配到流程角色
     * @param sysRole 系统角色
     * @return  流程角色
     */
    public static Role getRole(SysRole sysRole){
        if(sysRole == null){
            return null;
        }
        Role role = new Role();
        role.setId(sysRole.getRoleCode());
        role.setName(sysRole.getName() + "-" + sysRole.getAppId());
        role.setDescription("所属系统：" + sysRole.getAppId());
        return role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
    
    
}
