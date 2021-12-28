package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "图片附件表单对象6类,分别为root根stem茎leaf叶flower花fruit果seed种子")
public class PictureAccessoriesForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "文件ID(支撑平台文件ID)")
    private String fileId;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件用途 root根stem茎leaf叶flower花fruit果seed种子(前端不用填写)")
    private String fileUse;
    @ApiModelProperty(value = "文件描述")
    private String fileDescribe;
    @ApiModelProperty(value = "来源ID(前端不用填写)")
    private String sourceId;
}
