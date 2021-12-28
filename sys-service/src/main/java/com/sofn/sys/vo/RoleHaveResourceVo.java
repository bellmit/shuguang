package com.sofn.sys.vo;

import lombok.Data;

import java.util.List;

/**
 * 角色有的资源VO
 * @author heyongjie
 * @date 2020/7/22 15:38
 */
@Data
public class RoleHaveResourceVo {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 系统代码  一般角色和资源的系统代码都是同一个
     */
    private String appId;

    /**
     * 角色对应的菜单
     */
    private List<SysResourceForm> sysResources;

}
