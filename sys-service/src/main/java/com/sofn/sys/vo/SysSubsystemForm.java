package com.sofn.sys.vo;

import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.SysSubsystem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
public class SysSubsystemForm {
    @ApiModelProperty("子系统ID")
    @Length(max = 64,message = "父节点ID超长")
    private String id;
    @Length(max = 64,message = "父节点ID超长")
    @ApiModelProperty("子系统父类ID")
    private String parentId;
    @Length(max = 256,message = "父节点ID集合超长")
    @ApiModelProperty("父节点ID集合")
    private String parentIds;
    @ApiModelProperty("子系统父类名称")
    private String parentName;

    @NotBlank(message = "子系统名称不能为空")
    @Length(max = 50,message = "子系统名称超长")
    @ApiModelProperty("子系统名称")
    private String subsystemName;
    @NotBlank(message = "子系统appId不能为空")
    @Length(max = 50,message = "子系统appId超长")
    @ApiModelProperty("子系统APPID")
    private String appId;

    @Length(max=1,message = "是否删除标志超长")
    private String delFlag;

    @ApiModelProperty("前端访问路径")
    @Length(max = 150,message = "前端访问路径超长，最多150个字符")
    public String viewUrl;

    @ApiModelProperty("系统排序")
    @Min(value = 0,message = "系统排序最小值为0")
    public Integer sort;

    private List<SysSubsystemForm> childList;
    //子系统下的菜单
    private List<SysResourceForm> sysResourceList;
    private String description;

    @ApiModelProperty("是否有权限，Y有权限，N没有权限，用作界面展示，添加时不传")
    private String havePermission;

    @ApiModelProperty("系统类型，1 平台 2 分系统 3 子系统")
    private String systemType;
    /**
     * 将表单对象转为持久层对象
     * @param sysSubsystemForm  表单对象
     * @return 持久层对象
     */
    public static SysSubsystem getSysSubsystem(SysSubsystemForm sysSubsystemForm){
        SysSubsystem sysSubsystem = new SysSubsystem();
        BeanUtils.copyProperties(sysSubsystemForm,sysSubsystem);
        return sysSubsystem;
    }

    public SysSubsystemForm(SysSubsystem subsystem) {
        this.id = subsystem.getId();
        this.subsystemName = subsystem.getSubsystemName();
        this.appId = subsystem.getAppId();
        this.parentId = subsystem.getParentId();
        this.parentIds = subsystem.getParentIds();
        this.description = subsystem.getDescription();
        this.delFlag = subsystem.getDelFlag();
        this.sort = subsystem.getSort();
        this.viewUrl = subsystem.getViewUrl();
        this.systemType = subsystem.getSystemType();
    }

    public SysSubsystemForm(SysSubsystem subsystem,List<String> userHaveAppIds) {
        this(subsystem);
        if(!CollectionUtils.isEmpty(userHaveAppIds)){
            if(userHaveAppIds.contains(subsystem.getAppId())){
                this.havePermission = SysManageEnum.DEL_FLAG_Y.getCode();
            }else{
                this.havePermission =  SysManageEnum.DEL_FLAG_N.getCode();
            }
        }
    }

}
