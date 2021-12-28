package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件VO对象
 * Created by heyongjie on 2019/6/18 16:15
 */
@ApiModel(value = "文件VO对象")
@Data
public class SysFileVo {

    /**
     * ID
     */
    @ApiModelProperty(value = "文件ID")
    private String id;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private long fileSize;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型，pdf、word、txt由上传时的后缀名确认")
    private String fileType;

    @ApiModelProperty(value = "上传用户")
    private String createUserId;

    @ApiModelProperty(value = "上传时间")
    private String createTime;

}
