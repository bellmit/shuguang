package com.sofn.ducss.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据请求参数")
public class DataAnalysisIn {
    @ApiModelProperty(value = "年份段 ,分割")
    private String year;

    @ApiModelProperty(value = "区域code码")
    private String areaCode;

    @ApiModelProperty(value = "作物类型")
    private String cropType;

    @ApiModelProperty(value = "指标内容")
    private String analysisIndex;

    @ApiModelProperty(value = "1 全国 2,省级信息 3, 市级信息 , 4 县级信息")
    private String allCropType;

    private Integer pageSize;

    private Integer pageNum;

}
