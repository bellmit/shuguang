package com.sofn.common.log;

/**
 * 日志类型
 */
public enum LogType {
  //查询
  get("查询"),
  //新增
  add("新增"),
  //修改
  update("修改"),
  //删除
  del("删除");

  private String typeName;

  LogType(String typeName){
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String logName) {
    this.typeName = typeName;
  }
}
