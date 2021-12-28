package com.sofn.agpjpm.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:37
 */
@Data
@ApiModel(value = "文件save对象")
public class FileForm {
    @ApiModelProperty(value = "主键，前端不传")
    private String id;
    @ApiModelProperty(value = "文件名")
    private String fileName;
    private List<FileAttForm> fileAttFormList;
}
