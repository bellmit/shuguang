package com.sofn.provider.dgap;

import com.google.common.base.Strings;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.model.dgap.Column;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.Tables;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.service.DbUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * author helong
 *
 * @date 2016/11/23
 */
@DubboService(interfaceClass = TDgapDw2Provider.class, timeout = 10*5*1000)
@Transactional("dwTM")
public class TDgapDw2ProviderImpl implements TDgapDw2Provider {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Resource(name = "dwDataSource")
  DataSource dataSource;

  @Value("${db.dw.schema}")
  String schemaName;

  @Override
  public List<TDgapDataImportTable> getValidWdTables(String pattern) {
    List<TDgapDataImportTable> ret = new ArrayList<>();
    try {
      Map<String, Tables> tables =
          DbUtils.getTables(getConnection(), schemaName, "%" + pattern + "%");
      for (Tables t : tables.values()) {
        TDgapDataImportTable dataImportTable = new TDgapDataImportTable();
        dataImportTable.setId(t.getTableName());
        dataImportTable.setEnglishName(t.getTableName());
        dataImportTable.setChineseName(t.getRemarks());
        ret.add(dataImportTable);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ret;
  }

  @Override
  public TDgapDataImportTable getByTableName(String tableName) {
    if (Strings.isNullOrEmpty(tableName)) {
      throw new IllegalParameterException("tableName:" + tableName);
    }

    Map<String, Tables> tables = null;
    try {
      tables = DbUtils.getTables(getConnection(), schemaName, tableName);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    for (Map.Entry<String, Tables> e : tables.entrySet()) {
      Tables t = e.getValue();
      if (t != null) {
        TDgapDataImportTable dataImportTable = new TDgapDataImportTable();
        dataImportTable.setId(t.getTableName());
        dataImportTable.setEnglishName(t.getTableName());
        dataImportTable.setChineseName(t.getRemarks() == null ? "" : t.getRemarks());
        return dataImportTable;
      }
    }
    throw new RuntimeException(tableName + " not found");
  }

  @Override
  public List<TDgapDataImportField> getFieldsByTable(String tableName) {
    List<TDgapDataImportField> ret = new ArrayList<>();
    try {
      Map<String, Column> fields = DbUtils.getAllColumns(getConnection(), schemaName, tableName);
      for (Column c : fields.values()) {
        TDgapDataImportField dataImportField = new TDgapDataImportField();
        dataImportField.setId(c.getcolumnName());
        dataImportField.setEnglishName(c.getcolumnName());
        dataImportField.setChineseName(c.getColumnRemarks() == null ? "" : c.getColumnRemarks());
        dataImportField.setType(convertToColumnType(c.getColumnType()));
        dataImportField.setLen(Integer.toString(c.getColumnSize()));
        ret.add(dataImportField);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ret;
  }

  @Override
  public List<TDgapDataImportField> getByFieldNames(String tableName, String[] fields) {
    List<TDgapDataImportField> list = getFieldsByTable(tableName);
    Arrays.sort(fields);

    //解决集合remove后，元素向前移动导致报错
    for (int i =0;i<list.size();i++) {
      TDgapDataImportField importField=list.get(i);
      String columnName = importField.getEnglishName();
      if (!Arrays.asList(fields).contains(columnName)) {
        list.remove(importField);
        i=i-1; //解决集合remove后，元素向前移动导致报错
      }
    }
    return list;
  }

  @Override
  public List<Map<String, Object>> getDatas(
      String tableId, Integer start, Integer end, String condition) {
    List<TDgapDataImportField> fieldList = getFieldsByTable(tableId);
    List<String> fields = new ArrayList<String>();
    for (TDgapDataImportField s : fieldList) {
      fields.add(s.getEnglishName());
    }

    return getDatas(tableId, start, end, fields, condition);
  }

  @Override
  public List<Map<String, Object>> getDatas(
      String tableId, Integer start, Integer end, List<String> fields, String condition) {
    StringBuilder sb = new StringBuilder();
    StringBuilder fieldsSb = new StringBuilder();
    for (String field : fields) {
      fieldsSb.append(field).append(",");
    }
    fieldsSb.setLength(fieldsSb.length() - 1);

    sb.append("select ")
        .append(fieldsSb)
        .append(" from (")
        .append("select t.*,rownum rn from ")
        .append(tableId)
        .append(" t where rownum<=" + end);

    if (condition != null) {
      sb.append(condition);
    }

    sb.append(") t1 where t1.rn>=" + start);
    JdbcTemplate template = new JdbcTemplate(dataSource);
    List<Map<String, Object>> list = template.queryForList(sb.toString());
    return list;
  }

  private Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("dataSource.getConnection()", e);
    }
  }

  String convertToColumnType(String jdbcType) {

    String ret = Constants.FieldType.文本.type();

    if (jdbcType.equals(Integer.toString(Types.VARCHAR))
        || jdbcType.equals(Integer.toString(Types.CHAR))
        || jdbcType.equals(Integer.toString(Types.LONGVARCHAR))
        || jdbcType.equals(Integer.toString(Types.DECIMAL))
        || jdbcType.equals(Integer.toString(Types.NUMERIC))) {
      ret = Constants.FieldType.文本.type();
    } else if (jdbcType.equals(Integer.toString(Types.TINYINT))
        || jdbcType.equals(Integer.toString(Types.SMALLINT))
        || jdbcType.equals(Integer.toString(Types.INTEGER))
        || jdbcType.equals(Integer.toString(Types.BIGINT))) {
      ret = Constants.FieldType.数值.type();
    } else if (jdbcType.equals(Integer.toString(Types.REAL))
        || jdbcType.equals(Integer.toString(Types.FLOAT))
        || jdbcType.equals(Integer.toString(Types.DOUBLE))) {
      ret = Constants.FieldType.小数.type();
    } else if (jdbcType.equals(Integer.toString(Types.DATE))
        || jdbcType.equals(Integer.toString(Types.TIME))) {
      ret = Constants.FieldType.时间.type();
    } else if (jdbcType.equals(Integer.toString(Types.TIMESTAMP))) {
      ret = Constants.FieldType.时间戳.type();
    } else if (jdbcType.equals(Integer.toString(Types.BLOB))) {
      ret = Constants.FieldType.二进制数据.type();
    } else {
        ret = "未支持";
        logger.error("unsupported jdbc type:" + jdbcType);
    }
    return ret;
  }
}
