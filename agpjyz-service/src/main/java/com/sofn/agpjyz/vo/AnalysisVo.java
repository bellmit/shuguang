package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-23 13:19
 */
@Data
public class AnalysisVo {
    @ApiModelProperty(value = "目标物种ID")
    private String specId;
    @ApiModelProperty(value = "目标物种名称")
    private String specValue;
    @ApiModelProperty(value = "保护点id")
    private String protectId;
    @ApiModelProperty(value = "风险等级")
    private String riskLevel;
    @ApiModelProperty(value = "颜色标识")
    private String colorMark;
    @ApiModelProperty(value = "信息（超出阀值设置）0未超出 1超出")
    private String message;
    @ApiModelProperty(value="分布地区列表")
    private List<AgricultureSpeciesAddrVo> addrList;
    @ApiModelProperty(value = "指标分类ID  1:数量 2 面积")
    private String indexId;

}
