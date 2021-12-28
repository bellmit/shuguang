package com.sofn.ducss.sysapi.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
public class SysUserForm {

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
     * 角色IDs ,  逗号分隔
     */
    @ApiModelProperty("用户对应的角色ID(用,分隔)")
    private String roleIds;
    @ApiModelProperty("用户对应的角色组ID(用,分隔)")
    private String groupIds;
    @ApiModelProperty("用户对应的角色组ID(用,分隔),用于展示")
    private String groupNames;
    /**
     * 角色IDs ,  逗号分隔
     */
    @ApiModelProperty("用户对应的角色名(用,分隔),用于展示")
    private String roleNames;

    /**
     * 角色码
     */
    private Set<String> roleCodes;

    /**
     * 用户状态 启用、禁用
     */
    @ApiModelProperty("用户资源权限列表")
    private Set<String> resourceList;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("登录用户所属区划，只有末级")
    private String regionCode;

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

    // 创建人
    @ApiModelProperty(value = "创建人ID")
    private String createUserId;
    // 创建时间
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;
    // 更新人
    @ApiModelProperty(value = "修改人ID",hidden = true)
    private String updateUserId;
    // 更新时间
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date updateTime;


}
