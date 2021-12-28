package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
public class SysUserRoleCodeForm {

    @Length(max = 32,message = "用户ID超长")
    private String id;

    /**
     * 登录账号
     */
    @ApiModelProperty("昵称，必填")
    @NotBlank(message = "昵称必填")
    private String nickname;

    /**
     * 部门ID
     */
    @ApiModelProperty("组织机构ID，必填")
    @NotBlank(message = "用户必须属于一个组织机构")
    private String organizationId;

    /**
     * 部门名称
     */
    @ApiModelProperty("组织机构名")
    private String organizationName;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名，必填")
    @NotBlank(message = "用户名必填")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("用户初始密码")
    @Length(max = 18,message = "密码不能超过18位")
    private String initPassword;

    /**
     * 角色码
     */
    @ApiModelProperty("角色码集合，必填")
    @NotBlank(message = "角色码不能为空")
    private String roleCodes;

    /**
     * 系统appid
     */
    @ApiModelProperty("系统APPID，必填")
    @NotBlank(message = "APPID不能为空")
    private String appId;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 用户状态 启用、禁用
     */
    @ApiModelProperty("用户状态")
    private String status;

    @ApiModelProperty(value = "删除标识Y/N",hidden = true)
    private String delFlag;

    @ApiModelProperty("性别，1-男，2-女，3-未知")
    @Max(value = 3,message = "最大值3-未知")
    @Min(value = 1,message = "最小值1-男")
    private Integer sex = 3;

    @ApiModelProperty("备注")
    private String remark;
}
