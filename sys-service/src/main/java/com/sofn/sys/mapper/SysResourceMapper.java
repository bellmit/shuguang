package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysResource;
import com.sofn.sys.vo.RoleHaveResourceVo;
import com.sofn.sys.vo.SysResourceForm;
import com.sofn.sys.vo.SysResourceTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sofn
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
    /**
     * 根据角色id获取所有的资源菜单
     * @param roleId   角色ID
     * @return    List<SysResourceForm>
     */
    List<SysResourceForm> getAllResourceByRoleId(String roleId);

    /**
     * 根据资源菜单的id,name,url获取资源菜单个数
     * @param permission   权限码
     * @param resourceUrl   资源路径
     * @param id   资源ID
     * @return      菜单数量
     */
    Integer getSysResourceByPermissionOrUrl(@Param("permission") String permission, @Param("resourceUrl") String resourceUrl,@Param("id") String id);

    /**
     * 根据优先级倒序排序所有菜单
     * @return    List<SysResource>
     */
    List<SysResource> selectResourceListByPriority();

    /**
     * 根据条件获取所有的资源菜单
     * @param params  条件 resourceName 菜单名称-模糊查询
     *                resourceUrl   资源路径
     *                delFlag   是否删除
     *                parentId   父节点
     *                appId      系统代码
     * @return  List<SysResourceForm>
     */
    List<SysResourceForm> getSysResourceByCondition(Map<String,Object> params);

    /**
     * 根据系统代码和角色代码获取菜单
     * @param appId  系统AppId-可以为空
     * @param roleIds   角色ID-不能为空
     * @return  List<SysResource>
     */
    List<SysResource> getSysResourceByUserAppId(@Param("appId") String appId, @Param("roleIds") List<String> roleIds);

    /**
     * 根据菜单ID批量获取信息
     * @param ids  菜单ID
     * @return  SysResource
     */
    List<SysResourceForm> getListByIds(@Param("ids") List<String> ids);

    /**
     * 根据条件获取菜单树信息
     * @param params  参数
     * @return    List<SysResourceTreeVo>
     */
    List<SysResourceTreeVo> getSysResourceTreeVo(Map<String,Object> params);

    /**
     * 批量添加数据
     * @param sysResources  菜单信息
     */
    void batchSave(@Param("sysResources") List<SysResource> sysResources);

    /**
     * 如根据0/Id/Id 匹配出所有的ParentIdS以0/Id/Id开头的
     * @param parentIds   0/Id/Id类型的字符串组成的集合
     * @return List<SysResource>
     */
    List<SysResource> getSysResourceByParentStrIds(@Param("parentIds") Set<String> parentIds);

    /**
     * 批量删除
     * @param deleteUserId 删除的用户
     * @param deleteDate   删除时间
     * @param ids  需要删除的菜单ID集合
     */
    void batchDelete(@Param("ids") Set<String> ids,@Param("deleteUserId") String deleteUserId, @Param("deleteDate") Date deleteDate);

    /**
     * 根据IDS更新子节点
     * @param ids   待更新的子节点ID
     * @param newParentIdPrefix  新的parentIds前缀
     * @param oldParentIdPrefix  旧的parentIds前缀
     */
    void updateParentIdsByIds(@Param("ids") List<String> ids,@Param("newParentIdPrefix") String newParentIdPrefix,
                              @Param("oldParentIdPrefix") String oldParentIdPrefix);


    /**
     * 根据角色获取角色有的资源
     * @param roleIdsOrRoleCodes   角色ID或者角色代码ID
     * @param appIds   系统代码集合
     * @return  List<RoleHaveResourceVo>
     */
    List<RoleHaveResourceVo> getResourceByRoleIdsOrRoleCodesOrAppId(@Param("roleIdsOrRoleCodes") List<String> roleIdsOrRoleCodes,
                                                                    @Param("appIds") List<String> appIds);


    /**
     * 根据系统代码获取资源信息
     * @param appIds  系统代码
     * @return  List<SysResourceForm>
     */
    List<SysResourceForm> getResourceInfoByAppId(@Param("appIds")List<String> appIds);


    /**
     * 根据类似0/aaa/vvv/的parentIdtrings查询菜单信息
     * @param parentIdStrings    0/aaa/vvv/这样的字符串结合
     * @param appId    系统代码
     * @return  List<SysResourceForm>
     */
    List<SysResourceForm> getResourceInfoByParentIdStrings(@Param("parentIdStrings") List<String> parentIdStrings,@Param("appId") String appId);

    /**
     * 获取用户创建的菜单
     * @param userId  用户Id
     * @return  getUserCreateResource
     */
    List<SysResourceForm> getUserCreateResource(@Param("userId") String userId);

}
