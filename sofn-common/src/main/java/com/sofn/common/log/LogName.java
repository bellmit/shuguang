package com.sofn.common.log;

/**
 * 日志名称
 */
public enum LogName {
  //用户操作日志
  OPERATE("operate");

  private String logName;

  LogName(String logName){
    this.logName = logName;
  }

  public String getLogName() {
    return logName;
  }

  public void setLogName(String logName) {
    this.logName = logName;
  }
}
