package com.sofn.infroflow.model;

import com.cvicse.workflow.api.resources.User;

/**
 * @author heyongjie
 * @date 2020年4月28日14:43:51
 */
public class SysUser {

    /**
     * 用户唯一标识
     */
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobilePhone;

    /**
     * 描述
     */
    private String description;


    /**
     * 将系统用户信息适配到流程中的用户信息
     * @param sysUser
     * @return
     */
    public static User getUser(SysUser sysUser){
        if(sysUser == null){
            return null;
        }
        User user = new User();
        user.setId(sysUser.getUsername());
        user.setDescription(sysUser.getDescription());
        user.setName(sysUser.getNickname());
        user.setEmail(sysUser.getEmail());
        user.setMobilePhone(sysUser.getMobilePhone());
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
