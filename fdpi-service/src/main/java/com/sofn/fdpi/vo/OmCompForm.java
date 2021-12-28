package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "欧鳗企业提交信息form对象")
public class OmCompForm {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @ApiModelProperty(value = "单位名称")
    private String compName;
    @ApiModelProperty(value = "企业类型")
    private String compType;
    @ApiModelProperty(value = "所在省份编码")
    private String provinceCode;
    @ApiModelProperty(value = "所在省份名称")
    private String provinceName;
    @ApiModelProperty(value = "法人代表")
    private String legalPerson;
    @ApiModelProperty(value = "联系人")
    private String linkman;
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "通讯地址")
    private String contactAddress;
    @ApiModelProperty(value = "邮政编码")
    private String postal;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "驯养繁育许可证")
    private String tameBreedClience;
    @ApiModelProperty(value = "准许驯养繁殖情况鳗苗(吨)")
    private Double tameAllowTon;
    @ApiModelProperty(value = "经营利用许可证编号")
    private String manageLicence;
    @ApiModelProperty(value = "准许经营利用情况(吨)")
    private Double allowManageTon;
    @ApiModelProperty(value = "状态 0申报1不通过2通过")
    private String status;
    @ApiModelProperty(value = "欧鳗文件列表")
    private List<OmFileForm> files;
}
