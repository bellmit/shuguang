package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 进口比例折算导出对象
 * @Author wg
 * @Date 2021/5/14 11:08
 **/
@Data
@ExcelSheetInfo(title = "进口企业列表", sheetName = "进口企业列表")
public class OmImportExportBean implements Serializable {

    //进口企业
    @ExcelField(title = "进口企业")
    private String importMan;

    //《允许进出口证明书》号
    @ExcelField(title = "允许进出口证明书号")
    private String credential;

    //进口国
    @ExcelField(title = "进口国")
    private String importCountry;

    //规格
    @ExcelField(title = "规格")
    private String size;

    //进口数量(吨)
    @ExcelField(title = "进口数量(吨)")
    private Double quantity;

    //折算比例(黑*600，白*900)
    @ExcelField(title = "折算比例(吨)")
    private Double obversion;

    //操作人（企业名称）
    @ExcelField(title = "操作人")
    private String operator;

    //创建时间
    @ExcelField(title = "创建时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
}


