package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 15:42
 */
@ApiModel(value = "检测对象")
@Data
public class AnalysisVo {
    @ApiModelProperty(value="有效群体数量")
    private  Double amount;
    @ApiModelProperty(value="风险等级")
    private  String grade;
    @ApiModelProperty(value="颜色")
    private  String color;
    @ApiModelProperty(value="品种名称")
    private  String variety;
}
