package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.sys.model.SysResource;
import com.sofn.sys.vo.RoleHaveResourceVo;
import com.sofn.sys.vo.SysResourceBatchSaveVo;
import com.sofn.sys.vo.SysResourceForm;

import java.util.List;

/**
 * @author sofn
 */
public interface SysResourceService extends IService<SysResource> {


    /**
     * 根据当前登录用户和系统ID获取系统APP_ID获取菜单信息
     * @param subsystemId   系统ID或者AppId
     * @return  List<SysResourceForm>
     */
    List<SysResourceForm> getAllResourceBySubsystemId(String subsystemId);


    /**
     * 创建资源
     * @param resource   资源信息
     */
    void createResource(SysResource resource);

    /**
     * 更新资源
     * @param resource  资源
     */
    void updateResource(SysResource resource);

    /**
     * 删除资源
     * @param id  资源ID
     */
    void deleteResource(String id);



//    List<SysSubsystemForm> getAllRoleResource();


    /**
     * 根据子系统代码获取所属的所有菜单
     * @param appId  系统代码
     * @param resourceName   资源名称
     * @return  List<SysResourceForm>
     */
    List<SysResourceForm> getAllResourceByAppId(String appId,String resourceName);

    /**
     * 根据菜单名称和系统代码获取菜单信息，不做权限限制
     * @param appId  系统代码
     * @param resourceName   资源名称
     * @return   List<SysResourceForm>
     */
    List<SysResourceForm> getAllResourceByAppIdWithOutPermission(String appId, String resourceName);
    /**
     * 根据roleID获取所属的所有菜单
     *
     * @param roleId   角色ID
     * @return List<SysResourceForm>
     */
    List<SysResourceForm> getAllResourceByRoleId(String roleId);

//    List<SysResourceForm> getSysResourceTreeList(String resourceName);

    /**
     * 根据内容获取子节点
     * @param list   内容
     * @param parentId   父节点ID,没用
     * @param appId 系统代码
     * @return  List<SysResourceForm>
     */
    List<SysResourceForm> getChildPerms(List<SysResourceForm> list, String parentId,String appId);

    /**
     * 导入菜单
     * @param sysResourceBatchSaveVos   填入的菜单信息
     */
    void importResource(List<SysResourceBatchSaveVo> sysResourceBatchSaveVos);

    /**
     * 批量删除
     * @param ids   需要删除的ID
     */
    void batchDelete(List<String> ids);

    /**
     * 根据IDS更新子节点
     * @param ids   待更新的子节点ID
     * @param newParentIdPrefix  新的parentIds前缀
     * @param oldParentIdPrefix  旧的parentIds前缀
     */
    void updateParentIdsByIds( List<String> ids,  String newParentIdPrefix,
                               String oldParentIdPrefix);


    /**
     * 根据角色获取角色有的资源，如果是超级管理员获取所有的
     * @param roleIdsOrRoleCodes   角色ID或者角色代码ID
     * @param appIds   系统代码集合
     * @return  List<RoleHaveResourceVo>
     */
    List<RoleHaveResourceVo> getResourceByRoleIdsOrRoleCodesOrAppId(List<String> roleIdsOrRoleCodes,List<String> appIds);

    /**
     * 根据角色获取角色有的资源，如果是超级管理员获取所有的
     * @param roleIdsOrRoleCodes   角色ID或者角色代码ID
     * @param appIds   系统代码集合
     * @param carryParent 是否携带父节点
     * @param isMyCreate    是否是自己创建的
     * @return  List<RoleHaveResourceVo>
     */
    List<SysResourceForm> getResourceByRoleIdsOrRoleCodesOrAppId(List<String> roleIdsOrRoleCodes,List<String> appIds,boolean carryParent,boolean isMyCreate);


    /**
     * 根据菜单ID获取菜单信息
     * @param resourceIds 菜单ID
     * @return    List<SysResourceForm>
     */
    List<SysResourceForm> getResourceInfoByIds(List<String> resourceIds);
}
