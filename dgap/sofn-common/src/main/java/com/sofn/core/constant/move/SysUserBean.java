package com.sofn.core.constant.move;

import com.sofn.core.validation.First;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class SysUserBean extends SysUser {
	//后台向前台
//	private String deptName;
	private String orgName;
	private String orgType;
	private String roleName;
	private String postName;
	private String statusName;
	//前台向后台
	@NotBlank(groups = {First.class}, message = "角色IDcsv字符串不能为空")
	private String roleStr;

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr == null ? null : roleStr.trim();
	}
/*
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}*/

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public SysUserBean(){}
	public SysUserBean(SysUser user){
		super.setOrganizationId(user.getOrganizationId());
		super.setAccount(user.getAccount());
		super.setDelFlag(user.getDelFlag());
		super.setDeptId(user.getDeptId());
		super.setEmail(user.getEmail());
		super.setPassword(user.getPassword());
		super.setPhone(user.getPhone());
		super.setPostId(user.getPostId());
		super.setReservedField1(user.getReservedField1());
		super.setReservedField2(user.getReservedField2());
		super.setRoleId(user.getRoleId());
		super.setStatus(user.getStatus());
		super.setUserName(user.getUserName());
		super.setCreateBy(user.getCreateBy());
		super.setCreateTime(user.getCreateTime());
		super.setId(user.getId());
		super.setRemark(user.getRemark());
		super.setUpdateBy(user.getUpdateBy());
		super.setUpdateTime(user.getUpdateTime());
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SysUserBean{");
		sb.append("orgName='").append(orgName).append('\'');
		sb.append(", orgType='").append(orgType).append('\'');
		sb.append(", roleName='").append(roleName).append('\'');
		sb.append(", postName='").append(postName).append('\'');
		sb.append(", statusName='").append(statusName).append('\'');
		sb.append(", roleStr='").append(roleStr).append('\'');
		sb.append(", account='").append(getAccount()).append('\'');
		sb.append(", password='").append(getPassword()).append('\'');
		sb.append(", userName='").append(getUserName()).append('\'');
		sb.append(", deptId='").append(getDeptId()).append('\'');
		sb.append(", organizationId='").append(getOrganizationId()).append('\'');
		sb.append(", userType='").append(getUserType()).append('\'');
		sb.append(", roleId='").append(getRoleId()).append('\'');
		sb.append(", postId='").append(getPostId()).append('\'');
		sb.append(", phone='").append(getPhone()).append('\'');
		sb.append(", email='").append(getEmail()).append('\'');
		sb.append(", status='").append(getStatus()).append('\'');
		sb.append(", delFlag='").append(getDelFlag()).append('\'');
		sb.append(", reservedField1='").append(getReservedField1()).append('\'');
		sb.append(", reservedField2='").append(getReservedField2()).append('\'');
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
