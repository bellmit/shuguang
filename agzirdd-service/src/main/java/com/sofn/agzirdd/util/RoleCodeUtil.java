package com.sofn.agzirdd.util;

import com.sofn.common.utils.UserUtil;

import java.util.List;

/**
 * 权限码工具
 */
public class RoleCodeUtil {

    /**
     * 获取登录用户，当前子系统的第一个权限码
     * @return
     */
    public static String getLoginUserAgzirddRoleCode() {
        List<String> roleList = UserUtil.getLoginUserRoleCodeList();
        String res = "";
        for (String role : roleList) {
            if(role.indexOf("agzirdd")!=-1){
                res = role;
                break;
            }
        }
        return res;
    }
}
