package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 9:20
 */
@Data
@ApiModel(value = "wenjian附件对象")
public class AttFrom {
    @ApiModelProperty(value = "附件id")
    private String attId;
    @ApiModelProperty(value = "附件name")
    private String attName;
    @ApiModelProperty(value = "附件url")
    private String attUrl;
}
