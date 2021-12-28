package com.sofn.infroflow.resources;

import com.cvicse.workflow.api.resources.*;
import com.sofn.infroflow.dao.SysOrgDao;
import com.sofn.infroflow.dao.SysRoleDao;
import com.sofn.infroflow.dao.SysUserDao;
import com.sofn.infroflow.model.SysOrg;
import com.sofn.infroflow.model.SysRole;
import com.sofn.infroflow.model.SysUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * InforFlow 用户角色机构资源类
 *
 * @author heyongjie
 * @date 2020年4月28日13:58:31
 */
public class SysResourceProvider extends AbstractResourceProvider {

    private static final long serialVersionUID = 1L;

    private SysUserDao sysUserDao = new SysUserDao();

    private SysRoleDao sysRoleDao = new SysRoleDao();

    private SysOrgDao sysOrgDao = new SysOrgDao();


    /**
     * 返回指定执行人标示号的执行人对象
     *
     * @param id
     *            执行人标示号
     *
     * @return User 执行人对象
     */
    @Override
    public User getUser(String id) {
        SysUser sysUser = sysUserDao.getUserById(id);
        return SysUser.getUser(sysUser);
    }


    /**
     * 将List<SysUser> 转换成User[]
     * @param users   List<SysUser>
     * @return User[]
     */
    private User[] getUsers(List<SysUser> users) {
    	if(users != null && users.size() > 0) {
    		 List<User> collect = new ArrayList<>();
             for (SysUser user : users) {
                 User user1 = SysUser.getUser(user);
                 collect.add(user1);
             }
             return collect.toArray(new User[]{});
    	}
        return new User[]{};
    }

    /**
     * 返回指定角色和机构代码的执行人对象数组
     *
     * @param roleId
     *            角色标示号
     *
     * @param orgnizationUnitId
     *            机构代码
     *
     * @return User[] 执行人对象数组
     */
    @Override
    public User[] getUsers(String roleId, String orgnizationUnitId) {
        List<SysUser> users = sysUserDao.getUsers(roleId, orgnizationUnitId);
        return  getUsers(users);
    }


    /**
     * 返回系统所有执行人对象数组
     *
     * @return User[] 执行人对象数组
     */
    @Override
    public User[] getUsers() {
        List<SysUser> allUser = sysUserDao.getAllUser();
        return getUsers(allUser);
    }


    /**
     * 返回指定角色的执行人对象数组
     *
     * @param roleId
     *            角色标示号
     *
     * @return User[] 执行人对象数组
     */
    @Override
    public User[] getUsers(String roleId) {
        List<SysUser> users = sysUserDao.getUsers(roleId,"");
        return getUsers(users);
    }

    private Role[] getRole(List<SysRole> users) {
    	if(users != null && users.size() > 0) {
    		   List<Role> collect = new ArrayList<>();
               for (SysRole role : users) {
                   Role role1 = SysRole.getRole(role);
                   collect.add(role1);
               }
               return collect.toArray(new Role[]{});
    	}
    	
        return new Role[]{};
    }

    /**
     * 返回系统所有角色对象数组
     *
     * @return Role[] 角色对象数组
     */
    @Override
    public Role[] getRoles() {
        List<SysRole> allRole = sysRoleDao.getAllRole();
        return getRole(allRole);
    }

    /**
     * 获取执行人拥有的所有扩展属性定义。
     *
     * @return Map对象，key为属性名称，value为属性类型。目前支持的属性类型有： java.lang.String
     *         java.lang.Integer java.lang.Float java.util.Timestamp
     */
    @Override
    public Map getWorkerExtAttrs() {
        return null;
    }

    /**
     * 返回指定资源标示号的资源对象
     *
     * @param id
     *            资源标示号
     *
     * @return Resource 资源对象
     */
    @Override
    public Resource getResource(String id) {
        return EngineResource.getResourceById(id);
    }

    /**
     * 返回指定角色和机构代码的资源对象数组
     *
     * @param roleId
     *            角色标示号
     *
     * @param orgnizationUnitId
     *            机构代码
     *
     * @return Resource[] 资源对象数组
     */
    @Override
    public Resource[] getResources(String roleId, String orgnizationUnitId) {
        return EngineResource.getResources();
    }

    /**
     * 返回系统所有资源对象的数组
     *
     * @return Resource[] 资源对象数组
     */
    @Override
    public Resource[] getResources() {
        return EngineResource.getResources();
    }

    private OrgnizationUnit[] getOrgs(List<SysOrg> users) {
    	if(users != null && users.size() > 0) {
    		   List<OrgnizationUnit> collect = new ArrayList<>();
               for (SysOrg org : users) {
                   OrgnizationUnit org1 = SysOrg.getOrg(org);
                   collect.add(org1);
               }
               return collect.toArray(new OrgnizationUnit[]{});
    	}
    
        return new OrgnizationUnit[]{};
    }

    /**
     * 返回系统所有组织单位对象的数组
     *
     * @return OrgnizationUnit[] 组织单位对象数组
     */
    @Override
    public OrgnizationUnit[] getOrgnizationUnits() {
        List<SysOrg> allOrg = sysOrgDao.getAllOrg();
        return getOrgs(allOrg);
    }

    /**
     * 返回指定用户归属角色数组
     * 如果角色有优先级或存在默认角色(主角色),需要把默认角色放在数组第一项
     * @param userId
     * @return Role
     */
    @Override
    public Role[] getRolesByUserId(String userId) {
        List<SysRole> roleByUserId = sysRoleDao.getRoleByUserId(userId);
        return getRole(roleByUserId);
    }

    /**
     * 获取父级机构
     * @param orgid 机构编码
     * @return 父级机构编码 如果没有父级或本身就是父级返回空值""
     */
    @Override
    public String getParentOrgnization(String orgid) {
        SysOrg parentIdByParentId = sysOrgDao.getParentIdByParentId(orgid);
        if(parentIdByParentId == null){
            return "";
        }else{
            return parentIdByParentId.getId();
        }
    }
    
    public static void main(String[] args) {
    	SysResourceProvider sys = new SysResourceProvider();
    	 OrgnizationUnit[]  or = sys.getOrgnizationUnits();
    	
    	System.out.print(or);
    }


}
