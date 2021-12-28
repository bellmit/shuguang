package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.TDgapDataImportTable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 由MyBatis Generator工具自动生成，请不要手动修改 */
@MyBatisDao
public interface TDgapDataImportTableExpandMapper extends BaseMapper {
  /**
   * 查询表的中文名
   *
   * @return
   */
  String selectTableChineseName(String id);

  /**
   * 查询表的英文名
   *
   * @return
   */
  String selectTableEnglishName(String id);

  /**
   * 获取所有表名
   * @return
   */
  List<String> getAlltableNames(String loginedUserId);

  /**
   * 查询表是否存在的状态
   *
   * @param id
   * @return
   */
  String checkStatus(String id);

  TDgapDataImportTable getDataById(String id);

  void updateStatus(TDgapDataImportTable tDgapDataImportTable);

  List<TDgapDataImportTable> selectByName(@Param("tableName") String tableName);

  List<String> getAllTables(String id);
}
