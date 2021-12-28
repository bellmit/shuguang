package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("企业注册中证书信息")
public class PapersInRegisterForm implements Serializable {
    //
    //1：人工繁育；2：驯养繁殖；3：经营利用
    @NotBlank(message="证书类型必选")
    @Length(max = 32,message = "证书类型不能超过32位")
    @ApiModelProperty(name="papersType",value="证书类型：1：人工繁育许可证；2：驯养繁殖许可证；3：经营利用许可证")
    private String papersType;

    @NotBlank(message="证书id必填")
    @Length(max = 32,message = "证书id不能超过32位")
    @ApiModelProperty(name="papersId",value="证书id，下拉中key值")
    private String papersId;

    @NotBlank(message="证书编号必选")
    @Length(max = 32,message = "证书编号不能超过32位")
    @ApiModelProperty(name="papersNumber",value="证书编号，下拉中val值")
    private String papersNumber;

    @NotBlank(message="证书中法人必传")
    @Length(max = 100,message = "证书中法人不能超过100")
    @ApiModelProperty("法人")
    private String legal;

    @NotBlank(message="证书中企业地址必闯")
    @Length(max = 500,message = "证书中企业地址不能超过500")
    @ApiModelProperty("证书中企业地址")
    private String compAddress;

    @ApiModelProperty(name="papersSpecList",value="证书物种列表")
    private List<PapersSpecForm> papersSpecList;
    //上传文件相关的字段
    @ApiModelProperty(name="fileList",value="文件列表对象")
    private List<FileManageVo> fileList;
}
