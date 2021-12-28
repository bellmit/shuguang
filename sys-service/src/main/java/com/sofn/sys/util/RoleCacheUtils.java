package com.sofn.sys.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sofn.common.constants.Constants;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.sys.enums.SysManageEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 *  角色权限缓存工具类  用于维护角色和权限的缓存 KEY为 Constants.ROLE_HAVA_PERMISSION_CACHE
 * @author heyongjie
 * @date 2020/1/13 11:10
 */
public class RoleCacheUtils {

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    /**
     * 添加权限的时候调用，用于更新超级管理员的权限
     * @param permission    需要添加的权限
     */
    public static void addPermission(String permission){
        List<String> idsByStr = getPermissionByRoleId(SysManageEnum.DEVELOPER_ROLE_ID.getCode());
        List<String> permissions = Lists.newArrayList(idsByStr);
        if(!idsByStr.contains(permission)){
            permissions.add(permission);
            Set<String> permissionSet = Sets.newHashSet(permissions);
            redisHelper.hset(Constants.ROLE_HAVA_PERMISSION_CACHE,SysManageEnum.DEVELOPER_ROLE_ID.getCode(), String.join(",", permissionSet));
        }
    }

    /**
     * 根据角色获取权限列表
     * @param roleId  角色ID
     * @return   List<String>
     */
    private static List<String> getPermissionByRoleId(String roleId){
        List<String> result = Lists.newArrayList();
        if(!StringUtils.isBlank(roleId)){
            String permissions = (String) redisHelper.hget(Constants.ROLE_HAVA_PERMISSION_CACHE,roleId);
            if(!StringUtils.isBlank(permissions)){
                result =  IdUtil.getIdsByStr(permissions);
            }
        }
        if(CollectionUtils.isEmpty(result)){
            result = Lists.newArrayList();
        }
        return result;
    }

    /**
     * 删除权限的时候调用，用于更新超级管理员权限
     * @param permission  需要删除的权限
     */
    public static void delPermission(String permission){
        if(!StringUtils.isBlank(permission)){
            List<String> permissions = getPermissionByRoleId(SysManageEnum.DEVELOPER_ROLE_ID.getCode());
            List<String> permissionList = Lists.newArrayList(permissions);
            permissionList.remove(permission);
            Set<String> permissionSet = Sets.newHashSet(permissionList);
            redisHelper.hset(Constants.ROLE_HAVA_PERMISSION_CACHE,SysManageEnum.DEVELOPER_ROLE_ID.getCode(), String.join(",", permissionSet));
        }
    }

    /**
     * 添加或者修改角色
     * @param roleId    角色ID
     * @param permission   权限列表
     */
    public static void addOrUpdateRole(String roleId,Set<String> permission){
        if(!StringUtils.isBlank(roleId) && !CollectionUtils.isEmpty(permission)){
            redisHelper.hset(Constants.ROLE_HAVA_PERMISSION_CACHE,roleId, String.join(",", permission));
        }
    }

    /**
     * 删除角色
     * @param roleId  角色ID
     */
    public static void delRole(String roleId){
        if(!StringUtils.isBlank(roleId)){
            redisHelper.hdel(Constants.ROLE_HAVA_PERMISSION_CACHE,roleId);
        }
    }



}
