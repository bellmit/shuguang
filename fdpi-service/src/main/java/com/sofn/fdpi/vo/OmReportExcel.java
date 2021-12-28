package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/28 10:36
 **/
@Data
@ExcelSheetInfo(sheetName = "企业出口折算报表", title = "企业出口折算报表")
public class OmReportExcel {
    @ExcelField(title = "出口企业")
    private String sourceProc;
    @ExcelField(title = "允许进出口说明书")
    private String credential;
    @ExcelField(title = "规格")
    private String importSize;
    @ExcelField(title = "交易数量(吨)")
    private Double exportVolume;
    @ExcelField(title = "折算比例(吨)")
    private Double obversion;
    @ExcelField(title = "操作人")
    private String operator;
    @ExcelField(title = "创建时间")
    private Date createTime;
}
