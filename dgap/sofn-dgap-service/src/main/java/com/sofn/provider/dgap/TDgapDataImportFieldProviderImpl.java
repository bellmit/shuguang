package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapDataImportFieldExpandMapper;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.TDgapDataImportFieldDto;
import com.sofn.model.generator.TDgapDataImportField;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by Administrator on 2016/11/23. */
@DubboService(interfaceClass = TDgapDataImportFieldProvider.class)
public class TDgapDataImportFieldProviderImpl extends BaseProviderImpl<TDgapDataImportField>
    implements TDgapDataImportFieldProvider {

  @Autowired private TDgapDataImportFieldExpandMapper tDgapDataImportFieldExpandMapper;

  //        @Override
  //    public List<TDgapDataImportField> getByFieldNames(String... fieldNames) {
  //        StringBuilder sb = new StringBuilder();
  //        for(String fieldName :fieldNames ){
  //            sb.append(fieldName).append(",");
  //        }
  //        sb.setLength(sb.length()-1);
  //        return tDgapDataImportFieldExpandMapper.getFieldsByTable(sb.toString());
  //    }
  @Override
  public List<TDgapDataImportField> getByFieldNames(String[] fieldIds) {
    return tDgapDataImportFieldExpandMapper.getByFieldName(fieldIds);
  }

  @Override
  public List<TDgapDataImportField> getByTableId(String tableId) {
    return tDgapDataImportFieldExpandMapper.getFieldsByTable(tableId);
  }

  /**
   * 查询对应表的所有字段信息
   *
   * @param id
   * @return
   */
  @Override
  public List<TDgapDataImportFieldDto> selectAllField(String id) {
    List<TDgapDataImportFieldDto> list = tDgapDataImportFieldExpandMapper.selectAllField(id);
    for (TDgapDataImportFieldDto fieldDto : list) {
      for (Constants.FieldType fieldType : Constants.FieldType.values()) {
        if (fieldType.type().equals(fieldDto.getType())) {
          fieldDto.setTypeName(fieldType.name());
        }
      }
      for (Constants.DataImportFieldStatus fieldStatus : Constants.DataImportFieldStatus.values()) {
        if (fieldStatus.code().equals(fieldDto.getStatus())) {
          fieldDto.setStatusText(fieldStatus.name());
        }
      }
    }
    return list;
  }

  @Override
  public List<TDgapDataImportField> getAllField(String id) {
    return tDgapDataImportFieldExpandMapper.getField(id);
  }

  @Override
  public List<String> getAllEnglishName(String id) {
    return tDgapDataImportFieldExpandMapper.getAllEnglishName(id);
  }
}
