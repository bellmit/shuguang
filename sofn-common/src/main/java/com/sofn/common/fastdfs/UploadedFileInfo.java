/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-03-23 10:50
 */
package com.sofn.common.fastdfs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 已上传文件信息
 *
 * @author lijiang
 * @version 1.0
 **/
@ApiModel(value = "已上传文件信息")
@Data
public class UploadedFileInfo implements Serializable {
    private static final long serialVersionUID = -6907930578909471802L;
    /**
     * 上传文件名
     */
    @ApiModelProperty(value = "上传文件名")
    private String fileName;
    /**
     * 文件扩展名
     */
    @ApiModelProperty(value = "文件扩展名")
    private String fileExtName;
    /**
     * 文件字节数
     */
    @ApiModelProperty(value = "文件字节数")
    private Long fileLength;
    /**
     * 文件内容类型
     */
    @ApiModelProperty(value = "文件内容类型")
    private String contentType;
    /**
     * 文件下载链接
     */
    @ApiModelProperty(value = "文件下载链接")
    private String fileUrl;
}
