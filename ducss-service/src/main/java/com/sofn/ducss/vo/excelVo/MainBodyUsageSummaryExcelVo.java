package com.sofn.ducss.vo.excelVo;


import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ExcelSheetInfo(title = "秸秆系统市场主体汇总导出表")
public class MainBodyUsageSummaryExcelVo {

    @ExcelField(title = "序号")
    private String num;

    @ExcelField(title = "年度")
    private String year;

    /**
     * 这个要导出省-市-县 全称
     */
    @ExcelField(title = "区域")
    private String area;

    @ExcelField(title = "市场主体名称")
    private String mainBodyName;

    @ExcelField(title = "地址")
    private String address;

    @ExcelField(title = "法人名称")
    private String corporationName;

    @ExcelField(title = "法人电话")
    private String mobilePhone;

    @ExcelField( title ="肥料化")
    private BigDecimal fertilising;

    @ExcelField( title ="饲料化")
    private BigDecimal forage;

    @ExcelField( title ="燃料化")
    private BigDecimal fuel;

    @ExcelField( title ="基料化")
    private BigDecimal base;

    @ExcelField( title ="原料化")
    private BigDecimal material;

    @ExcelField( title ="合计")
    private BigDecimal total;

    @ExcelField( title ="外县来源")
    private BigDecimal other;

    @ExcelField( title ="本县来源")
    private BigDecimal thisCount;

}
