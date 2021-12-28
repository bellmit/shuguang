package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import java.util.List;

/** Created by weiq on 2016/12/6. */
@MyBatisDao
public interface DataImportExpandMapper extends BaseExpandMapper {
  TDgapDataImportTable getTableNameByResourceId(String resourceId);

  List<TDgapDataImportField> getFieldNameByResourceId(String tableId);

  List<TDgapDataImportField> getFieldNameByTableName(String tableName);
  //根据表名得到导入资源id
  TDgapDataImportTable getPreResourceId(String tableName);
}
