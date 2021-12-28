package com.sofn.ducss.vo;


import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 本县来源= （肥料化 + 饲料化 + 燃料化 + 基料化 + 原料化）- 外县来源
 */
@ApiModel
@Data
public class MainBodyUsageSummaryVo {

    @ApiModelProperty("查询具体县的时候会返回，查询总量时没值")
    @ExcelField(title = "", isShow = false)
    private String id;

    @ApiModelProperty("年份")
    @ExcelField(title = "年份")
    private String year;

    @ApiModelProperty("区域ID")
    @ExcelField(title = "区域", isShow = false)
    private String area;

    @ApiModelProperty("区域名称")
    @ExcelField(title = "区域")
    private String areaName;

    @ApiModelProperty("市场主体个数或者市场主体名字")
    @ExcelField(title = "市场主体个数")
    private String mainBodyNameOrCount;

    @ApiModelProperty("肥料化")
    @ExcelField(title = "肥料化(吨)",merge = "市场主体规模化秸秆利用量")
    private String fertilising;

    @ApiModelProperty("饲料化")
    @ExcelField(title = "饲料化(吨)",merge = "市场主体规模化秸秆利用量")
    private String forage;

    @ApiModelProperty("燃料化")
    @ExcelField(title = "燃料化(吨)",merge = "市场主体规模化秸秆利用量")
    private String fuel;

    @ApiModelProperty("基料化")
    @ExcelField(title = "基料化(吨)",merge = "市场主体规模化秸秆利用量")
    private String base;

    @ApiModelProperty("原料化")
    @ExcelField(title = "原料化(吨)",merge = "市场主体规模化秸秆利用量")
    private String material;

    @ApiModelProperty("合计")
    @ExcelField(title = "合计(吨)",merge = "年总利用量")
    private String count;

    @ApiModelProperty("本县来源")
    @ExcelField(title = "本县来源(吨)",merge = "年总利用量")
    private String thisCount;

    @ApiModelProperty("外县来源")
    @ExcelField(title = "外县来源(吨)",merge = "年总利用量")
    private String other;

}
