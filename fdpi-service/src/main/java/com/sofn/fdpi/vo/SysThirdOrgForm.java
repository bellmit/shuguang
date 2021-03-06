package com.sofn.fdpi.vo;


import com.sofn.fdpi.sysapi.bean.SysOrganization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value="非行政机构管理对象")
public class SysThirdOrgForm {

    @ApiModelProperty(value = "组织机构管理ID")
    private String id;

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

    public static SysOrganization getSysOrganization(SysThirdOrgForm sysOrganizationForm){
        SysOrganization sysOrganization = new SysOrganization();
        BeanUtils.copyProperties(sysOrganizationForm,sysOrganization);
        return sysOrganization;
    }

    public static SysThirdOrgForm getSysOrganizationForm(SysOrganization sysOrganization){
        SysThirdOrgForm sysOrganizationForm = new SysThirdOrgForm();
        BeanUtils.copyProperties(sysOrganization,sysOrganizationForm);
        return sysOrganizationForm;
    }
}
