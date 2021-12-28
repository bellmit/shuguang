package com.sofn.service.dgap;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.TDgapDataImportFieldDto;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.provider.dgap.TDgapDataImportFieldProvider;
import java.util.List;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/11/23. */
@Service
public class TDgapDataImportFieldService
    extends BaseService<TDgapDataImportFieldProvider, TDgapDataImportField> {
  @DubboReference
  public void setProvider(TDgapDataImportFieldProvider provider) {
    this.provider = provider;
  }

  /**
   * 查询对应表的所有字段信息
   *
   * @param id
   * @return
   */
  public List<TDgapDataImportFieldDto> list(String id) {
    return provider.selectAllField(id);
  }

  public List<TDgapDataImportField> getAllField(String id) {
    return provider.getAllField(id);
  }

  public List<String> getAllEnglishName(String id) {
    return provider.getAllEnglishName(id);
  }
}
