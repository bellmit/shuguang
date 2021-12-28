package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-27 14:22
 */
@Data
@ApiModel(value = "半生植物基础信息对象")
public class HalophytesVo {

    //    保护点ID
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    //    保护点value
    @ApiModelProperty(value = "保护点value")
    private String protectValue;
    //  伴生植物名称
    @ApiModelProperty(value = "伴生植物名称")
    private String associated;
    //    数量
    @ApiModelProperty(value = "数量")
    private String amount;
    //    生长状况
    @ApiModelProperty(value = "生长状况")
    private String growth;
    // 物种丰富度
    @ApiModelProperty(value = "物种丰富度")
    private String richness;
}
