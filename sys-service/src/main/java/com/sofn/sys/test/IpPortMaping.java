package com.sofn.sys.test;


import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(sheetName = "测试环境和试用环境对应")
public class IpPortMaping {

  @ExcelField(title = "测试环境内网IP")
  private String testInIp;

  @ExcelField(title = "测试环境内网端口")
  private String testInPort;

  @ExcelField(title = "测试环境外网IP")
  private String testOutIp;

  @ExcelField(title = "测试环境外网端口")
  private String testOUtPort;

  @ExcelField(title = "试用环境内网IP")
  private String trialInIp;

  @ExcelField(title = "试用环境内网端口")
  private String trialInPort;

  @ExcelField(title = "试用环境外网IP")
  private String trialOutIp;

  @ExcelField(title = "试用环境外网端口")
  private String trialOUtPort;

}
