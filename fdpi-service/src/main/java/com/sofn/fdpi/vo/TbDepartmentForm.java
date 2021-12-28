package com.sofn.fdpi.vo;

import com.sofn.fdpi.model.TbDepartment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("机构档案表单对象")
public class TbDepartmentForm {
    @ApiModelProperty("主键")
    private String id;
    @NotBlank(message = "机构名称不能为空")
    @Length(max=50,message = "机构名称长度不能超过50")
    @ApiModelProperty("机构名称")
    private String deptName;
    //省
    @ApiModelProperty("省编码")
    @NotBlank(message = "省编码不能为空")
    @Length(max=32,message = "省编码长度不能超过32")
    private String deptPro;
    //市
    @ApiModelProperty("市编码")
    @Length(max=50,message = "市编码长度不能超过50")
    private String deptCity;
    //区
    @ApiModelProperty("区编码")
    @Length(max=32,message = "区编码长度不能超过32")
    private String deptArea;

    //行政区划中文：省-市-区
    @ApiModelProperty("行政区划中文，格式：省-市-区，例：四川省-成都市-高新区")
    @NotBlank(message = "行政区划中文不能为空")
    @Length(max=200,message = "行政区划中文长度不能超过200")
    private String regionInChina;

    @NotBlank(message = "功能权限类型不能为空")
    @ApiModelProperty("功能权限类型 数据来源/department/getAuthType")
    private String type;

    //支撑平台中机构id
    @ApiModelProperty("支撑平台中机构id")
    @NotBlank(message = "支撑平台中机构id不能为空")
    @Length(max=32,message = "支撑平台中机构id长度不能超过32")
    private String orgId;

    public TbDepartment getTbDepartment(TbDepartmentForm tbDepartmentForm){
        TbDepartment tbDepartment=new TbDepartment();
        BeanUtils.copyProperties(tbDepartmentForm,tbDepartment);
        return tbDepartment;
    }
}
