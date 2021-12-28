package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapDataImportTableExpandMapper;
import com.sofn.dao.generator.TDgapDataImportTableMapper;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.service.ListTables;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/** Created by Administrator on 2016/11/23. */
@DubboService(interfaceClass = TDgapDataImportTableProvider.class)
public class TDgapDataImportTableProviderImpl extends BaseProviderImpl<TDgapDataImportTable>
    implements TDgapDataImportTableProvider {
  @Autowired private TDgapDataImportTableExpandMapper tDgapDataImportTableExpandMapper;

  @Autowired private TDgapDataImportTableMapper tDgapDataImportTableMapper;

  @Resource(name = "preJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  public TDgapDataImportTableProviderImpl() throws SQLException {}

  /**
   * 根据资源ID得到表名
   *
   * @param id
   * @return
   */
  @Override
  public String selectENName(String id) {
    return tDgapDataImportTableExpandMapper.selectTableEnglishName(id);
  }

  @Override
  public List<String> getAlltableNames(String loginedUserId) {
    return tDgapDataImportTableExpandMapper.getAlltableNames(loginedUserId);
  }

  /**
   * 根据表名检查表中是否有数据
   *
   * @param enName
   * @return
   * @throws SQLException
   */
  @Override
  public Integer selectData(String enName) throws SQLException {
    // 建表类
    ListTables listTables = new ListTables();
    String countSql = listTables.getCount(enName);
      Integer count = jdbcTemplate.queryForObject(countSql, Integer.class);
    return count;
  }

  /**
   * 检查表是否存在的状态
   *
   * @param id
   * @return
   */
  @Override
  public String checkStatus(String id) {
    return tDgapDataImportTableExpandMapper.checkStatus(id);
  }

  /**
   * 根据资源ID获得改ID对应的表名信息
   *
   * @param id
   * @return
   */
  @Override
  public TDgapDataImportTable getDataById(String id) {
    return tDgapDataImportTableExpandMapper.getDataById(id);
  }

  /**
   * 更新表是否存在的状态
   *
   * @param tDgapDataImportTable
   */
  @Override
  public void updateStatus(TDgapDataImportTable tDgapDataImportTable) {
    tDgapDataImportTableExpandMapper.updateStatus(tDgapDataImportTable);
  }

  @Override
  public List<String> getAllTables(String id) {
    List<String> allTables = tDgapDataImportTableExpandMapper.getAllTables(id);
    return allTables;
  }

  @Override
  public TDgapDataImportTable getImportDataById(String id) {
    return tDgapDataImportTableMapper.selectByPrimaryKey(id);
  }
}
