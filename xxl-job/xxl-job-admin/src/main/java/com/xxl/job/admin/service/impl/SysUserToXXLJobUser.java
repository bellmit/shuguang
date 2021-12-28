package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.core.util.PinYinUtils;
import com.xxl.job.admin.dao.MaxIdDao;
import com.xxl.job.admin.dao.XxlJobUserDao;
import com.xxl.job.admin.sysapi.bean.SysUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * 支撑平台用户转为XXLJOB任务
 * @author heyongjie
 * @date 2019/12/12 11:18
 */
@Component
public class SysUserToXXLJobUser {

    @Autowired
    private XxlJobUserDao xxlJobUserDao;

    @Autowired
    private MaxIdDao maxIdDao;

    /**
     * 用户的默认密码
     */
    public final static String DEFAULT_PASSWORD = "123456";

    /**
     * 根据支撑平台的用户添加XXLJOB 用户
     * @param sysUserForm    支撑平台用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public XxlJobUser addXXlJobUserBySysUser(SysUserForm sysUserForm){
        XxlJobUser xxlJobUser = new XxlJobUser();
        Integer maxId = maxIdDao.selectMaxId("XXL_JOB_USER");
        xxlJobUser.setId(maxId);
        // 设置默认密码为123456
        xxlJobUser.setPassword(DigestUtils.md5DigestAsHex(DEFAULT_PASSWORD.getBytes()));
        xxlJobUser.setUsername(PinYinUtils.getHanziPinYin(sysUserForm.getUsername()));
        xxlJobUser.setPermission("");
        // 默认是超级管理员权限
        xxlJobUser.setRole(1);
        xxlJobUserDao.save(xxlJobUser);
        return xxlJobUser;
    }




}
