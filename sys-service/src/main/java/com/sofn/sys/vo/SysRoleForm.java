package com.sofn.sys.vo;

import com.sofn.sys.model.SysResource;
import com.sofn.sys.model.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysRoleForm {

    /**
     * 编号
     */
    @ApiModelProperty("角色ID")
    @Length(max = 32,message = "角色ID超长")
    private String id;

    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
     * 角色描述,UI界面显示使用
     */
    @ApiModelProperty("角色描述")
    private String describe;
    @ApiModelProperty("子系统ID")
    @NotBlank(message = "子系统ID不能为空")

    private String SubsystemId;
    @ApiModelProperty("子系统名称")
    @NotBlank(message = "系统名称不能为空")
    private String SubsystemName;

    @ApiParam("角色码")
//    @NotBlank(message = "角色码不能为空")
//    @Pattern(regexp = "\\w*",message = "角色码只能是英文字母、数字、下划线")
    @Length(max = 50,message = "角色码超长")
    private String roleCode;
    /**
     * 拥有的资源
     */
    @ApiModelProperty("角色对应的资源Id(用\",\"分隔)")
    private String resourceIds;
    private List<SysResource> resourceList;
    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    @ApiModelProperty("删除标识Y/N")
    private String delFlag;
    @ApiModelProperty("状态标识")
    private String status;



    @ApiModelProperty("创建人ID")
    private String createUserId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人ID")
    private String updateUserId;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    public SysRoleForm() {}


    public static SysRole getSysRole(SysRoleForm sysRoleForm){
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleForm,sysRole);
        return sysRole;
    }

}
