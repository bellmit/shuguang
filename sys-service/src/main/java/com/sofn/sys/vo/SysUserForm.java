package com.sofn.sys.vo;

import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.model.SysRoleGroup;
import com.sofn.sys.model.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
public class SysUserForm  {

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

    @ApiModelProperty("登录用户所属区划完整名称")
    private String regionNames;

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
    // 判断是否为首次修改密码
    @ApiModelProperty(value = "首次修改密码",hidden = true)
    private Integer updated;


    /**
     * 将VO 转换为 Model
     * @param sysUserForm SysUserForm  VO
     * @return SysUser
     */
    public static SysUser getSysUser(SysUserForm sysUserForm) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserForm, sysUser);
        sysUser.setSex(sysUserForm.getSex());
        //创建用户时，设置一个默认密码
         String defaultPassword = SysConstant.DEFAULT_PWD;
        //sysUser.setInitPassword(sysUserForm.getInitPassword());
        sysUser.setInitPassword(defaultPassword);
        //首次创建用户时，将修改是否修改过密码状态设置为0  0表示“未修改”  1表示”已修改“
        Integer updated = SysConstant.UPDATED_0;
        sysUser.setUpdated(updated);

        if (!StringUtils.isEmpty(sysUserForm.getRoleIds()) ) {
            String[] roleIds = sysUserForm.getRoleIds().split(",");
            List<SysRole> roleList = new ArrayList<>();
            for (String roleId : roleIds) {
                SysRole sysRole = new SysRole();
                sysRole.setId(roleId);
                roleList.add(sysRole);
            }
            sysUser.setRoleList(roleList);
        }
        if (!StringUtils.isEmpty(sysUserForm.getGroupIds()) ) {
            String[] groupIds = sysUserForm.getGroupIds().split(",");
            List<SysRoleGroup> groupList = new ArrayList<>();
            for(String groupId:groupIds){
                SysRoleGroup sysRoleGroup = new SysRoleGroup();
                sysRoleGroup.setId(groupId);
                groupList.add(sysRoleGroup);
            }
            sysUser.setGroupList(groupList);
        }
        return sysUser;
    }
    /**
     * 将VO 转换为 Model
     * @param sysUserForm SysUserForm  VO
     * @return SysUser
     */
    public static SysUser getSysUserforEdit(SysUserForm sysUserForm) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserForm, sysUser);
        sysUser.setSex(sysUserForm.getSex());
        //sysUser.setInitPassword(sysUserForm.getInitPassword());
        if (!StringUtils.isEmpty(sysUserForm.getRoleIds()) ) {
            String[] roleIds = sysUserForm.getRoleIds().split(",");
            List<SysRole> roleList = new ArrayList<>();
            for (String roleId : roleIds) {
                SysRole sysRole = new SysRole();
                sysRole.setId(roleId);
                roleList.add(sysRole);
            }
            sysUser.setRoleList(roleList);
        }
        if (!StringUtils.isEmpty(sysUserForm.getGroupIds()) ) {
            String[] groupIds = sysUserForm.getGroupIds().split(",");
            List<SysRoleGroup> groupList = new ArrayList<>();
            for(String groupId:groupIds){
                SysRoleGroup sysRoleGroup = new SysRoleGroup();
                sysRoleGroup.setId(groupId);
                groupList.add(sysRoleGroup);
            }
            sysUser.setGroupList(groupList);
        }
        return sysUser;
    }

    public SysUserForm() {}

    public SysUserForm(SysUser user) {
        this.id = user.getId();
        this.initPassword=user.getInitPassword();
        this.username=user.getUsername();
        this.delFlag=user.getDelFlag();
        this.email=user.getEmail();
        this.mobile=user.getMobile();
        this.organizationId=user.getOrganizationId();
        this.nickname=user.getNickname();
        this.updated =user.getUpdated();
    }

}
