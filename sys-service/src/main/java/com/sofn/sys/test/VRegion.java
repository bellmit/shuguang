package com.sofn.sys.test;


import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(sheetName = "黑龙江农垦")
public class VRegion {

  @ExcelField(title = "index")
  private String index;

  @ExcelField(title = "parent")
  private String parent;

  @ExcelField(title = "name")
  private String name;

  @ExcelField(title = "code")
  private String code;

}
