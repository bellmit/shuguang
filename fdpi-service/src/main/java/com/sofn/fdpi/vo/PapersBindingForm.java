package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 证书绑定表单
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书表单")
public class PapersBindingForm implements Serializable {
    //企业id，主键
    @NotBlank(message = "企业id必填")
    @Length(max = 32, message = "企业id不能超过32位")
    @ApiModelProperty(name = "compId", value = "企业id，企业表主键")
    private String compId;
    //证书id，主键
    @NotBlank(message = "证书id必填")
    @Length(max = 32, message = "证书id不能超过32位")
    @ApiModelProperty(name = "papersId", value = "证书id，证书编号下拉中key值")
    private String papersId;

    @NotBlank(message = "证书编号必选")
    @Length(max = 50, message = "证书编号不能超过50位")
    @ApiModelProperty(name = "papersNumber", value = "证书编号，证书编号下拉中val值")
    private String papersNumber;

    //    @NotBlank(message="核对物种必填")
//    @Length(max = 50,message = "核对物种不能超过50位")
//    @ApiModelProperty(name="issueSpe",value="核对物种")
//    private String issueSpe;
//
//    //核对数量
//    @ApiModelProperty(name="issueNum",value="核对数量")
//    private Integer issueNum;
//
//    //支撑平台返回的许可证上传文件名称
//    @NotBlank(message="证书文件名称必填")
//    @Length(max = 100,message = "证书文件名称不能超过100位")
//    @ApiModelProperty(name="papersFileName",value="支撑平台返回的证书文件名称")
//    private String papersFileName;
//
//    //支撑平台返回的许可证上传文件名id
//    @NotBlank(message="证书文件ID必填")
//    @Length(max = 100,message = "证书文件ID不能超过100位")
//    @ApiModelProperty(name="papersFileId",value="支撑平台返回的证书文件ID")
//    private String papersFileId;
//
//    //支撑平台返回的许可证文件路径
//    @NotBlank(message="证书文件路径必填")
//    @Length(max = 100,message = "证书文件路径不能超过100位")
//    @ApiModelProperty(name="papersFilePath",value="支撑平台返回的证书文件路径")
//    private String papersFilePath;
    @ApiModelProperty(name = "fileList", value = "文件列表对象")
    private List<FileManageVo> fileList;
}
