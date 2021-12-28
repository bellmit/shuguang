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
@ExcelSheetInfo(sheetName = "出口企业汇总报表", title = "出口企业汇总报表")
public class OmAllReportExcel {
    @ExcelField(title = "出口企业")
    private String sourceProc;
    @ExcelField(title = "允许进出口说明书")
    private String credential;
    @ExcelField(title = "出口时间")
    private Date outOfDate;
    @ExcelField(title = "发货口岸")
    private String portOfDispatch;
    @ExcelField(title = "货物类型")
    private String goodsType;
    @ExcelField(title = "交易数量(吨)")
    private Double exportVolume;
    @ExcelField(title = "折算比例(吨)")
    private Double obversion;
}
