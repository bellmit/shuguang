package com.sofn.flow.util;

import com.sofn.infroflow.dao.SysRoleDao;
import com.sofn.infroflow.dao.SysUserDao;
import com.sofn.infroflow.model.SysRole;
import com.sofn.infroflow.model.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户工具
 * @author heyongjie
 * @date 2020/5/21 14:20
 */
public class UserUtil {

    /**
     * 根据用户名称获取用户信息
     * @param usernames  用户名称
     * @return  用户信息
     */
    public static List<SysUser> getUserInfoByUserNames(Set<String> usernames){
        if(CollectionUtils.isEmpty(usernames)){
            return new ArrayList<>();
        }
        SysUserDao sysUserDao = new SysUserDao();
        return sysUserDao.getUserByUsernames(usernames);
    }


    /**
     * 获取用户的昵称
     * @param username   用户的名称
     * @param sysUsers    用户列表集合
     * @return  用户昵称
     */
    public static String getUserNickName(String username, List<SysUser> sysUsers){
        if(StringUtils.isBlank(username) || CollectionUtils.isEmpty(sysUsers)){
            return "";
        }
        for (SysUser sysUser : sysUsers) {
            if(sysUser != null){
                if(sysUser.getUsername().equals(username)){
                    return sysUser.getNickname();
                }
            }
        }
        return username;
    }

    /**
     * 获取用户的角色
     * @param username   用户的名称
     * @return  用户的角色列表
     */
    public static List<String> getUserRoles(String username){
        List<String> userRoles = new ArrayList<>();
        if(StringUtils.isBlank(username)){
            return userRoles;
        }
        SysRoleDao sysRoleDao = new SysRoleDao();
        List<SysRole> roleByUserId = sysRoleDao.getRoleByUserId(username);
        if(CollectionUtils.isEmpty(roleByUserId)){
            return userRoles;
        }
        for (SysRole sysRole : roleByUserId) {
            userRoles.add(sysRole.getRoleCode());
        }
        return userRoles;
    }

}
