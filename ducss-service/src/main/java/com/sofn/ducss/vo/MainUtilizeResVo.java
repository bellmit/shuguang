package com.sofn.ducss.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(value = "市场化主体利用量汇总数据实体")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ExcelSheetInfo(sheetName = "市场化主体利用量汇总数据列表")
public class MainUtilizeResVo {
    @ExcelField(title="所属年度")
    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ExcelField(title="",isShow = false)
    @ApiModelProperty(name = "areaId", value = "区域ID")
    private String areaId;

    @ExcelField(title="",isShow = false)
    @ApiModelProperty(name = "utilizeId", value = "主表Id",hidden = true)
    private String utilizeId;

    @ExcelField(title="区域")
    @ApiModelProperty(name = "areaName", value = "区域")
    private String areaName;

    @ExcelField(title="",isShow = false)
    @ApiModelProperty(name = "mainId", value = "主体ID")
    private String mainId;

    @ExcelField(title="市场主体名称")
    @ApiModelProperty(name = "mainName", value = "市场主体名称")
    private String mainName;

    @ExcelField(title="肥料化")
    @ApiModelProperty(name = "fertilising", value = "肥料化")
    private BigDecimal fertilising;

    @ExcelField(title="饲料化")
    @ApiModelProperty(name = "forage", value = "饲料化")
    private BigDecimal forage;

    @ExcelField(title="燃料化")
    @ApiModelProperty(name = "fuel", value = "燃料化")
    private BigDecimal fuel;

    @ExcelField(title="基料化")
    @ApiModelProperty(name = "base", value = "基料化")
    private BigDecimal base;

    @ExcelField(title="原料化")
    @ApiModelProperty(name = "material", value = "原料化")
    private BigDecimal material;

    @ExcelField(title="本县来源")
    @ApiModelProperty(name = "ownSource", value = "本县来源")
    private BigDecimal ownSource;

    @ExcelField(title="外县来源")
    @ApiModelProperty(name = "otherSource", value = "外县来源")
    private BigDecimal otherSource;

    @ExcelField(title="总利用量")
    @ApiModelProperty(name = "total", value = "总利用量")
    private BigDecimal total;

}
