package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Description: 企业注册上传表单对象
 * @Author: wuXY
 * @Date: 2019-12-30 13:51:50
 */
@Data
@ApiModel(value = "企业注册上传表单对象")
public class EnterpriseForm {

    @NotBlank(message = "账号必填")
    @Length(max = 20, message = "账号不能超过20位")
    @ApiModelProperty(name = "account", value = "账号")
    private String account;

    @NotBlank(message = "密码必填")
    @Length(max = 20, message = "密码不能超过20位")
    @ApiModelProperty(name = "password", value = "密码")
    private String password;

    @NotBlank(message = "公司名称必填")
//    @Length(max = 20, message = "公司名称不能超过20位")
    @ApiModelProperty(name = "compName", value = "公司名称")
    private String compName;

    @NotBlank(message = "区域_省必填")
    @Length(max = 32, message = "区域_省不能超过32位")
    @ApiModelProperty(name = "province", value = "区域_省编号")
    private String province;

    @NotBlank(message = "区域_市必填")
    @Length(max = 32, message = "公区域_市不能超过32位")
    @ApiModelProperty(name = "city", value = "区域_市编号")
    private String city;

    @NotBlank(message = "区域_区必填")
    @Length(max = 32, message = "公区域_区不能超过32位")
    @ApiModelProperty(name = "district", value = "区域_区编号")
    private String district;

    @NotBlank(message = "行政区域必填")
    @Length(max = 50, message = "行政区划不能超过50位")
    @ApiModelProperty(name = "region", value = "行政区划中文名格式：四川省-成都市-高新区")
    private String region;

    @NotBlank(message = "通讯地址必填")
    @Length(max = 50, message = "通讯地址不能超过50位")
    @ApiModelProperty(name = "addr", value = "通讯地址")
    private String addr;

//    @NotBlank(message = "邮政编码必填")
//    @Length(max = 10, message = "邮政编码不能超过10位")
//    @ApiModelProperty(name = "postcode", value = "邮政编码")
//    private String postcode;

    @NotBlank(message = "法人代表必填")
    @Length(max = 10, message = "法人代表不能超过10位")
    @ApiModelProperty(name = "legal", value = "法人代表")
    private String legal;

    @NotBlank(message = "联系人必填")
    @Length(max = 10, message = "联系人不能超过10位")
    @ApiModelProperty(name = "linKMan", value = "联系人")
    private String linKMan;

    @NotBlank(message = "联系电话必填")
    @Length(max = 20, message = "联系电话不能超过20位")
    @ApiModelProperty(name = "phone", value = "联系电话")
    private String phone;

    @NotBlank(message = "电子邮箱必填")
    @Length(max = 30, message = "电子邮箱不能超过30位")
    @ApiModelProperty(name = "email", value = "电子邮箱")
    private String email;
    //支撑平台返回的营业执照文件名称
    @NotBlank(message = "营业执照文件名称必填")
    @Length(max = 100, message = "营业执照文件名称不能超过100位")
    @ApiModelProperty(name = "busLicenseFileName", value = "支撑平台返回的营业执照文件名称")
    private String busLicenseFileName;

    //支撑平台返回的营业执照文件id
    @NotBlank(message = "营业执照文件ID必填")
    @Length(max = 32, message = "营业执照文件ID不能超过32位")
    @ApiModelProperty(name = "busLicenseFileId", value = "支撑平台返回的营业执照文件ID")
    private String busLicenseFileId;

    //支撑平台返回的营业执照文件路径
    @NotBlank(message = "营业执照文件路径必填")
    @Length(max = 100, message = "营业执照文件路径不能超过100位")
    @ApiModelProperty(name = "busLicenseFilePath", value = "支撑平台返回的营业执照文件路径")
    private String busLicenseFilePath;

    //企业类型
    @ApiModelProperty(name = "compType", value = "企业类型 通过数据字典fdpi_comp_type获取")
    private String compType;

    @ApiModelProperty(name = "papersList", value = "证书信息列表")
    private List<PapersInRegisterForm> papersList;


//    //许可证信息
//    //1：人工繁育；2：驯养繁殖；3：经营利用
//    @NotBlank(message = "证书类型必选")
//    @Length(max = 32, message = "证书类型不能超过32位")
//    @ApiModelProperty(name = "papersType", value = "证书类型：1：人工繁育许可证；2：驯养繁殖许可证；3：经营利用许可证")
//    private String papersType;
//
//    @ApiModelProperty(name = "papersTypeName", value = "证书类型名称：1：人工繁育许可证；2：驯养繁殖许可证；3：经营利用许可证")
//    private String papersTypeName;
//
//    @NotBlank(message = "证书id必填")
//    @Length(max = 32, message = "证书id不能超过32位")
//    @ApiModelProperty(name = "papersId", value = "证书id，下拉中key值")
//    private String papersId;
//
//    @NotBlank(message = "证书编号必选")
//    @Length(max = 32, message = "证书编号不能超过32位")
//    @ApiModelProperty(name = "papersNumber", value = "证书编号，下拉中val值")
//    private String papersNumber;
//
//    @ApiModelProperty(name = "technicalDirector", value = "技术负责人")
//    private String technicalDirector;
//
//    @ApiModelProperty(name = "issueUnit", value = "发证机关")
//    @JSONField(format = "yyyy-MM-dd")
//    private String issueUnit;
//
//    @ApiModelProperty(name = "issueDate", value = "发证日期")
//    @JSONField(format = "yyyy-MM-dd")
//    private Date issueDate;
//
//    @ApiModelProperty(name = "dataStart", value = "有效日期开始")
//    @JSONField(format = "yyyy-MM-dd")
//    private Date dataStart;
//
//    @ApiModelProperty(name = "dataClos", value = "有效日期截止")
//    @JSONField(format = "yyyy-MM-dd")
//    private Date dataClos;
//
//    //上传文件相关的字段
//    @ApiModelProperty(name = "fileList", value = "文件列表对象")
//    private List<FileManageVo> fileList;
//
//    //是否有效
//    @ApiModelProperty(name = "isEnable", value = "证书是否生效 '1':生效；‘0’：未生效")
//    private String isEnable;

}
