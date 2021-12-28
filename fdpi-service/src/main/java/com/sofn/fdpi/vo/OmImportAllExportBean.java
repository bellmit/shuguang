package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/17 17:58
 **/
@Data
@ExcelSheetInfo(title = "进口企业汇总分析列表", sheetName = "进口企业汇总分析列表")
public class OmImportAllExportBean implements Serializable {
    //进口企业
    @ExcelField(title = "进口企业")
    private String importMan;

    //《允许进出口证明书》号
    @ExcelField(title = "允许进出口证明书号")
    private String credential;

    //进口国
    @ExcelField(title = "进口国")
    private String importCountry;

    @ExcelField(title = "进口时间")
    private Date importDate;

    //规格
    @ExcelField(title = "规格")
    private String size;

    //进口数量(吨)
    @ExcelField(title = "进口数量(吨)")
    private Double quantity;

    //折算比例(黑*600，白*900)
    @ExcelField(title = "折算比例(吨)")
    private Double obversion;

    //进口数量(吨)
    @ExcelField(title = "剩余数量(吨)")
    private Double remainingQty;

    //折算比例(黑*600，白*900)
    @ExcelField(title = "剩余折算(吨)")
    private Double remainingQtyConvert;

}
