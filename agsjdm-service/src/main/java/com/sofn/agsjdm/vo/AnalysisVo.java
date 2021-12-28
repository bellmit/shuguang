package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 15:09
 */
@Data
@ApiModel("地图信息")
public class AnalysisVo {
    @ApiModelProperty(value = "颜色标识")
    private String colorMark;
    @ApiModelProperty(value = "信息（超出阀值设置）0未超出 1超出")
    private String message;
   @ApiModelProperty(value = "分布地区")
   private AddrVo addrVo;
}
