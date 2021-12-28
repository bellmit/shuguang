package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author helong
 *
 * @date 2016/11/23
 */
@DubboService(interfaceClass = TDgapDw3Provider.class, timeout = 10*5*1000)
public class TDgapDw3ProviderImpl implements TDgapDw3Provider {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Resource(name = "dwJdbcTemplate")
  private JdbcTemplate dwJdbcTemplate;

  @Resource(name = "dwDataSource")
  DataSource dataSource;

  @Value("${db.dw.schema}")
  String schemaName;

  @Override
  public List<TDgapDataImportTable> getValidWdTables(String pattern) {

      logger.debug("start getValidWdTables("+pattern+")");
    String sql="select t.table_name,f.comments from user_tables t inner join user_tab_comments f on t.table_name = f.table_name WHERE t.table_name like  ? or f.comments like ? ";
    List<TDgapDataImportTable> ret = new ArrayList<>();
      try {
          if (pattern == null || pattern.equals("")) {
              ret = dwJdbcTemplate.query("select t.table_name,f.comments from user_tables t inner join user_tab_comments f on t.table_name = f.table_name",
                      new RowMapper() {
                          @Override
                          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                              TDgapDataImportTable table = new TDgapDataImportTable();
                              table.setId(rs.getString("table_name"));
                              table.setEnglishName(rs.getString("table_name"));
                              table.setChineseName(rs.getString("comments"));
                              return table;
                          }
                      });
          } else {
              ret = dwJdbcTemplate.query(sql,
                      new Object[]{'%' + pattern + '%', '%' + pattern + '%'},
                      new int[]{java.sql.Types.VARCHAR, java.sql.Types.VARCHAR},
                      new RowMapper() {

                          @Override
                          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                              TDgapDataImportTable table = new TDgapDataImportTable();
                              table.setId(rs.getString("table_name"));
                              table.setEnglishName(rs.getString("table_name"));
                              table.setChineseName(rs.getString("comments"));
                              return table;
                          }
                      });
          }
      }catch(Exception e){
          logger.error("",e);
      }
      logger.debug("end getValidWdTables("+pattern+")");
    return ret;
  }

  @Override
  public TDgapDataImportTable getByTableName(String tableName) {
      logger.debug("start getByTableName("+tableName+")");
    if (Strings.isNullOrEmpty(tableName)) {
      throw new IllegalParameterException("tableName:" + tableName);
    }
      final  TDgapDataImportTable ret = new TDgapDataImportTable();
      try {
          String sql = "select t.table_name,f.comments from user_tables t inner join user_tab_comments f on t.table_name = f.table_name WHERE t.table_name = ? ";
          dwJdbcTemplate.query(sql,
                  new Object[]{tableName},
                  new RowCallbackHandler() {
                      @Override
                      public void processRow(ResultSet rs) throws SQLException {
                          ret.setId(rs.getString("table_name"));
                          ret.setEnglishName(rs.getString("table_name"));
                          ret.setChineseName(rs.getString("comments"));
                      }
                  });
      }catch(Exception e){
          logger.error("",e);
      }
      logger.debug("end getByTableName("+tableName+")");
      return ret;
  }

    @Override
    public long getAllTablesCount() {
        return  dwJdbcTemplate.queryForObject( "select count(0) from user_tables ",long.class);
    }

    @Override
    public List<TDgapDataImportTable> getTablesforPages(Map<String, Object> params) {
        Integer start=(Integer)params.get("start")+1;
        Integer length=(Integer)params.get("length");
        Integer end=start+length-1;
        String sql="SELECT * FROM(SELECT M.*,ROWNUM AS rn FROM (select t.table_name,f.comments from user_tables t inner join " +
                "user_tab_comments f on t.table_name = f.table_name order by t.table_name)M)" +
                "WHERE rn BETWEEN "+start+" AND " +end;
        List<TDgapDataImportTable> ret = new ArrayList<>();
        try {
                ret = dwJdbcTemplate.query(sql,
                        new RowMapper() {
                            @Override
                            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                TDgapDataImportTable table = new TDgapDataImportTable();
                                table.setId(rs.getString("table_name"));
                                table.setEnglishName(rs.getString("table_name"));
                                table.setChineseName(rs.getString("comments"));
                                return table;
                            }
                        });

        }catch(Exception e){
            logger.error("",e);
        }
        return ret;
    }

    @Override
  public List<TDgapDataImportField> getFieldsByTable(String tableName) {
      logger.debug("start getFieldsByTable("+tableName+")");
    List<TDgapDataImportField> ret = new ArrayList<>();
      try {
          if (tableName == null || tableName.equals("")||tableName.equals("NULL")) {
              String sql="select A.column_name ,A.data_type ,A.data_length ,A.data_precision ," +
                      " A.Data_Scale ,A.nullable ,B.comments from user_tab_columns A , user_col_comments B " +
                      "where" +
                      "    A.Table_Name = B.Table_Name" +
                      "    and A.Column_Name = B.Column_Name" +
                      "    and A.Table_Name = ?"   ;
              ret = dwJdbcTemplate.query(sql,
                      new Object[]{tableName},
                      new int[]{java.sql.Types.VARCHAR},
                      new RowMapper() {
                          @Override
                          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                              TDgapDataImportField dataImportField = new TDgapDataImportField();
                              dataImportField.setId(rs.getString("column_name"));
                              dataImportField.setEnglishName(rs.getString("column_name"));
                              dataImportField.setChineseName(rs.getString("comments"));
                              dataImportField.setType(rs.getString("data_type"));
                              dataImportField.setLen(rs.getString("data_length"));
                              return dataImportField;
                          }
                      });
          }else{
              String sql="select A.column_name ,A.data_type ,A.data_length ,A.data_precision ," +
                      " A.Data_Scale ,A.nullable ,B.comments from user_tab_columns A , user_col_comments B, user_tab_comments C " +
                      "where" +
                      "    A.Table_Name = B.Table_Name and A.Table_Name = C.Table_Name" +
                      "    and A.Column_Name = B.Column_Name " +
                      "    and ( A.Table_Name = ? or C.comments = ?)"   ;
              ret = dwJdbcTemplate.query(sql,
                      new Object[]{ tableName , tableName},
                      new int[]{java.sql.Types.VARCHAR, java.sql.Types.VARCHAR},
                      new RowMapper() {
                          @Override
                          public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                              TDgapDataImportField dataImportField = new TDgapDataImportField();
                              dataImportField.setId(rs.getString("column_name"));
                              dataImportField.setEnglishName(rs.getString("column_name"));
                              dataImportField.setChineseName(rs.getString("comments"));
                              dataImportField.setType(rs.getString("data_type"));
                              dataImportField.setLen(rs.getString("data_length"));
                              return dataImportField;
                          }
                      });
          }

      }catch(Exception e){
          logger.error("",e);
      }
      logger.debug("end getFieldsByTable("+tableName+")");
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
  public PageInfo<Map<String, Object>> getDatas(
      String tableId, Integer start, Integer end, String condition) {
    List<TDgapDataImportField> fieldList = getFieldsByTable(tableId);
    List<String> fields = new ArrayList<String>();
    for (TDgapDataImportField s : fieldList) {
      fields.add(s.getEnglishName());
    }

    return getDatas(tableId, start, end, fields, condition);
  }

  @Override
  public PageInfo <Map<String, Object>> getDatas(
      String tableId, Integer start, Integer end, List<String> fields, String condition) {
    StringBuilder sb = new StringBuilder();
    StringBuilder fieldsSb = new StringBuilder();
    for (String field : fields) {
      fieldsSb.append("\""+field+"\"").append(",");
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
      List <Map<String, Object>> list = dwJdbcTemplate.queryForList(sb.toString());
      Integer count = dwJdbcTemplate.queryForObject("select count(0) from "+tableId, Integer.class);
      PageInfo<Map<String, Object>> pageInfo = new PageInfo();
      int startdata = start + 1;
      int length = start -end;
      int pagaNum = ((start + 1) / length) + 1;
      pageInfo.setTotal(count);
      pageInfo.setList(list);
      pageInfo.setPageNum(pagaNum);
      return pageInfo;
  }

}
