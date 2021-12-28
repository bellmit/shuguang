package com.sofn.agsjsi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称必填")
    private String nickname;

    /**
     * 部门ID
     */
    @ApiModelProperty("组织机构ID")
    @NotBlank(message = "用户必须属于一个组织机构")
    private String organizationId;

    /**
     * 部门名称
     */
    @ApiModelProperty("组织机构名")
    @NotBlank(message = "用户必须属于一个组织机构")
    private String organizationName;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名必填")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("用户初始密码")
    @Length(max = 18,message = "密码不能超过18位")
    private String initPassword;

    /**
     * 加密盐值
     */
    @ApiModelProperty("加密盐值")
    @JSONField(serialize = false)
    private String salt;

    /**
     * 角色IDs ,  逗号分隔
     */
    @ApiModelProperty("用户对应的角色ID(用\",\"分隔)")
    @NotBlank(message = "角色信息必填")
    private String roleIds;

    /**
     * 角色IDs ,  逗号分隔
     */
    @ApiModelProperty("用户对应的角色名(用\",\"分隔)")
    @NotBlank(message = "角色信息必填")
    private String roleNames;

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

    @ApiModelProperty("删除标识Y/N")
    private String delFlag;

    // 创建人
    @ApiModelProperty("创建人ID")
    private String createUserId;
    // 创建时间
    @ApiModelProperty("创建时间")
    private Date createTime;
    // 更新人
    @ApiModelProperty("修改人ID")
    private String updateUserId;
    // 更新时间
    @ApiModelProperty("修改时间")
    private Date updateTime;


    public SysUserForm() {}


}
