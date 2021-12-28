package com.sofn.service.dgap;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.provider.dgap.TDgapDataImportTableProvider;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/11/23. */
@Service
public class TDgapDataImportTableService
    extends BaseService<TDgapDataImportTableProvider, TDgapDataImportTable> {
  @DubboReference private TDgapDataImportTableProvider tDgapDataImportTableProvider;

  @DubboReference
  public void setProvider(TDgapDataImportTableProvider provider) {
    this.provider = provider;
  }

  /**
   * 根据资源ID得到表名
   *
   * @param id
   * @return
   */
  public String selectENName(String id) {
    return tDgapDataImportTableProvider.selectENName(id);
  }

  /**
   * 根据表名检查表中是否有数据
   *
   * @param enName
   * @return
   * @throws SQLException
   */
  public Integer selectData(String enName) {
    Integer selectData = null;
    try {
      selectData = tDgapDataImportTableProvider.selectData(enName);
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return selectData;
  }

  /**
   * 检查表是否存在
   *
   * @param id
   */
  public String checkStatus(String id) {
    return tDgapDataImportTableProvider.checkStatus(id);
  }

  /**
   * 根据资源ID获得改ID对应的表名信息
   *
   * @param id
   * @return
   */
  public TDgapDataImportTable getDataById(String id) {
    return tDgapDataImportTableProvider.getDataById(id);
  }

  /**
   * 更新表是否存在的状态
   *
   * @param tDgapDataImportTable
   */
  public void updateStatus(TDgapDataImportTable tDgapDataImportTable) {
    tDgapDataImportTableProvider.updateStatus(tDgapDataImportTable);
  }

  public List<String> getAllTables(String id) {
    List<String> allTables = tDgapDataImportTableProvider.getAllTables(id);
    return allTables;
  }
}
