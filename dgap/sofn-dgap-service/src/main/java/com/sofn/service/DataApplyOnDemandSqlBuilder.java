package com.sofn.service;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.UUIDUtil;
import com.sofn.dao.dgap.DataImportExpandMapper;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataApplyOnDemandSqlBuilder {


  private static boolean prefixToken = false;

  public static Map<String,String> insertData(String id,
                                       String tableEnglishName,
                                       List<TDgapDataImportField> fieldList, String dwSchemaName) {
    Map<String,String> map= new HashMap<String,String>();

    StringBuffer sql = new StringBuffer();

    StringBuffer columnNames = new StringBuffer();
    columnNames.append("(");
    columnNames.append("\"ID\"").append(",");
    StringBuffer queryColumnNames = new StringBuffer();
    if(prefixToken) {
      queryColumnNames.append("\"TOKEN\" || '-' || \"DATAID\"").append(",");
    }else{
      queryColumnNames.append("\"DATAID\"").append(",");
    }
    /** 对进来的数据进行验证 */
    for (TDgapDataImportField field : fieldList) {
      columnNames.append("\""+field.getEnglishName()+"\"").append(",");
      queryColumnNames.append("\""+field.getEnglishName()+"\"").append(",");
    }
    // 获取当前执行方法的名称
    columnNames.setLength(columnNames.length() - 1);
    columnNames.append(") ");
    queryColumnNames.setLength(queryColumnNames.length() - 1);
    sql.append("insert into "+dwSchemaName+"." + tableEnglishName + " " + columnNames + " " +
            "select "+queryColumnNames+ " from "+tableEnglishName + " where id='"+id+"'");
    System.out.println("拼接的SQL：" + sql.toString());
    map.put("sql",sql.toString());
    return map;
  }

  public static Map<String,String> updateData(String id,
                                        String tableEnglishName,
                                        List<TDgapDataImportField> fieldList, String dwSchemaName) {
    Map<String,String> map= new HashMap<String,String>();

    StringBuffer sql = new StringBuffer();

    StringBuffer columnNames = new StringBuffer();
    columnNames.append("(");
    StringBuffer queryColumnNames = new StringBuffer();
    /** 对进来的数据进行验证 */
    for (TDgapDataImportField field : fieldList) {
      columnNames.append("\""+field.getEnglishName()+"\"").append(",");
      queryColumnNames.append("\""+field.getEnglishName()+"\"").append(",");
    }
    // 获取当前执行方法的名称
    columnNames.setLength(columnNames.length() - 1);
    columnNames.append(") ");
    queryColumnNames.setLength(queryColumnNames.length() - 1);
    if(prefixToken) {
      sql.append("update " + dwSchemaName + "." + tableEnglishName + " set " + columnNames + "=" +
              "(select " + queryColumnNames + " from " + tableEnglishName + " where id='" + id + "') " +
              "where id=(select \"TOKEN\" || '-' || \"DATAID\" from " + tableEnglishName + " where id='" + id + "')");
    }else{
      sql.append("update " + dwSchemaName + "." + tableEnglishName + " set " + columnNames + "=" +
              "(select " + queryColumnNames + " from " + tableEnglishName + " where id='" + id + "') " +
              "where id=(select \"DATAID\" from " + tableEnglishName + " where id='" + id + "')");
    }
    System.out.println("拼接的SQL：" + sql.toString());
    map.put("sql",sql.toString());
    return map;
  }


  public static String deleteData(String id,
                           String tableEnglishName, String dwSchemaName) {
    StringBuffer sql = new StringBuffer();
    /** 对进来的数据进行验证 */
    if(prefixToken) {
      sql.append("DELETE FROM " + dwSchemaName + "." + tableEnglishName)
              .append("  WHERE ID =(select \"TOKEN\" || '-' || \"DATAID\" from " + tableEnglishName + " where id='" + id + "')");
    }else{
      sql.append("DELETE FROM " + dwSchemaName + "." + tableEnglishName)
              .append("  WHERE ID =(select \"DATAID\" from " + tableEnglishName + " where id='" + id + "')");
    }
    // 获取当前执行方法的名称
    System.out.println("拼接的SQL：" + sql.toString());
    return sql.toString();
  }

}
