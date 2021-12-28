package com.sofn.agzirz.sysapi.bean;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 文件VO对象
 */
@ApiModel(value = "文件管理VO对象")
@Data
public class SysFileManageVo {

    @ApiModelProperty(value = "文件ID")
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件大小")
    private long fileSize;

    @ApiModelProperty(value = "文件类型，pdf、word、txt由上传时的后缀名确认")
    private String fileType;

    @ApiModelProperty(value = "操作用户")
    private String operator;

    @ApiModelProperty(value = "操作用户名称")
    private String operatorName;

    @ApiModelProperty(value = "操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    @ApiModelProperty(value = "所属系统ID")
    private String systemId;

    @ApiModelProperty(value = "AppId")
    private String appId;

    @ApiModelProperty(value = "所属系统名称")
    private String systemName;

    @ApiModelProperty(value = "下载次数")
    private Integer downloadTimes;

    @ApiModelProperty(value = "备注")
    private String remark;

}
