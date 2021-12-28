package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 9:19
 */
@Data
@ApiModel(value = "图片附件对象")
public class PicFrom {
    @ApiModelProperty(value = "图片id")
    private String picId;
    @ApiModelProperty(value = "图片name")
    private String picName;
    @ApiModelProperty(value = "图片url")
    private String picUrl;
}
