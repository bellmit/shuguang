package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:39
 */
@Data
@ApiModel(value = "文件附件对象")
public class FileAttForm {
    @ApiModelProperty(value = "主键(新增不用管，修改更新时必传)")
    private String id;
//    @ApiModelProperty(value = "图片id")
//    private String picId;
//    @ApiModelProperty(value = "图片name")
//    private String picName;
//    @ApiModelProperty(value = "图片url")
//    private String picUrl;
//    @ApiModelProperty(value = "附件id")
//    private String attId;
//    @ApiModelProperty(value = "附件name")
//    private String attName;
//    @ApiModelProperty(value = "附件url")
//    private String attUrl;

    @ApiModelProperty(value = "来源id(前端不管，后端使用字段)")
    private String sourceId;
    private AttFrom att;
    private PicFrom pic;
}
