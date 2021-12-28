package com.sofn.core.constant;


import com.sofn.core.base.BaseModel;

import java.util.Date;

/**
 * Created by: dong4j.
 * Date: 2016-09-27.
 * Time: 09:43.
 * Description:
 */
@SuppressWarnings("serial")
public class CurrentUser extends BaseModel {
    private String account;

    private String password;

    private String userName;

    private String deptId;

    private String roleId;

    private String postId;

    private String phone;

    private String email;

    private String status;

    private Organization organization;

    private String organizationId;

    private String orgName;

    private String orgType;

    private String orgLevel;

    private String regionName;

    private String typeId;

    private String levelId;

    private String regionId;

    private String orgId;
    private String userType;

    private Date passwordValidatePeriod;

    public Date getPasswordValidatePeriod() {
        return passwordValidatePeriod;
    }

    public void setPasswordValidatePeriod(Date passwordValidatePeriod) {
        this.passwordValidatePeriod = passwordValidatePeriod;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel == null ? null : orgLevel.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getAccount() {
        return account;
    }

    public CurrentUser setAccount(String account) {
        this.account = account == null ? null : account.trim();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public CurrentUser setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
        return this;
    }

    public String getDeptId() {
        return deptId;
    }

    public CurrentUser setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public CurrentUser setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
        return this;
    }

    public String getPostId() {
        return postId;
    }

    public CurrentUser setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CurrentUser setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CurrentUser setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CurrentUser setStatus(String status) {
        this.status = status == null ? null : status.trim();
        return this;
    }

    public Organization getOrganization (){
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrentUser{");
        sb.append("account='").append(account).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", deptId='").append(deptId).append('\'');
        sb.append(", roleId='").append(roleId).append('\'');
        sb.append(", postId='").append(postId).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", organization=").append(organization);
        sb.append(", organizationId='").append(organizationId).append('\'');
        sb.append(", orgName='").append(orgName).append('\'');
        sb.append(", orgType='").append(orgType).append('\'');
        sb.append(", orgLevel='").append(orgLevel).append('\'');
        sb.append(", regionName='").append(regionName).append('\'');
        sb.append(", typeId='").append(typeId).append('\'');
        sb.append(", levelId='").append(levelId).append('\'');
        sb.append(", regionId='").append(regionId).append('\'');
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", userType='").append(userType).append('\'');
        sb.append(", passwordValidatePeriod=").append(passwordValidatePeriod);
        sb.append(", delFlag='").append(getDelFlag()).append('\'');
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", remark='").append(getRemark()).append('\'');
        sb.append(", createBy='").append(getCreateBy()).append('\'');
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateBy='").append(getUpdateBy()).append('\'');
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append('}');
        return sb.toString();
    }
}
