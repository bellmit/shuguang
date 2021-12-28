package com.sofn.common.excel.test.regionexport;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * 行政区划导出数据
 * @author heyongjie
 * @date 2019/10/17 15:34
 */
@Data
@ExcelSheetInfo(title = "行政区划信息")
public class SysRegionExportBean {

    @ExcelField(title = "省")
    private String sheng;

    @ExcelField(title = "市/州")
    private String shi;

    @ExcelField(title = "区/县")
    private String  xian;


}
