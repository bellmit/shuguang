package com.sofn.sys.service.permission;

import com.sofn.common.utils.PageUtils;
import com.sofn.sys.vo.SysRoleGroupVo;

import java.util.List;

/**
 * 角色组权限相关
 * @author heyongjie
 * @date 2019/12/19 16:11
 */
public interface PermissionRoleGroupService {

    /**
     * 根据用户获取用户拥有的角色组和自己创建的角色组
     * @param userId   用户ID
     * @return     List<SysRoleGroupVo>
     */
    List<SysRoleGroupVo> selectUserHasRoleGroup(String userId);

    /**
     * 按条件分页获取当前用户有的角色组
     * @param userId   用户
     * @param groupName   用户组名称  模糊查询
     * @param pageNo  从哪条记录开始
     * @param pageSize  每页显示多少条数据
     * @return
     */
    PageUtils<SysRoleGroupVo> selectUserHasRoleGroupByPage(String userId,String groupName,Integer pageNo,Integer pageSize);

    /**
     * 是否可以编辑
     * @param roleGroupId    角色ID
     * @return   true可以编辑， false 不可以编辑
     */
    boolean isEdit(String roleGroupId);
}
