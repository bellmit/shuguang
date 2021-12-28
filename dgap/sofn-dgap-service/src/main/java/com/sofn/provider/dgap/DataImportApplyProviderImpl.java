package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
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
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.codec.binary.Base64;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;


import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@DubboService(interfaceClass = DataImportApplyProvider.class)
@Transactional(value = "dwTM", rollbackFor = {DataImportApplyExeption.class, RuntimeException.class})
public class DataImportApplyProviderImpl implements DataImportApplyProvider {

    private static final Logger logger =
            LoggerFactory.getLogger(DataImportApplyProviderImpl.class.getName());

    @Resource(name = "dwJdbcTemplate")
    private JdbcTemplate dwJdbcTemplate;

    @Value("${dataImport.rowData.length}")
    int MAXLENGTH;

    private boolean prefixToken = false;

    Base64 bs = new Base64(-1);

    @Autowired
    private DataImportExpandMapper dataImportExpandMapper;

    @Autowired
    private TDgapResourceApplicationExpandMapper tDgapResourceApplicationExpandMapper;

    private LobHandler lobHandler = new DefaultLobHandler();

    @Override
    @Transactional(value = "dwTM",
            rollbackFor = {DataImportApplyExeption.class, RuntimeException.class},
            isolation = Isolation.SERIALIZABLE)
    public DataImportResult applyDataUpsert(String token, String resourceId, RowData rowData) throws DataImportApplyExeption {
        DataImportResult dataImportResult = applyDataUpdate(token, resourceId, rowData);
        if (dataImportResult.isSuccess()) {
        } else if (StringUtils.isNotBlank(dataImportResult.getErrorCode())) {
            throw new DataImportApplyExeption();
        } else {
            dataImportResult = applyDataAdd(token, resourceId, rowData);
        }
        return dataImportResult;
    }


