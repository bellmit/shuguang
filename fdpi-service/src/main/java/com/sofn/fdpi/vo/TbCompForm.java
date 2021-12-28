package com.sofn.fdpi.vo;

import com.sofn.fdpi.model.TbComp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("企业信息表单")
public class TbCompForm implements Serializable {
    //企业id（支撑平台组织机构id）
    @NotBlank(message = "企业id不能为空")
    @Length(max = 32,message = "企业id长度已超长！")
    @ApiModelProperty("企业id（支撑平台组织机构id）")
    private String id;
    //省
    @NotBlank(message = "省不能为空")
    @Length(max = 32,message = "省长度已超长！")
    @ApiModelProperty("省编码")
    private String compProvince;
    //市
    @NotBlank(message = "市不能为空")
    @Length(max = 32,message = "市长度已超长！")
    @ApiModelProperty("市编码")
    private String compCity;
    //区
    @NotBlank(message = "区不能为空")
    @Length(max = 32,message = "区长度已超长！")
    @ApiModelProperty("区编码")
    private String compDistrict;
    //行政区划中文
    @NotBlank(message = "行政区划中文不能为空")
    @Length(max = 100,message = "行政区划中文已超长！")
    @ApiModelProperty("行政区划中文（省-市-区中文拼接）")
    private String regionInCh;
    @NotBlank(message = "通讯地址不能为空")
    @Length(max = 200,message = "通讯地址已超长！")
    @ApiModelProperty("通讯地址")
    private String contactAddress;
    //邮政编码
    @NotBlank(message = "邮政编码不能为空")
    @Length(max = 10,message = "邮政编码已超长！")
    @ApiModelProperty("邮政编码")
    private String postAddress;
    //法人
    @NotBlank(message = "法人不能为空")
    @Length(max = 20,message = "法人已超长！")
    @ApiModelProperty("法人")
    private String legal;
    @NotBlank(message = "联系人不能为空")
    @Length(max = 20,message = "联系人已超长！")
    @ApiModelProperty("联系人")
    private String LinkMan;
    @NotBlank(message = "联系电话不能为空")
    @Length(max = 15,message = "联系电话已超长！")
    @ApiModelProperty("联系电话")
    private String phone;
    @NotBlank(message = "电子邮箱不能为空")
    @Length(max = 40,message = "电子邮箱已超长！")
    @ApiModelProperty("电子邮箱")
    private String email;

    //营业执照上传的文件名称
    @Length(max = 100,message = "营业执照上传的文件名称已超长！")
    @ApiModelProperty("营业执照上传的文件名称")
    private String busLicenseFileName;
    //营业执照上传的文件ID
    @Length(max = 32,message = "营业执照上传的文件ID已超长！")
    @ApiModelProperty("营业执照上传的文件ID")
    private String busLicenseFileId;
    //营业执照上传的文件路径
    @Length(max = 200,message = "营业执照上传的文件路径已超长！")
    @ApiModelProperty("营业执照上传的文件路径")
    private String busLicenseFilePath;

    public TbComp getTbComp(TbCompForm tbCompForm){
        TbComp tbComp=new TbComp();
        BeanUtils.copyProperties(tbCompForm,tbComp);
        return tbComp;
    }
}
