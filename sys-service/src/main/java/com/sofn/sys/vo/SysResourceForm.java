package com.sofn.sys.vo;


import com.sofn.sys.model.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by sofn
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysResourceForm  {

    /**
     * 编号
     */
    @ApiModelProperty("菜单ID")
    @Length(max = 64,message = "菜单ID超长")
    private String id;

    @NotBlank(message = "菜单名称不能为空")
    @Length(max = 50,message = "菜单名称超长")
    @ApiModelProperty("菜单名称")
    private String resourceName;
    /**
     * 资源类型
     */
    @ApiModelProperty("菜单类型")
    @NotNull(message = "菜单类型不能为空")
    private String type;
    /**
     * 资源路径
     */
    @ApiModelProperty("菜单url")
    @Length(max = 100,message = "菜单url超长，最多100个英文字符")
    private String resourceUrl;
    /**
     * 权限字符串
     */
    @ApiModelProperty("权限代码")
    @Length(max = 100,message = "权限代码超长，最多100个英文字符")
    private String permission;
    /**
     * 父编号
     */
    @ApiModelProperty("父菜单ID")
    @Length(max = 64,message = "父菜单ID超长")
    @NotNull(message = "父编号不能为空")
    private String parentId;
    private List<SysResourceForm> childList;
    /**
     * 父编号列表
     */

    private String parentIds;
    private String parentName;

    @ApiModelProperty("删除标志")
    private String delFlag;
    /**
     * 图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;
    /**
     * 排序
     */
    private Long priority;
    private String appId;
    /**
     * 叶子节点
     */
    private Boolean leaf;


    private Date createTime;

    private String sysSubsystemName;

   /* private List<SysResourceForm> children;*/

    public SysResourceForm(SysResource resource) {
        this.id = resource.getId();
        this.resourceName = resource.getResourceName();
        this.type = resource.getType();
        this.resourceUrl = resource.getResourceUrl();
        this.permission = resource.getPermission();
        this.parentId = resource.getParentId();
        this.parentIds = resource.getParentIds();
        this.delFlag = resource.getDelFlag();
        this.icon = resource.getIcon();
        this.priority = resource.getPriority();
        this.leaf = resource.getLeaf();
        this.appId=resource.getAppId();
        this.createTime = resource.getCreateTime();
    }


    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }
    public String getParentIds() {
        return parentIds;
    }
    public String getId() {
        return id;
    }
    public boolean isRootNode() {
        return parentId == "0";
    }

    public static SysResource getSysResource(SysResourceForm sysResourceForm){
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceForm,sysResource);
        return sysResource;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this == obj){
            return true;
        }

        if (obj instanceof SysResourceForm) {
            SysResourceForm sysResourceForm = (SysResourceForm) obj;
            // 只要ID相同就代表同一个对象
            if (sysResourceForm.id.equals(this.id)){
                return true;
            }
        }
        return false;
    }

    /**
     * 重写hashcode 方法，返回的hashCode不一样才再去比较每个属性的值
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }



}
