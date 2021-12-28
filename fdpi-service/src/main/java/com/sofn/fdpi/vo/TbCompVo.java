package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("企业信息对象")
public class TbCompVo implements Serializable {
    //企业id（支撑平台组织机构id）
    @ApiModelProperty("企业id（支撑平台组织机构id）")
    private String id;
    //公司名称
    @ApiModelProperty("公司名称")
    private String compName;
    //省
    @ApiModelProperty("省")
    private String compProvince;
    //市
    @ApiModelProperty("市")
    private String compCity;
    //区
    @ApiModelProperty("区")
    private String compDistrict;
    //行政区划中文
    @ApiModelProperty("行政区划中文")
    private String regionInCh;
    @ApiModelProperty("通讯地址")
    private String contactAddress;
    //邮政编码
    @ApiModelProperty("邮政编码")
    private String postAddress;
    //法人
    @ApiModelProperty("法人")
    private String legal;
    @ApiModelProperty("联系人")
    private String LinkMan;
    @ApiModelProperty("联系电话")
    private String phone;
    @ApiModelProperty("电子邮箱")
    private String email;
    @ApiModelProperty("状态")
    //0：注册；1：已审核；2：退回
    private String compStatus;
    @ApiModelProperty("状态名称")
    private String compStatusName;
    //直属机构
    @ApiModelProperty("直属机构")
    private String direclyId;
    //省级机构
    @ApiModelProperty("省级机构")
    private String provincialId;
    //营业执照上传的文件名称
    @ApiModelProperty("营业执照上传的文件名称")
    private String busLicenseFileName;
    //营业执照上传的文件ID
    @ApiModelProperty("营业执照上传的文件ID")
    private String busLicenseFileId;
    //营业执照上传的文件路径
    @ApiModelProperty("营业执照上传的文件路径")
    private String busLicenseFilePath;

    @ApiModelProperty("上次年审id")
    private String lastYearInspectId;

    @ApiModelProperty("上次年审日期")
    @JSONField(format = "yyyy-MM-dd")
    private Date lastYearInspectDate;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("创建人id")
    private String createUserId;
    @ApiModelProperty("修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty("修改人id")
    private String updateUserId;
    @ApiModelProperty("删除标识")
    private String delFlag;
    @ApiModelProperty(value = "是否是当前机构的直属企业。1：是；'0'：否",example = "1")
    private String isDirectly;
    @ApiModelProperty(value = "企业编号")
    private String compCode;
    @ApiModelProperty(value = "企业类型")
    private String compType;
}
