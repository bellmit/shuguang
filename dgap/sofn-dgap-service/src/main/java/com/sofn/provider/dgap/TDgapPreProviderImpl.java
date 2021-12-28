package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.PreExpandDao;
import com.sofn.dao.dgap.TDgapDataImportFieldExpandMapper;
import com.sofn.dao.dgap.TDgapDataImportTableExpandMapper;
import com.sofn.dao.generator.TDgapDataImportTableMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * author tianjie
 *
 * @date 2016/11/23
 */
@DubboService(interfaceClass = TDgapPreProvider.class)
public class TDgapPreProviderImpl implements TDgapPreProvider {

  @Autowired private PreExpandDao preExpandDao;

  @Autowired private TDgapDataImportTableExpandMapper tDgapDataImportTableExpandMapper;

  @Autowired private TDgapDataImportTableMapper tDgapDataImportTableMapper;

  @Autowired private TDgapDataImportFieldExpandMapper tDgapDataImportFieldExpandMapper;


  @Override
  public List<Map<String, Object>> getDatas(
          String tableName, String mark) {
    return preExpandDao.getDatas(tableName, mark);
  }

  @Override
  public List<String> getAllTableNames(String pattern) {
    return preExpandDao.getAllTableNames(pattern);
  }

  @Override
  public List<Map<String, Object>>  getAllDatas(String tableName) {
    return preExpandDao.getAllDatas(tableName);
  }
}
