package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-13 14:55
 */
@ApiModel(value = "物种和物种级别")
@Data
public class SpeNameLevelVo {
    @ApiModelProperty(value = "物种编号")
    private String id;
    @ApiModelProperty(value = "物种名")
    private String speName;
    @ApiModelProperty(value = "物种保护级别")
    private String proLevel;
    @ApiModelProperty(value = "物种编号")
    private String speCode;
    @ApiModelProperty(value = "物种类别")
    private String speType;
}
