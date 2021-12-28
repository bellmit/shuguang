package com.sofn.ducss.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.*;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.*;
import com.sofn.ducss.vo.Excel.ImportSysUserVo;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

    /**
     * 执行登录
     *
     * @param loginVo 登录对象
     * @param isApp   如果不是App登录会进行验证码校验
     * @param type    因有的系统不走支撑平台的登录，而是使用系统内置的用户直接进行登录，需要加上类别，以免导致token被正常流程给识别，如share系统
     * @return 最终返回消息
     */
    Auth execuLogin(LoginVo loginVo, boolean isApp, String type);

    /**
     * 按条件查询用户
     *
     * @param params   条件
     * @param pageNo   页数
     * @param pageSize 每页显示多少条
     * @return
     */
    PageUtils<SysUserForm> findAllUserList(Map<String, Object> params, Integer pageNo, Integer pageSize);


    PageUtils<SysUserForm> findUserListBySubsystem(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 添加用户
     *
     * @param sysUserForm 传入的用户信息
     */
    String saveSysUser(SysUser sysUserForm);

    /**
     * 修改用户
     *
     * @param sysUserForm 修改的用户信息
     */
    void updateSysUser(SysUser sysUserForm);

    /**
     * 批量添加用户
     *
     * @param sysUsers 用户信息集合
     */
    void batchSave(List<SysUser> sysUsers);

    /**
     * 删除用户
     *
     * @param id 要删除用户ID
     */
    void deleteSysUser(String id);

    List<SysUserForm> getUserByCondition(Map<String, Object> params);

    /**
     * 批量删除用户
     *
     * @param ids 用户ID
     */
    void batchDelete(List<String> ids);

    /**
     * 更改密码
     *
     * @param id          ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String id, String oldPassword, String newPassword);

    void resetPwd(String id);

    /**
     * 加载用户角色列表，会携带当前用户创建的角色
     *
     * @param id 角色ID
     * @return List<SysRole>
     */
    List<SysRole> loadRolesByUserId(String id);

    /**
     * 批量添加Excel导入的数据
     *
     * @param userList Excel填写的数据
     */
    void batchInsert(List<ImportSysUserVo> userList);

    /**
     * 根据组织结构id获取用户列表
     *
     * @param orgId     组织 机构id
     * @param proxyUser 是否包含代理机构  Y 包含 N不包含
     * @return 用户列表
     */
    List<SysUser> getUserListByOrgId(String orgId, String proxyUser);

    /**
     * 根据ID集合批量查询用户信息
     *
     * @param ids IDS
     * @return List<SysUserForm>
     */
    List<SysUserForm> getUserInfoByIds(List<String> ids);

    /**
     * 修改用户信息
     *
     * @param updateUserInfoVo 更改后的用户信息
     */
    void updateUserInfo(UpdateUserInfoVo updateUserInfoVo);

    /**
     * 按条件查询用户信息
     *
     * @param selectUserConditionVo 用户信息
     * @param pageNo                开始也是
     * @param pageSize              每页显示多少条
     * @return PageUtils<SysUserForm>
     */
    PageUtils<BasicUserInfo> getBasicUserInfo(SelectUserConditionVo selectUserConditionVo, Integer pageNo, Integer pageSize);

    PageUtils<BasicUserInfo> getBasicUserInfoNew(SelectUserConditionVo selectUserConditionVo, Integer pageNo, Integer pageSize);

    /**
     * 检测用户名是否存在
     *
     * @param id       用户id
     * @param username 用户名
     * @return boolean
     */
    boolean checkUsernameExist(String id, String username);

    /**
     * 获取导出用户信息
     *
     * @param params 导出参数
     * @return List<ImportSysUserVo>
     */
    List<ImportSysUserVo> getExportUserInfo(Map<String, String> params);

    /**
     * 获取用户有的用户信息
     *
     * @param params roleId  subsystemId  username  nickname
     * @return PageUtils<List < BasicUserInfo>>
     */
    PageUtils<List<BasicUserInfo>> getRoleHaveUserInfo(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 获取用户没有的用户信息
     *
     * @param params roleId  subsystemId  username  nickname
     * @return PageUtils<List < BasicUserInfo>>
     */
    PageUtils<List<BasicUserInfo>> getRoleDontHaveUserInfo(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 获取所有的用户
     *
     * @return List<BasicUserInfo>
     */
    List<BasicUserInfo> getAllUser();

    /**
     * 机构下是否有用户
     *
     * @param orgId
     * @return
     */
    boolean hasUserByOrgId(String orgId);

    /**
     * 获取当前登录用户归属的系统APPID
     *
     * @return
     */
    List<String> getLoginUserBelongSystem();

    /**
     * 根据用户名称查询用户信息
     *
     * @param username
     * @return
     */
    SysUserForm getUserInfoByUsername(String username);


    /**
     * 根据区划代码和系统代码获取用户信息
     *
     * @param regionCode 区划代码
     * @param appId      用户信息
     * @return List<SysUserForm>
     */
    List<SysUserForm> getUserInfoByRegionCodeAndAppId(String regionCode, String appId);


    /**
     * 根据区划代码和系统代码获取用户信息
     *
     * @param regionCodes       区划代码
     * @param appId             用户信息
     * @param Level             机构级别
     * @param includeRoleAndOrg 是否包含机构及角色信息
     * @return List<SysUserForm>
     */
    List<SysUserForm> getUserByOrgInfoAndAppId(List<String> regionCodes, String appId, String Level, boolean includeRoleAndOrg);

    /**
     * 根据token获取用户常用子系统列表
     *
     * @param userId
     * @return List<SysUserSystem>
     */
    List<SysUserSystem> getUserUseSystemList(String userId);

    /**
     * 根据token,和systemId新增用户常用子系统列表
     *
     * @param userId
     * @param systemId
     * @return List<SysUserSystem>
     */
    String addUserSystem(String userId, String systemId);

    List<SysUser> findByUserIds(Map<String, Object> map);
}
