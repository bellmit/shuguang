package com.sofn.sys.service.permission;

import com.sofn.common.utils.PageUtils;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SystemRoleVo;

import java.util.List;
import java.util.Map;

/**
 * 角色权限相关
 * @author heyongjie
 * @date 2019/12/18 16:13
 */
public interface PermissionRoleService {

    /**
     * 获取当前登录用户有的角色-包含用户有的角色组中角色还有自己创建的角色
     * @return   List<SysRoleVo>
     */
    List<SysRoleVo> getLoginUserHaveRole();


    /**
     * 获取当前登录用户有的角色-包含用户有的角色组中角色还有自己创建的角色
     * @param userId    用户ID
     * @param superAdminSearchAll    如果是超级管理员是否查询所有
     * @return   List<SysRoleVo>
     */
    List<SysRoleVo> getLoginUserHaveRole(String userId,boolean superAdminSearchAll);


    /**
     * 获取当前登录用户有的角色-包含用户有的角色组中角色   可选择是否包含自己创建的角色
     * @param userId   用户ID
     * @param superAdminSearchAll   超级管理员是否查询所有
     * @return   List<SysRoleVo>
     */
    List<SysRoleVo> getLoginUserHaveRoleNotIncludeUserCreate(String userId, boolean superAdminSearchAll);


    /**
     * 分页显示用户有的角色信息
     * @param params  参数
     * @param pageNo  从哪条记录开始
     * @param pageSize   每页显示多少条
     * @return    PageUtils<SysRoleVo>
     */
    PageUtils<SysRoleVo> getLoginUserByPage(Map<String,Object> params,Integer pageNo,Integer pageSize);

    /**
     * 是否可以编辑
     * @param roleId    角色ID
     * @return   true可以编辑， false 不可以编辑
     */
    boolean isEdit(String roleId);


    /**
     * 按条件分页查询角色和系统信息
     * @param keyword   可以是系统名称或者菜单名称
     * @param pageNo    每页显示多少条
     * @param roleIds  角色ID，多个用英文逗号隔开
     * @param pageSize  每页显示页数
     * @return    PageUtils<SystemRoleVo>
     */
    PageUtils<SystemRoleVo> getRoleAndSystemInfoByPage(String keyword,String roleIds, Integer pageNo, Integer pageSize);



}
