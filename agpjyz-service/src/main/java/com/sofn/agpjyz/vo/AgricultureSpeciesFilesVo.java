package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 农业植物物种图片
 * @Author: WXY
 * @Date: 2020-2-28 13:55:00
 */
@Data
@ApiModel("农业植物物种图片")
public class AgricultureSpeciesFilesVo implements Serializable {
    @ApiModelProperty(value="主键")
    private String id;
    //支撑平台中的文件id；
    @ApiModelProperty(value="支撑平台中的文件id")
    private String fileId;
    @ApiModelProperty(value="文件名称")
    private String fileName;
    @ApiModelProperty(value="文件路径")
    private String filePath;

}
