package com.sofn.fdpi.vo;

import com.sofn.fdpi.model.FileManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
@ApiModel(value = "文件VO对象")
public class FileManageVo {

    @ApiModelProperty(value = "主键(支撑平台文件ID)")
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "文件大小")
    private Integer fileSize;

    public FileManage getFileManage(FileManageVo fileManageVo){
        FileManage fileManage=new FileManage();
        BeanUtils.copyProperties(fileManageVo,fileManage);
        return fileManage;
    }
}
