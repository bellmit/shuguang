package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.DataImportExpandMapper;
import com.sofn.dao.dgap.PreExpandDao;
import com.sofn.dao.dgap.TDgapDataImportFieldExpandMapper;
import com.sofn.dao.dgap.TDgapDataImportTableExpandMapper;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.service.DataApplyOnDemandSqlBuilder;
import com.sofn.service.ListTables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Created by Administrator on 2016/11/23. */
@DubboService(interfaceClass = TDgapDataImportProvider.class)
@Transactional("dataImportManager")
public class TDgapDataImportProviderImpl implements TDgapDataImportProvider {

  private static final Logger logger =
            LoggerFactory.getLogger(TDgapDataImportProviderImpl.class.getName());

  @Value("${dataImport.applydataRealtime.enable}")
  String applydataRealtime = "false";

  @Value("${db.dw.schema}")
  String dwSchemaName;

  @Autowired private TDgapDataImportFieldExpandMapper tDgapDataImportFieldExpandMapper;

  @Autowired private TDgapDataImportTableExpandMapper tDgapDataImportTableExpandMapper;

  @Autowired private PreExpandDao preExpandDao;

  @Autowired
  private DataImportExpandMapper dataImportExpandMapper;

  @Resource(name = "preJdbcTemplate")
  private JdbcTemplate preJdbcTemplate;

  @Resource(name = "dwJdbcTemplate")
  private JdbcTemplate dwJdbcTemplate;

  public void createTable(String id) throws SQLException {
    // 建表类
    ListTables listTables = new ListTables();
    // 中文表名
    String tableChineseName = tDgapDataImportTableExpandMapper.selectTableChineseName(id);
    // 英文表名
    String tableEnglishName = tDgapDataImportTableExpandMapper.selectTableEnglishName(id);
    List<String> chineseName = new ArrayList<>();
    List<String> englishName = new ArrayList<>();
    List<String> len = new ArrayList<>();
    List<String> type = new ArrayList<>();
    TDgapDataImportTable dataById = tDgapDataImportTableExpandMapper.getDataById(id);
    List<TDgapDataImportField> allField =
        tDgapDataImportFieldExpandMapper.getAllField(dataById.getId());
    for (TDgapDataImportField field : allField) {
      String status = field.getStatus();
      if (status.equals(Constants.DataImportFieldStatus.不存在.code())) {
        chineseName.add(field.getChineseName());
        englishName.add(field.getEnglishName());
        len.add(field.getLen());
        type.add(field.getType());
      }
    }
    List<String> sql = new ArrayList<String>();
    // 建表，
    String sqlTable = listTables.createDefaultTable(tableEnglishName);
    //表中自增字段mark
    String seqMark= listTables.createseqMark(tableEnglishName) ;
    // 添加字段
    List<String> addField = listTables.addField(tableEnglishName, englishName, len, type);
    // 添加表的注释
    String tableComment = listTables.tableComment(tableEnglishName, tableChineseName);
    // 添加字段的注释
    List<String> filedComment = listTables.fieldComment(tableEnglishName, englishName, chineseName);
    String tableStatus = tDgapDataImportTableExpandMapper.checkStatus(id);
    // 如果表不存在，就添加建表语句及表注释语句，否则不添加
    if (tableStatus.equals(Constants.DataImportTableStatus.不存在.code())) {
      sql.add(sqlTable);
      sql.add(seqMark);
      sql.add(tableComment);
    }

    for (String field : addField) {
      sql.add(field);
    }
    for (String filed : filedComment) {
      sql.add(filed);
    }
    for (String s : sql) {
      preJdbcTemplate.execute(s);
    }
  }

  @Transactional("dataImportManager")
  public void createWsTable(String id) throws SQLException {
    // 建表类
    ListTables listTables = new ListTables();
    // 中文表名
    String tableChineseName = tDgapDataImportTableExpandMapper.selectTableChineseName(id);
    // 英文表名
    String tableEnglishName = tDgapDataImportTableExpandMapper.selectTableEnglishName(id);
    List<String> chineseName = new ArrayList<>();
    List<String> englishName = new ArrayList<>();
    List<String> len = new ArrayList<>();
    List<String> type = new ArrayList<>();
    TDgapDataImportTable dataById = tDgapDataImportTableExpandMapper.getDataById(id);
    List<TDgapDataImportField> allField =
            tDgapDataImportFieldExpandMapper.getAllField(dataById.getId());
    for (TDgapDataImportField field : allField) {
      String status = field.getStatus();
      if (status.equals(Constants.DataImportFieldStatus.不存在.code())) {
        chineseName.add(field.getChineseName());
        englishName.add(field.getEnglishName());
        len.add(field.getLen());
        type.add(field.getType());
      }
    }
    List<String> sql = new ArrayList<String>();
    // 建表，
    String sqlTable = listTables.createDefaultWsTable(tableEnglishName);
    // 添加字段
    List<String> addField = listTables.addField(tableEnglishName, englishName, len, type);
    // 添加表的注释
    String tableComment = listTables.tableComment(tableEnglishName, tableChineseName);
    // 添加字段的注释
    List<String> filedComment = listTables.fieldComment(tableEnglishName, englishName, chineseName);
    String tableStatus = tDgapDataImportTableExpandMapper.checkStatus(id);
    // 如果表不存在，就添加建表语句及表注释语句，否则不添加
    if (tableStatus.equals(Constants.DataImportTableStatus.不存在.code())) {
      sql.add(sqlTable);
      sql.add(tableComment);
    }

    for (String field : addField) {
      sql.add(field);
    }
    for (String filed : filedComment) {
      sql.add(filed);
    }
    for (String s : sql) {
      dwJdbcTemplate.execute(s);
    }
  }

