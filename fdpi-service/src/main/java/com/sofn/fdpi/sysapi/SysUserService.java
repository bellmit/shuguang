//package com.sofn.fdpi.sysapi;
//
//import com.sofn.fdpi.sysapi.bean.SysOrganizationInfoBean;
//import com.sofn.fdpi.sysapi.bean.SysUserBean;
//
//import java.util.Set;
//
///**
// * 系统用户相关  这里需要通过调用接口的方式获取值
// * @author heyongjie
// * @date 2019/8/15 10:51
// */
//public interface SysUserService {
//
//    /**
//     * 获取登录用户ID
//     * @return  当前登录用户ID
//     */
//    String getLoginUserId();
//
//
//    /**
//     * 根据用户ID获取当前用户有的角色
//     * @param userId  用户ID
//     * @return Set<String>
//     */
//    Set<String> getRoleIdsByUserId(String userId);
//
//    /**
//     * 检查当前用户是否有这个角色
//     * @param userId  用户ID
//     * @param roleIds  角色ID
//     * @return true 有
//     */
//    boolean checkRoleHasUser(String userId, String roleIds);
//
//    /**
//     * 获取登录用户有的角色
//     * @return  Set<String> 登录用户有的角色
//     */
//    Set<String> getLoginUserHasRole();
//
//    /**
//     * 获取当前登录用户信息
//     * @param userId  如果传入获取当前登录用户信息 如果没有传入直接获取当前登录用户信息
//     * @return  当前登录用户的机构角色等信息
//     */
//    SysUserBean getLoginUserInfo(String userId);
//
//    /**
//     * 获取父机构信息
//     * @param organizationId    机构ID
//     * @param organizationLevel  父机构的级别信息
//     * @return  SysOrganizationInfoBean  机构信息
//     */
//    SysOrganizationInfoBean getParentOrganizationInfo(String organizationId, String organizationLevel);
//
//    /**
//     * 根据机构ID 获取机构名称
//     * @param organizationId  机构ID
//     * @return  机构名称
//     */
//    String getOrganizationName(String organizationId);
//
//    /**
//     * 获取字典描述
//     * @param dictType   字典类型
//     * @param dictId    字典ID
//     * @return   字典ID 对应的中文描述
//     */
//    String getDictDescription(String dictType, String dictId);
//}
