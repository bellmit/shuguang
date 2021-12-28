package com.sofn.fdpi.sysapi.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="非行政机构管理对象")
public class SysThirdOrgForm {

	@ApiModelProperty(value = "组织机构管理ID")
	private String id;

	/**
	 * 系统appid
	 */
	@ApiModelProperty(value = "系统appid")
	@NotBlank(message = "系统appid不能为空")
	private String appId;

	/**
	 * 组织机构名称
	 */
	@ApiModelProperty(value = "组织机构名称")
	@NotBlank(message = "组织机构名称不能为空")
	private String organizationName;

	/**
	 * 所在地址
	 */
	@ApiModelProperty(value = "所在地址")
	@NotBlank(message = "所在地址不能为空")
	private String address;

	/**
	 * 负责人
	 */
	@ApiModelProperty(value = "负责人")
	@Length(max = 25,message = "负责人超长")
	private String principal;

	/**
	 * 负责人电话
	 */
	@ApiModelProperty(value = "负责人电话")
	@Length(max = 12,message = "负责人电话超长")
	private String phone;
}
