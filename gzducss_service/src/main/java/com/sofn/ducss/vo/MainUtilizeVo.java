package com.sofn.ducss.vo;

import com.sofn.ducss.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MainUtilizeVo {
    @ExcelField(title="所属年度")
    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "areaId", value = "区域ID")
    private String areaId;

    @ApiModelProperty(name = "utilizeId", value = "主表Id")
    private String utilizeId;

    @ExcelField(title="区域")
    @ApiModelProperty(name = "areaName", value = "区域")
    private String areaName;

    @ApiModelProperty(name = "mainId", value = "主体ID")
    private String mainId;

    @ExcelField(title="市场主体名称")
    @ApiModelProperty(name = "mainName", value = "市场主体名称")
    private String mainName;

}
