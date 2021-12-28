package com.sofn.sys.service;

import com.sofn.sys.model.dgap.DgapSysUser;
import com.sofn.sys.model.dgap.SysUserBean;
import com.sofn.sys.persistence.Page;

import java.util.List;

/**
 * 共享系统用户service
 */
public interface SysUserProvider {
    /**
     * 通过用户id获取用户信息
     * @param id 用户id
     * @return
     */
    DgapSysUser queryById(String id);

    /**
     * 更新用户信息(共享系统修改邮箱和电话)
     * @param dgapSysUser
     * @return
     */
    DgapSysUser updateUser(DgapSysUser dgapSysUser);

    /**
     * 更新密码
     * @param account 用户
     * @param oldPassword
     * @param newPassword
     */
    Boolean changePwd(String account,String oldPassword,String newPassword, String token);
    /**
     * 获取某个用户id下同一机构下的人员总数
     * @param status 用户状态
     * @param userId 用户id
     * @return
     */
    int getOrgUserRecordsCountByUserId(String status,String userId);

    /**
     * 获取同一机构下的人员分页数据
     * @param page
     * @param status
     * @param userId
     * @return
     */
    List<SysUserBean> getOrgUserRecordsByUserId(Page page, String status, String userId);

}
