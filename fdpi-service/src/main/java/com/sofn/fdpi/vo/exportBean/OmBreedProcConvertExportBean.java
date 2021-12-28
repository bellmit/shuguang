package com.sofn.fdpi.vo.exportBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/9 14:52
 **/
@Data
@ExcelSheetInfo(title = "交易折算列表", sheetName = "交易折算列表")
public class OmBreedProcConvertExportBean {
    @ExcelField(title = "欧洲鳗鲡出让企业")
    private String cellComp;
    @ExcelField(title = "欧洲鳗鲡受让企业")
    private String transferComp;
    @ExcelField(title = "允许进出口证明书号")
    private String credential;
    @ExcelField(title = "进口规格")
    private Integer importSize;
    @ExcelField(title = "交易数量")
    private Double dealNum;
    @ExcelField(title = "折算比例")
    private Double obversion;
    @ExcelField(title = "交易日期")
    private Date dealDate;
    @ExcelField(title = "操作人")
    private String operator;
}
