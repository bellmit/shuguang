package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.dgap.TDgapDataImportFieldDto;
import com.sofn.model.generator.TDgapDataImportField;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 由MyBatis Generator工具自动生成，请不要手动修改 */
@MyBatisDao
public interface TDgapDataImportFieldExpandMapper extends BaseMapper {

  List<TDgapDataImportField> getFieldsByTable(@Param("tableId") String tableId);

  /**
   * 查询字段的中文名
   *
   * @return
   */
  List<String> selectChineseName(@Param("id") String id);

  /**
   * 查询字段的英文名
   *
   * @return
   */
  List<String> selectEnglishName(@Param("id") String id);

  /**
   * 查询字段的类型
   *
   * @return
   */
  List<String> selectType(@Param("id") String id);

  /**
   * 查询字段的长度
   *
   * @return
   */
  List<String> selectLen(@Param("id") String id);

  List<TDgapDataImportField> getByFieldNames(@Param("fieldNames") String[] fieldNames);

  List<TDgapDataImportField> getByFieldName(@Param("fieldIds") String[] fieldIds);

  List<TDgapDataImportFieldDto> selectAllField(String id);

  List<TDgapDataImportField> getAllField(String id);

  List<TDgapDataImportField> getField(String id);

  public List<String> getAllEnglishName(String id);
}
