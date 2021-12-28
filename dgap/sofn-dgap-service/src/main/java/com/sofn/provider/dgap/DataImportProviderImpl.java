package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.UUIDUtil;
import com.sofn.dao.dgap.DataImportExpandMapper;
import com.sofn.dao.dgap.TDgapResourceApplicationExpandMapper;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
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
import org.springframework.transaction.annotation.Transactional;


/** Created by weiq on 2016/12/6. */
@DubboService(interfaceClass = DataImportProvider.class, timeout = 100*5*1000)
public class DataImportProviderImpl implements DataImportProvider {

  private static final Logger logger =
      LoggerFactory.getLogger(DataImportProviderImpl.class.getName());

  Base64 bs = new Base64(-1);

  @Value("${dataImport.applydataRealtime.enable}")
  String applydataRealtime = "false";

  @Resource(name = "preJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @Autowired private DataImportExpandMapper dataImportExpandMapper;

  @Autowired private TDgapResourceApplicationExpandMapper tDgapResourceApplicationExpandMapper;
  private LobHandler lobHandler = new DefaultLobHandler();

  public Map<String,String> insertData(String resourceId,String token, RowData rowData, Constants.DataImportOp op) {
    Map<String,String> map= new HashMap<String,String>();
    String uuid = UUIDUtil.getUUID();
    // 获取到与资源对应的表的信息
    TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
    // 获取表名
    String tableEnglishName = table.getEnglishName();
    String tableId = table.getId();
    logger.debug("####################表名==== " + tableEnglishName);
    String seqName="seq_"+tableEnglishName;//得到oracle中的自增序列名
    String seqSql="select "+seqName+".Nextval from DUAL";
    String seq=(String)jdbcTemplate.queryForObject(seqSql,java.lang.String.class);//得到下个自增的值

    // 获取表中字段信息
    List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByResourceId(tableId);
    // 传进来的数据
    List<FieldData> fieldsData = rowData.getFieldsData();
    String rowDataId = rowData.getId();
    StringBuffer sql = new StringBuffer();
    StringBuffer columnNames = new StringBuffer();
    columnNames.append("(");
    columnNames.append("\"ID\"").append(",");
    columnNames.append("\"DATAID\"").append(",");
    columnNames.append("\"TOKEN\"").append(",");
    columnNames.append("\"STATUS\"").append(",");
    columnNames.append("\"DATA_IMPORT_TIME\"").append(",");
    columnNames.append("\"MARK\"").append(",");
    StringBuffer values = new StringBuffer();
    values.append("values(");
    values.append("'").append(uuid).append("'").append(",");
    values.append("'").append(rowDataId).append("'").append(",");
    values.append("'").append(token).append("'").append(",");
    values.append("'").append(getDefaultDataStatus()).append("'").append(",");
    DateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStrs = formats.format( new Date());
    values.append("to_timestamp('").append(dateStrs).append("','yyyy-MM-dd hh24:mi:ss')").append(",");
    values.append("'").append(seq).append("'").append(",");
    /** 对进来的数据进行验证 */
    if(fieldsData == null){
      fieldsData = new ArrayList<>();
    }
    for (FieldData data : fieldsData) {
      String columnName = data.getColumnName();
      if(columnName.equalsIgnoreCase("ID")||columnName.equalsIgnoreCase("DATAID")
              ||columnName.equalsIgnoreCase("TOKEN")||columnName.equalsIgnoreCase("STATUS")
              ||columnName.equalsIgnoreCase("DATA_IMPORT_TIME")||columnName.equalsIgnoreCase("MARK")
              ||columnName.equalsIgnoreCase("OPREATE")){
        continue;
      }
      TDgapDataImportField field = findColumnByName(fieldList, columnName);
      if (field == null) {
        // 数据源错误
        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      }
      // 获取数据字段的内容
      String type = field.getType();
      String value = data.getValue();

      if(value==null||"".equals(value)){
        String toValue=null;
        columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
        values.append(toValue).append(",");
        continue;
      }
      // 文本类型数据验证
      if (Constants.FieldType.文本.type().equals(type)) {
        if(value.contains("'")){
          value=value.replace("'", "''");
        }
        try {
          int len = Integer.parseInt(field.getLen());
          if (value.length() <= len ) {
            columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
            values.append("'").append(value).append("'").append(",");
          } else {
            throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
          }
        } catch (Exception e) {
          logger.error("", e);
          throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        }
      }
      // 小数位最大4位 验证类型为小数型的数据
      if (Constants.FieldType.小数.type().equals(type)) {
        try {
          // 先验证是否是一个小数类型 字符型转成double型
          new BigDecimal(value);
//          Double.parseDouble(value);
          // 判断长度
          if (value.contains(".")) {
            // 从小数点处截取数据成整数部分和小数部分
            String[] ar = value.split("\\.");
            String parti = ar[0];
            if (parti.length() > 38) {
              throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            }
            String partf = ar[1];
            if (partf.length() > 4) {
              throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            }
            columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
            values.append("'").append(data.getValue()).append("'").append(",");
          } else {
            columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
            values.append("'").append(data.getValue()).append("'").append(",");
//            throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
          }
        } catch (Exception e) {
          logger.error("", e);
          throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        }
      }
      // 验证数据类型为数值的数据
      if (Constants.FieldType.数值.type().equals(type)) {
        try {
          int len = Integer.parseInt(field.getLen());
          new BigDecimal(value);
//          Integer.parseInt(value);
          if (value.length() <= len && value.length() >= 1) {
            columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
            values.append("'").append(data.getValue()).append("'").append(",");
          } else {
            throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
          }
        } catch (Exception e) {
          logger.error("", e);
          throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        }
      }
      // 验证数据类型为时间的数据
      if (Constants.FieldType.时间.type().equals(type)) {

        DateFormat formatter = null;
        if(value.contains("/") || value.contains("-")){
          formatter = new SimpleDateFormat("yyyy MM dd");
          String deleteString = "";
          for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != '/' && value.charAt(i) != '-') {
              deleteString += value.charAt(i);
            }else{
              deleteString+= " ";
            }
          }
          value=deleteString.toString();
        }else{
           formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
          // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
          formatter.setLenient(true);
          Date parse =(Date) formatter.parse(value.toString());
          if (parse instanceof Date) {
            columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
            values
                    .append("to_date('")
                    .append(value)
                    .append("','yyyy-MM-dd hh24:mi:ss')")
                    .append(",");
          } else {
            throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
          }
        } catch (Exception e) {
          logger.error("", e);
          throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        }
      }
      // 验证数据类型为二进制数据的数据
      if (Constants.FieldType.二进制数据.type().equals(type)) {
        if (value != null && value.length() != 0){
          columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
          values.append( "?").append(",");
          map.put("bigData",value);
        }
      }

      // 验证时间戳
      if (Constants.FieldType.时间戳.type().equals(type)) {
        try {
          // 先判断数据类型
          Long.parseLong(value);
          if (value.length() >= 13) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(Long.parseLong(value));
            String dateStr = format.format(date);
            columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
            values
                    .append("to_timestamp('")
                    .append(dateStr)
                    .append("','yyyy-MM-dd hh24:mi:ss')")
                    .append(",");
          } else {
            //                        System.out.println("数据格式不匹配");
            throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
          }
        } catch (Exception e) {
          logger.error("", e);
          throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        }
      }
    }
    // 获取当前执行方法的名称
    values.append("'").append(op.code()).append("'").append(",");
    columnNames.append("\"OPREATE\"").append(",");
    columnNames.setLength(columnNames.length() - 1);
    columnNames.append(") ");
    values.setLength(values.length() - 1);
    values.append(")");
    sql.append("insert into " + tableEnglishName + " " + columnNames + " " + values);
    System.out.println("拼接的SQL：" + sql.toString());
    StringEscapeUtils.escapeSql(sql.toString());
    map.put("sql",sql.toString());
    return map;
  }

  private TDgapDataImportField findColumnByName(
      List<TDgapDataImportField> fieldList, String columnName) {
    TDgapDataImportField dataImportField = null;
    for (TDgapDataImportField dgapDataImportField : fieldList) {
      String filedEnglishName = dgapDataImportField.getEnglishName();
      if (columnName.equals(filedEnglishName)) {
        dataImportField = new TDgapDataImportField();
        String len = dgapDataImportField.getLen();
        String type = dgapDataImportField.getType();
        dataImportField.setEnglishName(filedEnglishName);
        dataImportField.setLen(len);
        dataImportField.setType(type);
      }
    }
    return dataImportField;
  }

  @Override
  public DataImportResult addData(String token,String resourceId, RowData rowData) {
    DataImportResult dataImportResult = new DataImportResult();
    try {
      Map<String,String> insertData = insertData(resourceId, token, rowData, Constants.DataImportOp.添加);
      String sql=insertData.get("sql");
      String bigData=insertData.get("bigData");
      StringEscapeUtils.escapeSql(sql);
      if (sql.length() <= 3) {
        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      } else {
        if (bigData==null|| bigData.length()<=0){
          jdbcTemplate.execute(sql);
        }else{
             List<String> bigdataValues= getBIgDataList(resourceId, rowData);//大数据字段 值的集合
            jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
              @Override
              protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                try {
                  for(int i=0;i<bigdataValues.size();i++){
                    String bigdataValue=bigdataValues.get(i);
                    byte[] decoded = bs.decodeBase64(bigdataValue);
                    lobCreator.setBlobAsBytes(ps,i+1,decoded);
                  }
                } catch (Exception e) {
                  logger.error("", e);
                }
              }
            });
        }
        dataImportResult.setSuccess(true);
      }
    } catch (Exception e) {
      logger.error("", e);
      dataImportResult.setSuccess(false);
      if (e.equals(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode())) {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.DATA_ERROR.getErrorInnerDesc());
      } else {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorInnerDesc());
      }
    }
    return dataImportResult;
  }

  @Override
  public DataImportResult addDataLog(String token,String resourceId, RowData rowData) {
    DataImportResult dataImportResult = new DataImportResult();
    try {
      Map<String,String> insertData = insertData(resourceId, token, rowData, Constants.DataImportOp.添加日志);
      String sql=insertData.get("sql");
      String bigData=insertData.get("bigData");
      StringEscapeUtils.escapeSql(sql);
      if (sql.length() <= 3) {
        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      } else {
        if (bigData==null|| bigData.length()<=0){
          jdbcTemplate.execute(sql);
        }else{
          List<String> bigdataValues= getBIgDataList(resourceId, rowData);//大数据字段 值的集合
          jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
              try {
                for(int i=0;i<bigdataValues.size();i++){
                  String bigdataValue=bigdataValues.get(i);
                  byte[] decoded = bs.decodeBase64(bigdataValue);
                  lobCreator.setBlobAsBytes(ps,i+1,decoded);
                }
              } catch (Exception e) {
                logger.error("", e);
              }
            }
          });
        }
        dataImportResult.setSuccess(true);
      }
    } catch (Exception e) {
      logger.error("", e);
      dataImportResult.setSuccess(false);
      if (e.equals(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode())) {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.DATA_ERROR.getErrorInnerDesc());
      } else {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorInnerDesc());
      }
    }
    return dataImportResult;
  }

  //获取大数据字段的值 的集合
  private List<String> getBIgDataList(String resourceId, RowData rowData) {
    List<FieldData> fieldsData = rowData.getFieldsData();
    // 获取到与资源对应的表的信息
    TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
    // 获取表名
    String tableId = table.getId();
    // 获取表中字段信息
    List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByResourceId(tableId);
    List<String> bigdataValues=new ArrayList<String>();
    for (FieldData data : fieldsData) {
      String columnName = data.getColumnName();
      TDgapDataImportField field = findColumnByName(fieldList, columnName);
      if (field == null) {
        // 数据源错误
        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      }
      // 获取数据字段的内容
      String type = field.getType();
      String value = data.getValue();
      if (Constants.FieldType.二进制数据.type().equals(type)) {
        if (value != null && value.length() != 0){
          bigdataValues.add(value);
        }
      }
    }
    return  bigdataValues;
  }

  @Override
  public DataImportResult deleteData(String token,String resourceId, RowData rowData) {
    DataImportResult dataImportResult = new DataImportResult();
    try {
      Map<String,String> insertData = insertData(resourceId,token, rowData, Constants.DataImportOp.删除);
      String sql=insertData.get("sql");
      String bigData=insertData.get("bigData");
      StringEscapeUtils.escapeSql(sql);
      if (sql.length() <= 3) {
        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      } else {
        if (bigData==null|| bigData.length()<=0){
          jdbcTemplate.execute(sql);
        }else{
          List<String> bigdataValues= getBIgDataList(resourceId, rowData);//大数据字段 值的集合
          jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
              try {
                for(int i=0;i<bigdataValues.size();i++){
                  String bigdataValue=bigdataValues.get(i);
                  byte[] decoded = bs.decodeBase64(bigdataValue);
                  lobCreator.setBlobAsBytes(ps,i+1,decoded);
                }
              } catch (Exception e) {
                logger.error("", e);
              }
            }
          });
        }
        dataImportResult.setSuccess(true);
      }
    } catch (Exception e) {
      logger.error("", e);
      dataImportResult.setSuccess(false);
      if (e.equals(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode())) {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      } else {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorInnerDesc());
      }
    }
    return dataImportResult;
  }

  @Override
  public DataImportResult updateData(String token,String resourceId, RowData rowData) {
    DataImportResult dataImportResult = new DataImportResult();
    try {
      Map<String,String> insertData = insertData(resourceId,token, rowData, Constants.DataImportOp.修改);
      String sql=insertData.get("sql");
      String bigData=insertData.get("bigData");
      StringEscapeUtils.escapeSql(sql);
      if (sql.length() <= 3) {
        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
      } else {
        if (bigData==null|| bigData.length()<=0){
          jdbcTemplate.execute(sql);
        }else{
          List<String> bigdataValues= getBIgDataList(resourceId, rowData);//大数据字段 值的集合
          jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
              try {
                for(int i=0;i<bigdataValues.size();i++){
                  String bigdataValue=bigdataValues.get(i);
                  byte[] decoded = bs.decodeBase64(bigdataValue);
                  lobCreator.setBlobAsBytes(ps,i+1,decoded);
                }
              } catch (Exception e) {
                logger.error("", e);
              }
            }
          });
        }
        dataImportResult.setSuccess(true);
      }
    } catch (Exception e) {
      logger.error("数据更新时发生异常：", e);
      dataImportResult.setSuccess(false);
      if (Constants.ImportDataErrorType.DATA_ERROR.getErrorCode().equals(e.getLocalizedMessage())) {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.DATA_ERROR.getErrorInnerDesc());
      } else {
        dataImportResult.setErrorCode(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorCode());
        dataImportResult.setErrorDesc(Constants.ImportDataErrorType.SYSTEM_ERR.getErrorInnerDesc());
      }
    }
    return dataImportResult;
  }

  @Override
  public TDgapDataImportTable getTableNameByResourceId(String resourceId) {
    return dataImportExpandMapper.getTableNameByResourceId(resourceId);
  }

  @Override
  public List<TDgapDataImportField> getFieldNameByResourceId(String tableId) {
    return (List<TDgapDataImportField>) dataImportExpandMapper.getFieldNameByResourceId(tableId);
  }

  @Override
  @Transactional(value="dataImportManager",timeout = 60*60)
  public DataImportResult addBatch(String token,String resourceId, List<RowData> rowData) {
    /*if (rowData.size() > MAXLENGTH) {
      throw new RuntimeException(Constants.ImportDataErrorType.DATALEN_ERROR.getErrorCode());
    }*/
    DataImportResult dataImportResult = new DataImportResult();
    for (RowData data : rowData) {
      Map<String,String> insertData = insertData(resourceId,token, data, Constants.DataImportOp.批量添加);
      String sql=insertData.get("sql");
      String bigData=insertData.get("bigData");
      StringEscapeUtils.escapeSql(sql);
      if (bigData==null|| bigData.length()<=0){
        try {
          jdbcTemplate.execute(sql);
        }catch (Exception e) {
          logger.error("", e);
        }
      }else{
        List<String> bigdataValues= getBIgDataList(resourceId, data);//大数据字段 值的集合
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
          @Override
          protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
            try {
                for(int i=0;i<bigdataValues.size();i++){
                  String bigdataValue=bigdataValues.get(i);
                  byte[] decoded = bs.decodeBase64(bigdataValue);
                  lobCreator.setBlobAsBytes(ps,i+1,decoded);
                }
            } catch (Exception e) {
              logger.error("", e);
            }
          }
        });
      }
    }
    dataImportResult.setSuccess(true);
    return dataImportResult;
  }

  @Override
  public List<RowData> sampleData(String token, int size) {
    Map<String, Object> map =
        tDgapResourceApplicationExpandMapper.getTdgapResourceApplication(token);
    if (null == map) {
      throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
    }
    String resourceId = map.get("WS_ID").toString();
    // 获取到与资源对应的表的信息
    TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
    if (null == table) {
      throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
    }
    // 获取表中字段信息
    List<TDgapDataImportField> fieldList =
        dataImportExpandMapper.getFieldNameByResourceId(table.getId());
    List<RowData> rowDataList = new ArrayList<>();
    RowData rowData = null;
    for (int i = 0; i < size; i++) {
      rowData = new RowData();
      rowData.setId(UUIDUtil.getUUID());
      List<FieldData> fieldsData = new ArrayList<>();
      FieldData fieldData = null;
      for (TDgapDataImportField field : fieldList) {
        fieldData = new FieldData();
        fieldData.setColumnName(field.getEnglishName());
        // 文本类型数据验证
        if (Constants.FieldType.文本.type().equals(field.getType())) {
          Random random = new Random();
          String k = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
          // 添加不同的随机字符串
          StringBuffer str = new StringBuffer();
          try {
            int stringLength = random.nextInt(Integer.parseInt(field.getLen()));
            for (int j = 0; j <= stringLength; j++) {
              int number = random.nextInt(k.length());
              str.append(k.charAt(number));
            }
          } catch (Exception e) {
            throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
          }
          fieldData.setValue(str.toString());
          logger.debug("成功添加字符串：" + str);
        }
        // 验证数据类型为数值的数据
        if (Constants.FieldType.数值.type().equals(field.getType())) {
          Random random = new Random();
          int number = random.nextInt(9);
          fieldData.setValue("" + number);
          logger.debug("成功添加数值：" + number);
        }
        // 验证时间戳
        if (Constants.FieldType.时间戳.type().equals(field.getType())) {
          fieldData.setValue("" + System.currentTimeMillis());
          logger.debug("成功添加时间戳：" + System.currentTimeMillis());
        }
        // 验证数据类型为时间的数据
        if (Constants.FieldType.时间.type().equals(field.getType())) {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          formatter.setLenient(false);
          String dateStr = formatter.format(System.currentTimeMillis());
          fieldData.setValue(dateStr);
          logger.debug("成功添加时间：" + dateStr);
        }
        if (Constants.FieldType.二进制数据.type().equals(field.getType())) {
          String dateStr = "二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试" +
                  "二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试" +
                  "二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试二进制数据测试";
          try {
            fieldData.setValue(bs.encodeBase64String(dateStr.getBytes("utf8")));
          } catch (IOException e) {
            logger.debug("",e);
          }
          logger.debug("成功添加二进制数据：" + dateStr);
        }
        // 小数位最大4位 验证类型为小数型的数据
        if (Constants.FieldType.小数.type().equals(field.getType())) {
          Random random = new Random();
          int tail = random.nextInt(9);
          fieldData.setValue("1." + tail);
          logger.debug("成功添加小数：1." + tail);
        }
        fieldsData.add(fieldData);
      }
      rowData.setFieldsData(fieldsData);
      rowDataList.add(rowData);
    }
    logger.debug("生成的数据：" + rowDataList);
    return rowDataList;
  }

  @Override
  public Integer getMark(String resourceId) {
    // 获取到与资源对应的表的信息
    TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
    // 获取表名
    String tableEnglishName = table.getEnglishName();
    String sql="SELECT max(TO_NUMBER(MARK)) FROM "+tableEnglishName;
    Integer mark=jdbcTemplate.queryForObject(sql,Integer.class);
    return mark;
  }

  private String getDefaultDataStatus(){
    if("true".equals(applydataRealtime)){
      return Constants.DataImportStatus.自动导入.code();
    }else{
      return Constants.DataImportStatus.未审核.code();
    }
  }
}
