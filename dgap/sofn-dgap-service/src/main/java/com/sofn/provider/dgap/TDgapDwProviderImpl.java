package com.sofn.provider.dgap;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.DwExpandDao;
import com.sofn.dao.dgap.TDgapDataImportFieldExpandMapper;
import com.sofn.dao.dgap.TDgapDataImportTableExpandMapper;
import com.sofn.dao.generator.TDgapDataImportTableMapper;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author tianjie
 *
 * @date 2016/11/23
 */
@DubboService(interfaceClass = TDgapDwProvider.class)
public class TDgapDwProviderImpl implements TDgapDwProvider{

  @Autowired private DwExpandDao dwExpandDao;

  @Autowired private TDgapDataImportTableExpandMapper tDgapDataImportTableExpandMapper;

  @Autowired private TDgapDataImportTableMapper tDgapDataImportTableMapper;

  @Autowired private TDgapDataImportFieldExpandMapper tDgapDataImportFieldExpandMapper;

  @Override
  public List<TDgapDataImportTable> getValidWdTables(String parttern) {
    List<String> list = dwExpandDao.listDwTables(parttern);
    List<TDgapDataImportTable> list2 = tDgapDataImportTableExpandMapper.selectByName(parttern);
    List<TDgapDataImportTable> validtables = new ArrayList<>();
    for (TDgapDataImportTable table : list2) {
      if (list.contains(table.getEnglishName())) {
        validtables.add(table);
      }
    }
    return validtables;
  }

  @Override
  public List<TDgapDataImportField> getFieldsByTable(String tableId) {
    TDgapDataImportTable table = tDgapDataImportTableMapper.selectByPrimaryKey(tableId);
    String tableName = table.getEnglishName().toUpperCase();
    List<String> list = dwExpandDao.listDwTableFields(tableName);
    List<TDgapDataImportField> fields = tDgapDataImportFieldExpandMapper.getFieldsByTable(tableId);
    List<TDgapDataImportField> validfields = new ArrayList<>();
    for (TDgapDataImportField field : fields) {
      if (list.contains(field.getEnglishName().toUpperCase())) {
        validfields.add(field);
      }
    }
    return validfields;
  }

  @Override
  public List<Map<String, Object>> getDatas(
      String tableId, Integer start, Integer end, String condition) {
    List<TDgapDataImportField> fieldList = getFieldsByTable(tableId);
    String tableName = tDgapDataImportTableMapper.selectByPrimaryKey(tableId).getEnglishName();
    List<String> fields = new ArrayList<String>();
    for (TDgapDataImportField s : fieldList) {
      fields.add(s.getEnglishName());
    }
    return dwExpandDao.getDatas(tableName, start, end, fields, condition);
  }

  @Override
  public List<Map<String, Object>> getDatas(
      String tableName, Integer start, Integer end, List<String> fields, String condition) {
    return dwExpandDao.getDatas(tableName, start, end, fields, condition);
  }

  @Override
  public boolean isNullByDataId(String id,String tableName) {
    return dwExpandDao.isNullByDataId(id,tableName);
  }

  @Override
  public Map<String,Map<String,Object>> getExistedData(String tableName) {
    return dwExpandDao.getExistedData(tableName);
  }

  @Override
  public Long getdataNumber(String tableName, String condition) {
    return dwExpandDao.getdataNumber(tableName,condition);
  }
}
