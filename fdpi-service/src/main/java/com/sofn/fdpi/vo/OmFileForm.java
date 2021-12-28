package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "欧鳗文件信息form对象")
public class OmFileForm {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "文件id")
    @NotBlank(message = "文件的fileId不能为空")
    @Length(max = 32, message = "文件的fileId不能超过32位")
    private String fileId;

    @NotBlank(message = "文件url不能为空")
    @Length(max = 500, message = "文件url不能超过500位")
    @ApiModelProperty(value = "文件url")
    private String fileUrl;

    @NotBlank(message = "文件名字不能为空")
    @Length(max = 150, message = "文件url不能超过150位")
    @ApiModelProperty(value = "文件名字")
    private String fileName;

    @ApiModelProperty(value = "文件用途")
    @NotBlank(message = "文件用途不能为空")
    @Length(max = 50, message = "文件用途不能超过50位")
    private String fileUse;

}
