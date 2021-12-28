package com.sofn.common.constants;

public class Constants {

    private Constants(){}

    /**
     * 用户登录成功后缓存的数据键
     */
    public static class UserSession {
        public static final String userId = "userId";
        public static final String userName = "userName";
        public static final String user = "user";
        public static final String userOrganizationId = "userOrganizationId";
        public static final String userOrgInfo = "userOrgInfo";
        public static final String userRoles = "userRoles";
        public static final String userGroups = "userGroups";
        public static final String permissions = "permissions";
        public static final String rememberMe = "rememberMe";
        public static final String mobilePlatform = "mobilePlatform";
        public static final String superAdminId = "0";
        public static final String userNameToken = "username_token";
        public static final String isSysUser = "isSysUser";
        // 用户有的系统ID
        public static final String USER_HAVA_SYSTEM_IDS = "userHaveSystemIds";
        // 用户有的系统代码集合
        public static final String USER_HAVA_SYSTEM_APPIDS = "userHaveSystemAppIds";
    }

    /**
     * 移动端平台
     */
    public static String[] mobilePlatform = new String[]{"android","ios"};

    /**
     * 角色有的权限的缓存
     * 存储的是Map<String,Set<String>>  结构
     */
    public static final String ROLE_HAVA_PERMISSION_CACHE = "ROLE_HAVA_PERMISSION_CACHE";

}
