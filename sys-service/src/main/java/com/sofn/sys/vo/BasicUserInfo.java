package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 用户基本信息VO
 *
 * @author heyongjie
 * @date 2019/12/5 11:35
 */
@Data
@ApiModel(value = "用户基本信息VO")
public class BasicUserInfo {
    @Length(max = 32, message = "用户ID超长")
    private String id;

    @ApiModelProperty("昵称，必填")
    private String nickname;


    @ApiModelProperty("用户名-登录名，必填")
    private String username;

    @ApiModelProperty("用户初始密码")
    private String initPassword;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("用户状态")
    private String status;


    @ApiModelProperty("性别，1-男，2-女，3-未知")
    private Integer sex = 3;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("机构名称")
    private String orgName;

    @ApiModelProperty("机构ID")
    private String orgId;

    @ApiModelProperty("角色信息")
    private List<SysRoleVo> sysRoleVos;
}
