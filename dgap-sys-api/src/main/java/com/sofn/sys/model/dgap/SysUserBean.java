package com.sofn.sys.model.dgap;

import com.sofn.sys.validation.First;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 共享系统的SysUserBean
 */
@SuppressWarnings("serial")
@Data
public class SysUserBean extends DgapSysUser {
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


//	private String userType;
	private String deptId;
	private String roleId;
	private String postId;
}
