package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件管理类
 *
 * @Author yumao
 * @Date 2020/1/2 13:45
 **/
@Data
@TableName("FILE_MANAGE")
@ApiModel("文件对象")
public class FileManage {

    /**
     * 主键(支撑平台文件ID)
     */
    @ApiModelProperty(value = "主键(支撑平台文件ID)")
    private String id;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "区")
    private String filePath;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件路径")
    private String fileType;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件类型")
    private Integer fileSize;
    /**
     * 文件来源
     */
    @ApiModelProperty(value = "文件来源")
    private String fileSource;
    /**
     * 文件来源ID
     */
    @ApiModelProperty(value = "文件来源ID")
    private String fileSourceId;
    /**
     * 文件状态
     */
    @ApiModelProperty(value = "文件状态")
    private String fileStatus;


}
