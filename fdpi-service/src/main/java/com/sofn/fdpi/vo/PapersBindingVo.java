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
 * @Description: 证书绑定明细返回对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书绑定明细返回对象")
public class PapersBindingVo {

    @ApiModelProperty(name = "papersId", value = "证书id")
    private String papersId;

    @ApiModelProperty(name = "compId", value = "企业id，企业表主键")
    private String compId;

    @ApiModelProperty(name = "compName", value = "公司名称")
    private String compName;

    @ApiModelProperty(name = "province", value = "区域_省编号")
    private String province;

    @ApiModelProperty(name = "city", value = "区域_市编号")
    private String city;

    @ApiModelProperty(name = "district", value = "区域_区编号")
    private String district;

    @ApiModelProperty(name = "region", value = "行政区划中文名格式：四川省-成都市-高新区")
    private String region;

    @ApiModelProperty(name = "addr", value = "通讯地址")
    private String addr;

    @ApiModelProperty(name = "postcode", value = "邮政编码")
    private String postcode;

    @NotBlank(message = "法人代表必填")
    @Length(max = 20, message = "法人代表不能超过20位")
    @ApiModelProperty(name = "legal", value = "法人代表")
    private String legal;

    @ApiModelProperty(name = "linKMan", value = "联系人")
    private String linKMan;

    @ApiModelProperty(name = "phone", value = "联系电话")
    private String phone;

    @ApiModelProperty(name = "email", value = "电子邮箱")
    private String email;
    //支撑平台返回的营业执照文件名称
    @ApiModelProperty(name = "busLicenseFileName", value = "支撑平台返回的营业执照文件名称")
    private String busLicenseFileName;

    //支撑平台返回的营业执照文件id
    @ApiModelProperty(name = "busLicenseFileId", value = "支撑平台返回的营业执照文件ID")
    private String busLicenseFileId;

    //支撑平台返回的营业执照文件路径
    @ApiModelProperty(name = "busLicenseFilePath", value = "支撑平台返回的营业执照文件路径")
    private String busLicenseFilePath;


    //许可证信息
    //1：人工繁育；2：驯养繁殖；3：经营利用
    @NotBlank(message = "证书类型必选")
    @Length(max = 32, message = "证书类型不能超过32位")
    @ApiModelProperty(name = "papersType", value = "证书类型：1：人工繁育许可证；2：驯养繁殖许可证；3：经营利用许可证")
    private String papersType;

    @ApiModelProperty(name = "papersTypeName", value = "证书类型名称：1：人工繁育许可证；2：驯养繁殖许可证；3：经营利用许可证")
    private String papersTypeName;

    @ApiModelProperty(name = "papersNumber", value = "证书编号，下拉中val值")
    private String papersNumber;

    @ApiModelProperty(name = "technicalDirector", value = "技术负责人")
    private String technicalDirector;
    @ApiModelProperty(name = "purpose", value = "人工繁育目的id")
    private String purpose;
    @ApiModelProperty(name = "purposeName", value = "人工繁育目的名称")
    private String purposeName;

    @ApiModelProperty(name = "issueUnit", value = "发证机关")
    @JSONField(format = "yyyy-MM-dd")
    private String issueUnit;

    @ApiModelProperty(name = "issueDate", value = "发证日期")
    @JSONField(format = "yyyy-MM-dd")
    private Date issueDate;

    @ApiModelProperty(name = "dataStart", value = "有效日期开始")
    @JSONField(format = "yyyy-MM-dd")
    private Date dataStart;

    @ApiModelProperty(name = "dataClos", value = "有效日期截止")
    @JSONField(format = "yyyy-MM-dd")
    private Date dataClos;

    @ApiModelProperty(name = "speciesList", value = "物种列表对象")
    private List<PapersSpecVo> speciesList;
    //上传文件相关的字段
    @ApiModelProperty(name = "fileList", value = "文件列表对象")
    private List<FileManageVo> fileList;

    @ApiModelProperty(name = "modeOperation", value = "经营方式")
    private String modeOperation;
    @ApiModelProperty(name = "salesDestination", value = "销售去向")
    private String salesDestination;

    @ApiModelProperty(name = "source", value = "前端不需要使用这个字段，后台使用")
    private String source;

    @ApiModelProperty("企业地址")
    private String compAddress;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("申请编号")
    private String applyNum;
}