    @Override
    public DataImportResult applyDataAdd(String token, String resourceId, RowData rowData) throws DataImportApplyExeption {
        DataImportResult dataImportResult = new DataImportResult();
        try {
            Map<String, String> insertData = insertData(token, resourceId, rowData);
            String sql = insertData.get("sql");
            String bigData = insertData.get("bigData");
            StringEscapeUtils.escapeSql(sql);
            if (sql.length() <= 3) {
                throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            } else {
                if (bigData == null || bigData.length() <= 0) {
                    dwJdbcTemplate.execute(sql);
                } else {
                    List<String> bigdataValues = getBIgDataList(resourceId, rowData);//大数据字段 值的集合
                    dwJdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                        @Override
                        protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                            try {
                                for (int i = 0; i < bigdataValues.size(); i++) {
                                    String bigdataValue = bigdataValues.get(i);
                                    byte[] decoded = bs.decodeBase64(bigdataValue);
                                    lobCreator.setBlobAsBytes(ps, i + 1, decoded);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                dataImportResult.setSuccess(true);
            }
        } catch (RuntimeException e) {

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
    public DataImportResult applyDataUpdate(String token, String resourceId, RowData rowData) throws DataImportApplyExeption {
        DataImportResult dataImportResult = new DataImportResult();
        try {
            boolean result = updateData(token, resourceId, rowData);
            dataImportResult.setSuccess(result);
        } catch (RuntimeException e) {
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
    public DataImportResult applyDataDelete(String token, String resourceId, RowData rowData) throws DataImportApplyExeption {
        DataImportResult dataImportResult = new DataImportResult();
        try {
            String insertData = deleteData(token, resourceId, rowData, Constants.DataImportOp.删除);
            if (insertData.length() <= 3) {
                throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            } else {
                dwJdbcTemplate.execute(insertData);
                dataImportResult.setSuccess(true);
            }
        } catch (RuntimeException e) {
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
    public DataImportResult applyDataBatchAdd(String token, String resourceId, List<RowData> rowData) throws DataImportApplyExeption {
//    if (rowData.size() > MAXLENGTH) {
//      throw new RuntimeException(Constants.ImportDataErrorType.DATALEN_ERROR.getErrorCode());
//    }
        DataImportResult dataImportResult = new DataImportResult();
        for (RowData data : rowData) {
            applyDataUpsert(token, resourceId, data);
        }
        dataImportResult.setSuccess(true);
        return dataImportResult;
    }

    public Map<String, String> insertData(String token, String resourceId, RowData rowData) {
        Map<String, String> map = new HashMap<String, String>();
        String uuid = UUIDUtil.getUUID();
        // 获取到与资源对应的表的信息
        TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
        // 获取表名
        String tableEnglishName = table.getEnglishName();
        String tableId = table.getId();
        logger.debug("####################表名==== " + tableEnglishName);
        // 获取表中字段信息
        List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByResourceId(tableId);
        // 传进来的数据
        List<FieldData> fieldsData = rowData.getFieldsData();
        String rowDataId = rowData.getId();
        StringBuffer sql = new StringBuffer();
        StringBuffer columnNames = new StringBuffer();
        columnNames.append("(");
        columnNames.append("\"ID\"").append(",");
        StringBuffer values = new StringBuffer();
        values.append("values(");
        if (rowDataId == null || rowDataId.equals("")) {
            values.append("'").append(UUIDUtil.getUUID()).append("'").append(",");
        } else {
            if (prefixToken) {
                values.append("'").append(token + "-" + rowDataId).append("'").append(",");
            } else {
                values.append("'").append(rowDataId).append("'").append(",");
            }
        }
        /** 对进来的数据进行验证 */
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

            if (value == null || "".equals(value)) {
                columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
                values.append("NULL").append(",");
                continue;
            }
            // 文本类型数据验证
            if (Constants.FieldType.文本.type().equals(type)) {
                try {
                    int len = Integer.parseInt(field.getLen());
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
                if (value.contains("/")) {
                    formatter = new SimpleDateFormat("yyyy MM dd");
                    String deleteString = "";
                    for (int i = 0; i < value.length(); i++) {
                        if (value.charAt(i) != '/') {
                            deleteString += value.charAt(i);
                        } else {
                            deleteString += " ";
                        }
                    }
                    value = deleteString.toString();
                } else {
                    value = value.substring(0, 19);
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                try {
                    // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
                    formatter.setLenient(false);
                    Date parse = formatter.parse(value);
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
                if (value != null && value.length() != 0) {
                    columnNames.append("\"" + field.getEnglishName() + "\"").append(",");
                    values.append("?").append(",");
                    map.put("bigData", value);
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
        columnNames.setLength(columnNames.length() - 1);
        columnNames.append(") ");
        values.setLength(values.length() - 1);
        values.append(")");
        sql.append("insert into " + tableEnglishName + " " + columnNames + " " + values);
        System.out.println("拼接的SQL：" + sql.toString());
        map.put("sql", sql.toString());
        return map;
    }

    public boolean updateData(String token, String resourceId, RowData rowData) {
        // 获取到与资源对应的表的信息
        TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
        String tableEnglishName = table.getEnglishName();  // 获取表名
        logger.debug("####################表名==== " + tableEnglishName);

        // 获取表中字段信息
        String tableId = table.getId();
        List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByResourceId(tableId);
        // 传进来的数据
        List<FieldData> fieldsData = rowData.getFieldsData();
        String rowDataId = rowData.getId();
        if (rowDataId == null) {
            return false;
        }

        StringBuffer sql = new StringBuffer("UPDATE " + tableEnglishName + " SET ");
        for (FieldData data : fieldsData) {
            String columnName = data.getColumnName();
            TDgapDataImportField field = findColumnByName(fieldList, columnName);
            if (field == null) {    // 数据源错误
                throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            }
            // 获取数据字段的内容
            String type = field.getType();
            String value = data.getValue();

            if (value == null || "".equals(value)) {
                String toValue = null;
                sql.append(field.getEnglishName() + "=" + toValue + ", ");
                continue;
            }

            if (Constants.FieldType.文本.type().equals(type)) {
                value = value.contains("'") ? value.replace("'", "''") : value;
                int len = Integer.parseInt(field.getLen());
                if (value.length() <= len) {
                    sql.append(field.getEnglishName() + "='" + value + "', ");
                } else {
                    logger.debug("error_sql:" + sql);
                    throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                }
            } else if (Constants.FieldType.小数.type().equals(type)) {
                if (value.contains(".")) {
                    // 从小数点处截取数据成整数部分和小数部分
                    String[] ar = value.split("\\.");
                    String parti = ar[0];
                    String partf = ar[1]; // 小数位最大4位 验证类型为小数型的数据
                    if (parti.length() > 38 || partf.length() > 4) {
                        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                    }
                    sql.append(field.getEnglishName() + "=" + value + ", ");
                    dwJdbcTemplate.update(sql.toString());
                } else {
                    throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                }
            }else if (Constants.FieldType.数值.type().equals(type)) {
                int len = Integer.parseInt(field.getLen());
                if (value.length() <= len && value.length() >= 1) {
                    sql.append(field.getEnglishName() + "=" + value + ", ");
                } else {
                    throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                }
            }else if (Constants.FieldType.时间.type().equals(type)) {
                DateFormat formatter = new SimpleDateFormat("yyyy MM dd");
                //value = value.endsWith(".0") ? value.substring(0, value.lastIndexOf(".")) : value;
                if (value.contains("/")) {
                    String deleteString = "";
                    for (int i = 0; i < value.length(); i++) {
                        deleteString = value.charAt(i) != '/'?deleteString+value.charAt(i):deleteString +" ";
                    }
                    value = deleteString;
                } else {
                    value=value.substring(0,19);
                    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                try {
                    // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
                    formatter.setLenient(false);
                    Date parse = formatter.parse(value);
                    if (parse instanceof Date) {
                        sql.append(field.getEnglishName() + "= to_date('" + value + "','yyyy-MM-dd hh24:mi:ss'), ");
                    } else {
                        throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                    }
                } catch (Exception e) {
                    logger.error("", e);
                    throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                }
            } else if (Constants.FieldType.时间戳.type().equals(type)) {
                // 先判断数据类型
                if (value.length() >= 13) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(Long.parseLong(value));
                    String dateStr = format.format(date);
                    sql.append(field.getEnglishName() + "= to_timestamp('" + dateStr + "','yyyy-MM-dd hh24:mi:ss'), ");
                } else {
                    throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                }
            }

            // 验证数据类型为二进制数据的数据
            if (Constants.FieldType.二进制数据.type().equals(type)) {
                StringBuffer bin_sql = new StringBuffer("UPDATE " + tableEnglishName + " SET ");
                if (value != null && value.length() != 0) {
                    if (prefixToken) {
                        bin_sql.append("\"" + field.getEnglishName() + "\"").append("= ?").append("  WHERE ID = ").append("'").append(token + "-" + rowDataId).append("'");
                    } else {
                        bin_sql.append("\"" + field.getEnglishName() + "\"").append("= ?").append("  WHERE ID = ").append("'").append(rowDataId).append("'");
                    }
                    byte[] decoded = bs.decodeBase64(value);
                    dwJdbcTemplate.execute(bin_sql.toString(), new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                        @Override
                        protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                            try {
                                lobCreator.setBlobAsBytes(ps, 1, decoded);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
        //去除掉set子句里的最后一个逗号
        if(sql.toString().endsWith(", ")){
            sql.delete(sql.lastIndexOf(","),sql.length());
        }
        if (prefixToken) {
            sql.append("  WHERE ID = ").append("'").append(token + "-" + rowDataId).append("'");
        } else {
            sql.append("  WHERE ID = ").append("'").append(rowDataId).append("'");
        }
        int result = dwJdbcTemplate.update(sql.toString());
        return result>0;
    }


    public String deleteData(String token, String resourceId, RowData rowData, Constants.DataImportOp op) {
        String uuid = UUIDUtil.getUUID();
        // 获取到与资源对应的表的信息
        TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
        // 获取表名
        String tableEnglishName = table.getEnglishName();
        logger.debug("####################表名==== " + tableEnglishName);
        String rowDataId = rowData.getId();
        StringBuffer sql = new StringBuffer();
        /** 对进来的数据进行验证 */
        if (prefixToken) {
            sql.append("DELETE FROM " + tableEnglishName).append("  WHERE ID = ").append("'").append(token + "-" + rowDataId).append("'");
        } else {
            sql.append("DELETE FROM " + tableEnglishName).append("  WHERE ID = ").append("'").append(rowDataId).append("'");
        }

        // 获取当前执行方法的名称
        System.out.println("拼接的SQL：" + sql.toString());
        StringEscapeUtils.escapeSql(sql.toString());
        return sql.toString();
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

    //获取大数据字段的值 的集合
    private List<String> getBIgDataList(String resourceId, RowData rowData) {
        List<FieldData> fieldsData = rowData.getFieldsData();
        // 获取到与资源对应的表的信息
        TDgapDataImportTable table = dataImportExpandMapper.getTableNameByResourceId(resourceId);
        // 获取表名
        String tableId = table.getId();
        // 获取表中字段信息
        List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByResourceId(tableId);
        List<String> bigdataValues = new ArrayList<String>();
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
                if (value != null && value.length() != 0) {
                    bigdataValues.add(value);
                }
            }
        }
        return bigdataValues;
    }
}
