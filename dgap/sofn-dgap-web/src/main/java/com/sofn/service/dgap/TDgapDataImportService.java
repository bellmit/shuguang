package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.provider.dgap.TDgapDataImportProvider;
import com.sofn.provider.dgap.TDgapDataImportTableProvider;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Created by Administrator on 2016/11/23. */
@Service
public class TDgapDataImportService {
  @DubboReference private TDgapDataImportProvider tDgapDataImportProvider;

  @DubboReference private TDgapDataImportTableProvider tDgapDataImportTableProvider;

  @Autowired private TDgapDataImportTableService tDgapDataImportTableService;

  @Autowired private TDgapDataImportFieldService tDgapDataImportFieldService;

  /**
   * 新建表
   *
   * @param id
   * @param fieldList
   */
  @Transactional
  public void createTable(String id, List<TDgapDataImportField> fieldList) {
    try {
      TDgapDataImportTable dataImportTable = tDgapDataImportTableProvider.getDataById(id);
      tDgapDataImportProvider.createTable(id);
      tDgapDataImportProvider.createWsTable(id);
      dataImportTable.setStatus(Constants.DataImportTableStatus.存在.code());
      dataImportTable.setId(dataImportTable.getId());
      tDgapDataImportTableService.updateStatus(dataImportTable);
      for (TDgapDataImportField field : fieldList) {
        field.setStatus(Constants.DataImportFieldStatus.存在.code());
        tDgapDataImportFieldService.update(field);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 删除表
   *
   * @param id
   * @throws SQLException
   */
  @Transactional
  public void deleteTable(String id, List<TDgapDataImportField> allField) {
    try {
      try {
        tDgapDataImportProvider.deleteTable(id);
      }catch (Exception e){
        e.printStackTrace();
      }
      TDgapDataImportTable dataImportTable = tDgapDataImportTableService.getDataById(id);
      dataImportTable.setStatus(Constants.DataImportTableStatus.不存在.code());
      tDgapDataImportTableService.updateStatus(dataImportTable);
      for (TDgapDataImportField field : allField) {
        if (field.getStatus().equals(Constants.DataImportFieldStatus.存在.code())) {
          field.setStatus(Constants.DataImportFieldStatus.不存在.code());
          tDgapDataImportFieldService.update(field);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
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
  public String checkStatus(String enName, String dataId) throws SQLException {
    String status = tDgapDataImportProvider.checkStatus(enName, dataId);
    return status;
  }

  /**
   * 审核数据
   *
   * @param tableName
   * @param status
   * @param dataId
   * @throws SQLException
   */
  public void updateDataStatus(String tableName, String status, String dataId) throws SQLException, DataImportApplyExeption {
    tDgapDataImportProvider.updateDataStatus(tableName, status, dataId);
  }

  /**
   * 分页查询
   *
   * @param start
   * @param length
   * @param status
   * @param tableName
   * @return
   * @throws SQLException
   */
  public PageInfo<Map<String, Object>> dataList(
      Integer start, Integer length, String status, String tableName) throws SQLException {
    PageInfo<Map<String, Object>> list =
        tDgapDataImportProvider.dataList(start, length, status, tableName);
    return list;
  }

  /**
   * 分页查询
   *
   * @param start
   * @param length
   * @param status
   * @param tableName
   * @return
   * @throws SQLException
   */
  public PageInfo<Map<String, Object>> dataList(
      Integer start, Integer length, String status, String tableName, String conditions)
      throws SQLException {
    // leftKey用于装key，rightValue用于装value
    List<String> leftKey = new ArrayList<>();
    List<String> rightValue = new ArrayList<>();
    if (!conditions.equals("")) {
      // 传过来的多条件用,分隔，再把=左边的放在left里面，右边的放在right里面
      String[] ss = conditions.split(",");
      for (int i = 0; i < ss.length; i++) {
        leftKey.add(ss[i].substring(0, ss[i].indexOf("=")));
        rightValue.add(ss[i].substring(ss[i].indexOf("=") + 1, ss[i].length()));
      }
    }
    PageInfo<Map<String, Object>> list =
        tDgapDataImportProvider.dataList(start, length, status, tableName, leftKey, rightValue);
    return list;
  }

  public Map<String,Object> getDataById(String tableName, String id) {
    Map<String,Object> data =
            tDgapDataImportProvider.getDataById(tableName,id);
    return  data;
  }
}
