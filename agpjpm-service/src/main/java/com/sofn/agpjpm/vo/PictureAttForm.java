package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "图片附件表单对象")
public class PictureAttForm {

    @ApiModelProperty(value = "主键(新增不用管，修改更新时必传)")
    private String id;
    @ApiModelProperty(value = "文件ID(支撑平台文件ID)")
    private String fileId;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件用途")
    private String fileUse;
    @ApiModelProperty(value = "文件描述")
    private String fileDescribe;
    @ApiModelProperty(value = "来源ID(前端不用填写)")
    private String sourceId;
}