  /**
   * 删除表
   *
   * @param id
   * @throws SQLException
   */
  @Override
  @Transactional("dataImportManager")
  public void deleteTable(String id) throws SQLException {
    // 建表类
    ListTables listTables = new ListTables();
    String tableEnglishName = tDgapDataImportTableExpandMapper.selectTableEnglishName(id);
    String deleteTable = listTables.deleteTable(tableEnglishName);
    String deleteSeq =  listTables.deleteSeqMark(tableEnglishName);
    try {
      preJdbcTemplate.execute(deleteTable);
    }catch(Exception e){
      e.printStackTrace();
    }
    try {
        preJdbcTemplate.execute(deleteSeq);
    }catch(Exception e){
        e.printStackTrace();
    }
    try {
        dwJdbcTemplate.execute(deleteTable);
    }catch(Exception e){
        e.printStackTrace();
    }
  }

  /**
   * 检查数据是否通过审核的状态
   *
   * @param enName
   * @param dataId
   * @return
   * @throws SQLException
   */
  @Override
  public String checkStatus(String enName, String dataId) throws SQLException {
    ListTables listTables = new ListTables();
    String sql = listTables.checkStatus(enName, dataId);
    String status = preJdbcTemplate.queryForObject(sql, java.lang.String.class);
    return status;
  }

  @Override
  public void updateDataStatus(String tableName, String status, String dataId) throws SQLException, DataImportApplyExeption {
    ListTables listTables = new ListTables();
    String updateStatus = listTables.updateStatus(tableName, status, dataId);
    preJdbcTemplate.execute(updateStatus);

    if(!"true".equals(applydataRealtime) && Constants.DataImportStatus.人工审核通过.code().equals(status)){
        String sql = listTables.checkOperate(tableName, dataId);
        String operate = preJdbcTemplate.queryForObject(sql, java.lang.String.class);
        if(Constants.DataImportOp.删除.code().equals(operate)){
            applyDataDelete(tableName,dataId);
        }else if(Constants.DataImportOp.修改.code().equals(operate)){
            applyDataUpdate(tableName,dataId);
        }else if(Constants.DataImportOp.添加.code().equals(operate)
                || Constants.DataImportOp.批量添加.code().equals(operate)){
            applyDataAdd(tableName,dataId);
        }
    }
  }

  /**
   * 分页查询数据
   *
   * @param start
   * @param length
   * @param status
   * @param tableName
   * @return
   */
  @Override
  public PageInfo<Map<String, Object>> dataList(
      Integer start, Integer length, String status, String tableName) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo();
    int startdata = start + 1;
    int end = start + length;
    int pagaNum = ((start + 1) / length) + 1;
    List<Map<String, Object>> list =
        preExpandDao.dataList(startdata, end, status, tableName, null, null);
    int count = preExpandDao.dataCount(status, tableName, null, null);
    pageInfo.setTotal(count);
    pageInfo.setList(list);
    pageInfo.setPageNum(pagaNum);
    return pageInfo;
  }


  public PageInfo<Map<String, Object>> dataList(
      Integer start,
      Integer length,
      String status,
      String tableName,
      List<String> leftKey,
      List<String> rightValue) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo();
    int startdata = start + 1;
    int end = start + length;
    int pagaNum = ((start + 1) / length) + 1;
    List<Map<String, Object>> list =
        preExpandDao.dataList(startdata, end, status, tableName, leftKey, rightValue);
    int count = preExpandDao.dataCount(status, tableName, leftKey, rightValue);
    pageInfo.setTotal(count);
    pageInfo.setList(list);
    pageInfo.setPageNum(pagaNum);
    return pageInfo;
  }

    @Override
    public Map<String, Object> getDataById(String tableName, String id) {
        return preExpandDao.getDataById(tableName,id);
    }

    public DataImportResult applyDataAdd(String tableName, String id) throws DataImportApplyExeption {
        DataImportResult dataImportResult = new DataImportResult();
        try {
            // 获取表中字段信息
            List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByTableName(tableName);

            Map<String,String> insertData = DataApplyOnDemandSqlBuilder.insertData(id,tableName,fieldList, dwSchemaName);
            String sql= insertData.get("sql");
            String bigData=insertData.get("bigData");
            if (sql.length() <= 3) {
                throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            } else {
                if (bigData==null|| bigData.length()<=0) {
                    preJdbcTemplate.execute(sql);
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

  public DataImportResult applyDataUpdate(String tableName, String id) throws DataImportApplyExeption {
        DataImportResult dataImportResult = new DataImportResult();
        try {
            // 获取表中字段信息
            List<TDgapDataImportField> fieldList = dataImportExpandMapper.getFieldNameByTableName(tableName);

            Map<String,String> insertData = DataApplyOnDemandSqlBuilder.updateData(id,tableName,fieldList, dwSchemaName);
            String sql= insertData.get("sql");
            String bigData=insertData.get("bigData");
            if (sql.length() <= 3) {
                throw new RuntimeException(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
            } else {
                if (bigData==null|| bigData.length()<=0) {
                    preJdbcTemplate.execute(sql);
                }
                dataImportResult.setSuccess(true);
            }
        } catch (RuntimeException e) {
            logger.error("", e);
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

  public DataImportResult applyDataDelete(String tableName, String id) throws DataImportApplyExeption {
        DataImportResult dataImportResult = new DataImportResult();
        try {
            String insertData = DataApplyOnDemandSqlBuilder.deleteData(id, tableName, dwSchemaName);

            preJdbcTemplate.execute(insertData);
            dataImportResult.setSuccess(true);
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


}
