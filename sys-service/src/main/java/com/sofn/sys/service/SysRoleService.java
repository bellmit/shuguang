package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.vo.SysRoleForm;
import com.sofn.sys.vo.UserHaveRoleVo;

import java.util.List;
import java.util.Map;

/**
 * 角色相关服务
 * @author sofn
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 创建角色
     * @param roleForm   角色信息
     */
    void createRole(SysRoleForm roleForm);

    /**
     * 更新角色
     * @param roleForm 角色信息
     */
    void updateRole(SysRoleForm roleForm);

    /**
     * 删除角色
     * @param ids   角色ID
     */
    void deleteRole(List<String> ids);

    /**
     * 根据条件查询角色信息
     * @param params  条件  subsystemId  系统ID  roleCode  角色代码    keyword 角色名称或者描述
     * @return     List<SysRoleForm>
     */
    List<SysRoleForm> getSysRoleByCondition(Map<String,Object> params);

    /**
     * 根据系统查询角色
     * @param subsystemId   系统ID
     * @return   List<SysRole>
     */
    List<SysRole> getSysRoleListBySubsystemId(String subsystemId);

    /**
     * 根据系统代码和角色代码获取角色
     * @param appId   系统代码
     * @param roleCode  角色代码
     * @return  SysRole
     */
    SysRole getSysRoleByAppIdAndRoleCode(String appId, String roleCode);

    /**
     * 按条件分页查询角色信息
     * @param params  条件  subsystemId  系统ID  roleCode  角色代码    keyword 角色名称或者描述
     * @param pageNo   从哪条数据开始
     * @param pageSize  每页显示多少条
     * @return  分页信息和角色信息
     */
    PageUtils<SysRoleForm> getSysRoleByCondition(Map<String,Object> params, Integer pageNo, Integer pageSize);

    /**
     * 根据系统代码查询系统角色列表
     * @param appId 系统代码
     * @return 角色列表
     */
    List<SysRole> getRoleListByAppId(String appId);


    /**
     * 获取每个角色有哪些权限
     * @return   Map<String,String>
     *     ROLEID   角色ID
     *     ROLECODE   角色代码
     *     PERMISSION   角色所对应的权限
     */
    Map<String,String> getRoleHasPermission();

    /**
     * 批量获取用户有的角色
     * @param userIds   用户ID
     * @return   Map<String,List<SysRole>>   key为用户ID   List为用户有的角色
     */
    List<UserHaveRoleVo> getUserHaveRole(List<String> userIds);

}
