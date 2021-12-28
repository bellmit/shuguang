package com.sofn.fdpi.vo.importBean;


import com.sofn.common.excel.annotation.ExcelField;
import lombok.Data;

@Data
public class UploadCodeExcelBean {
    @ExcelField(title = "编码")
    private String code;
}
