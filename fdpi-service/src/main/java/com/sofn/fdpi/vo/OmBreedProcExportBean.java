package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Description 欧鳗养殖企业和加工企业报表与导出数据格式
 * @Author wg
 * @Date 2021/5/18 9:23
 **/
@Data
@ExcelSheetInfo(sheetName = "交易比例折算列表", title = "交易比例折算列表")
public class OmBreedProcExportBean {
    @ExcelField(title = "欧洲鳗鲡出让企业")
    private String cellComp;
    @ExcelField(title = "欧洲鳗鲡受让企业")
    private String transferComp;
    @ExcelField(title = "允许进出口证明书号")
    private String credential;
    @ExcelField(title = "进口规格")
    private String importSize;
    @ExcelField(title = "海关报送单号")
    private String customsList;
    @ExcelField(title = "交易数量(吨)")
    private Double dealNum;
    @ExcelField(title = "折算比例(吨)")
    private Double obversion;
    @ExcelField(title = "交易日期")
    private Date dealDate;
    @ExcelField(title = "操作人")
    private String operator;
}
