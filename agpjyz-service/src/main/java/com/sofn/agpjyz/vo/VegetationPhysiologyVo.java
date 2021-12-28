package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-02 16:52
 */
@Data
@ApiModel(value = "植被生理基础信息表单对象")
public class VegetationPhysiologyVo {
    //    保护点ID
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    //    保护点value
    @ApiModelProperty(value = "保护点value")
    private String protectValue;
    //  覆盖度
    @ApiModelProperty(value = "覆盖度")
    private String coverDegree;
    //    冠层
    @ApiModelProperty(value = "冠层")
    private String canopy;
    //    叶面积
    @ApiModelProperty(value = "叶面积")
    private String leafArea;
}
