package com.sofn.service.dgap;

import com.sofn.core.constant.*;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.sys.model.SysRole;
import com.sofn.model.generator.SysDictData;
import com.sofn.sys.model.dgap.DgapSysUser;
import com.sofn.sys.model.dgap.SysUserBean;
import com.sofn.sys.persistence.Page;
import com.sofn.sys.service.SysRoleProvider;
import com.sofn.sys.service.SysUserProvider;
import com.sofn.sys.service.SysUserRoleProvider;
import com.sofn.provider.sso.SSOLoginProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("outerApiService")
public class OuterApiService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前登录用户的id
     *
     * @return 当前登录用户的id
     */
    public String getLoginedUserId(String token) {
        logger.debug("token：" + token);
        String currentUserId = WebUtil.getCurrentUserId(token);
        logger.debug("获取登录用户的ID，currentUserId:" + currentUserId);
        return currentUserId;
    }

    /**
     * 获取当前登录用户
     *
     * @param token
     * @return
     */
    public CurrentUser getCurrentUser(String token) {
        logger.debug("token：" + token);
        CurrentUser u = WebUtil.getCurrentUser(token);
        String user = objectString(u);
        logger.debug("当前登录用户信息,CurrentUser:" + user);
        return u;
    }

    /**
     * 获取资源类型
     *
     * @return 资源类型的列表
     */
    public List<ListItem> getResourceTypes() {
        List<ListItem> list = new ArrayList<>();
        Constants.ResourceType[] resourceTypes = Constants.ResourceType.values();
        for (int i = 0; i < resourceTypes.length; i++) {
            String resourceName = resourceTypes[i].name();
            String resourceCode = resourceTypes[i].code();
            if (resourceTypes[i].isAvailable()) {
                list.add(new ListItem(resourceCode, resourceName));
            }
        }
        logger.debug("资源类型的列表," + listObjectString(list));
        return list;
    }

    /**
     * 资源申请的审批状态
     */
    public List<ListItem> ResourceApplicationPassOrNot() {
        List<ListItem> list = new ArrayList<ListItem>();
        for (Constants.ResourceApplicationPassOrNot rt :
                Constants.ResourceApplicationPassOrNot.values()) {
            list.add(new ListItem(rt.code(), rt.name()));
        }
        logger.debug("资源申请的审批状态," + listObjectString(list));
        return list;
    }

    /**
     * 获取资源状态
     *
     * @return 资源状态的列表
     */
    public List<ListItem> getResourceStatus() {
        List<ListItem> list = new ArrayList<ListItem>();
        for (Constants.ResourceStatus rt : Constants.ResourceStatus.values()) {
            list.add(new ListItem(rt.code(), rt.name()));
        }
        logger.debug("资源状态的列表," + listObjectString(list));
        return list;
    }

    /**
     * 获取WS错误类型
     *
     * @return WS错误类型的列表
     */
    public List<ListItem> getWsErrorTypes() {
        List<ListItem> list = new ArrayList<ListItem>();
        for (Constants.WSErrorType rt : Constants.WSErrorType.values()) {
            list.add(new ListItem(rt.getErrorCode(), rt.getErrorOutDesc()));
        }
        logger.debug("WS错误类型的列表," + listObjectString(list));
        return list;
    }


    /**
     * 将对象转化为字符串
     *
     * @param object
     * @return
     */
    public String objectString(Object object) {
        if (object instanceof String) {
            return object.toString();
        } else {
            ReflectionToStringBuilder reflectionToStringBuilder = new ReflectionToStringBuilder(object);
            return reflectionToStringBuilder.toString(object);
        }
    }

    /**
     * 将List泛型转化为字符串
     *
     * @param list
     * @return
     */
    public String listObjectString(List list) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object ob : list) {
            if (ob instanceof String) {
                sb.append("List[" + i + "]:");
                sb.append(ob);

            } else {
                sb.append("List[" + i + "]:");
                sb.append(this.objectString(ob));
            }
            i++;
        }
        return sb.toString();
    }


    /**
     * 外部接口返回的实体类
     */
    public static class ListItem {
        private String id;
        private String name;

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

        public ListItem() {
        }

        public ListItem(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Role {
        String id;
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Role(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Role() {
        }
    }

    public static class User {
        private String id;
        private String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public User() {
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
    }





    //----------------------------------- start ------------------------------
    private SysUserProvider sysUserProvider;
    private SysRoleProvider sysRoleProvider;
    private SysUserRoleProvider sysUserRoleProvider;
    private SSOLoginProvider ssoLoginProvider;

    @DubboReference
    public void setProvider(SSOLoginProvider ssoLoginProvider) {
        this.ssoLoginProvider = ssoLoginProvider;
    }

    @DubboReference
    public void setSysUserRoleProvider(SysUserRoleProvider sysUserRoleProvider) {
        this.sysUserRoleProvider = sysUserRoleProvider;
    }

    @DubboReference
    public void setSysUserProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setSysRoleProvider(SysRoleProvider sysRoleProvider) {
        this.sysRoleProvider = sysRoleProvider;
    }


    //$$$$$$$$$$$$$$$$$$$$  TODO USER
    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    public Object getUserInfo(String id) {
        DgapSysUser sysUser = sysUserProvider.queryById(id);
        return sysUser;
    }

    /**
     * 获取同一机构下的人员总数
     *
     * @param status
     * @param orgId
     * @return
     */
    public int getUserCount(String status, String orgId) {
        int totalNew = sysUserProvider.getOrgUserRecordsCountByUserId(status, orgId);
        logger.debug("获取同一机构下的人员总数" + totalNew);
        return totalNew;
    }

    /**
     * 获取同一机构下的人员分页数据
     *
     * @param page
     * @param status
     * @param orgId
     * @throws Exception
     */
    public List<SysUserBean> getUsersOfSameOrganization(Page page, String status, String orgId)
            throws Exception {
        List<SysUserBean> sysUserListNew =
                sysUserProvider.getOrgUserRecordsByUserId(page, status, orgId);
        logger.debug("获取同一机构下的人员分页数据" + listObjectString(sysUserListNew));
        return sysUserListNew;
    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     */
    public DgapSysUser changeInfo(DgapSysUser sysUser) {
        DgapSysUser oldInfo = sysUserProvider.queryById(sysUser.getId());
        if (oldInfo != null) {
            oldInfo.setPhone(sysUser.getPhone());
            oldInfo.setEmail(sysUser.getEmail());
            oldInfo.setUpdateBy(sysUser.getId());
            oldInfo.setUpdateTime(new Date());
            return sysUserProvider.updateUser(oldInfo);
        } else return null;
    }

    public Boolean changePwd(String account, String oldPwd, String newPwd, String token) {
        return sysUserProvider.changePwd(account, oldPwd, newPwd, token);
    }




    //$$$$$$$$$$$$$$$$$$$$  TODO ROLE
    /**
     * 获取与当前登录用户同一机构下面的所有角色列表
     *
     * @param userId
     * @param
     * @return
     */
    public List<SysRole> getRolesOfSameDepartment(String userId) {
        DgapSysUser sysUser = sysUserProvider.queryById(userId);
        //List<SysRole> roleByUserId = sysRoleProvider.getOrgRoleByUserId(status, userId);
        String orgId = sysUser.getOrganizationId();
        List<SysRole> orgRoleByOrgId = sysRoleProvider.getOrgRoleByOrgId(orgId);
        logger.debug("获取与当前登录用户同一机构下面的所有角色列表,:" + listObjectString(orgRoleByOrgId));
        return orgRoleByOrgId;

    }

    /**
     * 角色不分页数据
     *
     * @param param
     * @return
     */
    public List<SysRole> getRoleList(Map<String, Object> param) {

        List<SysRole> sysRoles = sysRoleProvider.getSysRoleAllList(param);
        logger.debug("角色列表不分页数据" + sysRoles);
        return sysRoles;
    }


    //$$$$$$$$$$$$$$$$$$$$  TODO USERROLE
    /**
     * 获取指定用户的拥有的角色的id列表
     *
     * @param userId 指定用户的id
     * @return 角色id的列表
     */
    public List<String> getRoleByUserId(String userId) {
        logger.debug("userId" + userId);
        List<String> roleIds = sysUserRoleProvider.getRoleIdsByUserId(userId);
        logger.debug("角色id的列表,ids" + listObjectString(roleIds));
        return roleIds;
    }


    /**
     * 获取用户职位信息
     *
     * @param dictType
     * @return
     */
    public List<SysDictData> getPostType(DictType dictType) {
        return new ArrayList<>();
    }


    //$$$$$$$$$$$$$$$$$$$$  TODO LOGIN
    /**
     * Login map.
     *
     * @param account  the account
     * @param password the password
     * @return the map
     */
    public Map<String, Object> login(String account, String password) {
        return ssoLoginProvider.login(account, password);
    }

    /**
     * Logout.
     *
     * @param token the token
     * @param type  the type
     */
    public void logout(String token, String type) {
        ssoLoginProvider.logout(token, type);
    }

    /**
     * Gets sys user. Cacheable 缓存测试 如果缓存中有数据 则不会走 db 然后将数据放入缓存中
     *
     * @param id the id
     * @return the sys user
     */
    @Cacheable(value = "default", key = "'queryById_id_'+#id")
    public CurrentUser getSysUser(String id) {
        logger.info("real query SysUser. {}" + id);
        return ssoLoginProvider.queryById(id);
    }

    /**
     * Delete by primary key sys user. 缓存测试
     *
     * @param id   the id
     * @param type the type
     * @return the sys user
     */
    @CacheEvict(value = "default", key = "'delete_id_'+#id")
    public String deleteByPrimaryKey(String id, String type) {
        ssoLoginProvider.delete(id, "admin");
        return "return cache";
    }

    /**
     * 事务测试
     */
    public Integer register(CurrentUser userModel) {
        return ssoLoginProvider.register(userModel);
    }


    /**
     * Insert selective sys user. CachePut 缓存测试 如果参数与缓存不一致,则更新数据库,并且更新缓存
     *
     * @param user the user
     * @return the sys user
     */
    @CachePut(value = "default", key = "#user.getUserName()")
    public CurrentUser insertSelective(CurrentUser user) {
        logger.info("real insertSelective SysUser. {}" + user);
        return ssoLoginProvider.update(user);
    }

    //----------------------------------- end ------------------------------

}
