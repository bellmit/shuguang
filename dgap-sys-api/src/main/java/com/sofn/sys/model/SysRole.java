package com.sofn.sys.model;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by sofn
 */

public class SysRole implements Serializable {


    private static final long serialVersionUID = 1L;

    // id
    private String id;
    // 创建人
    private String createUserId;
    // 创建时间

    private Date createTime;
    // 更新人
    private String updateUserId;
    // 更新时间

    private Date updateTime;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    private String roleName;

    /**
     * 角色描述,UI界面显示使用
     */
    private String describe;

    private String status;

    public SysRole(String roleName) {
        this.roleName = roleName;
    }
    public SysRole() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
