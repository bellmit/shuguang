package com.sofn.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 特殊字符转移工具类
 */
public class SqlEscapeUtils {
  public static final String DB_ORACLE = "oracle";
  public static final String DB_MYSQL = "mysql";

  /**
   * 特殊字符转译，需要配合sql语句使用，sql中加 escape
   * oracle中：select * from A where name like '%\%%' escape ‘\’
   * mysql中: select * from A where name like '%/%%' escape ‘/’
   */
  public static String escape(String param, DB db) {
    if (StringUtils.isBlank(param)) {
      return "";
    }

    switch (db){
      case MYSQL:
        return param.replaceAll("/", "//")
          .replaceAll("%", "/%")
          .replaceAll("_", "/_");
      case ORACLE:
        return param.replaceAll("\\\\","\\\\\\\\")
          .replaceAll("%", "\\\\%")
          .replaceAll("_", "\\\\_");
      default:
        return param;
    }
  }

  public enum DB {
    ORACLE("oracle"),
    MYSQL("mysql");

    private String name;

    DB(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
