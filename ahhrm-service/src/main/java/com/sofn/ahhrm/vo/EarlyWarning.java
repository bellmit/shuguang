package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-26 15:43
 */
@ApiModel(value = "监测点信息对象")
@Data
public class EarlyWarning {
    @ApiModelProperty(value="保护点名称")
    private String pointName;
    @ApiModelProperty(value="检测对象集合")
    private List<AnalysisVo> analysis;
}
