package com.sofn.sys.vo;

import com.sofn.sys.model.SysResource;
import com.sofn.sys.model.SysRole;
import com.sofn.sys.model.SysRoleResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysRoleResourceForm {

    /**
     * 编号
     */
    @ApiModelProperty("角色权限ID")
    @Length(max = 40,message = "角色权限ID超长")
    private String id;

    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色标识不能为空")
    private String roleName;
    /**
     * 角色描述,UI界面显示使用
     */
    @ApiModelProperty("菜单名称")
    @NotBlank(message = "角色描述不能为空")
    private String resourceName;
    /**
     * 拥有的资源
     */
    @ApiModelProperty("权限id")
    @Length(max = 40,message = "权限ID超长")
    private String resourceId;

    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    @ApiModelProperty("角色id")
    @Length(max = 40,message = "角色ID超长")
    private String roleId;



    public SysRoleResourceForm() {}
    public SysRoleResourceForm(SysRoleResource roleResource) {
        this.id = roleResource.getId();
        this.roleId = roleResource.getRoleId();
        this.resourceId = roleResource.getResourceId();
    }

    public static SysRoleResource getSysRoleResource(SysRoleResourceForm sysRoleresourceForm){
        SysRoleResource roleResource = new SysRoleResource();
        BeanUtils.copyProperties(sysRoleresourceForm,roleResource);
        return roleResource;
    }

}
